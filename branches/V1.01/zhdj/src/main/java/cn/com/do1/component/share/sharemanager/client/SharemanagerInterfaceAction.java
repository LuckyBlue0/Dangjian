package cn.com.do1.component.share.sharemanager.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.branch.vo.MyActivityListRequest;
import cn.com.do1.component.photowall.photowall.service.IPhotowallService;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeVO;
import cn.com.do1.component.post.postinfo.service.IPostinfoService;
import cn.com.do1.component.share.sharemanager.service.ISharemanagerService;
import cn.com.do1.component.share.sharemanager.vo.ShareRequest;
import cn.com.do1.component.share.sharemanager.vo.TeamRequest;
import cn.com.do1.component.team.teaminfo.service.ITeaminfoService;
import cn.com.do1.component.team.teaminfo.vo.TbTeamInfoVO;
import cn.com.do1.component.util.CommonAuthManage;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class SharemanagerInterfaceAction extends BaseAction{

	private final static transient Logger logger = LoggerFactory.getLogger(SharemanagerInterfaceAction.class);
	@Resource
	private ISharemanagerService sharemanagerService;
	@Resource
	private IPostinfoService postinfoService;
	@Resource
	private ITeaminfoService teaminfoService;
	
	private String requestJson;
	/**
	 * 分享类型列表
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "分享类型列表获取成功", faileMsg = "分享类型列表获取失败"))
	public void getShareTypeList()throws BaseException, Exception{
		 Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
         pager=sharemanagerService .searchTbShareInfo(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 分享-帖子信息列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "帖子信息列表获取成功", faileMsg = "帖子信息列表获取失败"))
	public void getShareInfoList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String shareId = request.getParameter("shareId");
		String keyword = request.getParameter("keyword");
		if (AssertUtil.isEmpty(shareId)) {
			throw new BaseException("9002", "shareId参数不能为空");
		}
		if(keyword!=null&keyword!=""){
			getSearchValue().put("keyword", "%"+keyword+"%");
		}
		int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count") == null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("shareId", shareId);
        pager=postinfoService .searchTbPostInfo(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 分享-回帖信息列表
	 * @throws BaseException 
	 * @throws Exception 
	 *//*
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "回帖信息列表获取成功", faileMsg = "回帖信息列表获取失败"))
	public void getReplyInfoList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count") == null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("userId", userId);
        pager=postinfoService .searchPostData(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	*/
	/**
	 * 分享-帖子详情列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "帖子详情获取成功", faileMsg = "帖子详情获取失败"))
	public void getShareDetailList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count")== null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("id", id);
        pager=postinfoService .searchTbPostDetail(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 近期发帖列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "近期发帖获取成功", faileMsg = "近期发帖获取失败"))
	public void getPostList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count")== null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("userId", userId);
        pager=postinfoService .searchPostData(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}	
	/**
	 * 近期回帖列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "近期发帖获取成功", faileMsg = "近期发帖获取失败"))
	public void getReplyList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count")== null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("userId", userId);
        pager=postinfoService .searchReplyData(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}	
	/**
	 * 分享-发贴
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "发布成功", faileMsg = "发布失败"))
	public void postShare() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String postUserId = request.getParameter("postUserId");
		String title = request.getParameter("title");
		String shareId = request.getParameter("shareId");
		String context = request.getParameter("context");
		if (AssertUtil.isEmpty(postUserId)) {
			throw new BaseException("9002", "postUserId参数不能为空");
		}
		/*logger.debug("postShare requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		ShareRequest request = (ShareRequest) JSONObject.toBean(paramJson, ShareRequest.class);*/
		if (AssertUtil.isEmpty(shareId)) {
			throw new BaseException("9002", "shareId参数不能为空");
		}
		if (AssertUtil.isEmpty(context)) {
			throw new BaseException("9002", "context参数不能为空");
		}
		this.sharemanagerService.postShare(postUserId,shareId,context,title);
		addJsonObj("status", 0); 
	}
	/**
	 * 分享-回复
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "回帖成功", faileMsg = "回帖失败"))
	public void replyShare() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String replyUserId = request.getParameter("replyUserId");
		String postId = request.getParameter("postId");
		String context = request.getParameter("context");
		if (AssertUtil.isEmpty(replyUserId)) {
			throw new BaseException("9002", "replyUserId参数不能为空");
		}
		if (AssertUtil.isEmpty(postId)) {
			throw new BaseException("9002", "postId参数不能为空");
		}
		if (AssertUtil.isEmpty(context)) {
			throw new BaseException("9002", "context参数不能为空");
		}
		this.sharemanagerService.replyShare(replyUserId,postId,context);
		addJsonObj("status", 0); 
	}
	
	/**
	 * 集体-列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "集体列表获取成功", faileMsg = "集体列表获取失败"))
	public void getTeamList() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String teamTypeId = request.getParameter("teamTypeId");
		int pageIndex = request.getParameter("page_no")== null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_count")== null ? 10 : Integer.parseInt(request.getParameter("page_count"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("teamTypeId", teamTypeId);
        pager=teaminfoService .searchTbTeamInfo(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("page_no", pager.getCurrentPage());
	    addJsonObj("page_count", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	/**
	 * 集体详情
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "0", successMsg = "集体详情获取成功", faileMsg = "集体详情获取失败"))
	public void getTeamDetail() throws BaseException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		TbTeamInfoVO response = this.teaminfoService.getDetailById(id);
	    addJsonObj("list", response); 
	}	
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
}
