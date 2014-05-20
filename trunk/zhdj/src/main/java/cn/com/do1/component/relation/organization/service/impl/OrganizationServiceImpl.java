package cn.com.do1.component.relation.organization.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.relation.organization.dao.IOrganizationDAO;
import cn.com.do1.component.relation.organization.service.IOrganizationService;
import cn.com.do1.component.relation.organization.vo.OrganizationTransferVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseService implements
		IOrganizationService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrganizationServiceImpl.class);

	private IOrganizationDAO organizationDAO;

	@Resource
	public void setOrganizationDAO(IOrganizationDAO organizationDAO) {
		this.organizationDAO = organizationDAO;
		setDAO(organizationDAO);
	}

	public Pager searchOrganization(Map searchMap, Pager pager)
			throws Exception, BaseException {
		return organizationDAO.searchOrganization(searchMap, pager);
	}

	@Override
	public OrganizationTransferVO getOrganizationTransferById(String id)
			throws Exception {
		return this.organizationDAO.getOrganizationTransferById(id);
	}
}
