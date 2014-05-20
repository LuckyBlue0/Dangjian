package cn.com.do1.component.index.membercenter.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.index.membercenter.model.PartyMenberScoreVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IMembercenterService extends IBaseService{
    Pager searchMembercenter(Map searchMap, Pager pager) throws Exception, BaseException;
    
    /**
     * 根据用户id查询待办的三会
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<TbMeetingPO> getMeetingByUserid(String userid)throws Exception, BaseException;
    
    /**
     * 根据用户id查询待办的党课
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<TbPartyLecturePO> getPartyLectureByUserid(String userid)throws Exception, BaseException;
    /**
     * 根据用户id查询待办的支部活动
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<BranchActivityVO> getBranchActivityByUserid(String userid)throws Exception, BaseException;
    
    /**
     * 根据用户id查询待办的民主生活会
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<DemocrticLifeVO> getDemocrticLifeByUserId(String userId)throws Exception, BaseException;
    
    /**
     * 根据用户id查询待办的志愿活动
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<VolunteerVO> getVolunteerByUserId(String userId)throws Exception, BaseException;
    
    /**
     * 根据用户id查询已经审核的思想汇报
     * @param userid
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<IdeologyReportVO> searchReportByUserid(String userid)throws Exception, BaseException;
    /**
     * 根据用户id查询个人积分
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public PartyMenberScoreVO searchPersonalScore(TbPartyMenberInfoPO userpo,Map searchMap,Pager pager)throws Exception, BaseException;
    
    /**
     * 查询我的党课和我的会议
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public Pager getMeetingListByUserid(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查询我的支部活动
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public Pager getBranchActivityListByUserid(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查询我的思想汇报
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public Pager searchUserReportList(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 判断用户是否为支部书记
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public boolean searchPositionByUserId(String userid)throws Exception, BaseException;

    
}
