package cn.com.do1.component.vote.orgvote.model;

import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class AddVoteRequest extends BaseEncryptionVO{

	private String [] userIds;
	private String voteNum;
	private String reason;
	private String userId;
	private String status;
	private String voteUserId;
	
	private String voteId;
	
	
	public String getVoteId() {
		return voteId;
	}
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVoteUserId() {
		return voteUserId;
	}
	public void setVoteUserId(String voteUserId) {
		this.voteUserId = voteUserId;
	}

	public String[] getUserIds() {
		return userIds;
	}
	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
