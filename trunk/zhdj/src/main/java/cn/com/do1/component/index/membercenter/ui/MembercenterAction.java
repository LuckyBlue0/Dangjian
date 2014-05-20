package cn.com.do1.component.index.membercenter.ui;
import java.awt.Rectangle;
import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.filter.ImageHepler;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.index.membercenter.model.PartyListClientVO;
import cn.com.do1.component.index.membercenter.model.PartyMenberMeetingVO;
import cn.com.do1.component.index.membercenter.model.PartyMenberScoreVO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.service.IDemocrticLifeService;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.ideologyreport.model.TbIdeologyReportPO;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;
import cn.com.do1.component.partywork.meetinguser.model.TbMeetingUserPO;
import cn.com.do1.component.partywork.meetinguser.service.IMeetinguserService;
import cn.com.do1.component.partywork.volunteer.service.IVolunteerService;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.meeting.vo.MeetingVO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;
import cn.com.do1.component.util.CommonFileUtils;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.Md5;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class MembercenterAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(MembercenterAction.class);
    private IMembercenterService membercenterService;
    private String ids[];
    private String id;
    private TbPartyMenberInfoPO partymember;
    private TbIdeologyReportPO tbIdeologyReportPO;
    @Resource
	private IPartymenberService partymenberService;
    @Resource
    private IPersonalscoreService personalscoreService;
    @Resource
    private IIndexService indexService;
    @Resource
	private IVolunteerService volunteerService;
    @Resource
	private IMeetinguserService meetinguserService;
    @Resource
    private IDemocrticLifeService democrticLifeService;
    private String picture;
    private String password;//旧密码
    private String newPassword;//新密码
    private String cfPassword;//确认密码
    private String type;

    public IMembercenterService getMembercenterService() {
        return membercenterService;
    }

    @Resource
    public void setMembercenterService(IMembercenterService membercenterService) {
        this.membercenterService = membercenterService;
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
    @SearchValueTypes(
        nameFormat="false",value={
        @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "testNumber", type = "number"),
        @SearchValueType(name = "testString", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=membercenterService.searchMembercenter(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }
    /**
     * 查询个人积分
     * @throws Exception
     * @throws BaseException
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchPersonalScore()throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
    	if(!AssertUtil.isEmpty(uservo)){
    		TbPartyMenberInfoPO  userpo=partymenberService.getPartyMenberByUserName(uservo.getUsername());
    		Pager pager = new Pager(ServletActionContext.getRequest(),999999);
    		Map searchMap=new HashMap();
    		searchMap.put("id", userpo.getId());
			PartyMenberScoreVO scorevo=new PartyMenberScoreVO();

			scorevo=membercenterService.searchPersonalScore(userpo,searchMap,pager);
    		if(AssertUtil.isEmpty(scorevo.getTatalScore())){
    			scorevo.setTatalScore("0");
    		}
    		if(AssertUtil.isEmpty(scorevo.getSort())){
    			scorevo.setSort("无");
    		}
    		addJsonObj("scorevo", scorevo);
    		
    		List<TbMeetingPO> listmet=membercenterService.getMeetingByUserid(uservo.getUserId());
    		List<TbPartyLecturePO> listParty =membercenterService.getPartyLectureByUserid(uservo.getUserId());
            List<BranchActivityVO> listact=membercenterService.getBranchActivityByUserid(uservo.getUserId());
            PartyMenberMeetingVO meetingvo=new PartyMenberMeetingVO();
            int countPmt=listmet == null ? 0 : listmet.size();
            int countMt=listParty == null ? 0 : listParty.size();
            meetingvo.setPartyMeetingNum(countPmt);
			meetingvo.setMeetingNum(countMt);
//			if (!AssertUtil.isEmpty(listmet)) {
//				for (TbMeetingPO vo : listmet) {
//					if (vo.getType() >= 100l) {
//						countPmt = countPmt + 1;
//					} else {
//						countMt = countMt + 1;
//					}
//				}
//				meetingvo.setPartyMeetingNum(countPmt);
//				meetingvo.setMeetingNum(countMt);
//			} else {
//				meetingvo.setPartyMeetingNum(0);
//				meetingvo.setMeetingNum(0);
//			}
            meetingvo.setActivityNum(listact.size());
            int num=0;
            num=meetingvo.getPartyMeetingNum()+meetingvo.getMeetingNum()+meetingvo.getActivityNum();
            addJsonObj("num", num);
    	}else{
    		throw new BaseException("请先登录个人中心");
    	}
    }
    /**
     * 查询个人待办信息
     * @throws Exception
     * @throws BaseException
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchPersonalMeeting()throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		//TbPartyMenberInfoPO  userpo=partymenberService.getPartyMenberByUserName(uservo.getUsername());
            List<TbMeetingPO> listmet=membercenterService.getMeetingByUserid(uservo.getUserId());
            List<TbPartyLecturePO> listpl=membercenterService.getPartyLectureByUserid(uservo.getUserId());
            List<BranchActivityVO> listact=membercenterService.getBranchActivityByUserid(uservo.getUserId());
            PartyMenberMeetingVO meetingvo=new PartyMenberMeetingVO();
            int countMt=0;
            int countPl=0;
            if(!AssertUtil.isEmpty(listmet)){
            	countMt = listmet.size();
            }
            if(!AssertUtil.isEmpty(listpl)){
            	countPl = listpl.size();
            }
            meetingvo.setPartyMeetingNum(countPl);
            meetingvo.setMeetingNum(countMt);
            meetingvo.setActivityNum(listact.size());
            List<IdeologyReportVO> listrep=membercenterService.searchReportByUserid(uservo.getUserId());
            meetingvo.setReportNum(listrep.size());
            addJsonObj("meetingvo", meetingvo);
        }else{
        	throw new BaseException("请先登录个人中心");
        }
    }
    /**
     * 查询个人信息，必须为党员
     * @throws Exception
     * @throws BaseException
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchUserDetail()throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		TbPartyMenberInfoPO  userpo=partymenberService.getPartyMenberByUserName(uservo.getUsername());
    		PartyMenberInfoVO membervo=partymenberService.getPartyMenberById(userpo.getId());

    		addJsonObj("partymember", membervo);
        }else{
        	throw new BaseException("请先登录个人中心");
        }
        
    }
    //更新信息
    @JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void updateUserDetail() throws Exception, BaseException {
		try {
			if(!AssertUtil.isEmpty(password)){
				LoginUserVO uservo=new LoginUserVO();
				uservo.setUsername(partymember.getUserName());
				uservo.setUserPwd(new Md5().getMD5ofStr(password).trim().toLowerCase());
				uservo.setOrganizationId(partymember.getOrganizationId());
				if(AssertUtil.isEmpty(indexService.loginUser(uservo, "1"))){
					throw new BaseException("您输入的旧密码不匹配！");
				}
			}
			if(!AssertUtil.isEmpty(newPassword)&&!AssertUtil.isEmpty(cfPassword)){
				if(newPassword.equals(cfPassword)){
					partymember.setPassword(newPassword);
				}
			}
			partymenberService.updateUserInfo(partymember);
			getSessionObj().invalidate();
			setActionResult("0", "您的信息更新成功，请重新登录！");
		} catch (Exception e) {
			setActionResult("1001", "保存失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} 
	}
    //保存头像
    public void savePic() throws Exception{
 	   HttpServletRequest request=getReqeustObj();
 	   String path=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/"+picture;
 	   String name=picture.substring(13, picture.length());
 	  File file = CommonFileUtils.getFileObj("upload\\image", name);
		int cutTop = Integer.parseInt(request.getParameter("txt_top"));
		int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
		Rectangle rec = new Rectangle(cutLeft,cutTop,120,120);
		File saveFile=new File(request.getRealPath("")+"/download/"+name);
		
		ImageHepler.cut(file,saveFile,354,266,rec);
		logger.info("开始上传图片至文件服务器");
		String backURL="";
		FileUploadUtil up=new FileUploadUtil();
		String fileExt = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
		String uuid = java.util.UUID.randomUUID().toString();
		String newFileName = uuid + "." + fileExt;
		try {
			backURL=up.uploadFileBySMB(saveFile, "upload/image",newFileName);
			setActionResult("0",backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
    }
    /**
     * 个人积分列表
     * @throws Exception 
     * @throws BaseException 
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchPersonalScoreList() throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
    	if(!AssertUtil.isEmpty(uservo)){
    		TbPartyMenberInfoPO  userpo=partymenberService.getPartyMenberByUserName(uservo.getUsername());
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		Map searchMap=getSearchValue();
    		searchMap.put("id", userpo.getId());
    		pager =personalscoreService.searchPersonalscoreInfo(searchMap,pager);
    		addJsonPager("pageData",pager);
    	}
    }
    /**
     * 查询所有积分列表
     * @throws Exception
     * @throws BaseException
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchAllScoreList()throws Exception, BaseException{
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		if(!AssertUtil.isEmpty(getSearchValue().get("type"))){
        		String type=(String) getSearchValue().get("type");
                if("3".equals(type)){
                	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
                	getSearchValue().put("userid", uservo.getUserId());
                }
    		}
    		pager =personalscoreService.searchPersonalscore(getSearchValue(),pager);
    		addJsonPager("pageData",pager);
    	
    }
    //个人空间》组织生活
    /**
     * 我的党课
     * @throws BaseException 
     * @throws Exception 
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchPersonalPartyMeetingList() throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		getSearchValue().put("userid", uservo.getUserId());
    		getSearchValue().put("type", "2");
    		getSearchValue().put("status", "1");
    		
    		String oldkeword=(String)getSearchValue().get("keyword");//根据关键字查询
    		if(!AssertUtil.isEmpty(oldkeword)){
    		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
    		getSearchValue().remove("keyword");
    		getSearchValue().put("keyword", "%"+keyword+"%");
    		}
    		
    		String sid=(String) getSearchValue().get("sid");//根据状态查询
    		if(!AssertUtil.isEmpty(sid)){
    			if("1".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("singInStatus", 0);
    				getSearchValue().put("forLeaveStatus", 0);
    			}else if("2".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("secondStatus", 1);
    			}else if("3".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 1);
    			}else{
    				getSearchValue().remove("sid");
    			}
    		}
    		pager=membercenterService.getMeetingListByUserid(getSearchValue(),pager);
    		if(!AssertUtil.isEmpty(pager.getPageData())){
    			List<PartyLectureVO> list=(List<PartyLectureVO>) pager.getPageData();
    			for(PartyLectureVO vo:list){
    				if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSignInStatus())&&"0".equals(vo.getForLeaveStatus())){
    					vo.setStatusDesc("进行中");
    					if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
        					vo.setStatus("1");
    					}else{
    						vo.setStatus("3");
    					}
    				}else{
    					vo.setStatusDesc("已办");
    					if("1".equals(vo.getForLeaveStatus())){
    						vo.setStatus("2");
    					}
    					if("1".equals(vo.getSignInStatus())){
    						vo.setStatus("2");
    					}
    				}
    				if(vo.getWhetherEnd().equals("1")){
    					vo.setStatusDesc("已结束");
    					vo.setStatus("3");
    				}
    			}
    			pager.setPageData(list);
    		}
            addJsonPager("pageData",pager);
        }
    }
    /**
     * 我的会议
     * @throws BaseException 
     * @throws Exception 
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchPersonalMeetingList() throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		getSearchValue().put("userid", uservo.getUserId());
    		getSearchValue().put("type", "1");
    		getSearchValue().put("status", "1");
    		
    		String oldkeword=(String)getSearchValue().get("keyword");
    		if(!AssertUtil.isEmpty(oldkeword)){
    		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
    		getSearchValue().remove("keyword");
    		getSearchValue().put("keyword", "%"+keyword+"%");
    		}
    		
    		String sid=(String) getSearchValue().get("sid");
    		if(!AssertUtil.isEmpty(sid)){
    			if("1".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("singInStatus", 0);
    				getSearchValue().put("forLeaveStatus", 0);
    			}else if("2".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("secondStatus", 1);
    			}else if("3".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 1);
    			}else{
    				getSearchValue().remove("sid");
    			}
    		}
    		pager=membercenterService.getMeetingListByUserid(getSearchValue(),pager);
    		if(!AssertUtil.isEmpty(pager.getPageData())){
    			List<MeetingVO> list=(List<MeetingVO>) pager.getPageData();
    			for(MeetingVO vo:list){
    				if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSignInStatus())&&"0".equals(vo.getForLeaveStatus())){
    					vo.setStatusDesc("进行中");
    					if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
        					vo.setStatus("1");
    					}else{
    						vo.setStatus("3");
    					}
    				}else{
    					vo.setStatusDesc("已办");
    					if("1".equals(vo.getForLeaveStatus())){
    						vo.setStatus("2");
    					}
    					if("1".equals(vo.getSignInStatus())){
    						vo.setStatus("2");
    					}
    				}
    				if(vo.getWhetherEnd().equals("1")){
    					vo.setStatusDesc("已结束");
    					vo.setStatus("3");
    				}
    			}
    			pager.setPageData(list);
    		}
    		addJsonPager("pageData",pager);
        }
    }
    
    /**
     * 民主生活会
     * @throws BaseException 
     * @throws Exception 
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchDemocrticLifeList() throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		getSearchValue().put("userid", uservo.getUserId());
    		String oldkeword=(String)getSearchValue().get("keyword");
    		getSearchValue().put("status", "1");
    		
    		if(!AssertUtil.isEmpty(oldkeword)){
	    		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
	    		getSearchValue().remove("keyword");
	    		getSearchValue().put("keyword", "%"+keyword+"%");
    		}
    		
    		String sid=(String) getSearchValue().get("sid");
			getSearchValue().remove("sid");
    		if(!AssertUtil.isEmpty(sid)){
    			if("1".equals(sid)){
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("singInStatus", 0);
    				getSearchValue().put("forLeaveStatus", 0);
    			}else if("2".equals(sid)){
    				getSearchValue().put("whetherEnd", 0);
//    				getSearchValue().put("secondStatus", 1);
    			}else if("3".equals(sid)){
    				getSearchValue().put("whetherEnd", 1);
    			}
    		}
			getSearchValue().put("mobileClient", "mobileClient");
			pager=democrticLifeService.searchDemocrticLife(getSearchValue(), pager);
    		if(!AssertUtil.isEmpty(pager.getPageData())){
    			DemocrticLifeVO dlVo = null;
    			List<PartyListClientVO> list = (List<PartyListClientVO>) pager.getPageData();
    			List<DemocrticLifeVO> plcList = new ArrayList<DemocrticLifeVO>();
    			for(PartyListClientVO vo:list){
    				dlVo = new DemocrticLifeVO();
					dlVo.setId(vo.getId());
					dlVo.setTitle(vo.getTitle());
					dlVo.setCreateUserName(vo.getCreateUserName());
					dlVo.setCreateTime(vo.getCreateTime());
					dlVo.setStartTime(vo.getStartTime());
					dlVo.setEndTime(vo.getEndTime());
					dlVo.setForLeaveStatus(vo.getForLeaveStatus());
					if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
						dlVo.setStatus("1");
					}else{
						dlVo.setStatus("3");
					}
					plcList.add(dlVo);
    			}
    			pager.setPageData(plcList);
    		}
    		addJsonPager("pageData",pager);
        }
    }
    
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		DemocrticLifeVO vo = democrticLifeService.getDemocrticLifeById(id);
		if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
			vo.setStatus("1");
		}else{
			vo.setStatus("3");
		}
		if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSingInStatus())&&"0".equals(vo.getForLeaveStatus())){
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
		addJsonObj("democrticLifeVO", vo);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
    
    /**
     * 我的支部活动
     * @throws BaseException 
     * @throws Exception 
     */
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void searchBranchActivityListByUserid() throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
    		Pager pager = new Pager(ServletActionContext.getRequest(),10);
    		getSearchValue().put("userid", uservo.getUserId());
    		getSearchValue().put("status", "1");
    		
    		String oldkeword=(String)getSearchValue().get("keyword");
    		if(!AssertUtil.isEmpty(oldkeword)){
    		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
    		getSearchValue().remove("keyword");
    		getSearchValue().put("keyword", "%"+keyword+"%");
    		}
    		
    		String sid=(String) getSearchValue().get("sid");
    		if(!AssertUtil.isEmpty(sid)){
    			if("1".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("singInStatus", 0);
    				getSearchValue().put("forLeaveStatus", 0);
    			}else if("2".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 0);
    				getSearchValue().put("secondStatus", 1);
    			}else if("3".equals(sid)){
    				getSearchValue().remove("sid");
    				getSearchValue().put("whetherEnd", 1);
    			}else{
    				getSearchValue().remove("sid");
    			}
    		}
    		pager=membercenterService.getBranchActivityListByUserid(getSearchValue(),pager);
    		if(!AssertUtil.isEmpty(pager.getPageData())){
    			List<BranchActivityVO> list=(List<BranchActivityVO>) pager.getPageData();
    			for(BranchActivityVO vo:list){
    				if("0".equals(vo.getWhetherEnd())&&"0".equals(vo.getSignInStatus())&&"0".equals(vo.getForLeaveStatus())){
    					vo.setStatusDesc("进行中");
    					if(new Date().before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()))){
        					vo.setStatus("1");
    					}else{
    						vo.setStatus("3");
    					}
    				}else{
    					vo.setStatusDesc("已办");
    					if("1".equals(vo.getForLeaveStatus())){
    						vo.setStatus("2");
    					}
    					if("1".equals(vo.getSignInStatus())){
    						vo.setStatus("2");
    					}
    				}
    				if(vo.getWhetherEnd().equals("1")){
    					vo.setStatusDesc("已结束");
    					vo.setStatus("3");
    				}
    			}
    			pager.setPageData(list);
    		}
    		addJsonPager("pageData",pager);
        }
    }
    //签到
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
    public void updateSingInStatus()throws Exception, BaseException{
    	LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		TbMeetingUserPO po= this.meetinguserService.getMeetingUserByMeetingId(id,uservo.getUserId());
    		if(po.getSignInStatus()==1){
    			setActionResult("-1", "您已经签到过,请勿重复请假!");
				logger.warn("重复签到>>>,党员ID=="+po.getUserId());
    		}else{
    		po.setSignInStatus(1l);
    		po.setSignInTime(new Date());
    		membercenterService.updatePO(po, false);
    		}

    }
    //请假和取消请假
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
    public void updateForLeaveStatus()throws Exception, BaseException{
    	if("1".equals(type)){//请假
    		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
    		TbMeetingUserPO po= this.meetinguserService.getMeetingUserByMeetingId(id,uservo.getUserId());
    		if(po.getForLeaveStatus()==1){
    			setActionResult("-1", "您已经请假,请勿重复请假!");
				logger.warn("重复请假>>>,党员ID=="+po.getUserId());
    		}else{
    		po.setForLeaveStatus(1l);
    		po.setForLeaveTime(new Date());
    		membercenterService.updatePO(po, false);
    		}
    	}
    	if("2".equals(type)){//取消请假
    		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
    		TbMeetingUserPO po= this.meetinguserService.getMeetingUserByMeetingId(id,uservo.getUserId());
    		po.setForLeaveStatus(0l);
    		po.setForLeaveTime(new Date());
    		membercenterService.updatePO(po, false);
    	}
    }
    //查询所有志愿活动
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchAllVolunteerActivity() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		getSearchValue().put("userid", uservo.getUserId());
		String oldkeword=(String)getSearchValue().get("keyword");
		if(!AssertUtil.isEmpty(oldkeword)){
		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
		getSearchValue().remove("keyword");
		getSearchValue().put("keyword", "%"+keyword+"%");
		}
		//getSearchValue().put("status", "2");
		pager = volunteerService.searchVolunteer(getSearchValue(), pager);
		if(!AssertUtil.isEmpty(pager.getPageData())){
			List<VolunteerVO> list=(List<VolunteerVO>) pager.getPageData();
			for(VolunteerVO vo:list){
				if("0".equals(vo.getSignUpStatus())){ // 没报名
					vo.setSignUpStatusDesc("未报名");
					vo.setSignUpStatus("0"); // 可报名
				}else{
					vo.setSignUpStatusDesc("已报名");
					vo.setSignUpStatus("1"); // 不可报名
				}
				if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()).after(new Date())){	//未开始
					vo.setStatusDesc("未开始");
				}else if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()).before(new Date())){	//已开始
					vo.setStatusDesc("进行中");
					vo.setSignUpStatus("1"); // 不可报名
					if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getEndTime()).before(new Date())){
						vo.setStatusDesc("已结束");
					}
				}
			}
			pager.setPageData(list);
		}
		addJsonPager("pageData", pager);
	}
	//查询个人已报名志愿活动
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchVolunteerActivity() throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
        Pager pager = new Pager(ServletActionContext.getRequest(),10);
        getSearchValue().put("userid", uservo.getUserId());
        String oldkeword=(String)getSearchValue().get("keyword");
		if(!AssertUtil.isEmpty(oldkeword)){
		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
		getSearchValue().remove("keyword");
		getSearchValue().put("keyword", "%"+keyword+"%");
		}
		getSearchValue().put("status", "2");
		pager = volunteerService.searchVolunteerByUserId(getSearchValue(), pager);
		if(!AssertUtil.isEmpty(pager.getPageData())){
			List<VolunteerVO> list=(List<VolunteerVO>) pager.getPageData();
			for(VolunteerVO vo:list){
				if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()).after(new Date())){	//未开始
					vo.setStatusDesc("未开始");
					vo.setSignUpStatus("0");
				}else if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getStartTime()).before(new Date())){	//已开始
					vo.setStatusDesc("进行中");
					vo.setSignUpStatus("1");
					if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getEndTime()).before(new Date())){
						vo.setStatusDesc("已结束");
					}
				}
			}
			pager.setPageData(list);
		}
		
		addJsonPager("pageData", pager);
        }
	}
	/**
	 * 报名志愿活动
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
	public void signUp()throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		TbMeetingUserPO meetingUserPO= this.meetinguserService.getMeetingUserByMeetingId(id,uservo.getUserId());
		if("1".equals(type)){
			if(!AssertUtil.isEmpty(meetingUserPO)){
				if(meetingUserPO.getSignUpStatus() == 1){
					setActionResult("-1", "您已经报过名,请勿重复报名!");
					logger.warn("重复报名>>>,党员ID=="+meetingUserPO.getUserId());
				}else{
					meetingUserPO.setSignUpStatus(1l);
					meetingUserPO.setSignUpTime(new Date());
					this.meetinguserService.updatePO(meetingUserPO, false);
					setActionResult("0", "报名成功!");
				}
			}else{
				meetingUserPO = new TbMeetingUserPO(); 
				meetingUserPO.setId(UUID.randomUUID().toString().toLowerCase());
				meetingUserPO.setMeetingId(id);
				meetingUserPO.setUserId(uservo.getUserId());
				meetingUserPO.setSignUpStatus(1l);
				meetingUserPO.setSignUpTime(new Date());
				meetingUserPO.setCreateTime(new Date());
				this.meetinguserService.insertPO(meetingUserPO, false);
				setActionResult("0", "报名成功!");
			}
		}else if("2".equals(type)){
			if(AssertUtil.isEmpty(meetingUserPO)){
				logger.warn("没有报名参加活动的用户正在尝试取消报名>>>,党员ID=="+uservo.getUserId());
				setActionResult("-1", "你没有报过名，不需要取消");
			}else{
					meetingUserPO.setSignUpStatus(0l);
					meetingUserPO.setSignUpTime(new Date());
					this.meetinguserService.updatePO(meetingUserPO, false);
					setActionResult("0", "取消报名成功!");
			}
		}
	}
	/**
	 * 志愿活动详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxViewVolunteerActivity() throws Exception, BaseException {
		VolunteerVO xxPO = volunteerService.getVolunteerById(id);
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		TbMeetingUserPO meetingUserPO= this.meetinguserService.getMeetingUserByMeetingId(id,uservo.getUserId());
		if("0".equals(xxPO.getSignUpStatus()) || (null != meetingUserPO && 0L == meetingUserPO.getSignUpStatus())){ // 没报名
			xxPO.setSignUpStatus("0"); // 可报名
		}else{
			xxPO.setSignUpStatus("1"); // 不可报名
		}
		if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(xxPO.getStartTime()).before(new Date())){	//已开始
			xxPO.setSignUpStatus("1"); // 不可报名
		}
		addJsonObj("tbVolunteerActivityPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	//查询个人思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchUserReportList() throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
        Pager pager = new Pager(ServletActionContext.getRequest(),10);
        getSearchValue().put("userid", uservo.getUserId());
        String oldkeword=(String)getSearchValue().get("keyword");
		if(!AssertUtil.isEmpty(oldkeword)){
		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
		getSearchValue().remove("keyword");
		getSearchValue().put("keyword", "%"+keyword+"%");
		}
		pager = membercenterService.searchUserReportList(getSearchValue(), pager);
		addJsonPager("pageData", pager);
        }
	}
	//新增思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "新增成功", faileMsg = "新增失败"))
	public void addUserReport()throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		tbIdeologyReportPO.setCreateTime(new Date());
		tbIdeologyReportPO.setCreateUserId(uservo.getUserId());
		tbIdeologyReportPO.setOrganizationId(uservo.getOrganizationId());
		membercenterService.insertPO(tbIdeologyReportPO,true);
	}
	//修改思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
	public void updateUserReport()throws Exception, BaseException {
		if(tbIdeologyReportPO.getStatus()==1||tbIdeologyReportPO.getStatus()==2){
			LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
			tbIdeologyReportPO.setReviewUserId(uservo.getUserId());
			tbIdeologyReportPO.setReviewTime(new Date());
		}
		membercenterService.updatePO(tbIdeologyReportPO,false);
	}
	//查看思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void viewUserReport()throws Exception, BaseException {
		tbIdeologyReportPO = membercenterService.searchByPk(TbIdeologyReportPO.class, id);
		addJsonFormateObj("tbIdeologyReportPO", tbIdeologyReportPO);
	}
	//删除思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "删除成功", faileMsg = "删除失败"))
	public void delUserReport()throws Exception, BaseException {
		tbIdeologyReportPO = membercenterService.searchByPk(TbIdeologyReportPO.class, id);
		membercenterService.delPO(tbIdeologyReportPO);
	}
	//判断当前用户是否为书记
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void checkLoginUser()throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
		boolean result=membercenterService.searchPositionByUserId(uservo.getUserId());
		if(result){
			logger.info("当前用户为书记");
			addJsonObj("result", "1");
		}else{
			logger.info("当前用户不是书记");
			addJsonObj("result", "0");
		}
	}
	//查询支部书记下面的党员的思想汇报
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void searchUserReportByOrgIdList() throws Exception, BaseException {
		LoginUserVO uservo=(LoginUserVO) getSessionObj().getAttribute("uservo");
        if(!AssertUtil.isEmpty(uservo)){
        Pager pager = new Pager(ServletActionContext.getRequest(),10);
        getSearchValue().put("orgId", uservo.getOrganizationId());
        getSearchValue().put("status", "0");
        String oldkeword=(String)getSearchValue().get("keyword");
		if(!AssertUtil.isEmpty(oldkeword)){
		String keyword=URLDecoder.decode(oldkeword, "UTF-8");
		getSearchValue().remove("keyword");
		getSearchValue().put("keyword", "%"+keyword+"%");
		}
		pager = membercenterService.searchUserReportList(getSearchValue(), pager);
		addJsonPager("pageData", pager);
        }
	}
	public void setPartymember(TbPartyMenberInfoPO partymember) {
		this.partymember = partymember;
	}

	public TbPartyMenberInfoPO getPartymember() {
		return partymember;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicture() {
		return picture;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setCfPassword(String cfPassword) {
		this.cfPassword = cfPassword;
	}

	public String getCfPassword() {
		return cfPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTbIdeologyReportPO(TbIdeologyReportPO tbIdeologyReportPO) {
		this.tbIdeologyReportPO = tbIdeologyReportPO;
	}

	public TbIdeologyReportPO getTbIdeologyReportPO() {
		return tbIdeologyReportPO;
	}
    
    


}
