package cn.com.do1.component.partywork.democrticlife.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IDemocrticLifeDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException;

	DemocrticLifeVO getDemocrticLifeById(String id)throws SQLException;

}
