package cn.com.do1.component.vote.orgvote.dao.impl;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.vote.orgvote.dao.IOrgvoteDAO;
import cn.com.do1.component.vote.orgvote.model.OrgVoteListVO;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteVO;
import cn.com.do1.component.vote.orgvote.model.VoteResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class OrgvoteDAOImpl extends BaseDAOImpl implements IOrgvoteDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(OrgvoteDAOImpl.class);
    
    public Pager searchOrgvote(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_org_vote where topic like :topic order by create_time desc";
    	String countSQL="select count(*) from tb_org_vote where topic like :topic";
    	return super.pageSearchByField(TbOrgVotePO.class, countSQL, searchSQL, searchMap, pager);

    }
    public Pager searchOrgVotePages(Map searchMap, Pager pager)throws Exception, BaseException{
    	String searchSQL="select * from tb_org_vote t where t.push_status=1 and sysdate < t.end_time order by t.push_time DESC";
    	String countSQL="select count(*) from tb_org_vote t where t.push_status=1 and sysdate < t.end_time";
    	return super.pageSearchByField(TbOrgVoteVO.class, countSQL, searchSQL, searchMap, pager);
    }
    public Pager searchOrgVoteForInterface(Map searchMap, Pager pager) throws Exception, BaseException{
//    	String searchSQL="select id,vote_org,start_time,end_time from tb_org_vote where push_status = 1 order by create_time desc";
//    	String countSQL="select count(*) from tb_org_vote  where push_status = 1";
    	int status = Integer.parseInt(searchMap.get("status").toString());
    	searchMap.remove("status");
    	String condSQL = "";
    	switch(status){
    	case 0://全部
    		condSQL = " from tb_org_vote_list t where t.ORG_VOTE_ID = :orgVoteId ";
    		break;
    	case 1://未评议
    		condSQL = " from tb_org_vote_list t where t.ORG_VOTE_ID= :orgVoteId " +
    					"and t.id not in (select m.vote_id from tb_minzhu_vote_result m where m.user_id = :userId) order by create_time desc";
    		break;
    	case 2://已评议
    		condSQL = " from tb_org_vote_list t where t.ORG_VOTE_ID= :orgVoteId " +
						"and t.id in (select m.vote_id from tb_minzhu_vote_result m where m.user_id = :userId) order by create_time desc";
    		break;
    	}
    	
    	String countSQL="select count(*) "+condSQL;
    	String searchSQL="select t.id,t.vote_org,t.ORG_VOTE_ID,t.CREATE_TIME "+condSQL;
    	return super.pageSearchByField(OrgVoteListVO.class, countSQL, searchSQL, searchMap, pager);

    }


    public Pager searchReason(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_minzhu_vote_result where vote_id = :voteId order by vote_time desc";
    	String countSQL="select count(*) from tb_minzhu_vote_result where vote_id = :voteId  order by vote_time desc";
    	return super.pageSearchByField(TbMinzhuVoteResultPO.class, countSQL, searchSQL, searchMap, pager);
    }
    public VoteResultVO searchVoteResult(String voteId)throws Exception, BaseException{
    	String sql="select (select count(*)  from tb_minzhu_vote_result where  vote_id = :voteId) total_count,"+
                   " (select count(*)  from tb_minzhu_vote_result where result1=1 and  vote_id = :voteId) result1, "+
                   " (select count(*)  from tb_minzhu_vote_result where result2=1 and  vote_id = :voteId)  result2,"+
                   " (select count(*)  from tb_minzhu_vote_result where result3=1 and  vote_id = :voteId)  result3,"+
                   " (select count(*)  from tb_minzhu_vote_result where result4=1 and  vote_id = :voteId) result4,"+
                   " (select s.vote_org  from tb_org_vote_list s where s.id = :voteId) vote_org"+
    	           " from dual";
    	super.preparedSql(sql);
    	super.setPreValue("voteId", voteId);
    	return super.executeQuery(VoteResultVO.class);
    }
	@Override
	public TbMinzhuVoteResultPO getOrgVoteResultByVoteIdAndUserId(String voteId, String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
    	String sql="select * from tb_minzhu_vote_result where vote_id = :voteId and user_id = :userId";
    	this.preparedSql(sql);
    	super.setPreValue("voteId", voteId);
    	super.setPreValue("userId", userId);
    	return this.executeQuery(TbMinzhuVoteResultPO.class);
	}
}
