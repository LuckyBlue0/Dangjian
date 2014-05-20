package cn.com.do1.component.vote.partymembervote.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class VoteMemberTotalResultVO {
	private String id;
	private String voteTopic;
	private String totalCount;
	private String voteStatus;
	private String voteImgPath;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String startTime;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String endTime;
	private String remark;
	private java.lang.String resizeVoteImgPath;// 压缩后图片地址

	public java.lang.String getResizeVoteImgPath() {
		if (resizeVoteImgPath == null) {
			if (!AssertUtil.isEmpty(voteImgPath)){
				resizeVoteImgPath = ImageUtil.setImgPath(voteImgPath, ConfigMgr.get("news", "middle", "_middle"));
			}
		}
		return resizeVoteImgPath;
	}

	public void setResizeVoteImgPath(java.lang.String resizeVoteImgPath) {
		this.resizeVoteImgPath = resizeVoteImgPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setVoteTopic(String voteTopic) {
		this.voteTopic = voteTopic;
	}

	public String getVoteTopic() {
		return voteTopic;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteImgPath(String voteImgPath) {
		this.voteImgPath = voteImgPath;
	}

	public String getVoteImgPath() {
		return voteImgPath;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

}
