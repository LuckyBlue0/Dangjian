package cn.com.do1.component.partywork.volunteer.ui;
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
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.FileUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.partywork.volunteer.service.IVolunteerService;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class VolunteerAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(VolunteerAction.class);
	@Resource
	private IVolunteerService volunteerService;
	@Resource
	private IMeetinguserService meetinguserService;
	@Resource
	private IMembercenterService membercenterService;
	private TbVolunteerActivityPO tbVolunteerActivityPO;
	private String ids[];
	private String id;
	private String userIds;
	private File file;
	private String fileFileName;
	private String fileContentType;

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
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		IUser user =(IUser)DqdpAppContext.getCurrentUser();
		boolean flag = membercenterService.searchPositionByUserId(user.getPersonId());
		if(flag){
			TbPartyMenberInfoPO po=membercenterService.searchByPk(TbPartyMenberInfoPO.class,user.getPersonId());
			if(po != null){
				getSearchValue().put("organizationId",po.getOrganizationId());
			}
		}
		pager = volunteerService.searchVolunteer(getSearchValue(), pager);
		addJsonPager("pageData", pager);
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
		if (tbVolunteerActivityPO == null)
			tbVolunteerActivityPO = new TbVolunteerActivityPO();
		tbVolunteerActivityPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbVolunteerActivityPO.class, ids);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		} else {
			super.ajaxDelete(tbVolunteerActivityPO);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		}
	}
	
	/**
	 * 新增或修改
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		Map<String, Object> result = volunteerService.saveVolunteerActivityInfo(tbVolunteerActivityPO,userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}
	
	/**
	 * 查看详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		VolunteerVO xxPO = volunteerService.getVolunteerById(id);
		addJsonObj("tbVolunteerActivityPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	
	/**
	 * 提交审核
	 * @throws SQLException 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = " 提交审核成功", faileMsg = " 提交审核失败"))
	public void ajaxSubmitAudit() throws SQLException{
		TbVolunteerActivityPO po = new TbVolunteerActivityPO();
		po.setId(id);
		po.setStatus(1l);
		this.volunteerService.updatePO(po, false);
	}

	/**
	 * 审核
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxAudit() throws Exception, BaseException {
		IUser user = (IUser)DqdpAppContext.getCurrentUser();
		if(tbVolunteerActivityPO.getStatus() == 2){
						
		}
		tbVolunteerActivityPO.setAuditUserId(user.getPersonId());	
		tbVolunteerActivityPO.setAuditTime(new Date());
		setActionResult("0", "审核成功");
		this.volunteerService.updatePO(tbVolunteerActivityPO, false);
		doJsonOut();
	}
	
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "修改成功", faileMsg = "修改失败"))
	public void ajaxUpdateVolunteerAcitivity()throws Exception, BaseException {
		this.volunteerService.updatePO(tbVolunteerActivityPO, false);
	}
	/**
	 * 保存活动记录
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSaveRecord() throws Exception, BaseException{
		Map<String, Object> result = volunteerService.saveVolunteerRecordInfo(tbVolunteerActivityPO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
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
			backURL = up.uploadFileBySMB(file, "upload/volunteerActivity/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
			//复制一个命名规范的图片文件在本地
			File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "small", "_small"),"upload/volunteerActivity/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
			//按比例压缩,压缩后及时删除本地文件
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "middle", "_middle"),"upload/volunteerActivity/image/"+DateUtil.formatCurrent("yyyyMM"), true);
			setActionResult("0", backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
	}
	public void setTbVolunteerActivityPO(TbVolunteerActivityPO tbVolunteerActivityPO) {
		this.tbVolunteerActivityPO = tbVolunteerActivityPO;
	}

	public TbVolunteerActivityPO setTbVolunteerActivityPO() {
		return this.tbVolunteerActivityPO;
	}

	public void addTbVolunteerActivityPO() {
		super.ajaxAdd(tbVolunteerActivityPO);
	}

	public void updateTbVolunteerActivityPO() {
		super.ajaxUpdate(tbVolunteerActivityPO);
	}

	public void deleteTbVolunteerActivityPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbVolunteerActivityPO._setPKValue(id);
		super.ajaxDelete(tbVolunteerActivityPO);
	}

	public void batchDeleteTbVolunteerActivityPO() {
		super.ajaxBatchDelete(TbVolunteerActivityPO.class, ids);
	}


	public TbVolunteerActivityPO getTbVolunteerActivityPO() {
		return this.tbVolunteerActivityPO;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
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
}
