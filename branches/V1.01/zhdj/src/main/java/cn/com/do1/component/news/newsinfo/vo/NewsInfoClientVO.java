package cn.com.do1.component.news.newsinfo.vo;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsInfoClientVO {
    private java.lang.String newsInfoId ;//新闻id
    private java.lang.String title ;//标题
    private java.lang.String newsInfoType;//新闻类型
    private java.lang.String imgPath;//图片地址
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
    private java.lang.String pushTime;//发布时间
	public java.lang.String getNewsInfoId() {
		return newsInfoId;
	}
	public void setNewsInfoId(java.lang.String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	
	public java.lang.String getNewsInfoType() {
		return newsInfoType;
	}
	public void setNewsInfoType(java.lang.String newsInfoType) {
		this.newsInfoType = newsInfoType;
	}
	public java.lang.String getImgPath() {
		return imgPath;
	}
	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}
	public java.lang.String getPushTime() {
		return pushTime;
	}
	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = pushTime;
	}
}
