package cn.com.do1.component.mobileclient.installrecord.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.installrecord.dao.IInstallRecordDAO;
import cn.com.do1.component.mobileclient.installrecord.model.TbInstallRecordPO;
import cn.com.do1.component.mobileclient.version.model.TbVersionPO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class InstallRecordDAOImpl extends BaseDAOImpl implements IInstallRecordDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(InstallRecordDAOImpl.class);
    public Pager searchInstallRecord(Map searchMap,Pager pager) throws Exception, BaseException {
    	String searchSQL="select * from tb_install_record where  install_time >= :startTime and install_time <= :endTime order by install_time desc ";
    	String countSQL="select count(*) from tb_install_record where  install_time >= :startTime and install_time <= :endTime";
    	return super.pageSearchByField(TbInstallRecordPO.class, countSQL, searchSQL, searchMap, pager);
    }
}  
