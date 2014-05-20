package cn.com.do1.component.partywork.activitytips.vo;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.po.LargeObject;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class ActivityTipsVO {

	private String userName;
	private String organizationName;
	@LargeObject
	private String content;
	private String source;
	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
	private String createTime;
	@DictDesc(refField = "source", typeName = "activityTipsSource")
	private String sourceDesc;
	private String portraitPic;
	
	public String getPortraitPic() {
		return portraitPic;
	}
	public void setPortraitPic(String portraitPic) {
		this.portraitPic = portraitPic;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceDesc() {
		if(sourceDesc != null){
			if(source.equals("1") || source.equals("2")){
				sourceDesc = "来自"+	sourceDesc + "手机客户端";
			}else{
				sourceDesc = "来自"+	sourceDesc + "客户端";
			}
		}
		return sourceDesc;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	
	
}
