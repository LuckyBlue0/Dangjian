package cn.com.do1.component.vote.orgvote.ui;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteListPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.VoteResultVO;
import cn.com.do1.component.vote.orgvote.service.IOrgvoteService;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrgvoteAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(OrgvoteAction.class);
	private IOrgvoteService orgvoteService;
	private TbOrgVotePO tbOrgVotePO;
	private String ids[];
	private String id;
	private List<TbOrgVoteListPO> tbVoteOrgList; 

	public IOrgvoteService getOrgvoteService() {
		return orgvoteService;
	}

	@Resource
	public void setOrgvoteService(IOrgvoteService orgvoteService) {
		this.orgvoteService = orgvoteService;
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
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "voteOrg", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = orgvoteService.searchOrgvote(getSearchValue(), pager);
		addJsonFormatePager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxSave() throws Exception, BaseException {
		tbOrgVotePO.setCreateTime(new Date());
		if (AssertUtil.isEmpty(tbOrgVotePO.getPushStatus())) {
			tbOrgVotePO.setPushStatus(0l);
		} else {
			tbOrgVotePO.setPushTime(new Date());
		}
		orgvoteService.saveOrgVote(tbOrgVotePO,tbVoteOrgList);
	}

	/**
	 * 跳转至编辑页面
	 * @return
	 */
	public String toEdit(){
		try {
			tbVoteOrgList = orgvoteService.getOrgVoteListByOrgVoteId(id);
			tbOrgVotePO = orgvoteService.searchByPk(TbOrgVotePO.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "edit";
	}
	
	/**
	 * 跳转至详情页面
	 * @return
	 */
	public String view(){
		try {
			tbVoteOrgList = orgvoteService.getOrgVoteListByOrgVoteId(id);
			tbOrgVotePO = orgvoteService.searchByPk(TbOrgVotePO.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "view";
	}
	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		orgvoteService.updatePO(tbOrgVotePO, false);
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		orgvoteService.delOrgVote(ids);

	}

	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearchReason() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = orgvoteService.searchReason(getSearchValue(), pager);
		addJsonFormatePager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearchVoteResult() throws Exception, BaseException {
		VoteResultVO vo = orgvoteService.searchVoteResult(id);
		addJsonObj("resultVO", vo);

	}

	/**
	 * 民主评议投票
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "投票成功", faileMsg = "投票失败"))
	public void ajaxAddVoteResult() throws Exception, BaseException {
		TbMinzhuVoteResultPO po = new TbMinzhuVoteResultPO();
		LoginUserVO uservo = (LoginUserVO) getSessionObj().getAttribute("uservo");
		if (!AssertUtil.isEmpty(uservo)) {
			po.setUserId(uservo.getUserId());
		} else {
			throw new BaseException("请先登录");
		}
		orgvoteService.addVoteResult(po);
	}
	
	/**
	 * 发布或下架
	 */
	public void pushOrOut(){
		if(AssertUtil.isEmpty(tbOrgVotePO) && AssertUtil.isEmpty(tbOrgVotePO.getId())){
			setActionResult("-1","参数有误!");	
		}else{
			try {
				if(tbOrgVotePO.getPushStatus().equals(1l)){
					tbOrgVotePO.setPushTime(new Date());
				}
				this.orgvoteService.updatePO(tbOrgVotePO, false);
				setActionResult("0",tbOrgVotePO.getPushStatus() == 1 ? "发布成功" : "下架成功");
			} catch (SQLException e) {
				setActionResult("0",tbOrgVotePO.getPushStatus() == 1 ? "发布失败" : "下架失败");
			}
		}
		doJsonOut();
	}

	public void setTbOrgVotePO(TbOrgVotePO tbOrgVotePO) {
		this.tbOrgVotePO = tbOrgVotePO;
	}

	public TbOrgVotePO setTbOrgVotePO() {
		return this.tbOrgVotePO;
	}

	public void addTbOrgVotePO() {
		super.ajaxAdd(tbOrgVotePO);
	}

	public void updateTbOrgVotePO() {
		super.ajaxUpdate(tbOrgVotePO);
	}

	public void deleteTbOrgVotePO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbOrgVotePO._setPKValue(id);
		super.ajaxDelete(tbOrgVotePO);
	}

	public void batchDeleteTbOrgVotePO() {
		super.ajaxBatchDelete(TbOrgVotePO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbOrgVotePO xxPO = orgvoteService.searchByPk(TbOrgVotePO.class, id);
		addJsonFormateObj("tbOrgVotePO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	public TbOrgVotePO getTbOrgVotePO() {
		return this.tbOrgVotePO;
	}

	public List<TbOrgVoteListPO> getTbVoteOrgList() {
		return tbVoteOrgList;
	}

	public void setTbVoteOrgList(List<TbOrgVoteListPO> tbVoteOrgList) {
		this.tbVoteOrgList = tbVoteOrgList;
	}
}
