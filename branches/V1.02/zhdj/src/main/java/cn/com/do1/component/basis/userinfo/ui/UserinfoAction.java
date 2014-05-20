package cn.com.do1.component.basis.userinfo.ui;

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
import cn.com.do1.component.basis.userinfo.model.TbUserInfoPO;
import cn.com.do1.component.basis.userinfo.service.IUserinfoService;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class UserinfoAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(UserinfoAction.class);
	private IUserinfoService userinfoService;
	private TbUserInfoPO tbUserInfoPO;
	private String ids[];
	private String id;
	private String page;

	public IUserinfoService getUserinfoService() {
		return userinfoService;
	}

	@Resource
	public void setUserinfoService(IUserinfoService userinfoService) {
		this.userinfoService = userinfoService;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "userName", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = userinfoService.searchUserinfo(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		// todo:完成新增的代码;
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		// todo:完成更新的代码;
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbUserInfoPO == null)
			tbUserInfoPO = new TbUserInfoPO();
		tbUserInfoPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPartyMenberInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbUserInfoPO);
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "操作成功", faileMsg = "操作失败"))
	public void ajaxChange() throws Exception, BaseException {
		TbUserInfoPO tempPO = userinfoService.searchByPk(TbUserInfoPO.class,
				tbUserInfoPO.getId());
		tempPO.setState(tbUserInfoPO.getState());
		this.userinfoService.updatePO(tempPO, false);
	}

	public void setTbUserInfoPO(TbUserInfoPO tbUserInfoPO) {
		this.tbUserInfoPO = tbUserInfoPO;
	}

	public TbUserInfoPO setTbUserInfoPO() {
		return this.tbUserInfoPO;
	}

	public void addTbUserInfoPO() {
		super.ajaxAdd(tbUserInfoPO);
	}

	public void updateTbUserInfoPO() {
		super.ajaxUpdate(tbUserInfoPO);
	}

	public void deleteTbUserInfoPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbUserInfoPO._setPKValue(id);
		super.ajaxDelete(tbUserInfoPO);
	}

	public void batchDeleteTbUserInfoPO() {
		super.ajaxBatchDelete(TbUserInfoPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		UserInfoVO userInfoVO = userinfoService.getUserInfoById(id);
		addJsonObj("userInfoVO", userInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	public TbUserInfoPO getTbUserInfoPO() {
		return this.tbUserInfoPO;
	}
}
