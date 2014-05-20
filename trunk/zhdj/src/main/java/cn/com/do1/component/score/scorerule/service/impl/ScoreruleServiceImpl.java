package cn.com.do1.component.score.scorerule.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.score.scorerule.dao.*;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("scoreruleService")
public class ScoreruleServiceImpl extends BaseService implements IScoreruleService {
    private final static transient Logger logger = LoggerFactory.getLogger(ScoreruleServiceImpl.class);

    private IScoreruleDAO scoreruleDAO;
    @Resource
    public void setScoreruleDAO(IScoreruleDAO scoreruleDAO) {
        this.scoreruleDAO = scoreruleDAO;
        setDAO(scoreruleDAO);
    }

    public Pager searchScorerule(Map searchMap,Pager pager) throws Exception, BaseException {
    	List<ExItemObj> list =DictOperater.getAllItemByType("personalScore");
    	List<TbScoreRulePO> scoreRuleList=new ArrayList<TbScoreRulePO>();
	    for(ExItemObj dict:list){
	    	TbScoreRulePO oldPO=this.searchByType(dict.getFsDictItemId());
	    	if(AssertUtil.isEmpty(oldPO)){
	    	TbScoreRulePO po=new TbScoreRulePO();
	    	po.setId(UUID.randomUUID().toString());
	    	po.setScoreDesc("");
	    	po.setScoreType(dict.getFsDictItemId());
	    	po.setScoreValue("");
	    	po.setCreateTime(new Date());
	    	po.setScoreAttr("1");
	    	scoreRuleList.add(po);
	    	}
	    }
	    List<ExItemObj> list1 =DictOperater.getAllItemByType("orgScore");
	    for(ExItemObj dict:list1){
	    	TbScoreRulePO oldPO=this.searchByType(dict.getFsDictItemId());
	    	if(AssertUtil.isEmpty(oldPO)){
	    	TbScoreRulePO po=new TbScoreRulePO();
	    	po.setId(UUID.randomUUID().toString());
	    	po.setScoreDesc("");
	    	po.setScoreType(dict.getFsDictItemId());
	    	po.setScoreValue("");
	    	po.setCreateTime(new Date());
	    	po.setScoreAttr("2");
	    	scoreRuleList.add(po);
	    	}
	    }
	    if(!AssertUtil.isEmpty(scoreRuleList)){
	    scoreruleDAO.execBatchInsert(scoreRuleList);
	    }
        return scoreruleDAO.searchScorerule(searchMap,pager);
    }
    public String searchDictDesc(String id)throws Exception, BaseException{
    	String sql="select FS_ITEM_DESC from dictionary where FS_DICT_ITEM_ID = :id";
    	scoreruleDAO.preparedSql(sql);
    	scoreruleDAO.setPreValue("id", id);
    	return scoreruleDAO.executeQuery(String.class);
    }
    

    public TbScoreRulePO  searchByType(String itemid)throws Exception, BaseException{
    	return scoreruleDAO.searchByType(itemid);
    }
}
