package cn.com.do1.component.index.index.model;

import cn.com.do1.component.util.BaseEncryptionVO;

public class LoginUserVO extends BaseEncryptionVO{
	private String userId;
	private String username;
	private String userPwd;
	
	
	private String name;					//姓名
	private String organizationId;			//支部ID  当userType==1时，organizationId才有值	
	private String userType;				//用户类型 1：党员，2：普通群众
	
	private String platformType;			//平台类型 1：android，2：iphone,手机客户端登陆时加分使用

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	
}
