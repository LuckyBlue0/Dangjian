package cn.com.do1.component.share.sharemanager.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class TbReplyPO implements IBaseDBVO {

	private java.lang.String id;

	public java.lang.String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(java.lang.String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public java.lang.String getPostId() {
		return postId;
	}

	public void setPostId(java.lang.String postId) {
		this.postId = postId;
	}

	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "回帖人id", showLength = 36)
	private java.lang.String replyUserId;

	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "发帖人id", showLength = 36)
	private java.lang.String postId;

	@DictDesc(typeName = "leaderStatus", refField = "status")
	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "select", showOrder = 4, showName = "状态", showLength = 2)
	private java.lang.String status;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 5, showName = "帖子内容", showLength = 500)
	private java.lang.String context;

	@Validation(must = false, length = 7, fieldType = "datetime")
	@PageView(showType = "datetime", showOrder = 6, showName = "创建时间", showLength = 7)
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	private Long onNum;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public Long getOnNum() {
		return onNum;
	}

	public void setOnNum(Long onNum) {
		this.onNum = onNum;
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
		return "TB_REPLY_INFO";
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

	public java.lang.String getContext() {
		return context;
	}

	public void setContext(java.lang.String context) {
		this.context = context;
	}
}
