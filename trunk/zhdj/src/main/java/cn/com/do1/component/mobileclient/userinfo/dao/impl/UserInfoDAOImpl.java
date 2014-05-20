package cn.com.do1.component.mobileclient.userinfo.dao.impl;

import java.sql.SQLException;

import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.userinfo.dao.IUserInfoDAO;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class UserInfoDAOImpl extends BaseDAOImpl implements IUserInfoDAO{

	@Override
	public CrowdUserInfoVO getCrowdUserInfoByUserId(String userId) throws SQLException {
		String sql = "select t.* from tb_party_dev_menber_info t where t.ID=:userId";
		super.preparedSql(sql);
		super.setPreValue("userId", userId);
		return super.executeQuery(CrowdUserInfoVO.class);
	}

	@Override
	public PartyMenberUserInfoVO getPartyMenberUserInfoByUserId(String userId) throws SQLException {
		String sql = "select t.*,o.id oraganization_id,o.organization_name from TB_PARTY_MENBER_INFO t left join tb_organization o on t.organization_id = o.id where t.ID=:userId";
		super.preparedSql(sql);
		super.setPreValue("userId", userId);
		return super.executeQuery(PartyMenberUserInfoVO.class);
	}

}
