package cn.com.do1.component.basis.userinfo.dao;

import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IUserinfoDAO extends IBaseDAO {
	public Pager searchUserInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception;

	public UserInfoVO getUserInfoById(String id) throws Exception;
}
