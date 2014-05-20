package cn.com.do1.component.mobileclient.version.vo;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class VersionVO {
	private java.lang.String versionName;
	private java.lang.String versionNum;
	private java.lang.String fileSize;
	private java.lang.String filePath;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private java.lang.String pushTime;
	private java.lang.String remark;
	public java.lang.String getVersionName() {
		return versionName;
	}
	public void setVersionName(java.lang.String versionName) {
		this.versionName = versionName;
	}
	public java.lang.String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(java.lang.String versionNum) {
		this.versionNum = versionNum;
	}
	public java.lang.String getFileSize() {
		return fileSize;
	}
	public void setFileSize(java.lang.String fileSize) {
		this.fileSize = fileSize;
	}
	public java.lang.String getFilePath() {
		return filePath;
	}
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}
	public java.lang.String getPushTime() {
		return pushTime;
	}
	public void setPushTime(java.lang.String pushTime) {
		this.pushTime = pushTime;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
