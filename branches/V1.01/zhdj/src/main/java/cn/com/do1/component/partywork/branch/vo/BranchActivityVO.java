package cn.com.do1.component.partywork.branch.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.shyk.meeting.util.MeetingUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
public class BranchActivityVO {
	private java.lang.String id;
	private java.lang.String title;
	private java.lang.String type;
	private java.lang.String organizationId;
	private java.lang.String whetherEnd;
	private java.lang.String whetherRecommend;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String endTime;
	private java.lang.String address;
	private java.lang.String content;
	@LargeObject
	private java.lang.String record;
	private java.lang.String qrCode;
	private java.lang.String smsNotice;
	private java.lang.String createUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String createTime;
	private java.lang.String status;
	private java.lang.String auditUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String auditTime;
	private java.lang.String organizationName;
	private java.lang.String forLeaveStatus;
	private java.lang.String signInStatus;
	private java.lang.String meetingId;

	private String imgPath;
	
	private String joinStatus;

	private String userId;
	
	private String carryOutStatus;//开展状态
	private String createUserName;
	
	private String userName;
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCarryOutStatus() {
		if(AssertUtil.isEmpty(carryOutStatus)){
			carryOutStatus = MeetingUtil.getCarryOutStatus(startTime, endTime);
		}
		return carryOutStatus;
	}

	public java.lang.String getRecord() {
		return record;
	}

	public void setRecord(java.lang.String record) {
		this.record = record;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@DictDesc(refField = "type", typeName = "branchActivityType")
	private String typeDesc;

	@DictDesc(refField = "whetherEnd", typeName = "meetingWhetherEnd")
	private java.lang.String whetherEndDesc;

	@DictDesc(refField = "whetherRecommend", typeName = "meetingWhetherRecommend")
	private java.lang.String whetherRecommendDesc;

	@DictDesc(refField = "smsNotice", typeName = "meetingSmsNotice")
	private java.lang.String smsNoticeDesc;

	@DictDesc(refField = "status", typeName = "newsInfoStatus")
	private String statusDesc;


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

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getWhetherEnd() {
		return whetherEnd;
	}

	public void setWhetherEnd(java.lang.String whetherEnd) {
		this.whetherEnd = whetherEnd;
	}

	public java.lang.String getWhetherRecommend() {
		return whetherRecommend;
	}

	public void setWhetherRecommend(java.lang.String whetherRecommend) {
		this.whetherRecommend = whetherRecommend;
	}

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getQrCode() {
		return qrCode;
	}

	public void setQrCode(java.lang.String qrCode) {
		this.qrCode = qrCode;
	}

	public java.lang.String getSmsNotice() {
		return smsNotice;
	}

	public void setSmsNotice(java.lang.String smsNotice) {
		this.smsNotice = smsNotice;
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

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(java.lang.String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.lang.String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.lang.String auditTime) {
		this.auditTime = auditTime;
	}

	public java.lang.String getOrganizationName() {
		if(organizationName == null){
			organizationName = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public java.lang.String getWhetherEndDesc() {
		return whetherEndDesc;
	}

	public void setWhetherEndDesc(java.lang.String whetherEndDesc) {
		this.whetherEndDesc = whetherEndDesc;
	}

	public java.lang.String getWhetherRecommendDesc() {
		return whetherRecommendDesc;
	}

	public void setWhetherRecommendDesc(java.lang.String whetherRecommendDesc) {
		this.whetherRecommendDesc = whetherRecommendDesc;
	}

	public java.lang.String getSmsNoticeDesc() {
		return smsNoticeDesc;
	}

	public void setSmsNoticeDesc(java.lang.String smsNoticeDesc) {
		this.smsNoticeDesc = smsNoticeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public void setForLeaveStatus(java.lang.String forLeaveStatus) {
		this.forLeaveStatus = forLeaveStatus;
	}

	public java.lang.String getForLeaveStatus() {
		return forLeaveStatus;
	}


	public java.lang.String getSignInStatus() {
		return signInStatus;
	}

	public void setSignInStatus(java.lang.String signInStatus) {
		this.signInStatus = signInStatus;
	}

	public void setCarryOutStatus(String carryOutStatus) {
		this.carryOutStatus = carryOutStatus;
	}

	public void setMeetingId(java.lang.String meetingId) {
		this.meetingId = meetingId;
	}

	public java.lang.String getMeetingId() {
		return meetingId;
	}

	public String getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
