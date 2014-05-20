package cn.com.do1.component.score.orgscore.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreInfoVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IOrgscoreService extends IBaseService {
	Pager searchOrgscore(Map<?, ?> searchMap, Pager pager) throws Exception,
			BaseException;

	Pager searchOrgscoreInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	void saveOrgscoreInfoPO(TbOrganizationScoreInfoPO po) throws Exception,
			BaseException;

	TbOrganizationScoreInfoPO getOrgScoreInfoByUserId(String userId)
			throws Exception;

	void updateOrgscore() throws Exception, BaseException;

	List<OrganizationScoreVO> getOrgScore(Map<?, ?> searchMap)
			throws Exception, BaseException;

	List<OrganizationScoreInfoVO> getOrgScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException;

	OrganizationScoreInfoVO getOrgScoreById(String id) throws Exception;

	OrganizationScoreVO getOrgScoreByOrgId(String orgId) throws Exception;

}
