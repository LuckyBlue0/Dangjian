package cn.com.do1.component.mobileclient.installrecord.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.component.util.BaseEncryptionVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class InstallRecordRequest extends BaseEncryptionVO{
	@FormatMask(type = "number")
	private String type;
	private String equipmentNum; 
	private String versionNum;
	private String appVersionNum;

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getAppVersionNum() {
		return appVersionNum;
	}
	public void setAppVersionNum(String appVersionNum) {
		this.appVersionNum = appVersionNum;
	}
}
