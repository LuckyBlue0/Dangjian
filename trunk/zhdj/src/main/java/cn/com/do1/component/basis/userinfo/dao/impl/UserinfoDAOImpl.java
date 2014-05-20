package cn.com.do1.component.basis.userinfo.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.userinfo.dao.IUserinfoDAO;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class UserinfoDAOImpl extends BaseDAOImpl implements IUserinfoDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(UserinfoDAOImpl.class);
	final static String condSQL = " from TB_USER_INFO t where t.USER_NAME like :userName order by t.REG_TIME desc ";
	final static String countSQL = "select count(1) " + condSQL;
	final static String searchSQL = "select t.* " + condSQL;

	@Override
	public UserInfoVO getUserInfoById(String id) throws Exception {
		String sql = "select t.* from TB_USER_INFO t where t.ID=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(UserInfoVO.class);
	}

	@Override
	public Pager searchUserInfo(Map searchMap, Pager pager) throws Exception {
		return super.pageSearchByField(UserInfoVO.class, countSQL, searchSQL,
				searchMap, pager);
	}

}
