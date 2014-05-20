package cn.com.do1.component.shyk.meeting.service.impl;

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
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.qrcode.GenerateQRCode;
import cn.com.do1.component.shyk.meeting.dao.IMeetingDAO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.service.IMeetingService;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* All rights reserved.
* User: ${user}
*/
@Service("meetingService")
public class MeetingServiceImpl extends BaseService implements IMeetingService {
    private final static transient Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

    private IMeetingDAO meetingDAO;
    @Resource
    public void setMeetingDAO(IMeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
        setDAO(meetingDAO);
    }
    @Resource
    private IOrgService orgService;
    @Resource
    private IMeetinguserService meetinguserService;

    public Pager searchMeeting(Map searchMap,Pager pager) throws Exception, BaseException {
        return meetingDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public Map<String, Object> saveMeetingInfo(TbMeetingPO tbMeetingPO, String userIds) throws Exception, BaseException {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		if(tbMeetingPO != null){
			IUser user = (IUser)DqdpAppContext.getCurrentUser();
			OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				tbMeetingPO.setOrganizationId(orgVO.getOrganizationId());
			}else if(user.getUsername().equals("admin")){
				logger.info("系统管理员没有归属组织,不需要记录组织");
			}
			if(tbMeetingPO.getSmsNotice() == null){
				tbMeetingPO.setSmsNotice(0l);
			}
			if(AssertUtil.isEmpty(tbMeetingPO.getId())){
				String meetingId = UUID.randomUUID().toString();
				tbMeetingPO.setId(meetingId);
				tbMeetingPO.setCreateTime(new Date());
				tbMeetingPO.setWhetherEnd(0l);
				tbMeetingPO.setWhetherRecommend(0l);
				tbMeetingPO.setStatus(0l);
				tbMeetingPO.setCreateUserId(user.getPersonId());
				tbMeetingPO.setCreateUserName(user.getPersonName());
				
				

				// 生成二维码,返回二维码存放路径
				StringBuffer params = new StringBuffer();
				params.append("title:").append(tbMeetingPO.getTitle()).append(";").append("id:").append(tbMeetingPO.getId());
				String imgPath = GenerateQRCode.getInstance().generateRrCode(
						tbMeetingPO.getId(), params.toString(),null);
				//生成二维码
				tbMeetingPO.setQrCode(imgPath);
				this.meetingDAO.insertData(tbMeetingPO);
				if(!AssertUtil.isEmpty(userIds)){
					String [] userId = userIds.split(",");
					int addUserCount = 0;
					TbMeetingUserPO meetingUser = null;
					for(String id : userId){
						meetingUser = new TbMeetingUserPO();
						meetingUser.setId(UUID.randomUUID().toString());
						meetingUser.setUserId(id);
						meetingUser.setMeetingId(meetingId);
						meetingUser.setCreateTime(new Date());
						meetingUser.setSignUpStatus(0l);
						meetingUser.setForLeaveStatus(0l);
						meetingUser.setSignInStatus(0l);
						meetingUser.setUserType(0l);//0：党员,1：群众
						meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
						meetingUser.setActivityType(1l);//会议类型
						this.meetingDAO.insert(meetingUser);
						addUserCount++;
					}
					logger.info("本次共新增了"+addUserCount+"个党员。");
				}
				result.put("code", "0");
				result.put("desc", "新增成功!");
			}else{
				this.meetingDAO.update(tbMeetingPO, false);
				Map userMap = getMeetingUserMap(tbMeetingPO.getId());
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
							meetingUser.setMeetingId(tbMeetingPO.getId());
							meetingUser.setCreateTime(new Date());
							meetingUser.setSignUpStatus(0l);
							meetingUser.setForLeaveStatus(0l);
							meetingUser.setSignInStatus(0l);
							meetingUser.setUserType(0l);//0：党员,1：群众
							meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
							meetingUser.setActivityType(1l);//会议类型
							this.meetingDAO.insert(meetingUser);
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
					this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbMeetingPO.getId(),entry.getKey());
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
	
	public Map<String,MeetingUserVO> getMeetingUserMap(String meetingId) throws Exception, BaseException{
		Map<String,MeetingUserVO> map = new HashMap<String,MeetingUserVO>();
		
		List<MeetingUserVO> list = this.meetinguserService.getMeetingUsersByMeetingId(meetingId);
		if(!AssertUtil.isEmpty(list)){
			for(MeetingUserVO VO : list){
				map.put(VO.getUserId(), VO);
			}
		}
		return map;
	}

	@Override
	public MeetingVO getMeetingById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.meetingDAO.getMeetingById(id);
	}

	@Override
	public void endMeetingProcess() throws Exception, BaseException {
		// TODO Auto-generated method stub
		List<MeetingVO> meetings = meetingDAO.getNoEndMeetings(1);
		List<MeetingVO> partyLectures = meetingDAO.getNoEndMeetings(2);
		List<MeetingVO> branchs = meetingDAO.getNoEndMeetings(3);
		List<MeetingVO> volunteers = meetingDAO.getNoEndMeetings(4);
		List<MeetingVO> democrticlifes = meetingDAO.getNoEndMeetings(5);
		if(!AssertUtil.isEmpty(meetings)){
			for(MeetingVO m : meetings){
				TbMeetingPO PO = new TbMeetingPO();
				PO.setId(m.getId());
				PO.setWhetherEnd(1L);
				meetingDAO.updateData(PO, false);
				logger.info("-----------党会["+m.getTitle()+"]已结束..");
			}
		}
		if(!AssertUtil.isEmpty(partyLectures)){
			for(MeetingVO p : partyLectures){
				TbPartyLecturePO PO = new TbPartyLecturePO();
				PO.setId(p.getId());
				PO.setWhetherEnd(1L);
				meetingDAO.updateData(PO, false);
				logger.info("-----------党课["+p.getTitle()+"]已结束..");
			}
		}
		if(!AssertUtil.isEmpty(branchs)){
			for(MeetingVO b : branchs){
				TbBranchActivityPO PO = new TbBranchActivityPO();
				PO.setId(b.getId());
				PO.setWhetherEnd(1L);
				meetingDAO.updateData(PO, false);
				logger.info("-----------支部活动["+b.getTitle()+"]已结束...");
			}
		}
		if(!AssertUtil.isEmpty(volunteers)){
			for(MeetingVO v : volunteers){
				TbVolunteerActivityPO PO = new TbVolunteerActivityPO();
				PO.setId(v.getId());
				PO.setWhetherEnd(1L);
				meetingDAO.updateData(PO, false);
				logger.info("-----------志愿活动["+v.getTitle()+"]已结束...");
			}
		}
		if(!AssertUtil.isEmpty(democrticlifes)){
			for(MeetingVO d : democrticlifes){
				TbDemocraticLifePO PO = new TbDemocraticLifePO();
				PO.setId(d.getId());
				PO.setWhetherEnd(1L);
				meetingDAO.updateData(PO, false);
				logger.info("-----------民主生活会["+d.getTitle()+"]已结束...");
			}
		}
	}

	@Override
	public List<MeetingVO> getNoEndMeetings(int type) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.meetingDAO.getNoEndMeetings(type);
	}

	@Override
	public Map<String, Object> saveMeetingRecordInfo(TbMeetingPO tbMeetingPO, String userIds) throws Exception, BaseException {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		String [] uids = null;
		int addCount = 0;
		this.meetingDAO.update(tbMeetingPO, false);
		Map userMap = getSupplementUserMap(tbMeetingPO.getId());
		if(!AssertUtil.isEmpty(userIds)){
			uids = userIds.split(",");
			TbMeetingUserPO meetingUser = null;
			
			for(String id : uids){
				//之前没有这个用户,则新增
				if(!userMap.containsKey(id)){
					meetingUser = new TbMeetingUserPO();
					meetingUser.setId(UUID.randomUUID().toString());
					meetingUser.setUserId(id);
					meetingUser.setMeetingId(tbMeetingPO.getId());
					meetingUser.setCreateTime(new Date());
					meetingUser.setSignUpStatus(0l);
					meetingUser.setForLeaveStatus(0l);
					meetingUser.setSignInStatus(0l);
					meetingUser.setUserType(0l);//0：党员,1：群众
					meetingUser.setIsSupplement(1l);//是否为补录人员(0：否,1：是)
					meetingUser.setActivityType(1l);//会议类型
					this.meetingDAO.insert(meetingUser);
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
				this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbMeetingPO.getId(),entry.getKey());
				delCount++;
			}
			logger.info("本次编辑,删除了"+delCount+"个之前添加的党员");
		}
		result.put("code", "0");
		result.put("desc", "保存成功!");
		return result;
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
