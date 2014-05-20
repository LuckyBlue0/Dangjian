package com.do1.zhdj.info;

import java.util.List;
import java.util.Map;

import com.do1.zhdj.util.Constants;


public class UserInfo {
	private static UserInfo userInfo = null;
	
	private String userId = "";//用户id唯一
	////////////////////////////////
	private String userName = "";//用户名
	private String name;//真实姓名 、中文名
	private String password = "";
	private String branchId = "";//所在支部ID
	private String is_can_test = "";//是否可参加今天的考试
//	private String join_url = "";//入党生日
//	private String party_url = "";//党生日图片地址
//	private String person_url = "";//个人生日图片
	private List<Map<String, Object>> birthdayMap;//生日集合
	private String user_type = "";//用户类型1：党员账号、2：群众账号
	/////////////////////////////////////
	private String mobile = "";//手机
	private String IDcard = "";//身份证
	
	// add bu yfq
	private String headImg;//头像URL
	private String totalIntegral;//个人总积分
	private String integralRank;//个人积分排名
	private String branchRank;//所在支部排名
	private String todos;//待办数量
	private String mettingSigns;//会议签到数量
	private String analysisReports;//智能分析报表数量
	////////////////////////////////////////////
	private String job; //职务
	///////////////////////////
	private String joinTime; //入党时间
	//////////////////////////
	private String partAge; //党龄
	
	private String checkDept; //登记组织
	private String regDept; //注册组织
	private String branchPeoples; //组织人数
	
	private String branchSec; //支部书记
	private String branchInt; //组织积分
	private String partyStuUrl; //党员学习页面URL
	private String isCanTest; //党员今日是否还可参加考试 1:可以，0：不可以
	private String email; //电子邮箱
	private String qq; //QQ
	private String contactAddr; //联系地址
	private String isPartyWorkers; //是否党务工作者1：是，0：不是
	private String branchImg;//支部照片
	////////////////////////////
	private String branchName;//支部名称
	private String rankNum;	//组织排名
	
	private boolean isLogin = false;
	
	public static  UserInfo getInstance() {
	    if (userInfo == null) 	userInfo = new UserInfo();
	    return userInfo;
	}

	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchImg() {
		return branchImg;
	}
	public void setBranchImg(String branchImg) {
		this.branchImg = branchImg;
	}
	public String getTodos() {
		return todos;
	}
	public void setTodos(String todos) {
		this.todos = todos;
	}
	public String getMettingSigns() {
		return mettingSigns;
	}
	public void setMettingSigns(String mettingSigns) {
		this.mettingSigns = mettingSigns;
	}
	public String getAnalysisReports() {
		return analysisReports;
	}
	public void setAnalysisReports(String analysisReports) {
		this.analysisReports = analysisReports;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return Constants.sharedProxy.getString("keyId", userId);
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getIs_can_test() {
		return is_can_test;
	}
	public void setIs_can_test(String is_can_test) {
		this.is_can_test = is_can_test;
	}
	public String getRankNum() {
		return rankNum;
	}
	public void setRankNum(String rankNum) {
		this.rankNum = rankNum;
	}
	public List<Map<String, Object>> getBirthdayMap() {
		return birthdayMap;
	}
	public void setBirthdayMap(List<Map<String, Object>> birthdayMap) {
		this.birthdayMap = birthdayMap;
	}

	public String getUser_type() {
		return Constants.sharedProxy.getString("keyType", user_type);
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIDcard() {
		return IDcard;
	}
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTotalIntegral() {
		return totalIntegral;
	}
	public void setTotalIntegral(String totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	public String getIntegralRank() {
		return integralRank;
	}
	public void setIntegralRank(String integralRank) {
		this.integralRank = integralRank;
	}
	public String getBranchRank() {
		return branchRank;
	}
	public void setBranchRank(String branchRank) {
		this.branchRank = branchRank;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public String getPartAge() {
		return partAge;
	}
	public void setPartAge(String partAge) {
		this.partAge = partAge;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	public String getRegDept() {
		return regDept;
	}
	public void setRegDept(String regDept) {
		this.regDept = regDept;
	}
	public String getBranchPeoples() {
		return branchPeoples;
	}
	public void setBranchPeoples(String branchPeoples) {
		this.branchPeoples = branchPeoples;
	}
	public String getBranchSec() {
		return branchSec;
	}
	public void setBranchSec(String branchSec) {
		this.branchSec = branchSec;
	}
	public String getBranchInt() {
		return branchInt;
	}
	public void setBranchInt(String branchInt) {
		this.branchInt = branchInt;
	}
	public String getPartyStuUrl() {
		return partyStuUrl;
	}
	public void setPartyStuUrl(String partyStuUrl) {
		this.partyStuUrl = partyStuUrl;
	}
	public String getIsCanTest() {
		return isCanTest;
	}
	public void setIsCanTest(String isCanTest) {
		this.isCanTest = isCanTest;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getContactAddr() {
		return contactAddr;
	}
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}
	public String getIsPartyWorkers() {
		return isPartyWorkers;
	}
	public void setIsPartyWorkers(String isPartyWorkers) {
		this.isPartyWorkers = isPartyWorkers;
	}
	
}
