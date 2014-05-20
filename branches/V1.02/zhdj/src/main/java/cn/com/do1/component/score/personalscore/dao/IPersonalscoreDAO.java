package cn.com.do1.component.score.personalscore.dao;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreInfoVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IPersonalscoreDAO extends IBaseDAO {
	Pager searchPersonalscore(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	Pager searchPersonalscoreInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException;

	void savePersonalscorePO(TbPersonalScoreInfoPO po) throws Exception,
			BaseException;

	TbPersonalScoreInfoPO getPersonalScoreInfoByUserId(String userId)
			throws Exception;

	void updatePersonalscore() throws Exception, BaseException;

	List<PersonalScoreVO> getPersonalScore(Map<?, ?> searchMap)
			throws Exception, BaseException;

	List<PersonalScoreInfoVO> getPersonalScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException;

	PersonalScoreInfoVO getPersonalScoreInfoByid(String id) throws Exception;

	PersonalScoreVO getPersonalScoreByUserId(String userId) throws Exception;

}
