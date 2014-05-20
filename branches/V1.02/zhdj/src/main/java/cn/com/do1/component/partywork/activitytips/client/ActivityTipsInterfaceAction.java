package cn.com.do1.component.partywork.activitytips.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import cn.com.do1.component.partywork.activitytips.model.TbActivityTipsPO;
import cn.com.do1.component.partywork.activitytips.service.IActivityTipsService;
import cn.com.do1.component.partywork.activitytips.vo.ActivityTipsRequest;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class ActivityTipsInterfaceAction extends BaseAction{
	private final static transient Logger logger = LoggerFactory.getLogger(ActivityTipsInterfaceAction.class);
	private String requestJson;
	private String type;
	@Resource
	private IActivityTipsService activityTipsService;
    @Resource
    private IOrgService orgService;
    @Resource
    private IScoreruleService scoreruleService;
	/**
	 * 心得列表
	 * @param type(0：志愿者心语,1：民主生活会成员心得)
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void tipsList() throws BaseException, Exception{
		logger.debug("tipsList requestJson>>>"+requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		ActivityTipsRequest request = (ActivityTipsRequest) JSONObject.toBean(paramJson, ActivityTipsRequest.class);
		request = (ActivityTipsRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type","activityId","pageIndex","pageSize" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		if(!request.getType().equals("0") && !request.getType().equals("1")){
			throw new BaseException("9002", "type参数不在范围内");
		}
		if(AssertUtil.isEmpty(request.getActivityId())){
			throw new BaseException("9002", "activityId参数不能为空");
		}
		String activityId = request.getActivityId();
		int pageIndex = request.getPageIndex() == null ? 1 : Integer.parseInt(request.getPageIndex());
		int pageSize = request.getPageSize() == null ? 10 : Integer.parseInt(request.getPageSize());
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
        getSearchValue().put("activityId", activityId);
        getSearchValue().put("mobileClient", "mobileClient");
        
		pager = activityTipsService.searchActivityTips(getSearchValue(), pager);
	    addJsonArray("pageData", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}
	
	/**
	 * 提交心语或心得
	 * 
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "提交成功", faileMsg = "提交失败"))
	public void saveTips() throws BaseException, Exception {
		logger.debug("installRecord requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "参数为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		ActivityTipsRequest request = (ActivityTipsRequest) JSONObject.toBean(paramJson, ActivityTipsRequest.class);
		request = (ActivityTipsRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type","activityId", "userId","source","content"});
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		if (AssertUtil.isEmpty(request.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		if(!request.getType().equals("0") && !request.getType().equals("1")){
			throw new BaseException("9002", "type参数不在范围内");
		}
		if(AssertUtil.isEmpty(request.getActivityId())){
			throw new BaseException("9002", "activityId参数不能为空");
		}
		if(AssertUtil.isEmpty(request.getUserId())){
			throw new BaseException("9002", "userId参数不能为空");
		}
		if(AssertUtil.isEmpty(request.getSource())){
			throw new BaseException("9002", "source参数不能为空");
		}
		if(!request.getSource().equals("1") && !request.getSource().equals("2")){
			throw new BaseException("9002", "source参数不在范围内");
		}
		if(AssertUtil.isEmpty(request.getContent())){
			throw new BaseException("9002", "content参数不在范围内");
		}
		TbActivityTipsPO po = activityTipsService.getActivityTipsByActivityIdAndUserId(request.getActivityId(),request.getUserId());
		if(po != null){
			throw new BaseException("1001", "抱歉,您已经提交过一次,请不要重复提交!");
		}
		TbActivityTipsPO activityTipsPO= new TbActivityTipsPO();
		activityTipsPO.setId(UUID.randomUUID().toString().toLowerCase());
		activityTipsPO.setActivityId(request.getActivityId());
		activityTipsPO.setContent(request.getContent());
		activityTipsPO.setCreateTime(new Date());
		activityTipsPO.setCreateUserId(request.getUserId());
		activityTipsPO.setType(Long.parseLong(request.getType()));
		activityTipsPO.setSource(request.getSource());
		OrgVO orgVO = orgService.getOrgByPersonId(request.getUserId());
		if(orgVO != null){
			activityTipsPO.setOrganizationId(orgVO.getOrganizationId());
		}
		activityTipsService.insertPO(activityTipsPO, false);
		
		String scoreType = "";
		String title = "";
		if("0".equals(request.getType())){
			scoreType = "8";//志愿活动心得体会提交
			TbVolunteerActivityPO va = activityTipsService.searchByPk(TbVolunteerActivityPO.class, activityTipsPO.getActivityId());
			if(null != va){
				title = va.getTitle();
			}
		}else if("1".equals(request.getType())){
			scoreType = "7";//民主生活会心得体会提交
			TbDemocraticLifePO dl = activityTipsService.searchByPk(TbDemocraticLifePO.class, activityTipsPO.getActivityId());
			if(null != dl){
				title = dl.getTitle();
			}
		}
		TbPersonalScoreInfoPO perScore = new TbPersonalScoreInfoPO();
    	ExItemObj dic = DictOperater.getItem("personalScore", scoreType);
    	perScore.setId(UUID.randomUUID().toString().toLowerCase());
    	perScore.setUserId(activityTipsPO.getCreateUserId());
    	perScore.setScoreType(scoreType);
		TbScoreRulePO scoreRule = this.scoreruleService.searchByType(dic.getFsDictItemId());
		if(null != scoreRule){
			perScore.setScore(Long.parseLong(scoreRule.getScoreValue()));
		}
		perScore.setGetTime(new Date());
		perScore.setScoreDesc("《"+title+"》获得积分");
		this.activityTipsService.insertPO(perScore, true);
		
		setOuter(JSONOuter.getInstance());
	}
	
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
