package cn.com.do1.component.util;

import java.io.Serializable;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class BaseEncryptionVO implements Serializable{
	/**
	 * 
	 */
	private static long serialVersionUID = -6177914673370655853L;
	/**
	 * 摘要
	 */
	protected String digest;
	protected String id;
	protected String pageIndex;
	protected String pageSize;
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
