package cn.com.do1.component.data.videofile.ui;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.data.videofile.model.TbVideoPO;
import cn.com.do1.component.data.videofile.service.IDatainfoService;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderPO;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.myProgressListener;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class DatainfoAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(DatainfoAction.class);
    private IDatainfoService datainfoService;
    private TbVideoPO tbVideoPO;
    private String ids[];
    private String id;
    private String dir;

    public IDatainfoService getDatainfoService() {
        return datainfoService;
    }

    @Resource
    public void setDatainfoService(IDatainfoService datainfoService) {
        this.datainfoService = datainfoService;
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
        @SearchValueType(name = "fileName", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=datainfoService.searchDatainfo(getSearchValue(),pager);
            addJsonFormatePager("pageData",pager);
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	    IUser user = (IUser) DqdpAppContext.getCurrentUser();// 获取当前登录用户信息
	    tbVideoPO.setCreator(user.getUsername());
	    tbVideoPO.setPushTime(new Date());
	    tbVideoPO.setStatus(0l);	    
	    datainfoService.insertPO(tbVideoPO, true);
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	    datainfoService.updatePO(tbVideoPO, false);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	 if(!AssertUtil.isEmpty(ids)){
   	   for(String id:ids){
   		   TbVideoPO xxPO = this.datainfoService.searchByPk(TbVideoPO.class, id);
   		   this.datainfoService.delPO(xxPO);
   	   }
      }
    }
   @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
   public void uploadFile(){
	   String savePath =getReqeustObj().getSession().getServletContext().getRealPath("/");
		 //同步图片至文件服务器
		File file=new File(savePath+tbVideoPO.getFilePath());
		System.out.println("文件路径："+savePath+tbVideoPO.getFilePath());
		if(file.exists()){
		
		String backURL="";
		FileUploadUtil up=new FileUploadUtil();
		try {
			backURL=up.uploadFileBySMB(file, tbVideoPO.getFilePath().substring(0, 30), tbVideoPO.getFilePath().substring(31, tbVideoPO.getFilePath().length()));
		    if(!AssertUtil.isEmpty(backURL)){
			file.delete();
		    }
		} catch (Exception e1) {
			logger.info("同步文件至文件服务器失败。");
			e1.printStackTrace();
		}
		}else{
			logger.info("同步文件："+tbVideoPO.getFileName()+"不存在");
		}
   }
   public void setTbVideoPO(TbVideoPO tbVideoPO){
       this.tbVideoPO=tbVideoPO;
    }
   public TbVideoPO setTbVideoPO(){
       return this.tbVideoPO;
    }
   public void addTbVideoPO(){
       super.ajaxAdd(tbVideoPO);
   }
   public void updateTbVideoPO(){
       super.ajaxUpdate(tbVideoPO);
   }
   public void deleteTbVideoPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbVideoPO._setPKValue(id);
       super.ajaxDelete(tbVideoPO);
   }
   public void batchDeleteTbVideoPO(){
       super.ajaxBatchDelete(TbVideoPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{	
            TbVideoPO xxPO = datainfoService.searchByPk(TbVideoPO.class, id);
            addJsonFormateObj("tbVideoPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbVideoPO getTbVideoPO() {
        return this.tbVideoPO;
    }

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}

}
