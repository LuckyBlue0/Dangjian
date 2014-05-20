package cn.com.do1.component.index.membercenter.model;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PartyDetailsClientVO {
	private String id;
	private String title;
	private String type;
	private String typeDesc;
	private String createUserName;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private String endTime;
	private String address;
	private String content;
	private String joinUser;
	private String forLeaveStatus;
	private String signInStatus;
	private String signUpStatus;
	
	private String signUpCount;
	private String signInCount;
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
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getSignUpCount() {
		return signUpCount;
	}
	public void setSignUpCount(String signUpCount) {
		this.signUpCount = signUpCount;
	}
	public String getSignInCount() {
		return signInCount;
	}
	public void setSignInCount(String signInCount) {
		this.signInCount = signInCount;
	}
}
