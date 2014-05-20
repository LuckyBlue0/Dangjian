package cn.com.do1.component.mobileclient.userinfo.dao;

import java.sql.SQLException;

import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface IUserInfoDAO {

	CrowdUserInfoVO getCrowdUserInfoByUserId(String userId)throws SQLException;

	PartyMenberUserInfoVO getPartyMenberUserInfoByUserId(String userId)throws SQLException;

}
