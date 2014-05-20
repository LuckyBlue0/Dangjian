package cn.com.do1.component.mobileclient.deviceloginrecord.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class TbDeviceLoginRecordPO implements IBaseDBVO {

	private java.lang.String id;

	@Validation(must = true, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "用户ID", showLength = 50)
	private java.lang.String userId;

	@Validation(must = true, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "设备编号", showLength = 64)
	private java.lang.String deviceId;

	@DictDesc(typeName = "os_type", refField = "osType")
	@Validation(must = true, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "系统类型", showLength = 22)
	private java.lang.Integer osType;

	@Validation(must = true, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "系统版本", showLength = 50)
	private java.lang.String osVersion;

	@Validation(must = true, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "设备型号", showLength = 100)
	private java.lang.String deviceModel;

	@Validation(must = true, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "登陆时间", showLength = 7)
	private java.util.Date loginTime;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setDeviceId(java.lang.String deviceId) {
		this.deviceId = deviceId;
	}

	public java.lang.String getDeviceId() {
		return this.deviceId;
	}


	public java.lang.Integer getOsType() {
		return osType;
	}

	public void setOsType(java.lang.Integer osType) {
		this.osType = osType;
	}

	public void setOsVersion(java.lang.String osVersion) {
		this.osVersion = osVersion;
	}

	public java.lang.String getOsVersion() {
		return this.osVersion;
	}

	public void setDeviceModel(java.lang.String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public java.lang.String getDeviceModel() {
		return this.deviceModel;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setLoginTime(java.lang.String loginTime) {
		this.loginTime = ConvertUtil.cvStUtildate(loginTime);
	}

	public java.util.Date getLoginTime() {
		return this.loginTime;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_DEVICE_LOGIN_RECORD";
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
}
