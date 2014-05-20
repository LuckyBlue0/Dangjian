package com.do1.aqzhdj.info;



public class LoginInfo {
	
	private static LoginInfo loginInfo = null;
	private boolean isLogin = false;
	
	public static  LoginInfo getInstance() {
	    if (loginInfo == null) 	
	    	loginInfo = new LoginInfo();
	    return loginInfo;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
