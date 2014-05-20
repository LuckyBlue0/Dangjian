package cn.com.do1.component.pushnotification.pushnotification.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

/**
 * 推送服务for Android 所需要的参数
 ***/
public class SendAndroidVO {
	
	private String broadcast;
	private String username;
	private String title;
	private String message;
	private String uri;
	private String consumeId;

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}
	
	public String getConsumeId() {
		return consumeId;
	}
	
	public List<BasicNameValuePair> getParams(){
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		BasicNameValuePair b1 = new BasicNameValuePair("broadcast", broadcast); //broadcast--->N
		BasicNameValuePair b2 = new BasicNameValuePair("username", username); // mmie
		BasicNameValuePair b3 = new BasicNameValuePair("title", title);
		BasicNameValuePair b4 = new BasicNameValuePair("message", message);
		BasicNameValuePair b5 = new BasicNameValuePair("uri", "type:1;id:" + consumeId);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		list.add(b5);
		return list;
	}

}
