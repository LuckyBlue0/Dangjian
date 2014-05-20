package cn.com.do1.component.index.index.service;

import java.util.List;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-23 上午11:32:35
 * 
 *       All rights reserved
 * 
 */
public interface IOrgIndexService extends IBaseService {
	List<ShortNewsInfoVO> getOrgAnnouncements(String newInfoType, String orgId)
			throws Exception, BaseException;

	List<BranchActivityVO> getOrgDynamic(String orgId) throws Exception,
			BaseException;

	List<VolunteerVO> getVolunteer(String status, String orgId)
			throws Exception, BaseException;

	List<OrganizationScoreVO> getOrgRanking() throws Exception, BaseException;

	public List<ShortNewsInfoVO> searchTopPic(String orgId) throws Exception,
			BaseException;

	public boolean isUserNameExist(String username) throws Exception,
			BaseException;

	/**
	 * 查询某个通知公告前一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public ShortNewsInfoVO searchPartalNews(TbNewsInfoPO news)
			throws Exception, BaseException;

	/**
	 * 查询某个通知公告下一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public ShortNewsInfoVO searchNextNews(TbNewsInfoPO news) throws Exception,
			BaseException;

	/**
	 * 查询某个热点新闻前一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public ShortNewsInfoVO searchPartalHotNews(TbHotNewsPO news)
			throws Exception, BaseException;

	/**
	 * 查询某个热点新闻下一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public ShortNewsInfoVO searchNextHotNews(TbHotNewsPO news)
			throws Exception, BaseException;

	/**
	 * 查询某个支部动态前一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public BranchActivityVO searchPartalDynamic(BranchActivityVO vo)
			throws Exception, BaseException;

	/**
	 * 查询某个支部动态下一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public BranchActivityVO searchNextDynamic(BranchActivityVO vo)
			throws Exception, BaseException;

	/**
	 * 查询某个志愿活动前一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public VolunteerVO searchPartalVolunteer(VolunteerVO po) throws Exception,
			BaseException;

	/**
	 * 查询某个志愿活动下一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public VolunteerVO searchNextVolunteer(VolunteerVO po) throws Exception,
			BaseException;
}
