package cn.com.do1.component.partywork.democrticlife.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.index.membercenter.model.PartyListClientVO;
import cn.com.do1.component.partywork.democrticlife.dao.IDemocrticLifeDAO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class DemocrticLifeDAOImpl extends BaseDAOImpl implements IDemocrticLifeDAO {
	private final static transient Logger logger = LoggerFactory.getLogger(DemocrticLifeDAOImpl.class);
	final static String condSQL = " from TB_DEMOCRATIC_LIFE t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id"
			+ " where 1=1 and (t.title like :keyword or t.create_user_name like :keyword) and t.STATUS=:status and t.WHETHER_END=:whetherEnd " + " and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus "
			+ " and t.ORGANIZATION_ID=:organizationId order by t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.ID,t.TITLE,t.ORGANIZATION_ID,t.WHETHER_END,t.START_TIME,t.END_TIME," + "t.ADDRESS,t.CONTENT,t.QR_CODE,t.SMS_NOTICE,t.CREATE_USER_ID,t.CREATE_USER_NAME,t.CREATE_TIME,"
			+ " t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,o.ORGANIZATION_NAME  " + condSQL;

	final static String mobileCondSQL = " from TB_DEMOCRATIC_LIFE t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join tb_meeting_user p on t.id=p.meeting_id"
			+ " where 1=1 and (t.title like :keyword or t.create_user_name like :keyword) and p.user_id = :userid" + " and t.STATUS=:status and t.WHETHER_END=:whetherEnd"
					+ " and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus "
					+ " and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"
			+ " and t.ORGANIZATION_ID=:organizationId order by t.CREATE_TIME desc";
	final static String mobileCountSQL = "select count(1)  " + mobileCondSQL;
	final static String mobileSearchSQL = "select t.ID,t.TITLE,t.ORGANIZATION_ID,t.WHETHER_END,t.START_TIME,t.END_TIME," + "t.ADDRESS,t.CONTENT,t.QR_CODE,t.SMS_NOTICE,t.CREATE_USER_ID,t.CREATE_USER_NAME,t.CREATE_TIME,"
			+ " t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,o.ORGANIZATION_NAME,p.for_leave_status " + mobileCondSQL;

	@Override
	public DemocrticLifeVO getDemocrticLifeById(String id) throws SQLException {
		String sql = "select t.*,o.ORGANIZATION_NAME from TB_DEMOCRATIC_LIFE t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.id=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return this.executeQuery(DemocrticLifeVO.class);
	}

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		logger.info(">>>>" + mobileSearchSQL);
		String mobileClient = (String) searchMap.get("mobileClient");
		searchMap.remove("mobileClient");
		if (AssertUtil.isEmpty(mobileClient)) {
			return super.pageSearchByField(DemocrticLifeVO.class, countSQL, searchSQL, searchMap, pager);
		} else {
			return super.pageSearchByField(PartyListClientVO.class, mobileCountSQL, mobileSearchSQL, searchMap, pager);
		}
	}
}
