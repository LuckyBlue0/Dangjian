package cn.com.do1.component.index.membercenter.model;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PratyDetailsClientVO {

	private String id;
	private String title;
	private String type;
	private String typeDesc;
	private String createUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private String endTime;
	private String addresss;
	private String content;
	private String joinUser;
	private String forLeaveStatus;
	private String signInStatus;
	private String signUpStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getJoinUser() {
		return joinUser;
	}
	public void setJoinUser(String joinUser) {
		this.joinUser = joinUser;
	}
	public String getForLeaveStatus() {
		return forLeaveStatus;
	}
	public void setForLeaveStatus(String forLeaveStatus) {
		this.forLeaveStatus = forLeaveStatus;
	}
	public String getSignInStatus() {
		return signInStatus;
	}
	public void setSignInStatus(String signInStatus) {
		this.signInStatus = signInStatus;
	}
	public String getSignUpStatus() {
		return signUpStatus;
	}
	public void setSignUpStatus(String signUpStatus) {
		this.signUpStatus = signUpStatus;
	}
}

