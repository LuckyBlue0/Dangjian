package cn.com.do1.component.partywork.branch.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.partywork.branch.model.TbActivityUserPO;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IBranchService extends IBaseService{
    Pager searchBranch(Map searchMap, Pager pager) throws Exception, BaseException;
	/**
	 * 保存支部活动信息
	 * @param branchActivityPO
	 * @param userIds 参与人员ID
	 * @return
	 */
	Map<String, Object> saveBranchActivityInfo(TbBranchActivityPO branchActivityPO, String userIds)throws Exception, BaseException;
	
	/**
	 * 根据Id获取支部信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	BranchActivityVO getBranchActivityById(String id)throws Exception, BaseException;
	
	/**
	 * 获取未结束的支部活动信息
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public List<TbBranchActivityPO>getNoEndBranchActivity()throws Exception, BaseException;
	
	
	/**
	 * 保存支部活动记录
	 * @param tbBranchActivityPO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> saveBranchRecordInfo(TbBranchActivityPO tbBranchActivityPO, String userIds)throws Exception, BaseException;
	
	
	Map<String, Object> saveBranchActivity(TbBranchActivityPO branchActivityPO,
			String userIds) throws Exception, BaseException;
	
	Pager getBranchActivityByUserid(Map searchValue, Pager pager)throws Exception, BaseException;
	List<BranchActivityVO> getDataByactivityId(String activityId)throws Exception, BaseException;
	
    boolean searchDataByUserId(String activityId,String userId)throws Exception, BaseException;
	void joinMyActivity(String activityId, String userId) throws Exception,
			BaseException;
	void addMyActivity(String createUserId, String content, String title,
			String startTime, String endTime, String address) throws Exception,
			BaseException;
	List<BranchActivityVO> searchList() throws Exception,
			BaseException;
}
