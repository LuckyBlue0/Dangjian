package cn.com.do1.component.mobileclient.suggestion.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.suggestion.dao.ISuggestionDAO;
import cn.com.do1.component.mobileclient.suggestion.model.TbSuggestionPO;
import cn.com.do1.component.mobileclient.version.model.TbVersionPO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class SuggestionDAOImpl extends BaseDAOImpl implements ISuggestionDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(SuggestionDAOImpl.class);
    public Pager searchSuggestion(Map searchMap,Pager pager) throws Exception, BaseException {
    	String searchSQL="select * from tb_suggestion where type = :type order by create_time desc ";
    	String countSQL="select count(*) from tb_suggestion where type = :type ";
    	return super.pageSearchByField(TbSuggestionPO.class, countSQL, searchSQL, searchMap, pager);
    }
} 
