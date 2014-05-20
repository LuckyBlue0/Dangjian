package cn.com.do1.component.index.wap.ui;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.index.wap.service.IWapService;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.util.Md5;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class WapAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(WapAction.class);
    private IWapService wapService;
    private String ids[];
    private String id;
    private String newInfoType;
    private LoginUserVO uservo;
    private String type;
    @Resource
    private IIndexService indexService;

    public IWapService getWapService() {
        return wapService;
    }

    @Resource
    public void setWapService(IWapService wapService) {
        this.wapService = wapService;
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
     * wap首页图片展示
     * @throws Exception
     * @throws BaseException
     */
    public String searchTopPic()throws Exception, BaseException{
    	List<ShortNewsInfoVO> listPic=indexService.searchTopPic();
    	getReqeustObj().setAttribute("listPic", listPic);
    	return "index";
    }
    /**
     * 根据传进来的类型查询wap新闻列表
     * @throws Exception
     * @throws BaseException
     */
    public String searchNews()throws Exception, BaseException{
    	List<ShortNewsInfoVO> listNews=indexService.ajaxSearchNews(newInfoType);
    	if(!AssertUtil.isEmpty(listNews)){
    		for(ShortNewsInfoVO vo:listNews){
    			if("1".equals(newInfoType)){
    				vo.setType("通知公告");
    			}
    			if("2".equals(newInfoType)){
    				vo.setType("工作动态");
    			}
    		}
    	}
    	getReqeustObj().setAttribute("listNews", listNews);
    	return "list";
    }
    /**
     * wap前台查看新闻类型详情
     * @throws Exception
     * @throws BaseException
     */
    public String searchNewsDetail()throws Exception, BaseException{
    	TbNewsInfoPO news=indexService.searchByPk(TbNewsInfoPO.class, id);
    	getReqeustObj().setAttribute("news", news);
    	return "detail";
    }
    /**
     * 展示新闻类别
     * @throws Exception
     * @throws BaseException
     */
    public String ajaxSearchNewsBySort()throws Exception, BaseException{
    	
    	//List<TbNewsColumnTypePO> listNewType=indexService.searchNewsType("");
    	
    	//getReqeustObj().setAttribute("listNewType", listNewType);
    	return "newsTypeList";
    	
    }
    /**
     * wap注册用户
     * @throws Exception
     * @throws BaseException
     */
    public String  registe()throws Exception, BaseException{
    	return "";
    }
    /**
     * wap用户登录
     * @throws Exception
     * @throws BaseException
     */
    public String login()throws Exception, BaseException{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String opResult="";
    	boolean isVidataPass=true;
    	
    	if(AssertUtil.isEmpty(uservo)) {
    		opResult="error";
    		isVidataPass=false;
        }else if(AssertUtil.isEmpty(uservo.getUsername())){
        		opResult="用户名不能为空";
        		isVidataPass=false;
            
        }else if(AssertUtil.isEmpty(uservo.getUserPwd())){
        		opResult="用户密码不能为空";
        		isVidataPass=false;
    	}
    	
    	if(isVidataPass){
    		//去空格,加密密码
    	
    		uservo.setUsername(uservo.getUsername().trim());
    		Md5 m =new Md5();
    		uservo.setUserPwd(m.getMD5ofStr(uservo.getUserPwd().trim()).toUpperCase());
    		//登录方法
    		LoginUserVO userVO=indexService.loginUser(uservo,type);
    	    if(null != userVO){
    	    	opResult="登录成功";
    	    	loginSuccess(uservo);
    	    }else{
    	    	opResult="用户名或密码错误";
    	    }
    		
    	}
    	getReqeustObj().setAttribute("opResult", opResult);
    	
    	return "index";
    }
    /**
     * 登录成功后处理
     * @param vo 
     * @throws BaseException 
     */
    private void loginSuccess(LoginUserVO vo) throws BaseException{
    	if(!AssertUtil.isEmpty(vo)){

    	getSessionObj().setMaxInactiveInterval(1800);
    	HttpServletRequest request = getReqeustObj();
    	try{
	    	HashMap<String, HttpSession> loginSession = (HashMap<String, HttpSession>) 
	    	request.getSession().getServletContext().getAttribute("loginSession");
	    	
	    	if (loginSession == null) {
	    		loginSession = new HashMap<String, HttpSession>();
	    		request.getSession().getServletContext().setAttribute("loginSession", loginSession);
	    	}
	    	/** 判断是否已经登录 */
			if (loginSession.containsKey(vo.getUsername())) {
				HttpSession oldsession = loginSession.get(vo.getUsername());
				try{
					oldsession.invalidate();
				}catch(IllegalStateException e){
					e.printStackTrace();
				}
				getSessionObj().setAttribute("uservo", vo);
				getSessionObj().setAttribute("isMemberLogin", true);
				loginSession.put(vo.getUsername(), getSessionObj());
			}else{				
				getSessionObj().setAttribute("uservo", vo);
				getSessionObj().setAttribute("isMemberLogin", true);
				loginSession.put(vo.getUsername(), getSessionObj());
			}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	}
    }

	public String getNewInfoType() {
		return newInfoType;
	}

	public void setNewInfoType(String newInfoType) {
		this.newInfoType = newInfoType;
	}

	public LoginUserVO getUservo() {
		return uservo;
	}

	public void setUservo(LoginUserVO uservo) {
		this.uservo = uservo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
    
}
