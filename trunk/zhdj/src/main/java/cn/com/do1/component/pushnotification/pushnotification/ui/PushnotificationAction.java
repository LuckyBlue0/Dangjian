/*
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 */

package cn.com.do1.component.pushnotification.pushnotification.ui;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.web.IpUtil;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.pushnotification.pushnotification.util.PushNotificationUtil;
import cn.com.do1.component.pushnotification.pushnotification.vo.InfoVo;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * <p>Title: 功能/模块</p>
 * <p>Description: 类的描述</p>
 * @author lilei
 * @version 1.0
 * 修订历史：
 * 日期          作者        参考         描述
 */
public class PushnotificationAction extends BaseAction {
	
	private IDeviceService deviceService;
    private final static transient Logger logger = LoggerFactory.getLogger(PushnotificationAction .class);
	
    private String id;
    private String type;
    private String title;
    private String publishTime;
    private String source;
    private String url;

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

	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/**
	 * 消息推送接口
	 * @throws Exception
	 * @throws BaseException
	 */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "推送成功", faileMsg = "推送失败"))
	public void push() throws Exception,BaseException{
		//ip鉴权
    	boolean needIpAuth=Boolean.valueOf(ConfigMgr.get("pushnotification", "need_ip_auth"));
    	String id=ServletActionContext.getRequest().getParameter("id");
    	if(needIpAuth){
    		String ip=IpUtil.getRequesterIp(ServletActionContext.getRequest(), ConfigMgr.getInstance());
    		String permitIp=ConfigMgr.get("pushnotification", "permit_ip");
    		if(!permitIp.contains(ip)){
    			logger.info("ip鉴权不通过!请求的ip为:"+ip);
    			setActionResult("1002", "ip鉴权不通过!");
    			return;
    		}
    	}
    	//参数检查
    	if(AssertUtil.isEmpty(id)){
    		logger.info("请求参数格式不正确,id为空");
			setActionResult("1003", "请求参数格式不正确,id为空");
			return;
    	}
    	if(AssertUtil.isEmpty(type)){
    		logger.info("请求参数格式不正确,type为空");
			setActionResult("1003", "请求参数格式不正确,type为空");
			return;
    	}
    	if(AssertUtil.isEmpty(title)){
    		logger.info("请求参数格式不正确,title为空");
			setActionResult("1003", "请求参数格式不正确,title为空");
			return;
    	}
    	final InfoVo vo=new InfoVo();
    	vo.setId(id);
    	vo.setTitle(title);
    	vo.setType(type);
    	vo.setPublishTime(publishTime);
    	vo.setSource(source);
    	vo.setUrl(url);
    	
    	//iOS推送
    	final List<String> devices=deviceService.getDeviceForiOS();
    	new Thread(new Runnable() {
			@Override
			public void run() {
		    	PushNotificationUtil.push4Iphone(vo,devices);
			}
		}).start();
    	
    	//android推送
    	new Thread(new Runnable() {
			@Override
			public void run() {
		    	PushNotificationUtil.push4Android(vo);
			}
		}).start();
    	
	}
	

}
