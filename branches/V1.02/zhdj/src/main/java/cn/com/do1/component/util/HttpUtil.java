package cn.com.do1.component.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import cn.com.do1.common.util.AssertUtil;

public class HttpUtil {

	/**
	 * 日志对象
	 */
	private static Logger log = Logger.getLogger(HttpUtil.class);
	
	public static String getPost(String url, NameValuePair[] data){
		HttpClient client = new HttpClient();
		int reqTimeout = 30000;
		int readTimeout = 60000;
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(reqTimeout);
		client.getHttpConnectionManager().getParams().setSoTimeout(readTimeout);
		PostMethod post = new PostMethod(url);
		if (!(AssertUtil.isEmpty(data))){
			post.setRequestBody(data);
		}
		String resutl = "";
		try {
			log.info("post开始"+new Date());
			int responseStatus = client.executeMethod(post);
			if( responseStatus==200 ){
				resutl = post.getResponseBodyAsString();
			}else{
				return null;
			}
			
			log.info("post结束"+new Date());
		}catch (Exception e) {
			log.error("post异常"+e);
		}finally{
			post.releaseConnection();
		}
		return resutl;
	}
	
	public static String get(String uri) throws IOException, Exception {
		GetMethod get = null;
		try {
			HttpClient httpClient = new HttpClient();
			int reqTimeout = 120000;
			int readTimeout = 120000;
			httpClient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(reqTimeout);
			httpClient.getHttpConnectionManager().getParams()
					.setSoTimeout(readTimeout);
			get = new GetMethod(uri);
			int responseStatus = httpClient.executeMethod(get);
			if (responseStatus == 200) {
				return getString(get.getResponseBodyAsStream());
			}
		} catch (Exception e) {
			log.error("调用httpclient方法get["+uri+"]"+e);
		}finally{
			if(get != null){
				get.releaseConnection();
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	private static String getString(InputStream is) throws Exception {
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		// String s = new String(result.toString().getBytes(),"GBK");
		// s = s.replaceAll("\\?", "");
		// return s;
		return result.toString();
	}
}
