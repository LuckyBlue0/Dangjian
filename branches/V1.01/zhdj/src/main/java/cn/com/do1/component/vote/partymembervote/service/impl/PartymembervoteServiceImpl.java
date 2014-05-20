package cn.com.do1.component.vote.partymembervote.service.impl;

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
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.vote.orgvote.model.AddVoteRequest;
import cn.com.do1.component.vote.partymembervote.dao.IPartymembervoteDAO;
import cn.com.do1.component.vote.partymembervote.model.TbPartyMemberVotePO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberResultPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;
import cn.com.do1.component.vote.partymembervote.service.IPartymembervoteService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("partymembervoteService")
public class PartymembervoteServiceImpl extends BaseService implements IPartymembervoteService {
    private final static transient Logger logger = LoggerFactory.getLogger(PartymembervoteServiceImpl.class);
    private final static List<String> voteIdKey = Collections.synchronizedList(new ArrayList<String>());
    private IPartymembervoteDAO partymembervoteDAO;
    @Resource
    private IScoreruleService scoreruleService;
    @Resource
    public void setPartymembervoteDAO(IPartymembervoteDAO partymembervoteDAO) {
        this.partymembervoteDAO = partymembervoteDAO;
        setDAO(partymembervoteDAO);
    }

    public Pager searchPartymembervote(Map searchMap,Pager pager) throws Exception, BaseException {
        return partymembervoteDAO.searchPartymembervote( searchMap,pager);
    }
    public Pager searchPartymembervoteForInterface(Map searchMap,Pager pager) throws Exception, BaseException {
        return partymembervoteDAO.searchPartymembervoteForInterface( searchMap,pager);
    }

    public List<TbVoteMemberPO> searchVoteMember(String voteId)throws Exception, BaseException{
    	return partymembervoteDAO.searchVoteMember(voteId);
    }
    public void  addPartymember(List<TbVoteMemberPO> tbVoteMemberPOList)throws Exception, BaseException{
    	List<TbVoteMemberPO> tbVoteMemberPOList1=new ArrayList<TbVoteMemberPO>();
    	if(!AssertUtil.isEmpty(tbVoteMemberPOList)){
    		for(TbVoteMemberPO po:tbVoteMemberPOList){
    			if(po == null){
    				continue;
    			}
    			po.setId(UUID.randomUUID().toString());
    			tbVoteMemberPOList1.add(po);
    		}
    	}
    	if(tbVoteMemberPOList1 != null && tbVoteMemberPOList1.size()>0){
    		this.partymembervoteDAO.execBatchInsert(tbVoteMemberPOList1);
    	}
    }
    public void  updatePartymember(List<TbVoteMemberPO> tbVoteMemberPOList)throws Exception, BaseException{
    	List<TbVoteMemberPO> tbVoteMemberPOList1=new ArrayList<TbVoteMemberPO>();
    	if(!AssertUtil.isEmpty(tbVoteMemberPOList)){
    		String voteTopId = "";
    		for(TbVoteMemberPO po:tbVoteMemberPOList){
    			if(po == null){
    				continue;
    			}
    			voteTopId = po.getVoteTopicId();
    			if(AssertUtil.isEmpty(po.getId())){
    			    po.setId(UUID.randomUUID().toString());
    			    

    			}else{
    			}
    			tbVoteMemberPOList1.add(po);
    		}
    		
    		if(!AssertUtil.isEmpty(tbVoteMemberPOList1)){
    			this.partymembervoteDAO.deleteByField(new TbVoteMemberPO(), "voteTopicId", voteTopId);
    			this.partymembervoteDAO.execBatchInsert(tbVoteMemberPOList1);
    		}
    	}
    }
    public void delPartymemberVote(String[] ids)throws Exception, BaseException{
    	if(!AssertUtil.isEmpty(ids)){
  	   	   for(String id:ids){
  	   		   TbPartyMemberVotePO xxPO = this.searchByPk(TbPartyMemberVotePO.class, id);
  	   		   this.delPO(xxPO);
  	   		   List<TbVoteMemberPO> memberPos=this.searchMemberPO(id);
  	   		   if(!AssertUtil.isEmpty(memberPos)){
  	   			 String[] delIds=new String[memberPos.size()] ;
  	   			 for(int i=0;i<memberPos.size();i++){
  	   				delIds[i]=memberPos.get(i).getId();
  	   			 }
  	   			 this.batchDel(TbVoteMemberPO.class, delIds);
  	   		   }
  	   		List<TbVoteMemberResultPO> resultPos=this.searchResultPO(id);
	   		   if(!AssertUtil.isEmpty(resultPos)){
	   			 String[] delIds=new String[resultPos.size()] ;
	   			 for(int i=0;i<resultPos.size();i++){
	   				delIds[i]=resultPos.get(i).getId();
	   			 }
	   			 this.batchDel(TbVoteMemberResultPO.class, delIds);
	   		   }
  	   	   }
  	      }
    }
    public List<TbVoteMemberPO>  searchMemberPO(String voteId)throws Exception, BaseException{
    	String sql="select * from tb_vote_member where vote_topic_id = :voteId";
    	partymembervoteDAO.preparedSql(sql);
    	partymembervoteDAO.setPreValue("voteId", voteId);
    	return partymembervoteDAO.getList(TbVoteMemberPO.class);
    }
    public List<TbVoteMemberResultPO>  searchResultPO(String voteId)throws Exception, BaseException{
    	String sql="select * from tb_vote_member_result where vote_id = :voteId";
    	partymembervoteDAO.preparedSql(sql);
    	partymembervoteDAO.setPreValue("voteId", voteId);
    	return partymembervoteDAO.getList(TbVoteMemberResultPO.class);
    }

    public VoteMemberTotalResultVO searchVoteTotalResult(String id)throws Exception, BaseException{
    	return partymembervoteDAO.searchVoteTotalResult(id);
    }

    public List<VoteMemberResultVO> searchVoteResultList(String id)throws Exception, BaseException{
    	return partymembervoteDAO.searchVoteResultList(id);
    }

	public void addVoteResult(TbVoteMemberResultPO po) throws Exception, BaseException {
		// 必须带有用户id
		// 判断用户是否投过票
		String id = po.getUserId();
		String key = id + "|" + po.getVoteId();
		logger.info("该用户(" + id + ")开始投票(" + po.getVoteId() + ")...");
		if (checkIDKeyContain(key)) {
			logger.info("该用户(" + id + ")已经在投票(" + po.getVoteId() + ")...");
			throw new BaseException("用户已投票");
		} else {
//			voteIdKey.add(key);

			int result = this.checkUser(po.getVoteId(), po.getUserId(), po.getVoteUserId());
			int count = this.checkUser(po.getVoteId(), "", po.getVoteUserId());
			TbPartyMemberVotePO votePO = this.searchByPk(TbPartyMemberVotePO.class, po.getVoteId());
			if (!AssertUtil.isEmpty(votePO.getVoteLimit())) {
				int limit = Integer.valueOf(votePO.getVoteLimit());
				if (count > limit) {
					throw new BaseException("用户投票次数超过限制");
				}
			}
			if (result >= 1) {
				throw new BaseException("用户已投票");
			} else {
				po.setVoteTime(new Date());
				TbPartyMenberInfoPO userPO = this.searchByPk(TbPartyMenberInfoPO.class, po.getUserId());
				if (!AssertUtil.isEmpty(userPO)) {
					po.setVoteUserType("1");
				} else {
					po.setVoteUserType("0");
				}
				this.insertPO(po, true);
			}
		}
	}
    public int checkUser(String voteId,String userId,String voteUserId)throws Exception, BaseException{
//    	String sql="select count(*) from TB_VOTE_MEMBER_RESULT where vote_id = :voteId and user_id = :userId and vote_user_id = :voteUserId";
//    	partymembervoteDAO.preparedSql(sql);
//    	partymembervoteDAO.setPreValue("voteId", voteId);
//    	partymembervoteDAO.setPreValue("userId", userId);
//    	partymembervoteDAO.setPreValue("voteUserId", voteUserId);
    	String sql="select count(*) from TB_VOTE_MEMBER_RESULT where vote_id = :voteId";
    	if(!AssertUtil.isEmpty(userId)){
    		sql += " and user_id = :userId";
    	}
    	
    	if(!AssertUtil.isEmpty(voteUserId)){
    		sql += " and vote_user_id = :voteUserId";
    	}
    	partymembervoteDAO.preparedSql(sql);
    	partymembervoteDAO.setPreValue("voteId", voteId);
    	
    	if(!AssertUtil.isEmpty(userId)){
    		partymembervoteDAO.setPreValue("userId", userId);
    	}
    	
    	if(!AssertUtil.isEmpty(voteUserId)){
    		partymembervoteDAO.setPreValue("voteUserId", voteUserId);
    	}
    	
    	return partymembervoteDAO.executeCount();
    	
    }
    private synchronized Boolean checkIDKeyContain(String idKey) {
		return PartymembervoteServiceImpl.voteIdKey.contains(idKey);
	}
    public List<TbVoteMemberVO> votePartyMemberList(String voteId,String voteUserId)throws Exception, BaseException{
    	List<TbVoteMemberVO> list = partymembervoteDAO.votePartyMemberList(voteId);
    	if(!AssertUtil.isEmpty(list)){
    		for(TbVoteMemberVO vo : list){
    			int count = this.checkUser(voteId, vo.getUserId(),voteUserId);
    			//isVote是否已投票 0:已投,1:未投
    			if(count >= 1){
    				vo.setIsVote("0");//已投票
    			}else{
    				vo.setIsVote("1");//未投票
    			}
    		}
    	}
    	
    	return list;
    }
    public TbVoteMemberVO searchPartyMember(String voteId,String userId)throws Exception, BaseException{
    	return partymembervoteDAO.searchPartyMember(voteId,userId);
    }

	@Override
	public void batchAddVoteResult(AddVoteRequest request) throws Exception, BaseException {
		String [] userIds = request.getUserIds();
		String voteUserId = request.getVoteUserId();
		String voteId = request.getId();
		TbPartyMemberVotePO partyMemberVote= partymembervoteDAO.searchByPk(TbPartyMemberVotePO.class, voteId);
		Date currentTime = new Date();
		Date startTime = partyMemberVote.getStartTime();
		Date endTime = partyMemberVote.getEndTime();
		if(currentTime.getTime() < startTime.getTime()){
			throw new BaseException("抱歉,评选活动还未开始,暂时不能投票!");
		}
		if(currentTime.getTime() > endTime.getTime()){
			throw new BaseException("抱歉,评选活动已经结束,投票已经截止!");
		}
		TbVoteMemberResultPO po = null;
		for(String userId : userIds){
			po = new TbVoteMemberResultPO();
			po.setVoteUserId(voteUserId);
			po.setVoteId(voteId);
			po.setUserId(userId);
			addVoteResult(po);
		}
		setPersonalScore(voteId,voteUserId);
	}

	/**
	 * 添加投票积分
	 * @param po
	 * @throws BaseException
	 * @throws Exception
	 * @throws DataConfictException
	 */
	private void setPersonalScore(String voteId,String userId) throws BaseException, Exception, DataConfictException {
		TbPersonalScoreInfoPO perScore = new TbPersonalScoreInfoPO();
		ExItemObj dic = DictOperater.getItem("personalScore", "10");//参加优秀党员评议
		perScore.setId(UUID.randomUUID().toString().toLowerCase());
		perScore.setUserId(userId);
		perScore.setScoreType("10");
		String title = "";
		TbPartyMemberVotePO vote = this.searchByPk(TbPartyMemberVotePO.class,voteId);
		if(null != vote){
			title = vote.getVoteTopic();
		}
		TbScoreRulePO scoreRule = this.scoreruleService.searchByType(dic.getFsDictItemId());
		if(null != scoreRule){
			perScore.setScore(Long.parseLong(scoreRule.getScoreValue()));
		}
		perScore.setGetTime(new Date());
		perScore.setScoreDesc("参加优秀党员评议	:《"+title+"》获得积分");
		this.insertPO(perScore, true);
	}

	@Override
	public VoteMemberTotalResultVO searchVoteCanyuTotal(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return partymembervoteDAO.searchVoteCanyuTotal(id);
	}
}
