package cn.com.do1.component.mobileclient.version.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.data.datafile.model.TbDataFilePO;
import cn.com.do1.component.mobileclient.version.model.*;
import cn.com.do1.component.mobileclient.version.service.IVersionService;
import cn.com.do1.component.util.FileUploadUtil;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.do1.common.exception.BaseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class VersionAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(VersionAction.class);
    private IVersionService versionService;
    private TbVersionPO tbVersionPO;
    private String ids[];
    private String id;

    public IVersionService getVersionService() {
        return versionService;
    }

    @Resource
    public void setVersionService(IVersionService versionService) {
        this.versionService = versionService;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

/**
* 列表查询时，页面要传递的参数
*/
    @SearchValueTypes(
        nameFormat="false",value={
        @SearchValueType(name = "versionName", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=versionService.searchVersion(getSearchValue(),pager);
            addJsonFormatePager("pageData",pager);
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	         tbVersionPO.setStatus(0l);	 	     
	         versionService.insertPO(tbVersionPO, true);
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	        if(!AssertUtil.isEmpty(tbVersionPO.getStatus())&& 1 == tbVersionPO.getStatus()){
	        	IUser user = (IUser) DqdpAppContext.getCurrentUser();// 获取当前登录用户信息
		         tbVersionPO.setCreator(user.getUsername());
	        	tbVersionPO.setPushTime(new Date());
	        }
	        versionService.invalidVersion();
	        versionService.updatePO(tbVersionPO, false);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	if(!AssertUtil.isEmpty(ids)){
	   	   for(String id:ids){
	   		TbVersionPO xxPO = this.versionService.searchByPk(TbVersionPO.class, id);
	   		   this.versionService.delPO(xxPO);
	   	   }
	      }
    }
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
public void ajaxSearchTopVersion() throws Exception, BaseException{
        Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
        getSearchValue().put("status", "1");
        pager=versionService.searchVersion(getSearchValue(),pager);
        if(!AssertUtil.isEmpty(pager.getPageData())){
        	List<TbVersionPO> list=(List<TbVersionPO>) pager.getPageData();
        	setActionResult("0", list.get(0).getVersionNum());
            addJsonObj("versionNum",list.get(0).getVersionNum());
        }else{
        	setActionResult("0", "0.0.0");
        	addJsonObj("versionNum","0.0.0");
        }
}
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
public void uploadFile(){
	   String savePath =getReqeustObj().getSession().getServletContext().getRealPath("/");
		 //同步图片至文件服务器
		File file=new File(savePath+tbVersionPO.getFilePath());
		System.out.println("文件路径："+savePath+tbVersionPO.getFilePath());
		if(file.exists()){
		
		String backURL="";
		FileUploadUtil up=new FileUploadUtil();
		try {
			backURL=up.uploadFileBySMB(file, tbVersionPO.getFilePath().substring(0, 32), tbVersionPO.getFilePath().substring(33, tbVersionPO.getFilePath().length()));
		    if(!AssertUtil.isEmpty(backURL)){
			file.delete();
		    }
		} catch (Exception e1) {
			logger.info("同步文件至文件服务器失败。");
			e1.printStackTrace();
		}
		}else{
			logger.info("同步文件："+tbVersionPO.getVersionName()+"不存在");
		}
}
   public void setTbVersionPO(TbVersionPO tbVersionPO){
       this.tbVersionPO=tbVersionPO;
    }
   public TbVersionPO setTbVersionPO(){
       return this.tbVersionPO;
    }
   public void addTbVersionPO(){
       super.ajaxAdd(tbVersionPO);
   }
   public void updateTbVersionPO(){
       super.ajaxUpdate(tbVersionPO);
   }
   public void deleteTbVersionPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbVersionPO._setPKValue(id);
       super.ajaxDelete(tbVersionPO);
   }
   public void batchDeleteTbVersionPO(){
       super.ajaxBatchDelete(TbVersionPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbVersionPO xxPO = versionService.searchByPk(TbVersionPO.class, id);
            addJsonFormateObj("tbVersionPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbVersionPO getTbVersionPO() {
        return this.tbVersionPO;
    }
    public void downLoadFile() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		File file = new File(request.getSession().getServletContext()
				.getRealPath("")
				+ "/upload/android/aqzhdj.apk");
		InputStream in = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		String fileName = "aqzhdj.apk";
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
			fileName = new String(fileName.getBytes("utf-8"),
					("iso-8859-1"));
		} else {
			fileName = fileName;
		}
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		response.addHeader("Content-length", file.length() + "");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-download");
		int data = 0;
		while ((data = in.read()) != -1) {
			os.write(data);
		}
		os.close();
		in.close();
	}
}
