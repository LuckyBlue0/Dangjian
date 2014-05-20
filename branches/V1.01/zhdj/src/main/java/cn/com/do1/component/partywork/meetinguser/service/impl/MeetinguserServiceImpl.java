package cn.com.do1.component.partywork.meetinguser.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.partywork.meetinguser.dao.IMeetinguserDAO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserDetailsVO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;

/**
* All rights reserved.
* User: ${user}
*/
@Service("meetinguserService")
public class MeetinguserServiceImpl extends BaseService implements IMeetinguserService {
    private final static transient Logger logger = LoggerFactory.getLogger(MeetinguserServiceImpl.class);

    private IMeetinguserDAO meetinguserDAO;
    @Resource
    public void setMeetinguserDAO(IMeetinguserDAO meetinguserDAO) {
        this.meetinguserDAO = meetinguserDAO;
        setDAO(meetinguserDAO);
    }

    public Pager searchMeetinguser(Map searchMap,Pager pager) throws Exception, BaseException {
        return meetinguserDAO.pageSearchByField( IBaseDBVO.class,searchMap,null,pager);
    }

	@Override
	public List<MeetingUserVO> getMeetingUsersByMeetingId(String meetingId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return meetinguserDAO.getMeetingUsersByMeetingId(meetingId);
	}

	@Override
	public void deleteMeetingById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		this.meetinguserDAO.deleteMeetingById(id);
	}

	@Override
	public void deleteMeetingByMeetingIdAndUserId(String meetingId, String userId) throws Exception, BaseException {
		this.meetinguserDAO.deleteMeetingByMeetingIdAndUserId(meetingId,userId);
	}

	@Override
	public void delMeetingUserByMeetingId(String meetingId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		this.meetinguserDAO.delMeetingUserByMeetingId(meetingId);
	}

	@Override
	public List<MeetingUserDetailsVO> getMeetingUsersDetailsByMeetingId(String meetingId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return meetinguserDAO.getMeetingUsersDetailsByMeetingId(meetingId);
	}

	@Override
	public List<MeetingUserVO> getMeetingUsersByVO(Integer type, MeetingUserVO vo) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return meetinguserDAO.getMeetingUsersByVO(type, vo);
	}

	@Override
	public TbMeetingUserPO getMeetingUserByMeetingId(String meetingId, String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return  meetinguserDAO.getMeetingUserByMeetingId(meetingId,userId); 
	}
}
