package cn.com.do1.component.index.membercenter.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.shyk.meeting.util.MeetingUtil;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 * 三会一课/支部活动/志愿活动列表VO
 */
public class PartyListClientVO {

	private String id;
	private String title;
	private String createUserName;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private String createTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss") 
	private java.lang.String endTime;
	private int carryOutStatus;//开展状态
	private String forLeaveStatus;
	
	public int getCarryOutStatus() {
		carryOutStatus = MeetingUtil.getCarryOutStat(startTime, endTime);
		return carryOutStatus;
	}
	public void setCarryOutStatus(int carryOutStatus) {
		this.carryOutStatus = carryOutStatus;
	}
	public String getId() {
		return id;
	}
	public String getForLeaveStatus() {
		return forLeaveStatus;
	}
	public void setForLeaveStatus(String forLeaveStatus) {
		this.forLeaveStatus = forLeaveStatus;
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

	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
