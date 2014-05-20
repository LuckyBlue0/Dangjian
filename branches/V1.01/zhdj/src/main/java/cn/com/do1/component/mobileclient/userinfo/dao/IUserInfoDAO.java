package cn.com.do1.component.mobileclient.userinfo.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbPostInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface IUserInfoDAO {

	CrowdUserInfoVO getCrowdUserInfoByUserId(String userId)throws SQLException;

	PartyMenberUserInfoVO getPartyMenberUserInfoByUserId(String userId)throws SQLException;

	PartyMenberUserInfoVO countUserInfo(Map<String, Object> searchValue) throws SQLException ;
	
	 
}
