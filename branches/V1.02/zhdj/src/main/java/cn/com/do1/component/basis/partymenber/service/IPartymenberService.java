package cn.com.do1.component.basis.partymenber.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IPartymenberService extends IBaseService {
	Pager searchPartymenber(Map<?, ?> searchMap, Pager pager) throws Exception,
			BaseException;

	Pager searchPartyRelation(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	void savePartymenberPO(TbPartyMenberInfoPO po) throws Exception,
			BaseException;

	PartyMenberInfoVO getPartyMenberById(String id) throws Exception;

	List<TbOrganizationVO> getOrganizations() throws Exception;

	String updateUserInfo(TbPartyMenberInfoPO partyMember) throws Exception,
			BaseException;

	/**
	 * 根据用户名查询党员信息
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public TbPartyMenberInfoPO getPartyMenberByUserName(String userName)
			throws Exception;
	
	/**
	 * 更新历史党员用户状态
	 * @throws Exception
	 */
	void updateHisPartyMenberState()throws Exception;
}
