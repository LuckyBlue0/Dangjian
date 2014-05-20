package cn.com.do1.component.photowall.photowall.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class TbPhotoWallPO implements IBaseDBVO {

	private java.lang.String id;

	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "留影墙主题", showLength = 100)
	private java.lang.String title;

	@DictDesc(typeName = "photowallType", refField = "type")
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "select", showOrder = 2, showName = "留影墙类型", showLength = 2)
	private java.lang.String type;

	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "描述", showLength = 2000)
	private java.lang.String des;

	@DictDesc(typeName = "photowallStatus", refField = "status")
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "select", showOrder = 4, showName = "状态", showLength = 2)
	private java.lang.String status;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "创建人ID", showLength = 36)
	private java.lang.String createUserId;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "创建时间", showLength = 7)
	@FormatMask(type="date",value="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setDes(java.lang.String des) {
		this.des = des;
	}

	public java.lang.String getDes() {
		return this.des;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	public java.lang.String getCreateUserId() {
		return this.createUserId;
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
		return "TB_PHOTO_WALL";
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
