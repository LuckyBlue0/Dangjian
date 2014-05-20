package cn.com.do1.component.share.sharemanager.vo;

import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class ShareRequest extends BaseEncryptionVO{

	private String shareId;
	private String keyword;
	
	
	
	private String postId;
	private String contxt;
	private String userId;
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getContxt() {
		return contxt;
	}
	public void setContxt(String contxt) {
		this.contxt = contxt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
