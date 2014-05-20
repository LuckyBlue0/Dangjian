package cn.com.do1.component.relation.organization.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrganizationTransferVO {
	private java.lang.String id;
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String organizationFrom;
	private java.lang.String organizationNameFrom;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String createTime;
	private java.lang.String leaveDesc;
	private java.lang.String organizationTo;
	private java.lang.String organizationNameTo;
	private java.lang.String status;
	@DictDesc(refField = "status", typeName = "relationAuditType")
	private java.lang.String statusDesc;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String intoTime;
	private java.lang.String organizationInto;
	private java.lang.String organizationNameInto;
	private java.lang.String intoDesc;
	private java.lang.String auditUserInfo;
	private java.lang.String auditUserNameInfo;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String auditTime;
	private java.lang.String auditDesc;
	private java.lang.String type;
	@DictDesc(refField = "type", typeName = "orgTransferType")
	private java.lang.String typeDesc;
	private java.lang.String mobile;
	private java.lang.String idCard;
	private java.lang.String politicalLandscape;
	@DictDesc(refField = "politicalLandscape", typeName = "politicalLandscape")
	private java.lang.String politicalLandscapeDesc;
	private java.lang.String national;
	private java.lang.Long age;
	private java.lang.String sex;
	@DictDesc(refField = "sex", typeName = "personSex")
	private java.lang.String sexDesc;
	private java.lang.String organizationId;
	private java.lang.String organizationName;
	private java.lang.String organizationTel;
	private java.lang.String organizationZipCode;
	private java.lang.String organizationAddress;
    private java.lang.String address ;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public java.lang.String getIdCard() {
		return idCard;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPoliticalLandscape(java.lang.String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public java.lang.String getPoliticalLandscapeDesc() {
		return politicalLandscapeDesc;
	}

	public void setPoliticalLandscapeDesc(
			java.lang.String politicalLandscapeDesc) {
		this.politicalLandscapeDesc = politicalLandscapeDesc;
	}

	public java.lang.String getNational() {
		return national;
	}

	public void setNational(java.lang.String national) {
		this.national = national;
	}

	public java.lang.Long getAge() {
		return age;
	}

	public void setAge(java.lang.Long age) {
		this.age = age;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(java.lang.String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public java.lang.String getOrganizationTel() {
		return organizationTel;
	}

	public void setOrganizationTel(java.lang.String organizationTel) {
		this.organizationTel = organizationTel;
	}

	public java.lang.String getOrganizationZipCode() {
		return organizationZipCode;
	}

	public void setOrganizationZipCode(java.lang.String organizationZipCode) {
		this.organizationZipCode = organizationZipCode;
	}

	public java.lang.String getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(java.lang.String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public java.lang.String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(java.lang.String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(java.lang.String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public java.lang.String getOrganizationFrom() {
		return organizationFrom;
	}

	public void setOrganizationFrom(java.lang.String organizationFrom) {
		this.organizationFrom = organizationFrom;
	}

	public java.lang.String getOrganizationNameFrom() {
		return organizationNameFrom;
	}

	public void setOrganizationNameFrom(java.lang.String organizationNameFrom) {
		this.organizationNameFrom = organizationNameFrom;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getLeaveDesc() {
		return leaveDesc;
	}

	public void setLeaveDesc(java.lang.String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}

	public java.lang.String getOrganizationTo() {
		return organizationTo;
	}

	public void setOrganizationTo(java.lang.String organizationTo) {
		this.organizationTo = organizationTo;
	}

	public java.lang.String getOrganizationNameTo() {
		return organizationNameTo;
	}

	public void setOrganizationNameTo(java.lang.String organizationNameTo) {
		this.organizationNameTo = organizationNameTo;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getIntoTime() {
		return intoTime;
	}

	public void setIntoTime(java.lang.String intoTime) {
		this.intoTime = intoTime;
	}

	public java.lang.String getOrganizationInto() {
		return organizationInto;
	}

	public void setOrganizationInto(java.lang.String organizationInto) {
		this.organizationInto = organizationInto;
	}

	public java.lang.String getOrganizationNameInto() {
		return organizationNameInto;
	}

	public void setOrganizationNameInto(java.lang.String organizationNameInto) {
		this.organizationNameInto = organizationNameInto;
	}

	public java.lang.String getIntoDesc() {
		return intoDesc;
	}

	public void setIntoDesc(java.lang.String intoDesc) {
		this.intoDesc = intoDesc;
	}

	public java.lang.String getAuditUserInfo() {
		return auditUserInfo;
	}

	public void setAuditUserInfo(java.lang.String auditUserInfo) {
		this.auditUserInfo = auditUserInfo;
	}

	public java.lang.String getAuditUserNameInfo() {
		return auditUserNameInfo;
	}

	public void setAuditUserNameInfo(java.lang.String auditUserNameInfo) {
		this.auditUserNameInfo = auditUserNameInfo;
	}

	public java.lang.String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.lang.String auditTime) {
		this.auditTime = auditTime;
	}

	public java.lang.String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(java.lang.String auditDesc) {
		this.auditDesc = auditDesc;
	}
}
