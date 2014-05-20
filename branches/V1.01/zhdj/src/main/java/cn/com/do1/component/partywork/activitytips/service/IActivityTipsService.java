package cn.com.do1.component.partywork.activitytips.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.partywork.activitytips.model.TbActivityTipsPO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IActivityTipsService extends IBaseService{
    Pager searchActivityTips(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 根据活动ID和用户ID查询心得信息
	 * @param id
	 * @param userId
	 * @return
	 */
	TbActivityTipsPO getActivityTipsByActivityIdAndUserId(String activityId, String userId)throws Exception, BaseException;

}
