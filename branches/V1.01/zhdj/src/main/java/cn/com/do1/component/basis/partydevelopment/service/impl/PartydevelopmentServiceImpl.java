package cn.com.do1.component.basis.partydevelopment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.basis.partydevelopment.dao.IPartydevelopmentDAO;
import cn.com.do1.component.basis.partydevelopment.model.TbPartyDevelopmentMenberPO;
import cn.com.do1.component.basis.partydevelopment.service.IPartydevelopmentService;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevApplyForVO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevelopmentMenberVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.util.Md5;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("partydevelopmentService")
public class PartydevelopmentServiceImpl extends BaseService implements
		IPartydevelopmentService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartydevelopmentServiceImpl.class);

	private IPartydevelopmentDAO partydevelopmentDAO;

	@Resource
	public void setPartydevelopmentDAO(IPartydevelopmentDAO partydevelopmentDAO) {
		this.partydevelopmentDAO = partydevelopmentDAO;
		setDAO(partydevelopmentDAO);
	}

	@Override
	public List<TbOrganizationVO> getOrganizations() throws Exception {
		return this.partydevelopmentDAO.getOrganizations();
	}

	@Override
	public PartyDevelopmentMenberVO getPartyDevelopmentById(String id)
			throws Exception {
		return this.partydevelopmentDAO.getPartyDevelopmentById(id);
	}

	@Override
	public void savePartydevelopmentPO(TbPartyDevelopmentMenberPO po)
			throws Exception, BaseException {
		po.setId(UUID.randomUUID().toString());
		po.setCreateTime(new Date());
		po.setState(1L);
		po.setPassWord(new Md5().getMD5ofStr(po.getPassWord()).trim()
				.toLowerCase());
		this.partydevelopmentDAO.insertData(po);
	}

	@Override
	public Pager searchPartydevelopment(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException {
		return this.partydevelopmentDAO
				.searchPartyDevelopment(searchMap, pager);
	}

	@Override
	public PartyDevApplyForVO getPartyDevApplyForLastByUserId(String userId)
			throws Exception {
		return this.partydevelopmentDAO.getPartyDevApplyForLastByUserId(userId);
	}

	@Override
	public List<PartyDevApplyForVO> getPartyDevApplyForPassByUserId(
			String userId) throws Exception {
		return this.partydevelopmentDAO.getPartyDevApplyForPassByUserId(userId);
	}
}
