package cn.com.do1.component.basis.partymenber.ui;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
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
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.util.FileUploadUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartymenberAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartymenberAction.class);
	private IPartymenberService partymenberService;
	private TbPartyMenberInfoPO tbPartyMenberInfoPO;
	private PartyMenberInfoVO partyMenberInfoVO;
	private String ids[];
	private String id;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private StringBuilder impLog;

	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public IPartymenberService getPartymenberService() {
		return partymenberService;
	}

	@Resource
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
	@SearchValueTypes(nameFormat = "false", value = {
			@SearchValueType(name = "affairsWorker", type = "number"),
			@SearchValueType(name = "name", type = "string", format = "%%%s%%"),
			@SearchValueType(name = "idCard", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = partymenberService.searchPartymenber(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	/**
	 * 组织关系列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "name", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1007", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearchRelation() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = partymenberService.searchPartyRelation(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		try {
			if (!"7".equals(tbPartyMenberInfoPO.getDegree())
					&& !"6".equals(tbPartyMenberInfoPO.getDegree())) {
				tbPartyMenberInfoPO.setDegreeIn(null);
			}
			partymenberService.savePartymenberPO(tbPartyMenberInfoPO);
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
			tbPartyMenberInfoPO = new TbPartyMenberInfoPO();
			ConvertUtils
					.register(new DateConverter(null), java.util.Date.class);
			BeanHelper.copyBeanProperties(tbPartyMenberInfoPO,
					partyMenberInfoVO);
			if (null == partyMenberInfoVO.getState()) {
				tbPartyMenberInfoPO.setState(null);
			}
			if (null == partyMenberInfoVO.getUserType()) {
				tbPartyMenberInfoPO.setUserType(null);
			}
			if (!"7".equals(tbPartyMenberInfoPO.getDegree())
					&& !"6".equals(tbPartyMenberInfoPO.getDegree())) {
				tbPartyMenberInfoPO.setDegreeIn(null);
			}
			String msg = this.partymenberService
					.updateUserInfo(tbPartyMenberInfoPO);
			if (StringUtil.isNullEmpty(msg)) {
				setActionResult("0", "保存成功");
			} else {
				setActionResult("1000", "保存失败," + msg);
			}
		} catch (Exception e) {
			setActionResult("1001", "保存失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "操作成功", faileMsg = "更新失败"))
	public void ajaxChangeState() throws Exception, BaseException {
		try {
			if (null != tbPartyMenberInfoPO
					&& !StringUtil.isNullEmpty(tbPartyMenberInfoPO.getId())) {
				String msg = this.partymenberService
						.updateUserInfo(tbPartyMenberInfoPO);
				if (StringUtil.isNullEmpty(msg)) {
					setActionResult("0", "操作成功");
				} else {
					setActionResult("1000", "操作失败," + msg);
				}
			} else {
				setActionResult("1000", "操作失败,丢失参数!");
			}
		} catch (Exception e) {
			setActionResult("1001", "操作失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbPartyMenberInfoPO == null)
			tbPartyMenberInfoPO = new TbPartyMenberInfoPO();
		tbPartyMenberInfoPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPartyMenberInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbPartyMenberInfoPO);
		}
	}

	public void setTbPartyMenberInfoPO(TbPartyMenberInfoPO tbPartyMenberInfoPO) {
		this.tbPartyMenberInfoPO = tbPartyMenberInfoPO;
	}

	public TbPartyMenberInfoPO setTbPartyMenberInfoPO() {
		return this.tbPartyMenberInfoPO;
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

	public PartyMenberInfoVO getPartyMenberInfoVO() {
		return partyMenberInfoVO;
	}

	public void setPartyMenberInfoVO(PartyMenberInfoVO partyMenberInfoVO) {
		this.partyMenberInfoVO = partyMenberInfoVO;
	}

	public void addTbPartyMenberInfoPO() {
		super.ajaxAdd(tbPartyMenberInfoPO);
	}

	public void updateTbPartyMenberInfoPO() {
		super.ajaxUpdate(tbPartyMenberInfoPO);
	}

	public void deleteTbPartyMenberInfoPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbPartyMenberInfoPO._setPKValue(id);
		super.ajaxDelete(tbPartyMenberInfoPO);
	}

	public void batchDeleteTbPartyMenberInfoPO() {
		super.ajaxBatchDelete(TbPartyMenberInfoPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		PartyMenberInfoVO partyMenberInfoVO = partymenberService
				.getPartyMenberById(id);
		addJsonObj("partyMenberInfoVO", partyMenberInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	public TbPartyMenberInfoPO getTbPartyMenberInfoPO() {
		return this.tbPartyMenberInfoPO;
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
			logger.info("上传党员信息表至文件服务器成功。");
			impLog = new StringBuilder();
			count = importDate();
			setActionResult(0, "成功导入" + count + "条党员信息！");
			this.partymenberService.updateHisPartyMenberState();//更新历史党员用户状态
			if (StringUtil.isNullEmpty(impLog.toString())) {
				impLog.append("全部导入成功……");
			}
			addJsonObj("msg", impLog.toString());
		} catch (Exception e1) {
			logger.info("上传党员信息表至文件服务器失败。");
			setActionResult("1000", "导入失败，已成功导入" + count + "条党员信息！" + impLog);
		}
		doJsonOut();
	}

	public Integer importDate() {
		int count = 0;
		Workbook wb = null;
		TbPartyMenberInfoPO po = null;
		try {
			List<TbOrganizationVO> oraganizationList = this.partymenberService
					.getOrganizations();
			List<ExItemObj> affairsWorkerList = DictOperater
					.getAllItemByType("affairsWorkerStatu");// 党务工作者
			List<ExItemObj> volunteersList = DictOperater
					.getAllItemByType("volunteersStatus");// 志愿者
			List<ExItemObj> behalfList = DictOperater
					.getAllItemByType("behalfStatus");// 党代表
			List<ExItemObj> hardPartyMembersList = DictOperater
					.getAllItemByType("hardPartyMembers");// 困难党员
			List<ExItemObj> sexList = DictOperater
					.getAllItemByType("personSex");// 性别
			List<ExItemObj> degreeList = DictOperater
					.getAllItemByType("degree");// 学历
			List<ExItemObj> degreeInList = DictOperater
					.getAllItemByType("degreeIn");// 学位
			List<ExItemObj> isManagementList = DictOperater
					.getAllItemByType("isManagement");// 是否管理员

			Map oraganizationMap = new HashMap();
			for (int i = 0; i < oraganizationList.size(); i++) {
				oraganizationMap.put(oraganizationList.get(i)
						.getOrganizationName(), oraganizationList.get(i)
						.getId());
			}
			Map affairsWorkerMap = new HashMap();
			for (int i = 0; i < affairsWorkerList.size(); i++) {
				affairsWorkerMap.put(affairsWorkerList.get(i).getFsItemDesc(),
						affairsWorkerList.get(i).getFsItemCode());
			}
			Map volunteersMap = new HashMap();
			for (int i = 0; i < volunteersList.size(); i++) {
				volunteersMap.put(volunteersList.get(i).getFsItemDesc(),
						volunteersList.get(i).getFsItemCode());
			}
			Map behalfMap = new HashMap();
			for (int i = 0; i < behalfList.size(); i++) {
				behalfMap.put(behalfList.get(i).getFsItemDesc(), behalfList
						.get(i).getFsItemCode());
			}
			Map hardPartyMembersMap = new HashMap();
			for (int i = 0; i < hardPartyMembersList.size(); i++) {
				hardPartyMembersMap.put(hardPartyMembersList.get(i)
						.getFsItemDesc(), hardPartyMembersList.get(i)
						.getFsItemCode());
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
			Map isManagementMap = new HashMap();
			for (int i = 0; i < isManagementList.size(); i++) {
				isManagementMap.put(isManagementList.get(i).getFsItemDesc(),
						isManagementList.get(i).getFsItemCode());
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
				if (!StringUtil.isNullEmpty(rs.getCell(0, i).getContents())) {
					po = new TbPartyMenberInfoPO();
					po.setUserType(tbPartyMenberInfoPO.getUserType());// 党员状态
					po.setPassword("a12345");
					po.setUserName(rs.getCell(0, i).getContents());// 登录名
					po.setName(rs.getCell(1, i).getContents());// 姓名
					if (null == sexMap.get(rs.getCell(2, i).getContents())) {
						po.setSex(null);
					} else {
						po.setSex(Long.parseLong(sexMap.get(
								rs.getCell(2, i).getContents()).toString()));
					}
					po.setMobile(rs.getCell(3, i).getContents());
					po.setIdCard(rs.getCell(4, i).getContents());
					po.setNational(rs.getCell(5, i).getContents());// 民族
					if (null == degreeMap.get(rs.getCell(6, i).getContents())) {
						po.setDegree(null);
					} else {
						po.setDegree(degreeMap.get(
								rs.getCell(6, i).getContents()).toString());
					}
					if (null == degreeInMap.get(rs.getCell(7, i).getContents())) {
						po.setDegreeIn(null);
					} else {
						po.setDegreeIn(degreeInMap.get(
								rs.getCell(7, i).getContents()).toString());
					}
					po.setNativePlace(rs.getCell(8, i).getContents());// 籍贯
					po.setBirthday(rs.getCell(9, i).getContents());
					po.setPartyTime(rs.getCell(10, i).getContents().toString());
					if (null == oraganizationMap.get(rs.getCell(11, i)
							.getContents())) {// 所属组织
						po.setOrganizationId(null);
					} else {
						po.setOrganizationId(oraganizationMap.get(
								rs.getCell(11, i).getContents()).toString());
					}
					if (null == affairsWorkerMap.get(rs.getCell(12, i)
							.getContents())) {// 是否党务工作者
						po.setAffairsWorker(null);
					} else {
						po.setAffairsWorker(Long.parseLong(affairsWorkerMap
								.get(rs.getCell(12, i).getContents())
								.toString()));
					}
					if (null == volunteersMap.get(rs.getCell(13, i)
							.getContents())) {// 是否志愿者
						po.setVolunteers(null);
					} else {
						po.setVolunteers(Long.parseLong(volunteersMap.get(
								rs.getCell(13, i).getContents()).toString()));
					}
					if (null == behalfMap.get(rs.getCell(14, i).getContents())) {// 是否党代表
						po.setBehalf(null);
					} else {
						po.setBehalf(Long.parseLong(behalfMap.get(
								rs.getCell(14, i).getContents()).toString()));
					}
					if (null == hardPartyMembersMap.get(rs.getCell(15, i)
							.getContents())) {// 是否困难党员
						po.setHardPartyMembers(null);
					} else {
						po.setHardPartyMembers(Long
								.parseLong(hardPartyMembersMap.get(
										rs.getCell(15, i).getContents())
										.toString()));
					}
					po.setHomeAddress(rs.getCell(16, i).getContents());
					po.setNote(rs.getCell(17, i).getContents());
					if (null == isManagementMap.get(rs.getCell(18, i)
							.getContents())) {// 是否管理员
						po.setIsManagement(null);
					} else {
						po.setIsManagement(isManagementMap.get(
								rs.getCell(18, i).getContents()).toString());
					}
					po.setApplyForLeaveTime(rs.getCell(19, i).getContents());
					po.setLeaveTime(rs.getCell(20, i).getContents());
					po.setAuditUserName(rs.getCell(21, i).getContents());
					po.setCreateTime(new Date());
					po.setState(1L);
					try {
						partymenberService.savePartymenberPO(po);
						count++;
					} catch (Exception e) {
						impLog.append(e.getMessage());
					} catch (BaseException e) {
						impLog.append(e.getMessage() + "：【" + po.getUserName()+"】");
						return count;
					}
				}
			}
			return count;
		} catch (Exception e) {
			return count;
		} catch (BaseException e) {
			return count;
		}
	}
}
