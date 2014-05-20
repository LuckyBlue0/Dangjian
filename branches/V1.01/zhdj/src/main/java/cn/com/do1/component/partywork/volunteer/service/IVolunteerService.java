package cn.com.do1.component.partywork.volunteer.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.index.membercenter.model.PartyDetailsClientVO;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IVolunteerService extends IBaseService{
    Pager searchVolunteer(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查询个人志愿活动
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    Pager searchVolunteerByUserId(Map searchMap, Pager pager) throws Exception, BaseException;
    VolunteerVO getVolunteerById(String id)throws Exception, BaseException;
    void updateVolunteer(TbVolunteerActivityPO volunteerActivityPO)throws Exception, BaseException;
	Map<String, Object> saveVolunteerActivityInfo(TbVolunteerActivityPO tbVolunteerActivityPO,String userIds)throws Exception, BaseException;
	
	
	/**
	 * 三会一课/支部活动/志愿活动详情
	 * @param id
	 * @param userId
	 * @param type
	 * @return
	 */
	PartyDetailsClientVO getPartyDetails(String id,String userId, Integer type)throws Exception, BaseException;
	
	
	/**
	 * 保存活动记录信息
	 * @param tbVolunteerActivityPO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> saveVolunteerRecordInfo(TbVolunteerActivityPO tbVolunteerActivityPO, String userIds)throws Exception, BaseException;
}
