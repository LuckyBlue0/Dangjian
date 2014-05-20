package cn.com.do1.component.demo.demomodel.service;

import cn.com.do1.common.dac.DACException;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.demo.demomodel.model.TbTest2PO;
import cn.com.do1.component.demo.demomodel.model.YcQrcodeAssetsPO;

import java.util.List;
import java.util.Map;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public interface IDemomodelService extends IBaseService{

    Pager searchDemomodel(Map searchMap,Pager pager) throws Exception, BaseException;

//    void delPO(TbRbacUserPO userPO) throws Exception;

    Pager customSearchDemomodel(Map searchValue, Pager pager) throws Exception, BaseException;

    List customSearchDemomodel2(Map searchValue) throws Exception;
    List<YcQrcodeAssetsPO> listAllYcQrcode()throws Exception;

    void casAdd(TbTest2PO po) throws Exception, DataConfictException;
    List casQuery(String id) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

    void batchInsert() throws Exception, DACException, IllegalAccessException, DataConfictException;

    void customSearchDemomodel3(Map map) throws BaseException;

    void customSearchDemomodel4(IBaseDBVO po) throws BaseException;

    Pager testLot() throws Exception, BaseException;

    void testTbTest() throws DataConfictException, Exception;

    void testSqlserverNumb() throws DataConfictException, Exception;

}
