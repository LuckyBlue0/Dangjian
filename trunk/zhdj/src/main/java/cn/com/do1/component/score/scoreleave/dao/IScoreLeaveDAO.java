package cn.com.do1.component.score.scoreleave.dao;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.score.scoreleave.vo.ScoreLeaveVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public interface IScoreLeaveDAO extends IBaseDAO {
	Pager searchScoreLeave(Map<?, ?> searchMap, Pager pager) throws Exception,
			BaseException;

	ScoreLeaveVO getScoreLeaveById(String id) throws Exception;

	List<ScoreLeaveVO> getScoreLeaves(Map<?, ?> searchMap) throws Exception,
			BaseException;
}
