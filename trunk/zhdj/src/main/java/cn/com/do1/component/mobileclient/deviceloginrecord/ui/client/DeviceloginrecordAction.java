package cn.com.do1.component.mobileclient.deviceloginrecord.ui.client;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.deviceloginrecord.service.IDeviceloginrecordService;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: lilei
 */
public class DeviceloginrecordAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(DeviceloginrecordAction.class);
	private IDeviceloginrecordService deviceloginrecordService;
	private TbDeviceLoginRecordPO po;

	@Resource
	public void setDeviceloginrecordService(
			IDeviceloginrecordService deviceloginrecordService) {
		this.deviceloginrecordService = deviceloginrecordService;
	}

	public TbDeviceLoginRecordPO getPo() {
		return po;
	}

	public void setPo(TbDeviceLoginRecordPO po) {
		this.po = po;
	}

	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "添加成功", faileMsg = "添加失败"))
	public void add() throws BaseException, Exception {
		if (po == null) {
			logger.error("po为空!");
			throw new BaseException("1002", "参数为空!");
		}
		if (AssertUtil.isEmpty(po.getUserId())) {
			logger.error("userId为空");
			throw new BaseException("1002", "po.userId为空!");
		}
		if (AssertUtil.isEmpty(po.getDeviceId())) {
			logger.error("deviceid为空");
			throw new BaseException("1002", "po.deviceId为空!");
		}
		if (AssertUtil.isEmpty(po.getOsType())) {
			logger.error("osType为空!");
			throw new BaseException("1002", "po.osType为空!");
		}
		if (AssertUtil.isEmpty(po.getOsVersion())) {
			logger.error("osVersion为空!");
			throw new BaseException("1002", "po.osVersion为空!");
		}
		if (AssertUtil.isEmpty(po.getDeviceModel())) {
			logger.error("deviceModel为空!");
			throw new BaseException("1002", "po.deviceModel为空!");
		}
		po.setId(UUID.randomUUID().toString());
		po.setLoginTime(new Date());
		deviceloginrecordService.insertPO(po, false);
	}

}
