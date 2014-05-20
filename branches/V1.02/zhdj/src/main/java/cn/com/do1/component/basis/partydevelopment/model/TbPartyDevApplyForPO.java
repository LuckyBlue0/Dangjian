package cn.com.do1.component.basis.partydevelopment.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbPartyDevApplyForPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userId", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String userId;
	@Validation(must = false, length = 4000, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "applyForDesc", showType = "input", showOrder = 2, showLength = 4000)
	private java.lang.String applyForDesc;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 3, showLength = 7)
	private java.util.Date createTime;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "auditUserId", showType = "input", showOrder = 4, showLength = 36)
	private java.lang.String auditUserId;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "auditTime", showType = "datetime", showOrder = 5, showLength = 7)
	private java.util.Date auditTime;
	@Validation(must = false, length = 5, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "status", showType = "input", showOrder = 6, showLength = 5)
	private java.lang.String status;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationIdentity", showType = "input", showOrder = 7, showLength = 36)
	private java.lang.String organizationIdentity;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "applyForOrgIdentity", showType = "input", showOrder = 8, showLength = 36)
	private java.lang.String applyForOrgIdentity;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "specialty", showType = "input", showOrder = 8, showLength = 200)
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

	public java.lang.String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(java.lang.String specialty) {
		this.specialty = specialty;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setApplyForDesc(java.lang.String applyForDesc) {
		this.applyForDesc = applyForDesc;
	}

	public java.lang.String getApplyForDesc() {
		return this.applyForDesc;
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

	public void setAuditUserId(java.lang.String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.lang.String getAuditUserId() {
		return this.auditUserId;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	public void setAuditTime(java.lang.String auditTime) {
		this.auditTime = ConvertUtil.cvStUtildate(auditTime);
	}

	public java.util.Date getAuditTime() {
		return this.auditTime;
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

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_PARTY_DEV_APPLY_FOR";
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
