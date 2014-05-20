package com.do1.aqzhdj.activity.study.xietong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.do1.aqzhdj.util.Constants;

public class UserService {

	// 登录方法 向httpclient 发送请求
	public static boolean login(Map<String, String> params, String url) {
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
	private static boolean sendHttpClientPOSTRequest(String path,
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
			// Intent intent = new Intent(this, XietongActivity.class);
			// startActivity(intent);
			// System.out.println("cookie: " + cookie.get(0));
			// Cookie c=cookie.get(0);
			// String str1=entity1.get
			return true;
		}
		return false;
	}

}
