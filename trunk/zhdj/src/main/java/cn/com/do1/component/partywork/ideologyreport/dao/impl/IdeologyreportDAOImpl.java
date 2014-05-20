package cn.com.do1.component.partywork.ideologyreport.dao.impl;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.partywork.ideologyreport.dao.IIdeologyreportDAO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;

/**
* All rights reserved.
* User: ${user}
*/
public class IdeologyreportDAOImpl extends BaseDAOImpl implements IIdeologyreportDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(IdeologyreportDAOImpl.class);
	final static String condSQL = " from TB_IDEOLOGY_REPORT t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on t.CREATE_USER_ID=m.id left join TB_PARTY_MENBER_INFO m2 on t.REVIEW_USER_ID=m2.id " +
			"where 1=1 and t.TITLE like :title and t.STATUS=:status and t.CREATE_USER_ID=:userId and t.ORGANIZATION_ID=:organizationId";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.*,o.ORGANIZATION_NAME,m.name,m2.name reviewUser  " + condSQL;
    @Override
    public Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException{
    	logger.info("-----------SQL="+searchSQL);
    	return super.pageSearchByField(IdeologyReportVO.class, countSQL, searchSQL, searchMap, pager);
    }
    
    
    @Override
    public IdeologyReportVO searchById(String id) throws SQLException{
		String sql = "select t.*,o.ORGANIZATION_NAME,m.name from TB_IDEOLOGY_REPORT t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on t.CREATE_USER_ID=m.id where t.ID=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return super.executeQuery(IdeologyReportVO.class);
    }
}
