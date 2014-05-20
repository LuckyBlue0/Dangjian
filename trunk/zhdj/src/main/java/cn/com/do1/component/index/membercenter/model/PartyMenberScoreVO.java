package cn.com.do1.component.index.membercenter.model;


/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartyMenberScoreVO {
	private String username;//用户姓名
	private String lastdayScore;//昨天积分
	private String tatalScore;//总积分
	private String sort;//排名
	private String imgPath;//用户头像
	public String getLastdayScore() {
		return lastdayScore;
	}
	public void setLastdayScore(String lastdayScore) {
		this.lastdayScore = lastdayScore;
	}
	public String getTatalScore() {
		return tatalScore;
	}
	public void setTatalScore(String tatalScore) {
		this.tatalScore = tatalScore;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	
}
