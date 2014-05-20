package cn.com.do1.component.data.datafile.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.data.datafile.dao.IDatafileDAO;
import cn.com.do1.component.data.datafile.model.TbDataFilePO;
import cn.com.do1.component.data.partyfile.model.TbPartyFilePO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class DatafileDAOImpl extends BaseDAOImpl implements IDatafileDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(DatafileDAOImpl.class);
     
    public Pager searchDatafile(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_data_file where file_name like :fileName and type = :type order by push_time desc ";
    	String countSQL="select count(*) from tb_data_file where file_name like :fileName and type = :type";
    	return super.pageSearchByField(TbDataFilePO.class, countSQL, searchSQL, searchMap, pager);
    }
}
