package cn.com.do1.component.partywork.meetinguser.dao;
import java.sql.SQLException;
import java.util.List;

import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserDetailsVO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IMeetinguserDAO extends IBaseDAO {

	List<MeetingUserVO> getMeetingUsersByMeetingId(String meetingId)throws SQLException;

	void deleteMeetingById(String id)throws SQLException;

	void deleteMeetingByMeetingIdAndUserId(String meetingId, String userId)throws SQLException;

	void delMeetingUserByMeetingId(String meetingId)throws SQLException;

	List<MeetingUserDetailsVO> getMeetingUsersDetailsByMeetingId(String meetingId)throws SQLException;
	
	List<MeetingUserVO> getMeetingUsersByVO(Integer type, MeetingUserVO vo)throws SQLException;

	TbMeetingUserPO getMeetingUserByMeetingId(String meetingId, String userId)throws SQLException;
}
