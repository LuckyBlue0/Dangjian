package cn.com.do1.component.partywork.ideologyreport.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbIdeologyReportPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "title", showType = "input", showOrder = 1, showLength = 100)
	private java.lang.String title;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 2, showLength = 36)
	private java.lang.String organizationId;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "accessoryPath", showType = "input", showOrder = 3, showLength = 200)
	private java.lang.String accessoryPath;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "createUserId", showType = "input", showOrder = 4, showLength = 36)
	private java.lang.String createUserId;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 5, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	@Validation(must = false, length = 500, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "postil", showType = "input", showOrder = 6, showLength = 500)
	private java.lang.String postil;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "reviewUserId", showType = "input", showOrder = 7, showLength = 36)
	private java.lang.String reviewUserId;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "reviewTime", showType = "datetime", showOrder = 8, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date reviewTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "status", showType = "input", showOrder = 9, showLength = 22)
	private java.lang.Long status;
	@Validation(must = false, length = 0, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "content", showType = "input", showOrder = 10, showLength = 0)
	@LargeObject
	private java.lang.String content;
	private String accessName;

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

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public void setAccessoryPath(java.lang.String accessoryPath) {
		this.accessoryPath = accessoryPath;
	}

	public java.lang.String getAccessoryPath() {
		return this.accessoryPath;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
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

	public void setPostil(java.lang.String postil) {
		this.postil = postil;
	}

	public java.lang.String getPostil() {
		return this.postil;
	}

	public void setReviewUserId(java.lang.String reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	public java.lang.String getReviewUserId() {
		return this.reviewUserId;
	}

	public void setReviewTime(java.util.Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public void setReviewTime(java.lang.String reviewTime) {
		this.reviewTime = ConvertUtil.cvStUtildate(reviewTime);
	}

	public java.util.Date getReviewTime() {
		return this.reviewTime;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	public java.lang.Long getStatus() {
		return this.status;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_IDEOLOGY_REPORT";
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
