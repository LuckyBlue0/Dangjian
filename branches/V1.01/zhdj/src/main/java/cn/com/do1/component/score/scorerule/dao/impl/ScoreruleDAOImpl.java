package cn.com.do1.component.score.scorerule.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.data.datafile.model.TbDataFilePO;
import cn.com.do1.component.score.scorerule.dao.IScoreruleDAO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class ScoreruleDAOImpl extends BaseDAOImpl implements IScoreruleDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(ScoreruleDAOImpl.class);
    
    public Pager searchScorerule(Map searchMap,Pager pager) throws Exception, BaseException {
    	String searchSQL="select * from tb_score_rule order by create_time desc";
    	String countSQL="select count(*) from tb_score_rule";
    	return super.pageSearchByField(TbScoreRulePO.class, countSQL, searchSQL, searchMap, pager);
    }
    
    public TbScoreRulePO  searchByType(String itemid)throws Exception, BaseException{
    	String sql="select *　from tb_score_rule where score_type = :itemid";
    	super.preparedSql(sql);
    	super.setPreValue("itemid", itemid);
    	return super.executeQuery(TbScoreRulePO.class);
    }
}
