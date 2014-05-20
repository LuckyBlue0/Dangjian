package cn.com.do1.component.mobileclient.installrecord.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.mobileclient.installrecord.dao.*;
import cn.com.do1.component.mobileclient.installrecord.service.IInstallRecordService;
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
@Service("installRecordService")
public class InstallRecordServiceImpl extends BaseService implements IInstallRecordService {
    private final static transient Logger logger = LoggerFactory.getLogger(InstallRecordServiceImpl.class);

    private IInstallRecordDAO installRecordDAO;
    @Resource
    public void setInstallRecordDAO(IInstallRecordDAO installRecordDAO) {
        this.installRecordDAO = installRecordDAO;
        setDAO(installRecordDAO);
    }

    public Pager searchInstallRecord(Map searchMap,Pager pager) throws Exception, BaseException {
        return installRecordDAO.searchInstallRecord(searchMap,pager);
    }
}
