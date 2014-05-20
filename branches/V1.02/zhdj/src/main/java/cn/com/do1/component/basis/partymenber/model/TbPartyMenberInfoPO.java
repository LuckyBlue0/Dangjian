package cn.com.do1.component.basis.partymenber.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbPartyMenberInfoPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userName", showType = "input", showOrder = 1, showLength = 50)
	private java.lang.String userName;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "password", showType = "input", showOrder = 2, showLength = 100)
	private java.lang.String password;
	@Validation(must = false, length = 25, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "name", showType = "input", showOrder = 3, showLength = 25)
	private java.lang.String name;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 4, showLength = 100)
	private java.lang.String organizationId;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "affairsWorker", showType = "input", showOrder = 5, showLength = 22)
	private java.lang.Long affairsWorker;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "volunteers", showType = "input", showOrder = 6, showLength = 22)
	private java.lang.Long volunteers;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "behalf", showType = "input", showOrder = 7, showLength = 22)
	private java.lang.Long behalf;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "hardPartyMembers", showType = "input", showOrder = 8, showLength = 22)
	private java.lang.Long hardPartyMembers;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "position", showType = "input", showOrder = 9, showLength = 50)
	private java.lang.String position;
	@Validation(must = false, length = 500, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "rewardsPunishment", showType = "input", showOrder = 10, showLength = 500)
	private java.lang.String rewardsPunishment;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "sex", showType = "input", showOrder = 11, showLength = 22)
	private java.lang.Long sex;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "idCard", showType = "input", showOrder = 12, showLength = 20)
	private java.lang.String idCard;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "nativePlace", showType = "input", showOrder = 13, showLength = 100)
	private java.lang.String nativePlace;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "national", showType = "input", showOrder = 14, showLength = 20)
	private java.lang.String national;
	@Validation(must = false, length = 10, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "degree", showType = "input", showOrder = 15, showLength = 10)
	private java.lang.String degree;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "politicalLandscape", showType = "input", showOrder = 16, showLength = 20)
	private java.lang.String politicalLandscape;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "birthday", showType = "input", showOrder = 17, showLength = 20)
	private java.lang.String birthday;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "partyTime", showType = "datetime", showOrder = 18, showLength = 7)
	private java.util.Date partyTime;
	@Validation(must = false, length = 15, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "mobile", showType = "input", showOrder = 19, showLength = 15)
	private java.lang.String mobile;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "email", showType = "input", showOrder = 20, showLength = 50)
	private java.lang.String email;
	@Validation(must = false, length = 15, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "qq", showType = "input", showOrder = 21, showLength = 15)
	private java.lang.String qq;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "homeAddress", showType = "input", showOrder = 22, showLength = 100)
	private java.lang.String homeAddress;
	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "note", showType = "input", showOrder = 23, showLength = 2000)
	private java.lang.String note;
	@Validation(must = false, length = 300, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "portraitPic", showType = "input", showOrder = 24, showLength = 300)
	private java.lang.String portraitPic;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 25, showLength = 7)
	private java.util.Date createTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "state", showType = "input", showOrder = 26, showLength = 22)
	private java.lang.Long state;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userType", showType = "input", showOrder = 27, showLength = 22)
	private java.lang.Long userType;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "degreeIn", showType = "input", showOrder = 28, showLength = 36)
	private java.lang.String degreeIn;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "applyForLeaveTime", showType = "datetime", showOrder = 29, showLength = 7)
	private java.util.Date applyForLeaveTime;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "leaveTime", showType = "datetime", showOrder = 30, showLength = 7)
	private java.util.Date leaveTime;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "otherContaceWay", showType = "input", showOrder = 31, showLength = 200)
	private java.lang.String otherContaceWay;
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "isManagement", showType = "input", showOrder = 32, showLength = 2)
	private java.lang.String isManagement;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "auditUserId", showType = "input", showOrder = 28, showLength = 36)
	private java.lang.String auditUserId;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "auditUserName", showType = "input", showOrder = 28, showLength = 36)
	private java.lang.String auditUserName;
	
	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
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

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getOrganizationId() {
		return this.organizationId;
	}

	public void setAffairsWorker(java.lang.Long affairsWorker) {
		this.affairsWorker = affairsWorker;
	}

	public java.lang.Long getAffairsWorker() {
		return this.affairsWorker;
	}

	public void setVolunteers(java.lang.Long volunteers) {
		this.volunteers = volunteers;
	}

	public java.lang.Long getVolunteers() {
		return this.volunteers;
	}

	public void setBehalf(java.lang.Long behalf) {
		this.behalf = behalf;
	}

	public java.lang.Long getBehalf() {
		return this.behalf;
	}

	public void setHardPartyMembers(java.lang.Long hardPartyMembers) {
		this.hardPartyMembers = hardPartyMembers;
	}

	public java.lang.Long getHardPartyMembers() {
		return this.hardPartyMembers;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
	}

	public java.lang.String getPosition() {
		return this.position;
	}

	public void setRewardsPunishment(java.lang.String rewardsPunishment) {
		this.rewardsPunishment = rewardsPunishment;
	}

	public java.lang.String getRewardsPunishment() {
		return this.rewardsPunishment;
	}

	public void setSex(java.lang.Long sex) {
		this.sex = sex;
	}

	public java.lang.Long getSex() {
		return this.sex;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getIdCard() {
		return this.idCard;
	}

	public void setNativePlace(java.lang.String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public java.lang.String getNativePlace() {
		return this.nativePlace;
	}

	public void setNational(java.lang.String national) {
		this.national = national;
	}

	public java.lang.String getNational() {
		return this.national;
	}

	public void setDegree(java.lang.String degree) {
		this.degree = degree;
	}

	public java.lang.String getDegree() {
		return this.degree;
	}

	public void setPoliticalLandscape(java.lang.String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public java.lang.String getPoliticalLandscape() {
		return this.politicalLandscape;
	}

	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
	}

	public java.lang.String getBirthday() {
		return this.birthday;
	}

	public void setPartyTime(java.util.Date partyTime) {
		this.partyTime = partyTime;
	}

	public void setPartyTime(java.lang.String partyTime) {
		this.partyTime = ConvertUtil.cvStUtildate(partyTime);
	}

	public java.util.Date getPartyTime() {
		return this.partyTime;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	public java.lang.String getQq() {
		return this.qq;
	}

	public void setHomeAddress(java.lang.String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public java.lang.String getHomeAddress() {
		return this.homeAddress;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.String getNote() {
		return this.note;
	}

	public void setPortraitPic(java.lang.String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public java.lang.String getPortraitPic() {
		return this.portraitPic;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = ConvertUtil.cvStUtildate(createTime);
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setState(java.lang.Long state) {
		this.state = state;
	}

	public java.lang.Long getState() {
		return this.state;
	}

	public void setUserType(java.lang.Long userType) {
		this.userType = userType;
	}

	public java.lang.Long getUserType() {
		return this.userType;
	}

	public void setDegreeIn(java.lang.String degreeIn) {
		this.degreeIn = degreeIn;
	}

	public java.lang.String getDegreeIn() {
		return this.degreeIn;
	}

	public void setApplyForLeaveTime(java.util.Date applyForLeaveTime) {
		this.applyForLeaveTime = applyForLeaveTime;
	}

	public void setApplyForLeaveTime(java.lang.String applyForLeaveTime) {
		this.applyForLeaveTime = ConvertUtil.cvStUtildate(applyForLeaveTime);
	}

	public java.util.Date getApplyForLeaveTime() {
		return this.applyForLeaveTime;
	}

	public void setLeaveTime(java.util.Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public void setLeaveTime(java.lang.String leaveTime) {
		this.leaveTime = ConvertUtil.cvStUtildate(leaveTime);
	}

	public java.util.Date getLeaveTime() {
		return this.leaveTime;
	}


	public java.lang.String getOtherContaceWay() {
		return otherContaceWay;
	}

	public void setOtherContaceWay(java.lang.String otherContaceWay) {
		this.otherContaceWay = otherContaceWay;
	}

	public void setIsManagement(java.lang.String isManagement) {
		this.isManagement = isManagement;
	}

	public java.lang.String getIsManagement() {
		return this.isManagement;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_PARTY_MENBER_INFO";
	}

	/**
	 * 获取对应表的主键字段名称
	 * 
	 * @return
	 */
	public String _getPKColumnName() {
		return "id";
	}

	/**
	 * 获取主键值
	 * 
	 * @return
	 */
	public String _getPKValue() {
		return String.valueOf(id);
	}

	/**
	 * 设置主键的值
	 * 
	 * @return
	 */
	public void _setPKValue(Object value) {
		this.id = (java.lang.String) value;
	}

}
