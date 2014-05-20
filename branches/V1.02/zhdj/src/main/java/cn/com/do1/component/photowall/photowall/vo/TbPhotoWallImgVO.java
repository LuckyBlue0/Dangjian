package cn.com.do1.component.photowall.photowall.vo;

import java.util.Arrays;
import java.util.List;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class TbPhotoWallImgVO implements IBaseVO {

	private java.lang.String id;

	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 1, showName = "留影墙Id", showLength = 36)
	private java.lang.String photoWallId;

	@Validation(must = false, length = 2, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 2, showName = "留影墙封面图片", showLength = 255)
	private java.lang.String imgUrl;

	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showType = "input", showOrder = 3, showName = "排序值", showLength = 2000)
	private java.lang.String sort;

	private String thumImgUrl;
	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getPhotoWallId() {
		return photoWallId;
	}


	public void setPhotoWallId(java.lang.String photoWallId) {
		this.photoWallId = photoWallId;
	}


	public java.lang.String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(java.lang.String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public java.lang.String getSort() {
		return sort;
	}


	public void setSort(java.lang.String sort) {
		this.sort = sort;
	}


	public java.lang.String getId() {
		return id;
	}


	public String getThumImgUrl() {
		if (thumImgUrl == null) {
			if (!AssertUtil.isEmpty(imgUrl)){
				thumImgUrl = ImageUtil.setImgPath(imgUrl, ConfigMgr.get("news", "middle", "_middle"));
			}
		}
		return thumImgUrl;
	}


	public void setThumImgUrl(String thumImgUrl) {
		this.thumImgUrl = thumImgUrl;
	}


	private List<String> tableNames;
	
	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public List<String> _getTableNames() {
		if (tableNames == null || tableNames.isEmpty()) {
			tableNames = Arrays.asList("TB_PHOTO_WALL_IMG".split(","));
		}
		return tableNames;
	}
}
