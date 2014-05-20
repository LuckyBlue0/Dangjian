package cn.com.do1.component.data.videofile.dao.impl;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.data.videofile.dao.IDatainfoDAO;
import cn.com.do1.component.data.videofile.model.TbVideoPO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class DatainfoDAOImpl extends BaseDAOImpl implements IDatainfoDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(DatainfoDAOImpl.class);
    
    
    public Pager searchDatainfo(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_video_file where file_name like :fileName and type = :type order by push_time desc ";
    	String countSQL="select count(*) from tb_video_file where file_name like :fileName and type = :type";
    	return super.pageSearchByField(TbVideoPO.class, countSQL, searchSQL, searchMap, pager);
    }
}
