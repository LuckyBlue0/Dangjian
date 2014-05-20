package cn.com.do1.component.partywork.volunteer.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.shyk.meeting.util.MeetingUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class VolunteerVO {
	private java.lang.String id;
	private java.lang.String title;
	private java.lang.String type;
	private java.lang.String oraganizationId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String endTime;
	private java.lang.String address;
	private java.lang.String content;
	private java.lang.String imgPath;
	@LargeObject
	private java.lang.String record;
	private java.lang.String themeImgPath;
	private java.lang.String status;
	private java.lang.String createUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String createTime;
	private java.lang.String auditUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String auditTime;
	private java.lang.String signUpStatus;//志愿活动报名状态
	@DictDesc(refField = "signUpStatus", typeName = "signUpStatus")
	private java.lang.String signUpStatusDesc;//志愿活动报名状态
	
	private java.lang.String organizationName;
	@DictDesc(refField = "type", typeName = "volunteerType")
	private String typeDesc;
	
	private String name;
	private java.lang.String whetherEnd;
	private String createUserName;
	
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public java.lang.String getWhetherEnd() {
		return whetherEnd;
	}

	public void setWhetherEnd(java.lang.String whetherEnd) {
		this.whetherEnd = whetherEnd;
	}

	public java.lang.String getSignUpStatus() {
		return signUpStatus;
	}

	public void setSignUpStatus(java.lang.String signUpStatus) {
		this.signUpStatus = signUpStatus;
	}

	public java.lang.String getSignUpStatusDesc() {
		return signUpStatusDesc;
	}

	public void setSignUpStatusDesc(java.lang.String signUpStatusDesc) {
		this.signUpStatusDesc = signUpStatusDesc;
	}

	public void setCarryOutStatus(String carryOutStatus) {
		this.carryOutStatus = carryOutStatus;
	}

	@DictDesc(refField = "status", typeName = "activityAuditStatus")
	private String statusDesc;
	public String getStatusDesc() {
		return statusDesc;
	}
	
	private String carryOutStatus;//开展状态
	
	public String getCarryOutStatus() {
		if(AssertUtil.isEmpty(carryOutStatus)){
			carryOutStatus = MeetingUtil.getCarryOutStatus(startTime, endTime);
		}
		return carryOutStatus;
	}
	
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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
	public java.lang.String getOraganizationId() {
		return oraganizationId;
	}
	public void setOraganizationId(java.lang.String oraganizationId) {
		this.oraganizationId = oraganizationId;
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
	public java.lang.String getImgPath() {
		return imgPath;
	}
	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}
	public java.lang.String getRecord() {
		return record;
	}
	public void setRecord(java.lang.String record) {
		this.record = record;
	}
	public java.lang.String getThemeImgPath() {
		return themeImgPath;
	}
	public void setThemeImgPath(java.lang.String themeImgPath) {
		this.themeImgPath = themeImgPath;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
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
}
