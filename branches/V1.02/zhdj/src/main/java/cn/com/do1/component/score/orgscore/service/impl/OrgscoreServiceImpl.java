package cn.com.do1.component.score.orgscore.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.score.orgscore.dao.IOrgscoreDAO;
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.orgscore.service.IOrgscoreService;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreInfoVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("orgscoreService")
public class OrgscoreServiceImpl extends BaseService implements
		IOrgscoreService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrgscoreServiceImpl.class);

	private IOrgscoreDAO orgscoreDAO;

	@Resource
	public void setOrgscoreDAO(IOrgscoreDAO orgscoreDAO) {
		this.orgscoreDAO = orgscoreDAO;
		setDAO(orgscoreDAO);
	}

	@Override
	public List<OrganizationScoreVO> getOrgScore(Map<?, ?> searchMap)
			throws Exception, BaseException {
		return this.orgscoreDAO.getOrgScore(searchMap);
	}

	@Override
	public List<OrganizationScoreInfoVO> getOrgScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException {
		return this.orgscoreDAO.getOrgScoreInfo(searchMap);
	}

	@Override
	public TbOrganizationScoreInfoPO getOrgScoreInfoByUserId(String userId)
			throws Exception {
		return this.getOrgScoreInfoByUserId(userId);
	}

	@Override
	public void saveOrgscoreInfoPO(TbOrganizationScoreInfoPO po)
			throws Exception, BaseException {
		this.orgscoreDAO.insertData(po);
	}

	@Override
	public Pager searchOrgscore(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException {
		return this.orgscoreDAO.searchOrgscore(searchMap, pager);
	}

	@Override
	public Pager searchOrgscoreInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException {
		return this.orgscoreDAO.searchOrgscoreInfo(searchMap, pager);
	}

	@Override
	public void updateOrgscore() throws Exception, BaseException {
		this.updateOrgscore();
	}

	@Override
	public OrganizationScoreInfoVO getOrgScoreById(String id) throws Exception {
		return this.orgscoreDAO.getOrgScoreById(id);
	}

	@Override
	public OrganizationScoreVO getOrgScoreByOrgId(String orgId)
			throws Exception {
		return this.orgscoreDAO.getOrgScoreByOrgId(orgId);
	}
}
