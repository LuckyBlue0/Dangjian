package cn.com.do1.component.index.index.model;

import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class LoginRequest extends BaseEncryptionVO{
	private String userId;
	private String username;
	private String userPwd;
	private String platformType;			//平台类型 1：android，2：iphone,手机客户端登陆时加分使用
	private String userType;				//用户类型 1：党员，2：普通群众
	private String deviceId;				//设备ID

	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	
}
