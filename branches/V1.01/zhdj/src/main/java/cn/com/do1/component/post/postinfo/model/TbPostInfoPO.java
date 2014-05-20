package cn.com.do1.component.post.postinfo.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.component.share.sharemanager.model.TbReplyPO;

import java.util.Date;
import java.util.List;

import oracle.sql.TIMESTAMP;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: zhanqiuxiang
 */
public class TbPostInfoPO implements IBaseDBVO {

	private java.lang.String id;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "发帖人id", showLength = 36)
	private java.lang.String postUserId;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "分享标识", showLength = 36)
	private java.lang.String shareId;

	@Validation(must = false, length = 500, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "分享内容", showLength = 500)
	private java.lang.String context;
	@Validation(must = false, length = 500, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "标题", showLength = 36)
	private java.lang.String title;

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 4, showName = "查看数", showLength = 22)
	private java.lang.Long viewNum;

	@DictDesc(typeName = "leaderStatus", refField = "status")
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "状态", showLength = 2)
	private java.lang.String status;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "创建时间", showLength = 7)
	private java.util.Date createTime;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setPostUserId(java.lang.String postUserId) {
		this.postUserId = postUserId;
	}

	public java.lang.String getPostUserId() {
		return this.postUserId;
	}

	public void setShareId(java.lang.String shareId) {
		this.shareId = shareId;
	}

	public java.lang.String getShareId() {
		return this.shareId;
	}

	public void setContext(java.lang.String context) {
		this.context = context;
	}

	public java.lang.String getContext() {
		return this.context;
	}

	public void setViewNum(java.lang.Long viewNum) {
		this.viewNum = viewNum;
	}

	public java.lang.Long getViewNum() {
		return this.viewNum;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getStatus() {
		return this.status;
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
		return "TB_POST_INFO";
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
