package cn.com.do1.component.photowall.photowall.vo;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;


/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: tanshaoqi
 */
public class PhotoWallListResponse{
	private String id;
	private String imgUrl;
	private String thumImgUrl;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
}
