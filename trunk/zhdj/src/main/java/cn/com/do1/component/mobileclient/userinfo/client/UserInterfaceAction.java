package cn.com.do1.component.mobileclient.userinfo.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.index.index.model.LoginRequest;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.installrecord.model.IntegralRankingRequest;
import cn.com.do1.component.mobileclient.userinfo.service.IUserInfoService;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.ModifyUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.ModifyUserPwdRequest;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.report.access.model.TbLoginLogPO;
import cn.com.do1.component.score.orgscore.service.IOrgscoreService;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;
import cn.com.do1.component.util.Md5;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class UserInterfaceAction extends BaseAction{
	private final static transient Logger logger = LoggerFactory.getLogger(UserInterfaceAction.class);
	private String requestJson;
	@Resource
	private IIndexService indexService;
	@Resource
	private IScoreruleService scoreruleService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IPartymenberService partymenberService;
	@Resource
	private IPersonalscoreService personalscoreService;
	@Resource
	private IOrgscoreService orgscoreService;
	
    @Resource
    private IDeviceService deviceService;
	/**
	 * 用户登录
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "登陆成功", faileMsg = "登陆失败"))
	public void login() throws Exception, BaseException {
		logger.debug("login requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
    	LoginRequest uservo = (LoginRequest)JSONObject.toBean(paramJson,LoginRequest.class);
    	uservo = (LoginRequest)CommonAuthManage.authDigestRetObj(uservo,new String []{"platformType","username","userPwd","deviceId"});
    	if(uservo == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		if (AssertUtil.isEmpty(uservo)) {
			throw new BaseException("9002", "uservo参数不能为空");
		}
		if (AssertUtil.isEmpty(uservo.getPlatformType())) {
			throw new BaseException("9002", "platformType参数不能为空");
		}
		if (AssertUtil.isEmpty(uservo.getUsername())) {
			throw new BaseException("9002", "username参数不能为空");
		}
		if (AssertUtil.isEmpty(uservo.getUserPwd())) {
			throw new BaseException("9002", "userPwd参数不能为空");
		}
		if (AssertUtil.isEmpty(uservo.getDeviceId())) {
			throw new BaseException("9002", "deviceId参数不能为空");
		}
		
		String opResult = "";
		String userType = "3";//先查询是否为党员，如果不为党员，在查询是否为群众用户
		boolean isVidataPass = true;


		if (isVidataPass) {
			// 去空格,加密密码

			uservo.setUsername(uservo.getUsername().trim());
			Md5 m = new Md5();
			uservo.setUserPwd(m.getMD5ofStr(uservo.getUserPwd().trim())
					.toLowerCase());
			// 登录方法
			LoginUserVO user = new LoginUserVO();
			BeanHelper.copyProperties(user, uservo);
			LoginUserVO userLoginvo = indexService.loginUser(user, userType);
			if (null != userLoginvo) {
				opResult = "登录成功";
				setActionResult("0", "登录成功");
				// 记录登录日志
				TbLoginLogPO logPO = new TbLoginLogPO();
				logPO.setLoginTime(new Date());
				logPO.setStatus("登录成功!");
				logPO.setUserName(userLoginvo.getUsername());
				indexService.insertPO(logPO, true);
				
				//记录客户端设备登录日志
				TbDevicePO device = deviceService.findByDeviceId(uservo.getDeviceId());
				if(device!=null){
					TbDeviceLoginRecordPO deviceLoginRecordPO = new TbDeviceLoginRecordPO();
					BeanHelper.copyProperties(deviceLoginRecordPO, device);
					deviceLoginRecordPO.setId(UUID.randomUUID().toString().toLowerCase());
					deviceLoginRecordPO.setLoginTime(new Date());
					deviceLoginRecordPO.setUserId(userLoginvo.getUserId());;
					deviceService.insertPO(deviceLoginRecordPO, false);
				}else{
					logger.warn("设备信息中没有此设备,设备ID>>>"+uservo.getDeviceId());
				}
				
				userLoginvo.setUserPwd("");
				addJsonObj("loginUserInfo", userLoginvo);
//				loginSuccess(userLoginvo);
				if(!indexService.checkLonginScoreToday(userLoginvo.getUserId(), "1")){
					// 记录加分情况
					TbPersonalScoreInfoPO score = new TbPersonalScoreInfoPO();
					ExItemObj obj = DictOperater.getItem("personalScore", "1");//手机端登录
					TbScoreRulePO scoreRule = scoreruleService.searchByType(obj.getFsDictItemId());
					score.setId(UUID.randomUUID().toString());
					score.setUserId(userLoginvo.getUserId());
					score.setScoreType("1");
					if (null != scoreRule
							&& !StringUtil.isNullEmpty(scoreRule.getScoreValue())) {
						score.setScore(Long.parseLong(scoreRule.getScoreValue()));
					} else {
						score.setScore(0L);
					}
					score.setGetTime(new Date());
					score.setScoreDesc(obj.getFsItemDesc());
					indexService.insertPO(score, true);
				}
			} else {
				setActionResult("1001", "用户名或密码错误!");
				opResult = "用户名或密码错误";
				// 记录登录日志
				TbLoginLogPO logPO = new TbLoginLogPO();
				logPO.setLoginTime(new Date());
				logPO.setStatus("登录失败，用户名或密码错误!");
				logPO.setUserName(uservo.getUsername());
				indexService.insertPO(logPO, true);
			}

		}
		addJsonObj("opResult", opResult);
		setOuter(JSONOuter.getInstance());
	}
	/**
	 * 登录成功后处理
	 * 
	 * @param vo
	 * @throws BaseException
	 */
	private void loginSuccess(LoginUserVO vo) throws BaseException {
		if (!AssertUtil.isEmpty(vo)) {
			getSessionObj().setMaxInactiveInterval(1800);
			HttpServletRequest request = getReqeustObj();
			try {
				HashMap<String, HttpSession> loginSession = (HashMap<String, HttpSession>) request
						.getSession().getServletContext().getAttribute(
								"mobileMemberLogin");

				if (loginSession == null) {
					loginSession = new HashMap<String, HttpSession>();
					request.getSession().getServletContext().setAttribute(
							"mobileMemberLogin", loginSession);
				}
				/** 判断是否已经登录 */
				if (loginSession.containsKey(vo.getUsername())) {
					HttpSession oldsession = loginSession.get(vo.getUsername());
					try {
						oldsession.invalidate();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					getSessionObj().setAttribute("mobileMemberLogin", true);
					loginSession.put(vo.getUsername(), getSessionObj());
				} else {
					getSessionObj().setAttribute("mobileMemberLogin", true);
					loginSession.put(vo.getUsername(), getSessionObj());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	/**
	 * 个人用户信息查询
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "用户信息获取成功", faileMsg = "用户信息获取失败"))
	public void myUserInfo() throws BaseException, Exception{
		logger.debug("myUserInfo requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
    	LoginRequest uservo = (LoginRequest)JSONObject.toBean(paramJson,LoginRequest.class);
    	uservo = (LoginRequest)CommonAuthManage.authDigestRetObj(uservo, new String []{"userId","userType"});
    	if(uservo == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		
		String userId = uservo.getUserId();
		String userType = uservo.getUserType();
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(userType)) {
			//查询党员用户信息
			PartyMenberUserInfoVO partyMenberInfo = userInfoService.getPartyMenberUserInfoByUserId(userId);
			if(!AssertUtil.isEmpty(partyMenberInfo)){
				addJsonObj("partyMenberInfo", partyMenberInfo);
				userType = "1";
			}else{
				//查询群众 用户信息
				CrowdUserInfoVO crowdUserInfoVO= userInfoService.getCrowdUserInfoByUserId(userId);
				addJsonObj("crowdUserInfo", crowdUserInfoVO);
				userType = "2";
			}
		}else{
			Integer uType = Integer.parseInt(userType);
			switch(uType){
			case 1:
				PartyMenberUserInfoVO partyMenberInfo = userInfoService.getPartyMenberUserInfoByUserId(userId);
				addJsonObj("partyMenberInfo", partyMenberInfo);
				break;
			case 2:
				CrowdUserInfoVO crowdUserInfoVO= userInfoService.getCrowdUserInfoByUserId(userId);
				addJsonObj("crowdUserInfo", crowdUserInfoVO);
				break;
			default:
				throw new BaseException("9001", "非法的请求参数userType=="+userType);
			}
		}
		addJsonObj("userType", userType);
		setOuter(JSONOuter.getInstance());
	}
	
	/**
	 * 个人用户信息修改
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "用户信息修改成功", faileMsg = "用户信息修改失败"))
	public void modifyUserInfo() throws BaseException, Exception{
		logger.debug("modifyUserInfo requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
    	ModifyUserInfoVO modifyUserInfoVO = (ModifyUserInfoVO)JSONObject.toBean(paramJson,ModifyUserInfoVO.class);
    	modifyUserInfoVO = (ModifyUserInfoVO)CommonAuthManage.authDigestRetObj(modifyUserInfoVO, new String []{"userType","userId","userName","mobile","email"});
    	if(modifyUserInfoVO == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		if (AssertUtil.isEmpty(modifyUserInfoVO)) {
			throw new BaseException("9001", "modifyUserInfo参数不能为空");
		}
		if (AssertUtil.isEmpty(modifyUserInfoVO.getUserId())) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(modifyUserInfoVO.getUserName())) {
			throw new BaseException("9002", "userName参数不能为空");
		}
		if (AssertUtil.isEmpty(modifyUserInfoVO.getUserType())) {
			throw new BaseException("9002", "userType参数不能为空");
		}
		Integer userType = Integer.parseInt(modifyUserInfoVO.getUserType());
		String userId = modifyUserInfoVO.getUserId();
		String userName = modifyUserInfoVO.getUserName();
		String portraitPic = modifyUserInfoVO.getPortraitPic();
		String mobile = modifyUserInfoVO.getMobile();
		String email = modifyUserInfoVO.getEmail();
		
		switch(userType){
		case 1://党员
			TbPartyMenberInfoPO partyMenber = this.indexService.searchByPk(TbPartyMenberInfoPO.class, userId);
			if(partyMenber != null){
				partyMenber = new TbPartyMenberInfoPO();
				partyMenber.setId(userId);
				partyMenber.setUserName(userName);
				partyMenber.setUserType(Long.parseLong(modifyUserInfoVO.getUserType()));
				partyMenber.setPortraitPic(portraitPic);
				partyMenber.setMobile(mobile);
				partyMenber.setEmail(email);
				this.userInfoService.updatePartMenber(partyMenber);
			}else{
				logger.warn("用户不存在,ID>>>"+userId);
				throw new BaseException("9005", "用户不存在");
			}
			break;
		case 2://群众
			TbPartyDevelopmentMenberPO userInfo = this.indexService.searchByPk(TbPartyDevelopmentMenberPO.class, userId);
			if(userInfo != null){
				userInfo = new TbPartyDevelopmentMenberPO();
				userInfo.setId(userId);
				userInfo.setId(userId);
				userInfo.setUserName(userName);
				userInfo.setPortraitPic(portraitPic);
				userInfo.setMobile(mobile);
//				userInfo.setEmail(email);
				this.userInfoService.updateCrowdUserInfo(userInfo);
			}else{
				logger.warn("用户不存在,ID>>>"+userId);
				throw new BaseException("9005", "用户不存在");
			}
			break;
		default:
			throw new BaseException("9001", "非法的请求参数userType=="+userType);
		}
		setOuter(JSONOuter.getInstance());
	}
	
	/**
	 * 密码修改
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "密码修改成功", faileMsg = "密码修改失败"))
	public void modifyUserPwd() throws BaseException, Exception{
		logger.debug("modifyUserPwd requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		ModifyUserPwdRequest request = (ModifyUserPwdRequest)JSONObject.toBean(paramJson,ModifyUserPwdRequest.class);
		request = (ModifyUserPwdRequest)CommonAuthManage.authDigestRetObj(request, new String []{"userId","oldPwd","newPwd"});
    	if(request == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
    	
		String userId = request.getUserId();
		String oldPwd = request.getOldPwd();
		String newPwd = request.getNewPwd();
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9001", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(oldPwd)) {
			throw new BaseException("9002", "oldPwd参数不能为空");
		}
		if (AssertUtil.isEmpty(newPwd)) {
			throw new BaseException("9002", "newPwd参数不能为空");
		}
		TbPartyMenberInfoPO partyMenber = this.indexService.searchByPk(TbPartyMenberInfoPO.class, userId);
		Md5 m = new Md5();
		if(!AssertUtil.isEmpty(partyMenber)){
			if(!partyMenber.getPassword().trim().toLowerCase().equals(m.getMD5ofStr(oldPwd).trim().toLowerCase())){
				logger.warn("旧密码输入错误"+oldPwd.trim().toLowerCase());
				throw new BaseException("9009", "旧密码输入错误!");
			}
			partyMenber.setPassword(newPwd);
			//同步党员、用户密码
			partymenberService.updateUserInfo(partyMenber);
		}else{
			TbPartyDevelopmentMenberPO userInfo = this.indexService.searchByPk(TbPartyDevelopmentMenberPO.class, userId);
			if(!userInfo.getPassWord().equals(m.getMD5ofStr(oldPwd).trim().toLowerCase())){
				logger.warn("旧密码输入错误"+oldPwd.trim().toLowerCase());
				throw new BaseException("9009", "旧密码输入错误!");
			}
			userInfo.setPassWord(newPwd);
			this.indexService.updatePO(userInfo, false);
		}
		setOuter(JSONOuter.getInstance());
	}
	/**
	 * 查询个人或支部积分排名列表
	 * 
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void integralRanking() throws BaseException, Exception {
		logger.debug("integralRanking requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		IntegralRankingRequest request = (IntegralRankingRequest) JSONObject.toBean(paramJson, IntegralRankingRequest.class);
		request = (IntegralRankingRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type", "orderType", "userId", "organizationId","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String type = request.getType();
		String orderType = request.getOrderType();
		String userId = request.getUserId();
		String organizationId = request.getOrganizationId();
		Integer pageIndex = AssertUtil.isEmpty(request.getPageIndex()) ? 1 : Integer.parseInt(request.getPageIndex());
		Integer pageSize = AssertUtil.isEmpty(request.getPageSize()) ? 10 : Integer.parseInt(request.getPageSize());
		if (AssertUtil.isEmpty(type)) {
			throw new BaseException("9002", "type参数不能为空");
		}
		if (AssertUtil.isEmpty(orderType)) {
			throw new BaseException("9002", "orderType参数不能为空");
		}

		if (type.equals("1")) {
			if (AssertUtil.isEmpty(userId)) {
				throw new BaseException("9002", "userId参数不能为空");
			}
		} else if (type.equals("2")) {
			if (AssertUtil.isEmpty(organizationId)) {
				throw new BaseException("9002", "organizationId参数不能为空");
			}
		}
		if (pageSize.equals(0)) {
			pageSize = 10;
		}
		Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
		pager.setCurrentPage(pageIndex);

		
		if ("1".equals(type)) {
			Map searchMap = getSearchValue();
			searchMap.put("type", orderType);
			if ("3".equals(orderType)) {
				pager.setPageSize(50);
				searchMap.put("userid", userId);
				searchMap.put("mobileClient", "mobileClient");
			}
			pager = personalscoreService.searchPersonalscore(searchMap, pager);
		}
		if ("2".equals(type)) {
			getSearchValue().put("type", orderType);
			getSearchValue().put("mobileClient", "mobileClient");
			if ("3".equals(orderType)) {
				getSearchValue().put("userid", userId);
				pager.setPageSize(50);
			}
			getSearchValue().put("oraganization", organizationId);
			pager = personalscoreService.searchPersonalscore(getSearchValue(), pager);
		}
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
		setOuter(JSONOuter.getInstance());
	}

	/**
	 * 查询个人或支部积分明细列表
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void rankingDetailsList() throws BaseException, Exception {
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		logger.debug("rankingDetailsList requestJson>>>" + requestJson);
		IntegralRankingRequest request = (IntegralRankingRequest) JSONObject.toBean(paramJson, IntegralRankingRequest.class);
		request = (IntegralRankingRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type", "userId", "organizationId","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String type = request.getType();
		String userId = request.getUserId();
		String organizationId = request.getOrganizationId();
		Integer pageIndex = AssertUtil.isEmpty(request.getPageIndex()) ? 1 : Integer.parseInt(request.getPageIndex());
		Integer pageSize = AssertUtil.isEmpty(request.getPageSize()) ? 10 : Integer.parseInt(request.getPageSize());
		if (AssertUtil.isEmpty(type)) {
			throw new BaseException("9002", "type参数不能为空");
		}

		if (type.equals("1")) {
			if (AssertUtil.isEmpty(userId)) {
				throw new BaseException("9002", "userId参数不能为空");
			}
		} else if (type.equals("2")) {
			if (AssertUtil.isEmpty(organizationId)) {
				throw new BaseException("9002", "organizationId参数不能为空");
			}
		}
		if (pageSize.equals(0)) {
			pageSize = 10;
		}
		Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
		pager.setCurrentPage(pageIndex);

		if ("1".equals(type)) {

			Map searchMap = getSearchValue();
			searchMap.put("id", userId);

			pager = personalscoreService.searchPersonalscoreInfo(searchMap, pager);
		}
		if ("2".equals(type)) {
			Map searchMap = getSearchValue();
			searchMap.put("id", organizationId);
			pager = orgscoreService.searchOrgscoreInfo(getSearchValue(), pager);
		}
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
		setOuter(JSONOuter.getInstance());
	}

	public String getRequestJson() {
		return requestJson;
	}


	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	
}
