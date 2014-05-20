package cn.com.do1.component.news.newsinfo.vo;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsPreviewInfoVO {

	private java.lang.String title;
	@LargeObject
	private java.lang.String content;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String pushTime;
	private java.lang.String pushUser;
	private java.lang.String pushRole;
	private java.lang.String pushOrganizationName;
	private String source;
	
	
	public String getSource() {
		if (source == null) {
			source = pushRole == null ? ConfigMgr.get("system", "defaultOrgName", "管理平台") : pushRole;
		}
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public java.lang.String getPushRole() {
		if(pushRole == null){
			pushRole = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return pushRole;
	}
	public void setPushRole(java.lang.String pushRole) {
		this.pushRole = pushRole;
	}
	public java.lang.String getPushOrganizationName() {
		if(pushOrganizationName == null){
			pushOrganizationName = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return pushOrganizationName;
	}
	public void setPushOrganizationName(java.lang.String pushOrganizationName) {
		this.pushOrganizationName = pushOrganizationName;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getPushTime() {
		return pushTime;
	}
	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = pushTime;
	}
	public java.lang.String getPushUser() {
		if(pushUser == null){
			pushUser = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return pushUser;
	}
	public void setPushUser(java.lang.String pushUser) {
		this.pushUser = pushUser;
	}
	
}
