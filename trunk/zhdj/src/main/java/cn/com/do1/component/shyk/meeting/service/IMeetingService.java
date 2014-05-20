package cn.com.do1.component.shyk.meeting.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IMeetingService extends IBaseService{
    Pager searchMeeting(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 保存三会一课信息
	 * @param tbMeetingPO
	 * @param userIds 参与人员ID
	 * @return
	 */
	Map<String, Object> saveMeetingInfo(TbMeetingPO tbMeetingPO, String userIds)throws Exception, BaseException;
	
	/**
	 * 根据Id获取会议信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	MeetingVO getMeetingById(String id)throws Exception, BaseException;
	
	
	/**
	 * 获取未结束的会议、党课、支部活动、志愿活动、民主生活会
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	List<MeetingVO>getNoEndMeetings(int type)throws Exception, BaseException;
	
	
	/**
	 * 设置会议、党课、支部活动、民主生活会、志愿活动为已结束
	 * @throws Exception
	 * @throws BaseException
	 */
	void endMeetingProcess()throws Exception, BaseException;

	/**
	 * 保存会议记录信息
	 * @param tbMeetingPO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> saveMeetingRecordInfo(TbMeetingPO tbMeetingPO, String userIds)throws Exception, BaseException;

}
