package cn.com.do1.component.shyk.partylecture.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.shyk.partylecture.dao.IPartyLectureDAO;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartyLectureDAOImpl extends BaseDAOImpl implements IPartyLectureDAO {
	private final static transient Logger logger = LoggerFactory.getLogger(PartyLectureDAOImpl.class);
	final static String condSQL = " from TB_PARTY_LECTURE t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.TYPE=:type and t.TITLE like :title "
			+ " and t.STATUS=:status and t.WHETHER_END=:whetherEnd and t.WHETHER_RECOMMEND=:whetherRecommend and t.ORGANIZATION_ID=:organizationId order by t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.ID,t.TITLE,t.TYPE,t.ORGANIZATION_ID,t.WHETHER_END,t.WHETHER_RECOMMEND,t.START_TIME,t.END_TIME," + " t.WAY,t.ADDRESS,t.CONTENT,t.QR_CODE,t.SMS_NOTICE,t.CREATE_USER_ID,t.CREATE_TIME,"
			+ " t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,t.PUSH_USER_ID,t.PUSH_TIME,o.ORGANIZATION_NAME  " + condSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
		logger.info(">>>>"+searchSQL);
		return super.pageSearchByField(PartyLectureVO.class, countSQL, searchSQL, searchMap, pager);
	}

	@Override
	public PartyLectureVO searchById(String id) throws SQLException {
		String sql = "select t.*,o.ORGANIZATION_NAME from TB_PARTY_LECTURE t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id" +
					" where t.id=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return this.executeQuery(PartyLectureVO.class);
	}

}
