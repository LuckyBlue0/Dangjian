package cn.com.do1.component.score.scoreleave.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.score.scoreleave.dao.IScoreLeaveDAO;
import cn.com.do1.component.score.scoreleave.service.IScoreLeaveService;
import cn.com.do1.component.score.scoreleave.vo.ScoreLeaveVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("scoreLeaveService")
public class ScoreLeaveServiceImpl extends BaseService implements IScoreLeaveService {
    private final static transient Logger logger = LoggerFactory.getLogger(ScoreLeaveServiceImpl.class);

    private IScoreLeaveDAO scoreLeaveDAO;
    @Resource
    public void setScoreLeaveDAO(IScoreLeaveDAO scoreLeaveDAO) {
        this.scoreLeaveDAO = scoreLeaveDAO;
        setDAO(scoreLeaveDAO);
    }

    public Pager searchScoreLeave(Map searchMap,Pager pager) throws Exception, BaseException {
        return scoreLeaveDAO.searchScoreLeave(searchMap, pager);
    }

	@Override
	public ScoreLeaveVO getScoreLeaveById(String id) throws Exception {
		return scoreLeaveDAO.getScoreLeaveById(id);
	}

	@Override
	public List<ScoreLeaveVO> getScoreLeaves(Map<?, ?> searchMap)
			throws Exception, BaseException {
		return scoreLeaveDAO.getScoreLeaves(searchMap);
	}
}
