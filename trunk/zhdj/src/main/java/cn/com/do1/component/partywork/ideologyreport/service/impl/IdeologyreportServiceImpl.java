package cn.com.do1.component.partywork.ideologyreport.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.partywork.ideologyreport.dao.IIdeologyreportDAO;
import cn.com.do1.component.partywork.ideologyreport.service.IIdeologyreportService;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;

/**
* All rights reserved.
* User: ${user}
*/
@Service("ideologyreportService")
public class IdeologyreportServiceImpl extends BaseService implements IIdeologyreportService {
    private final static transient Logger logger = LoggerFactory.getLogger(IdeologyreportServiceImpl.class);

    private IIdeologyreportDAO ideologyreportDAO;
    @Resource
    public void setIdeologyreportDAO(IIdeologyreportDAO ideologyreportDAO) {
        this.ideologyreportDAO = ideologyreportDAO;
        setDAO(ideologyreportDAO);
    }

    public Pager searchIdeologyreport(Map searchMap,Pager pager) throws Exception, BaseException {
        return ideologyreportDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public IdeologyReportVO searchById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return ideologyreportDAO.searchById(id);
	}
}
