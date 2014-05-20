package cn.com.do1.component.mobileclient.userinfo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.index.membercenter.model.PartyMenberScoreVO;
import cn.com.do1.component.index.membercenter.service.IMembercenterService;
import cn.com.do1.component.mobileclient.userinfo.dao.IUserInfoDAO;
import cn.com.do1.component.mobileclient.userinfo.service.IUserInfoService;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;
import cn.com.do1.component.shyk.meeting.model.TbMeetingPO;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.util.DateTimeUtil;
import cn.com.do1.component.util.FileUploadUtil;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseService implements IUserInfoService {
	private final static transient Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	@Resource
	private IUserInfoDAO userInfoDAO;
	@Resource
	private IPartymenberService partymenberService;
	@Resource
	private IMembercenterService membercenterService;
	@Resource
	private IPersonalscoreService personalscoreService;

	@Override
	public CrowdUserInfoVO getCrowdUserInfoByUserId(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return userInfoDAO.getCrowdUserInfoByUserId(userId);
	}

	@Override
	public PartyMenberUserInfoVO getPartyMenberUserInfoByUserId(String userId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		PartyMenberUserInfoVO partyMenberInfo = userInfoDAO.getPartyMenberUserInfoByUserId(userId);
		boolean flag = membercenterService.searchPositionByUserId(userId);
		// 是否是支部书记
		if (flag) {
			partyMenberInfo.setIsBranchLeader(1);
		} else {
			partyMenberInfo.setIsBranchLeader(0);
		}
		// 党龄
		if (partyMenberInfo.getPartyTime() != null) {
			Calendar cal = Calendar.getInstance();
			int currentYear = cal.get(Calendar.YEAR);
			cal.setTime(DateTimeUtil.string2Date(partyMenberInfo.getPartyTime(), "yyyy-MM-dd"));
			int joinPartyYear = cal.get(Calendar.YEAR);
			partyMenberInfo.setPartyAge((currentYear - joinPartyYear));
		}
		// 获取党员个人总积分,积分排名,支部内排名
		setPersonalScore(userId, partyMenberInfo);

		// 三会一课待办数量
		List<TbMeetingPO> meetingCount = membercenterService.getMeetingByUserid(userId);
		List<TbPartyLecturePO> partyLectureCount = membercenterService.getPartyLectureByUserid(userId);
		partyMenberInfo.setMeetingCount(meetingCount.size() + partyLectureCount.size());

		// 支部活动待办数量
		List<BranchActivityVO> listact = membercenterService.getBranchActivityByUserid(userId);
		partyMenberInfo.setActivityCount(listact.size());

		// 民主生活会待办数量
		List<DemocrticLifeVO> democrticLifes = membercenterService.getDemocrticLifeByUserId(userId);
		partyMenberInfo.setDemocrticlifeCount(democrticLifes.size());

		// 志愿活动待办数量
		List<VolunteerVO> volunteers = membercenterService.getVolunteerByUserId(userId);
		partyMenberInfo.setVolnteerCount(volunteers.size());

		// 思想汇报待办数量
		// List<IdeologyReportVO>
		// listrep=membercenterService.searchReportByUserid(userId);
		// partyMenberInfo.setIdeologyReportCount(listrep.size());

		return partyMenberInfo;
	}

	/**
	 * 设置党员积分
	 * 
	 * @param userId
	 * @param partyMenberInfo
	 * @throws Exception
	 */
	private void setPersonalScore(String userId, PartyMenberUserInfoVO partyMenberInfo) throws Exception {
		PersonalScoreVO personalScore = personalscoreService.getPersonalScoreByUserId(userId);
		String integralTotal = "0";
		String integralRank = "0";
		String branchRanking = "0";

		if (personalScore != null) {
			if (personalScore.getAccumulativeScore() != null) {
				integralTotal = personalScore.getAccumulativeScore();
			}

			if (personalScore.getRanking() != null) {
				integralRank = personalScore.getRanking();
			}

			if (personalScore.getBranchRanking() != null) {
				branchRanking = personalScore.getBranchRanking();
			}
		}
		// 个人总积分
		partyMenberInfo.setIntegralTotal(integralTotal);

		// 个人积分排名
		partyMenberInfo.setIntegralRank(integralRank);

		// 支部内排名
		partyMenberInfo.setBranchRanking(branchRanking);
	}

	public PartyMenberScoreVO getMenberScore(String userName) throws Exception, BaseException {
		TbPartyMenberInfoPO userpo = partymenberService.getPartyMenberByUserName(userName);
		Pager pager = new Pager(ServletActionContext.getRequest(), 999999);
		Map searchMap = new HashMap();
		searchMap.put("id", userpo.getId());
		PartyMenberScoreVO scorevo = new PartyMenberScoreVO();

		scorevo = membercenterService.searchPersonalScore(userpo, searchMap, pager);
		if (AssertUtil.isEmpty(scorevo.getTatalScore())) {
			scorevo.setTatalScore("0");
		}
		if (AssertUtil.isEmpty(scorevo.getSort())) {
			scorevo.setSort("无");
		}
		return scorevo;
	}

	@Override
	public void updatePartMenber(TbPartyMenberInfoPO partyMenber) throws Exception, BaseException {
		// TODO Auto-generated method stub
		if (!AssertUtil.isEmpty(partyMenber.getPortraitPic())) {
			String picPath = fileUpload(partyMenber.getPortraitPic(), "upload/image");
			partyMenber.setPortraitPic(picPath);
		}
		// 同步用户信息
		partymenberService.updateUserInfo(partyMenber);
	}

	@Override
	public void updateCrowdUserInfo(TbPartyDevelopmentMenberPO userInfo) throws Exception {
		// TODO Auto-generated method stub
		if (!AssertUtil.isEmpty(userInfo.getPortraitPic())) {
			String picPath = fileUpload(userInfo.getPortraitPic(), "upload/image");
			userInfo.setPortraitPic(picPath);
		}
		super.updatePO(userInfo, false);
	}

	/**
	 * 保存文件
	 * 
	 * @param portraitPic
	 * @throws Exception
	 */
	private String fileUpload(String portraitPic, String baseDir) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		StringBuilder dir = new StringBuilder();
		if ((baseDir.endsWith("/")) || (baseDir.endsWith("\\"))) {
			// dir.append(DateTimeUtil.date2String(date, "yyyyMMdd"));
		} else {
			// dir.append(File.separator);
			// dir.append(DateTimeUtil.date2String(date, "yyyyMMdd"));
		}

		File dirFile = new File(baseDir + dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		StringBuilder fileStr = null;
		fileStr = new StringBuilder(dir.toString());
		// fileStr.append(File.separator);
		fileStr.append(UUID.randomUUID().toString());
		fileStr.append(".jpg");
		File file = new File(baseDir + fileStr.toString());
		if (file.exists()) {
			// continue;
		}

		byte[] byteArray = Base64.decodeBase64(portraitPic);
		ByteArrayInputStream is = null;
		FileOutputStream fos = null;
		boolean noError = true;
		String msg = "";
		try {
			file.createNewFile();
			is = new ByteArrayInputStream(byteArray);
			fos = new FileOutputStream(file);
			byte[] bufferArray = new byte[1024];
			while (is.read(bufferArray) != -1)
				fos.write(bufferArray);
		} catch (Exception e) {
			this.logger.error("保存图片发生错误", e);
			noError = false;
			msg = e.getMessage();
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e1) {
				noError = false;
				msg = e.getMessage();
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception e2) {
				noError = false;
				msg = e.getMessage();
			}
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				noError = false;
				msg = e.getMessage();
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				noError = false;
				msg = e.getMessage();
			}
		}
		if (!noError) {
			if (file.exists()) {
				file.delete();
			}
			throw new Exception("保存文件发生异常,异常提示:" + msg);
		}
		String backURL = "";
		FileUploadUtil up = new FileUploadUtil();
		backURL = up.uploadFileBySMB(file, "upload/image", fileStr.toString());
		return backURL;
	}
}
