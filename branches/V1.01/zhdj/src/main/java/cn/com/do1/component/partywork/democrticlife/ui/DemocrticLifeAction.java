package cn.com.do1.component.partywork.democrticlife.ui;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.democrticlife.service.IDemocrticLifeService;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class DemocrticLifeAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(DemocrticLifeAction.class);
	@Resource
	private IDemocrticLifeService democrticLifeService;
	@Resource
	private IMeetinguserService meetinguserService;
	@Resource
	private IMembercenterService membercenterService;
	private TbDemocraticLifePO tbDemocraticLifePO;
	private String ids[];
	private String id;
	private String userIds;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private int type;

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "keyword", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		IUser user =(IUser)DqdpAppContext.getCurrentUser();
		boolean flag = membercenterService.searchPositionByUserId(user.getPersonId());
		if(flag){
			TbPartyMenberInfoPO po=meetinguserService.searchByPk(TbPartyMenberInfoPO.class,user.getPersonId());
			if(po != null){
				getSearchValue().put("organizationId",po.getOrganizationId());
			}
		}
		pager = democrticLifeService.searchDemocrticLife(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	/**
	 * 新增或修改
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		Map<String, Object> result = democrticLifeService.saveDemocrticLifeServiceInfo(tbDemocraticLifePO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}
	
	/**
	 * 删除
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量更新的代码
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbDemocraticLifePO == null)
			tbDemocraticLifePO = new TbDemocraticLifePO();
		tbDemocraticLifePO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbDemocraticLifePO.class, ids);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		} else {
			super.ajaxDelete(tbDemocraticLifePO);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		}
	}
	
	/**
	 * 保存民主生活会记录
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSaveRecord() throws Exception, BaseException{
		Map<String, Object> result = democrticLifeService.saveDemocrticLifeRecordInfo(tbDemocraticLifePO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}
	
	/**
	 * 发布或下架
	 */
	public void pushOrOut(){
		if(AssertUtil.isEmpty(id) || AssertUtil.isEmpty(type)){
			setActionResult("-1","参数有误!");	
		}else{
			if (tbDemocraticLifePO == null){
				tbDemocraticLifePO = new TbDemocraticLifePO();
			}
			tbDemocraticLifePO.setId(id);
			IUser user = (IUser) DqdpAppContext.getCurrentUser();
			if(type==1){//发布
				tbDemocraticLifePO.setPushTime(new Date());
				tbDemocraticLifePO.setPushUserId(user.getPersonId());
				tbDemocraticLifePO.setStatus(1l);
			}else if(type==2){
				tbDemocraticLifePO.setStatus(2l);
			}
			try {
				this.democrticLifeService.updatePO(tbDemocraticLifePO, false);
				setActionResult("0",type == 1 ? "发布成功" : "下架成功");
			} catch (SQLException e) {
				setActionResult("0",type == 1 ? "发布失败" : "下架失败");
			}
		}
		doJsonOut();
	}	

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		DemocrticLifeVO xxPO = democrticLifeService.getDemocrticLifeById(id);
		addJsonObj("tbDemocraticLifePO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	public void fileUpload() {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		if (AssertUtil.isEmpty(file)) {
			setActionResult("1000", "上传文件不能为空！");
			doJsonOut();
			return;
		}
		// 检查文件大小
		if (file.length() > maxSize) {
			setActionResult("1000", "上传文件大小超过限制！");
			doJsonOut();
			return;
		}
		// 检查扩展名
		String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
			setActionResult("1000", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
			doJsonOut();
			return;
		}

		String uuid = java.util.UUID.randomUUID().toString();
		String newFileName = uuid + "." + fileExt;
		// 上传图片至文件服务器
		logger.info("开始上传图片至文件服务器");
		String backURL = "";
		FileUploadUtil up = new FileUploadUtil();
		try {
			backURL = up.uploadFileBySMB(file, "upload/democrticlife/image", newFileName);
			setActionResult("0", backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
	}

	public TbDemocraticLifePO getTbDemocraticLifePO() {
		return tbDemocraticLifePO;
	}

	public void setTbDemocraticLifePO(TbDemocraticLifePO tbDemocraticLifePO) {
		this.tbDemocraticLifePO = tbDemocraticLifePO;
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

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
