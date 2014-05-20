package cn.com.do1.component.data.datafile.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.data.datafile.dao.*;
import cn.com.do1.component.data.datafile.service.IDatafileService;
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
@Service("datafileService")
public class DatafileServiceImpl extends BaseService implements IDatafileService {
    private final static transient Logger logger = LoggerFactory.getLogger(DatafileServiceImpl.class);

    private IDatafileDAO datafileDAO;
    @Resource
    public void setDatafileDAO(IDatafileDAO datafileDAO) {
        this.datafileDAO = datafileDAO;
        setDAO(datafileDAO);
    }

    public Pager searchDatafile(Map searchMap,Pager pager) throws Exception, BaseException {
        return datafileDAO.searchDatafile(searchMap,pager);
    }
}
