package com.do1.aqzhdj.activity.study.xietong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;

public class XietongLoginActivity extends BaseActivity {
	Context context;
	String username,pwd;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_xietong_login);
		context = this;
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.xietong), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.login);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.login:
			yanzheng();
			// intent = new Intent(this, XietongActivity.class);
			// startActivity(intent);
			break;
		}
	}

	public void yanzheng() {
		username = aq.find(R.id.userName).getText().toString();
		pwd = aq.find(R.id.pwd).getText().toString();
		if (!"".equals(username)) {
			if (!"".equals(pwd)) {
				login(username, pwd);
			} else {
				Toast.makeText(this, "密码不能为空", 1).show();
			}
		} else {
			Toast.makeText(this, "用户名不能为空", 1).show();
		}
	}

	public void login(String username, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("iswap", "1");
		map.put("cmd", "request");
		map.put("userName", username);
		map.put("userPassword", pwd);
		String url = "http://219.136.91.63:8200/anqingOA/login.mobo";
		System.out.println("url: " + url);
		 doRequest(1, url, map);
//		login(map, url);
	}

	// 登录方法 向httpclient 发送请求
	public boolean login(Map<String, String> params, String url) {
		String path = url; // url 拼接servlet
		// Map<String, String> params = new HashMap<String, String>(); // 向map
		// 中添加数据

		try {
			// return sendHttpClientPOSTRequest(path, params, "UTF-8");
			return sendHttpClientPOSTRequest(path, params, "utf-8"); // 调用发送登录的请求方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 通过HttpClient发送Post请求
	 * 
	 * @param path
	 *            请求路径
	 * @param params
	 *            请求参数
	 * @param encoding
	 *            编码
	 * @return 请求是否成功
	 */
	private boolean sendHttpClientPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();// 存放请求参数

		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {// 循环map中的数据
				pairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, encoding);
		HttpPost httpPost = new HttpPost(path);
		httpPost.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		HttpGet httpget = new HttpGet("http://www.hlovey.com/");
		int code = response.getStatusLine().getStatusCode();
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity1 = response.getEntity();
			List<Cookie> cookie = client.getCookieStore().getCookies();
			System.out.println("size: " + cookie.size());
			Constants.cookie = cookie.get(0);
			Intent intent = new Intent(context, XietongActivity.class);
			startActivity(intent);
			// System.out.println("cookie: " + cookie.get(0));
			// Cookie c=cookie.get(0);
			// String str1=entity1.get
			return true;
		}
		return false;
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
//		Toast.makeText(this, "成功了", 1).show();
		Constants.xietongname=username;
		Constants.xietongpwd=pwd;
		Intent intent = new Intent(this, XietongActivity.class);
		startActivity(intent);
	}
}
