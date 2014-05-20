package cn.com.do1.component.score.orgscore.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrganizationScoreInfoVO {
	private java.lang.String id;
	private java.lang.String organizationId;
	private java.lang.String scoreType;
	@DictDesc(refField = "scoreType", typeName = "scoreType")
	private java.lang.String scoreTypeDesc;
	private java.lang.String score;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
	private java.lang.String getTime;
	private java.lang.String scoreFrom;
	private java.lang.String recordFrom;
	private java.lang.String note;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getOrganizationId() {
		return this.organizationId;
	}

	public void setScoreType(java.lang.String scoreType) {
		this.scoreType = scoreType;
	}

	public java.lang.String getScoreType() {
		return this.scoreType;
	}

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.String getScoreTypeDesc() {
		return scoreTypeDesc;
	}

	public void setScoreTypeDesc(java.lang.String scoreTypeDesc) {
		this.scoreTypeDesc = scoreTypeDesc;
	}

	public java.lang.String getGetTime() {
		return getTime;
	}

	public void setGetTime(java.lang.String getTime) {
		this.getTime = getTime;
	}

	public java.lang.String getScore() {
		return score;
	}

	public void setScore(java.lang.String score) {
		this.score = score;
	}

	public void setScoreFrom(java.lang.String scoreFrom) {
		this.scoreFrom = scoreFrom;
	}

	public java.lang.String getScoreFrom() {
		return this.scoreFrom;
	}

	public void setRecordFrom(java.lang.String recordFrom) {
		this.recordFrom = recordFrom;
	}

	public java.lang.String getRecordFrom() {
		return this.recordFrom;
	}

}
