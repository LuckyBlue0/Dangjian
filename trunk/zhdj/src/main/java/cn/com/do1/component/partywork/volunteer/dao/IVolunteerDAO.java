package cn.com.do1.component.partywork.volunteer.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IVolunteerDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;
    /**
     * 查询个人志愿活动
     * @param searchMap
     * @param pager
     * @return
     * @throws SQLException
     */
	Pager searchVolunteerByUserId(Map searchMap, Pager pager)throws SQLException;
	VolunteerVO getVolunteerById(String id)throws SQLException;
}
