package cn.com.do1.component.news.hotnewsinfo.dao.impl;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.news.hotnewsinfo.dao.IHotNewsInfoDAO;
import cn.com.do1.component.news.hotnewsinfo.vo.HotNewsVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class HotNewsInfoDAOImpl extends BaseDAOImpl implements IHotNewsInfoDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(HotNewsInfoDAOImpl.class);

	final static String condSQL = " from TB_HOT_NEWS t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.ORGANIZATION_ID = :organizationId and t.TITLE like :title and t.STATUS=:status order by t.BUY_TOP desc,t.LAST_MODIFY_TIME desc,t.PUSH_TIME desc,t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.title,t.id,t.status,t.buy_top,t.organization_id,t.create_user_id,t.create_time,t.last_modify_user_id,t.last_modify_time,t.push_time,t.img_path,o.ORGANIZATION_NAME  " + condSQL;
	
	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
		logger.info(">>>>>>"+searchSQL);
		return super.pageSearchByField(HotNewsVO.class, countSQL, searchSQL, searchMap, pager);
	}

}
