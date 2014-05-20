package cn.com.do1.component.mobileclient.device.ui.client;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.mobileclient.device.enums.DeviceOSType;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.mobileclient.device.vo.DeviceRequest;
import cn.com.do1.component.util.CommonAuthManage;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class DeviceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(DeviceAction.class);
	private IDeviceService deviceService;
	private TbDevicePO po;

	public TbDevicePO getPo() {
		return po;
	}

	public void setPo(TbDevicePO po) {
		this.po = po;
	}

	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	private String requestJson;
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "注册成功", faileMsg = "注册失败"))
	public void regester() throws BaseException, Exception {
		
		logger.debug("regester requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
    	DeviceRequest request = (DeviceRequest)JSONObject.toBean(paramJson,DeviceRequest.class);
    	request = (DeviceRequest)CommonAuthManage.authDigestRetObj(request,new String []{"platformType","username","userPwd","deviceId"});
    	if(request == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		if (AssertUtil.isEmpty(request.getDeviceId())) {
			logger.error("deviceid为空");
			throw new BaseException("1002", "deviceId为空!");
		}
		if (AssertUtil.isEmpty(request.getOsType())) {
			logger.error("osType为空!");
			throw new BaseException("1002", "osType为空!");
		}
		if(request.getOsType().equals(DeviceOSType.iOS.getCode())){
			if(AssertUtil.isEmpty(request.getDeviceToken())){
				logger.error("deviceToken为空!");
				throw new BaseException("1002", "deviceToken为空!");
			}
		}
		if (AssertUtil.isEmpty(request.getOsVersion())) {
			logger.error("osVersion为空!");
			throw new BaseException("1002", "osVersion为空!");
		}
		if (AssertUtil.isEmpty(request.getDeviceModel())) {
			logger.error("deviceModel为空!");
			throw new BaseException("1002", "deviceModel为空!");
		}
		TbDevicePO oldPo = deviceService.findByDeviceId(request.getDeviceId());
		if(po == null){
			po = new TbDevicePO();
//			BeanHelper.copyProperties(po, request);
		}
		po.setDeviceId(request.getDeviceId());
		po.setDeviceModel(request.getDeviceModel());
		po.setDeviceToken(request.getDeviceToken());
		po.setOsType(Integer.parseInt(request.getOsType()));
		po.setOsVersion(request.getOsVersion());
		if (oldPo == null) {//添加设备
			po.setId(UUID.randomUUID().toString());
			po.setCreateTime(new Date());
			po.setUpdateTime(po.getCreateTime());
			deviceService.insertPO(po, false);
		} else {//更新设备
			po.setId(oldPo.getId());
			po.setUpdateTime(new Date());
			deviceService.updatePO(po, false);
		}
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	
	

}
