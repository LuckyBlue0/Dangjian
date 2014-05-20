/*
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 */

package cn.com.do1.component.pushnotification.pushnotification.vo;

/**
 * <p>Title: 功能/模块</p>
 * <p>Description: 类的描述</p>
 * @author lilei
 * @version 1.0
 * 修订历史：
 * 日期          作者        参考         描述
 */
public class InfoVo {
	
	private String id;
    private String type;
    private String title;
    private String publishTime;
    private String source;
    private String url;
    
    private String typeDesc;
    
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
    

}
