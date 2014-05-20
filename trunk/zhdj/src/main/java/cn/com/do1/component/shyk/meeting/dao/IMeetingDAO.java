package cn.com.do1.component.shyk.meeting.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IMeetingDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	MeetingVO getMeetingById(String id)throws SQLException;

	/**
	 * 根据类型获取对应的活动
	 * @param type 1：党会,2：党课,3：支部活动,4：志愿活动,5：民主生活会
	 * @return
	 * @throws SQLException
	 */
	List<MeetingVO> getNoEndMeetings(int type)throws SQLException;

}
