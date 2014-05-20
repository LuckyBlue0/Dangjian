package cn.com.do1.component.vote.orgvote.dao;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.vote.orgvote.model.TbMinzhuVoteResultPO;
import cn.com.do1.component.vote.orgvote.model.TbOrgVotePO;
import cn.com.do1.component.vote.orgvote.model.VoteResultVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IOrgvoteDAO extends IBaseDAO {
    
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
    
    public TbMinzhuVoteResultPO getOrgVoteResultByVoteIdAndUserId(String voteId,String userId)throws Exception, BaseException;
    
    
	/**
	 * 获取机关评议投票主题分页列表
	 * @param searchMap
	 * @param pager
	 * @return
	 */
	Pager searchOrgVotePages(Map searchMap, Pager pager)throws Exception, BaseException;
	
}
