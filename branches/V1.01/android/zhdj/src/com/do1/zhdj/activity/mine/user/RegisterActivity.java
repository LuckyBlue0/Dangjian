package com.do1.zhdj.activity.mine.user;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ScrollView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.IndexActivity;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.info.UserInfo;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.Entryption;
import com.do1.zhdj.util.Listenertool;

public class RegisterActivity extends BaseActivity implements OnFocusChangeListener{
	
	private final static int USER_ONE = 0;
	private final static int USER_REG = 1;
	private final static int CODE = 2;
	
	private AQuery aq = new AQuery(this);
	private String userId;
	private String userName;
	private String userIDcard;
	private String userMobile;
	private String userCode;
	private String userPassword;
	private String userPwd;
	
	private boolean isAllowGegister = false;//是否允许注册
	
	private boolean isUserName = false;
	private boolean isName = false;
	private boolean isIDcard = false;
	private boolean isMobile = false;
	private boolean isCode = false;
	private boolean isPwd = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		init();
		
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_head_2,"登录", "注册", 0,"", null, null);
		ScrollView scroll = (ScrollView) findViewById(R.id.scroll);
		scroll.setVerticalScrollBarEnabled(true);  
		
		int[] OnFocusChangeListenerIds = {R.id.userId,R.id.userName,R.id.userIDcard,R.id.userMobile,R.id.userCode,R.id.userPassword,R.id.userPwd};
		bindFocusChangeListener(this, this, OnFocusChangeListenerIds);
		
		int[] onclickListenerIds = {R.id.register,R.id.code};
		Listenertool.bindOnCLickListener(this, this, onclickListenerIds);
		
	}
	
	private void bindFocusChangeListener(Activity activity,final OnFocusChangeListener clickListener,int... resourceIds){
		for (int resourceId:resourceIds) {
			View view = activity.findViewById(resourceId);
			view.setOnFocusChangeListener(clickListener);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch (v.getId()) {
		case R.id.userId:
			userId =  ((EditText)v).getText().toString();
			if(v.hasFocus() == false){
				if(null == userId || "".equals(userId)){
					aq.id(R.id.prompt).visible().text("用户名不能为空");
					isUserName = false;
					return;
				}
				if(!valid(userId)){
					aq.id(R.id.prompt).visible().text("用户名格式不正确");
					isUserName = false;
					return;
				}
				isUserName = true;
				aq.id(R.id.prompt).gone();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_name", userId);
				String url = Constants.SERVER_URL + getResources().getString(R.string.check_user_id);
				request(USER_ONE,url,map);
			}
			break;
			
		case R.id.userName:
			if(v.hasFocus() == false && isUserName){
				userName =  ((EditText)v).getText().toString();
				if(null == userName || "".equals(userName)){
					aq.id(R.id.prompt).visible().text("姓名不能为空");
					isName = false;
					return;
				}
				if(!validUserName(userName)){
					aq.id(R.id.prompt).visible().text("姓名输入格式不正确");
					isName = false;
					return;
				}
				isName = true;
				aq.id(R.id.prompt).gone();
			}
			break;
			
		case R.id.userIDcard:
			if(v.hasFocus() == false && isUserName && isName){
				userIDcard =  ((EditText)v).getText().toString();
				if(null == userIDcard || "".equals(userIDcard)){
					aq.id(R.id.prompt).visible().text("身份号码不能为空");
					isIDcard = false;
					return;
				}
				if(!validIDcard(userIDcard)){
					aq.id(R.id.prompt).visible().text("身份号码格式不正确");
					isIDcard = false;
					return;
				}
				isIDcard = true;
				aq.id(R.id.prompt).gone();
			}
			break;
			
		case R.id.userMobile:
			if(v.hasFocus() == false && isUserName && isName&& isIDcard){
				userMobile =  ((EditText)v).getText().toString();
				if(null == userMobile || "".equals(userMobile)){
					aq.id(R.id.prompt).visible().text("手机号码不能为空");
					isMobile = false;
					return;
				}
				if(!validMoble(userMobile.trim())){
					aq.id(R.id.prompt).visible().text("手机号码不正确");
					isMobile = false;
					return;
				}
				isMobile = true;
				aq.id(R.id.prompt).gone();
			}
			break;
			
		case R.id.userCode:
			if(v.hasFocus() == false&& isUserName && isName&& isIDcard&& isMobile){
				userCode =  ((EditText)v).getText().toString();
				if(null == userCode || "".equals(userCode)){
					aq.id(R.id.prompt).visible().text("验证码不能为空");
					isCode = false;
					return;
				}
				if(!validcode(userCode)){
					aq.id(R.id.prompt).visible().text("验证码格式不正确");
					isCode = false;
					return;
				}
				isCode = true;
				aq.id(R.id.prompt).gone();
			}
			break;
			
		case R.id.userPassword:
			if(v.hasFocus() == false&& isUserName && isName&& isIDcard&& isMobile&& isCode){
				userPassword =  ((EditText)v).getText().toString();
				userPassword = StringChange(userPassword);
				if(null == userPassword || "".equals(userPassword)){
					aq.id(R.id.prompt).visible().text("密码不能为空");
					isPwd = false;
					return;
				}
				int len = userPassword.length();
				if(len > 16 || len < 6 ){
					aq.id(R.id.prompt).visible().text("密码长度不正确");
					isPwd = false;
					return;
				}
				isPwd = true;
				aq.id(R.id.prompt).gone();
			}
			break;
			
		case R.id.userPwd:
			if(v.hasFocus() == false&& isUserName && isName&& isIDcard&& isMobile&& isCode&&isPwd){
				userPwd =  ((EditText)v).getText().toString();
				userPwd = StringChange(userPwd);
				if(null == userPwd || "".equals(userPwd)){
					aq.id(R.id.prompt).visible().text("再次密码不能为空");
					return;
				}
				if(!userPwd.equals(userPassword)){
					aq.id(R.id.prompt).visible().text("两次输入密码不相同");
					return;
				}
				aq.id(R.id.prompt).gone();
			}
			break;
			

		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.register:
			userId =  aq.id(R.id.userId).getEditText().getText().toString();
			userName =  aq.id(R.id.userName).getEditText().getText().toString();
			userIDcard =  aq.id(R.id.userIDcard).getEditText().getText().toString();
			userMobile =  aq.id(R.id.userMobile).getEditText().getText().toString();
			userCode =  aq.id(R.id.userCode).getEditText().getText().toString();
			userPassword =  aq.id(R.id.userPassword).getEditText().getText().toString();
			userPwd =  aq.id(R.id.userPwd).getEditText().getText().toString();
			
			if(isUserName && isName&& isIDcard&& isMobile&& isCode&&isPwd){
				userPwd = StringChange(userPwd);
				if(null == userPwd || "".equals(userPwd)){
					aq.id(R.id.prompt).visible().text("再次密码不能为空");
					return;
				}
				if(!userPwd.equals(userPassword)){
					aq.id(R.id.prompt).visible().text("两次输入密码不相同");
					return;
				}
				aq.id(R.id.prompt).gone();
			}
			
			if(null == userName || "".equals(userName)){
				ToastUtil.showShortMsg(RegisterActivity.this, "姓名不能为空...");
				return;
			}
			if(!validUserName(userName)){
				ToastUtil.showShortMsg(RegisterActivity.this, "姓名输入格式不正确...");
				return;
			}
			if(null == userIDcard || "".equals(userIDcard)){
				ToastUtil.showShortMsg(RegisterActivity.this, "身份号码不能为空...");
				return;
			}
			if(!validIDcard(userIDcard)){
				ToastUtil.showShortMsg(RegisterActivity.this, "身份号码格式不正确...");
				return;
			}
			if(null == userMobile || "".equals(userMobile)){
				ToastUtil.showShortMsg(RegisterActivity.this, "手机号码不能为空...");
				return;
			}
			if(!validMoble(userMobile)){
				ToastUtil.showShortMsg(RegisterActivity.this, "手机号码不正确...");
				return;
			}
			if(null == userCode || "".equals(userCode)){
				ToastUtil.showShortMsg(RegisterActivity.this, "验证码不能为空...");
				return;
			}
			if(null == userPassword || "".equals(userPassword)){
				ToastUtil.showShortMsg(RegisterActivity.this, "密码不能为空...");
				return;
			}
			int len = userPassword.length();
			if(len > 16 || len < 6 ){
				ToastUtil.showShortMsg(RegisterActivity.this, "密码长度不正确...");
				return;
			}
			if(null == userPwd || "".equals(userPwd)){
				ToastUtil.showShortMsg(RegisterActivity.this, "再次密码不能为空...");
				return;
			}
			if(!userPwd.equals(userPassword)){
				ToastUtil.showShortMsg(RegisterActivity.this, "两次输入密码不相同...");
				return;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_name", userId);
			map.put("pwd", userPassword);
			map.put("cname", userName);
			map.put("idcard", userIDcard);
			map.put("vcode", userCode);
			map.put("mobile", userMobile);
			String url = Constants.SERVER_URL + getResources().getString(R.string.check_register);
			request(USER_REG, url, map);
			break;
			
		case R.id.code:
			userMobile =  aq.id(R.id.userMobile).getEditText().getText().toString();
			if(null == userMobile || "".equals(userMobile)){
				ToastUtil.showShortMsg(RegisterActivity.this, "手机号码不能为空...");
				return;
			}
			
			if(!validMoble(userMobile)){
				ToastUtil.showShortMsg(RegisterActivity.this, "手机号码不正确...");
				return;
			}
			
			Map<String, Object> codeMap = new HashMap<String, Object>();
			codeMap.put("mobile", userMobile);
			String codeurl = Constants.SERVER_URL + "GetSmsCheckCode.aspx";
			request(CODE, codeurl, codeMap);
			break;

		default:
			break;
		}
	}
	
	private boolean valid(String _userId){
		Pattern p = Pattern 
                .compile("[0-9a-zA-Z]*"); 
        Matcher m = p.matcher(_userId); 
        return m.matches();
	}
	
	private boolean validcode(String code){
		Pattern p = Pattern 
                .compile("[0-9]*"); 
        Matcher m = p.matcher(code); 
        return m.matches();
	}
	
	/**
	 * 匹配身份证号码
	 * @param _IDcard
	 * @return
	 */
	private boolean validIDcard(String _IDcard){
		Pattern p = Pattern 
                .compile("((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))"); 
        Matcher m = p.matcher(_IDcard); 
        return m.matches();
	}
	
	/**
	 * 匹配中文字符串
	 * @param _userName
	 * @return
	 */
	private boolean validUserName(String _userName){
		Pattern p = Pattern 
                .compile("[\\u4e00-\\u9fa5]+"); 
        Matcher m = p.matcher(_userName); 
        return m.matches();
	}
	
	/*
	 * 验证手机号，11位，且必须含全是数字
	 */
	private boolean validMoble(String _mobile){
		if(_mobile.length() != 11){
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[7])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
        Matcher m = p.matcher(_mobile); 
        return m.matches();
	}
	
	public String StringChange(String s){
		if(s.equals("")||s==null)
			return "";
		StringBuilder sb=new StringBuilder();
		int len=s.length();
		char c;
		
		for (int i = 0; i < len; i++){
			c=s.charAt(i);
//			if(c>='a'&&c<='z'){
//				c=(char)(c-32);
//			}
			if(c>='A'&&c<='Z'){
				c=(char)(c+32);
			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	private void request(int code,String url,Map<String, Object> map){
		try {
			String param = Entryption.encode(toJsonString(map));
			doRequestPostString(code, url,param );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toJsonString(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		Map<String, Object> map = new HashMap<String, Object>();
		switch (requestId) {
		case USER_ONE:
			map = resultObject.getDataMap();
			int code = Integer.valueOf(map.get("is_only").toString());
			if(code == 1){
				ToastUtil.showShortMsg(this, "该用户名可以注册...");
				aq.id(R.id.prompt).gone();
			}else if(code == 0){
				aq.id(R.id.prompt).visible();
				ToastUtil.showShortMsg(this, "该用户名已经存在，请重新输入...");
			}
			
			break;
			
		case USER_REG:
			map = resultObject.getDataMap();
			constants.userInfo = UserInfo.getInstance();
			constants.userInfo.setUserId(map.get("user_id").toString());
			constants.userInfo.setUserName(userId);
			constants.userInfo.setName(userName);
			constants.userInfo.setPassword(userPassword);
			constants.userInfo.setIDcard(userIDcard);
			constants.userInfo.setMobile(userMobile);
			constants.userInfo.setUser_type("2");
			constants.userInfo.setLogin(true);
			setAutoLogin();
			Intent intent = new Intent(this, IndexActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			ToastUtil.showShortMsg(this, "注册成功...");
			finish();
			break;
			
		case CODE:
			if(resultObject.getCode() == 1){
				ToastUtil.showShortMsg(RegisterActivity.this,"获取验证码成功,请稍后留意接收短信信息");
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		switch (requestId) {
		case USER_ONE:
			ToastUtil.showShortMsg(this, "输入有误，请重新输入...");
			aq.id(R.id.prompt).visible();
			break;

		case USER_REG:
			ToastUtil.showShortMsg(this, "注册失败,请重新注册...");
			aq.id(R.id.prompt).visible().text("注册失败");
			break;
			
		case CODE:
			ToastUtil.showShortMsg(this, "获取失败");
			aq.id(R.id.prompt).visible().text("获取验证码失败");
			break;
		default:
			break;
		}
	}
	
	private void setAutoLogin(){
		Constants.sharedProxy.putString("userId", userId);
		Constants.sharedProxy.putString("password", userPassword);
		Constants.sharedProxy.putBoolean("isAuto", true);
		Constants.sharedProxy.commit();
	}

}
