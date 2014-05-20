package cn.com.do1.component.photowall.photowall.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class TbPhotoWallReviewPO implements IBaseDBVO {

	private java.lang.String id;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "留影墙Id", showLength = 36)
	private java.lang.String photoWallId;
	
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "评论者Id", showLength = 36)
	private java.lang.String userId;
	
	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 3, showName = "评论时间", showLength = 7)
	@FormatMask(type="date",value="yyyy-MM-dd HH:mm:ss")
	private java.util.Date time;
	
	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "评论内容", showLength = 2000)
	private java.lang.String content;

	@Validation(must = false, length = 255, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "评论图片", showLength = 255)
	private java.lang.String imgUrl;

	@DictDesc(typeName = "photowallStatus", refField = "status")
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "select", showOrder = 6, showName = "状态", showLength = 2)
	private java.lang.String status;

	

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPhotoWallId() {
		return photoWallId;
	}

	public void setPhotoWallId(java.lang.String photoWallId) {
		this.photoWallId = photoWallId;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(java.lang.String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_PHOTO_WALL_REVIEW";
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
