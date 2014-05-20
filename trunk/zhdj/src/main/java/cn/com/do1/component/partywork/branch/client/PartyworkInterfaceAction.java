package cn.com.do1.component.partywork.branch.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.index.membercenter.model.PartyDetailsClientVO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.branch.vo.PartyMeetingListRequest;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.democrticlife.service.IDemocrticLifeService;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.partywork.volunteer.service.IVolunteerService;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.DateTimeUtil;
import cn.com.do1.component.util.JSONOuter;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PartyworkInterfaceAction extends BaseAction{

	private final static transient Logger logger = LoggerFactory.getLogger(PartyworkInterfaceAction.class);
	private String requestJson;
	@Resource
	private IMeetinguserService meetinguserService;
    @Resource
	private IVolunteerService volunteerService;
    @Resource
    private IScoreruleService scoreruleService;
    @Resource
    private IMembercenterService membercenterService;
    
	@Resource
	private IDemocrticLifeService democrticLifeService;
	
	/**
	 * 签到接口
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "签到成功", faileMsg = "签到失败"))
	public void signIn() throws BaseException, Exception{
		logger.debug("signIn requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id", "userId","type"});
		String userId = request.getUserId();
		String id = request.getId();
		Integer type = Integer.parseInt(request.getType());
		String scoreType ="";
		String title ="";
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		} else if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002","userId参数不能为空");
		} else if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		} else if (AssertUtil.isEmpty(type)) {
			throw new BaseException("9002", "type参数不能为空");
		} else {
			Date endTime = null;
			Date currentTime = DateTimeUtil.getNowDate();
			switch (type) {
			case 1://会议,党课
				TbMeetingPO meeting = meetinguserService.searchByPk(TbMeetingPO.class, id);
				if(meeting != null){
					endTime = meeting.getEndTime();
					title = meeting.getTitle();
				}else{
					TbPartyLecturePO partyLecture = meetinguserService.searchByPk(TbPartyLecturePO.class, id);
					endTime = partyLecture.getEndTime();
					title = partyLecture.getTitle();
				}
				scoreType = "4";//三会一课签到
				break;
			case 2://支部活动
				TbBranchActivityPO branchActivityPO = meetinguserService.searchByPk(TbBranchActivityPO.class, id);
				endTime = branchActivityPO.getEndTime();
				scoreType = "3";//支部活动签到
				title = branchActivityPO.getTitle();
				break;
			case 3://民主生活会
				TbDemocraticLifePO democraticLifePO = meetinguserService.searchByPk(TbDemocraticLifePO.class, id);
				endTime = democraticLifePO.getEndTime();
				scoreType = "5";//民主生活会签到
				title = democraticLifePO.getTitle();
				break;
			case 4://志愿活动
				TbVolunteerActivityPO volunteerActivityPO = meetinguserService.searchByPk(TbVolunteerActivityPO.class, id);
				endTime = volunteerActivityPO.getEndTime();
				scoreType = "6";//志愿活动签到
				title = volunteerActivityPO.getTitle();
				break;
			default://非法请求参数
				throw new BaseException("9002", "type参数不在范围内!");
			}
			if(currentTime.getTime() > endTime.getTime()){
				throw new BaseException("1009", "抱歉,活动已经结束,不能签到!");
			}else{
				TbMeetingUserPO meetingUserPO = this.meetinguserService.getMeetingUserByMeetingId(id, userId);
				if(meetingUserPO == null){
					throw new BaseException("1009", "抱歉,您没有报名,不能进行签到操作!");
				}else{
					if (!AssertUtil.isEmpty(meetingUserPO.getSignInStatus()) && meetingUserPO.getSignInStatus() == 1) {
						throw new BaseException("1009", "您之前已经签到过,不需要重复签到,谢谢!");
					}else{
						meetingUserPO.setSignInTime(new Date());
						meetingUserPO.setSignInStatus(1l);
						meetinguserService.updatePO(meetingUserPO, false);
						
						TbPersonalScoreInfoPO perScore = new TbPersonalScoreInfoPO();
				    	ExItemObj dic = DictOperater.getItem("personalScore", scoreType);
				    	perScore.setId(UUID.randomUUID().toString().toLowerCase());
				    	perScore.setUserId(meetingUserPO.getUserId());
				    	perScore.setScoreType(scoreType);
						TbScoreRulePO scoreRule = this.scoreruleService.searchByType(dic.getFsDictItemId());
						if(null != scoreRule){
							perScore.setScore(Long.parseLong(scoreRule.getScoreValue()));
						}
						perScore.setGetTime(new Date());
						perScore.setScoreDesc("《"+title+"》获得积分");
						this.meetinguserService.insertPO(perScore, true);
					}
				}
			}
		}
	}
	
	/**
	 * 志愿活动报名或取消报名
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void signUp() throws BaseException, Exception {
		logger.debug("signUp requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id", "userId", "type" });
		if (request == null) {
			setActionResult("1002", "摘要加密值有误!");
		} else if (AssertUtil.isEmpty(request.getUserId())) {
			setActionResult("9002", "userId参数不能为空");
		} else if (AssertUtil.isEmpty(request.getId())) {
			setActionResult("9002", "id参数不能为空");
		}else if (AssertUtil.isEmpty(request.getType())) {
			setActionResult("9002", "type参数不能为空");
		} else {
			String meetingId = request.getId();
			String userId = request.getUserId();
			int type = Integer.parseInt(request.getType());
			// 验证志愿活动是否已经开始了,报名只能在活动开始前进行
			TbVolunteerActivityPO volunteerActivity = this.meetinguserService.searchByPk(TbVolunteerActivityPO.class, meetingId);
			TbMeetingUserPO meetingUserPO = this.meetinguserService.getMeetingUserByMeetingId(meetingId, userId);

			switch (type) {
			case 1:// 报名
				if (DateTimeUtil.getNowDate().getTime() > volunteerActivity.getStartTime().getTime()) {
					setActionResult("-1", "抱歉,志愿活动[" + volunteerActivity.getTitle() + "],已经停止报名!");
				} else if (!AssertUtil.isEmpty(meetingUserPO)) {
					if (meetingUserPO.getSignUpStatus() == 1) {
						setActionResult("-1", "您已经报过名,请勿重复报名!");
						logger.warn("重复报名>>>,党员ID==" + meetingUserPO.getUserId());
					} else {
						meetingUserPO.setSignUpStatus(1l);
						meetingUserPO.setSignUpTime(new Date());
						this.meetinguserService.updatePO(meetingUserPO, false);
						setActionResult("0", "报名成功!");
					}
				} else {
					meetingUserPO = new TbMeetingUserPO();
					meetingUserPO.setId(UUID.randomUUID().toString().toLowerCase());
					meetingUserPO.setMeetingId(meetingId);
					meetingUserPO.setUserId(userId);
					meetingUserPO.setSignUpStatus(1l);
					meetingUserPO.setSignUpTime(new Date());
					meetingUserPO.setSignInStatus(0l);
					meetingUserPO.setForLeaveStatus(0l);
					meetingUserPO.setActivityType(5l);
					meetingUserPO.setCreateTime(new Date());
					this.meetinguserService.insertPO(meetingUserPO, false);
					setActionResult("0", "报名成功!");
				}
				break;
			case 2:// 取消报名
				if (AssertUtil.isEmpty(meetingUserPO)) {
					logger.warn("没有报名参加活动的用户正在尝试取消报名>>>,党员ID==" + userId);
					setActionResult("-1", "你没有报过名，不需要取消");
				} else {
					if (DateTimeUtil.getNowDate().getTime() > volunteerActivity.getStartTime().getTime()) {
						setActionResult("-1", "抱歉,志愿活动 " + volunteerActivity + "正在进行中,不能取消报名!");
					}
					meetingUserPO.setSignUpStatus(0l);
					meetingUserPO.setSignUpTime(new Date());
					this.meetinguserService.updatePO(meetingUserPO, false);
					setActionResult("0", "取消报名成功!");
				}
				break;
			default:
				setActionResult("9002", "非法的请求参数type==" + type);
			}
		}
		doJsonOut();
	}
	
	/**
	 * 三课一会,支部活动请假或取消请假
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void askForLeave() throws BaseException, Exception {
		logger.debug("signUp requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id", "userId", "type" });
		if (request == null) {
			setActionResult("1002", "摘要加密值有误!");
		} else if (AssertUtil.isEmpty(request.getUserId())) {
			setActionResult("9002", "userId参数不能为空");
		} else if (AssertUtil.isEmpty(request.getId())) {
			setActionResult("9002", "id参数不能为空");
		} else {
			String meetingId = request.getId();
			String userId = request.getUserId();
			Integer type = Integer.parseInt(request.getType());
			if (type != 1 && type != 2) {
				setActionResult("9002", "非法的请求参数type==" + type);
			}
			TbMeetingUserPO meetingUserPO = this.meetinguserService.getMeetingUserByMeetingId(meetingId, userId);
			if (meetingUserPO == null) {
				logger.warn("没有报名参加活动的用户正在尝试" + (type == 1 ? "请假" : "取消请假") + ">>>,活动ID==+" + meetingId + "党员ID==" + userId);
				setActionResult("-1", "您没有报名参加活动,不能请假或取消请假!");
			} else {
				// 请假
				if (type == 1) {
					if (!AssertUtil.isEmpty(meetingUserPO.getForLeaveStatus()) && meetingUserPO.getForLeaveStatus() == 1) {
						setActionResult("-1", "您已经请过假,请勿重复请假!");
						logger.warn("重复请假>>>,党员ID==" + meetingUserPO.getUserId());
					} else {
						meetingUserPO.setForLeaveStatus(1l);
						meetingUserPO.setForLeaveTime(new Date());
						this.meetinguserService.updatePO(meetingUserPO, false);
						setActionResult("0", "请假成功!");
					}
				}

				// 取消请假
				else if (type == 2) {
					if (!AssertUtil.isEmpty(meetingUserPO.getForLeaveStatus()) && meetingUserPO.getForLeaveStatus() == 0) {
						setActionResult("-1", "您已经取消过请假,请勿重复取消!");
						logger.warn("重复取消请假>>>,党员ID==" + meetingUserPO.getUserId());
					} else {
						meetingUserPO.setForLeaveStatus(0l);
						meetingUserPO.setForLeaveTime(new Date());
						this.meetinguserService.updatePO(meetingUserPO, false);
						setActionResult("0", "取消请假成功!");
					}
				}
			}
		}
		doJsonOut();
	}
	
	/**
	 * 三会一课列表查询
	 * @param userId  用户ID
	 * @param keyword 关键字包括标题、发起人
	 * @param type	  1：我的党课,2：我的会议
	 * @throws BaseException 
	 * @throws Exception 
     * @parma status  0：待办，1：已办
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void partyMeetingList() throws BaseException, Exception{
		logger.debug("partyMeetingList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","keyword","type","status","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}

		
		if (AssertUtil.isEmpty(request.getUserId())) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getStatus())) {
			throw new BaseException("9002", "status参数不能为空");
		}
		if(!"1".equals(request.getType()) && !"2".equals(request.getType())){
			throw new BaseException("9002", "type参数不在范围内");
		}
		
		String userId = request.getUserId();
		String keyword = request.getKeyword();
		String type = request.getType();
		String status = request.getStatus();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        
		if("0".equals(status)){//待办
			getSearchValue().put("whetherEnd", 0);
			getSearchValue().put("singInStatus", 0);
			getSearchValue().put("forLeaveStatus", 0);
		}else if("1".equals(status)){
			getSearchValue().put("thirdStatus", 1);
		}
		getSearchValue().put("type", type);//1：会议,2：党课
		getSearchValue().put("keyword", "%"+keyword+"%");
		getSearchValue().put("userid", userId);
		getSearchValue().put("status", "1");//已发布
		getSearchValue().put("mobileClient", "mobileClient");
		pager=membercenterService.getMeetingListByUserid(getSearchValue(),pager);
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	    
		//三会一课待办数量
		List<TbMeetingPO> meetingCount = membercenterService.getMeetingByUserid(userId);
		List<TbPartyLecturePO> partyLectureCount = membercenterService.getPartyLectureByUserid(userId);
		addJsonObj("toDoCount",meetingCount.size()+partyLectureCount.size());
		
	    setOuter(JSONOuter.getInstance());
	}
	
	
	/**
	 * 支部活动列表查询
	 * @param userId  用户ID
	 * @param keyword 关键字包括标题、发起人
	 * @throws BaseException 
	 * @throws Exception 
     * @parma status  0：待办，1：已办
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void branchActivityList() throws BaseException, Exception{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		
		logger.debug("branchActivityList requestJson>>>" + requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","keyword","status","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String userId = request.getUserId();
		String keyword = request.getKeyword();
		String status = request.getStatus();
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(status)) {
			throw new BaseException("9002", "status参数不能为空");
		}
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		}
		if("0".equals(status)){
			getSearchValue().put("whetherEnd", 0);
			getSearchValue().put("singInStatus", 0);
			getSearchValue().put("forLeaveStatus", 0);
		}else if("1".equals(status)){
			getSearchValue().put("thirdStatus", 1);
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("keyword", "%"+keyword+"%");
        getSearchValue().put("userid", userId);
        getSearchValue().put("status", "1");//已发布
        getSearchValue().put("mobileClient", "mobileClient");
		pager=membercenterService.getBranchActivityListByUserid(getSearchValue(),pager);
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
		//支部活动待办数量
		List<BranchActivityVO> listact=membercenterService.getBranchActivityByUserid(userId);
		addJsonObj("toDoCount",listact.size());
	    setOuter(JSONOuter.getInstance());
	}
	
	
	/**
	 * 个人或所有志愿活动列表查询
	 * @param userId  用户ID
	 * @param keyword 关键字包括标题、发起人
	 * @throws BaseException 
	 * @throws Exception 
     * @parma status  0：全部，1：已报名
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void volunteerActivityList() throws Exception, BaseException{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		
		logger.debug("volunteerActivityList requestJson>>>" + requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","keyword","status","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String userId = request.getUserId();
		String keyword = request.getKeyword();
		String status = request.getStatus();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		}

		if (AssertUtil.isEmpty(status)) {
			throw new BaseException("9002", "status参数不能为空");
		}else if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("keyword", "%"+keyword+"%");
		getSearchValue().put("status", "2");//审核已通过
		getSearchValue().put("mobileClient", "mobileClient");
		if("0".equals(status)){//所有志愿活动
			pager = volunteerService.searchVolunteer(getSearchValue(), pager);
		}else if("1".equals(status)){//个人已报名志愿活动
			if (AssertUtil.isEmpty(userId)) {
				throw new BaseException("9002", "userId参数不能为空");
			}
			getSearchValue().put("userid", userId);
			pager = volunteerService.searchVolunteerByUserId(getSearchValue(), pager);
		}
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	    setOuter(JSONOuter.getInstance());
	}
	
	
	/**
	 * 民主生活会列表查询
	 * @param userId  用户ID
	 * @param keyword 关键字包括标题、发起人
	 * @throws BaseException 
	 * @throws Exception 
     * @parma status  0：待办，1：已办
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void democrticLifeList() throws Exception, BaseException{
		logger.debug("democrticLifeList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","keyword","status","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String userId = request.getUserId();
		String keyword = request.getKeyword();
		String status = request.getStatus();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize == 0) {
			pageSize = 10;
		}
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(status)) {
			throw new BaseException("9002", "status参数不能为空");
		}
		if("0".equals(status)){
			getSearchValue().put("whetherEnd", 0);
			getSearchValue().put("singInStatus", 0);
			getSearchValue().put("forLeaveStatus", 0);
		}else if("1".equals(status)){
			getSearchValue().put("thirdStatus", 1);
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("keyword", "%"+keyword+"%");
        getSearchValue().put("status", "1");//已发布
		getSearchValue().put("mobileClient", "mobileClient");
		getSearchValue().put("userid", userId);
		pager = democrticLifeService.searchDemocrticLife(getSearchValue(), pager);
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	    
		// 民主生活会待办数量
		List<DemocrticLifeVO> democrticLifes = membercenterService.getDemocrticLifeByUserId(userId);
		addJsonObj("toDoCount",democrticLifes.size());
	    setOuter(JSONOuter.getInstance());
	}
	
	/**
	 * 三会一课/支部活动/志愿活动详情
	 * @param ID
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void workDetails() throws BaseException, Exception{
		logger.debug("workDetails requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id","userId","type"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getId())) {
			throw new BaseException("9002", "id参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getUserId())) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}

		String id = request.getId();
		String userId = request.getUserId();
		Integer type = Integer.parseInt(request.getType());
		
		
		PartyDetailsClientVO details = volunteerService.getPartyDetails(id,userId,type);
	    addJsonObj("details", details);
	    setOuter(JSONOuter.getInstance());
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
}
