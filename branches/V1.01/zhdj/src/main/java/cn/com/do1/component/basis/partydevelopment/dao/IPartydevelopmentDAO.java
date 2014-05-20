package cn.com.do1.component.basis.partydevelopment.dao;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevApplyForVO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevelopmentMenberVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IPartydevelopmentDAO extends IBaseDAO {
	public Pager searchPartyDevelopment(Map<?, ?> searchValue, Pager pager)
			throws Exception;

	public PartyDevelopmentMenberVO getPartyDevelopmentById(String id) throws Exception;

	public List<TbOrganizationVO> getOrganizations() throws Exception;

	PartyDevApplyForVO getPartyDevApplyForLastByUserId(String userId) throws Exception;

	List<PartyDevApplyForVO> getPartyDevApplyForPassByUserId(String userId) throws Exception;
}
