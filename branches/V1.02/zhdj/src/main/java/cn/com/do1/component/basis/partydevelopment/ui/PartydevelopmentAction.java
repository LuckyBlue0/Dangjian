package cn.com.do1.component.basis.partydevelopment.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.security.MD5;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevApplyForPO;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partydevelopment.service.IPartydevelopmentService;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevApplyForVO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevelopmentMenberVO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartydevelopmentAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartydevelopmentAction.class);
	private IPartydevelopmentService partydevelopmentService;
	private TbPartyDevelopmentMenberPO tbPartyDevelopmentMenberPO;
	private IPartymenberService partymenberService;
	private TbPartyDevApplyForPO tbPartyDevApplyForPO;
	private String ids[];
	private String id;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private StringBuilder impLog;

	public IPartydevelopmentService getPartydevelopmentService() {
		return partydevelopmentService;
	}

	@Resource
	public void setPartydevelopmentService(
			IPartydevelopmentService partydevelopmentService) {
		this.partydevelopmentService = partydevelopmentService;
	}

	public IPartymenberService getPartymenberService() {
		return partymenberService;
	}

	public void setPartymenberService(IPartymenberService partymenberService) {
		this.partymenberService = partymenberService;
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
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "name", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = partydevelopmentService.searchPartydevelopment(
				getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		try {
			partydevelopmentService
					.savePartydevelopmentPO(tbPartyDevelopmentMenberPO);
			setActionResult("0", "保存成功");
		} catch (Exception e) {
			setActionResult("1001", "保存失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		try {
			if (!StringUtil.isNullEmpty(tbPartyDevelopmentMenberPO
					.getPassWord())) {
				tbPartyDevelopmentMenberPO
						.setPassWord(new MD5().getMD5ofStr(
								tbPartyDevelopmentMenberPO.getPassWord())
								.toLowerCase());
			}else{
				tbPartyDevelopmentMenberPO.setPassWord(null);
			}
			partydevelopmentService.updatePO(tbPartyDevelopmentMenberPO, false);
			setActionResult("0", "保存成功");
		} catch (Exception e) {
			setActionResult("1001", "保存失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbPartyDevelopmentMenberPO == null)
			tbPartyDevelopmentMenberPO = new TbPartyDevelopmentMenberPO();
		tbPartyDevelopmentMenberPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPartyDevelopmentMenberPO.class, ids);
		} else {
			super.ajaxDelete(tbPartyDevelopmentMenberPO);
		}
	}

	public void setTbPartyDevelopmentMenberPO(
			TbPartyDevelopmentMenberPO tbPartyDevelopmentMenberPO) {
		this.tbPartyDevelopmentMenberPO = tbPartyDevelopmentMenberPO;
	}

	public TbPartyDevelopmentMenberPO setTbPartyDevelopmentMenberPO() {
		return this.tbPartyDevelopmentMenberPO;
	}

	public void addTbPartyDevelopmentMenberPO() {
		super.ajaxAdd(tbPartyDevelopmentMenberPO);
	}

	public void updateTbPartyDevelopmentMenberPO() {
		super.ajaxUpdate(tbPartyDevelopmentMenberPO);
	}

	public void deleteTbPartyDevelopmentMenberPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbPartyDevelopmentMenberPO._setPKValue(id);
		super.ajaxDelete(tbPartyDevelopmentMenberPO);
	}

	public void batchDeleteTbPartyDevelopmentMenberPO() {
		super.ajaxBatchDelete(TbPartyDevelopmentMenberPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		PartyDevelopmentMenberVO partyDevelopmentMenberVO = partydevelopmentService
				.getPartyDevelopmentById(id);
		addJsonObj("tbPartyDevelopmentMenberPO", partyDevelopmentMenberVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	@JSONOut(catchException = @CatchException(errCode = "1008", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxManagementView() throws Exception, BaseException {
		PartyDevelopmentMenberVO partyDevelopmentMenberVO = partydevelopmentService
				.getPartyDevelopmentById(id);// 发展党员
		PartyDevApplyForVO partyDevApplyForVO = partydevelopmentService
				.getPartyDevApplyForLastByUserId(partyDevelopmentMenberVO
						.getId());
		List<PartyDevApplyForVO> partyDevApplyForList = partydevelopmentService
				.getPartyDevApplyForPassByUserId(partyDevelopmentMenberVO
						.getId());
		if (null == partyDevApplyForVO) {// 如果不存在则新增一条发展记录
			TbPartyDevApplyForPO po = new TbPartyDevApplyForPO();
			po.setId(UUID.randomUUID().toString().toLowerCase());
			po.setUserId(id);
			// po.setCreateTime(new Date());
			po.setStatus("0");
			if (null != partyDevApplyForList && partyDevApplyForList.size() > 0) {
				po.setOrganizationIdentity(partyDevApplyForList.get(
						partyDevApplyForList.size() - 1)
						.getApplyForOrgIdentity());
				po.setApplyForDesc(partyDevApplyForList.get(0)
						.getApplyForDesc());
				po.setSpecialty(partyDevApplyForList.get(0).getSpecialty());
				po.setCreateTime(partyDevApplyForList.get(0).getCreateTime());
			} else {
				if (null != partyDevApplyForVO) {
					po.setOrganizationIdentity(partyDevApplyForVO
							.getOrganizationIdentity());
				} else {
					po.setOrganizationIdentity(partyDevelopmentMenberVO
							.getOrganizationIdentity());
				}
			}
			po.setApplyForOrgIdentity((Integer.parseInt(po
					.getOrganizationIdentity()) + 1)
					+ "");
			this.partydevelopmentService.insertPO(po, true);
			partyDevApplyForVO = partydevelopmentService
					.getPartyDevApplyForLastByUserId(partyDevelopmentMenberVO
							.getId());
		}
		partyDevApplyForVO.setApplyForOrgIdentityDesc("同意列为"
				+ partyDevApplyForVO.getApplyForOrgIdentityDesc());
		addJsonObj("tbPartyDevelopmentMenberPO", partyDevelopmentMenberVO);// 发展党员信息
		addJsonObj("partyDevApplyForVO", partyDevApplyForVO);
		addJsonArray("partyDevApplyForList", partyDevApplyForList);
	}

	@JSONOut(catchException = @CatchException(errCode = "1009", successMsg = "操作成功", faileMsg = "操作失败"))
	public void ajaxChangeIdentity() throws Exception, BaseException {
		try {
			TbPartyMenberInfoPO partyMenber = partymenberService
					.getPartyMenberByUserName(DqdpAppContext.getCurrentUser()
							.getUsername());
			if (null != partyMenber)
				tbPartyDevApplyForPO.setAuditUserId(partyMenber
						.getAuditUserId());
			tbPartyDevApplyForPO.setAuditTime(new Date());
			this.partydevelopmentService.updatePO(tbPartyDevApplyForPO, false);
			if ("1".equals(tbPartyDevApplyForPO.getStatus())) {// 如果通过
				TbPartyDevelopmentMenberPO menber = this.partydevelopmentService
						.searchByPk(TbPartyDevelopmentMenberPO.class,
								tbPartyDevApplyForPO.getUserId());
				menber.setOrganizationIdentity(tbPartyDevApplyForPO
						.getApplyForOrgIdentity());
				if ("6".equals(tbPartyDevApplyForPO.getApplyForOrgIdentity())) {// 如果转正
					menber.setState(0L);// 禁用发展党员
					// 新增党员信息
					TbPartyMenberInfoPO partyMenberInfoPO = new TbPartyMenberInfoPO();
					TbPartyDevelopmentMenberPO dMenber = this.partydevelopmentService.searchByPk(TbPartyDevelopmentMenberPO.class, tbPartyDevApplyForPO.getUserId());
					String userName = dMenber.getUserName();
					int i = 1;
					//判断系统用户名是否重复
					while (null != partymenberService.getPartyMenberByUserName(userName)) {
						userName = userName + i;
						i++;
					}
					partyMenberInfoPO.setId(dMenber.getId());
					partyMenberInfoPO.setUserName(userName);
					partyMenberInfoPO.setName(dMenber.getName());
					partyMenberInfoPO.setPassword(dMenber.getPassWord());
					if(!StringUtil.isNullEmpty(dMenber.getSex()))
						partyMenberInfoPO.setSex(Long.parseLong(dMenber.getSex()));
					partyMenberInfoPO.setMobile(dMenber.getMobile());
					partyMenberInfoPO.setIdCard(dMenber.getIdCard());
					partyMenberInfoPO.setOrganizationId(dMenber.getOrganizationId());
					partyMenberInfoPO.setDegree(dMenber.getDegree());
					partyMenberInfoPO.setDegreeIn(dMenber.getDegreeIn());
					partyMenberInfoPO.setBirthday(dMenber.getBirthday());
					partyMenberInfoPO.setNational(dMenber.getNational());
					partyMenberInfoPO.setNativePlace(dMenber.getNativePlace());
					partyMenberInfoPO.setPartyTime(new Date());
					partyMenberInfoPO.setCreateTime(new Date());
					partyMenberInfoPO.setHomeAddress(dMenber.getHomeAddress());
					partyMenberInfoPO.setNote(dMenber.getNote());
					partyMenberInfoPO.setUserType(1L);
					partyMenberInfoPO.setState(1L);
					partyMenberInfoPO.setIsManagement("0");
					partyMenberInfoPO.setPortraitPic(dMenber.getPortraitPic());
					partymenberService.savePartymenberPO(partyMenberInfoPO);
				}
				this.partydevelopmentService.updatePO(menber, false);

			}
			setActionResult("0", "操作成功");
		} catch (Exception e) {
			setActionResult("1001", "操作失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	public TbPartyDevelopmentMenberPO getTbPartyDevelopmentMenberPO() {
		return this.tbPartyDevelopmentMenberPO;
	}

	@JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导入成功", faileMsg = "导入失败"))
	public void fileUpload() throws BaseException {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "xls");
		Integer count = 0;

		// 最大文件大小
		long maxSize = 10000000;

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
		String fileExt = fileFileName.substring(
				fileFileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("file").split(",")).contains(
				fileExt)) {
			setActionResult("1000", "上传文件扩展名是不允许的扩展名。\n只允许"
					+ extMap.get("file") + "格式。");
			doJsonOut();
			return;
		}

		String uuid = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String newFileName = uuid + "." + fileExt;
		// 上传图片至文件服务器
		logger.info("开始上传至文件服务器");
		String backURL = "";
		FileUploadUtil up = new FileUploadUtil();
		try {
			backURL = up.uploadFileBySMB(file, "upload/excel", newFileName);
			logger.info("上传发展党员信息表至文件服务器成功。");
			impLog = new StringBuilder();
			count = importDate();
			setActionResult(0, "成功导入" + count + "条发展党员信息！");
			if (StringUtil.isNullEmpty(impLog.toString())) {
				impLog.append("全部导入成功……");
			}
			addJsonObj("msg", impLog.toString());
		} catch (Exception e1) {
			logger.info("上传发展党员信息表至文件服务器失败。");
			setActionResult("1000", "导入失败，已成功导入" + count + "条发展党员信息！" + impLog);
		}
		doJsonOut();
	}

	public Integer importDate() {
		int count = 0;
		Workbook wb = null;
		TbPartyDevelopmentMenberPO po = null;
		try {
			List<TbOrganizationVO> oraganizationList = this.partymenberService
					.getOrganizations();
			List<ExItemObj> orgIdentityList = DictOperater
					.getAllItemByType("orgIdentity");// 组织身份
			List<ExItemObj> sexList = DictOperater
					.getAllItemByType("personSex");// 性别
			List<ExItemObj> degreeList = DictOperater
					.getAllItemByType("degree");// 学历
			List<ExItemObj> degreeInList = DictOperater
					.getAllItemByType("degreeIn");// 学位

			Map oraganizationMap = new HashMap();
			for (int i = 0; i < oraganizationList.size(); i++) {
				oraganizationMap.put(oraganizationList.get(i)
						.getOrganizationName(), oraganizationList.get(i)
						.getId());
			}
			Map orgIdentityMap = new HashMap();
			for (int i = 0; i < orgIdentityList.size(); i++) {
				orgIdentityMap.put(orgIdentityList.get(i).getFsItemDesc(),
						orgIdentityList.get(i).getFsItemCode());
			}
			Map sexMap = new HashMap();
			for (int i = 0; i < sexList.size(); i++) {
				sexMap.put(sexList.get(i).getFsItemDesc(), sexList.get(i)
						.getFsItemCode());
			}
			Map degreeMap = new HashMap();
			for (int i = 0; i < degreeList.size(); i++) {
				degreeMap.put(degreeList.get(i).getFsItemDesc(), degreeList
						.get(i).getFsItemCode());
			}
			Map degreeInMap = new HashMap();
			for (int i = 0; i < degreeInList.size(); i++) {
				degreeInMap.put(degreeInList.get(i).getFsItemDesc(),
						degreeInList.get(i).getFsItemCode());
			}
			// 从输入流创建Workbook
			InputStream is = new FileInputStream(file);
			wb = Workbook.getWorkbook(is);
			// 获取第一张Sheet表
			Sheet rs = wb.getSheet(0); // 获取第0张excel表
			// 获取Sheet表中所包含的总列数
			// int rsColumns = rs.getColumns();
			// 获取Sheet表中所包含的总行数
			int rsRow = rs.getRows();
			for (int i = 1; i < rsRow; i++) {
				if (StringUtil.isNullEmpty(rs.getCell(0, i).getContents())) {
					continue;
				}
				po = new TbPartyDevelopmentMenberPO();
				po.setUserName(rs.getCell(0, i).getContents());
				po.setName(rs.getCell(1, i).getContents());
				if (null == sexMap.get(rs.getCell(2, i).getContents())) {
					po.setSex(null);
				} else {
					po.setSex(sexMap.get(rs.getCell(2, i).getContents())
							.toString());
				}
				po.setMobile(rs.getCell(3, i).getContents());
				po.setIdCard(rs.getCell(4, i).getContents());
				po.setNational(rs.getCell(5, i).getContents());// 民族
				if (null == oraganizationMap
						.get(rs.getCell(6, i).getContents())) {// 所属组织
					po.setOrganizationId(null);
				} else {
					po.setOrganizationId(oraganizationMap.get(
							rs.getCell(6, i).getContents()).toString());
				}
				if (null == orgIdentityMap.get(rs.getCell(7, i).getContents())) {// 组织身份
					po.setOrganizationIdentity(null);
				} else {
					po.setOrganizationIdentity(orgIdentityMap.get(
							rs.getCell(7, i).getContents()).toString());
				}
				if (null == degreeMap.get(rs.getCell(8, i).getContents())) {
					po.setDegree(null);
				} else {
					po.setDegree(degreeMap.get(rs.getCell(8, i).getContents())
							.toString());
				}
				if (null == degreeInMap.get(rs.getCell(9, i).getContents())) {
					po.setDegreeIn(null);
				} else {
					po.setDegreeIn(degreeInMap.get(
							rs.getCell(9, i).getContents()).toString());
				}
				po.setNativePlace(rs.getCell(10, i).getContents());// 籍贯
				po.setBirthday(rs.getCell(11, i).getContents());
				po.setUnit(rs.getCell(12, i).getContents());
				po.setHomeAddress(rs.getCell(13, i).getContents());
				po.setNote(rs.getCell(14, i).getContents());
				po.setPassWord("a12345");
				try {
					partydevelopmentService.savePartydevelopmentPO(po);
					count++;
				} catch (Exception e) {
					impLog.append(e.getMessage());
				}
			}
			return count;
		} catch (Exception e) {
			return count;
		} catch (BaseException e) {
			return count;
		}
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

	public TbPartyDevApplyForPO getTbPartyDevApplyForPO() {
		return tbPartyDevApplyForPO;
	}

	public void setTbPartyDevApplyForPO(
			TbPartyDevApplyForPO tbPartyDevApplyForPO) {
		this.tbPartyDevApplyForPO = tbPartyDevApplyForPO;
	}
}
