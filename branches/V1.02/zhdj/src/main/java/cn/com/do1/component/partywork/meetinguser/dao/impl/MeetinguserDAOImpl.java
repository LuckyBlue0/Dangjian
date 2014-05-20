package cn.com.do1.component.partywork.meetinguser.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.partywork.meetinguser.dao.IMeetinguserDAO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserDetailsVO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;

/**
* All rights reserved.
* User: ${user}
*/
public class MeetinguserDAOImpl extends BaseDAOImpl implements IMeetinguserDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(MeetinguserDAOImpl.class);

	@Override
	public List<MeetingUserVO> getMeetingUsersByMeetingId(String meetingId) throws SQLException {
		String sql = "select t.*,p.NAME from TB_MEETING_USER t left join TB_PARTY_MENBER_INFO p on t.USER_ID=p.id where t.MEETING_ID=:meetingId";
		this.preparedSql(sql);
		this.setPreValue("meetingId", meetingId);
		return this.getList(MeetingUserVO.class);
	}
	
	@Override
	public void deleteMeetingById(String id)throws SQLException{
		String sql = "delete from TB_MEETING_USER mu where mu.ID=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		super.executeUpdate();
	}
	
	@Override
	public void deleteMeetingByMeetingIdAndUserId(String meetingId, String userId)throws SQLException{
		String sql = "delete from TB_MEETING_USER mu where mu.MEETING_ID=:meetingId and mu.USER_ID=:userId";
		super.preparedSql(sql);
		super.setPreValue("meetingId", meetingId);
		super.setPreValue("userId", userId);
		super.executeUpdate();
	}
	
	@Override
	public void delMeetingUserByMeetingId(String meetingId)throws SQLException{
		String sql = "delete from TB_MEETING_USER mu where mu.MEETING_ID=:meetingId";
		super.preparedSql(sql);
		super.setPreValue("meetingId", meetingId);
		super.executeUpdate();
	}
	
	@Override
	public List<MeetingUserDetailsVO> getMeetingUsersDetailsByMeetingId(String meetingId)throws SQLException{
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.canyu,t2.baoming,t3.qingjia,(t2.baoming-t4.qiandao) as quexi,t4.qiandao,")
		.append(" (t2.baoming-t4.qiandao)as weiqiandao")
		.append(" from (select count(*) as canyu from tb_meeting_user t")
		.append(" where t.meeting_id=:meetingId)t1,")
		.append(" (select count(*) as baoming from tb_meeting_user t")
		.append(" where t.meeting_id=:meetingId and t.SIGN_UP_STATUS = 1)t2,")
		.append(" (select count(*) as qingjia from tb_meeting_user t")
		.append(" where t.meeting_id=:meetingId and t.FOR_LEAVE_STATUS = 1)t3,")
		.append(" (select count(*) as qiandao from tb_meeting_user t")
		.append(" where t.meeting_id=:meetingId and t.SING_IN_STATUS = 1)t4");
		this.preparedSql(sb.toString());
		this.setPreValue("meetingId", meetingId);
		return this.getList(MeetingUserDetailsVO.class);
	}
	
	@Override
	public List<MeetingUserVO> getMeetingUsersByVO(Integer type, MeetingUserVO vo)throws SQLException{
		String sql = "select t.*,p.NAME from TB_MEETING_USER t left join TB_PARTY_MENBER_INFO p on t.USER_ID=p.id where t.MEETING_ID=:meetingId";
		switch (type) {
		case 1:
			break;
		case 2:
			sql += " and t.SIGN_UP_STATUS=1";
			break;
		case 3:
			sql += " and t.FOR_LEAVE_STATUS=1";
			break;
		case 4:
			sql += " and t.SIGN_UP_STATUS=1 and t.FOR_LEAVE_STATUS=0 and t.SING_IN_STATUS=0";
			break;
		case 5:
			sql += " and t.SING_IN_STATUS=1";
			break;
		case 6:
			sql += " and t.SING_IN_STATUS=0";
			break;
		default:
			break;
		}
		this.preparedSql(sql);
		this.setPreValue("meetingId", vo.getMeetingId());
		return this.getList(MeetingUserVO.class);
	}

	@Override
	public TbMeetingUserPO getMeetingUserByMeetingId(String meetingId,String userId)throws SQLException{
		String sql = "select m.* from TB_MEETING_USER m where m.MEETING_ID=:meetingId and m.USER_ID=:userId";
		super.preparedSql(sql);
		super.setPreValue("meetingId", meetingId);
		super.setPreValue("userId", userId);
		return super.executeQuery(TbMeetingUserPO.class);
	}
}
