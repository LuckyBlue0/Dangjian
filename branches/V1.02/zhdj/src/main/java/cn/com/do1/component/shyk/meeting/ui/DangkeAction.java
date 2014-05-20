package cn.com.do1.component.shyk.meeting.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserDetailsVO;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.service.IDangkeService;
import cn.com.do1.component.shyk.meeting.service.IMeetingService;
import cn.com.do1.component.shyk.meeting.vo.DangkeVO;
import cn.com.do1.component.shyk.partylecture.service.IPartyLectureService;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * All rights reserved. User: ${user}
 */
public class DangkeAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(DangkeAction.class);
	@Resource
	private IDangkeService dangkeService;
	@Resource
	private IMeetingService meetingService;
	@Resource
	private IMeetinguserService meetinguserService;
	@Resource
	private IPartyLectureService partyLectureService;
	private TbMeetingPO tbMeetingPO;
	private String ids[];
	private String id;
	private String userIds;


	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
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
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		IUser user =(IUser)DqdpAppContext.getCurrentUser();
		TbPartyMenberInfoPO po=meetinguserService.searchByPk(TbPartyMenberInfoPO.class,user.getPersonId());
		if(po != null && "4".equals(po.getPosition())){
			getSearchValue().put("organizationId",po.getOrganizationId());
		}
		pager = dangkeService.searchDangke(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
	public void ajaxAdd() throws Exception, BaseException {
		// todo:完成新增的代码;
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		// todo:完成更新的代码;
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量更新的代码
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbMeetingPO == null)
			tbMeetingPO = new TbMeetingPO();
		tbMeetingPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbMeetingPO.class, ids);
			for(String id : ids){
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		} else {
			super.ajaxDelete(tbMeetingPO);
			for(String id : ids){
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		}
	}

	public void setTbMeetingPO(TbMeetingPO tbMeetingPO) {
		this.tbMeetingPO = tbMeetingPO;
	}
	
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		Map<String,Object> result = meetingService.saveMeetingInfo(tbMeetingPO,userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}

	public TbMeetingPO setTbMeetingPO() {
		return this.tbMeetingPO;
	}

	public void addTbMeetingPO() {
		super.ajaxAdd(tbMeetingPO);
	}

	public void updateTbMeetingPO() {
		super.ajaxUpdate(tbMeetingPO);
	}

	public void deleteTbMeetingPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		tbMeetingPO._setPKValue(id);
		super.ajaxDelete(tbMeetingPO);
	}

	public void batchDeleteTbMeetingPO() {
		super.ajaxBatchDelete(TbMeetingPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		PartyLectureVO vo = partyLectureService.searchById(id);
		if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
			vo.setStatus("1");	// 可请假
		}else{
			vo.setStatus("3");	// 不可请假
		}
		if(!"0".equals(vo.getSignInStatus()) || !"0".equals(vo.getForLeaveStatus())){
			vo.setStatusDesc("已办");
			if("1".equals(vo.getForLeaveStatus())){
				vo.setStatus("2");
			}
		}
		addJsonObj("partyLectureVO", vo);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
		
//		DangkeVO vo = dangkeService.getDangkeById(id);
//		if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSingInStatus())&&"0".equals(vo.getForLeaveStatus())){
//			vo.setStatusDesc("进行中");
//			vo.setStatus("1");
//		}else{
//			vo.setStatusDesc("已办");
//			if("1".equals(vo.getForLeaveStatus())){
//				vo.setStatus("2");
//			}
//		}
//		if(vo.getWhetherEnd().equals("1")){
//			vo.setStatusDesc("已结束");
//			vo.setStatus("3");
//		}
//		addJsonObj("TbPartyLecturePO", vo);
	}
	
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxUser() throws Exception, BaseException {
		List<MeetingUserVO> meetingUsers = this.meetinguserService.getMeetingUsersByMeetingId(id);
		addJsonArray("userList", meetingUsers);
	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxUserDetails() throws Exception, BaseException {
		DangkeVO meeting = dangkeService.getDangkeById(id);
		addJsonObj("tbMeetingPO", meeting);
		
		List<MeetingUserDetailsVO> meetingUserDetails = this.meetinguserService.getMeetingUsersDetailsByMeetingId(id);
		if(!AssertUtil.isEmpty(meetingUserDetails)){
			addJsonObj("meetingUserDetail", meetingUserDetails.get(0));
		}
	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "修改成功", faileMsg = "修改失败"))
	public void ajaxUpdateMeeting() throws Exception, BaseException {
		TbMeetingPO meeting = new TbMeetingPO();
		meeting.setId(id);
		meeting.setWhetherRecommend(1l);
		this.meetingService.updatePO(meeting, false);
	}
	

	public TbMeetingPO getTbMeetingPO() {
		return this.tbMeetingPO;
	}
}
