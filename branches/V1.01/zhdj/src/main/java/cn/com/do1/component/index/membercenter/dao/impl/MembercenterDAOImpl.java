package cn.com.do1.component.index.membercenter.dao.impl;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.index.membercenter.dao.IMembercenterDAO;
import cn.com.do1.component.index.membercenter.model.PartyListClientVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class MembercenterDAOImpl extends BaseDAOImpl implements IMembercenterDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(MembercenterDAOImpl.class);
    
    
    public List<TbMeetingPO> getMeetingByUserid(String userid)throws Exception, BaseException{
    	String sql="select t.id,t.title,t.type,t.start_time,t.end_time from tb_meeting t left join tb_meeting_user p on t.id=p.meeting_id " +
    			" where t.WHETHER_END=0 and (t.status=1 or t.status=0) and p.for_leave_status=0 and p.sign_in_status =0 and p.user_id = :userid";
    	super.preparedSql(sql);
    	super.setPreValue("userid", userid);
    	return super.getList(TbMeetingPO.class);
    }
    
    public List<TbPartyLecturePO> getPartyLectureByUserid(String userid)throws Exception, BaseException{
    	String sql="select t.id,t.title,t.type,t.start_time,t.end_time from TB_PARTY_LECTURE t left join tb_meeting_user p on t.id=p.meeting_id " +
    			" where t.WHETHER_END=0 and t.status=1 and p.for_leave_status=0 and p.sign_in_status =0 and p.user_id = :userid";
    	super.preparedSql(sql);
    	super.setPreValue("userid", userid);
    	return super.getList(TbPartyLecturePO.class);
    }
    

    public List<BranchActivityVO> getBranchActivityByUserid(String userid)throws Exception, BaseException{
    	String sql="select t.id,t.title,t.type,t.start_time,t.end_time from tb_branch_activity t left join tb_meeting_user p on t.id=p.meeting_id " +
				" where t.WHETHER_END=0 and t.status=1 and p.for_leave_status =0 and p.sign_in_status =0 and p.user_id = :userid";
        super.preparedSql(sql);
        super.setPreValue("userid", userid);
        return super.getList(BranchActivityVO.class);
    }
    public List<IdeologyReportVO> searchReportByUserid(String userid)throws Exception, BaseException{
    	String sql="select * from tb_ideology_report where create_user_id = :userid and status !=0";
    	super.preparedSql(sql);
    	super.setPreValue("userid", userid);
    	return super.getList(IdeologyReportVO.class);
    }
    public Pager getMeetingListByUserid(Map searchMap, Pager pager) throws Exception, BaseException{
    	String type=(String) searchMap.get("type");
    	searchMap.remove("type");
    	String searchSQL="";
    	String countSQL="";
    	String mobileClient = (String) searchMap.get("mobileClient");
    	searchMap.remove("mobileClient");
    	if("1".equals(type)){
    		searchSQL="select t.*,p.for_leave_status,p.sign_in_status,p.id as meeting_id from tb_meeting t left join tb_meeting_user p on t.id=p.meeting_id" +
    				" where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and  p.user_id = :userid" +
    				" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus)" +
    				" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    				" and t.whether_end =:whetherEnd order by t.whether_end,p.for_leave_status,p.sign_in_status asc";
    		countSQL="select count(*) from tb_meeting t left join tb_meeting_user p on t.id=p.meeting_id where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and  p.user_id = :userid" +
    				" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus) " +
    				" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    				" and t.whether_end =:whetherEnd";
    		if(AssertUtil.isEmpty(mobileClient)){
    			return super.pageSearchByField(MeetingVO.class, countSQL, searchSQL, searchMap, pager);
    		}else{
    			return super.pageSearchByField(PartyListClientVO.class, countSQL, searchSQL, searchMap, pager);
    		}

    	}else if("2".equals(type)){
    		searchSQL="select t.*,p.for_leave_status,p.sign_in_status,p.id as meeting_id from TB_PARTY_LECTURE t left join tb_meeting_user p on t.id=p.meeting_id" +
    				" where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and  p.user_id = :userid" +
    				" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus)" +
    				" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    				" and t.whether_end =:whetherEnd order by t.whether_end,p.sign_in_status asc";
    		countSQL="select count(*) from TB_PARTY_LECTURE t left join tb_meeting_user p on t.id=p.meeting_id where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and  p.user_id = :userid" +
    				" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus) " +
    				" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    				" and t.whether_end =:whetherEnd";
    		if(AssertUtil.isEmpty(mobileClient)){
    			return super.pageSearchByField(PartyLectureVO.class, countSQL, searchSQL, searchMap, pager);
    		}else{
    			return super.pageSearchByField(PartyListClientVO.class, countSQL, searchSQL, searchMap, pager);
    		}
    	}
    	return pager;
    }
 
    public Pager getBranchActivityListByUserid(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select t.*,p.for_leave_status,p.sign_in_status,p.id as meeting_id from tb_branch_activity t left join tb_meeting_user p on t.id=p.meeting_id where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and p.user_id = :userid" +
    			" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus) " +
    			" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    			" and t.whether_end =:whetherEnd order by t.whether_end,p.sign_in_status asc";
    	String countSQL="select count(*) from tb_branch_activity t left join tb_meeting_user p on t.id=p.meeting_id where t.status=:status and (t.title like :keyword or t.create_user_name like :keyword) and  p.user_id = :userid" +
    			" and p.for_leave_status =:forLeaveStatus and p.sign_in_status =:singInStatus and (p.for_leave_status =:secondStatus or p.sign_in_status =:secondStatus) " +
    			" and (p.for_leave_status =:thirdStatus or p.sign_in_status =:thirdStatus or p.sign_up_status=:thirdStatus)"+
    			" and t.whether_end =:whetherEnd";
    	String mobileClient = (String) searchMap.get("mobileClient");
    	searchMap.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
    		return super.pageSearchByField(BranchActivityVO.class, countSQL, searchSQL, searchMap, pager);
    	}else{
    		return super.pageSearchByField(PartyListClientVO.class, countSQL, searchSQL, searchMap, pager);
    	}

    }
    public Pager searchUserReportList(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_ideology_report where create_user_id = :userid and title like :keyword and status =:status and organization_id =:orgId"; 
    	String countSQL="select count(*) from tb_ideology_report where create_user_id = :userid and title like :keyword and status =:status and organization_id =:orgId";
    	return super.pageSearchByField(IdeologyReportVO.class, countSQL, searchSQL, searchMap, pager);
    }
    public boolean searchPositionByUserId(String userid)throws Exception, BaseException{
    	String sql="select count(*) from TB_PARTY_MEMBER_POSITION_REF where party_member_position_id=9 and status=1 and user_id = :userid";
    	super.preparedSql(sql);
    	super.setPreValue("userid", userid);
    	if(super.executeCount()>=1){
    		return true;
    	}else{
    		return false;
    	}
    }

	@Override
	public List<DemocrticLifeVO> getDemocrticLifeByUserid(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		String sql = "select t.id,t.title,t.start_time,t.end_time from TB_DEMOCRATIC_LIFE t left join tb_meeting_user p on t.id=p.meeting_id " + 
					 " where t.WHETHER_END=0 and t.status=1 and p.for_leave_status=0 and p.sign_in_status =0 and p.user_id = :userid";
		super.preparedSql(sql);
		super.setPreValue("userid", userId);
		return super.getList(DemocrticLifeVO.class);
	}

	@Override
	public List<VolunteerVO> getVolunteerByUserId(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		String sql = "select t.id,t.title,t.type,t.start_time,t.end_time from TB_VOLUNTEER_ACTIVITY t left join tb_meeting_user p on t.id=p.meeting_id " +
					 " where t.WHETHER_END=0 and t.status=1 and p.for_leave_status=0 and p.sign_in_status =0 and p.user_id = :userid";
		super.preparedSql(sql);
		super.setPreValue("userid", userId);
		return super.getList(VolunteerVO.class);
	}


} 
