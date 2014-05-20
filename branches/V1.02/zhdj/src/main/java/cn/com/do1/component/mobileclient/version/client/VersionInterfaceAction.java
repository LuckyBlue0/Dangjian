package cn.com.do1.component.mobileclient.version.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbException;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.mobileclient.installrecord.model.InstallRecordRequest;
import cn.com.do1.component.mobileclient.version.service.IVersionService;
import cn.com.do1.component.mobileclient.version.vo.VersionRequest;
import cn.com.do1.component.mobileclient.version.vo.VersionVO;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;
import cn.com.do1.component.util.SmbFileUtils;
import cn.com.do1.dqdp.core.ConfigLoadExcetion;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class VersionInterfaceAction extends BaseAction{
	private final static transient Logger logger = LoggerFactory.getLogger(VersionInterfaceAction .class);
	private String filePath;
	
	@Resource
    private IVersionService versionService;
    private String requestJson;
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "获取最新版本信息成功", faileMsg = "获取最新版本信息失败"))
	public void getNewVersion() throws Exception, BaseException {
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		logger.debug("newsInfoDetails requestJson>>>"+requestJson);
    	VersionRequest request = (VersionRequest)JSONObject.toBean(paramJson,VersionRequest.class);
    	request = (VersionRequest)CommonAuthManage.authDigestRetObj(request,new String []{"type"});
    	if(request == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		HashMap searchMap = new HashMap();
		searchMap.put("type", request.getType());
		VersionVO versionVO = this.versionService.getLastNewVersion(searchMap);
		if(versionVO != null){
			versionVO.setFilePath("version/version!downLoad.action?filePath="+versionVO.getFilePath());
			addJsonObj("newVersion", versionVO);
		}
		setOuter(JSONOuter.getInstance());
	}
	/**
	 * 下载apk安装包
	 */
	public void downLoad(){
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = null;
		InputStream is = null;
		try {
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
			SmbFileUtils smbFile = new SmbFileUtils(filePath);
			is = smbFile.readFile();
			os = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName + "");
			response.setCharacterEncoding("utf-8");
	        response.setContentType("application/octet-stream"); 
	        int length = -1;
	        if(smbFile.getSmbFile().length() > 0l){
	        	length = Integer.parseInt(String.valueOf(smbFile.getSmbFile().length()));
	        }
	        response.setContentLength(length);
			int b = 0;
			byte[] buffer = new byte[8192];
			try {
				while ((b = is.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, b);
				}
				os.flush();
				is.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
