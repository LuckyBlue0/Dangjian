package cn.com.do1.component.partywork.activitytips.ui;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.exception.ui.ServiceException;
import cn.com.do1.component.partywork.activitytips.service.IActivityTipsService;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ActivityTipsAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(ActivityTipsAction.class);
	@Resource
	private IActivityTipsService activityTipsService;
	private String type;
	private String id;

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		if (type.isEmpty()) {
			throw new ServiceException("请求参数有误,type不能为空");
		} else if (!type.equals("0") && !type.equals("1")) {
			throw new ServiceException("请求参数有误,type不在范围内");
		}
		if (AssertUtil.isEmpty(id)) {
			throw new ServiceException("请求参数有误,id不能为空");
		}
		getSearchValue().put("type", type);
		String title = "";
		if (type.equals("0")) {// 志愿者心语
			type = "志愿者心语";
			TbVolunteerActivityPO PO = activityTipsService.searchByPk(TbVolunteerActivityPO.class, id);
			if (PO != null) {
				title = PO.getTitle();
			}
		} else if (type.equals("1")) {// 民主生活会成员心得
			type = "民主生活会成员心得";
			TbDemocraticLifePO PO = activityTipsService.searchByPk(TbDemocraticLifePO.class, id);
			if (PO != null) {
				title = PO.getTitle();
			}
		}
		
		getSearchValue().put("activityId", id);
		Pager pager = new Pager(ServletActionContext.getRequest(),5);
		pager = activityTipsService.searchActivityTips(getSearchValue(), pager);

		addJsonObj("type", type);
		addJsonObj("title", title);
		addJsonPager("pageData", pager);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
