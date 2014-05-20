package cn.com.do1.component.vote.orgvote.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.vote.orgvote.dao.IOrgvoteDAO;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteListPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteVO;
import cn.com.do1.component.vote.orgvote.model.VoteResultVO;
import cn.com.do1.component.vote.orgvote.service.IOrgvoteService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("orgvoteService")
public class OrgvoteServiceImpl extends BaseService implements IOrgvoteService {
    private final static transient Logger logger = LoggerFactory.getLogger(OrgvoteServiceImpl.class);
    private final static List<String> idKey = Collections.synchronizedList(new ArrayList<String>());
    private IOrgvoteDAO orgvoteDAO;
    @Resource
    private IScoreruleService scoreruleService;
    @Resource
    public void setOrgvoteDAO(IOrgvoteDAO orgvoteDAO) {
        this.orgvoteDAO = orgvoteDAO;
        setDAO(orgvoteDAO);
    }

    public Pager searchOrgvote(Map searchMap,Pager pager) throws Exception, BaseException {
        return orgvoteDAO.searchOrgvote(searchMap,pager);
    }
    public Pager searchOrgVoteForInterface(Map searchMap,Pager pager) throws Exception, BaseException {
        return orgvoteDAO.searchOrgVoteForInterface(searchMap,pager);
    }
 
    public Pager searchReason(Map searchMap, Pager pager) throws Exception, BaseException{
    	return orgvoteDAO.searchReason(searchMap, pager);
    }
    public VoteResultVO searchVoteResult(String voteId)throws Exception, BaseException{
    	return orgvoteDAO.searchVoteResult(voteId);
    }
    public void addVoteResult(TbMinzhuVoteResultPO po)throws Exception, BaseException{//必须带有用户id
    	TbOrgVoteListPO orgVoteListPO = orgvoteDAO.searchByPk(TbOrgVoteListPO.class, po.getVoteId());
    	TbOrgVotePO orgVotePO = orgvoteDAO.searchByPk(TbOrgVotePO.class, orgVoteListPO.getOrgVoteId());
		Date currentTime = new Date();
		Date startTime = orgVotePO.getStartTime();
		Date endTime = orgVotePO.getEndTime();
		if(currentTime.getTime() < startTime.getTime()){
			throw new BaseException("抱歉,评选活动还未开始,暂时不能投票!");
		}
		if(currentTime.getTime() > endTime.getTime()){
			throw new BaseException("抱歉,评选活动已经结束,投票已经截止!");
		}
		
    	String id = po.getUserId();
		String key = id + "|" + po.getVoteId();
		logger.info("该用户(" + id + ")开始投票(" +po.getVoteId() + ")...");
//		if (checkIDKeyContain(key)) {
//			logger.info("该用户(" + id + ")已经在投票(" + po.getVoteId()+ ")...");
//			throw new BaseException("用户已投票");
//		}
//		idKey.add(key);
		boolean result=this.checkUser(po.getVoteId(),po.getUserId());
    	if(result){
    		throw new BaseException("用户已投票");
    	}else{
    		po.setVoteTime(new Date());
    		TbPartyMenberInfoPO userPO=this.searchByPk(TbPartyMenberInfoPO.class, po.getUserId());
    		if(!AssertUtil.isEmpty(userPO)){
    			po.setVoteUserType(1l);
    		}else{
    			po.setVoteUserType(0l);
    		}
    		this.insertPO(po, true);
    		
    		TbPersonalScoreInfoPO perScore = new TbPersonalScoreInfoPO();
        	ExItemObj dic = DictOperater.getItem("personalScore", "9");//参加机关民主评议
        	perScore.setId(UUID.randomUUID().toString().toLowerCase());
        	perScore.setUserId(po.getId());
        	perScore.setScoreType("9");
    		String title = "";
    		TbOrgVotePO vote = this.searchByPk(TbOrgVotePO.class, po.getVoteId());
    		if(null != vote){
    			title = vote.getTopic();
    		}
    		TbScoreRulePO scoreRule = this.scoreruleService.searchByType(dic.getFsDictItemId());
    		if(null != scoreRule){
    			perScore.setScore(Long.parseLong(scoreRule.getScoreValue()));
    		}
    		perScore.setGetTime(new Date());
    		perScore.setScoreDesc("参加机关民主评议:《"+title+"》获得积分");
    		this.insertPO(perScore, true);
    	}
    }
    @Override
    public boolean checkUser(String voteId,String userId)throws Exception, BaseException{
    	String sql="select count(*) from tb_minzhu_vote_result where vote_id = :voteId and user_id = :userId";
    	orgvoteDAO.preparedSql(sql);
    	orgvoteDAO.setPreValue("voteId", voteId);
    	orgvoteDAO.setPreValue("userId", userId);
    	if(orgvoteDAO.executeCount()>=1){
    		return true;
    	}else{
    		return false;
    	}
    }
    private synchronized Boolean checkIDKeyContain(String idKey) {
		return OrgvoteServiceImpl.idKey.contains(idKey);
	}
    public void delOrgVote(String[] ids)throws Exception, BaseException{
    	if(!AssertUtil.isEmpty(ids)){
 	   	   for(String id:ids){
 	   		   TbOrgVotePO xxPO = this.searchByPk(TbOrgVotePO.class, id);
 	   		   this.delPO(xxPO);
 	   		   this.orgvoteDAO.deleteByField(new TbOrgVoteListPO(), "orgVoteId",id);
 	   		   List<TbMinzhuVoteResultPO> resultPos=this.searchResultPO(id);
 	   		   if(!AssertUtil.isEmpty(resultPos)){
 	   			 String[] delIds=new String[resultPos.size()] ;
 	   			 for(int i=0;i<resultPos.size();i++){
 	   				delIds[i]=resultPos.get(i).getId();
 	   			 }
 	   			 this.batchDel(TbMinzhuVoteResultPO.class, delIds);
 	   		   }
 	   	   }
 	      }
    }
    private List<TbMinzhuVoteResultPO> searchResultPO(String voteId)throws Exception, BaseException{
    	String sql="select * from tb_minzhu_vote_result where vote_id = :voteId";
    	orgvoteDAO.preparedSql(sql);
    	orgvoteDAO.setPreValue("voteId", voteId);
    	return orgvoteDAO.getList(TbMinzhuVoteResultPO.class);
    }

	@Override
	public TbMinzhuVoteResultPO getOrgVoteResultByVoteIdAndUserId(String voteId, String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return orgvoteDAO.getOrgVoteResultByVoteIdAndUserId(voteId, userId);
	}

	@Override
	public void saveOrgVote(TbOrgVotePO tbOrgVotePO, List<TbOrgVoteListPO> tbVoteOrgList) throws Exception, BaseException {
		// TODO Auto-generated method stub
		String orgVoteId = null;
		if(AssertUtil.isEmpty(tbOrgVotePO.getId())){
			orgVoteId = UUID.randomUUID().toString().toLowerCase();
			tbOrgVotePO.setId(orgVoteId);
			orgvoteDAO.insert(tbOrgVotePO);
		}else{
			orgVoteId = tbOrgVotePO.getId();
			orgvoteDAO.update(tbOrgVotePO,false);
		}
		
		this.orgvoteDAO.deleteByField(new TbOrgVoteListPO(), "orgVoteId",orgVoteId);
		
		if(!AssertUtil.isEmpty(tbVoteOrgList)){
			List<TbOrgVoteListPO> result = new ArrayList<TbOrgVoteListPO>();
			for(TbOrgVoteListPO po : tbVoteOrgList){
				if(po == null){
					continue;
				}
				po.setId(UUID.randomUUID().toString().toLowerCase());
				po.setOrgVoteId(orgVoteId);
				po.setCreateTime(new Date());
				result.add(po);
			}
			orgvoteDAO.execBatchInsert(result);
		}
	}
	
	private List<TbOrgVoteListPO> getOrgVoteList(String orgVoteId) throws SQLException{
    	String sql="select * from TB_ORG_VOTE_LIST t where t.org_vote_id = :orgVoteId";
    	orgvoteDAO.preparedSql(sql);
    	orgvoteDAO.setPreValue("orgVoteId", orgVoteId);
    	return orgvoteDAO.getList(TbOrgVoteListPO.class);
	}

	@Override
	public List<TbOrgVoteListPO> getOrgVoteListByOrgVoteId(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return getOrgVoteList(id);
	}

	@Override
	public Pager searchOrgVotePages(Map searchMap, Pager pager) throws Exception, BaseException {
		String userId = searchMap.get("userId").toString();
		searchMap.remove("userId");
		Pager page = orgvoteDAO.searchOrgVotePages(searchMap,pager);
		if (!AssertUtil.isEmpty(page) && !AssertUtil.isEmpty(page.getPageData())) {
			for (TbOrgVoteVO orgVote : (ArrayList<TbOrgVoteVO>) page.getPageData()) {
				// 未评议数量
				int nonCount = getOrgVoteCount(1, orgVote.getId(), userId);
				orgVote.setNonCount(nonCount + "");

				// 已评议数量
				int alreadyCount = getOrgVoteCount(2, orgVote.getId(), userId);
				orgVote.setAlreadyCount(alreadyCount + "");

				//  被评议对象数量
				int orgVoteCount = getOrgVoteListByOrgVoteId(orgVote.getId()).size();
				orgVote.setOrgVoteCount(orgVoteCount + "");
			}
		}
		return page;
	}
	
	public int getOrgVoteCount(int type,String orgVoteId,String userId) throws SQLException{
		String sql = "";
		switch(type){
		case 1://未评议
			sql = "select count(*) from tb_org_vote_list t where t.ORG_VOTE_ID= :orgVoteId " +
			"and t.id not in (select m.vote_id from tb_minzhu_vote_result m where m.user_id = :userId) order by create_time desc";
			break;
		case 2://已评议
			sql = "select count(*) from tb_org_vote_list t where t.ORG_VOTE_ID= :orgVoteId " +
			"and t.id in (select m.vote_id from tb_minzhu_vote_result m where m.user_id = :userId) order by create_time desc";
			break;
		}
		orgvoteDAO.preparedSql(sql);
		orgvoteDAO.setPreValue("orgVoteId", orgVoteId);
		orgvoteDAO.setPreValue("userId", userId);
		return orgvoteDAO.executeCount();
	}
    
}
