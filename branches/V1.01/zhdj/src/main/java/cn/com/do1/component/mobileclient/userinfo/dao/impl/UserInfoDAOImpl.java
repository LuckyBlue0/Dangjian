package cn.com.do1.component.mobileclient.userinfo.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;
import cn.com.do1.component.gmcc.core.gz.net.gmcc.portalapp2.uipservice.UserInfo;
import cn.com.do1.component.mobileclient.userinfo.dao.IUserInfoDAO;
import cn.com.do1.component.mobileclient.userinfo.vo.CrowdUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.PartyMenberUserInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbPostInfoVO;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;

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
	
	final static String countSQL = "select t.*,(select count(1) from TB_post_INFO t where t.post_user_id=:userId) as post_num ,(select count(1) from tb_reply_info r where r.reply_user_id=:userId) as reply_num,(select count(1) from tb_branch_activity b where b.create_user_id=:userId) as activity_num from tb_party_menber_info t where t.ID=:userId";
    @Override
    public PartyMenberUserInfoVO countUserInfo(Map<String, Object> searchValue) throws SQLException {
        preparedSql(countSQL);
        setPreValues(searchValue);//将参数设置进预置语句      
        return super.executeQuery(PartyMenberUserInfoVO.class);
    }
    
   
  

}
