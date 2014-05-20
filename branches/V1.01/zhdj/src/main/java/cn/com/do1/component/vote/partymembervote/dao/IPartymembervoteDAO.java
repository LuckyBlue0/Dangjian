package cn.com.do1.component.vote.partymembervote.dao;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IPartymembervoteDAO extends IBaseDAO {
	  Pager searchPartymembervote(Map searchMap, Pager pager) throws Exception, BaseException;
	  Pager searchPartymembervoteForInterface(Map searchMap, Pager pager) throws Exception, BaseException;
	  /**
	     * 根据评议主题id查找党员
	     * @param voteId
	     * @return
	     * @throws Exception
	     * @throws BaseException
	     */
	    public List<TbVoteMemberPO> searchVoteMember(String voteId)throws Exception, BaseException;
	    /**
	     * 统计评议的党员
	     * @param id
	     * @return
	     * @throws Exception
	     * @throws BaseException
	     */
	    public VoteMemberTotalResultVO searchVoteTotalResult(String id)throws Exception, BaseException;
	    /**
	     * 统计评议的党员票数
	     * @param id
	     * @return
	     * @throws Exception
	     * @throws BaseException
	     */
	    public List<VoteMemberResultVO> searchVoteResultList(String id)throws Exception, BaseException;
	    /**
	     * 根据主题id查所有的党员
	     * @param voteId
	     * @return
	     * @throws Exception
	     * @throws BaseException
	     */
	    public List<TbVoteMemberVO> votePartyMemberList(String voteId)throws Exception, BaseException;
	    /**
	     * 查询评议党员详情
	     * @param voteId
	     * @param userId
	     * @return
	     * @throws Exception
	     * @throws BaseException
	     */
	    public TbVoteMemberVO searchPartyMember(String voteId,String userId)throws Exception, BaseException;
	    
		/**
		 * 获取投票主题的参与人数
		 * @param id
		 * @return
		 */
		VoteMemberTotalResultVO searchVoteCanyuTotal(String id)throws Exception, BaseException;
}
