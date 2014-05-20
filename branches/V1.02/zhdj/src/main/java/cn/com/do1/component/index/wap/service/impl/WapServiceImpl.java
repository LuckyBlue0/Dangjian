package cn.com.do1.component.index.wap.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.index.wap.dao.*;
import cn.com.do1.component.index.wap.service.IWapService;
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
@Service("wapService")
public class WapServiceImpl extends BaseService implements IWapService {
    private final static transient Logger logger = LoggerFactory.getLogger(WapServiceImpl.class);

    private IWapDAO wapDAO;
    @Resource
    public void setWapDAO(IWapDAO wapDAO) {
        this.wapDAO = wapDAO;
        setDAO(wapDAO);
    }

    public Pager searchWap(Map searchMap,Pager pager) throws Exception, BaseException {
        return wapDAO.pageSearchByField( IBaseDBVO.class,searchMap,null,pager);
    }
}
