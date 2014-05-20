package cn.com.do1.component.demo.demomodel.service.impl;

import cn.com.do1.common.dac.DACException;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.demo.demomodel.dao.IDemomodelDAO;
import cn.com.do1.component.demo.demomodel.model.*;
import cn.com.do1.component.demo.demomodel.service.IDemomodelService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Copyright ? 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */

@Service("demomodelService")

public class DemomodelServiceImpl extends BaseService implements IDemomodelService {

    IDemomodelDAO demomodelDAO;

    @Resource
    public void setDemomodelDAO(IDemomodelDAO demomodelDAO) {
        this.demomodelDAO = demomodelDAO;
        setDAO(demomodelDAO);
    }

    /**
     * 分页查询 TbRbacUserPO所映射的表，调用框架的 pageSearchByField方法。
     *
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public Pager searchDemomodel(Map searchMap, Pager pager) throws Exception, BaseException {
        /**
         *  TbRbacUserPO.class指明想查询的单表
         *  searchMap 查询提供的参数名及值的MAP表，有多个字段时，字段间为AND关系，查询条件一律为=
         *  null 需要客外附加的语句，通常用于排序，如ORDER BY XXX DESC
         *  pager 分页信息
         */
//        return demomodelDAO.pageSearchByField(TbTest2PO.class, searchMap, null, pager);
        return demomodelDAO.pageSearchByField(TbTest2PO.class, "select count(1) from tb_test2", "select * from tb_test2", null, pager);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbRbacUserPO userPO) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        demomodelDAO.delete(userPO);
    }

    @CacheEvict("young")
    public Pager customSearchDemomodel(Map searchValue, Pager pager) throws Exception, BaseException {
        return demomodelDAO.customSearchDemomodel(searchValue, pager);
    }

    @Cacheable(value = "default", key = "'demo'+#searchValue['name1']")
    public List customSearchDemomodel2(Map searchValue) throws Exception {
//        return new ArrayList();
        return demomodelDAO.customSearchDemomodel2(searchValue);
    }

    @Override
    public List<YcQrcodeAssetsPO> listAllYcQrcode() throws Exception {
        return demomodelDAO.getList("select * from YC_QRCODE_ASSETS",YcQrcodeAssetsPO.class);
    }

    public void casAdd(TbTest2PO po) throws Exception, DataConfictException {
        demomodelDAO.insert(po);
    }

    public List casQuery(String id) throws Exception, EncryptionUtils.EncryptionException {
        return demomodelDAO.searchByField(TbTest2PO.class, new String[]{"name2"}, new Object[]{cn.com.do1.common.util.security.EncryptionUtils.encrypt("123456789012345678901234", id)}, "");
    }

    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbTest2PO po = new TbTest2PO();
        po.setPkId(id);
        demomodelDAO.delete(po);
    }

    @Override
    public void batchInsert() throws Exception, DACException, IllegalAccessException, DataConfictException {
        List<IBaseDBVO> poList = new ArrayList<IBaseDBVO>();
        for (int i = 0; i < 10000; i++) {
//            demomodelDAO.addBatch("insert into TB_TEST1(PK_ID,NAME1) values('pk_1_" + i+"','name_1_"+i+"')");
            TbTest1PO po = new TbTest1PO();
            po.setPkId("pk_1_" + i);
            po.setName1("name_1_" + i);
            po.setDate(null);
//            demomodelDAO.insert(po);
            poList.add(po);
        }
//        demomodelDAO.execBatch();
        demomodelDAO.execBatchInsert(poList);
    }

    @Override
    public void customSearchDemomodel3(Map map) throws BaseException {
        demomodelDAO.customSearchDemomodel3(map);
    }

    @Override
    public void customSearchDemomodel4(IBaseDBVO po) throws BaseException {
        demomodelDAO.customSearchDemomodel4(po);
    }

    @Override
    public Pager testLot() throws Exception, BaseException {
        return demomodelDAO.testLot();
    }

    @Override
    public void testTbTest() throws DataConfictException, Exception {
        TbTestPO testPO = new TbTestPO();
        testPO.setTestId(UUID.randomUUID().toString());
        testPO.setTestName("aaaaa");
        testPO.setTestDouble(new Double("0.01"));
        testPO.setTestFloat(new Float("0.01"));
        testPO.setTestInt(new Integer("10"));
        testPO.setTestTime(new Date());
        super.insertPO(testPO,false);
    }

    @Override
    public void testSqlserverNumb() throws DataConfictException, Exception {
        TbStationUsers user = new TbStationUsers();
        user.setId(UUID.randomUUID().toString());
        user.setStationId("t1");
        user.setRate(new Double("0.01"));
        super.insertPO(user,false);
    }


}
