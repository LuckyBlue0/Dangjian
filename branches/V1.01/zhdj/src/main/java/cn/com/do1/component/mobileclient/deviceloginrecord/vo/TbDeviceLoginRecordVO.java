package cn.com.do1.component.mobileclient.deviceloginrecord.vo;

import java.util.Arrays;
import java.util.List;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class TbDeviceLoginRecordVO implements IBaseVO {
	private String id;

	@Validation(must = true, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "用户ID", showLength = 50)
	private String userId;

	@Validation(must = true, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "设备编号", showLength = 64)
	private String deviceId;

	@DictDesc(typeName = "os_type", refField = "osType")
	private String _osType_desc;

	@Validation(must = true, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "系统类型", showLength = 22)
	private String osType;

	@Validation(must = true, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "系统版本", showLength = 50)
	private String osVersion;

	@Validation(must = true, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "设备型号", showLength = 100)
	private String deviceModel;

	@Validation(must = true, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "登陆时间", showLength = 7)
	private String loginTime;

	private List<String> tableNames;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsType() {
		return this.osType;
	}

	/** 字典关联属性 */
	public void set_osType_desc(String _osType_desc) {
		this._osType_desc = _osType_desc;
	}

	public String get_osType_desc() {
		return this._osType_desc;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public List<String> _getTableNames() {
		if (tableNames == null || tableNames.isEmpty()) {
			tableNames = Arrays.asList("TB_DEVICE_LOGIN_RECORD".split(","));
		}
		return tableNames;
	}
}
