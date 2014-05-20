package cn.com.do1.component.partywork.ideologyreport.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.component.partywork.branch.vo.PartyMeetingListRequest;
import cn.com.do1.component.partywork.ideologyreport.model.TbIdeologyReportPO;
import cn.com.do1.component.partywork.ideologyreport.service.IIdeologyreportService;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportClientVO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class IdeologreportInterfaceAction extends BaseAction{
	private final static transient Logger logger = LoggerFactory.getLogger(IdeologreportInterfaceAction.class);
	private String requestJson;
	@Resource
	private IIdeologyreportService ideologyreportService;
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "获取思想汇报列表成功", faileMsg = "获取思想汇报列表失败"))
	public void ideologyList() throws BaseException, Exception{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
        JSONObject paramJson = JSONObject.fromObject(requestJson);
        
		logger.debug("ideologyList requestJson>>>" + requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "userId","keyword","status","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
		String userId = request.getUserId();
		String keyword = request.getKeyword();
		String status = request.getStatus();
		Integer pageIndex = Integer.parseInt(request.getPageIndex());
		Integer pageSize =Integer.parseInt(request.getPageSize());
        
		if (AssertUtil.isEmpty(userId)) {
			throw new BaseException("9002", "userId参数不能为空");
		}
        if (pageSize.equals(0)) {
			pageSize = 10;
		}
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("userId", userId);
        if(!AssertUtil.isEmpty(keyword)){
        	searchMap.put("title", keyword);
        }
        if(!AssertUtil.isEmpty(status)){
        	searchMap.put("status", status);
        }
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        pager = ideologyreportService.searchIdeologyreport(searchMap, pager);
        addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
        addJsonObj("currentPage", pager.getCurrentPage());
        addJsonObj("totalPage", pager.getTotalPages());
        addJsonObj("totalRows", pager.getTotalRows());
        setOuter(JSONOuter.getInstance());
	}
	/**
	 * 思想汇报详情
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "获取思想汇报详情成功", faileMsg = "获取思想汇报详情失败"))
	public void ideologyDetail() throws BaseException, Exception{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
        JSONObject paramJson = JSONObject.fromObject(requestJson);
		logger.debug("ideologyDetail requestJson>>>" + requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
        String id = request.getId();
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		IdeologyReportVO vo = this.ideologyreportService.searchById(id);
		IdeologyReportClientVO result = new IdeologyReportClientVO();
		BeanHelper.copyProperties(result, vo);
		addJsonObj("ideology", result);
		setOuter(JSONOuter.getInstance());
	}
	
	/**
	 * 新增/修改思想汇报
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void editIdeologyl() throws BaseException{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
        JSONObject paramJson = JSONObject.fromObject(requestJson);
    	logger.debug("editIdeologyl requestJson>>>" + requestJson);
        Map<String, Class> classMap = new HashMap<String, Class>();
    	classMap.put("ideology", IdeologyReportClientVO.class);
    	IdeologyReportClientVO ideology = (IdeologyReportClientVO)JSONObject.toBean(paramJson,IdeologyReportClientVO.class,classMap);
    	ideology = (IdeologyReportClientVO) CommonAuthManage.authDigestRetObj(ideology, new String[] { "userId","id","title","content","status"});
		if (ideology == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if(AssertUtil.isEmpty(ideology)){
			throw new BaseException("9002", "ideology参数不能为空");
		}
		if(AssertUtil.isEmpty(ideology.getTitle())){
			throw new BaseException("9002", "title参数不能为空");
		}
		if(AssertUtil.isEmpty(ideology.getContent())){
			throw new BaseException("9002", "content参数不能为空");
		}
		if(AssertUtil.isEmpty(ideology.getStatus())){
			throw new BaseException("9002", "status参数不能为空");
		}
		if(!ideology.getStatus().equals("9") && !ideology.getStatus().equals("0")){
			throw new BaseException("9002", "status参数不在范围内");
		}
		
		
		if (AssertUtil.isEmpty(ideology.getId())) {//新增
			if(AssertUtil.isEmpty(ideology.getUserId())){
				throw new BaseException("9002", "userId参数不能为空");
			}
			TbIdeologyReportPO po = new TbIdeologyReportPO();
			po.setId(UUID.randomUUID().toString().toLowerCase());
			po.setCreateUserId(ideology.getUserId());
			po.setTitle(ideology.getTitle());
			po.setContent(ideology.getContent());
			po.setStatus(Long.parseLong(ideology.getStatus()));
			try {
				this.ideologyreportService.insertPO(po, false);
			} catch (Exception e) {
				logger.error("新增思想汇报出现错误:"+e.getMessage());
				setActionResult("-1", "新增失败!");
			}
			setActionResult("0", "新增成功!");
		}else{
			if(AssertUtil.isEmpty(ideology.getId())){
				throw new BaseException("9002", "id参数不能为空");
			}
			TbIdeologyReportPO po = new TbIdeologyReportPO();
			po.setId(ideology.getId());
			po.setTitle(ideology.getTitle());
			po.setContent(ideology.getContent());
			po.setStatus(Long.parseLong(ideology.getStatus()));
			try {
				this.ideologyreportService.updatePO(po, false);
			} catch (SQLException e) {
				logger.error("修改思想汇报出现错误:"+e.getMessage());
				setActionResult("-1", "修改失败!");
			}
			setActionResult("0", "修改成功!");
		}
		doJsonOut();
	}
	
	/**
	 * 删除思想汇报(状态为草稿才能删除)
	 * @throws Exception
	 * @throws BaseException
	 */
	public void delIdeology() throws Exception, BaseException{
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
        JSONObject paramJson = JSONObject.fromObject(requestJson);
		logger.debug("delIdeology requestJson>>>" + requestJson);
		PartyMeetingListRequest request = (PartyMeetingListRequest) JSONObject.toBean(paramJson, PartyMeetingListRequest.class);
		request = (PartyMeetingListRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "id"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		
        String id = request.getId();
		if (AssertUtil.isEmpty(id)) {
			throw new BaseException("9002", "id参数不能为空");
		}
		IdeologyReportVO VO = this.ideologyreportService.searchById(id);
		if(VO != null && !VO.getStatus().equals("9")){
			setActionResult("-1", "删除失败,只能状态为草稿才能删除!");
		}else{
			TbIdeologyReportPO po = new TbIdeologyReportPO();
			po.setId(VO.getId());
			this.ideologyreportService.delPO(po);
			setActionResult("0", "删除成功!");
		}
		doJsonOut();
	}
	

	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	
	/**
	 * @param pageData
	 * @return
	 */
	private List<IdeologyReportClientVO> getList(List<IdeologyReportVO> lists) {
		// TODO Auto-generated method stub
		List<IdeologyReportClientVO> results = new ArrayList<IdeologyReportClientVO>();
		if(lists != null && lists.size()>0){
			IdeologyReportClientVO vo = null;
			for(IdeologyReportVO v : lists){
				vo = new IdeologyReportClientVO();
				BeanHelper.copyProperties(vo, v);
//				vo.setId(v.getId());
//				vo.setContent(v.getContent());
//				vo.setCreateUser(v.getName());
//				vo.setTitle(v.getTitle());
//				vo.setCreateTime(v.getCreateTime());
//				vo.setReviewUser(v.getReviewUser());
//				vo.setReviewTime(v.getReviewTime());
//				vo.setPostil(v.getPostil());
				results.add(vo);
			}
		}
		return results;
	}
}
