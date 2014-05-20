package cn.com.do1.component.mobileclient.userinfo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbPostInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface IUserInfoService extends IBaseService{

	/**
	 * 根据userId获取群众用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	CrowdUserInfoVO getCrowdUserInfoByUserId(String userId) throws Exception,
	BaseException;
	
	/**
	 * 根据userId获取党员用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	PartyMenberUserInfoVO getPartyMenberUserInfoByUserId(String userId) throws Exception,
	BaseException;

	/**
	 * 保存党员信息
	 * @param partyMenber
	 */
	void updatePartMenber(TbPartyMenberInfoPO partyMenber)throws Exception, BaseException;

	/**
	 * 保存群众用户信息
	 * @param userInfo
	 */
	void updateCrowdUserInfo(TbPartyDevelopmentMenberPO userInfo)throws Exception;;
	
	
	PartyMenberUserInfoVO  countUserInfo(Map<String, Object> searchValue) throws SQLException;
	
}
