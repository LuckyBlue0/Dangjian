package cn.com.do1.component.index.membercenter.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.index.membercenter.dao.IMembercenterDAO;
import cn.com.do1.component.index.membercenter.model.PartyMenberScoreVO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreInfoVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("membercenterService")
public class MembercenterServiceImpl extends BaseService implements IMembercenterService {
    private final static transient Logger logger = LoggerFactory.getLogger(MembercenterServiceImpl.class);

    private IMembercenterDAO membercenterDAO;
    @Resource
    public void setMembercenterDAO(IMembercenterDAO membercenterDAO) {
        this.membercenterDAO = membercenterDAO;
        setDAO(membercenterDAO);
    }
    @Resource
	private IPartymenberService partymenberService;
    @Resource
    private IPersonalscoreService personalscoreService;

    public Pager searchMembercenter(Map searchMap,Pager pager) throws Exception, BaseException {
        return membercenterDAO.pageSearchByField( IBaseDBVO.class,searchMap,null,pager);
    }

    public List<TbMeetingPO> getMeetingByUserid(String userid)throws Exception, BaseException{
    	return membercenterDAO.getMeetingByUserid(userid);
    }

    public List<BranchActivityVO> getBranchActivityByUserid(String userid)throws Exception, BaseException{
    	return membercenterDAO.getBranchActivityByUserid(userid);
    }

    public List<IdeologyReportVO> searchReportByUserid(String userid)throws Exception, BaseException{
    	return membercenterDAO.searchReportByUserid(userid);
    }
    public PartyMenberScoreVO searchPersonalScore(TbPartyMenberInfoPO userpo,Map searchMap,Pager pager)throws Exception, BaseException{
    	PartyMenberScoreVO scorevo=new PartyMenberScoreVO();
    	pager =personalscoreService.searchPersonalscoreInfo(searchMap,pager);
		scorevo.setUsername(userpo.getName());
		if(!AssertUtil.isEmpty(userpo.getPortraitPic())){
			scorevo.setImgPath(userpo.getPortraitPic());
		}else{
			scorevo.setImgPath("image/man.gif");
		}
		if(!AssertUtil.isEmpty(pager.getPageData())){
		List<PersonalScoreInfoVO> list=(List<PersonalScoreInfoVO>) pager.getPageData();
		for(PersonalScoreInfoVO vo:list){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		String time = df.format(new Date().getTime()-1*24*60*60*1000);
		if(time.equals(vo.getGetTime())){
    		scorevo.setLastdayScore(vo.getScore());//查到昨天的积分
		}
		}
		Pager pager1 = new Pager(ServletActionContext.getRequest(),99999);
		Map map=new HashMap();
		map.put("userid", userpo.getId());
		pager1 = personalscoreService.searchPersonalscore(map,pager1);
		if(!AssertUtil.isEmpty(pager1.getPageData())){
			List<PersonalScoreVO> list1=(List<PersonalScoreVO>) pager1.getPageData();
			scorevo.setSort(list1.get(0).getRanking());//积分排名
			scorevo.setTatalScore(list1.get(0).getAccumulativeScore());//总积分
		}
		}else{
			scorevo.setSort("0");
			scorevo.setTatalScore("0");
			scorevo.setLastdayScore("0");
			
		}
		if(AssertUtil.isEmpty(scorevo.getLastdayScore())){
			scorevo.setLastdayScore("0");
		}
		return scorevo;
    }

    public Pager getMeetingListByUserid(Map searchMap, Pager pager) throws Exception, BaseException{
    	return membercenterDAO.getMeetingListByUserid(searchMap, pager);
    }
  
    public Pager getBranchActivityListByUserid(Map searchMap, Pager pager) throws Exception, BaseException{
    	return membercenterDAO.getBranchActivityListByUserid(searchMap, pager);
    }

    public Pager searchUserReportList(Map searchMap, Pager pager) throws Exception, BaseException{
    	return membercenterDAO.searchUserReportList(searchMap, pager);
    }

    public boolean searchPositionByUserId(String userid)throws Exception, BaseException{
    	return membercenterDAO.searchPositionByUserId(userid);
    }

	@Override
	public List<TbPartyLecturePO> getPartyLectureByUserid(String userid) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return membercenterDAO.getPartyLectureByUserid(userid);
	}

	@Override
	public List<DemocrticLifeVO> getDemocrticLifeByUserId(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return membercenterDAO.getDemocrticLifeByUserid(userId);
	}

	@Override
	public List<VolunteerVO> getVolunteerByUserId(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return membercenterDAO.getVolunteerByUserId(userId);
	}
}
