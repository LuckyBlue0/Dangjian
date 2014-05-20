package cn.com.do1.component.partywork.volunteer.dao.impl;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.index.membercenter.model.PartyListClientVO;
import cn.com.do1.component.partywork.volunteer.dao.IVolunteerDAO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;

/**
* All rights reserved.
* User: ${user}
*/
public class VolunteerDAOImpl extends BaseDAOImpl implements IVolunteerDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(VolunteerDAOImpl.class);
    
	final static String condSQL = " from TB_VOLUNTEER_ACTIVITY t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on t.CREATE_USER_ID=m.id left join tb_meeting_user p on t.id=p.meeting_id and p.sign_up_status = 1 and p.user_id = :userid where 1=1 and t.ORGANIZATION_ID = :organizationId and t.TYPE=:type and t.status =:status and t.TITLE like :title and (t.title like :keyword or t.CREATE_USER_ID like :keyword) order by t.CREATE_TIME desc";

	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select (case when t.status = 1 then 2 else case when p.id is not null then 1 else 0 end end)sign_Up_Status,t.ID,t.TITLE,t.TYPE,t.ORGANIZATION_ID,t.START_TIME,t.END_TIME,t.WHETHER_END," + " t.ADDRESS,t.CONTENT,t.IMG_PATH,t.THEME_IMG_PATH,t.CREATE_USER_NAME,t.CREATE_TIME,"
			+ " t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,o.ORGANIZATION_NAME,m.name  " + condSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException{
		// TODO Auto-generated method stub
		String mobileClient = (String) searchMap.get("mobileClient");
		searchMap.remove("mobileClient");
		if(AssertUtil.isEmpty(mobileClient)){
			return super.pageSearchByField(VolunteerVO.class, countSQL, searchSQL,searchMap, pager);
		}else{
			return super.pageSearchByField(PartyListClientVO.class, countSQL, searchSQL,searchMap, pager);
		}
	}
	public Pager searchVolunteerByUserId(Map searchMap, Pager pager)throws SQLException{
		String searchSql="select  t.ID,t.TITLE,t.TYPE,t.ORGANIZATION_ID,t.START_TIME,t.END_TIME,t.ADDRESS,t.CONTENT,t.IMG_PATH,t.THEME_IMG_PATH,t.CREATE_USER_ID,t.CREATE_USER_NAME,t.CREATE_TIME," +
				"t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME from TB_VOLUNTEER_ACTIVITY t left join tb_meeting_user p on t.id=p.meeting_id where p.user_id = :userid and t.status =:status" +
				" and p.sign_up_status = 1 and (t.title like :keyword or t.CREATE_USER_ID like :keyword)"; 
		String countSql="select count(*) from TB_VOLUNTEER_ACTIVITY t left join tb_meeting_user p on t.id=p.meeting_id where p.user_id = :userid and p.sign_up_status = 1 and t.status =:status and (t.title like :keyword or t.CREATE_USER_ID like :keyword)";
		String mobileClient = (String) searchMap.get("mobileClient");
		searchMap.remove("mobileClient");
		if(AssertUtil.isEmpty(mobileClient)){
			return super.pageSearchByField(VolunteerVO.class, countSql, searchSql,searchMap, pager);
		}else{
			return super.pageSearchByField(PartyListClientVO.class, countSql, searchSql,searchMap, pager);
		}
	}
	
	public VolunteerVO getVolunteerById(String id)throws SQLException{
		String sql = "select t.*,o.ORGANIZATION_NAME,m.name from TB_VOLUNTEER_ACTIVITY t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on t.CREATE_USER_ID=m.id where t.ID=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return super.executeQuery(VolunteerVO.class);
	}


}
