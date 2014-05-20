package cn.com.do1.component.news.newsinfo.vo;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsListInfoClientVO {
    private java.lang.String newsInfoId ;//新闻id
    private java.lang.String title ;//标题
    private java.lang.String newsInfoType;//新闻类型
    private java.lang.String imgPath;//图片地址
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
    private java.lang.String pushTime;//发布时间
    private String organizationName;		//组织名称
    private String digest;				//新闻摘要
    private String bodyDigest;			//摘要
    private java.lang.String buyTop;
    private java.lang.String resizeImgPath;// 压缩后图片地址
	public String getOrganizationName() {
		if(organizationName == null){
			organizationName = ConfigMgr.get("system", "defaultOrgName","管理平台");
		}
		return organizationName;
	}
	
	public java.lang.String getResizeImgPath() {
		if (resizeImgPath == null) {
			if (!AssertUtil.isEmpty(imgPath)){
				resizeImgPath = ImageUtil.setImgPath(imgPath, ConfigMgr.get("news", "middle", "_middle"));
			}
		}
		return resizeImgPath;
	}

	public void setResizeImgPath(java.lang.String resizeImgPath) {
		this.resizeImgPath = resizeImgPath;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getDigest() {
		if(digest == null){
			digest = bodyDigest;
		}
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
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


	public String getBodyDigest() {
		return bodyDigest;
	}
	public void setBodyDigest(String bodyDigest) {
		this.bodyDigest = bodyDigest;
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
	public java.lang.String getBuyTop() {
		return buyTop;
	}
	public void setBuyTop(java.lang.String buyTop) {
		this.buyTop = buyTop;
	}
}
