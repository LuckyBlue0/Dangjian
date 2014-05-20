package cn.com.do1.component.data.musicfile.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.data.musicfile.dao.*;
import cn.com.do1.component.data.musicfile.service.IMusicfileService;
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
@Service("musicfileService")
public class MusicfileServiceImpl extends BaseService implements IMusicfileService {
    private final static transient Logger logger = LoggerFactory.getLogger(MusicfileServiceImpl.class);

    private IMusicfileDAO musicfileDAO;
    @Resource
    public void setMusicfileDAO(IMusicfileDAO musicfileDAO) {
        this.musicfileDAO = musicfileDAO;
        setDAO(musicfileDAO);
    }

    public Pager searchMusicfile(Map searchMap,Pager pager) throws Exception, BaseException {
        return musicfileDAO.searchMusicfile(searchMap,pager);
    }
}
