package cn.com.do1.component.mobileclient.device.vo;

import cn.com.do1.component.util.BaseEncryptionVO;


/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class DeviceRequest extends BaseEncryptionVO{

	private String id;
	private String deviceId;
	private String osType;
	private String osVersion;
	private String deviceModel;
	private String deviceToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
}
