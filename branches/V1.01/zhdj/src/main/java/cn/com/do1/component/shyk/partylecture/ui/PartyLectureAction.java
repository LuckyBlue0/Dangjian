package cn.com.do1.component.shyk.partylecture.ui;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.shyk.partylecture.service.IPartyLectureService;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartyLectureAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(PartyLectureAction.class);
	@Resource
	private IPartyLectureService partyLectureService;
	private TbPartyLecturePO tbPartyLecturePO;
	@Resource
	private IMeetinguserService meetinguserService;
	@Resource
	private IMembercenterService membercenterService;
	private String id;
	private String ids [];
	private String userIds;
	private int type;

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		IUser user =(IUser)DqdpAppContext.getCurrentUser();
		boolean flag = membercenterService.searchPositionByUserId(user.getPersonId());
		if(flag){
			TbPartyMenberInfoPO po=membercenterService.searchByPk(TbPartyMenberInfoPO.class,user.getPersonId());
			if(po != null){
				getSearchValue().put("organizationId",po.getOrganizationId());
			}
		}
		pager = partyLectureService.searchPartyLecture(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}
	
	/**
	 * 保存
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		Map<String, Object> result = partyLectureService.savePartyLectureInfo(tbPartyLecturePO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}
	
	/**
	 * 保存会议记录
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSaveRecord() throws Exception, BaseException{
		Map<String, Object> result = partyLectureService.savePartyLectureRecordInfo(tbPartyLecturePO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}

	/**
	 * 删除
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量更新的代码
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbPartyLecturePO == null)
			tbPartyLecturePO = new TbPartyLecturePO();
		tbPartyLecturePO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbPartyLecturePO.class, ids);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		} else {
			super.ajaxDelete(tbPartyLecturePO);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		}
	}

	/**
	 * 查看详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbPartyLecturePO xxPO = partyLectureService.searchByPk(TbPartyLecturePO.class, id);
		addJsonFormateObj("tbPartyLecturePO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxViewDetails() throws Exception, BaseException{
		PartyLectureVO xxVO = partyLectureService.searchById(id);
		addJsonObj("tbPartyLecturePO", xxVO);
	}
	
	/**
	 * 发布或下架
	 */
	public void pushOrOut(){
		if(AssertUtil.isEmpty(id) || AssertUtil.isEmpty(type)){
			setActionResult("-1","参数有误!");	
		}else{
			if (tbPartyLecturePO == null)
				tbPartyLecturePO = new TbPartyLecturePO();
			tbPartyLecturePO.setId(id);
			if(type==1){//发布
				tbPartyLecturePO.setStatus(1l);
			}else if(type==2){
				tbPartyLecturePO.setStatus(2l);
			}
			try {
				this.partyLectureService.updatePO(tbPartyLecturePO, false);
				setActionResult("0",type == 1 ? "发布成功" : "下架成功");
			} catch (SQLException e) {
				setActionResult("0",type == 1 ? "发布失败" : "下架失败");
			}
		}
		doJsonOut();
	}

	public TbPartyLecturePO getTbPartyLecturePO() {
		return tbPartyLecturePO;
	}

	public void setTbPartyLecturePO(TbPartyLecturePO tbPartyLecturePO) {
		this.tbPartyLecturePO = tbPartyLecturePO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
