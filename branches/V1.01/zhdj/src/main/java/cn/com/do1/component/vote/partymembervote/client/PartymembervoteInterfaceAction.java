package cn.com.do1.component.vote.partymembervote.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cn.com.do1.component.partywork.branch.vo.PartyMeetingListRequest;
import cn.com.do1.component.util.BaseEncryptionVO;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.vote.orgvote.model.AddVoteRequest;
import cn.com.do1.component.vote.partymembervote.model.TbPartyMemberVotePO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;
import cn.com.do1.component.vote.partymembervote.service.IPartymembervoteService;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartymembervoteInterfaceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(PartymembervoteInterfaceAction.class);
	private IPartymembervoteService partymembervoteService;
	private TbPartyMemberVotePO tbPartyMemberVotePO;
	private String ids[];
	private String id;
	private TbVoteMemberPO tbVoteMemberPO;
	private List<TbVoteMemberPO> tbVoteMemberPOList;
	private String requestJson;

	public IPartymembervoteService getPartymembervoteService() {
		return partymembervoteService;
	}

	@Resource
	public void setPartymembervoteService(IPartymembervoteService partymembervoteService) {
		this.partymembervoteService = partymembervoteService;
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
	 * 主题列表
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "voteTopic", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void partymemberVoteList() throws Exception, BaseException {
		logger.debug("partymemberVoteList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"keyword","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		int pageIndex = Integer.parseInt(request.getPageIndex());
		int pageSize =Integer.parseInt(request.getPageSize());
        if (pageSize == 0) {
			pageSize = 10;
		}
        String keyword = request.getKeyword();
        getSearchValue().put("voteTopic", "%"+keyword+"%");
		Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
		pager.setCurrentPage(pageIndex);
		pager = partymembervoteService.searchPartymembervoteForInterface(getSearchValue(), pager);
		if (!AssertUtil.isEmpty(pager.getPageData())) {
			List<VoteMemberTotalResultVO> list = (List<VoteMemberTotalResultVO>) pager.getPageData();
			for (VoteMemberTotalResultVO po : list) {
				Long startTime = DateUtil.parse(po.getStartTime(), "yyyy-MM-dd HH:mm:ss").getTime();
				Long endTime = DateUtil.parse(po.getEndTime(), "yyyy-MM-dd HH:mm:ss").getTime();
				Long nowTime = new Date().getTime();
				if (nowTime < startTime) {
					po.setVoteStatus("0");
				} else if (nowTime > startTime && nowTime < endTime) {
					po.setVoteStatus("1");
				} else if (nowTime > endTime) {
					po.setVoteStatus("2");
				}
				VoteMemberTotalResultVO resultvo = partymembervoteService.searchVoteCanyuTotal(po.getId());
				po.setTotalCount(resultvo.getTotalCount());
			}
			pager.setPageData(list);
		}
		addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
		addJsonObj("currentPage", pager.getCurrentPage());
		addJsonObj("totalPage", pager.getTotalPages());
		addJsonObj("totalRows", pager.getTotalRows());
	}

	/**
	 * 优秀党员列表
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "查询成功", faileMsg = "查询失败"))
	public void votePartyMemberList() throws Exception, BaseException {
		logger.debug("votePartyMemberList requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"id","voteUserId"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String voteId = request.getId();// 投票的主题
		String voteUserId = request.getVoteUserId();// 投票人ID
		if (AssertUtil.isEmpty(voteId)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		if (AssertUtil.isEmpty(voteUserId)) {
			throw new BaseException("9002", "voteUserId参数不能为空");
		}
		//候选党员列表
		List<TbVoteMemberVO> memberList = partymembervoteService.votePartyMemberList(voteId,voteUserId);
		
		//本次可以投票的总数量
		TbPartyMemberVotePO memberVotePO = partymembervoteService.searchByPk(TbPartyMemberVotePO.class, voteId);
		
		//投票人已经投过多少票
		int voteUserCount = partymembervoteService.checkUser(voteId, "",voteUserId);
		addJsonObj("toDoCount", Integer.parseInt(memberVotePO.getVoteLimit()) - voteUserCount);
		addJsonArray("pageData", memberList);
	}

	/**
	 * 查看投票结果
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchVoteResult() throws Exception, BaseException {
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		logger.debug("searchVoteResult requestJson>>>" + requestJson);
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		BaseEncryptionVO request = (BaseEncryptionVO) JSONObject.toBean(paramJson, BaseEncryptionVO.class);
		request = (BaseEncryptionVO) CommonAuthManage.authDigestRetObj(request, new String[] {"id"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		} 
		String voteId = request.getId();// 投票的主题id
		if (AssertUtil.isEmpty(voteId)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		VoteMemberTotalResultVO resultvo = partymembervoteService.searchVoteTotalResult(voteId);
		VoteMemberTotalResultVO result = partymembervoteService.searchVoteCanyuTotal(voteId);
		List<VoteMemberResultVO> resultList = partymembervoteService.searchVoteResultList(voteId);
		addJsonObj("result", result);
		if (!AssertUtil.isEmpty(resultList)) {
			int total = 0;
			if (!AssertUtil.isEmpty(resultvo.getTotalCount())) {
				total = Integer.valueOf(resultvo.getTotalCount());
			}
			for (VoteMemberResultVO vo : resultList) {
				if (AssertUtil.isEmpty(vo.getResult1())) {
					vo.setResult1("0");
				}
				if (total != 0) {
					DecimalFormat df = new DecimalFormat("#.00");
					vo.setResult2(String.valueOf(df.format(Double.valueOf(vo.getResult1()) / total * 100)) + "%");
				} else {
					vo.setResult2("0%");
				}
			}
		}
		addJsonArray("list", resultList);
	}

	/**
	 * 优秀党员投票
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "投票成功", faileMsg = "投票失败"))
	public void addVoteResult() throws Exception, BaseException {
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		logger.debug("addVoteResult requestJson>>>" + requestJson);
		AddVoteRequest request = (AddVoteRequest) JSONObject.toBean(paramJson, AddVoteRequest.class);
		request = (AddVoteRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"voteUserId","id","userIds"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String [] userIds = request.getUserIds();// 投票对象id
		String voteUserId = request.getVoteUserId();// 投票人id
		String voteId = request.getId();// 投票的主题id
		if (AssertUtil.isEmpty(userIds)) {
			throw new BaseException("9002", "userIds参数不能为空");
		}
		if (AssertUtil.isEmpty(voteId)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		if (AssertUtil.isEmpty(voteUserId)) {
			throw new BaseException("9002", "voteUserId参数不能为空");
		}
		partymembervoteService.batchAddVoteResult(request);

	}

	/**
	 * 优秀党员信息
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void viewPartyMember() throws Exception, BaseException {
		logger.debug("viewPartyMember requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] {"id","userId"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		String userId = request.getUserId();// 投票对象id
		String voteId = request.getId();// 投票的主题id
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(voteId)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		TbVoteMemberVO member = partymembervoteService.searchPartyMember(voteId, userId);
		addJsonObj("member", member);

	}

	public void setTbPartyMemberVotePO(TbPartyMemberVotePO tbPartyMemberVotePO) {
		this.tbPartyMemberVotePO = tbPartyMemberVotePO;
	}

	public TbPartyMemberVotePO setTbPartyMemberVotePO() {
		return this.tbPartyMemberVotePO;
	}

	public void addTbPartyMemberVotePO() {
		super.ajaxAdd(tbPartyMemberVotePO);
	}

	public void updateTbPartyMemberVotePO() {
		super.ajaxUpdate(tbPartyMemberVotePO);
	}

	public void deleteTbPartyMemberVotePO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbPartyMemberVotePO._setPKValue(id);
		super.ajaxDelete(tbPartyMemberVotePO);
	}

	public void batchDeleteTbPartyMemberVotePO() {
		super.ajaxBatchDelete(TbPartyMemberVotePO.class, ids);
	}

	public TbPartyMemberVotePO getTbPartyMemberVotePO() {
		return this.tbPartyMemberVotePO;
	}

	public void setTbVoteMemberPO(TbVoteMemberPO tbVoteMemberPO) {
		this.tbVoteMemberPO = tbVoteMemberPO;
	}

	public TbVoteMemberPO getTbVoteMemberPO() {
		return tbVoteMemberPO;
	}

	public void setTbVoteMemberPOList(List<TbVoteMemberPO> tbVoteMemberPOList) {
		this.tbVoteMemberPOList = tbVoteMemberPOList;
	}

	public List<TbVoteMemberPO> getTbVoteMemberPOList() {
		return tbVoteMemberPOList;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

}
