package cn.com.do1.component.vote.orgvote.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbOrgVoteListPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "voteOrg", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String voteOrg;
	
	
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "groups", showType = "input", showOrder = 3, showLength = 22)
	@DictDesc(refField = "groups", typeName = "groups")
	private java.lang.Long groups;
	
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 9, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 10, showLength = 36)
	private java.lang.String organizationId;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "orgVoteId", showType = "input", showOrder = 10, showLength = 36)
	private java.lang.String orgVoteId;
	
	
	public java.lang.String getOrgVoteId() {
		return orgVoteId;
	}

	public void setOrgVoteId(java.lang.String orgVoteId) {
		this.orgVoteId = orgVoteId;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setVoteOrg(java.lang.String voteOrg) {
		this.voteOrg = voteOrg;
	}

	public java.lang.String getVoteOrg() {
		return this.voteOrg;
	}


	public void setGroups(java.lang.Long groups) {
		this.groups = groups;
	}

	public java.lang.Long getGroups() {
		return this.groups;
	}


	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = ConvertUtil.cvStUtildate(createTime);
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public java.lang.String getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_ORG_VOTE_LIST";
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
