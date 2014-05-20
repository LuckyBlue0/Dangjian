package cn.com.do1.component.shyk.meeting.ui;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.volunteer.model.TbVolunteerActivityPO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.service.IMeetingService;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.util.SmbFileUtils;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class MeetingAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(MeetingAction.class);
	@Resource
	private IMeetingService meetingService;
	@Resource
	private IMeetinguserService meetinguserService;
	@Resource
	private IMembercenterService membercenterService;
	private TbMeetingPO tbMeetingPO;
	private String ids[];
	private String id;
	private String userIds;
	private Integer type;

	private String backURL;

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
		pager = meetingService.searchMeeting(getSearchValue(), pager);
		addJsonPager("pageData", pager);
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
		if (tbMeetingPO == null)
			tbMeetingPO = new TbMeetingPO();
		tbMeetingPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbMeetingPO.class, ids);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		} else {
			super.ajaxDelete(tbMeetingPO);
			for (String id : ids) {
				this.meetinguserService.delMeetingUserByMeetingId(id);
			}
		}
	}



	/**
	 * 保存
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
//		tbMeetingPO.setQrCode(getReqeustObj().getContextPath()+"//image//qrcode//");
		Map<String, Object> result = meetingService.saveMeetingInfo(tbMeetingPO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}
	
	/**
	 * 保存会议记录
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSaveRecord() throws Exception, BaseException{
		Map<String, Object> result = meetingService.saveMeetingRecordInfo(tbMeetingPO, userIds);
		setActionResult(result.get("code").toString(), result.get("desc").toString());
		doJsonOut();
	}


	/**
	 * 查看详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		MeetingVO vo = meetingService.getMeetingById(id);
		if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
			vo.setStatus("1");
		}else{
			vo.setStatus("3");
		}
		if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSignInStatus())&&"0".equals(vo.getForLeaveStatus())){
			vo.setStatusDesc("进行中");
			vo.setStatus("1");
		}else{
			vo.setStatusDesc("已办");
			if("1".equals(vo.getForLeaveStatus())){
				vo.setStatus("2");
			}
		}
		if(vo.getWhetherEnd().equals("1")){
			vo.setStatusDesc("已结束");
			vo.setStatus("3");
		}
		addJsonObj("tbMeetingPO", vo);
	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxUserDetails() throws Exception, BaseException {
		MeetingVO meeting = meetingService.getMeetingById(id);
		addJsonObj("tbMeetingPO", meeting);
	}

	/**
	 * 推荐
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "修改成功", faileMsg = "修改失败"))
	public void ajaxUpdateMeeting() throws Exception, BaseException {
		TbMeetingPO meeting = new TbMeetingPO();
		meeting.setId(id);
		meeting.setWhetherRecommend(1l);
		this.meetingService.updatePO(meeting, false);
	}
	
	/**
	 * 发布或下架
	 */
	public void pushOrOut(){
		if(AssertUtil.isEmpty(id) || AssertUtil.isEmpty(type)){
			setActionResult("-1","参数有误!");	
		}else{
			if (tbMeetingPO == null)
				tbMeetingPO = new TbMeetingPO();
			tbMeetingPO.setId(id);
			if(type==1){//发布
				tbMeetingPO.setStatus(1l);
			}else if(type==2){
				tbMeetingPO.setStatus(2l);
			}
			try {
				this.meetingService.updatePO(tbMeetingPO, false);
				setActionResult("0",type == 1 ? "发布成功" : "下架成功");
			} catch (SQLException e) {
				setActionResult("0",type == 1 ? "发布失败" : "下架失败");
			}
		}
		doJsonOut();
	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "二维码查询成功", faileMsg = "二维码查询失败"))
	public void qRCodeView() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id) || AssertUtil.isEmpty(type)) {
			setActionResult("-1", "参数有误!");
		} else {
			String title = "";
			String qrCode = "";
			switch (type) {
			case 1:// 会议
				TbMeetingPO meeting = meetinguserService.searchByPk(TbMeetingPO.class, id);
				qrCode = meeting.getQrCode();
				title = meeting.getTitle();
				break;
			case 2:// 党课
				TbPartyLecturePO partyLecture = meetinguserService.searchByPk(TbPartyLecturePO.class, id);
				qrCode = partyLecture.getQrCode();
				title = partyLecture.getTitle();
				break;
			case 3:// 支部活动
				TbBranchActivityPO branchActivityPO = meetinguserService.searchByPk(TbBranchActivityPO.class, id);
				qrCode = branchActivityPO.getQrCode();
				title = branchActivityPO.getTitle();
				break;
			case 4:// 志愿活动
				TbVolunteerActivityPO volunteerActivityPO = meetinguserService.searchByPk(TbVolunteerActivityPO.class, id);
				qrCode = volunteerActivityPO.getQrCode();
				title = volunteerActivityPO.getTitle();
				break;
			case 5:// 民主生活会
				TbDemocraticLifePO democraticLifePO = meetinguserService.searchByPk(TbDemocraticLifePO.class, id);
				qrCode = democraticLifePO.getQrCode();
				title = democraticLifePO.getTitle();
				break;
			default:// 非法请求参数
				throw new BaseException("9002", "type参数不在范围内!");
			}
			addJsonObj("title", title);
			addJsonObj("qrCode", qrCode);
		}
	}
	
	
	/**
	 * 导出二维码
	 * @throws Exception 
	 * @throws BaseException 
	 */
	public void exportRecode() throws Exception, BaseException{
		if (AssertUtil.isEmpty(id) || AssertUtil.isEmpty(type)) {
			setActionResult("-1", "参数有误!");
		} else {
			String title = "";
			String qrCode = "";
			switch (type) {
			case 1:// 会议
				TbMeetingPO meeting = meetinguserService.searchByPk(TbMeetingPO.class, id);
				qrCode = meeting.getQrCode();
				title = meeting.getTitle();
				break;
			case 2:// 党课
				TbPartyLecturePO partyLecture = meetinguserService.searchByPk(TbPartyLecturePO.class, id);
				qrCode = partyLecture.getQrCode();
				title = partyLecture.getTitle();
				break;
			case 3:// 支部活动
				TbBranchActivityPO branchActivityPO = meetinguserService.searchByPk(TbBranchActivityPO.class, id);
				qrCode = branchActivityPO.getQrCode();
				title = branchActivityPO.getTitle();
				break;
			case 4:// 志愿活动
				TbVolunteerActivityPO volunteerActivityPO = meetinguserService.searchByPk(TbVolunteerActivityPO.class, id);
				qrCode = volunteerActivityPO.getQrCode();
				title = volunteerActivityPO.getTitle();
				break;
			case 5:// 民主生活会
				TbDemocraticLifePO democraticLifePO = meetinguserService.searchByPk(TbDemocraticLifePO.class, id);
				qrCode = democraticLifePO.getQrCode();
				title = democraticLifePO.getTitle();
				break;
			default:// 非法请求参数
				throw new BaseException("9002", "type参数不在范围内!");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = null;
			InputStream is = null;
			String dir = ConfigMgr.get("system","qrCodeDir");//从配置管理拿到文件服务器存放二维码图片的路径
			String fileName = qrCode.substring(qrCode.lastIndexOf("/")+1, qrCode.length());
			SmbFileUtils smbFile = new SmbFileUtils(dir, fileName);
			is = smbFile.readFile();
			os = response.getOutputStream();
			fileName = new String((title+"二维码.png").getBytes("GB2312"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName + "");
			response.setContentType("imgage/png;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			// 写出流信息
			int b = 0;
			byte[] buffer = new byte[8192];
			try {
				while ((b = is.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, b);
				}
				os.flush();
				is.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
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

	public void setTbMeetingPO(TbMeetingPO tbMeetingPO) {
		this.tbMeetingPO = tbMeetingPO;
	}

	public TbMeetingPO setTbMeetingPO() {
		return this.tbMeetingPO;
	}
	public TbMeetingPO getTbMeetingPO() {
		return this.tbMeetingPO;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBackURL() {
		return backURL;
	}

	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
