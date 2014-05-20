package cn.com.do1.component.partywork.branch.service.impl;

import java.sql.SQLException;
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
import cn.com.do1.component.partywork.branch.dao.IBranchDAO;
import cn.com.do1.component.partywork.branch.model.TbActivityUserPO;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.service.IBranchService;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.qrcode.GenerateQRCode;
import cn.com.do1.component.share.sharemanager.model.TbReplyPO;
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
@Service("branchService")
public class BranchServiceImpl extends BaseService implements IBranchService {
    private final static transient Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    private IBranchDAO branchDAO;
    @Resource
    public void setBranchDAO(IBranchDAO branchDAO) {
        this.branchDAO = branchDAO;
        setDAO(branchDAO);
    }
    @Resource
    private IOrgService orgService;
    @Resource
    private IBranchService branchService;
    @Resource
    private IMeetinguserService meetinguserService;

    public Pager searchBranch(Map searchMap,Pager pager) throws Exception, BaseException {
        return branchDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public BranchActivityVO getBranchActivityById(String id) throws Exception, BaseException {
		return branchDAO.getBranchActivityById(id);
	}

	@Override
	public Map<String, Object> saveBranchActivityInfo(TbBranchActivityPO branchActivityPO, String userIds) throws Exception, BaseException {
		Map<String,Object> result = new HashMap<String,Object>();
		if(branchActivityPO != null){
			IUser user = (IUser)DqdpAppContext.getCurrentUser();
			OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				branchActivityPO.setOrganizationId(orgVO.getOrganizationId());
			}else if(user.getUsername().equals("admin")){
				logger.info("系统管理员没有归属组织,不需要记录组织");
			}
			if(branchActivityPO.getSmsNotice() == null){
				branchActivityPO.setSmsNotice(0l);
			}
			if(AssertUtil.isEmpty(branchActivityPO.getId())){
				String branchActivityId = UUID.randomUUID().toString();
				branchActivityPO.setId(branchActivityId);
				branchActivityPO.setCreateTime(new Date());
				branchActivityPO.setWhetherEnd(0l);
				branchActivityPO.setWhetherRecommend(0l);
				branchActivityPO.setCreateUserId(user.getPersonId());
				branchActivityPO.setCreateUserName(user.getPersonName());
				branchActivityPO.setStatus(0l);
				// 生成二维码,返回二维码存放路径
				StringBuffer params = new StringBuffer();
				params.append("title:").append(branchActivityPO.getTitle()).append(";").append("id:").append(branchActivityPO.getId());
				String imgPath = GenerateQRCode.getInstance().generateRrCode(
						branchActivityPO.getId(), params.toString(),null);
				//生成二维码
				branchActivityPO.setQrCode(imgPath);
				this.branchDAO.insertData(branchActivityPO);
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
						meetingUser.setActivityType(3l);//会议类型
						this.branchDAO.insert(meetingUser);
						addUserCount++;
					}
					logger.info("本次共新增了"+addUserCount+"个党员。");
				}
				result.put("code", "0");
				result.put("desc", "新增成功!");
			}else{
				this.branchDAO.update(branchActivityPO, false);
				Map userMap = getMeetingUserMap(branchActivityPO.getId());
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
							meetingUser.setMeetingId(branchActivityPO.getId());
							meetingUser.setCreateTime(new Date());
							meetingUser.setSignUpStatus(0l);
							meetingUser.setForLeaveStatus(0l);
							meetingUser.setSignInStatus(0l);
							meetingUser.setUserType(0l);//0：党员,1：群众
							meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
							meetingUser.setActivityType(3l);//会议类型
							this.branchDAO.insert(meetingUser);
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
					this.meetinguserService.deleteMeetingByMeetingIdAndUserId(branchActivityPO.getId(),entry.getKey());
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
	@Override
	public Map<String, Object> saveBranchActivity(TbBranchActivityPO branchActivityPO, String userIds) throws Exception, BaseException {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		if(branchActivityPO != null){
			IUser user = (IUser)DqdpAppContext.getCurrentUser();
			OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				branchActivityPO.setOrganizationId(orgVO.getOrganizationId());
			}else if(user.getUsername().equals("admin")){
				logger.info("系统管理员没有归属组织,不需要记录组织");
			}
			if(branchActivityPO.getSmsNotice() == null){
				branchActivityPO.setSmsNotice(0l);
			}
			if(AssertUtil.isEmpty(branchActivityPO.getId())){
				String branchActivityId = UUID.randomUUID().toString();
				branchActivityPO.setId(branchActivityId);
				branchActivityPO.setCreateTime(new Date());
				branchActivityPO.setWhetherEnd(0l);
				branchActivityPO.setWhetherRecommend(0l);
				branchActivityPO.setCreateUserId(user.getPersonId());
				branchActivityPO.setCreateUserName(user.getPersonName());
				branchActivityPO.setStatus(0l);
				this.branchDAO.insertData(branchActivityPO);
				
				result.put("code", "0");
				result.put("desc", "新增成功!");
			}else{
				this.branchDAO.update(branchActivityPO, false);
				Map userMap = getMeetingUserMap(branchActivityPO.getId());
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
							meetingUser.setMeetingId(branchActivityPO.getId());
							meetingUser.setCreateTime(new Date());
							meetingUser.setSignUpStatus(0l);
							meetingUser.setForLeaveStatus(0l);
							meetingUser.setSignInStatus(0l);
							meetingUser.setUserType(0l);//0：党员,1：群众
							meetingUser.setIsSupplement(0l);//是否为补录人员(0：否,1：是)
							meetingUser.setActivityType(3l);//会议类型
							this.branchDAO.insert(meetingUser);
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
					this.meetinguserService.deleteMeetingByMeetingIdAndUserId(branchActivityPO.getId(),entry.getKey());
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
				map.put(VO.getUserId(), VO);;
			}
		}
		return map;
	}

	@Override
	public List<TbBranchActivityPO> getNoEndBranchActivity() throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.branchDAO.getNoEndBranchActivity();
	}

	@Override
	public Map<String, Object> saveBranchRecordInfo(TbBranchActivityPO tbBranchActivityPO, String userIds) throws Exception, BaseException {
		Map<String,Object> result = new HashMap<String,Object>();
		String [] uids = null;
		int addCount = 0;
		this.branchDAO.update(tbBranchActivityPO, false);
		Map userMap = getSupplementUserMap(tbBranchActivityPO.getId());
		if(!AssertUtil.isEmpty(userIds)){
			uids = userIds.split(",");
			TbMeetingUserPO meetingUser = null;
			
			for(String id : uids){
				//之前没有这个用户,则新增
				if(!userMap.containsKey(id)){
					meetingUser = new TbMeetingUserPO();
					meetingUser.setId(UUID.randomUUID().toString());
					meetingUser.setUserId(id);
					meetingUser.setMeetingId(tbBranchActivityPO.getId());
					meetingUser.setCreateTime(new Date());
					meetingUser.setSignUpStatus(0l);
					meetingUser.setForLeaveStatus(0l);
					meetingUser.setSignInStatus(0l);
					meetingUser.setUserType(0l);//0：党员,1：群众
					meetingUser.setIsSupplement(1l);//是否为补录人员(0：否,1：是)
					meetingUser.setActivityType(3l);//会议类型
					this.branchDAO.insert(meetingUser);
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
				this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbBranchActivityPO.getId(),entry.getKey());
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

	@Override
	public Pager getBranchActivityByUserid(Map searchValue, Pager pager) throws Exception, BaseException {
		return branchDAO.getBranchActivityByUserid(searchValue,pager);
	}
	 @Override
	   	public void addMyActivity(String createUserId,String content,String title,String startTime,String endTime,String address) throws Exception, BaseException {
	   	
		 TbBranchActivityPO po = new TbBranchActivityPO();	
	   		//发起活动
	   		String id = UUID.randomUUID().toString().toLowerCase();
	   		po.setId(id);
	   		po.setCreateTime(new Date());
	   		po.setCreateUserId(createUserId);
	   		po.setStatus(1l);
	   		po.setTitle(title);
	   		po.setStartTime(startTime);
	   		po.setEndTime(endTime);
	   		po.setAddress(address);
	   		po.setContent(content);
	   		this.branchDAO.insertData(po);
	   		
	   	
	   	}
	 @Override
	   	public void joinMyActivity(String activityId,String userId) throws Exception, BaseException {
		
			TbActivityUserPO po = new TbActivityUserPO();	
	   		//参与活动
	   		po.setId(UUID.randomUUID().toString().toLowerCase());
	   		po.setCreateTime(new Date());
	   		po.setActivityId(activityId);;
	   		po.setUserId(userId);;
	   		this.branchDAO.insertData(po);			
	   	}

	@Override
	public List<BranchActivityVO> getDataByactivityId(String activityId)
			throws Exception, BaseException {
		return this.branchDAO.getDataByactivityId(activityId);
	}
	 public boolean searchDataByUserId(String activityId,String userId)throws Exception, BaseException{
	    	return branchDAO.searchDataByUserId(activityId,userId);
	    }

	@Override
	public List<BranchActivityVO> searchList() throws Exception, BaseException {
		return this.branchDAO.searchList();
	}
}
