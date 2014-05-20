package cn.com.do1.component.data.partyfile.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.data.partyfile.dao.*;
import cn.com.do1.component.data.partyfile.service.IPartyfileService;
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
@Service("partyfileService")
public class PartyfileServiceImpl extends BaseService implements IPartyfileService {
    private final static transient Logger logger = LoggerFactory.getLogger(PartyfileServiceImpl.class);

    private IPartyfileDAO partyfileDAO;
    @Resource
    public void setPartyfileDAO(IPartyfileDAO partyfileDAO) {
        this.partyfileDAO = partyfileDAO;
        setDAO(partyfileDAO);
    }

    public Pager searchPartyfile(Map searchMap,Pager pager) throws Exception, BaseException {
        return partyfileDAO.searchPartyfile(searchMap,pager);
    }
}
