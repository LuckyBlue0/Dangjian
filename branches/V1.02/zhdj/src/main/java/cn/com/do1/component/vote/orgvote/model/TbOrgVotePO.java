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
public class TbOrgVotePO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "topic", showType = "input", showOrder = 2, showLength = 36)
	private java.lang.String topic;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "startTime", showType = "datetime", showOrder = 4, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date startTime;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "endTime", showType = "datetime", showOrder = 5, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "pushStatus", showType = "input", showOrder = 7, showLength = 22)
	@DictDesc(refField = "pushStatus", typeName = "pushStatus")
	private java.lang.Long pushStatus;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "pushTime", showType = "datetime", showOrder = 8, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pushTime;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 9, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setTopic(java.lang.String topic) {
		this.topic = topic;
	}

	public java.lang.String getTopic() {
		return this.topic;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = ConvertUtil.cvStUtildate(startTime);
	}

	public java.util.Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = ConvertUtil.cvStUtildate(endTime);
	}

	public java.util.Date getEndTime() {
		return this.endTime;
	}

	public void setPushStatus(java.lang.Long pushStatus) {
		this.pushStatus = pushStatus;
	}

	public java.lang.Long getPushStatus() {
		return this.pushStatus;
	}

	public void setPushTime(java.util.Date pushTime) {
		this.pushTime = pushTime;
	}

	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = ConvertUtil.cvStUtildate(pushTime);
	}

	public java.util.Date getPushTime() {
		return this.pushTime;
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

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_ORG_VOTE";
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
