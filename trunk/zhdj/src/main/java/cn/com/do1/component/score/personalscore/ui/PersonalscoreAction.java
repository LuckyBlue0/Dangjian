package cn.com.do1.component.score.personalscore.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreInfoVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PersonalscoreAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PersonalscoreAction.class);
	private IPersonalscoreService personalscoreService;
	private TbPersonalScoreInfoPO tbPersonalScoreInfoPO;
	private String ids[];
	private String id;

	public IPersonalscoreService getPersonalscoreService() {
		return personalscoreService;
	}

	@Resource
	public void setPersonalscoreService(
			IPersonalscoreService personalscoreService) {
		this.personalscoreService = personalscoreService;
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
		pager = personalscoreService.searchPersonalscore(getSearchValue(),
				pager);
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
		pager = personalscoreService.searchPersonalscoreInfo(getSearchValue(),
				pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		tbPersonalScoreInfoPO.setId(UUID.randomUUID().toString());
		tbPersonalScoreInfoPO.setGetTime(new Date());
		this.personalscoreService.insertPO(tbPersonalScoreInfoPO, true);
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		this.personalscoreService.updatePO(tbPersonalScoreInfoPO, false);
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbPersonalScoreInfoPO == null)
			tbPersonalScoreInfoPO = new TbPersonalScoreInfoPO();
		tbPersonalScoreInfoPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPersonalScoreInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbPersonalScoreInfoPO);
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "个人积分"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("姓名,累计积分,总排名,所属组织,支部内排名,星级\r\n");// CSV默认是逗号","分隔单元格。这里是表头

		List<PersonalScoreVO> list = personalscoreService
				.getPersonalScore(getSearchValue());// 取出的数据
		PersonalScoreVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getName() + "," + vo.getAccumulativeScore() + ","
					+ vo.getRanking() + "," + vo.getOrganizationName() + ","
					+ vo.getBranchRanking() + "," + vo.getLeaveDesc() + "\r\n");
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
		String fileName = "个人积分明细"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("积分类型,获得积分,获得时间,获得积分说明\r\n");// CSV默认是逗号","分隔单元格。这里是表头

		List<PersonalScoreInfoVO> list = personalscoreService
				.getPersonalScoreInfo(getSearchValue());// 取出的数据
		PersonalScoreInfoVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getScoreTypeDesc() + "," + vo.getScore() + ","
					+ vo.getGetTime() + "," + vo.getScoreDesc() + "\r\n");
		}
		bw.flush();
		bw.close();
		osw.close();
		out.close();
		addJsonObj("filePath", filePath);
	}

	public void setTbPersonalScoreInfoPO(
			TbPersonalScoreInfoPO tbPersonalScoreInfoPO) {
		this.tbPersonalScoreInfoPO = tbPersonalScoreInfoPO;
	}

	public TbPersonalScoreInfoPO setTbPersonalScoreInfoPO() {
		return this.tbPersonalScoreInfoPO;
	}

	public void addTbPersonalScoreInfoPO() {
		super.ajaxAdd(tbPersonalScoreInfoPO);
	}

	public void updateTbPersonalScoreInfoPO() {
		super.ajaxUpdate(tbPersonalScoreInfoPO);
	}

	public void deleteTbPersonalScoreInfoPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbPersonalScoreInfoPO._setPKValue(id);
		super.ajaxDelete(tbPersonalScoreInfoPO);
	}

	public void batchDeleteTbPersonalScoreInfoPO() {
		super.ajaxBatchDelete(TbPersonalScoreInfoPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		PersonalScoreInfoVO personalScoreInfoVO = personalscoreService
				.getPersonalScoreInfoByid(id);
		addJsonObj("personalScoreInfoVO", personalScoreInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	@JSONOut(catchException = @CatchException(errCode = "1015", successMsg = "查询成功", faileMsg = "查询失败"))
	public void getPersonalScoreByUserId() throws Exception, BaseException {
		PersonalScoreVO personalScoreVO = personalscoreService
				.getPersonalScoreByUserId(id);
		addJsonObj("personalScoreVO", personalScoreVO);
	}

	public TbPersonalScoreInfoPO getTbPersonalScoreInfoPO() {
		return this.tbPersonalScoreInfoPO;
	}

	public void downloadFile() {
		String filePath = getSearchValue().get("filePath").toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = null;
		InputStream is = null;
		System.out.println("开始输出要下载的文件...");
		String fileName = "";
		File file = new File(filePath);
		try {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
			is = new FileInputStream(file);
			os = response.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		// 设置响应头和保存文件名
		// response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName + "\"");
		// 写出流信息
		int b = 0;
		byte[] buffer = new byte[8192];
		try {
			while ((b = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, b);
			}
			os.flush();
			is.close();
			os.close();
			System.out.println("文件下载完毕.");
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("下载文件失败!");
		}
	}
}
