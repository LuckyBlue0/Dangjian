package cn.com.do1.component.mobileclient.suggestion.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.mobileclient.suggestion.dao.*;
import cn.com.do1.component.mobileclient.suggestion.service.ISuggestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("suggestionService")
public class SuggestionServiceImpl extends BaseService implements ISuggestionService {
    private final static transient Logger logger = LoggerFactory.getLogger(SuggestionServiceImpl.class);

    private ISuggestionDAO suggestionDAO;
    @Resource
    public void setSuggestionDAO(ISuggestionDAO suggestionDAO) {
        this.suggestionDAO = suggestionDAO;
        setDAO(suggestionDAO);
    }

    public Pager searchSuggestion(Map searchMap,Pager pager) throws Exception, BaseException {
        return suggestionDAO.searchSuggestion(searchMap,pager);
    }
}
