package cn.com.do1.component.photowall.photowall.vo;

import cn.com.do1.common.annotation.bean.FormatMask;


/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class PhotoWallReviewListResponse{
	private String userName;
	@FormatMask(type="date",value="yyyy-MM-dd HH:mm:ss")
	private String reviewTime;
	private String content;
	private String imgUrl;
	private String portraitPic;
	
	
	public String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
