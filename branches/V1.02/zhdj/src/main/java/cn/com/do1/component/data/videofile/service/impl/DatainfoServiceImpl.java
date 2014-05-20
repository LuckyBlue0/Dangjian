package cn.com.do1.component.data.videofile.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.data.videofile.dao.IDatainfoDAO;
import cn.com.do1.component.data.videofile.service.IDatainfoService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("datainfoService")
public class DatainfoServiceImpl extends BaseService implements IDatainfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(DatainfoServiceImpl.class);

    private IDatainfoDAO datainfoDAO;
    @Resource
    public void setDatainfoDAO(IDatainfoDAO datainfoDAO) {
        this.datainfoDAO = datainfoDAO;
        setDAO(datainfoDAO);
    }

    public Pager searchDatainfo(Map searchMap,Pager pager) throws Exception, BaseException {
        return datainfoDAO.searchDatainfo(searchMap,pager);
    }
}
