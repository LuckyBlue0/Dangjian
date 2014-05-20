package cn.com.do1.component.mobileclient.userinfo.vo;

import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class ModifyUserInfoVO extends BaseEncryptionVO{

	private String userId;
	private String userType;
	private String userName;
	private String portraitPic;
	private String email;
	private String mobile;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPortraitPic() {
		return portraitPic;
	}
	public void setPortraitPic(String portraitPic) {
		this.portraitPic = portraitPic;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
