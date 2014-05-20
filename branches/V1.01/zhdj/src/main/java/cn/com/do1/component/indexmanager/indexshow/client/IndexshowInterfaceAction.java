package cn.com.do1.component.indexmanager.indexshow.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.index.index.model.LoginRequest;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.indexmanager.indexshow.service.IIndexshowService;
import cn.com.do1.component.indexmanager.indexshow.ui.IndexshowAction;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.installrecord.model.IntegralRankingRequest;
import cn.com.do1.component.mobileclient.userinfo.service.IUserInfoService;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.ModifyUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.ModifyUserPwdRequest;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.report.access.model.TbLoginLogPO;
import cn.com.do1.component.score.orgscore.service.IOrgscoreService;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;
import cn.com.do1.component.util.Md5;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class IndexshowInterfaceAction extends BaseAction{
	private final static transient Logger logger = LoggerFactory.getLogger(IndexshowInterfaceAction.class);
	private String requestJson;
	@Resource
	private IIndexshowService indexshowService;
	/**
	 * 获取首页
	 * @throws BaseException 
	 * @throws Exception 
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "获取首页成功", faileMsg = "获取首页失败"))
	public void getHeadPage() throws BaseException, Exception{
		 Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
         pager=indexshowService .searchTbProjectInfo(getSearchValue(),pager);
	    addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
	    addJsonObj("currentPage", pager.getCurrentPage());
	    addJsonObj("totalPage", pager.getTotalPages());
	    addJsonObj("totalRows", pager.getTotalRows());
	}

	public String getRequestJson() {
		return requestJson;
	}


	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	
}
