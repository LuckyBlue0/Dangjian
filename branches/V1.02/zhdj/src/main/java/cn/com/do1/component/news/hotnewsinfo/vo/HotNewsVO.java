package cn.com.do1.component.news.hotnewsinfo.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class HotNewsVO {
	private java.lang.String id;
	private java.lang.String title;
	@LargeObject
	private java.lang.String content;
	private java.lang.String status;
	private java.lang.String buyTop;
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
	private java.lang.String source ;
	private java.lang.String organizationName;
	private java.lang.String bodyDigest ;
	
	
	public java.lang.String getBodyDigest() {
		return bodyDigest;
	}
	public void setBodyDigest(java.lang.String bodyDigest) {
		this.bodyDigest = bodyDigest;
	}
	@DictDesc(refField = "buyTop", typeName = "newsInfoBuyTop")
	private String buyTopDesc;
	
	public String getBuyTopDesc() {
		return buyTopDesc;
	}
	public void setBuyTopDesc(String buyTopDesc) {
		this.buyTopDesc = buyTopDesc;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
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
	public java.lang.String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(java.lang.String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
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
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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
	public java.lang.String getSource() {
		return source;
	}
	public void setSource(java.lang.String source) {
		this.source = source;
	}
}
