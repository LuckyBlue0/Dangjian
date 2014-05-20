package cn.com.do1.component.demo.demomodel.service.impl;

import cn.com.do1.common.dac.DACException;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.demo.demomodel.model.TbTest2PO;
import cn.com.do1.component.demo.demomodel.model.YcQrcodeAssetsPO;
import cn.com.do1.component.demo.demomodel.service.IDemomodelService;
import cn.com.do1.component.systemmgr.role.model.TbUserRoleRefPO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-5-3
 * Time: 下午1:54
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:cn/com/do1/component/demo/resource/spring.xml"})
        //, "classpath:cn/com/do1/component/dqdpmemcache/resource/spring.xml"})
public class DemomodelServiceImplTest {
    @Autowired
    IDemomodelService demomodelService;

    @Test
    public void testCasAdd() throws Exception, DataConfictException {
        List<YcQrcodeAssetsPO> list = demomodelService.listAllYcQrcode();
        System.out.println(list.size());
        TbTest2PO po = new TbTest2PO();
        po.setPkId("id2");
        po.setName1("name1");
        po.setName2("name2");
        demomodelService.casAdd(po);
        po.setName2("name3");
        demomodelService.updatePO(po, true);
    }

    @Test
    public void testCasQuery() throws Exception, BaseException {
        Map map1 = new HashMap();
        map1.put("name2", "VY3xkH5fWHo=");
        List<TbTest2PO> poList = demomodelService.casQuery("name3");
        System.out.println(poList.size());
        Pager pager = demomodelService.searchDemomodel(map1, new Pager());
        System.out.println(pager.getPageData());
        TbTest2PO po = demomodelService.searchByPk(TbTest2PO.class, "id2");
        Assert.assertEquals(po.getName1(), "name1");
    }

    @Test
    public void testCasDel() throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbTest2PO po = new TbTest2PO();
        po.setPkId("id2");
        demomodelService.delPO(po);
    }

    @Test
    public void testAjaxLogin() throws Exception, SQLException {
        Map map1 = new HashMap();
                map1.put("name1", "VY3xkH5fWHo=");
        demomodelService.customSearchDemomodel2(map1);
        demomodelService.customSearchDemomodel2(map1);
    }

    @Test
    public void testBatchInsert() throws Exception, IllegalAccessException, DACException, DataConfictException {
        long t1 = System.currentTimeMillis();
        demomodelService.batchInsert();
        System.out.println(System.currentTimeMillis() - t1);
    }
    @Test
    public void testSqlCondition() throws Exception, IllegalAccessException, BaseException {
        Map map1 = new HashMap();
        map1.put("name2", "VY3xkH5fWHo=");
        demomodelService.customSearchDemomodel3(map1);
    }
    @Test
    public void testSqlCondition1() throws Exception, IllegalAccessException, BaseException {
        TbUserRoleRefPO po = new TbUserRoleRefPO();
        demomodelService.customSearchDemomodel4(po);
    }
    @Test
    public void testLot() throws Exception, IllegalAccessException, BaseException {
        long l = System.currentTimeMillis();
        Pager pager = demomodelService.testLot();
        System.out.println(pager.getTotalRows());
        System.out.println(System.currentTimeMillis()-l);
    }
    @Test
    public void testTbTest() throws Exception, IllegalAccessException, BaseException {
        demomodelService.testTbTest();
    }
    @Test
    public void testSqlserverNumb() throws Exception, IllegalAccessException, BaseException {
        demomodelService.testSqlserverNumb();
    }
}
