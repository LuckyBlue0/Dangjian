package cn.com.do1.component.news.newsinfo.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsIndex;
import cn.com.do1.component.news.newsinfo.vo.NewsListInfoRequest;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.news.tissuemieninfo.service.ITissueMienInfoService;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.NewsConfig;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsInfoInterfaceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(NewsInfoInterfaceAction.class);
	@Resource
	private IIndexService indexService;
    @Resource
    private IOrgService orgService;
    @Resource
    private INewsInfoService newsInfoService;
    @Resource
    private ITissueMienInfoService tissueMienInfoService;
	private String requestJson;
	private String newsInfoId;
	private String newsInfoType;
	@Resource
	private INewsTypeService newsTypeService;

	/**
	 * 党讯速递(顶部图片新闻列表,底部多种新闻列表)
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void indexNewsInfo() throws Exception, BaseException {
		//党讯速递顶部图片新闻列表
		addJsonArray("topImgInfos", getTopPics());
		
		//党讯速递底部新闻列表
		addJsonArray("newsInfos", getNewsInfos());
		
		setOuter(getOuter());
	}
	
	/**
	 * 资讯列表
	 * @param parentType  1：通用类，2：支部类
	 * @param newsInfoType 
	 * 当parentType等于1   newsInfoType == (1：通知公告，2：工作动态，3：组织风采 )
	 * 当parentType等于2   newsInfoType == (1：通知公告，2：支部动态 )  查看本组织的资讯
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void newsInfoList() throws BaseException, Exception{
		logger.debug("newsInfoList requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		
    	NewsListInfoRequest newsListInfoRequest = (NewsListInfoRequest)JSONObject.toBean(paramJson,NewsListInfoRequest.class);
    	newsListInfoRequest = (NewsListInfoRequest)CommonAuthManage.authDigestRetObj(newsListInfoRequest,new String []{"parentType","newsInfoType","pageIndex","pageSize"});
    	if(newsListInfoRequest == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		
		String parentType = newsListInfoRequest.getParentType();
		String newsInfoType = newsListInfoRequest.getNewsInfoType();
		if (AssertUtil.isEmpty(newsListInfoRequest.getParentType())) {
			throw new BaseException("9002", "parentType参数不能为空");
		}
		if (AssertUtil.isEmpty(newsListInfoRequest.getNewsInfoType())) {
			throw new BaseException("9002", "newsInfoType");
		}
        Integer pageIndex = Integer.parseInt(newsListInfoRequest.getPageIndex());
        Integer pageSize = Integer.parseInt(newsListInfoRequest.getPageSize());
        if (pageSize.equals(0)) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("mobileClient", "mobileClient");
        //如果不是组织风采新闻
        if(!"3".equals(newsInfoType)){
	       
	        getSearchValue().put("newsInfoType", newsInfoType);
			//通用类,获取所有有新闻摘要，并按置顶、发布时间排序的的新闻列表
			if(parentType.equals("1")){
				pager = newsInfoService.searchNewsinfo(getSearchValue(), pager);
			}else if(parentType.equals("2")){
				//支部类,获取当前用户所属组织的新闻列表(按置顶、发布时间排序,且有新闻摘要)
				IUser user = (IUser)DqdpAppContext.getCurrentUser(getReqeustObj());
				if(user != null){
					OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
					if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
						getSearchValue().put("organizationId", orgVO.getOrganizationId());
					}
					pager = newsInfoService.searchNewsinfo(getSearchValue(), pager);
				}
			}
        }else{
        	pager = tissueMienInfoService.searchTissueMienInfo(getSearchValue(), pager);
        }
		
	    addJsonArray("newsInfos", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 新闻详情页面跳转
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void newsInfoDetails() throws BaseException, Exception{
		logger.debug("newsInfoDetails requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		NewsListInfoRequest request = (NewsListInfoRequest)JSONObject.toBean(paramJson,NewsListInfoRequest.class);
    	request = (NewsListInfoRequest)CommonAuthManage.authDigestRetObj(request,new String []{"newsInfoType,newsInfoId"});
    	if(request == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		if (AssertUtil.isEmpty(request.getNewsInfoType())) {
			throw new BaseException("9002", "newsInfoType参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getNewsInfoId())) { 
			throw new BaseException("9002", "newsInfoId参数不能为空");
		}
		String contentUrl = "/jsp/mobileclient/view.jsp?newsInfoId="+request.getNewsInfoId()+"&newsInfoType="+request.getNewsInfoType();
		addJsonObj("contentUrl", contentUrl);
		setOuter(getOuter());
	}
	
	/**
	 * 预览详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxPreview()throws Exception, BaseException {
		if (AssertUtil.isEmpty(newsInfoId)) {
			throw new BaseException("9002", "newsInfoId参数不能为空");
		}
		if (AssertUtil.isEmpty(newsInfoType)) { 
			throw new BaseException("9002", "newsInfoType参数不能为空");
		}
		NewsPreviewInfoVO perviewInfoVO = newsInfoService.getNewsPreviewInfo(newsInfoId,Integer.parseInt(newsInfoType));
		addJsonObj("perviewInfoVO", perviewInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	
	/**
	 * 党讯速递底部新闻列表(3种新闻资讯,通知公告、工作动态、组织风采)
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	private List<ShortNewsInfoVO> getNewsInfos()throws Exception, BaseException{
		
		List<ShortNewsInfoVO> listNews = new ArrayList<ShortNewsInfoVO>();
		
		//通知公告,工作动态
		for(int i=1;i<=2;i++){
			List<ShortNewsInfoVO> news = indexService.searchNewsByType(i+"",1);
			if(AssertUtil.isEmpty(news)){
				continue;
			}
			ShortNewsInfoVO vo = news.get(0);
			if(i==1){
				vo.setType("通知公告");
				TbNewsColumnTypePO po = indexService.searchNewsTypeId("通知公告");
				if(po!=null){
					vo.setTypeId(po.getType().toString());
				}
			}else if(i==2){
				vo.setType("工作动态");
				TbNewsColumnTypePO po = indexService.searchNewsTypeId("工作动态");
				if(po!=null){
					vo.setTypeId(po.getType().toString());
				}
			}
			if (vo.getTitle().length() > 25) {
				vo.setTitle(vo.getTitle().substring(0, 25) + "...");
			}
			listNews.add(vo);
		}
		//组织风采
		List<ShortNewsInfoVO> tissueMienInfos = tissueMienInfoService.searchTop5TissueMienInfo();
		if(tissueMienInfos != null && tissueMienInfos.size()>0){
			ShortNewsInfoVO vo = tissueMienInfos.get(0);
			vo.setType("组织风采");
			vo.setTypeId("3");
			listNews.add(vo);
		}
		
		
		//党员先锋,群众路线教育实践
		/**
		for(int i=101;i<=102;i++){
			List<ShortNewsInfoVO> news = indexService.ajaxSearchNews(i+"");
			if(news==null){
				continue;
			}
			ShortNewsInfoVO vo = news.get(0);
			TbNewsColumnTypePO po = newsTypeService.getNewsTypeByType(i+"");
			if(po != null){
				vo.setType(po.getName());
				vo.setTypeId(po.getNewsTypeId());
			}
			if (vo.getTitle().length() > 25) {
				vo.setTitle(vo.getTitle().substring(0, 25) + "...");
			}
			listNews.add(vo);
		}
		*/
		return listNews;
	}
	/**
	 * 党讯速递顶部图片新闻列表(5张)
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	private List<ShortNewsInfoVO> getTopPics() throws Exception, BaseException{
		List<ShortNewsInfoVO> listPic = indexService.searchTopHotNews();
		if (!AssertUtil.isEmpty(listPic)) {
			for (ShortNewsInfoVO vo : listPic) {
				if (vo.getTitle().length() > 25) {
					vo.setTitle(vo.getTitle().substring(0, 25) + "...");
				}
				vo.setTypeId("0");
			}
		} else {
			List<ShortNewsInfoVO> listPic1 = new ArrayList<ShortNewsInfoVO>();
			ShortNewsInfoVO vo = new ShortNewsInfoVO();
			vo.setImgPath("jsp/web/images/default.jpg");
			vo.setTitle("暂无新闻");
			listPic1.add(vo);
		}
		return listPic;
	}

	
	/**
	 * 党讯速递(安庆)(顶部图片新闻列表,底部多种新闻列表)
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void aQIndexNewsInfo() throws Exception, BaseException {
		//党讯速递顶部图片新闻列表
		addJsonArray("topImgInfos", getTopPics());
		
		//党讯速递底部新闻列表
		addJsonArray("newsInfos", getAqNewsList());
		
		setOuter(getOuter());
	}
	
	/**
	 * 安庆资讯列表
	 * @param parentType  1：通用类，2：支部类
	 * @param newsTypeId  新闻公告,组工动态,阳光部务,专题中心,阵地园地,掌上期刊,空中课堂
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void aqNewsInfoList() throws BaseException, Exception{
		logger.debug("aqNewsInfoList requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		
    	NewsListInfoRequest newsListInfoRequest = (NewsListInfoRequest)JSONObject.toBean(paramJson,NewsListInfoRequest.class);
    	newsListInfoRequest = (NewsListInfoRequest)CommonAuthManage.authDigestRetObj(newsListInfoRequest,new String []{"newsInfoTypeId","pageIndex","pageSize"});
    	if(newsListInfoRequest == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
		
		String newsInfoTypeId = newsListInfoRequest.getNewsInfoTypeId();
		if (AssertUtil.isEmpty(newsListInfoRequest.getNewsInfoTypeId())) {
			throw new BaseException("9002", "newsInfoTypeId参数不能为空");
		}
        Integer pageIndex = Integer.parseInt(newsListInfoRequest.getPageIndex());
        Integer pageSize = Integer.parseInt(newsListInfoRequest.getPageSize());
        if (pageSize.equals(0)) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
	    getSearchValue().put("newsInfoTypeId", newsInfoTypeId);
	    pager = newsInfoService.searchAqNewsinfo(getSearchValue(), pager);
		
	    addJsonArray("newsInfos", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	
	/**
	 * 安庆党讯速递底部新闻列表(7种新闻资讯,新闻公告、组工动态、阳光部务、专题中心、阵地园地、掌上期刊、空中课堂)
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	private List<ShortNewsInfoVO> getAqNewsList()throws Exception, BaseException{
		List<ShortNewsInfoVO> listNews = new ArrayList<ShortNewsInfoVO>();
		for(NewsIndex newsIndex : NewsConfig.getNewsIndexList()){
			TbNewsColumnTypePO po = indexService.searchNewsTypeId(newsIndex.getTitle());
			if(po == null){
				continue;
			}
			String type = "";
			if(po.getType() != null){
				type = po.getType().toString();
			}
			List<ShortNewsInfoVO> news = indexService.searchAqNewsIndexList(po.getNewsTypeId(),1);
			ShortNewsInfoVO vo = null;
			if(AssertUtil.isEmpty(news)){
				vo = new ShortNewsInfoVO();
			}else{
				vo = news.get(0);
			}
			vo.setType(newsIndex.getTitle());
//			vo.setTypeId(type);
			vo.setTypeId(po.getNewsTypeId());
			vo.setImgPath(newsIndex.getImgPath());
			if (vo.getTitle() != null && vo.getTitle().length() > 25) {
				vo.setTitle(vo.getTitle().substring(0, 25) + "...");
			}
			listNews.add(vo);
		}
		return listNews;
	}

	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void getAqNewsTabs()throws Exception, BaseException{
		logger.debug("getAqNewsTabs requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		
    	NewsListInfoRequest newsTypeRequest = (NewsListInfoRequest)JSONObject.toBean(paramJson,NewsListInfoRequest.class);
    	newsTypeRequest = (NewsListInfoRequest)CommonAuthManage.authDigestRetObj(newsTypeRequest,new String []{"newsInfoTypeId"});
    	if(newsTypeRequest == null){
    		throw new BaseException("1002", "摘要加密值有误!");
    	}
    	String newsInfoTypeId = newsTypeRequest.getNewsInfoTypeId();
    	if(newsInfoTypeId == null){
    		throw new BaseException("9002", "newsInfoTypeId参数不能为空!");
    	}
		
		List<AqNewsListTabVO> newsListTabs = newsTypeService.searchAqNewsListTab(newsInfoTypeId);
		addJsonArray("list",newsListTabs);
	}
	
	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	public String getNewsInfoId() {
		return newsInfoId;
	}

	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}

	public String getNewsInfoType() {
		return newsInfoType;
	}

	public void setNewsInfoType(String newsInfoType) {
		this.newsInfoType = newsInfoType;
	}
}
