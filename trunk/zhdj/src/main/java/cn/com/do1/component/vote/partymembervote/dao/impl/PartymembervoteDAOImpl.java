package cn.com.do1.component.vote.partymembervote.dao.impl;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.vote.partymembervote.dao.IPartymembervoteDAO;
import cn.com.do1.component.vote.partymembervote.model.TbPartyMemberVotePO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class PartymembervoteDAOImpl extends BaseDAOImpl implements IPartymembervoteDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(PartymembervoteDAOImpl.class);
    public Pager searchPartymembervote(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_party_member_vote where vote_topic like :voteTopic order by create_time desc";
    	String countSQL="select count(*) from tb_party_member_vote where vote_topic like :voteTopic";
    	return super.pageSearchByField(TbPartyMemberVotePO.class, countSQL, searchSQL, searchMap, pager);
    }
    public Pager searchPartymembervoteForInterface(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select id,vote_topic,vote_img_path,start_time,end_time,REMARK from tb_party_member_vote where push_status = 1 and vote_topic like :voteTopic order by create_time desc";
    	String countSQL="select count(*) from tb_party_member_vote where push_status = 1 and vote_topic like :voteTopic";
    	return super.pageSearchByField(VoteMemberTotalResultVO.class, countSQL, searchSQL, searchMap, pager);
    }
    
    public List<TbVoteMemberPO> searchVoteMember(String voteId)throws Exception, BaseException{
    	String sql="select * from tb_vote_member where vote_topic_id = :voteId";
    	super.preparedSql(sql);
    	super.setPreValue("voteId", voteId);
    	return super.getList(TbVoteMemberPO.class);
    }
    public VoteMemberTotalResultVO searchVoteTotalResult(String id)throws Exception, BaseException{
		String sql = " select (select t.vote_topic  from tb_party_member_vote t  where t.id = :id) vote_topic," 
			+ " (select count(*)  from tb_vote_member_result s where s.vote_id = :id) total_count " + " from dual";
    	super.preparedSql(sql);
    	super.setPreValue("id", id);
    	return super.executeQuery(VoteMemberTotalResultVO.class);
    }

    public List<VoteMemberResultVO> searchVoteResultList(String id)throws Exception, BaseException{
    	String sql="SELECT s.name as user_name,s.portrait_pic as user_img_path,b.COUNT as result1 FROM TB_VOTE_MEMBER a LEFT JOIN  "+
	               "(SELECT count(1) as count, USER_ID FROM TB_VOTE_MEMBER_RESULT where VOTE_ID = :id GROUP BY USER_ID) b "+
		           " on b.USER_ID=a.USER_ID   left join tb_party_menber_info s on a.user_id=s.id"+
                   " where VOTE_TOPIC_ID = :id";
    	super.preparedSql(sql);
    	super.setPreValue("id", id);
    	return super.getList(VoteMemberResultVO.class);
    }

    public List<TbVoteMemberVO> votePartyMemberList(String voteId)throws Exception, BaseException{
    	String sql="select t.user_id as user_id,t.vote_topic_id as vote_id,s.name as user_name,s.portrait_pic as user_img_path from tb_vote_member t left join tb_party_menber_info s  on t.user_id=s.id " +
    			"where vote_topic_id = :voteId";
    	super.preparedSql(sql);
    	super.setPreValue("voteId", voteId);
    	return super.getList(TbVoteMemberVO.class);
    }

    public TbVoteMemberVO searchPartyMember(String voteId,String userId)throws Exception, BaseException{
    	String sql="select t.user_id as user_id,t.vote_topic_id as vote_id,s.name as user_name,s.portrait_pic as user_img_path,t.advanced_deeds,t.party_work from tb_vote_member t left join tb_party_menber_info s  on t.user_id=s.id where vote_topic_id = :voteId and user_id = :userId";
    	super.preparedSql(sql);
    	super.setPreValue("voteId", voteId);
    	super.setPreValue("userId", userId);
    	return super.executeQuery(TbVoteMemberVO.class);
    }
    
	public VoteMemberTotalResultVO searchVoteCanyuTotal(String id) throws Exception, BaseException {
    	String sql=" select (select t.vote_topic  from tb_party_member_vote t  where t.id = :id) vote_topic,"+
        " (  select count(t.a) from(select 1 a  from tb_vote_member_result s where s.vote_id = :id group by s.vote_user_id) t) total_count "+
        " from dual";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(VoteMemberTotalResultVO.class);
	}
}
