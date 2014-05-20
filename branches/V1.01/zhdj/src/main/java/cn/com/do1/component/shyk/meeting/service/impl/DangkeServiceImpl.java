package cn.com.do1.component.shyk.meeting.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.shyk.meeting.dao.IDangkeDAO;
import cn.com.do1.component.shyk.meeting.service.IDangkeService;
import cn.com.do1.component.shyk.meeting.vo.DangkeVO;

/**
* All rights reserved.
* User: ${user}
*/
@Service("dangkeService")
public class DangkeServiceImpl extends BaseService implements IDangkeService {
    private final static transient Logger logger = LoggerFactory.getLogger(DangkeServiceImpl.class);

    private IDangkeDAO dangkeDAO;
    @Resource
    public void setDangkeDAO(IDangkeDAO dangkeDAO) {
        this.dangkeDAO = dangkeDAO;
        setDAO(dangkeDAO);
    }

    public Pager searchDangke(Map searchMap,Pager pager) throws Exception, BaseException {
        return dangkeDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public DangkeVO getDangkeById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return dangkeDAO.getDangkeById(id);
	}
}
