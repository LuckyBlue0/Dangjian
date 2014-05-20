package cn.com.do1.component.partywork.ideologyreport.vo;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class IdeologyReportClientVO extends BaseEncryptionVO{
    private java.lang.String id ;
	private java.lang.String title ;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String createTime ;
	private java.lang.String postil;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String reviewTime ;
	private java.lang.String status ;
	private java.lang.String content ;
	private String name;
	private String createUser;
	private String reviewUser;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReviewUser() {
		return reviewUser;
	}
	public void setReviewUser(String reviewUser) {
		this.reviewUser = reviewUser;
	}
	public String getCreateUser() {
		if(createUser==null){
			createUser = name;
		}
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
