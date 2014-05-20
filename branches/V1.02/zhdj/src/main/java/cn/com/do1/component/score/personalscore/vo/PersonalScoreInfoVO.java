package cn.com.do1.component.score.personalscore.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.util.AssertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PersonalScoreInfoVO {
	private java.lang.String id;
	private java.lang.String userId;
	private java.lang.String scoreType;
	@DictDesc(refField = "scoreType", typeName = "personalScore")
	private java.lang.String scoreTypeDesc;
	private java.lang.String score;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String getTime;
	private java.lang.String scoreFrom;
	@DictDesc(refField = "scoreFrom", typeName = "scoreFrom")
	private java.lang.String scoreFromDesc;
	private java.lang.String scoreDesc;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setScoreType(java.lang.String scoreType) {
		this.scoreType = scoreType;
	}

	public java.lang.String getScoreDesc() {
		return scoreDesc;
	}

	public void setScoreDesc(java.lang.String scoreDesc) {
		this.scoreDesc = scoreDesc;
	}

	public java.lang.String getScoreType() {
		return this.scoreType;
	}

	public java.lang.String getScoreTypeDesc() {
		return scoreTypeDesc;
	}

	public void setScoreTypeDesc(java.lang.String scoreTypeDesc) {
		this.scoreTypeDesc = scoreTypeDesc;
	}

	public java.lang.String getScoreFromDesc() {
		return scoreFromDesc;
	}

	public void setScoreFromDesc(java.lang.String scoreFromDesc) {
		this.scoreFromDesc = scoreFromDesc;
	}

	public void setScore(java.lang.String score) {
		this.score = score;
	}

	public void setScoreFrom(java.lang.String scoreFrom) {
		this.scoreFrom = scoreFrom;
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

	public java.lang.String getScoreFrom() {
		return this.scoreFrom;
	}
}
