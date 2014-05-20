package cn.com.do1.component.vote.partymembervote.ui;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
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
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.vote.partymembervote.model.TbPartyMemberVotePO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberResultPO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;
import cn.com.do1.component.vote.partymembervote.service.IPartymembervoteService;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartymembervoteAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(PartymembervoteAction.class);
	private IPartymembervoteService partymembervoteService;
	private TbPartyMemberVotePO tbPartyMemberVotePO;
	private String ids[];
	private String id;
	private TbVoteMemberPO tbVoteMemberPO;
	private List<TbVoteMemberPO> tbVoteMemberPOList;

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
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "voteTopic", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = partymembervoteService.searchPartymembervote(getSearchValue(), pager);
		if (!AssertUtil.isEmpty(pager.getPageData())) {
			List<TbPartyMemberVotePO> list = (List<TbPartyMemberVotePO>) pager.getPageData();
			for (TbPartyMemberVotePO po : list) {
				Long startTime = po.getStartTime().getTime();
				Long endTime = po.getEndTime().getTime();
				Long nowTime = new Date().getTime();
				if (nowTime < startTime) {
					po.setVoteStatus(0l);
				} else if (nowTime > startTime && nowTime < endTime) {
					po.setVoteStatus(1l);
				} else if (nowTime > endTime) {
					po.setVoteStatus(2l);
				}
			}
			pager.setPageData(list);
		}
		addJsonFormatePager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		tbPartyMemberVotePO.setCreateTime(new Date());
		tbPartyMemberVotePO.setPushStatus(0l);
		Long startTime = tbPartyMemberVotePO.getStartTime().getTime();
		Long endTime = tbPartyMemberVotePO.getEndTime().getTime();
		Long nowTime = new Date().getTime();
		if (nowTime < startTime) {
			tbPartyMemberVotePO.setVoteStatus(0l);
		} else if (nowTime > startTime && nowTime < endTime) {
			tbPartyMemberVotePO.setVoteStatus(1l);
		} else if (nowTime > endTime) {
			tbPartyMemberVotePO.setVoteStatus(2l);
		}
		tbPartyMemberVotePO.setId(UUID.randomUUID().toString());
		addJsonObj("id", tbPartyMemberVotePO.getId());
		addJsonObj("voteLimit", tbPartyMemberVotePO.getVoteLimit());
		partymembervoteService.insertPO(tbPartyMemberVotePO, false);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAddPartymember() throws Exception, BaseException {
		if(!AssertUtil.isEmpty(tbVoteMemberPOList)){
			HashMap<String,String> userMap=new HashMap<String, String>();
			for(TbVoteMemberPO po : tbVoteMemberPOList){
				if(po == null){
					continue;
				}
				if(userMap.containsKey(po.getUserId())){
					throw new BaseException("-1", "选择的党员有重复,请重新选择");
				}
				userMap.put(po.getUserId(), po.getUserName());
			}
		}
		
		
		partymembervoteService.addPartymember(tbVoteMemberPOList);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "修改成功", faileMsg = "修改失败"))
	public void ajaxUpdatePartymember() throws Exception, BaseException {
		if(!AssertUtil.isEmpty(tbVoteMemberPOList)){
			HashMap<String,String> userMap=new HashMap<String, String>();
			for(TbVoteMemberPO po : tbVoteMemberPOList){
				if(po == null){
					continue;
				}
				if(userMap.containsKey(po.getUserId())){
					throw new BaseException("-1", "选择的党员有重复,请重新选择");
				}
				userMap.put(po.getUserId(), po.getUserName());
			}
		}
		partymembervoteService.updatePartymember(tbVoteMemberPOList);
	}

	public String ajaxTurnToAddMember() throws Exception, BaseException {
		List<TbVoteMemberPO> list = partymembervoteService.searchVoteMember(id);
		getReqeustObj().setAttribute("id", id);
		getReqeustObj().setAttribute("tbVoteMemberPOList", list);
		return "addPartymember";
	}

	public String ajaxTurnToEditMember() throws Exception, BaseException {
		List<TbVoteMemberPO> list = partymembervoteService.searchVoteMember(id);
		getReqeustObj().setAttribute("id", id);
		getReqeustObj().setAttribute("tbVoteMemberPOList", list);
		return "editPartymember";
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		partymembervoteService.updatePO(tbPartyMemberVotePO, false);
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearchVoteResult() throws Exception, BaseException {
		VoteMemberTotalResultVO resultvo = partymembervoteService.searchVoteTotalResult(id);
		List<VoteMemberResultVO> resultList = partymembervoteService.searchVoteResultList(id);
		addJsonObj("resultvo", resultvo);
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

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		partymembervoteService.delPartymemberVote(ids);
	}

	/**
	 * 优秀党员投票
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "投票成功", faileMsg = "投票失败"))
	public void ajaxAddVoteResult() throws Exception, BaseException {
		TbVoteMemberResultPO po = new TbVoteMemberResultPO();
		LoginUserVO uservo = (LoginUserVO) getSessionObj().getAttribute("uservo");
		if (!AssertUtil.isEmpty(uservo)) {
			po.setVoteUserId(uservo.getUserId());
			partymembervoteService.addVoteResult(po);
		} else {
			throw new BaseException("请先登录");
		}
	}
	
	/**
	 * 发布或下架
	 */
	public void pushOrOut(){
		if(AssertUtil.isEmpty(tbPartyMemberVotePO) && AssertUtil.isEmpty(tbPartyMemberVotePO.getId())){
			setActionResult("-1","参数有误!");	
		}else{
			try {
				if(tbPartyMemberVotePO.getPushStatus().equals(1l)){
					tbPartyMemberVotePO.setPushTime(new Date());
				}
				this.partymembervoteService.updatePO(tbPartyMemberVotePO, false);
				setActionResult("0",tbPartyMemberVotePO.getPushStatus() == 1 ? "发布成功" : "下架成功");
			} catch (SQLException e) {
				setActionResult("0",tbPartyMemberVotePO.getPushStatus() == 1 ? "发布失败" : "下架失败");
			}
		}
		doJsonOut();
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

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbPartyMemberVotePO xxPO = partymembervoteService.searchByPk(TbPartyMemberVotePO.class, id);
		addJsonFormateObj("tbPartyMemberVotePO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
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
}
