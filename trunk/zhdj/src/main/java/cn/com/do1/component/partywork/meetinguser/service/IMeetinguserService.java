package cn.com.do1.component.partywork.meetinguser.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserDetailsVO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IMeetinguserService extends IBaseService{
    Pager searchMeetinguser(Map searchMap, Pager pager) throws Exception, BaseException;
    
    /**
     * 根据会议Id获取会议人员列表
     * @param meetingId
     * @return
     * @throws Exception
     * @throws BaseException
     */
    List<MeetingUserVO> getMeetingUsersByMeetingId(String meetingId)throws Exception, BaseException;

	/**
	 * 根据Id删除党员会议记录
	 * @param key
	 */
	void deleteMeetingById(String id)throws Exception, BaseException;
	
	/**
	 * 根据会议Id、党员ID删除会议党员
	 */
	void deleteMeetingByMeetingIdAndUserId(String meetingId,String userId)throws Exception, BaseException;
	
	/**
	 * 根据会议Id删除党员
	 * @param meetingId
	 * @throws Exception
	 * @throws BaseException
	 */
	void delMeetingUserByMeetingId(String meetingId)throws Exception, BaseException;

	/**
	 * 获取参会、报名、请假、缺席、签到、未签到的会议党员信息
	 * @param meetingId
	 * @return
	 */
	List<MeetingUserDetailsVO> getMeetingUsersDetailsByMeetingId(String meetingId)throws Exception, BaseException;

	/**
	 * 根据条件获取不同的会议党员信息
	 * @param type
	 * @param vo
	 * @return
	 */
	List<MeetingUserVO> getMeetingUsersByVO(Integer type, MeetingUserVO vo)throws Exception, BaseException;
	
	/**
	 * 根据会议或活动ID、用户ID查询用户信息
	 * @param meetingId
	 * @param userId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	TbMeetingUserPO getMeetingUserByMeetingId(String meetingId,String userId)throws Exception, BaseException;
	


}
