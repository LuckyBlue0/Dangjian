package cn.com.do1.component.partywork.activitytips.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.partywork.activitytips.dao.IActivityTipsDAO;
import cn.com.do1.component.partywork.activitytips.model.TbActivityTipsPO;
import cn.com.do1.component.partywork.activitytips.vo.ActivityTipsClientVO;
import cn.com.do1.component.partywork.activitytips.vo.ActivityTipsVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ActivityTipsDAOImpl extends BaseDAOImpl implements IActivityTipsDAO {
	private final static transient Logger logger = LoggerFactory.getLogger(ActivityTipsDAOImpl.class);
	final static String condSQL = " from TB_ACTIVITY_TIPS t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on m.id=t.CREATE_USER_ID"
			+ " where t.TYPE=:type and t.ACTIVITY_ID=:activityId order by t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.*,o.ORGANIZATION_NAME,m.name user_name,m.PORTRAIT_PIC  " + condSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
		logger.info(">>>>"+searchSQL);
    	String mobileClient = (String) searchMap.get("mobileClient");
    	searchMap.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
    		return super.pageSearchByField(ActivityTipsVO.class, countSQL, searchSQL, searchMap, pager);
    	}else{
    		return super.pageSearchByField(ActivityTipsClientVO.class, countSQL, searchSQL, searchMap, pager);
    	}
	}

	@Override
	public TbActivityTipsPO getActivityTipsByActivityIdAndUserId(String activityId, String userId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from TB_ACTIVITY_TIPS t where t.ACTIVITY_ID=:activityId and t.CREATE_USER_ID=:userId";
		this.preparedSql(sql);
		this.setPreValue("activityId", activityId);
		this.setPreValue("userId", userId);
		return super.executeQuery(TbActivityTipsPO.class);
	}


}
