package cn.com.do1.component.news.hotnewsinfo.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IHotNewsInfoDAO extends IBaseDAO {

	/**
	 * @param searchMap
	 * @param pager
	 * @return
	 */
	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

}
