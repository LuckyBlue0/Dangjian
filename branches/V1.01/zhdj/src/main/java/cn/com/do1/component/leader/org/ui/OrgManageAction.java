package cn.com.do1.component.leader.org.ui;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.FileUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.leader.org.service.IOrgManageService;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class OrgManageAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(OrgManageAction.class);
    private IOrgManageService orgManageService;
    private TbOrganizationPO tbOrganizationPO;
    private String ids[];
    private String id;
    private File file;
    private String fileFileName;
    private String fileContentType;
    private String parentName;
    private String page;
    private String memberName;

    public IOrgManageService getOrgManageService() {
        return orgManageService;
    }

    @Resource
    public void setOrgManageService(IOrgManageService orgManageService) {
        this.orgManageService = orgManageService;
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
        @SearchValueType(name = "organizationName", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(),getPageSize());
            pager=orgManageService.searchOrgManage(getSearchValue(),pager);
            if(!AssertUtil.isEmpty(pager.getPageData())){
            	List<TbOrganizationVO> list=(List<TbOrganizationVO>) pager.getPageData();
            	int num=0;
            	for(TbOrganizationVO vo:list){
            		num++;
            		vo.setSort(String.valueOf(num));
            	}
            	pager.setPageData(list);
            }
            addJsonPager("pageData",pager);
    }
    /**
     * 机构列表
     */
    public void listOrgRoot()
    {
      try
      {
        addJsonArray("orgList", this.orgManageService.listOrgRoot());
        setActionResult("0", "查询成功");
      } catch (SQLException e) {
    	  logger.error("获取组织机构根节点信息异常", e);
        setActionResult("2001", "初始化组织机构树异常");
      }
      doJsonOut();
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	
	        this.orgManageService.insertOrg(tbOrganizationPO);
	        
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	
            this.orgManageService.updateOrg(tbOrganizationPO);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	        this.orgManageService.deleteOrg(ids);
         
    }
   public void fileUpload(){
	 //定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 1000000;

		if(AssertUtil.isEmpty(file)){
			setActionResult("1000","上传文件不能为空！");
			doJsonOut();
			return;
		}
	 //检查文件大小
		if(file.length() > maxSize){
			setActionResult("1000","上传文件大小超过限制！");
			doJsonOut();
			return;
		}
		//检查扩展名
		String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
			setActionResult("1000","上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
			doJsonOut();
			return;
		}
		
		String uuid = java.util.UUID.randomUUID().toString();
		String newFileName = uuid + "." + fileExt;
	// 上传图片至文件服务器
		logger.info("开始上传图片至文件服务器");
		String backURL="";
		FileUploadUtil up=new FileUploadUtil();
		try {
			backURL=up.uploadFileBySMB(file, "upload/image", newFileName);
			//复制一个命名规范的图片文件在本地
			File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(newFile, ConfigMgr.get("system", "middle", "_middle"),"upload/image", true, true);
			setActionResult("0",backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
   }

   public void setTbOrganizationPO(TbOrganizationPO tbOrganizationPO){
       this.tbOrganizationPO=tbOrganizationPO;
    }
   public TbOrganizationPO setTbOrganizationPO(){
       return this.tbOrganizationPO;
    }
   public void addTbOrganizationPO(){
       super.ajaxAdd(tbOrganizationPO);
   }
   public void updateTbOrganizationPO(){
       super.ajaxUpdate(tbOrganizationPO);
   }
   public void deleteTbOrganizationPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbOrganizationPO._setPKValue(id);
       super.ajaxDelete(tbOrganizationPO);
   }
   public void batchDeleteTbOrganizationPO(){
       super.ajaxBatchDelete(TbOrganizationPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbOrganizationPO xxPO = orgManageService.searchByPk(TbOrganizationPO.class, id);
            if(!AssertUtil.isEmpty(xxPO.getParentId())){
            TbOrganizationPO parentPO = orgManageService.searchByPk(TbOrganizationPO.class, xxPO.getParentId());
            if(!AssertUtil.isEmpty(parentPO)){
            addJsonObj("parentName", parentPO.getOrganizationName());
            }
            }
            if(!AssertUtil.isEmpty(xxPO.getAdministrator())){
            	TbPartyMenberInfoPO member=orgManageService.searchByPk(TbPartyMenberInfoPO.class, xxPO.getAdministrator());
            	if(!AssertUtil.isEmpty(member)){
            		addJsonObj("memberName",member.getName());
            	}
            }
            //查询组织委员5
            List<TbPartyMenberInfoPO> listLeader=orgManageService.searchLeaderByOrgId(id);
            //查询党务工作者1
            List<TbPartyMenberInfoPO> listEditor=orgManageService.searchEditorByOrgId(id);
            if(!AssertUtil.isEmpty(listLeader)){
            	String name="";
            	for(TbPartyMenberInfoPO po:listLeader){
            		name+=po.getName()+";";
            		xxPO.setLeader(name.substring(0,name.length()-1));
            	}
            }else{
            	xxPO.setLeader("");
            }
            if(!AssertUtil.isEmpty(listEditor)){
            	String name="";
            	for(TbPartyMenberInfoPO po:listEditor){
            		name+=po.getName()+";";
            		xxPO.setEditor(name.substring(0,name.length()-1));
            	}
            }else{
            	xxPO.setEditor("");
            }
            addJsonFormateObj("tbOrganizationPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbOrganizationPO getTbOrganizationPO() {
        return this.tbOrganizationPO;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberName() {
		return memberName;
	}


    
}
