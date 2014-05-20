package cn.com.do1.component.data.partyfile.ui;
import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

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
import cn.com.do1.component.data.partyfile.model.TbPartyFilePO;
import cn.com.do1.component.data.partyfile.service.IPartyfileService;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class PartyfileAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(PartyfileAction.class);
    private IPartyfileService PartyfileService;
    private TbPartyFilePO tbPartyFilePO;
    private String ids[];
    private String id;
    private String dir;

    public IPartyfileService getPartyfileService() {
        return PartyfileService;
    }

    @Resource
    public void setPartyfileService(IPartyfileService PartyfileService) {
        this.PartyfileService = PartyfileService;
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
            pager=PartyfileService.searchPartyfile(getSearchValue(),pager);
            addJsonFormatePager("pageData",pager);
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	    IUser user = (IUser) DqdpAppContext.getCurrentUser();// 获取当前登录用户信息
	    tbPartyFilePO.setCreator(user.getUsername());
	    tbPartyFilePO.setPushTime(new Date());
	    tbPartyFilePO.setStatus(0l);	    
	    PartyfileService.insertPO(tbPartyFilePO, true);
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	    PartyfileService.updatePO(tbPartyFilePO, false);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	 if(!AssertUtil.isEmpty(ids)){
   	   for(String id:ids){
   		TbPartyFilePO xxPO = this.PartyfileService.searchByPk(TbPartyFilePO.class, id);
   		   this.PartyfileService.delPO(xxPO);
   	   }
      }
    }
   @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
   public void uploadFile(){
	   String savePath =getReqeustObj().getSession().getServletContext().getRealPath("/");
		 //同步图片至文件服务器
		File file=new File(savePath+tbPartyFilePO.getFilePath());
		System.out.println("文件路径："+savePath+tbPartyFilePO.getFilePath());
		if(file.exists()){
		
		String backURL="";
		FileUploadUtil up=new FileUploadUtil();
		try {
			backURL=up.uploadFileBySMB(file, tbPartyFilePO.getFilePath().substring(0, 30), tbPartyFilePO.getFilePath().substring(31, tbPartyFilePO.getFilePath().length()));
		    if(!AssertUtil.isEmpty(backURL)){
			file.delete();
		    }
		} catch (Exception e1) {
			logger.info("同步文件至文件服务器失败。");
			e1.printStackTrace();
		}
		}else{
			logger.info("同步文件："+tbPartyFilePO.getFileName()+"不存在");
		}
   }
   public void setTbPartyFilePO(TbPartyFilePO tbPartyFilePO){
       this.tbPartyFilePO=tbPartyFilePO;
    }
   public TbPartyFilePO setTbPartyFilePO(){
       return this.tbPartyFilePO;
    }
   public void addTbPartyFilePO(){
       super.ajaxAdd(tbPartyFilePO);
   }
   public void updateTbPartyFilePO(){
       super.ajaxUpdate(tbPartyFilePO);
   }
   public void deleteTbPartyFilePO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbPartyFilePO._setPKValue(id);
       super.ajaxDelete(tbPartyFilePO);
   }
   public void batchDeleteTbPartyFilePO(){
       super.ajaxBatchDelete(TbPartyFilePO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{	
	TbPartyFilePO xxPO = PartyfileService.searchByPk(TbPartyFilePO.class, id);
            addJsonFormateObj("tbPartyFilePO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbPartyFilePO getTbPartyFilePO() {
        return this.tbPartyFilePO;
    }

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}

}
