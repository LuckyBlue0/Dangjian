package cn.com.do1.component.score.scoreleave.dao.impl;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;
import cn.com.do1.component.score.scoreleave.dao.IScoreLeaveDAO;
import cn.com.do1.component.score.scoreleave.vo.ScoreLeaveVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class ScoreLeaveDAOImpl extends BaseDAOImpl implements IScoreLeaveDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(ScoreLeaveDAOImpl.class);

	final static String infoCondSQL = " from tb_score_leave t where t.type = :type ";
	final static String infoCountSQL = "select count(1) " + infoCondSQL;
	final static String infoSearchSQL = " select t.*,t.start_score||'～'||t.end_score score_desc "
			+ infoCondSQL;

	@Override
	public ScoreLeaveVO getScoreLeaveById(String id) throws Exception {
		String sql = "select * from tb_score_leave t where t.id=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(ScoreLeaveVO.class);
	}

	@Override
	public Pager searchScoreLeave(Map searchMap, Pager pager)
			throws Exception, BaseException {
		return super.pageSearchByField(ScoreLeaveVO.class, infoCountSQL,
				infoSearchSQL, searchMap, pager);
	}

	@Override
	public List<ScoreLeaveVO> getScoreLeaves(Map<?, ?> searchMap)
			throws Exception, BaseException {
		StringBuilder sb = new StringBuilder(
				"select t.*,t.start_score||'～'||t.end_score score_desc  from tb_score_leave t ");
		if (null != searchMap.get("type")) {
			sb.append(" where t.type = :type ");
		}
		sb.append(" order by t.type,t.name ");
		super.preparedSql(sb.toString());
		return super.getList(ScoreLeaveVO.class);
	}

}
