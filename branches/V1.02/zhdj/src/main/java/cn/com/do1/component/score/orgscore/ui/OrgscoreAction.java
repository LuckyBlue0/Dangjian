package cn.com.do1.component.score.orgscore.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.orgscore.service.IOrgscoreService;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreInfoVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrgscoreAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrgscoreAction.class);
	private IOrgscoreService orgscoreService;
	private TbOrganizationScoreInfoPO tbOrganizationScoreInfoPO;
	private String ids[];
	private String id;

	public IOrgscoreService getOrgscoreService() {
		return orgscoreService;
	}

	@Resource
	public void setOrgscoreService(IOrgscoreService orgscoreService) {
		this.orgscoreService = orgscoreService;
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
			@SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
			@SearchValueType(name = "testNumber", type = "number"),
			@SearchValueType(name = "testString", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = orgscoreService.searchOrgscore(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	/**
	 * 详情列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = {
			@SearchValueType(name = "startTime", type = "date", format = "yyyy-MM-dd"),
			@SearchValueType(name = "endTime", type = "date", format = "yyyy-MM-dd") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxInfo() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = orgscoreService.searchOrgscoreInfo(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		try {
			tbOrganizationScoreInfoPO.setId(UUID.randomUUID().toString());
			tbOrganizationScoreInfoPO.setGetTime(new Date());
			tbOrganizationScoreInfoPO.setScoreFrom("管理员添加");
			tbOrganizationScoreInfoPO.setRecordFrom(DqdpAppContext
					.getCurrentUser().getUsername());
			orgscoreService.saveOrgscoreInfoPO(tbOrganizationScoreInfoPO);
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
		this.orgscoreService.updatePO(tbOrganizationScoreInfoPO, false);
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbOrganizationScoreInfoPO == null)
			tbOrganizationScoreInfoPO = new TbOrganizationScoreInfoPO();
		tbOrganizationScoreInfoPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPersonalScoreInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbOrganizationScoreInfoPO);
		}
	}

	public void setTbOrganizationScoreInfoPO(
			TbOrganizationScoreInfoPO tbOrganizationScoreInfoPO) {
		this.tbOrganizationScoreInfoPO = tbOrganizationScoreInfoPO;
	}

	public TbOrganizationScoreInfoPO setTbOrganizationScoreInfoPO() {
		return this.tbOrganizationScoreInfoPO;
	}

	public void addTbOrganizationScoreInfoPO() {
		super.ajaxAdd(tbOrganizationScoreInfoPO);
	}

	public void updateTbOrganizationScoreInfoPO() {
		super.ajaxUpdate(tbOrganizationScoreInfoPO);
	}

	public void deleteTbOrganizationScoreInfoPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbOrganizationScoreInfoPO._setPKValue(id);
		super.ajaxDelete(tbOrganizationScoreInfoPO);
	}

	public void batchDeleteTbOrganizationScoreInfoPO() {
		super.ajaxBatchDelete(TbOrganizationScoreInfoPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		OrganizationScoreInfoVO organizationScoreInfoVO = orgscoreService
				.getOrgScoreById(id);
		addJsonObj("organizationScoreInfoVO", organizationScoreInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	@JSONOut(catchException = @CatchException(errCode = "1015", successMsg = "查询成功", faileMsg = "查询失败"))
	public void getOrgScoreByOrgId() throws Exception, BaseException {
		OrganizationScoreVO organizationScoreVO = orgscoreService
				.getOrgScoreByOrgId(id);
		addJsonObj("organizationScoreVO", organizationScoreVO);
	}

	@JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "组织积分"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("名次,组织名称,累计积分,星级\r\n");// CSV默认是逗号","分隔单元格。这里是表头

		List<OrganizationScoreVO> list = orgscoreService
				.getOrgScore(getSearchValue());// 取出的数据
		OrganizationScoreVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getRanking() + "," + vo.getOrganizationName() + ","
					+ vo.getAccumulativeScore() + "," + vo.getLeaveDesc()
					+ "\r\n");
		}
		bw.flush();
		bw.close();
		osw.close();
		out.close();
		addJsonObj("filePath", filePath);
	}

	@SearchValueTypes(nameFormat = "false", value = {
			@SearchValueType(name = "startTime", type = "date", format = "yyyy-MM-dd"),
			@SearchValueType(name = "endTime", type = "date", format = "yyyy-MM-dd") })
	@JSONOut(catchException = @CatchException(errCode = "1007", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExportInfo() throws Exception, BaseException {
		String fileName = "组织积分明细"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("积分类型,分数,获得时间,获得积分说明\r\n");// CSV默认是逗号","分隔单元格。这里是表头

		List<OrganizationScoreInfoVO> list = orgscoreService
				.getOrgScoreInfo(getSearchValue());// 取出的数据
		OrganizationScoreInfoVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getScoreTypeDesc() + "," + vo.getScore() + ","
					+ vo.getGetTime() + "," + vo.getNote() + "\r\n");
		}
		bw.flush();
		bw.close();
		osw.close();
		out.close();
		addJsonObj("filePath", filePath);
	}

	public TbOrganizationScoreInfoPO getTbOrganizationScoreInfoPO() {
		return this.tbOrganizationScoreInfoPO;
	}
}
