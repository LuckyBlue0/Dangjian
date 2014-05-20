package cn.com.do1.component.mobileclient.installrecord.client;

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
import cn.com.do1.component.mobileclient.installrecord.model.InstallRecordRequest;
import cn.com.do1.component.mobileclient.installrecord.model.TbInstallRecordPO;
import cn.com.do1.component.mobileclient.installrecord.service.IInstallRecordService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
public class InstallRecordInterfaceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(InstallRecordInterfaceAction.class);
	private String requestJson;
	@Resource
	private IInstallRecordService installRecordService;

	/**
	 * 设备安装记录
	 * 
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "设备安装记录成功", faileMsg = "设备安装记录失败"))
	public void installRecord() throws BaseException, Exception {
		logger.debug("installRecord requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		InstallRecordRequest request = (InstallRecordRequest) JSONObject.toBean(paramJson, InstallRecordRequest.class);
		request = (InstallRecordRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type", "equipmentNum", "versionNum", "appVersionNum" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getType()))
			throw new BaseException("9002", "未传入系统类型");
		if (AssertUtil.isEmpty(request.getEquipmentNum()))
			throw new BaseException("9002", "未传入设备ID");
		if (AssertUtil.isEmpty(request.getVersionNum()))
			throw new BaseException("9002", "未传入系统版本号");
		if (AssertUtil.isEmpty(request.getAppVersionNum()))
			throw new BaseException("9002", "未传入客户端版本号");
		TbInstallRecordPO installRecordPO = new TbInstallRecordPO();
		installRecordPO.setId(UUID.randomUUID().toString());
		installRecordPO.setInstallTime(new Date());
		installRecordPO.setAppVersionNum(request.getAppVersionNum());
		installRecordPO.setEquipmentNum(request.getEquipmentNum());
		installRecordPO.setType(Long.parseLong(request.getType()));
		installRecordPO.setVersionNum(request.getVersionNum());
		installRecordService.insertPO(installRecordPO, false);
		setOuter(JSONOuter.getInstance());
	}


	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

}
