package cn.com.do1.component.score.orgscore.dao;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreInfoVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IOrgscoreDAO extends IBaseDAO {
	Pager searchOrgscore(Map<?, ?> searchMap, Pager pager) throws Exception,
			BaseException;

	Pager searchOrgscoreInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	void saveOrgscoreInfoPO(TbOrganizationScoreInfoPO po) throws Exception,
			BaseException;

	TbPersonalScoreInfoPO getOrgScoreInfoByUserId(String userId)
			throws Exception;

	void updateOrgscore() throws Exception, BaseException;

	List<OrganizationScoreVO> getOrgScore(Map<?, ?> searchMap)
			throws Exception, BaseException;

	List<OrganizationScoreInfoVO> getOrgScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException;

	OrganizationScoreInfoVO getOrgScoreById(String userId) throws Exception;

	OrganizationScoreVO getOrgScoreByOrgId(String orgId) throws Exception;

	Pager searchOrgScoreRank(Map searchValue, Pager pager)throws Exception;
}
