package cn.com.do1.component.partywork.meetinguser.ui;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.meetinguser.vo.MeetingUserVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class MeetinguserAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(MeetinguserAction.class);
    private IMeetinguserService meetinguserService;
    private TbMeetingUserPO tbMeetingUserPO;
    private String ids[];
    private String id;
    private String meetinId;
    private Integer type;
    
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMeetinId() {
		return meetinId;
	}

	public void setMeetinId(String meetinId) {
		this.meetinId = meetinId;
	}

	public IMeetinguserService getMeetinguserService() {
        return meetinguserService;
    }

    @Resource
    public void setMeetinguserService(IMeetinguserService meetinguserService) {
        this.meetinguserService = meetinguserService;
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

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxUser() throws Exception, BaseException {
		List<MeetingUserVO> meetingUsers = this.meetinguserService.getMeetingUsersByMeetingId(id);
		addJsonArray("userList", meetingUsers);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxUserDetails() throws Exception, BaseException {

		List<MeetingUserVO> meetingUsers = this.meetinguserService.getMeetingUsersByMeetingId(id);
		if (!AssertUtil.isEmpty(meetingUsers)) {
			List<MeetingUserVO> shoulds = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> actuals = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> forLeaves = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> supplements = new ArrayList<MeetingUserVO>();
			
			for(MeetingUserVO VO : meetingUsers){
				
				//应到人员
				if(VO.getIsSupplement().equals("0")){
					shoulds.add(VO);
				//补录人员
				}else if(VO.getIsSupplement().equals("1")){
					supplements.add(VO);
				}
				
				//签到人员
				if(VO.getSignInStatus().equals("1")){
					actuals.add(VO);
				}
				
				//请假人员
				if(VO.getForLeaveStatus().equals("1")){
					forLeaves.add(VO);
				}
			}
			
			addJsonObj("shoulds", shoulds);
			addJsonObj("actuals", actuals);
			addJsonObj("forLeaves", forLeaves);
			addJsonObj("supplements", supplements);
		}

	}
	
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxVolunteerUserDetails() throws Exception, BaseException {

		List<MeetingUserVO> meetingUsers = this.meetinguserService.getMeetingUsersByMeetingId(id);
		if (!AssertUtil.isEmpty(meetingUsers)) {
			List<MeetingUserVO> shoulds = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> actuals = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> forLeaves = new ArrayList<MeetingUserVO>();
			List<MeetingUserVO> supplements = new ArrayList<MeetingUserVO>();
			
			for(MeetingUserVO VO : meetingUsers){
				
				//报名人员
				if(VO.getSignUpStatus().equals("1")){
					shoulds.add(VO);
				}
				
				
				//补录人员
				if(VO.getIsSupplement().equals("1")){
					supplements.add(VO);
				}
				
				//签到人员
				if(VO.getSignInStatus().equals("1")){
					actuals.add(VO);
				}
				
				//请假人员
				if(VO.getForLeaveStatus().equals("1")){
					forLeaves.add(VO);
				}
			}
			
			addJsonObj("shoulds", shoulds);
			addJsonObj("actuals", actuals);
			addJsonObj("forLeaves", forLeaves);
			addJsonObj("supplements", supplements);
		}

	}

   public void setTbMeetingUserPO(TbMeetingUserPO tbMeetingUserPO){
       this.tbMeetingUserPO=tbMeetingUserPO;
    }
   public TbMeetingUserPO setTbMeetingUserPO(){
       return this.tbMeetingUserPO;
    }
   public void addTbMeetingUserPO(){
       super.ajaxAdd(tbMeetingUserPO);
   }
   public void updateTbMeetingUserPO(){
       super.ajaxUpdate(tbMeetingUserPO);
   }
   public void deleteTbMeetingUserPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbMeetingUserPO._setPKValue(id);
       super.ajaxDelete(tbMeetingUserPO);
   }
   public void batchDeleteTbMeetingUserPO(){
       super.ajaxBatchDelete(TbMeetingUserPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbMeetingUserPO xxPO = meetinguserService.searchByPk(TbMeetingUserPO.class, id);
            addJsonFormateObj("tbMeetingUserPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbMeetingUserPO getTbMeetingUserPO() {
        return this.tbMeetingUserPO;
    }
}
