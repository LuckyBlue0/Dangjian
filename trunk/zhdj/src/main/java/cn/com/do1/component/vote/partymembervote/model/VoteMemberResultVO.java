package cn.com.do1.component.vote.partymembervote.model;


/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class VoteMemberResultVO  {
   private String userName;
   private String result1;//统计会员投票数
   private String result2;//统计支持率
   private String userImgPath;//头像

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}


public void setResult1(String result1) {
	this.result1 = result1;
}
public String getResult1() {
	return result1;
}
public void setResult2(String result2) {
	this.result2 = result2;
}
public String getResult2() {
	return result2;
}
public void setUserImgPath(String userImgPath) {
	this.userImgPath = userImgPath;
}
public String getUserImgPath() {
	return userImgPath;
}
   
  
}
