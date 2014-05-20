package cn.com.do1.component.partywork.ideologyreport.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IIdeologyreportDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	IdeologyReportVO searchById(String id)throws SQLException;

}
