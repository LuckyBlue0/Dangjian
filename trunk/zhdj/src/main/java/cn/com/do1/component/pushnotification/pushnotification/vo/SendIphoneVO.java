package cn.com.do1.component.pushnotification.pushnotification.vo;

public class SendIphoneVO {
	private String deviceToken;
	private String content;
	private String type;
	private String consumeId;
	
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}
	
	public String getConsumeId() {
		return consumeId;
	};
	
}
