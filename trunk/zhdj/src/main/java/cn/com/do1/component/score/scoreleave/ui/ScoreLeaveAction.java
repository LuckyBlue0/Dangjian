package cn.com.do1.component.score.scoreleave.ui;

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
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.score.scoreleave.model.TbScoreLeavePO;
import cn.com.do1.component.score.scoreleave.service.IScoreLeaveService;
import cn.com.do1.component.score.scoreleave.vo.ScoreLeaveVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ScoreLeaveAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(ScoreLeaveAction.class);
	private IScoreLeaveService scoreLeaveService;
	private TbScoreLeavePO tbScoreLeavePO;
	private String ids[];
	private String id;

	public IScoreLeaveService getScoreLeaveService() {
		return scoreLeaveService;
	}

	@Resource
	public void setScoreLeaveService(IScoreLeaveService scoreLeaveService) {
		this.scoreLeaveService = scoreLeaveService;
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
	@SearchValueTypes(nameFormat = "false", value = {})
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = scoreLeaveService.searchScoreLeave(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		if (null != tbScoreLeavePO) {
			tbScoreLeavePO.setId(UUID.randomUUID().toString().toLowerCase());
			tbScoreLeavePO.setCreateTime(new Date());
			this.scoreLeaveService.insertPO(tbScoreLeavePO, true);
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		try {
			this.scoreLeaveService.updatePO(tbScoreLeavePO, false);
			setActionResult("0", "修改成功");
		} catch (Exception e) {
			setActionResult("1001", "修改失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbScoreLeavePO == null)
			tbScoreLeavePO = new TbScoreLeavePO();
		tbScoreLeavePO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbScoreLeavePO.class, ids);
		} else {
			super.ajaxDelete(tbScoreLeavePO);
		}
	}

	public void setTbScoreLeavePO(TbScoreLeavePO tbScoreLeavePO) {
		this.tbScoreLeavePO = tbScoreLeavePO;
	}

	public TbScoreLeavePO setTbScoreLeavePO() {
		return this.tbScoreLeavePO;
	}

	public void addTbScoreLeavePO() {
		super.ajaxAdd(tbScoreLeavePO);
	}

	public void updateTbScoreLeavePO() {
		super.ajaxUpdate(tbScoreLeavePO);
	}

	public void deleteTbScoreLeavePO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbScoreLeavePO._setPKValue(id);
		super.ajaxDelete(tbScoreLeavePO);
	}

	public void batchDeleteTbScoreLeavePO() {
		super.ajaxBatchDelete(TbScoreLeavePO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		ScoreLeaveVO scoreLeave = scoreLeaveService.getScoreLeaveById(id);
		addJsonObj("tbScoreLeavePO", scoreLeave);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	@JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "积分星级"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("星级名称,属性,分值区间\r\n");// CSV默认是逗号","分隔单元格。这里是表头

		List<ScoreLeaveVO> list = scoreLeaveService
				.getScoreLeaves(getSearchValue());// 取出的数据
		ScoreLeaveVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getName() + "," + vo.getTypeDesc() + ","
					+ vo.getScoreDesc() + "\r\n");
		}
		bw.flush();
		bw.close();
		osw.close();
		out.close();
		addJsonObj("filePath", filePath);
	}

	public TbScoreLeavePO getTbScoreLeavePO() {
		return this.tbScoreLeavePO;
	}
}
