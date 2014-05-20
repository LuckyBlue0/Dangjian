package cn.com.do1.component.basis.partymenber.dao;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IPartymenberDAO extends IBaseDAO {
	public Pager searchPartyMenber(Map<?, ?> searchValue, Pager pager)
			throws Exception;

	public Pager searchPartyRelation(Map<?, ?> searchValue, Pager pager)
			throws Exception;

	public TbPartyMenberInfoPO getPartyMenberByUserName(String userName)
			throws Exception;

	public PartyMenberInfoVO getPartyMenberById(String id) throws Exception;

	public List<TbOrganizationVO> getOrganizations() throws Exception;
}
