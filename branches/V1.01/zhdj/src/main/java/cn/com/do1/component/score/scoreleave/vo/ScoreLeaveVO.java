package cn.com.do1.component.score.scoreleave.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ScoreLeaveVO {
	private java.lang.String id;
	private java.lang.String name;
	private java.lang.String type;
	@DictDesc(refField = "type", typeName = "scoreLeave")
	private java.lang.String typeDesc;
	private java.lang.String startScore;
	private java.lang.String endScore;
	private java.lang.String scoreDesc;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String createTime;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(java.lang.String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public java.lang.String getStartScore() {
		return startScore;
	}

	public void setStartScore(java.lang.String startScore) {
		this.startScore = startScore;
	}

	public java.lang.String getEndScore() {
		return endScore;
	}

	public void setEndScore(java.lang.String endScore) {
		this.endScore = endScore;
	}

	public java.lang.String getScoreDesc() {
		return scoreDesc;
	}

	public void setScoreDesc(java.lang.String scoreDesc) {
		this.scoreDesc = scoreDesc;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getType() {
		return this.type;
	}

}
