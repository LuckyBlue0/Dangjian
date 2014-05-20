package cn.com.do1.component.mobileclient.suggestion.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class SuggestionRequest extends BaseEncryptionVO{
	@FormatMask(type = "number")
	private String type;
	private String userName;
	private String suggestion;
	private String mobile;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
