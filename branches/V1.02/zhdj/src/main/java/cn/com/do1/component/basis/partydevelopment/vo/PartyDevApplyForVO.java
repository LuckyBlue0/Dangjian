package cn.com.do1.component.basis.partydevelopment.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

public class PartyDevApplyForVO {
	private java.lang.String id;
	private java.lang.String userId;
	private java.lang.String applyForDesc;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String createTime;
	private java.lang.String auditUserId;
	private java.lang.String auditUserName;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String auditTime;
	private java.lang.String status;
	@DictDesc(refField = "status", typeName = "auditStatus")
	private java.lang.String statusDesc;
	private java.lang.String organizationIdentity;
	@DictDesc(refField = "organizationIdentity", typeName = "orgIdentity")
	private java.lang.String organizationIdentityDesc;
	private java.lang.String applyForOrgIdentity;
	@DictDesc(refField = "applyForOrgIdentity", typeName = "orgIdentity")
	private java.lang.String applyForOrgIdentityDesc;
	private java.lang.String specialty;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public java.lang.String getAuditUserName() {
		return auditUserName;
	}

	public java.lang.String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(java.lang.String specialty) {
		this.specialty = specialty;
	}

	public void setAuditUserName(java.lang.String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public java.lang.String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(java.lang.String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public java.lang.String getOrganizationIdentityDesc() {
		return organizationIdentityDesc;
	}

	public void setOrganizationIdentityDesc(
			java.lang.String organizationIdentityDesc) {
		this.organizationIdentityDesc = organizationIdentityDesc;
	}

	public java.lang.String getApplyForOrgIdentityDesc() {
		return applyForOrgIdentityDesc;
	}

	public void setApplyForOrgIdentityDesc(
			java.lang.String applyForOrgIdentityDesc) {
		this.applyForOrgIdentityDesc = applyForOrgIdentityDesc;
	}

	public void setApplyForDesc(java.lang.String applyForDesc) {
		this.applyForDesc = applyForDesc;
	}

	public java.lang.String getApplyForDesc() {
		return this.applyForDesc;
	}

	public void setAuditUserId(java.lang.String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.lang.String getAuditUserId() {
		return this.auditUserId;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.lang.String auditTime) {
		this.auditTime = auditTime;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setOrganizationIdentity(java.lang.String organizationIdentity) {
		this.organizationIdentity = organizationIdentity;
	}

	public java.lang.String getOrganizationIdentity() {
		return this.organizationIdentity;
	}

	public void setApplyForOrgIdentity(java.lang.String applyForOrgIdentity) {
		this.applyForOrgIdentity = applyForOrgIdentity;
	}

	public java.lang.String getApplyForOrgIdentity() {
		return this.applyForOrgIdentity;
	}

}
