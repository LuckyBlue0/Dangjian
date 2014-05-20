package cn.com.do1.component.basis.partydevelopment.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartyDevelopmentMenberVO {
	private java.lang.String id;
	private java.lang.String name;
	private java.lang.String sex;
	@DictDesc(refField = "sex", typeName = "personSex")
	private java.lang.String sexDesc;
	private java.lang.String organizationIdentity;
	@DictDesc(refField = "organizationIdentity", typeName = "orgIdentity")
	private java.lang.String organizationIdentityDesc;
	private java.lang.String organizationId;
	private java.lang.String organizationName;
	private java.lang.String department;
	private java.lang.String birthday;
	private java.lang.String national;
	private java.lang.String nativePlace;
	private java.lang.String idCard;
	private java.lang.String degree;
	@DictDesc(refField = "degree", typeName = "degree")
	private java.lang.String degreeDesc;
	private java.lang.String degreeIn;
	@DictDesc(refField = "degreeIn", typeName = "degreeIn")
	private java.lang.String degreeInDesc;
	private java.lang.String unit;
	private java.lang.String workTime;
	private java.lang.String health;
	private java.lang.String school;
	private java.lang.String oldName;
	private java.lang.String mobile;
	private java.lang.String administrativeDuties;
	private java.lang.String cultivatingContacts;
	private java.lang.String note;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String createTime;
	private java.lang.String state;
	private java.lang.String portraitPic;
	private java.lang.String homeAddress;
	private java.lang.String userName;
	private java.lang.String password;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getSexDesc() {
		return sexDesc;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public void setSexDesc(java.lang.String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public java.lang.String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(java.lang.String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public java.lang.String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(java.lang.String homeAddress) {
		this.homeAddress = homeAddress;
	}


	public java.lang.String getOrganizationIdentityDesc() {
		return organizationIdentityDesc;
	}

	public void setOrganizationIdentityDesc(
			java.lang.String organizationIdentityDesc) {
		this.organizationIdentityDesc = organizationIdentityDesc;
	}

	public java.lang.String getDegreeDesc() {
		return degreeDesc;
	}

	public void setDegreeDesc(java.lang.String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}

	public java.lang.String getDegreeInDesc() {
		return degreeInDesc;
	}

	public void setDegreeInDesc(java.lang.String degreeInDesc) {
		this.degreeInDesc = degreeInDesc;
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

	public java.lang.String getNote() {
		return note;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public java.lang.String getOrganizationIdentity() {
		return organizationIdentity;
	}

	public void setOrganizationIdentity(java.lang.String organizationIdentity) {
		this.organizationIdentity = organizationIdentity;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	public java.lang.String getDepartment() {
		return this.department;
	}

	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
	}

	public java.lang.String getBirthday() {
		return this.birthday;
	}

	public void setNational(java.lang.String national) {
		this.national = national;
	}

	public java.lang.String getNational() {
		return this.national;
	}

	public void setNativePlace(java.lang.String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public java.lang.String getNativePlace() {
		return this.nativePlace;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getIdCard() {
		return this.idCard;
	}

	public void setDegree(java.lang.String degree) {
		this.degree = degree;
	}

	public java.lang.String getDegree() {
		return this.degree;
	}

	public void setDegreeIn(java.lang.String degreeIn) {
		this.degreeIn = degreeIn;
	}

	public java.lang.String getDegreeIn() {
		return this.degreeIn;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.String getUnit() {
		return this.unit;
	}

	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	public java.lang.String getWorkTime() {
		return this.workTime;
	}

	public void setHealth(java.lang.String health) {
		this.health = health;
	}

	public java.lang.String getHealth() {
		return this.health;
	}

	public void setSchool(java.lang.String school) {
		this.school = school;
	}

	public java.lang.String getSchool() {
		return this.school;
	}

	public void setOldName(java.lang.String oldName) {
		this.oldName = oldName;
	}

	public java.lang.String getOldName() {
		return this.oldName;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setAdministrativeDuties(java.lang.String administrativeDuties) {
		this.administrativeDuties = administrativeDuties;
	}

	public java.lang.String getAdministrativeDuties() {
		return this.administrativeDuties;
	}

	public void setCultivatingContacts(java.lang.String cultivatingContacts) {
		this.cultivatingContacts = cultivatingContacts;
	}

	public java.lang.String getCultivatingContacts() {
		return this.cultivatingContacts;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

}
