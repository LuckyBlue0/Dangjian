package cn.com.do1.component.news.newsinfo.vo;

import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsListInfoRequest extends BaseEncryptionVO{

	private String parentType;
	private String newsInfoType;
	private String newsInfoId;
	
	private String newsInfoTypeId;
	
	public String getNewsInfoId() {
		return newsInfoId;
	}
	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getNewsInfoType() {
		return newsInfoType;
	}
	public void setNewsInfoType(String newsInfoType) {
		this.newsInfoType = newsInfoType;
	}
	public String getNewsInfoTypeId() {
		return newsInfoTypeId;
	}
	public void setNewsInfoTypeId(String newsInfoTypeId) {
		this.newsInfoTypeId = newsInfoTypeId;
	}
	
}
