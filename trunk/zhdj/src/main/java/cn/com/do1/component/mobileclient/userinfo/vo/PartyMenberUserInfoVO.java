package cn.com.do1.component.mobileclient.userinfo.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PartyMenberUserInfoVO {
//	private java.lang.String id;
	private java.lang.String userName;
	private java.lang.String name;
	private java.lang.String organizationId;
	
	private java.lang.String organizationName;
	private java.lang.String affairsWorker;
//	@DictDesc(refField = "affairsWorker", typeName = "affairsWorkerStatu")
//	private java.lang.String affairsWorkerDesc;
//	private java.lang.String volunteers;
//	@DictDesc(refField = "volunteers", typeName = "volunteersStatus")
//	private java.lang.String volunteersDesc;
//	private java.lang.String behalf;
//	@DictDesc(refField = "behalf", typeName = "behalfStatus")
//	private java.lang.String behalfDesc;
//	private java.lang.String hardPartyMembers;
//	@DictDesc(refField = "hardPartyMembers", typeName = "hardPartyMembers")
//	private java.lang.String hardPartyMembersDesc;
	private java.lang.String position;
	@DictDesc(refField = "position", typeName = "partyPosition")
	private java.lang.String positionDesc;
//	private java.lang.String rewardsPunishment;
//	private java.lang.String sex;
//	@DictDesc(refField = "sex", typeName = "personSex")
//	private java.lang.String sexDesc;
//	private java.lang.String idCard;
//	private java.lang.String nativePlace;
//	private java.lang.String national;
//	private java.lang.String degree;
//	@DictDesc(refField = "degree", typeName = "degree")
//	private java.lang.String degreeDesc;
//	private java.lang.String politicalLandscape;
//	@DictDesc(refField = "politicalLandscape", typeName = "politicalLandscape")
//	private java.lang.String politicalLandscapeDesc;
//	private java.lang.String birthday;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String partyTime;
	private java.lang.String mobile;
	private java.lang.String email;
//	private java.lang.String qq;
//	private java.lang.String homeAddress;
//	private java.lang.String note;
	private java.lang.String portraitPic;
//	@FormatMask(type = "date", value = "yyyy-MM-dd")
//	private java.lang.String createTime;
//	private java.lang.String state;
//	@DictDesc(refField = "state", typeName = "partyRelationStatu")
//	private java.lang.String stateDesc;
//	private java.lang.String otherContaceWay;
//	private java.lang.String isManagement;
//	@DictDesc(refField = "isManagement", typeName = "isManagement")
//	private java.lang.String isManagementDesc;
//	@FormatMask(type = "date", value = "yyyy-MM-dd")
//	private java.lang.String leaveTime;
	
	private Integer isBranchLeader;			//是否支部书记
	private String integralTotal;			//个人总积分
	private String integralRank;			//个人总排名
	private String branchRanking;			//支部排名
	private Integer meetingCount;			//三会一课待办数量
	private Integer activityCount;			//支部活动待办数量
	private Integer partyAge;				//党龄
//	private Integer ideologyReportCount;	//思想汇报待办数量
	private Integer democrticlifeCount;		//民主生活会待办数量
	private Integer volnteerCount;			//志愿活动待办数量

	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	public java.lang.String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public java.lang.String getAffairsWorker() {
		return affairsWorker;
	}

	public void setAffairsWorker(java.lang.String affairsWorker) {
		this.affairsWorker = affairsWorker;
	}

	public java.lang.String getPosition() {
		return position;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
	}

	public java.lang.String getPositionDesc() {
		return positionDesc;
	}

	public void setPositionDesc(java.lang.String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public java.lang.String getPartyTime() {
		return partyTime;
	}

	public void setPartyTime(java.lang.String partyTime) {
		this.partyTime = partyTime;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(java.lang.String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getIsBranchLeader() {
		return isBranchLeader;
	}

	public void setIsBranchLeader(Integer isBranchLeader) {
		this.isBranchLeader = isBranchLeader;
	}

	public String getIntegralTotal() {
		return integralTotal;
	}

	public void setIntegralTotal(String integralTotal) {
		this.integralTotal = integralTotal;
	}

	public String getIntegralRank() {
		return integralRank;
	}

	public void setIntegralRank(String integralRank) {
		this.integralRank = integralRank;
	}

	public Integer getMeetingCount() {
		return meetingCount;
	}

	public void setMeetingCount(Integer meetingCount) {
		this.meetingCount = meetingCount;
	}

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

	public Integer getPartyAge() {
		return partyAge;
	}

	public void setPartyAge(Integer partyAge) {
		this.partyAge = partyAge;
	}

	public Integer getDemocrticlifeCount() {
		return democrticlifeCount;
	}

	public void setDemocrticlifeCount(Integer democrticlifeCount) {
		this.democrticlifeCount = democrticlifeCount;
	}

	public Integer getVolnteerCount() {
		return volnteerCount;
	}

	public void setVolnteerCount(Integer volnteerCount) {
		this.volnteerCount = volnteerCount;
	}

	public String getBranchRanking() {
		return branchRanking;
	}

	public void setBranchRanking(String branchRanking) {
		this.branchRanking = branchRanking;
	}
}
