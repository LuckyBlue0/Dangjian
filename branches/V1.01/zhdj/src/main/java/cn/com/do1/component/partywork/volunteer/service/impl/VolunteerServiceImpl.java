package cn.com.do1.component.partywork.volunteer.service.impl;

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
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.component.index.membercenter.model.PartyDetailsClientVO;
import cn.com.do1.component.partywork.branch.service.IBranchService;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.service.IDemocrticLifeService;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.partywork.volunteer.dao.IVolunteerDAO;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.partywork.volunteer.service.IVolunteerService;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.qrcode.GenerateQRCode;
import cn.com.do1.component.shyk.meeting.service.IMeetingService;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;
import cn.com.do1.component.shyk.partylecture.service.IPartyLectureService;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* All rights reserved.
* User: ${user}
*/
@Service("volunteerService")
public class VolunteerServiceImpl extends BaseService implements IVolunteerService {
    private final static transient Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);

    private IVolunteerDAO volunteerDAO;
    @Resource
    public void setVolunteerDAO(IVolunteerDAO volunteerDAO) {
        this.volunteerDAO = volunteerDAO;
        setDAO(volunteerDAO);
    }
    @Resource
    private IOrgService orgService;
    @Resource
    private IMeetinguserService meetinguserService;

    public Pager searchVolunteer(Map searchMap,Pager pager) throws Exception, BaseException {
        return volunteerDAO.pageSearchByField(searchMap,pager);
    }
    public Pager searchVolunteerByUserId(Map searchMap,Pager pager) throws Exception, BaseException {
        return volunteerDAO.searchVolunteerByUserId(searchMap,pager);
    }

	@Override
	public VolunteerVO getVolunteerById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return volunteerDAO.getVolunteerById(id);
	}

	@Override
	public void updateVolunteer(TbVolunteerActivityPO volunteerActivityPO) throws Exception, BaseException {
		// TODO Auto-generated method stub
		volunteerDAO.updateData(volunteerActivityPO, false);
	}

	@Override
	public Map<String, Object> saveVolunteerActivityInfo(TbVolunteerActivityPO tbVolunteerActivityPO,String userIds) throws Exception, BaseException{
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		if(tbVolunteerActivityPO != null){
			IUser user = (IUser)DqdpAppContext.getCurrentUser();
			OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				tbVolunteerActivityPO.setOrganizationId(orgVO.getOrganizationId());
			}else if(user.getUsername().equals("admin")){
				logger.info("系统管理员没有归属组织,不需要记录组织");
			}
			if(AssertUtil.isEmpty(tbVolunteerActivityPO.getId())){
				String volunteerActivityId = UUID.randomUUID().toString();
				tbVolunteerActivityPO.setId(volunteerActivityId);
				tbVolunteerActivityPO.setCreateTime(new Date());
				tbVolunteerActivityPO.setCreateUserId(user.getPersonId());
				tbVolunteerActivityPO.setCreateUserName(user.getPersonName());
				tbVolunteerActivityPO.setStatus(0L);
				tbVolunteerActivityPO.setWhetherEnd(0L);
				// 生成二维码,返回二维码存放路径
				StringBuffer params = new StringBuffer();
				params.append("title:").append(tbVolunteerActivityPO.getTitle()).append(";").append("id:").append(tbVolunteerActivityPO.getId());
				String imgPath = GenerateQRCode.getInstance().generateRrCode(
						tbVolunteerActivityPO.getId(), params.toString(),null);
				//生成二维码
				tbVolunteerActivityPO.setQrCode(imgPath);
				this.volunteerDAO.insertData(tbVolunteerActivityPO);
				
				if(!AssertUtil.isEmpty(userIds)){
					String [] userId = userIds.split(",");
					int addUserCount = 0;
					TbMeetingUserPO meetingUser = null;
					for(String id : userId){
						meetingUser = new TbMeetingUserPO();
						meetingUser.setId(UUID.randomUUID().toString());
						meetingUser.setUserId(id);
						meetingUser.setSignInStatus(1L);
						meetingUser.setMeetingId(volunteerActivityId);
						meetingUser.setCreateTime(new Date());
						meetingUser.setForLeaveStatus(0l);
						meetingUser.setSignUpStatus(0l);
						this.volunteerDAO.insert(meetingUser);
						addUserCount++;
					}
					logger.info("本次共新增了"+addUserCount+"个党员。");
				}
				result.put("code", "0");
				result.put("desc", "新增成功!");
			}else{
				this.volunteerDAO.update(tbVolunteerActivityPO, false);
				
				Map userMap = getMeetingUserMap(tbVolunteerActivityPO.getId());
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
							meetingUser.setSignInStatus(1L);
							meetingUser.setMeetingId(tbVolunteerActivityPO.getId());
							meetingUser.setCreateTime(new Date());
							this.volunteerDAO.insert(meetingUser);
							addCount++;
						}
					}
					//之前新增过，现在删除的志愿用户信息
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
					this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbVolunteerActivityPO.getId(),entry.getKey());
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
	
	@Resource
	private IPartyLectureService partyLectureService;
	@Resource
	private IMeetingService meetingService;
	@Resource
	private IBranchService branchService;
	@Resource
	private IVolunteerService volunteerService;
	@Resource
	private IDemocrticLifeService democrticLifeService;
	
	@Override
	public PartyDetailsClientVO getPartyDetails(String id,String userId, Integer type) throws Exception, BaseException {
		// TODO Auto-generated method stub
		PartyDetailsClientVO details = new PartyDetailsClientVO();
		switch (type) {
		case 1://三会一课
			PartyLectureVO partyLectureVO = partyLectureService.searchById(id);
			if(partyLectureVO != null){
				BeanHelper.copyProperties(details, partyLectureVO);
			}else{
				MeetingVO meeting = meetingService.getMeetingById(id);
				BeanHelper.copyProperties(details, meeting);
			}
			break;
		case 2://支部活动
			BranchActivityVO branchActivityVO = branchService.getBranchActivityById(id);
			BeanHelper.copyProperties(details, branchActivityVO);
			break;
		case 3://民主生活会
			DemocrticLifeVO democrticLifeVO = democrticLifeService.getDemocrticLifeById(id);
			BeanHelper.copyProperties(details, democrticLifeVO);
			break;
		case 4://志愿活动
			VolunteerVO volunteerVO= volunteerService.getVolunteerById(id);
			BeanHelper.copyProperties(details, volunteerVO);
		}
		
		//查看用户跟三会一课、支部活动、志愿活动的关系
		TbMeetingUserPO meetingUser = meetinguserService.getMeetingUserByMeetingId(id, userId);
		if(!AssertUtil.isEmpty(meetingUser)){
			details.setSignUpStatus(meetingUser.getSignUpStatus().toString());
			details.setSignInStatus(meetingUser.getSignInStatus().toString());
			details.setForLeaveStatus(meetingUser.getForLeaveStatus().toString());
		}
		
		//获取跟会议有关系的人员
		List<MeetingUserVO> joinUsers = meetinguserService.getMeetingUsersByMeetingId(id);
		
		//报名人数
		int signUpCount = 0;
		
		//签到人数
		int signInCount = 0;
		
		if(!AssertUtil.isEmpty(joinUsers)){
			StringBuffer joinUser = new StringBuffer();
			switch(type){
			case 1:
			case 2:
			case 3://三会一课,支部活动,民主生活会签到人数,参与人员
				for(MeetingUserVO user : joinUsers){
					joinUser.append(user.getName()).append(",");
					if(!AssertUtil.isEmpty(user.getSignInStatus()) && user.getSignInStatus().equals("1")){
						signInCount++;
					}
				}
				break;
			case 4://志愿活动参与人员,报名人数
				for(MeetingUserVO user : joinUsers){
					joinUser.append(user.getName()).append(",");
					if(!AssertUtil.isEmpty(user.getSignUpStatus()) && user.getSignUpStatus().equals("1")){
						signUpCount++;
					}
				}
				break;
			}
			String juser = joinUser.toString();
			if(juser.length()>0){
				juser = juser.substring(0, juser.lastIndexOf(","));
			}
			joinUser = null;
			details.setJoinUser(juser);
		}
		
		details.setType(type.toString());
		details.setSignUpCount(signUpCount+"");
		details.setSignInCount(signInCount+"");
		return details;
	}
	@Override
	public Map<String, Object> saveVolunteerRecordInfo(TbVolunteerActivityPO tbVolunteerActivityPO, String userIds) throws Exception, BaseException {
		Map<String,Object> result = new HashMap<String,Object>();
		String [] uids = null;
		int addCount = 0;
		this.volunteerDAO.update(tbVolunteerActivityPO, false);
		Map userMap = getSupplementUserMap(tbVolunteerActivityPO.getId());
		if(!AssertUtil.isEmpty(userIds)){
			uids = userIds.split(",");
			TbMeetingUserPO meetingUser = null;
			
			for(String id : uids){
				//之前没有这个用户,则新增
				if(!userMap.containsKey(id)){
					meetingUser = new TbMeetingUserPO();
					meetingUser.setId(UUID.randomUUID().toString());
					meetingUser.setUserId(id);
					meetingUser.setMeetingId(tbVolunteerActivityPO.getId());
					meetingUser.setCreateTime(new Date());
					meetingUser.setSignUpStatus(0l);
					meetingUser.setForLeaveStatus(0l);
					meetingUser.setSignInStatus(0l);
					meetingUser.setUserType(0l);//0：党员,1：群众
					meetingUser.setIsSupplement(1l);//是否为补录人员(0：否,1：是)
					this.volunteerDAO.insert(meetingUser);
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
				this.meetinguserService.deleteMeetingByMeetingIdAndUserId(tbVolunteerActivityPO.getId(),entry.getKey());
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
