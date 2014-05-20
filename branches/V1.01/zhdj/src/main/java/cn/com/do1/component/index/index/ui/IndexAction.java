package cn.com.do1.component.index.index.ui;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.report.access.model.TbAccessInfoPO;
import cn.com.do1.component.report.access.model.TbLoginLogPO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.util.Md5;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class IndexAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory
			.getLogger(IndexAction.class);
	private IIndexService indexService;
	private IScoreruleService scoreruleService;
	@Resource
	private INewsInfoService newsInfoService;
	private String ids[];
	private String id;
	private String newInfoType;// 新闻类型
	private String typeName;// 新闻版块顺序
	private String type;// （新闻或登录）类型
	private LoginUserVO uservo;
	private TbPartyDevelopmentMenberPO tbPartyDevelopmentMenberPO;
	private String dqdp_csrf_token;
	private String username;
	private String remember;

	public IIndexService getIndexService() {
		return indexService;
	}

	@Resource
	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 首页图片展示
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSearchTopPic() throws Exception, BaseException {
		List<ShortNewsInfoVO> listPic = indexService.searchTopHotNews();
		LoginUserVO uservo = (LoginUserVO) getReqeustObj().getSession()
				.getAttribute("uservo");
		if (!AssertUtil.isEmpty(uservo)) {
			TbAccessInfoPO accessPO = new TbAccessInfoPO();
			accessPO.setAccessItem("首页");
			accessPO.setAccessTime(new Date());
			accessPO.setAccessType("3");
			accessPO.setUserId(uservo.getUsername());
			accessPO.setIpAddress(getReqeustObj().getRemoteAddr());
			indexService.insertPO(accessPO, true);
		} else {
			TbAccessInfoPO accessPO = new TbAccessInfoPO();
			accessPO.setAccessItem("首页");
			accessPO.setAccessTime(new Date());
			accessPO.setAccessType("3");
			accessPO.setUserId("anonymous");
			accessPO.setIpAddress(getReqeustObj().getRemoteAddr());
			indexService.insertPO(accessPO, true);
		}
		if (!AssertUtil.isEmpty(listPic)) {
			setActionResult("0", "查询成功");
			for (ShortNewsInfoVO vo : listPic) {
				if (vo.getTitle().length() > 30) {
					vo.setTitle(vo.getTitle().substring(0, 30) + "...");
				}
			}
			addJsonArray("listPic", listPic);
			doJsonOut();
		} else {
			List<ShortNewsInfoVO> listPic1 = indexService.searchTopPic();
			ShortNewsInfoVO vo1 = new ShortNewsInfoVO();
			vo1.setImgPath("/jsp/web/images/default.jpg");
			vo1.setTitle("暂无新闻");
			listPic1.add(vo1);
			setActionResult("0", "查询成功");
			addJsonArray("listPic", listPic1);
			doJsonOut();
		}
	}

	/**
	 * 根据传进来的类型分页查询新闻展示
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(),
				getPageSize());
		System.out.println(getSearchValue().get("newInfoType").toString());
		TbNewsColumnTypePO newType = indexService.searchByPk(
				TbNewsColumnTypePO.class, getSearchValue().get("newInfoType")
						.toString());
		getSearchValue().remove("newInfoType");
		getSearchValue().put("newInfoType", newType.getType());
		pager = indexService.ajaxSearch(getSearchValue(), pager);
		LoginUserVO uservo = (LoginUserVO) getReqeustObj().getSession()
				.getAttribute("uservo");
		if (!AssertUtil.isEmpty(uservo)) {
			TbAccessInfoPO accessPO = new TbAccessInfoPO();
			accessPO.setAccessItem(newType.getName());
			accessPO.setAccessTime(new Date());
			accessPO.setAccessType("3");
			accessPO.setUserId(uservo.getUsername());
			accessPO.setIpAddress(getReqeustObj().getRemoteAddr());
			indexService.insertPO(accessPO, true);
		} else {
			TbAccessInfoPO accessPO = new TbAccessInfoPO();
			accessPO.setAccessItem(newType.getName());
			accessPO.setAccessTime(new Date());
			accessPO.setAccessType("3");
			accessPO.setUserId("anonymous");
			accessPO.setIpAddress(getReqeustObj().getRemoteAddr());
			indexService.insertPO(accessPO, true);
		}
		addJsonObj("newsType", newType.getName());
		addJsonPager("pageData", pager);
	}

	public IScoreruleService getScoreruleService() {
		return scoreruleService;
	}

	public void setScoreruleService(IScoreruleService scoreruleService) {
		this.scoreruleService = scoreruleService;
	}

	/**
	 * 根据传进来的类型查询首页新闻展示
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSearchNews() throws Exception, BaseException {
		List<ShortNewsInfoVO> listNews = indexService
				.ajaxSearchNews(newInfoType);
		if (!AssertUtil.isEmpty(listNews)) {
			for (ShortNewsInfoVO vo : listNews) {
				if ("1".equals(newInfoType)) {
					vo.setType("通知公告");
					TbNewsColumnTypePO po = indexService
							.searchNewsTypeId("通知公告");
					vo.setTypeId(po.getNewsTypeId());
					addJsonObj("newsTypeId", vo.getTypeId());
					addJsonObj("newsType", vo.getType());
				}
				if ("2".equals(newInfoType)) {
					vo.setType("工作动态");
					TbNewsColumnTypePO po = indexService
							.searchNewsTypeId("工作动态");
					vo.setTypeId(po.getNewsTypeId());
					addJsonObj("newsTypeId", vo.getTypeId());
					addJsonObj("newsType", vo.getType());
				}
			}
		}else{
			if ("1".equals(newInfoType)) {	
				addJsonObj("newsType", "通知公告");
			}
			if ("2".equals(newInfoType)) {
				addJsonObj("newsType", "工作动态");
			}
		}
		setActionResult("0", "查询成功");
		addJsonArray("listNews", listNews);
		doJsonOut();
	}

	/**
	 * 根据前台的顺序查询新闻展示
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSearchNewsBySort() throws Exception, BaseException {
		String typename = URLDecoder.decode(typeName, "UTF-8");
		TbNewsColumnTypePO newType = indexService.searchNewsType(typename);
		if (!AssertUtil.isEmpty(newType)) {

			List<ShortNewsInfoVO> listNews = indexService.ajaxSearchNews(String
					.valueOf(newType.getType()));
			if (!AssertUtil.isEmpty(listNews)) {
				for (ShortNewsInfoVO vo : listNews) {
					vo.setType(newType.getName());
					vo.setTypeId(newType.getNewsTypeId());
					addJsonObj("newsTypeId", vo.getTypeId());
					addJsonObj("newsType", vo.getType());
				}
			} else {
				ShortNewsInfoVO vo1 = new ShortNewsInfoVO();
				vo1.setType(newType.getName());
				vo1.setTypeId(newType.getNewsTypeId());
				addJsonObj("newsTypeId", vo1.getTypeId());
				addJsonObj("newsType", vo1.getType());
			}
			setActionResult("0", "查询成功");
			addJsonArray("listNews", listNews);
			doJsonOut();
		}
		setActionResult("0", "查询成功");
		addJsonArray("listNews", null);
		doJsonOut();

	}

	/**
	 * 前台查看新闻类型详情
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void searchNewsDetail() throws Exception, BaseException {
		NewsPreviewInfoVO news = newsInfoService.getNewsPreviewInfo(id,2);
		TbNewsColumnTypePO po = indexService.searchByPk(
				TbNewsColumnTypePO.class, newInfoType);
		addJsonObj("newsType", po.getName());
		addJsonObj("news", news);
		ShortNewsInfoVO partalNews = indexService.searchPartalNews(id);// 查询前一条新闻
		ShortNewsInfoVO nextNews = indexService.searchNextNews(id);// 查询下一条新闻
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
	public void searchHotNewsDetail() throws Exception, BaseException {
		TbHotNewsPO news = indexService.searchByPk(TbHotNewsPO.class, id);
		addJsonFormateObj("news", news);

		setActionResult("0", "查询成功");
		doJsonOut();
	}

	public void searchNewsType() throws Exception, BaseException {
		TbNewsColumnTypePO po = indexService.searchByPk(
				TbNewsColumnTypePO.class, newInfoType);
		addJsonObj("newsType", po.getName());
		setActionResult("0", "查询成功");
		doJsonOut();
	}

	public void checkUserName() throws Exception, BaseException {
		boolean result = indexService.isUserNameExist(username);
		if (result) {
			setActionResult("0", "true");

		} else {
			setActionResult("0", "false");
		}
		doJsonOut();
	}

	/**
	 * 前台注册用户
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void registe() throws Exception, BaseException {
		tbPartyDevelopmentMenberPO.setPassWord(new Md5().getMD5ofStr(tbPartyDevelopmentMenberPO.getPassWord())
				.toLowerCase());
		tbPartyDevelopmentMenberPO.setState(1l);
		tbPartyDevelopmentMenberPO.setCreateTime(new Date());
		tbPartyDevelopmentMenberPO.setOrganizationIdentity("1");
		this.indexService.insertPO(tbPartyDevelopmentMenberPO, true);
		setActionResult("0", "注册成功");
		getSessionObj().invalidate();
		doJsonOut();
	}

	/**
	 * 前台用户登录
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	public void login() throws Exception, BaseException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String opResult = "";
		boolean isVidataPass = true;

		if (AssertUtil.isEmpty(uservo)) {
			opResult = "error";
			isVidataPass = false;
		} else if (AssertUtil.isEmpty(uservo.getUsername())) {
			opResult = "用户名不能为空";
			setActionResult("1001", "用户名不能为空");
			isVidataPass = false;

		} else if (AssertUtil.isEmpty(uservo.getUserPwd())) {
			setActionResult("1001", "用户密码不能为空");
			isVidataPass = false;
		}

		if (isVidataPass) {
			// 去空格,加密密码
            String userPwd=uservo.getUserPwd();
			uservo.setUsername(uservo.getUsername().trim());
			Md5 m = new Md5();
			uservo.setUserPwd(m.getMD5ofStr(uservo.getUserPwd().trim())
					.toLowerCase());
			// 登录方法
			LoginUserVO userLoginvo = indexService.loginUser(uservo, type);
			if (null != userLoginvo) {
				opResult = "登录成功";
				setActionResult("0", "登录成功");
				//判断是否要记住密码
				
				// 记录登录日志
				TbLoginLogPO logPO = new TbLoginLogPO();
				logPO.setLoginTime(new Date());
				logPO.setStatus("登录成功!");
				logPO.setUserName(userLoginvo.getUsername());
				indexService.insertPO(logPO, true);
				loginSuccess(userLoginvo);

				if(!indexService.checkLonginScoreToday(userLoginvo.getUserId(), "1")){
					// 记录加分情况
					TbPersonalScoreInfoPO score = new TbPersonalScoreInfoPO();
					ExItemObj obj = DictOperater.getItem("personalScore", "2");//网页登录
					TbScoreRulePO scoreRule = scoreruleService.searchByType(obj.getFsDictItemId());
					score.setId(UUID.randomUUID().toString());
					score.setUserId(userLoginvo.getUserId());
					score.setScoreType("2");
					if (null != scoreRule
							&& !StringUtil.isNullEmpty(scoreRule.getScoreValue())) {
						score.setScore(Long.parseLong(scoreRule.getScoreValue()));
					} else {
						score.setScore(0L);
					}
					score.setGetTime(new Date());
					score.setScoreDesc(obj.getFsItemDesc());
					indexService.insertPO(score, true);
				}
			} else {
				setActionResult("1001", "用户名或密码错误");
				opResult = "用户名或密码错误";
				// 记录登录日志
				TbLoginLogPO logPO = new TbLoginLogPO();
				logPO.setLoginTime(new Date());
				logPO.setStatus("登录失败，用户名或密码错误!");
				logPO.setUserName(uservo.getUsername());
				indexService.insertPO(logPO, true);
			}

		}
		addJsonObj("opResult", opResult);

		doJsonOut();
	}

	/**
	 * 登录成功后处理
	 * 
	 * @param vo
	 * @throws BaseException
	 */
	private void loginSuccess(LoginUserVO vo) throws BaseException {
		if (!AssertUtil.isEmpty(vo)) {

			getSessionObj().setMaxInactiveInterval(1800);
			HttpServletRequest request = getReqeustObj();
			try {
				HashMap<String, HttpSession> loginSession = (HashMap<String, HttpSession>) request
						.getSession().getServletContext().getAttribute(
								"loginSession");

				if (loginSession == null) {
					loginSession = new HashMap<String, HttpSession>();
					request.getSession().getServletContext().setAttribute(
							"loginSession", loginSession);
				}
				/** 判断是否已经登录 */
				if (loginSession.containsKey(vo.getUsername())) {
					HttpSession oldsession = loginSession.get(vo.getUsername());
					try {
						oldsession.invalidate();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					getSessionObj().setAttribute("uservo", vo);
					getSessionObj().setAttribute("isMemberLogin", true);
					loginSession.put(vo.getUsername(), getSessionObj());
				} else {
					getSessionObj().setAttribute("uservo", vo);
					getSessionObj().setAttribute("isMemberLogin", true);
					loginSession.put(vo.getUsername(), getSessionObj());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
	public void loginOut()throws Exception, BaseException {
		getSessionObj().invalidate();
		
	}

	public String getNewInfoType() {
		return newInfoType;
	}

	public void setNewInfoType(String newInfoType) {
		this.newInfoType = newInfoType;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public LoginUserVO getUservo() {
		return uservo;
	}

	public void setUservo(LoginUserVO uservo) {
		this.uservo = uservo;
	}

	public void setDqdp_csrf_token(String dqdp_csrf_token) {
		this.dqdp_csrf_token = dqdp_csrf_token;
	}

	public String getDqdp_csrf_token() {
		return dqdp_csrf_token;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public TbPartyDevelopmentMenberPO getTbPartyDevelopmentMenberPO() {
		return tbPartyDevelopmentMenberPO;
	}

	public void setTbPartyDevelopmentMenberPO(
			TbPartyDevelopmentMenberPO tbPartyDevelopmentMenberPO) {
		this.tbPartyDevelopmentMenberPO = tbPartyDevelopmentMenberPO;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	public String getRemember() {
		return remember;
	}

}
