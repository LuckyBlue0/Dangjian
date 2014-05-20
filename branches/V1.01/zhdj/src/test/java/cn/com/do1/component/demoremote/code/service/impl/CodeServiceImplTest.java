package cn.com.do1.component.demoremote.code.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.demoremote.code.service.ICodeService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-5-3
 * Time: 下午6:31
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:cn/com/do1/component/demoremote/resource/spring-client.xml"})
public class CodeServiceImplTest {
    @Autowired
    ICodeService codeService;
    @Test
    public void testSearchCode() throws Exception, BaseException {
        Pager pager = new Pager(20);
        try {
            pager = codeService.searchCode(new HashMap(),pager );
        } catch (Exception e) {
            e.printStackTrace();
        } catch (BaseException e) {
            e.printStackTrace();
        }
        try {
            pager = codeService.searchCode(new HashMap(),pager );
        } catch (Exception e) {
            e.printStackTrace();
        } catch (BaseException e) {
            e.printStackTrace();
        }
        Thread.currentThread().sleep(30000);
    }
}
