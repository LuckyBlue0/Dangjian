package cn.com.do1.component.basis.userinfo.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.basis.userinfo.dao.IUserinfoDAO;
import cn.com.do1.component.basis.userinfo.service.IUserinfoService;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("userinfoService")
public class UserinfoServiceImpl extends BaseService implements
		IUserinfoService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(UserinfoServiceImpl.class);

	private IUserinfoDAO userinfoDAO;

	@Resource
	public void setUserinfoDAO(IUserinfoDAO userinfoDAO) {
		this.userinfoDAO = userinfoDAO;
		setDAO(userinfoDAO);
	}

	public Pager searchUserinfo(Map searchMap, Pager pager) throws Exception,
			BaseException {
		return userinfoDAO.searchUserInfo(searchMap, pager);
	}

	@Override
	public UserInfoVO getUserInfoById(String id) throws Exception,
			BaseException {
		return this.userinfoDAO.getUserInfoById(id);
	}
}
