package cn.com.do1.component.photowall.photowall.vo;

import java.util.List;

import cn.com.do1.common.annotation.bean.FormatMask;


/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class PhotoWallDetailsResponse{
	private String id;
	private String title;
	private String author;
	@FormatMask(type="date",value="yyyy-MM-dd")
	private String pushTime;
	private String reviewCount;
//	private String imgUrl;
//	private String thumImgUrl;
	private List<TbPhotoWallImgVO> imgs;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	public List<TbPhotoWallImgVO> getImgs() {
		return imgs;
	}

	public void setImgs(List<TbPhotoWallImgVO> imgs) {
		this.imgs = imgs;
	}
}
