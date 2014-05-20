package cn.com.do1.component.photowall.photowall.client;

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
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.photowall.photowall.service.IPhotowallService;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeVO;
import cn.com.do1.component.util.CommonAuthManage;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PhotowallInterfaceAction extends BaseAction{

	private final static transient Logger logger = LoggerFactory.getLogger(PhotowallInterfaceAction.class);
	@Resource
	private IPhotowallService photowallService;
	private String requestJson;
	
	
	/**
	 * 留影墙类型列表
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "留影墙类型列表获取成功", faileMsg = "留影墙类型列表获取失败"))
	public void typeList()throws BaseException, Exception{
		ArrayList<ExItemObj> dicts = DictOperater.getAllItemByType("photowallType");
		if(!AssertUtil.isEmpty(dicts)){
			ArrayList<PhotowallTypeVO> list = new ArrayList<PhotowallTypeVO>();
			PhotowallTypeVO type = null;
			for(ExItemObj dict : dicts){
				type = new PhotowallTypeVO();
				type.setTypeId(dict.getFsItemCode());
				type.setName(dict.getFsItemDesc());
				list.add(type);
			}
			addJsonArray("list", list);
		}
	}
	/**
	 * 留影墙列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "留影墙列表获取成功", faileMsg = "留影墙列表获取失败"))
	public void photowallList() throws BaseException, Exception{
		logger.debug("photowallList requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PhotowallTypeRequest request = (PhotowallTypeRequest) JSONObject.toBean(paramJson, PhotowallTypeRequest.class);
		request = (PhotowallTypeRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "typeId","keyword","pageIndex","pageSize"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		} 
		if (AssertUtil.isEmpty(request.getTypeId())) {
			throw new BaseException("9002", "typeId参数不能为空");
		}
		String typeId = request.getTypeId();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("type", typeId);
        getSearchValue().put("keyword", request.getKeyword());
        getSearchValue().put("mobileClient", "mobileClient");
		pager = photowallService.searchTbPhotoWall(getSearchValue(), pager);
	    addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	
	/**
	 * 留影墙详情
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "留影墙详情获取成功", faileMsg = "留影墙详情获取失败"))
	public void photowallView() throws BaseException, Exception{
		logger.debug("photowallView requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PhotowallTypeRequest request = (PhotowallTypeRequest) JSONObject.toBean(paramJson, PhotowallTypeRequest.class);
		request = (PhotowallTypeRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getId())) {
			throw new BaseException("9002", "id参数不能为空");
		}
		PhotoWallDetailsResponse response = this.photowallService.findPhotoWallById(request.getId());
	    addJsonObj("data", response); 
	}
	
	/**
	 * 留影墙发布
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "发布成功", faileMsg = "发布失败"))
	public void pushPhotowall() throws BaseException, Exception{
		logger.debug("pushPhotowall requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PhotowallTypeRequest request = (PhotowallTypeRequest) JSONObject.toBean(paramJson, PhotowallTypeRequest.class);
		request = (PhotowallTypeRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","title","type","photos","des"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getUserId())) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getTitle())) {
			throw new BaseException("9002", "title参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		this.photowallService.pushPhotowall(request);
	}
	
	/**
	 * 留影墙评论列表
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "留影墙评论列表获取成功", faileMsg = "留影墙评论列表获取失败"))
	public void photowallReviewList() throws BaseException, Exception{
		logger.debug("photowallReviewList requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PhotowallTypeRequest request = (PhotowallTypeRequest) JSONObject.toBean(paramJson, PhotowallTypeRequest.class);
		request = (PhotowallTypeRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id","pageIndex","pageSize"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		} 
		if (AssertUtil.isEmpty(request.getId())) {
			throw new BaseException("9002", "id参数不能为空");
		}
		String id = request.getId();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		} 
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("id", id);
        getSearchValue().put("mobileClient", "mobileClient");
		pager = photowallService.searchTbPhotoWallReview(getSearchValue(), pager);
	    addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	
	/**
	 * 留影墙评论
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "评论成功", faileMsg = "评论失败"))
	public void photowallReview() throws BaseException, Exception{
		logger.debug("photowallReview requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		PhotowallTypeRequest request = (PhotowallTypeRequest) JSONObject.toBean(paramJson, PhotowallTypeRequest.class);
		request = (PhotowallTypeRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id","userId","photos","content"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getId())) {
			throw new BaseException("9002", "id参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getUserId())) {
			throw new BaseException("9002", "userId参数不能为空");
		}
		if (AssertUtil.isEmpty(request.getContent())) {
			throw new BaseException("9002", "content参数不能为空");
		}
		this.photowallService.photowallReview(request);
	}
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
}
