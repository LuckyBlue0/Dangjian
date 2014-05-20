package cn.com.do1.component.data.musicfile.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.data.datafile.model.TbDataFilePO;
import cn.com.do1.component.data.musicfile.dao.IMusicfileDAO;
import cn.com.do1.component.data.musicfile.model.TbMusicFilePO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class MusicfileDAOImpl extends BaseDAOImpl implements IMusicfileDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(MusicfileDAOImpl.class);
    
    
    public Pager searchMusicfile(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_music_file where file_name like :fileName and type = :type order by push_time desc ";
    	String countSQL="select count(*) from tb_music_file where file_name like :fileName and type = :type";
    	return super.pageSearchByField(TbMusicFilePO.class, countSQL, searchSQL, searchMap, pager);
    }
}
