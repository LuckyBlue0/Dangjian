package cn.com.do1.component.score.orgscore.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbOrganizationScoreInfoPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String organizationId;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "scoreType", showType = "input", showOrder = 2, showLength = 36)
	private java.lang.String scoreType;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "score", showType = "input", showOrder = 3, showLength = 22)
	private java.lang.Long score;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "getTime", showType = "datetime", showOrder = 4, showLength = 7)
	private java.util.Date getTime;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "scoreFrom", showType = "input", showOrder = 5, showLength = 36)
	private java.lang.String scoreFrom;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "recordFrom", showType = "input", showOrder = 6, showLength = 36)
	private java.lang.String recordFrom;
	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "note", showType = "input", showOrder = 7, showLength = 2000)
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

	public void setScore(java.lang.Long score) {
		this.score = score;
	}

	public java.lang.Long getScore() {
		return this.score;
	}

	public void setGetTime(java.util.Date getTime) {
		this.getTime = getTime;
	}

	public void setGetTime(java.lang.String getTime) {
		this.getTime = ConvertUtil.cvStUtildate(getTime);
	}

	public java.util.Date getGetTime() {
		return this.getTime;
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

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_ORGANIZATION_SCORE_INFO";
	}

	/**
	 * 获取对应表的主键字段名称
	 * 
	 * @return
	 */
	public String _getPKColumnName() {
		return "id";
	}

	/**
	 * 获取主键值
	 * 
	 * @return
	 */
	public String _getPKValue() {
		return String.valueOf(id);
	}

	/**
	 * 设置主键的值
	 * 
	 * @return
	 */
	public void _setPKValue(Object value) {
		this.id = (java.lang.String) value;
	}

}
