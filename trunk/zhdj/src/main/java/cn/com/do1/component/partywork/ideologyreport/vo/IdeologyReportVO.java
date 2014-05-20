package cn.com.do1.component.partywork.ideologyreport.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class IdeologyReportVO {
    private java.lang.String id ;
	private java.lang.String title ;
	private java.lang.String organizationId ;
	private java.lang.String accessoryPath ;
	private java.lang.String createUserId ;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String createTime ;
	private java.lang.String postil;
	private java.lang.String reviewUserId ;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String reviewTime ;
	private java.lang.String status ;
	@LargeObject
	private java.lang.String content ;
	private String organizationName;
	private String name;
	@DictDesc(refField = "status", typeName = "reviewStatus")
	private String statusDesc;
	private String accessoryName;
	
	private java.lang.String reviewUser;
	
	public java.lang.String getReviewUser() {
		return reviewUser;
	}
	public void setReviewUser(java.lang.String reviewUser) {
		this.reviewUser = reviewUser;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}
	public java.lang.String getAccessoryPath() {
		return accessoryPath;
	}
	public void setAccessoryPath(java.lang.String accessoryPath) {
		this.accessoryPath = accessoryPath;
	}
	public java.lang.String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}
	public java.lang.String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getPostil() {
		return postil;
	}
	public void setPostil(java.lang.String postil) {
		this.postil = postil;
	}
	public java.lang.String getReviewUserId() {
		return reviewUserId;
	}
	public void setReviewUserId(java.lang.String reviewUserId) {
		this.reviewUserId = reviewUserId;
	}
	public java.lang.String getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(java.lang.String reviewTime) {
		this.reviewTime = reviewTime;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
