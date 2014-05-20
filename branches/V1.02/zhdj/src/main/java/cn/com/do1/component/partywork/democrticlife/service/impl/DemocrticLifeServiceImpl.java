package cn.com.do1.component.partywork.democrticlife.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.partywork.democrticlife.dao.IDemocrticLifeDAO;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.democrticlife.service.IDemocrticLifeService;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.qrcode.GenerateQRCode;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("democrticLifeService")
public class DemocrticLifeServiceImpl extends BaseService implements IDemocrticLifeService {
    private final static transient Logger logger = LoggerFactory.getLogger(DemocrticLifeServiceImpl.class);

    private IDemocrticLifeDAO democrticLifeDAO;
    @Resource
    public void setDemocrticLifeDAO(IDemocrticLifeDAO democrticLifeDAO) {
        this.democrticLifeDAO = democrticLifeDAO;
        setDAO(democrticLifeDAO);
    }

    @Resource
    private IOrgService orgService;
    @Resource
    private IMeetinguserService meetinguserService;
    public Pager searchDemocrticLife(Map searchMap,Pager pager) throws Exception, BaseException {
        return democrticLifeDAO.pageSearchByField( searchMap,pager);
    }

	@Override
	public DemocrticLifeVO getDemocrticLifeById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return democrticLifeDAO.getDemocrticLifeById(id);
	}

	@Override
	public Map<String, Object> saveDemocrticLifeRecordInfo(TbDemocraticLifePO tbDemocraticLifePO, String userIds) throws Exception, BaseException {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		String [] uids = null;
		int addCount = 0;
		this.democrticLifeDAO.update(tbDemocraticLifePO, false);
		Map userMap = getSupplementUserMap(tbDemocraticLifePO.getId());
		if(!AssertUtil.isEmpty(userIds)){
			uids = userIds.split(",");
			TbMeetingUserPO meetingUser = null;
			
			for(String id : uids){
				//之前没有这个用户,则新增
				if(!userMap.containsKey(id)){
					meetingUser = new TbMeetingUserPO();
					meetingUser.setId(UUID.randomUUID().toString());
					meetingUser.setUserId(id);
					meetingUser.setMeetingId(tbDemocraticLifePO.getId());
					meetingUser.setCreateTime(new Date());
					meetingUser.setSignUpStatus(0l);
					meetingUser.setForLeaveStatus(0l);
					meetingUser.setSignInStatus(0l);
					meetingUser.setUserType(0l);//0：党员,1：群众
					meetingUser.setIsSupplement(1l);//是否为补录人员(0：否,1：是)
					meetingUser.setActivityType(5l);//会议类型
					this.democrticLifeDAO.insert(meetingUser);
					addCount++;
				}
			}
			//删除之前新增过，现在删除的会议用户信息
			for(String id : uids){
				if(userMap.containsKey(id)){
					userMap.remove(id);
				}
			}
			
			int delCount = 0;
			Iterator<Map.Entry<String, MeetingUserVO>> it = userMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, MeetingUserVO> entry = it.next();
				this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbDemocraticLifePO.getId(),entry.getKey());
				delCount++;
			}
			logger.info("本次编辑,删除了"+delCount+"个之前添加的党员");
		}
		result.put("code", "0");
		result.put("desc", "保存成功!");
		return result;
	}

	@Override
	public Map<String, Object> saveDemocrticLifeServiceInfo(TbDemocraticLifePO tbDemocraticLifePO, String userIds) throws Exception, BaseException {
		Map<String,Object> result = new HashMap<String,Object>();
		if(tbDemocraticLifePO != null){
			IUser user = (IUser)DqdpAppContext.getCurrentUser();
			OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				tbDemocraticLifePO.setOrganizationId(orgVO.getOrganizationId());
			}else if(user.getUsername().equals("admin")){
				logger.info("系统管理员没有归属组织,不需要记录组织");
			}
			if(tbDemocraticLifePO.getSmsNotice() == null){
				tbDemocraticLifePO.setSmsNotice(0l);
			}
			if(AssertUtil.isEmpty(tbDemocraticLifePO.getId())){
				String branchActivityId = UUID.randomUUID().toString();
				tbDemocraticLifePO.setId(branchActivityId);
				tbDemocraticLifePO.setCreateTime(new Date());
				tbDemocraticLifePO.setWhetherEnd(0l);
				tbDemocraticLifePO.setCreateUserId(user.getPersonId());
				tbDemocraticLifePO.setCreateUserName(user.getPersonName());
				tbDemocraticLifePO.setStatus(0l);
				// 生成二维码,返回二维码存放路径
				StringBuffer params = new StringBuffer();
				params.append("title:").append(tbDemocraticLifePO.getTitle()).append(";").append("id:").append(tbDemocraticLifePO.getId());
				String imgPath = GenerateQRCode.getInstance().generateRrCode(
						tbDemocraticLifePO.getId(), params.toString(),null);
				//生成二维码
				tbDemocraticLifePO.setQrCode(imgPath);
				this.democrticLifeDAO.insertData(tbDemocraticLifePO);
				if(!AssertUtil.isEmpty(userIds)){
					String [] userId = userIds.split(",");
					int addUserCount = 0;
					TbMeetingUserPO meetingUser = null;
					for(String id : userId){
						meetingUser = new TbMeetingUserPO();
						meetingUser.setId(UUID.randomUUID().toString());
						meetingUser.setUserId(id);
						meetingUser.setMeetingId(branchActivityId);
						meetingUser.setCreateTime(new Date());
						meetingUser.setForLeaveStatus(0l);
						meetingUser.setSignUpStatus(0l);
						meetingUser.setSignInStatus(0l);
						meetingUser.setUserType(0l);//0：党员,1：群众
						meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
						meetingUser.setActivityType(5l);//会议类型
						this.democrticLifeDAO.insert(meetingUser);
						addUserCount++;
					}
					logger.info("本次共新增了"+addUserCount+"个党员。");
				}
				result.put("code", "0");
				result.put("desc", "新增成功!");
			}else{
				this.democrticLifeDAO.update(tbDemocraticLifePO, false);
				Map userMap = getMeetingUserMap(tbDemocraticLifePO.getId());
				String [] uids = null;
				int addCount = 0;
				if(!AssertUtil.isEmpty(userIds)){
					uids = userIds.split(",");
					TbMeetingUserPO meetingUser = null;
					
					for(String id : uids){
						//之前没有这个用户,则新增
						if(!userMap.containsKey(id)){
							meetingUser = new TbMeetingUserPO();
							meetingUser.setId(UUID.randomUUID().toString());
							meetingUser.setUserId(id);
							meetingUser.setMeetingId(tbDemocraticLifePO.getId());
							meetingUser.setCreateTime(new Date());
							meetingUser.setSignUpStatus(0l);
							meetingUser.setForLeaveStatus(0l);
							meetingUser.setSignInStatus(0l);
							meetingUser.setUserType(0l);//0：党员,1：群众
							meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
							meetingUser.setActivityType(5l);//会议类型
							this.democrticLifeDAO.insert(meetingUser);
							addCount++;
						}
					}
					//删除之前新增过，现在删除的会议用户信息
					for(String id : uids){
						if(userMap.containsKey(id)){
							userMap.remove(id);
						}
					}
				}
				logger.info("本次编辑,共新增了"+addCount+"个党员");
				
				

				
				
				int delCount = 0;
				Iterator<Map.Entry<String, MeetingUserVO>> it = userMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, MeetingUserVO> entry = it.next();
					this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbDemocraticLifePO.getId(),entry.getKey());
					delCount++;
				}
				logger.info("本次编辑,删除了"+delCount+"个之前添加的党员");
				result.put("code", "0");
				result.put("desc", "保存成功!");
			}
		}else{
			result.put("code", "-1");
			result.put("desc", "操作失败,参数为空,非法的请求参数!");
		}
		return result;
	}
	
	
	private Map<String,MeetingUserVO> getMeetingUserMap(String meetingId) throws Exception, BaseException{
		Map<String,MeetingUserVO> map = new HashMap<String,MeetingUserVO>();
		
		List<MeetingUserVO> list = this.meetinguserService.getMeetingUsersByMeetingId(meetingId);
		if(!AssertUtil.isEmpty(list)){
			for(MeetingUserVO VO : list){
				map.put(VO.getUserId(), VO);;
			}
		}
		return map;
	}
	
	/**
	 * 获取补录的人员信息
	 * @param meetingId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	private Map<String,MeetingUserVO> getSupplementUserMap(String meetingId) throws Exception, BaseException{
		Map<String,MeetingUserVO> map = new HashMap<String,MeetingUserVO>();
		
		List<MeetingUserVO> list = this.meetinguserService.getMeetingUsersByMeetingId(meetingId);
		if(!AssertUtil.isEmpty(list)){
			for(MeetingUserVO VO : list){
				if(VO.getIsSupplement() != null && VO.getIsSupplement().equals("1")){
					map.put(VO.getUserId(), VO);
				}
			}
		}
		
		return map;
	}
}
