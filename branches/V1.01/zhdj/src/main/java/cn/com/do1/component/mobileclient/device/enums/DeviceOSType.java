package cn.com.do1.component.mobileclient.device.enums;


/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public enum DeviceOSType {
	
	Android(1,"Android"),
	iOS(2,"iOS");
	
	private Integer code;
	private String desc;
	private DeviceOSType(Integer code,String desc){
		this.code=code;
		this.desc=desc;
	}

	public Integer getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static DeviceOSType convert(Integer code) {
		for (DeviceOSType enm : values()) {
			if (enm.code==code)
				return enm;
		}
		return null;
	}
}
