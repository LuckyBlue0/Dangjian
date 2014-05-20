package cn.com.do1.component.vote.orgvote.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteListPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVoteVO;
import cn.com.do1.component.vote.orgvote.model.VoteResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IOrgvoteService extends IBaseService{
    Pager searchOrgvote(Map searchMap, Pager pager) throws Exception, BaseException;
    Pager searchOrgVoteForInterface(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查询不满意理由
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    Pager searchReason(Map searchMap, Pager pager) throws Exception, BaseException;
    
    /**
     * 统计民主机关评议结果
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public VoteResultVO searchVoteResult(String voteId)throws Exception, BaseException;
    /**
     * 民族评议投票，实名投票（对象参数必须有userId）
     * @param po
     * @throws Exception
     * @throws BaseException
     */
    public void addVoteResult(TbMinzhuVoteResultPO po)throws Exception, BaseException;
    /**
     * 删除民主机关评议及其投票信息
     * @param ids
     * @throws Exception
     * @throws BaseException
     */
    public void delOrgVote(String[] ids)throws Exception, BaseException;
    
    /**
     * 查询用户是否可投票
     * @param voteId
     * @param userId
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public boolean checkUser(String voteId,String userId)throws Exception, BaseException;
    
    /**
     * 查看用户投票内容
     * @param voteId
     * @param userId
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbMinzhuVoteResultPO getOrgVoteResultByVoteIdAndUserId(String voteId,String userId)throws Exception, BaseException;
    
	/**
	 * 新增或修改民主机关投票
	 * @param tbOrgVotePO
	 * @param tbVoteOrgList
	 */
	void saveOrgVote(TbOrgVotePO tbOrgVotePO, List<TbOrgVoteListPO> tbVoteOrgList)throws Exception, BaseException;
	
	/**
	 * 根据民主机关投票主题ID获取被评选机关列表
	 * @param id
	 * @return
	 */
	List<TbOrgVoteListPO> getOrgVoteListByOrgVoteId(String id)throws Exception, BaseException;
	
	/**
	 * 获取机关评议投票主题分页列表
	 * @return
	 */
	Pager searchOrgVotePages(Map searchMap, Pager pager)throws Exception, BaseException;
	
	/**
	 * 获取未评议数量或已评议数量
	 * @param type
	 * @param orgVoteId
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int getOrgVoteCount(int type,String orgVoteId,String userId) throws SQLException;
	

}
