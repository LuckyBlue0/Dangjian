package cn.com.do1.component.partywork.meetinguser.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;


/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class MeetingUserVO {
	private java.lang.String id;
	private java.lang.String meetingId;
	private java.lang.String userId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String createTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String signUpTime;
	private java.lang.String signUpStatus;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String forLeaveTime;
	private java.lang.String forLeaveStatus;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String signInTime;
	private java.lang.String signInStatus;
	private String name;
	
	
	private java.lang.String signUpChannel;
	private java.lang.String signInChannel;
	private java.lang.String forLeaveChannel;
	private java.lang.String userType;  			//0:党员,1：群众
	private java.lang.String isSupplement;		  	//是否为补录人员
	
	public java.lang.String getForLeaveChannel() {
		return forLeaveChannel;
	}
	public void setForLeaveChannel(java.lang.String forLeaveChannel) {
		this.forLeaveChannel = forLeaveChannel;
	}
	public java.lang.String getUserType() {
		return userType;
	}
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	
	public java.lang.String getIsSupplement() {
		return isSupplement;
	}
	public void setIsSupplement(java.lang.String isSupplement) {
		this.isSupplement = isSupplement;
	}


	@DictDesc(refField = "signUpStatus", typeName = "meeting_signUp")
	private String singUpStatusDesc;
	
	@DictDesc(refField = "forLeaveStatus", typeName = "meeting_forLeave")
	private java.lang.String forLeaveStatusDesc;
	
	@DictDesc(refField = "signInStatus", typeName = "meeting_signLn")
	private java.lang.String signInStatusDesc;
	
	public String getSingUpStatusDesc() {
		return singUpStatusDesc;
	}
	public void setSingUpStatusDesc(String singUpStatusDesc) {
		this.singUpStatusDesc = singUpStatusDesc;
	}
	public java.lang.String getForLeaveStatusDesc() {
		return forLeaveStatusDesc;
	}
	public void setForLeaveStatusDesc(java.lang.String forLeaveStatusDesc) {
		this.forLeaveStatusDesc = forLeaveStatusDesc;
	}

	public java.lang.String getSignInStatusDesc() {
		return signInStatusDesc;
	}
	public void setSignInStatusDesc(java.lang.String signInStatusDesc) {
		this.signInStatusDesc = signInStatusDesc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(java.lang.String meetingId) {
		this.meetingId = meetingId;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.lang.String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getSignUpTime() {
		return signUpTime;
	}
	public void setSignUpTime(java.lang.String signUpTime) {
		this.signUpTime = signUpTime;
	}
	public java.lang.String getSignUpStatus() {
		return signUpStatus;
	}
	public void setSignUpStatus(java.lang.String signUpStatus) {
		this.signUpStatus = signUpStatus;
	}
	public java.lang.String getForLeaveTime() {
		return forLeaveTime;
	}
	public void setForLeaveTime(java.lang.String forLeaveTime) {
		this.forLeaveTime = forLeaveTime;
	}
	public java.lang.String getForLeaveStatus() {
		return forLeaveStatus;
	}
	public void setForLeaveStatus(java.lang.String forLeaveStatus) {
		this.forLeaveStatus = forLeaveStatus;
	}
	public java.lang.String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(java.lang.String signInTime) {
		this.signInTime = signInTime;
	}
	public java.lang.String getSignInStatus() {
		return signInStatus;
	}
	public void setSignInStatus(java.lang.String signInStatus) {
		this.signInStatus = signInStatus;
	}
	public java.lang.String getSignUpChannel() {
		return signUpChannel;
	}
	public void setSignUpChannel(java.lang.String signUpChannel) {
		this.signUpChannel = signUpChannel;
	}
	public java.lang.String getSignInChannel() {
		return signInChannel;
	}
	public void setSignInChannel(java.lang.String signInChannel) {
		this.signInChannel = signInChannel;
	}
}
