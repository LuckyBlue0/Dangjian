package cn.com.do1.component.basis.newstype.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsTypeVO {
	private java.lang.String newsTypeId;
	private java.lang.String name;
	private java.lang.String parentColunmName;
	private java.lang.String orderValue;
	private java.lang.String des;
	private java.lang.String type;
	private java.lang.String link;
	private java.lang.String createType;
	private java.lang.String attributeId;
	private java.lang.String createUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd") 
	private java.lang.String createTime;
	private java.lang.String lastModifyUserId;
	@FormatMask(type = "date", value = "yyyy-MM-dd") 
	private java.lang.String lastModifyTime;
	private java.lang.String useStatus;
	
	
	@DictDesc(refField = "createType", typeName = "newsTypeCreateWay")
	private String createTypeDesc;
	@DictDesc(refField = "useStatus", typeName = "newsTypeUseStatus")
	private String useStatusDesc;
	private String parentName;
	
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCreateTypeDesc() {
		return createTypeDesc;
	}
	public void setCreateTypeDesc(String createTypeDesc) {
		this.createTypeDesc = createTypeDesc;
	}
	public String getUseStatusDesc() {
		return useStatusDesc;
	}
	public void setUseStatusDesc(String useStatusDesc) {
		this.useStatusDesc = useStatusDesc;
	}

	
	public java.lang.String getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(java.lang.String newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getParentColunmName() {
		return parentColunmName;
	}
	public void setParentColunmName(java.lang.String parentColunmName) {
		this.parentColunmName = parentColunmName;
	}
	public java.lang.String getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(java.lang.String orderValue) {
		this.orderValue = orderValue;
	}
	public java.lang.String getDes() {
		return des;
	}
	public void setDes(java.lang.String des) {
		this.des = des;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getLink() {
		return link;
	}
	public void setLink(java.lang.String link) {
		this.link = link;
	}
	public java.lang.String getCreateType() {
		return createType;
	}
	public void setCreateType(java.lang.String createType) {
		this.createType = createType;
	}
	public java.lang.String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(java.lang.String attributeId) {
		this.attributeId = attributeId;
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
	public java.lang.String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(java.lang.String useStatus) {
		this.useStatus = useStatus;
	}
	
}
