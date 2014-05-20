package cn.com.do1.component.vote.partymembervote.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.vote.orgvote.model.AddVoteRequest;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberResultPO;
import cn.com.do1.component.vote.partymembervote.model.TbVoteMemberVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberResultVO;
import cn.com.do1.component.vote.partymembervote.model.VoteMemberTotalResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IPartymembervoteService extends IBaseService{
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
     * 加入评议党员
     * @param tbVoteMemberPOList
     * @throws Exception
     * @throws BaseException
     */
    public void  addPartymember(List<TbVoteMemberPO> tbVoteMemberPOList)throws Exception, BaseException;
    /**
     * 修改评议党员
     * @param tbVoteMemberPOList
     * @throws Exception
     * @throws BaseException
     */
    public void  updatePartymember(List<TbVoteMemberPO> tbVoteMemberPOList)throws Exception, BaseException;
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
     * 删除优秀党员评议及其投票信息
     * @param ids
     * @throws Exception
     * @throws BaseException
     */
    public void delPartymemberVote(String[] ids)throws Exception, BaseException;
    /**
     * 优秀党员投票，实名投票（对象参数必须有userId）
     * @param po
     * @throws Exception
     * @throws BaseException
     */
    public void addVoteResult(TbVoteMemberResultPO po)throws Exception, BaseException;
    /**
     * 根据主题id查所有的党员
     * @param voteId
     * @param voteUserId 投票人员ID 
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<TbVoteMemberVO> votePartyMemberList(String voteId, String voteUserId)throws Exception, BaseException;
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
	 * 优秀党员投票，实名投票（对象参数必须有userId）
	 * @param request
	 */
	public void batchAddVoteResult(AddVoteRequest request)throws Exception, BaseException;
	
	/**
	 * 检查用户投过票的数量
	 * @param voteId
	 * @param userId
	 * @param voteUserId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public int checkUser(String voteId,String userId,String voteUserId)throws Exception, BaseException;
	
	/**
	 * 获取投票主题的参与人数
	 * @param id
	 * @return
	 */
	VoteMemberTotalResultVO searchVoteCanyuTotal(String id)throws Exception, BaseException;
}
