package cn.com.do1.component.score.personalscore.vo;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PersonalScoreVO {
	private java.lang.String id;
	private java.lang.String userId;
	private java.lang.String name;
	private java.lang.String accumulativeScore;
	private java.lang.String ranking;
	private java.lang.String organizationId;
	private java.lang.String organizationName;
	private java.lang.String branchRanking;
	@FormatMask(type = "date", value = "yyyy-MM-dd")
	private java.lang.String createTime;
	private java.lang.String leave;
	private java.lang.String leaveDesc;
	private java.lang.String yesterdayGetScore;
	private java.lang.String status;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getOrganizationName() {
		return organizationName;
	}

	public java.lang.String getLeaveDesc() {
		return leaveDesc;
	}

	public void setLeaveDesc(java.lang.String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getLeave() {
		return leave;
	}

	public void setLeave(java.lang.String leave) {
		this.leave = leave;
	}

	public java.lang.String getYesterdayGetScore() {
		return yesterdayGetScore;
	}

	public void setYesterdayGetScore(java.lang.String yesterdayGetScore) {
		this.yesterdayGetScore = yesterdayGetScore;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setOrganizationId(java.lang.String oraganizationId) {
		this.organizationId = oraganizationId;
	}

	public java.lang.String getOrganizationId() {
		return this.organizationId;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getAccumulativeScore() {
		return accumulativeScore;
	}

	public void setAccumulativeScore(java.lang.String accumulativeScore) {
		this.accumulativeScore = accumulativeScore;
	}

	public java.lang.String getRanking() {
		return ranking;
	}

	public void setRanking(java.lang.String ranking) {
		this.ranking = ranking;
	}

	public java.lang.String getBranchRanking() {
		return branchRanking;
	}

	public void setBranchRanking(java.lang.String branchRanking) {
		this.branchRanking = branchRanking;
	}

}
