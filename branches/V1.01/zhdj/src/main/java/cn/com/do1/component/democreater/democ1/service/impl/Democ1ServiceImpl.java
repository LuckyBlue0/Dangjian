package cn.com.do1.component.democreater.democ1.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.democreater.democ1.dao.*;
import cn.com.do1.component.democreater.democ1.service.IDemoc1Service;
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
@Service("democ1Service")
public class Democ1ServiceImpl extends BaseService implements IDemoc1Service {
    private final static transient Logger logger = LoggerFactory.getLogger(Democ1ServiceImpl.class);

    private IDemoc1DAO democ1DAO;
    @Resource
    public void setDemoc1DAO(IDemoc1DAO democ1DAO) {
        this.democ1DAO = democ1DAO;
        setDAO(democ1DAO);
    }

    public Pager searchDemoc1(Map searchMap,Pager pager) throws Exception, BaseException {
        return democ1DAO.pageSearchByField( IBaseDBVO.class,searchMap,null,pager);
    }
}
