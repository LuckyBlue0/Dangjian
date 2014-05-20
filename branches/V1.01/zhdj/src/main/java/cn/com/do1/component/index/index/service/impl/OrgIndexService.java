package cn.com.do1.component.index.index.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.index.index.dao.IOrgIndexDAO;
import cn.com.do1.component.index.index.service.IOrgIndexService;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-23 上午11:33:52
 * 
 *       All rights reserved
 * 
 */
@Service("orgIndexService")
public class OrgIndexService extends BaseService implements IOrgIndexService {

	private IOrgIndexDAO orgIndexDAO;

	@Resource
	public void setOrgIndexDAO(IOrgIndexDAO orgIndexDAO) {
		this.orgIndexDAO = orgIndexDAO;
		setDAO(orgIndexDAO);
	}

	@Override
	public List<ShortNewsInfoVO> getOrgAnnouncements(String newInfoType,
			String orgId) throws Exception, BaseException {
		return orgIndexDAO.getOrgAnnouncements(newInfoType, orgId);
	}

	@Override
	public List<BranchActivityVO> getOrgDynamic(String orgId) throws Exception,
			BaseException {
		return orgIndexDAO.getOrgDynamic(orgId);
	}

	@Override
	public List<OrganizationScoreVO> getOrgRanking() throws Exception,
			BaseException {
		return orgIndexDAO.getOrgRanking();
	}

	@Override
	public List<VolunteerVO> getVolunteer(String status, String orgId)
			throws Exception, BaseException {
		return orgIndexDAO.getVolunteer(status, orgId);
	}

	@Override
	public List<ShortNewsInfoVO> searchTopPic(String orgId) throws Exception,
			BaseException {
		return orgIndexDAO.searchTopPic(orgId);
	}

	@Override
	public boolean isUserNameExist(String username) throws Exception,
			BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BranchActivityVO searchNextDynamic(BranchActivityVO vo)
			throws Exception, BaseException {
		return orgIndexDAO.searchNextDynamic(vo);
	}

	@Override
	public ShortNewsInfoVO searchNextNews(TbNewsInfoPO news) throws Exception,
			BaseException {
		return orgIndexDAO.searchNextNews(news);
	}

	@Override
	public VolunteerVO searchNextVolunteer(VolunteerVO po) throws Exception,
			BaseException {
		return orgIndexDAO.searchNextVolunteer(po);
	}

	@Override
	public BranchActivityVO searchPartalDynamic(BranchActivityVO vo)
			throws Exception, BaseException {
		return orgIndexDAO.searchPartalDynamic(vo);
	}

	@Override
	public ShortNewsInfoVO searchPartalNews(TbNewsInfoPO news)
			throws Exception, BaseException {
		return orgIndexDAO.searchPartalNews(news);
	}

	@Override
	public VolunteerVO searchPartalVolunteer(VolunteerVO po) throws Exception,
			BaseException {
		return orgIndexDAO.searchPartalVolunteer(po);
	}

	@Override
	public ShortNewsInfoVO searchNextHotNews(TbHotNewsPO news)
			throws Exception, BaseException {
		return orgIndexDAO.searchNextHotNews(news);
	}

	@Override
	public ShortNewsInfoVO searchPartalHotNews(TbHotNewsPO news)
			throws Exception, BaseException {
		return orgIndexDAO.searchPartalHotNews(news);
	}

}
