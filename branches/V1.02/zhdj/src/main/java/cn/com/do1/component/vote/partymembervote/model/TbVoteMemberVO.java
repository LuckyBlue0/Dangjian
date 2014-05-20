package cn.com.do1.component.vote.partymembervote.model;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbVoteMemberVO {
	private java.lang.String voteId;
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String userImgPath;
	private java.lang.String advancedDeeds;
	private java.lang.String partyWork;
	private java.lang.String isVote;		//是否可投票
	private String remark;
	
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.lang.String getVoteId() {
		return voteId;
	}

	public void setVoteId(java.lang.String voteId) {
		this.voteId = voteId;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserImgPath() {
		return userImgPath;
	}

	public void setUserImgPath(java.lang.String userImgPath) {
		this.userImgPath = userImgPath;
	}

	public java.lang.String getAdvancedDeeds() {
		return advancedDeeds;
	}

	public void setAdvancedDeeds(java.lang.String advancedDeeds) {
		this.advancedDeeds = advancedDeeds;
	}

	public java.lang.String getPartyWork() {
		return partyWork;
	}

	public void setPartyWork(java.lang.String partyWork) {
		this.partyWork = partyWork;
	}

	public java.lang.String getIsVote() {
		return isVote;
	}

	public void setIsVote(java.lang.String isVote) {
		this.isVote = isVote;
	}
}
