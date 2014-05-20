package cn.com.do1.component.partywork.volunteer.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbVolunteerActivityPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "title", showType = "input", showOrder = 1, showLength = 100)
	private java.lang.String title;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "type", showType = "input", showOrder = 2, showLength = 22)
	private java.lang.Long type;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 3, showLength = 36)
	private java.lang.String organizationId;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "startTime", showType = "datetime", showOrder = 4, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date startTime;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "endTime", showType = "datetime", showOrder = 5, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endTime;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "address", showType = "input", showOrder = 6, showLength = 200)
	private java.lang.String address;
	@Validation(must = false, length = 1000, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "content", showType = "input", showOrder = 7, showLength = 1000)
	private java.lang.String content;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "imgPath", showType = "input", showOrder = 8, showLength = 200)
	private java.lang.String imgPath;
	@Validation(must = false, length = 0, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "record", showType = "input", showOrder = 9, showLength = 0)
	@LargeObject
	private java.lang.String record;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "themeImgPath", showType = "input", showOrder = 10, showLength = 200)
	private java.lang.String themeImgPath;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "status", showType = "input", showOrder = 11, showLength = 22)
	private java.lang.Long status;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "createUserId", showType = "input", showOrder = 12, showLength = 36)
	private java.lang.String createUserId;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "createUserName", showType = "input", showOrder = 14, showLength = 36)
	private java.lang.String createUserName;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 13, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "auditUserId", showType = "input", showOrder = 14, showLength = 36)
	private java.lang.String auditUserId;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "auditTime", showType = "datetime", showOrder = 15, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date auditTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "whetherEnd", showType = "input", showOrder = 4, showLength = 22)
	private java.lang.Long whetherEnd;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "qrCode", showType = "input", showOrder = 12, showLength = 200)
	private java.lang.String qrCode;
	
	
	
	public java.lang.String getQrCode() {
		return qrCode;
	}

	public void setQrCode(java.lang.String qrCode) {
		this.qrCode = qrCode;
	}

	private String opinion;
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public java.lang.Long getWhetherEnd() {
		return whetherEnd;
	}

	public void setWhetherEnd(java.lang.Long whetherEnd) {
		this.whetherEnd = whetherEnd;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.Long getType() {
		return this.type;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = ConvertUtil.cvStUtildate(startTime);
	}

	public java.util.Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = ConvertUtil.cvStUtildate(endTime);
	}

	public java.util.Date getEndTime() {
		return this.endTime;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}

	public java.lang.String getImgPath() {
		return this.imgPath;
	}

	public java.lang.String getRecord() {
		return record;
	}

	public void setRecord(java.lang.String record) {
		this.record = record;
	}

	public void setThemeImgPath(java.lang.String themeImgPath) {
		this.themeImgPath = themeImgPath;
	}

	public java.lang.String getThemeImgPath() {
		return this.themeImgPath;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	public java.lang.Long getStatus() {
		return this.status;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	public java.lang.String getCreateUserId() {
		return this.createUserId;
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

	public java.lang.String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(java.lang.String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.util.Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	public java.lang.String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(java.lang.String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_VOLUNTEER_ACTIVITY";
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

	/**
	 * 重写默认的toString方法，使其调用输出的内容更有意义
	 */
	public String toString() {
		return "";
	}
}
