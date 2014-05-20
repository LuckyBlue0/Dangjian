package cn.com.do1.component.partywork.meetinguser.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * All rights reserved. User: ${user}
 */
public class TbMeetingUserPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = true, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "meetingId", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String meetingId;
	@Validation(must = true, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userId", showType = "input", showOrder = 2, showLength = 36)
	private java.lang.String userId;
	@Validation(must = true, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 3, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "signUpTime", showType = "datetime", showOrder = 4, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date signUpTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "signUpStatus", showType = "input", showOrder = 5, showLength = 22)
	private java.lang.Long signUpStatus;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "forLeaveTime", showType = "datetime", showOrder = 6, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date forLeaveTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "forLeaveStatus", showType = "input", showOrder = 7, showLength = 22)
	private java.lang.Long forLeaveStatus;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "signInTime", showType = "datetime", showOrder = 8, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date signInTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "signInStatus", showType = "input", showOrder = 9, showLength = 22)
	private java.lang.Long signInStatus;

	private java.lang.Long signUpChannel;
	private java.lang.Long signInChannel;
	private java.lang.Long forLeaveChannel;
	private java.lang.Long userType;  //0:党员,1：群众
	private Long isSupplement;		  //是否为补录人员
	private Long activityType;			//活动类型
	

	public Long getActivityType() {
		return activityType;
	}

	public void setActivityType(Long activityType) {
		this.activityType = activityType;
	}

	public Long getIsSupplement() {
		return isSupplement;
	}

	public void setIsSupplement(Long isSupplement) {
		this.isSupplement = isSupplement;
	}

	public java.lang.Long getForLeaveChannel() {
		return forLeaveChannel;
	}

	public void setForLeaveChannel(java.lang.Long forLeaveChannel) {
		this.forLeaveChannel = forLeaveChannel;
	}

	public java.lang.Long getUserType() {
		return userType;
	}

	public void setUserType(java.lang.Long userType) {
		this.userType = userType;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setMeetingId(java.lang.String meetingId) {
		this.meetingId = meetingId;
	}

	public java.lang.String getMeetingId() {
		return this.meetingId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
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

	public void setSignUpTime(java.util.Date signUpTime) {
		this.signUpTime = signUpTime;
	}

	public void setSignUpTime(java.lang.String signUpTime) {
		this.signUpTime = ConvertUtil.cvStUtildate(signUpTime);
	}

	public java.util.Date getSignUpTime() {
		return this.signUpTime;
	}

	public void setSignUpStatus(java.lang.Long signUpStatus) {
		this.signUpStatus = signUpStatus;
	}
	

	public java.lang.Long getSignUpStatus() {
		return this.signUpStatus;
	}

	public void setForLeaveTime(java.util.Date forLeaveTime) {
		this.forLeaveTime = forLeaveTime;
	}

	public void setForLeaveTime(java.lang.String forLeaveTime) {
		this.forLeaveTime = ConvertUtil.cvStUtildate(forLeaveTime);
	}

	public java.util.Date getForLeaveTime() {
		return this.forLeaveTime;
	}

	public void setForLeaveStatus(java.lang.Long forLeaveStatus) {
		this.forLeaveStatus = forLeaveStatus;
	}

	public java.lang.Long getForLeaveStatus() {
		return this.forLeaveStatus;
	}


	public java.util.Date getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(java.util.Date signInTime) {
		this.signInTime = signInTime;
	}

	public java.lang.Long getSignUpChannel() {
		return signUpChannel;
	}

	public void setSignUpChannel(java.lang.Long signUpChannel) {
		this.signUpChannel = signUpChannel;
	}

	public java.lang.Long getSignInChannel() {
		return signInChannel;
	}

	public void setSignInChannel(java.lang.Long signInChannel) {
		this.signInChannel = signInChannel;
	}

	public java.lang.Long getSignInStatus() {
		return signInStatus;
	}

	public void setSignInStatus(java.lang.Long signInStatus) {
		this.signInStatus = signInStatus;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_MEETING_USER";
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
