package cn.com.do1.component.news.newsinfo.model;

import java.util.List;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ShortNewsInfoVO {
	private java.lang.String newsInfoId;// 新闻id
	private java.lang.String title;// 标题
	private java.lang.String type;// 新闻类型
	private java.lang.String typeId;// 新闻类型ID
	private java.lang.String imgPath;// 图片地址
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String pushTime;// 发布时间

	private String bodyDigest;
	private java.lang.String resizeImgPath;// 压缩后图片地址
	
	
	private List<AqNewsListTabVO> newsListTabs; 

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

	public String getBodyDigest() {
		return bodyDigest;
	}

	public void setBodyDigest(String bodyDigest) {
		this.bodyDigest = bodyDigest;
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

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getImgPath() {
		return imgPath;
	}

	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}

	public void setTypeId(java.lang.String typeId) {
		this.typeId = typeId;
	}

	public java.lang.String getTypeId() {
		return typeId;
	}

	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = pushTime;
	}

	public java.lang.String getPushTime() {
		return pushTime;
	}

	public List<AqNewsListTabVO> getNewsListTabs() {
		return newsListTabs;
	}

	public void setNewsListTabs(List<AqNewsListTabVO> newsListTabs) {
		this.newsListTabs = newsListTabs;
	}
}
