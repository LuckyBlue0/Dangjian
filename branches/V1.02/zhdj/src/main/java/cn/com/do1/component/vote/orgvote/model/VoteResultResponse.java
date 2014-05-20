package cn.com.do1.component.vote.orgvote.model;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class VoteResultResponse {
	private String voteResult;
	private String reason;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private String voteTime;

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime;
	}
}
