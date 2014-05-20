package cn.com.do1.component.mobileclient.device.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class TbDevicePO implements IBaseDBVO {

	private java.lang.String id;

	@Validation(must = true, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "设备编号", showLength = 64)
	private java.lang.String deviceId;

	@DictDesc(typeName = "os_type", refField = "osType")
	@Validation(must = true, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "系统类型", showLength = 22)
	private java.lang.Integer osType;

	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "系统版本", showLength = 50)
	private java.lang.String osVersion;

	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "设备型号", showLength = 100)
	private java.lang.String deviceModel;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 5, showName = "创建时间", showLength = 7)
	private java.util.Date createTime;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "更新时间", showLength = 7)
	private java.util.Date updateTime;
	
	@Validation(must = false, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "设备token", showLength = 64)
	private java.lang.String deviceToken;

	public java.lang.String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(java.lang.String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setDeviceId(java.lang.String deviceId) {
		this.deviceId = deviceId;
	}

	public java.lang.String getDeviceId() {
		return this.deviceId;
	}

	public void setOsType(java.lang.Integer osType) {
		this.osType = osType;
	}

	public java.lang.Integer getOsType() {
		return this.osType;
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

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = ConvertUtil.cvStUtildate(createTime);
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateTime(java.lang.String updateTime) {
		this.updateTime = ConvertUtil.cvStUtildate(updateTime);
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_DEVICE";
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
