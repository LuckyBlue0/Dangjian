package cn.com.do1.component.mobileclient.suggestion.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.mobileclient.suggestion.model.SuggestionRequest;
import cn.com.do1.component.mobileclient.suggestion.model.TbSuggestionPO;
import cn.com.do1.component.mobileclient.suggestion.service.ISuggestionService;
import cn.com.do1.component.util.CommonAuthManage;
import cn.com.do1.component.util.JSONOuter;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
public class SuggestionInterfaceAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(SuggestionInterfaceAction.class);
	private String requestJson;
	@Resource
	private ISuggestionService suggestionService;

	/**
	 * 意见反馈
	 * 
	 * @throws BaseException
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "意见反馈提交成功", faileMsg = "意见反馈提交失败"))
	public void addSuggestion() throws BaseException, Exception {
		logger.debug("addSuggestion requestJson>>>" + requestJson);
		if (AssertUtil.isEmpty(requestJson)) {
			throw new BaseException("9001", "requestJson参数不能为空");
		}
		JSONObject paramJson = JSONObject.fromObject(requestJson);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("suggestion", SuggestionRequest.class);
		SuggestionRequest request = (SuggestionRequest) JSONObject.toBean(paramJson, SuggestionRequest.class, classMap);
		request = (SuggestionRequest) CommonAuthManage.authDigestRetObj(request, new String[] { "type", "userName", "suggestion", "mobile" });
		if (request == null) {
			throw new BaseException("1002", "摘要加密值有误!");
		}
		TbSuggestionPO suggestion = new TbSuggestionPO();
		suggestion.setMobile(request.getMobile());
		suggestion.setSuggestion(request.getSuggestion());
		suggestion.setType(Integer.parseInt(request.getType()));
		suggestion.setUserName(request.getUserName());
		if (AssertUtil.isEmpty(suggestion.getType())) {
			throw new BaseException("9002", "type参数不能为空");
		}
		if (suggestion.getType() != 1 && suggestion.getType() != 2) {
			throw new BaseException("9002", "非法的请求参数type==" + suggestion.getType());
		}
		if (AssertUtil.isEmpty(suggestion.getSuggestion())) {
			throw new BaseException("9002", "suggestion参数不能为空");
		}
		suggestion.setId(UUID.randomUUID().toString().toLowerCase());
		suggestion.setCreateTime(new Date());
		suggestionService.insertPO(suggestion, false);
		setOuter(JSONOuter.getInstance());
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
}
