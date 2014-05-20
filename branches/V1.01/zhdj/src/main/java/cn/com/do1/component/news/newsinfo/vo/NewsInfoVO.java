package cn.com.do1.component.news.newsinfo.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
public class NewsInfoVO {
	private java.lang.String newsInfoId;
	private java.lang.String title;
	@LargeObject
	private java.lang.String content;
	private java.lang.String status;
	private java.lang.String buyTop;
	private java.lang.String solidTop;
	private java.lang.String importance;
	private java.lang.String newsInfoType;
	private java.lang.String organizationId;
	private java.lang.String createUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String createTime;
	private java.lang.String lastModifyUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String lastModifyTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String pushTime;
	private java.lang.String imgPath;

	@DictDesc(refField = "status", typeName = "newsInfoStatus")
	private String statusDesc;
	@DictDesc(refField = "buyTop", typeName = "newsInfoBuyTop")
	private String buyTopDesc;
	@DictDesc(refField = "solidTop", typeName = "newsInfoSolidTop")
	private String solidTopDesc;
	@DictDesc(refField = "Importance", typeName = "newsInfoImportance")
	private String ImportanceDesc;

	private java.lang.String organizationName;

	private java.lang.String projectType;
	private java.lang.String bodyDigest;
	private java.lang.String tabBar;
	private java.lang.String source;

	public java.lang.String getProjectType() {
		return projectType;
	}

	public void setProjectType(java.lang.String projectType) {
		this.projectType = projectType;
	}

	public java.lang.String getBodyDigest() {
		return bodyDigest;
	}

	public void setBodyDigest(java.lang.String bodyDigest) {
		this.bodyDigest = bodyDigest;
	}

	public java.lang.String getTabBar() {
		return tabBar;
	}

	public void setTabBar(java.lang.String tabBar) {
		this.tabBar = tabBar;
	}

	public java.lang.String getSource() {
		return source;
	}

	public void setSource(java.lang.String source) {
		this.source = source;
	}

	public java.lang.String getOrganizationName() {
		if(organizationName == null){
			organizationName = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return organizationName;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSolidTopDesc() {
		return solidTopDesc;
	}

	public void setSolidTopDesc(String solidTopDesc) {
		this.solidTopDesc = solidTopDesc;
	}

	public String getImportanceDesc() {
		return ImportanceDesc;
	}

	public void setImportanceDesc(String importanceDesc) {
		ImportanceDesc = importanceDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getBuyTopDesc() {
		return buyTopDesc;
	}

	public void setBuyTopDesc(String buyTopDesc) {
		this.buyTopDesc = buyTopDesc;
	}

	public java.lang.String getNewsInfoId() {
		return newsInfoId;
	}

	public void setNewsInfoId(java.lang.String newsInfoId) {
		this.newsInfoId = newsInfoId;
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

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getBuyTop() {
		return buyTop;
	}

	public void setBuyTop(java.lang.String buyTop) {
		this.buyTop = buyTop;
	}

	public java.lang.String getSolidTop() {
		return solidTop;
	}

	public void setSolidTop(java.lang.String solidTop) {
		this.solidTop = solidTop;
	}

	public java.lang.String getImportance() {
		return importance;
	}

	public void setImportance(java.lang.String importance) {
		this.importance = importance;
	}

	public java.lang.String getNewsInfoType() {
		return newsInfoType;
	}

	public void setNewsInfoType(java.lang.String newsInfoType) {
		this.newsInfoType = newsInfoType;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	public java.lang.String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(java.lang.String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(java.lang.String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public java.lang.String getPushTime() {
		return pushTime;
	}

	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = pushTime;
	}

	public java.lang.String getImgPath() {
		return imgPath;
	}

	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}
}
