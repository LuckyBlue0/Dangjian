package cn.com.do1.component.partywork.ideologyreport.ui;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.ideologyreport.model.TbIdeologyReportPO;
import cn.com.do1.component.partywork.ideologyreport.service.IIdeologyreportService;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * All rights reserved. User: ${user}
 */
public class IdeologyreportAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(IdeologyreportAction.class);
	@Resource
	private IIdeologyreportService ideologyreportService;
	@Resource
	private IMembercenterService membercenterService;
	private TbIdeologyReportPO tbIdeologyReportPO;
	private String ids[];
	private String id;


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
		String userId =(String)getSearchValue().get("userId");
		if(userId != null){
			getSearchValue().remove("userId");
			getSearchValue().put("createUserId", userId);
		}
		pager = ideologyreportService.searchIdeologyreport(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			for (String id : ids) {
				this.ideologyreportService.delPO(this.ideologyreportService.searchByPk(TbIdeologyReportPO.class, id));
			}
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		IdeologyReportVO xxPO = ideologyreportService.searchById(id);
		addJsonObj("tbIdeologyReportPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "保存成功", faileMsg = "保存失败"))
	public void ajaxSave() throws Exception, BaseException {
		IUser user =(IUser)DqdpAppContext.getCurrentUser();
		tbIdeologyReportPO.setReviewTime(new Date());
		tbIdeologyReportPO.setReviewUserId(user.getPersonId());
		this.ideologyreportService.updatePO(tbIdeologyReportPO, false);
	}

	public void setTbIdeologyReportPO(TbIdeologyReportPO tbIdeologyReportPO) {
		this.tbIdeologyReportPO = tbIdeologyReportPO;
	}

	public TbIdeologyReportPO setTbIdeologyReportPO() {
		return this.tbIdeologyReportPO;
	}

	public TbIdeologyReportPO getTbIdeologyReportPO() {
		return this.tbIdeologyReportPO;
	}
}
