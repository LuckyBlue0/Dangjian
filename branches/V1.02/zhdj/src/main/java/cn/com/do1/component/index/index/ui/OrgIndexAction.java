package cn.com.do1.component.index.index.ui;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.index.index.service.IOrgIndexService;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.leader.org.service.IOrgManageService;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.hotnewsinfo.service.IHotNewsInfoService;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.service.IBranchService;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.volunteer.service.IVolunteerService;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.orgscore.service.IOrgscoreService;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-23 上午10:09:35
 * 
 *       All rights reserved
 * 
 */
public class OrgIndexAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrgIndexAction.class);
	private IOrgIndexService orgIndexService;
	// 组织
	private IOrgManageService orgManageService;
	// 热点新闻
	private IHotNewsInfoService hotNewsInfoService;
	// 通知公告
	private INewsInfoService newsInfoService;
	// 支部动态
	private IBranchService branchService;
	// 志愿活动
	private IVolunteerService volunteerService;
	// 组织积分
	private IOrgscoreService orgscoreService;
	// 组织ID
	private String orgId;
	private String id;

	/**
	 * 首页通知公告
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgAnnouncementsIndex() throws Exception, BaseException {
		List<ShortNewsInfoVO> newsList = orgIndexService.getOrgAnnouncements(
				"1", orgId);
		setActionResult("0", "查询成功");
		addJsonArray("newsList", newsList);
		doJsonOut();
	}

	/**
	 * 首页支部动态
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgDynamicIndex() throws Exception, BaseException {
		List<BranchActivityVO> dynamicList = orgIndexService
				.getOrgDynamic(orgId);
		setActionResult("0", "查询成功");
		addJsonArray("dynamicList", dynamicList);
		doJsonOut();
	}

	/**
	 * 首页志愿活动
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgVolunteerIndex() throws Exception, BaseException {
		List<VolunteerVO> volunteerList = orgIndexService.getVolunteer("2",
				orgId);
		setActionResult("0", "查询成功");
		addJsonArray("volunteerList", volunteerList);
		doJsonOut();
	}

	/**
	 * 首页支部排行榜
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgRankingIndex() throws Exception, BaseException {
		List<OrganizationScoreVO> rankingList = orgIndexService.getOrgRanking();
		setActionResult("0", "查询成功");
		addJsonArray("rankingList", rankingList);
		doJsonOut();
	}

	/**
	 * 首页图片展示
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearchTopPic() throws Exception, BaseException {
		List<ShortNewsInfoVO> picList = orgIndexService.searchTopPic(orgId);
		setActionResult("0", "查询成功");
		addJsonArray("picList", picList);
		doJsonOut();
	}

	/**
	 * 首页支部信息简介
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void orgInfo() throws Exception, BaseException {
		TbOrganizationPO organization = orgManageService.searchByPk(
				TbOrganizationPO.class, orgId);
		if (!StringUtil.isNullEmpty(organization.getRemark())
				&& organization.getRemark().length() > 90) {
			organization.setRemark(organization.getRemark().substring(0, 90)
					+ "…");
		}
		setActionResult("0", "查询成功");
		addJsonObj("organization", organization);
		doJsonOut();
	}

	/**
	 * 首页党员风采
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void partyMenber() throws Exception, BaseException {

	}

	/**
	 * 根据传进来的类型分页查询通知公告
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxNewsSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		try {
			pager = newsInfoService.searchNewsinfo(getSearchValue(), pager);
			addJsonPager("pageData", pager);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	/**
	 * 根据传进来的类型分页查询热点新闻
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxHotNewsSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		try {
			pager = hotNewsInfoService.searchHotNewsInfo(getSearchValue(),
					pager);
			addJsonPager("pageData", pager);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 前台查看通知公告详情
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchNewsDetail() throws Exception, BaseException {
		TbNewsInfoPO news = orgIndexService.searchByPk(TbNewsInfoPO.class, id);
		addJsonFormateObj("news", news);
		ShortNewsInfoVO partalNews = orgIndexService.searchPartalNews(news);// 查询前一条新闻
		ShortNewsInfoVO nextNews = orgIndexService.searchNextNews(news);// 查询下一条新闻
		if (!AssertUtil.isEmpty(partalNews)) {
			addJsonObj("partalNews", partalNews);
		} else {
			ShortNewsInfoVO newpartalNews = new ShortNewsInfoVO();
			newpartalNews.setTitle("暂无");
			addJsonObj("partalNews", newpartalNews);
		}
		if (!AssertUtil.isEmpty(nextNews)) {
			addJsonObj("nextNews", nextNews);
		} else {
			ShortNewsInfoVO newnextNews = new ShortNewsInfoVO();
			newnextNews.setTitle("暂无");
			addJsonObj("nextNews", newnextNews);
		}
		setActionResult("0", "查询成功");
		doJsonOut();
	}

	/**
	 * 查看热点新闻详情
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchHotNewsDetail() throws Exception, BaseException {
		TbHotNewsPO news = orgIndexService.searchByPk(TbHotNewsPO.class, id);
		addJsonFormateObj("news", news);
		ShortNewsInfoVO partalNews = orgIndexService.searchPartalHotNews(news);// 查询前一条新闻
		ShortNewsInfoVO nextNews = orgIndexService.searchNextHotNews(news);// 查询下一条新闻
		if (!AssertUtil.isEmpty(partalNews)) {
			addJsonObj("partalNews", partalNews);
		} else {
			ShortNewsInfoVO newpartalNews = new ShortNewsInfoVO();
			newpartalNews.setTitle("暂无");
			addJsonObj("partalNews", newpartalNews);
		}
		if (!AssertUtil.isEmpty(nextNews)) {
			addJsonObj("nextNews", nextNews);
		} else {
			ShortNewsInfoVO newnextNews = new ShortNewsInfoVO();
			newnextNews.setTitle("暂无");
			addJsonObj("nextNews", newnextNews);
		}
		setActionResult("0", "查询成功");
		doJsonOut();
	}

	/**
	 * 根据传进来的类型分页查询支部动态
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxDynamicSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		try {
			pager = branchService.searchBranch(getSearchValue(), pager);
			addJsonPager("pageData", pager);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	/**
	 * 前台查看支部动态详情
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchDynamicDetail() throws Exception, BaseException {
		BranchActivityVO dynamic = branchService.getBranchActivityById(id);
		addJsonObj("dynamic", dynamic);
		BranchActivityVO partalDynamic = orgIndexService
				.searchPartalDynamic(dynamic);// 查询前一条新闻
		BranchActivityVO nextDynamic = orgIndexService
				.searchNextDynamic(dynamic);// 查询下一条新闻
		if (!AssertUtil.isEmpty(partalDynamic)) {
			addJsonObj("partalDynamic", partalDynamic);
		} else {
			TbBranchActivityPO newPartalNews = new TbBranchActivityPO();
			newPartalNews.setTitle("暂无");
			addJsonObj("partalDynamic", newPartalNews);
		}
		if (!AssertUtil.isEmpty(nextDynamic)) {
			addJsonObj("nextDynamic", nextDynamic);
		} else {
			TbBranchActivityPO newNextDynamic = new TbBranchActivityPO();
			newNextDynamic.setTitle("暂无");
			addJsonObj("nextDynamic", newNextDynamic);
		}
		setActionResult("0", "查询成功");
		doJsonOut();
	}

	/**
	 * 根据传进来的类型分页查询志愿活动
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxVolunteerSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		try {
			pager = volunteerService.searchVolunteer(getSearchValue(), pager);
			addJsonPager("pageData", pager);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	/**
	 * 前台查看志愿活动详情
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchVolunteerDetail() throws Exception, BaseException {
		VolunteerVO volunteer = volunteerService.getVolunteerById(id);
		addJsonObj("volunteer", volunteer);
		VolunteerVO partalVolunteer = orgIndexService
				.searchPartalVolunteer(volunteer);// 查询前一条新闻
		VolunteerVO nextVolunteer = orgIndexService
				.searchNextVolunteer(volunteer);// 查询下一条新闻
		if (!AssertUtil.isEmpty(partalVolunteer)) {
			addJsonObj("partalVolunteer", partalVolunteer);
		} else {
			TbBranchActivityPO newPartalVolunteer = new TbBranchActivityPO();
			newPartalVolunteer.setTitle("暂无");
			addJsonObj("partalVolunteer", newPartalVolunteer);
		}
		if (!AssertUtil.isEmpty(nextVolunteer)) {
			addJsonObj("nextVolunteer", nextVolunteer);
		} else {
			TbBranchActivityPO newNextVolunteer = new TbBranchActivityPO();
			newNextVolunteer.setTitle("暂无");
			addJsonObj("nextVolunteer", newNextVolunteer);
		}
		setActionResult("0", "查询成功");
		doJsonOut();
	}

	/**
	 * 分页查询积分排行
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxScoreRankSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		try {
			pager = orgscoreService.searchOrgscore(getSearchValue(), pager);
			addJsonPager("pageData", pager);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void getOrgScoreInfo() throws Exception, BaseException {
		try {
			OrganizationScoreVO orgScore = orgscoreService
					.getOrgScoreByOrgId(orgId);
			TbOrganizationPO orgPo = orgscoreService.searchByPk(
					TbOrganizationPO.class, orgId);
			addJsonObj("orgScore", orgScore);
			addJsonObj("orgPo", orgPo);
			setActionResult("0", "查询成功");
		} catch (Exception e) {
			setActionResult("1001", "查询失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}

	public IOrgManageService getOrgManageService() {
		return orgManageService;
	}

	public void setOrgManageService(IOrgManageService orgManageService) {
		this.orgManageService = orgManageService;
	}

	public INewsInfoService getNewsInfoService() {
		return newsInfoService;
	}

	public void setNewsInfoService(INewsInfoService newsInfoService) {
		this.newsInfoService = newsInfoService;
	}

	public IVolunteerService getVolunteerService() {
		return volunteerService;
	}

	public void setVolunteerService(IVolunteerService volunteerService) {
		this.volunteerService = volunteerService;
	}

	public IOrgIndexService getOrgIndexService() {
		return orgIndexService;
	}

	public IOrgscoreService getOrgscoreService() {
		return orgscoreService;
	}

	public void setOrgscoreService(IOrgscoreService orgscoreService) {
		this.orgscoreService = orgscoreService;
	}

	public void setOrgIndexService(IOrgIndexService orgIndexService) {
		this.orgIndexService = orgIndexService;
	}

	public IBranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}

	public String getOrgId() {
		return orgId;
	}

	public IHotNewsInfoService getHotNewsInfoService() {
		return hotNewsInfoService;
	}

	public void setHotNewsInfoService(IHotNewsInfoService hotNewsInfoService) {
		this.hotNewsInfoService = hotNewsInfoService;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
