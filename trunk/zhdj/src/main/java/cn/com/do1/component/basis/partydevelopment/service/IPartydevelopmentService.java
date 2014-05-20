package cn.com.do1.component.basis.partydevelopment.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevApplyForVO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevelopmentMenberVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IPartydevelopmentService extends IBaseService {
	Pager searchPartydevelopment(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	void savePartydevelopmentPO(TbPartyDevelopmentMenberPO po)
			throws Exception, BaseException;

	PartyDevelopmentMenberVO getPartyDevelopmentById(String id)
			throws Exception;

	List<TbOrganizationVO> getOrganizations() throws Exception;

	PartyDevApplyForVO getPartyDevApplyForLastByUserId(String userId) throws Exception;

	List<PartyDevApplyForVO> getPartyDevApplyForPassByUserId(String userId) throws Exception;
}
