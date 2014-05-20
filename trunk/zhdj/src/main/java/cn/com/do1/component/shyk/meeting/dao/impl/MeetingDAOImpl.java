package cn.com.do1.component.shyk.meeting.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.shyk.meeting.dao.IMeetingDAO;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;

/**
* All rights reserved.
* User: ${user}
*/
public class MeetingDAOImpl extends BaseDAOImpl implements IMeetingDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(MeetingDAOImpl.class);
	final static String condSQL = " from TB_MEETING t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.TYPE=:type and t.TITLE like :title "+
									" and t.STATUS=:status and t.WHETHER_END=:whetherEnd and t.WHETHER_RECOMMEND=:whetherRecommend and t.ORGANIZATION_ID=:organizationId order by t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.ID,t.TITLE,t.TYPE,t.ORGANIZATION_ID,t.WHETHER_END,t.WHETHER_RECOMMEND,t.START_TIME,t.END_TIME,"+
										" t.WAY,t.ADDRESS,t.CONTENT,t.QR_CODE,t.SMS_NOTICE,t.CREATE_USER_ID,t.CREATE_TIME,"+
										" t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,t.PUSH_USER_ID,t.PUSH_TIME,o.ORGANIZATION_NAME  " + condSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException{
		// TODO Auto-generated method stub
		return super.pageSearchByField(MeetingVO.class, countSQL, searchSQL, searchMap, pager);
	}

	@Override
	public MeetingVO getMeetingById(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select t.*,o.ORGANIZATION_NAME from TB_MEETING t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id" +
				" where t.id=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return this.executeQuery(MeetingVO.class);
	}
	
	@Override
	public List<MeetingVO> getNoEndMeetings(int type)throws SQLException{
		String sql = "";
		switch(type){
		case 1:
			sql = "select t.id,t.title,t.status from TB_MEETING t where t.whether_end=0 and t.end_time < sysdate";
			break;
		case 2:
			sql = "select t.id,t.title,t.status from TB_PARTY_LECTURE t where t.whether_end=0 and t.end_time < sysdate";
			break;
		case 3:
			sql = "select t.id,t.title,t.status from TB_BRANCH_ACTIVITY t where t.whether_end=0 and t.end_time < sysdate";
			break;
		case 4:
			sql = "select t.id,t.title,t.status from TB_VOLUNTEER_ACTIVITY t where t.whether_end=0 and t.end_time < sysdate";
			break;
		case 5:
			sql = "select t.id,t.title,t.status from TB_DEMOCRATIC_LIFE t where t.whether_end=0 and t.end_time < sysdate";
			break;
		}
		this.preparedSql(sql);
		return this.getList(MeetingVO.class);
	}

}
