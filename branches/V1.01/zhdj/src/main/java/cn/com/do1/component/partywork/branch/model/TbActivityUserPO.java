package cn.com.do1.component.partywork.branch.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbActivityUserPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "activityId", showType = "input", showOrder = 1, showLength = 36)
	private java.lang.String activityId;
	public java.lang.String getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.String activityId) {
		this.activityId = activityId;
	}


	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}


	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userId", showType = "input", showOrder = 2, showLength = 22)
	private java.lang.String userId;
	
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 15, showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;


	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
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
		return "TB_ACTIVITY_USER";
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

	/**
	 * 重写默认的toString方法，使其调用输出的内容更有意义
	 */
	public String toString() {
		return "";
	}
}
