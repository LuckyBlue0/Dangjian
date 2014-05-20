package com.do1.zhdj.activity.mine;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.StringUtil;

public class ChangePasswordActivity extends BaseActivity{
	
	private String oldPassword;
	private String newPassword;
	private String repeatPassword;
	
	private AQuery aq = new AQuery(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);
		
		init();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "修改密码", R.drawable.btn_head_2,"保存", this, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			oldPassword = aq.id(R.id.oldPwd).getText().toString();
			newPassword = aq.id(R.id.newPwd).getText().toString();
			repeatPassword = aq.id(R.id.rePwd).getText().toString();
			
			if(null == oldPassword || "".equals(oldPassword)){
				ToastUtil.showShortMsg(this, "原密码不能为空...");
				return;
			}
			newPassword = StringChange(newPassword);
			if(null == newPassword || "".equals(newPassword)){
				ToastUtil.showShortMsg(this, "新密码不能为空...");
				return;
			}
			int len = newPassword.length();
			if(len > 16 || len < 6){
				ToastUtil.showShortMsg(this, "新密码长度不正确...");
				return;
			}
			repeatPassword = StringChange(repeatPassword);
			if(!newPassword.equals(repeatPassword)){
				ToastUtil.showShortMsg(this, "两次输入的新密码不相同...");
				return;
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
//			String userid=SecurityUtil.encryptToDES(Constants.MICHI, constants.userInfo.getUserId());
//			String newpwd=SecurityUtil.encryptToDES(Constants.MICHI, newPassword);
//			String oldpwd=SecurityUtil.encryptToDES(Constants.MICHI, oldPassword);
//			String kes=constants.userInfo.getUserId()+newPassword+oldPassword;
//			String digest=SecurityUtil.encryptToMD5(kes.toLowerCase());
			
			map.put("userId", constants.userInfo.getUserId());
			map.put("newPwd", newPassword);
			map.put("oldPwd", oldPassword);
//			map.put("digest", digest);
//			Map<String, String> map2 = new HashMap<String, String>();
//			map2.put("requestJson", toJsonString(map));
//			System.out.println("map2: " + map2);
			String url = Constants.SERVER_RUL2 + getResources().getString(R.string.change_password);
			request(0,url,StringUtil.jiami(map));
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		ToastUtil.showShortMsg(this, "修改成功");
		constants.userInfo.setPassword(newPassword);
		Constants.sharedProxy.putString("password", newPassword);
		Constants.sharedProxy.commit();
		finish();
	}
	
	private void request(int code,String url,Map<String, String> map){
		try {
//			String param = Entryption.encode(toJsonString(map));
//			doRequestPostString(code, url,param );
			doRequest(code, url, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toJsonString(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
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

}
