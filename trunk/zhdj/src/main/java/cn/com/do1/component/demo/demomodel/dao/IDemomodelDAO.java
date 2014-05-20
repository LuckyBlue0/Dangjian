package cn.com.do1.component.demo.demomodel.dao;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.util.List;
import java.util.Map;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public interface IDemomodelDAO extends IBaseDAO {

    Pager customSearchDemomodel(Map searchValue, Pager pager) throws Exception, BaseException;

    List customSearchDemomodel2(Map searchValue) throws Exception;

    void customSearchDemomodel3(Map map) throws BaseException;

    void customSearchDemomodel4(IBaseDBVO po) throws BaseException;

    Pager testLot() throws Exception, BaseException;
}