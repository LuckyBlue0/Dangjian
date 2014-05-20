package cn.com.do1.component.vote.orgvote.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

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
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.DateTimeUtil;
import cn.com.do1.component.vote.orgvote.model.AddVoteRequest;
import cn.com.do1.component.vote.orgvote.model.OrgVoteListVO;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteListPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteVO;
import cn.com.do1.component.vote.orgvote.model.VoteResultResponse;
import cn.com.do1.component.vote.orgvote.service.IOrgvoteService;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrgvoteInterfaceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(OrgvoteInterfaceAction.class);
	private IOrgvoteService orgvoteService;
	private TbOrgVotePO tbOrgVotePO;
	private String ids[];
	private String id;
	private String requestJson;

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
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgVoteList() throws Exception, BaseException {
		logger.debug("orgVoteList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"voteUserId","pageIndex","pageSize"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String voteUserId = request.getVoteUserId();
		if (AssertUtil.isEmpty(voteUserId)) {
			throw new BaseException("1002", "voteUserId参数不能为空!");
		}
		int pageIndex = Integer.parseInt(request.getPageIndex());
		int pageSize =Integer.parseInt(request.getPageSize());
        if (pageSize == 0) {
			pageSize = 10;
		}
		Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
		pager.setCurrentPage(pageIndex);
		getSearchValue().put("userId", voteUserId);
		pager = orgvoteService.searchOrgVotePages(getSearchValue(), pager);
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
		addJsonObj("currentPage", pager.getCurrentPage());
		addJsonObj("totalPage", pager.getTotalPages());
		addJsonObj("totalRows", pager.getTotalRows());
	}

	/**
	 * 评议机关列表
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void appraisalOrgVoteList() throws Exception, BaseException {
		logger.debug("appraisalOrgVoteList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"voteUserId","voteId","pageIndex","pageSize","status"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String voteUserId = request.getVoteUserId();
		String voteId = request.getVoteId();
		String status = request.getStatus();
		if (AssertUtil.isEmpty(voteUserId)) {
			throw new BaseException("1002", "voteUserId参数不能为空!");
		}
		if (AssertUtil.isEmpty(voteId)) {
			throw new BaseException("1002", "voteId参数不能为空!");
		}
		if (AssertUtil.isEmpty(status)) {
			throw new BaseException("1002", "status参数不能为空!");
		}
		int pageIndex = Integer.parseInt(request.getPageIndex());
		int pageSize =Integer.parseInt(request.getPageSize());
        if (pageSize == 0) {
			pageSize = 10;
		}
        
		Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
		pager.setCurrentPage(pageIndex);
		if(status.equals("0")){
			
		}else{
			getSearchValue().put("userId", voteUserId);
		}
		getSearchValue().put("status", status);
		getSearchValue().put("orgVoteId", voteId);
		pager = orgvoteService.searchOrgVoteForInterface(getSearchValue(), pager);
		if (!AssertUtil.isEmpty(pager.getPageData()) && status.equals("0")) {
			List<OrgVoteListVO> result = new ArrayList<OrgVoteListVO>();
			List<OrgVoteListVO> list = (List<OrgVoteListVO>) pager.getPageData();
			for (OrgVoteListVO po : list) {
				if (orgvoteService.checkUser(po.getId(), voteUserId)) {
					po.setIsVote("0");
				} else {
					po.setIsVote("1");
				}
				result.add(po);
			}
			pager.setPageData(result);
		}
		//未评议数量或已评议数量
		int nonCount = orgvoteService.getOrgVoteCount(1, voteId, voteUserId);
		addJsonObj("nonCount", nonCount);
		int alreadyCount = orgvoteService.getOrgVoteCount(2, voteId, voteUserId);
		addJsonObj("alreadyCount",alreadyCount);
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
		addJsonObj("currentPage", pager.getCurrentPage());
		addJsonObj("totalPage", pager.getTotalPages());
		addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 查看本人投票内容
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void getVoteContent() throws Exception, BaseException {
		logger.debug("getVoteContent requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"id","userId"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String userId = request.getUserId();// 投票用户id
		String id = request.getId();// 关联被投票的机关
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("1002", "userId参数不能为空!");
		}
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("1002", "id参数不能为空!");
		}
		TbMinzhuVoteResultPO po = this.orgvoteService.getOrgVoteResultByVoteIdAndUserId(id, userId);
		VoteResultResponse response = new VoteResultResponse();
		if(po != null){
			response.setVoteTime(DateTimeUtil.date2String(po.getVoteTime(),"yyyy-MM-dd HH:mm:ss"));
			response.setReason(po.getReason());
			
			if(po.getResult1() == 1){
				response.setVoteResult("1");
			}else if(po.getResult2() == 1){
				response.setVoteResult("2");
			}else if(po.getResult3() == 1){
				response.setVoteResult("3");
			}else if(po.getResult4() == 1){
				response.setVoteResult("4");
			}
		}
		addJsonObj("voteContent", response);
	}

	/**
	 * 民主评议投票
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "投票成功", faileMsg = "投票失败"))
	public void addVoteResult() throws Exception, BaseException {
		logger.debug("addVoteResult requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"id","userId","voteNum","reason" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String userId = request.getUserId();// 投票用户id
		String id = request.getId();// 关联被投票的机关
		String voteNum = request.getVoteNum();// 投票选项
		String reason = request.getReason();// 不满意理由
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		if (AssertUtil.isEmpty(voteNum)) {
			throw new BaseException("9002", "voteNum参数不能为空");
		}else{
			if(voteNum.equals("4") && AssertUtil.isEmpty(reason)){
				throw new BaseException("9002", "reason参数不能为空");
			}
		}
		TbMinzhuVoteResultPO po = new TbMinzhuVoteResultPO();
		po.setUserId(userId);
		po.setVoteId(id);
		if ("1".equals(voteNum)) {
			po.setResult1(1l);
		} else {
			po.setResult1(0l);
		}
		if ("2".equals(voteNum)) {
			po.setResult2(1l);
		} else {
			po.setResult2(0l);
		}
		if ("3".equals(voteNum)) {
			po.setResult3(1l);
		} else {
			po.setResult3(0l);
		}
		if ("4".equals(voteNum)) {
			po.setResult4(1l);
		} else {
			po.setResult4(0l);
		}
		if (!AssertUtil.isEmpty(reason)) {
			po.setReason(reason);
		}
		orgvoteService.addVoteResult(po);
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

	public TbOrgVotePO getTbOrgVotePO() {
		return this.tbOrgVotePO;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	public static Logger getLogger() {
		return logger;
	}

}
