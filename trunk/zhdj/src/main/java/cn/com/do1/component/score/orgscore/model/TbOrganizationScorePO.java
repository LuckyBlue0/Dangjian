package cn.com.do1.component.score.orgscore.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbOrganizationScorePO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String organizationId;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "accumulativeScore", showType = "input", showOrder = 2, showLength = 22)
	private java.lang.Long accumulativeScore;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "ranking", showType = "input", showOrder = 3, showLength = 22)
	private java.lang.Long ranking;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "leave", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String leave;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 4, showLength = 7)
	private java.util.Date createTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "status", showType = "input", showOrder = 3, showLength = 22)
	private java.lang.Long status;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getLeave() {
		return leave;
	}

	public void setLeave(java.lang.String leave) {
		this.leave = leave;
	}

	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getOrganizationId() {
		return this.organizationId;
	}

	public void setAccumulativeScore(java.lang.Long accumulativeScore) {
		this.accumulativeScore = accumulativeScore;
	}

	public java.lang.Long getAccumulativeScore() {
		return this.accumulativeScore;
	}

	public void setRanking(java.lang.Long ranking) {
		this.ranking = ranking;
	}

	public java.lang.Long getRanking() {
		return this.ranking;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_ORGANIZATION_SCORE";
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
