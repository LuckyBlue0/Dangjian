package cn.com.do1.component.demoremote.code.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.demo.demomodel.model.TbTest2PO;
import cn.com.do1.component.demoremote.code.dao.ICodeDAO;
import cn.com.do1.component.demoremote.code.service.ICodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

@Service("codeService")

public class CodeServiceImpl extends BaseService implements ICodeService {

    ICodeDAO codeDAO;
    @Resource
    public void setCodeDAO(ICodeDAO codeDAO) {
        this.codeDAO = codeDAO;
        setDAO(codeDAO);
    }

    public Pager searchCode(Map searchMap,Pager pager) throws Exception, BaseException {
        return codeDAO.pageSearchByField( TbTest2PO.class,searchMap,null,pager);
    }
}
