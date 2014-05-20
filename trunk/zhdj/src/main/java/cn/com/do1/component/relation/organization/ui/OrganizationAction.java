package cn.com.do1.component.relation.organization.ui;

import java.util.Date;

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
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.relation.organization.model.TbOrganizationTransferPO;
import cn.com.do1.component.relation.organization.service.IOrganizationService;
import cn.com.do1.component.relation.organization.vo.OrganizationTransferVO;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrganizationAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrganizationAction.class);
	private IOrganizationService organizationService;
	private IPartymenberService partymenberService;
	private TbOrganizationTransferPO tbOrganizationTransferPO;
	private OrganizationTransferVO organizationTransferVO;
	private String ids[];
	private String id;

	public IOrganizationService getOrganizationService() {
		return organizationService;
	}

	@Resource
	public void setOrganizationService(IOrganizationService organizationService) {
		this.organizationService = organizationService;
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
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "userName", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		pager = organizationService.searchOrganization(getSearchValue(), pager);
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

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxAudit() throws Exception, BaseException {
		try {
			TbOrganizationTransferPO po = new TbOrganizationTransferPO();
			OrganizationTransferVO vo = this.organizationService
					.getOrganizationTransferById(organizationTransferVO.getId());
			if ("0".equals(vo.getStatus())) {
				TbPartyMenberInfoPO partyMenber = partymenberService
						.getPartyMenberByUserName(DqdpAppContext
								.getCurrentUser().getUsername());
				if (null == partyMenber)
					partyMenber = new TbPartyMenberInfoPO();
				po.setId(organizationTransferVO.getId());
				po.setAuditTime(new Date());
				po.setStatus(organizationTransferVO.getStatus());
				po.setAuditUserInfo(partyMenber.getId());
				po.setAuditDesc(organizationTransferVO.getAuditDesc());
				// 如果关系转移类型是转出，审核为通过，则更新党员用户信息
				if ("2".equals(vo.getType())
						&& "1".equals(organizationTransferVO.getStatus())) {
					TbPartyMenberInfoPO partyMember = organizationService
							.searchByPk(TbPartyMenberInfoPO.class, vo
									.getUserId());
					partyMember.setUserType(3L);
					partyMember.setState(0L);
					partyMember.setApplyForLeaveTime(vo.getCreateTime());
					partyMember.setLeaveTime(po.getAuditTime());
					partyMember.setAuditUserId(partyMenber.getId());
					partyMember.setAuditUserName(partyMenber.getName());
					this.organizationService.updatePO(partyMember, false);
				}
				this.organizationService.updatePO(po, false);
				setActionResult("0", "操作成功");
			} else if ("1".equals(vo.getStatus())) {
				setActionResult("1000", "该申请已被审核");
			} else if ("2".equals(vo.getStatus())) {
				setActionResult("2000", "该申请已被退审");
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
		// 完成批量更新的代码
	}

	public void setTbOrganizationTransferPO(
			TbOrganizationTransferPO tbOrganizationTransferPO) {
		this.tbOrganizationTransferPO = tbOrganizationTransferPO;
	}

	public TbOrganizationTransferPO setTbOrganizationTransferPO() {
		return this.tbOrganizationTransferPO;
	}

	public void addTbOrganizationTransferPO() {
		super.ajaxAdd(tbOrganizationTransferPO);
	}

	public void updateTbOrganizationTransferPO() {
		super.ajaxUpdate(tbOrganizationTransferPO);
	}

	public void deleteTbOrganizationTransferPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbOrganizationTransferPO._setPKValue(id);
		super.ajaxDelete(tbOrganizationTransferPO);
	}

	public void batchDeleteTbOrganizationTransferPO() {
		super.ajaxBatchDelete(TbOrganizationTransferPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxViewTransferOut() throws Exception, BaseException {
		OrganizationTransferVO organizationTransferVO = organizationService
				.getOrganizationTransferById(id);
		addJsonObj("organizationTransferVO", organizationTransferVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	@JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxViewTransferInto() throws Exception, BaseException {
		TbOrganizationTransferPO xxPO = organizationService.searchByPk(
				TbOrganizationTransferPO.class, id);
		addJsonFormateObj("tbOrganizationTransferPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	public TbOrganizationTransferPO getTbOrganizationTransferPO() {
		return this.tbOrganizationTransferPO;
	}

	public OrganizationTransferVO getOrganizationTransferVO() {
		return organizationTransferVO;
	}

	public void setOrganizationTransferVO(
			OrganizationTransferVO organizationTransferVO) {
		this.organizationTransferVO = organizationTransferVO;
	}
}
