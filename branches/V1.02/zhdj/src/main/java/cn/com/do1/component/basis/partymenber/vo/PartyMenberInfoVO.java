package cn.com.do1.component.basis.partymenber.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartyMenberInfoVO {
	private java.lang.String id;
	private java.lang.String userName;
	private java.lang.String password;
	private java.lang.String name;
	private java.lang.String organizationId;
	private java.lang.String organizationName;
	@FormatMask(type = "number")
	private java.lang.String affairsWorker;
	@DictDesc(refField = "affairsWorker", typeName = "affairsWorkerStatu")
	private java.lang.String affairsWorkerDesc;
	@FormatMask(type = "number")
	private java.lang.String volunteers;
	@DictDesc(refField = "volunteers", typeName = "volunteersStatus")
	private java.lang.String volunteersDesc;
	@FormatMask(type = "number")
	private java.lang.String behalf;
	@DictDesc(refField = "behalf", typeName = "behalfStatus")
	private java.lang.String behalfDesc;
	@FormatMask(type = "number")
	private java.lang.String hardPartyMembers;
	@DictDesc(refField = "hardPartyMembers", typeName = "hardPartyMembers")
	private java.lang.String hardPartyMembersDesc;
	private java.lang.String position;
	@DictDesc(refField = "position", typeName = "partyPosition")
	private java.lang.String positionDesc;
	private java.lang.String rewardsPunishment;
	@FormatMask(type = "number")
	private java.lang.String sex;
	@DictDesc(refField = "sex", typeName = "personSex")
	private java.lang.String sexDesc;
	private java.lang.String idCard;
	private java.lang.String nativePlace;
	private java.lang.String national;
	private java.lang.String degree;
	@DictDesc(refField = "degree", typeName = "degree")
	private java.lang.String degreeDesc;
	private java.lang.String politicalLandscape;
	@DictDesc(refField = "politicalLandscape", typeName = "politicalLandscape")
	private java.lang.String politicalLandscapeDesc;
	private java.lang.String birthday;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String partyTime;
	private java.lang.String mobile;
	private java.lang.String email;
	private java.lang.String qq;
	private java.lang.String homeAddress;
	private java.lang.String note;
	private java.lang.String portraitPic;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String createTime;
	@FormatMask(type = "number")
	private java.lang.String state;
	@DictDesc(refField = "state", typeName = "userState")
	private java.lang.String stateDesc;
	private java.lang.String otherContaceWay;
	private java.lang.String isManagement;
	@DictDesc(refField = "isManagement", typeName = "isManagement")
	private java.lang.String isManagementDesc;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String leaveTime;
	@FormatMask(type = "number")
	private java.lang.String userType;
	@DictDesc(refField = "userType", typeName = "userType")
	private java.lang.String userTypeDesc;
	private java.lang.String degreeIn;
	@DictDesc(refField = "degreeIn", typeName = "degreeIn")
	private java.lang.String degreeInDesc;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String applyForLeaveTime;
	private java.lang.String auditUserId;
	private java.lang.String auditUserName;
	
	
	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public java.lang.String getPositionDesc() {
		return positionDesc;
	}

	public void setPositionDesc(java.lang.String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public java.lang.String getOtherContaceWay() {
		return otherContaceWay;
	}

	public java.lang.String getIsManagement() {
		return isManagement;
	}

	public java.lang.String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(java.lang.String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.lang.String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(java.lang.String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public void setIsManagement(java.lang.String isManagement) {
		this.isManagement = isManagement;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(java.lang.String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	public java.lang.String getDegreeIn() {
		return degreeIn;
	}

	public void setDegreeIn(java.lang.String degreeIn) {
		this.degreeIn = degreeIn;
	}

	public java.lang.String getDegreeInDesc() {
		return degreeInDesc;
	}

	public void setDegreeInDesc(java.lang.String degreeInDesc) {
		this.degreeInDesc = degreeInDesc;
	}

	public java.lang.String getApplyForLeaveTime() {
		return applyForLeaveTime;
	}

	public void setApplyForLeaveTime(java.lang.String applyForLeaveTime) {
		this.applyForLeaveTime = applyForLeaveTime;
	}

	public java.lang.String getIsManagementDesc() {
		return isManagementDesc;
	}

	public java.lang.String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(java.lang.String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public void setIsManagementDesc(java.lang.String isManagementDesc) {
		this.isManagementDesc = isManagementDesc;
	}

	public void setOtherContaceWay(java.lang.String otherContaceWay) {
		this.otherContaceWay = otherContaceWay;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public java.lang.String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(java.lang.String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public java.lang.String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getDegreeDesc() {
		return degreeDesc;
	}

	public void setDegreeDesc(java.lang.String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}

	public java.lang.String getPoliticalLandscapeDesc() {
		return politicalLandscapeDesc;
	}

	public void setPoliticalLandscapeDesc(
			java.lang.String politicalLandscapeDesc) {
		this.politicalLandscapeDesc = politicalLandscapeDesc;
	}

	public java.lang.String getName() {
		return name;
	}

	public java.lang.String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(java.lang.String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public java.lang.String getAffairsWorkerDesc() {
		return affairsWorkerDesc;
	}

	public void setAffairsWorkerDesc(java.lang.String affairsWorkerDesc) {
		this.affairsWorkerDesc = affairsWorkerDesc;
	}

	public java.lang.String getVolunteersDesc() {
		return volunteersDesc;
	}

	public void setVolunteersDesc(java.lang.String volunteersDesc) {
		this.volunteersDesc = volunteersDesc;
	}

	public java.lang.String getBehalfDesc() {
		return behalfDesc;
	}

	public void setBehalfDesc(java.lang.String behalfDesc) {
		this.behalfDesc = behalfDesc;
	}

	public java.lang.String getHardPartyMembersDesc() {
		return hardPartyMembersDesc;
	}

	public void setHardPartyMembersDesc(java.lang.String hardPartyMembersDesc) {
		this.hardPartyMembersDesc = hardPartyMembersDesc;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	public java.lang.String getAffairsWorker() {
		return affairsWorker;
	}

	public void setAffairsWorker(java.lang.String affairsWorker) {
		this.affairsWorker = affairsWorker;
	}

	public java.lang.String getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(java.lang.String volunteers) {
		this.volunteers = volunteers;
	}

	public java.lang.String getBehalf() {
		return behalf;
	}

	public void setBehalf(java.lang.String behalf) {
		this.behalf = behalf;
	}

	public java.lang.String getHardPartyMembers() {
		return hardPartyMembers;
	}

	public void setHardPartyMembers(java.lang.String hardPartyMembers) {
		this.hardPartyMembers = hardPartyMembers;
	}

	public java.lang.String getPosition() {
		return position;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
	}

	public java.lang.String getRewardsPunishment() {
		return rewardsPunishment;
	}

	public void setRewardsPunishment(java.lang.String rewardsPunishment) {
		this.rewardsPunishment = rewardsPunishment;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getIdCard() {
		return idCard;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(java.lang.String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public java.lang.String getNational() {
		return national;
	}

	public void setNational(java.lang.String national) {
		this.national = national;
	}

	public java.lang.String getDegree() {
		return degree;
	}

	public void setDegree(java.lang.String degree) {
		this.degree = degree;
	}

	public java.lang.String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPoliticalLandscape(java.lang.String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public java.lang.String getBirthday() {
		return birthday;
	}

	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
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

	public java.lang.String getQq() {
		return qq;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	public java.lang.String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(java.lang.String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(java.lang.String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

}
