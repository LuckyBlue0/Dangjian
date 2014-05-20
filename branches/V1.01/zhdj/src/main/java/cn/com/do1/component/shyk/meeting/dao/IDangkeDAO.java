package cn.com.do1.component.shyk.meeting.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.shyk.meeting.vo.DangkeVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IDangkeDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	DangkeVO getDangkeById(String id)throws SQLException;

}
