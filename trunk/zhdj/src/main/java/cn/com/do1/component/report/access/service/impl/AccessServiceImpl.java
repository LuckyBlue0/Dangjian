package cn.com.do1.component.report.access.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.report.access.dao.IAccessDAO;
import cn.com.do1.component.report.access.model.AccessDataVO;
import cn.com.do1.component.report.access.model.PvReportVO;
import cn.com.do1.component.report.access.model.TbAccessInfoVO;
import cn.com.do1.component.report.access.service.IAccessService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("accessService")
public class AccessServiceImpl extends BaseService implements IAccessService {
    private final static transient Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);

    private IAccessDAO accessDAO;
    @Resource
    public void setAccessDAO(IAccessDAO accessDAO) {
        this.accessDAO = accessDAO;
        setDAO(accessDAO);
    }

    public Pager searchAccess(Map searchMap,Pager pager) throws Exception, BaseException {
        return accessDAO.searchAccess(searchMap,pager);
    }

    public AccessDataVO searchAccessData()throws Exception, BaseException{
    	return accessDAO.searchAccessData();
    }

    public List<PvReportVO> searchPvDataByDate(Date date)throws Exception, BaseException{
    	return accessDAO.searchPvDataByDate(date);
    }
    public List<PvReportVO> searchPlDataByDate(Date date)throws Exception, BaseException{
    	return accessDAO.searchPlDataByDate(date);
    }

    public TbAccessInfoVO searchAccessByUsername(String username,Date date)throws Exception, BaseException{
    	return accessDAO.searchAccessByUsername(username,date );
    }
}
