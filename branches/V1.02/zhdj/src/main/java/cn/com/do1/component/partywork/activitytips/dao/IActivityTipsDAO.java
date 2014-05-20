package cn.com.do1.component.partywork.activitytips.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.activitytips.model.TbActivityTipsPO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IActivityTipsDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	public TbActivityTipsPO getActivityTipsByActivityIdAndUserId(String activityId, String userId)throws SQLException;

}
