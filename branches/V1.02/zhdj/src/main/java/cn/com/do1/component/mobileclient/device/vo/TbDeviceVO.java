package cn.com.do1.component.mobileclient.device.vo;

import java.util.Arrays;
import java.util.List;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class TbDeviceVO implements IBaseVO {
	private String id;

	@Validation(must = true, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "设备编号", showLength = 64)
	private String deviceId;

	@DictDesc(typeName = "os_type", refField = "osType")
	private String _osType_desc;

	@Validation(must = true, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "系统类型", showLength = 22)
	private String osType;

	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "系统版本", showLength = 50)
	private String osVersion;

	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "设备型号", showLength = 100)
	private String deviceModel;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 5, showName = "创建时间", showLength = 7)
	private String createTime;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "更新时间", showLength = 7)
	private String updateTime;
	
	@Validation(must = false, length = 64, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "设备token", showLength = 64)
	private java.lang.String deviceToken;

	public java.lang.String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(java.lang.String deviceToken) {
		this.deviceToken = deviceToken;
	}

	private List<String> tableNames;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public List<String> _getTableNames() {
		if (tableNames == null || tableNames.isEmpty()) {
			tableNames = Arrays.asList("TB_DEVICE".split(","));
		}
		return tableNames;
	}
}
