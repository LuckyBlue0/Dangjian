package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseConverter;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-2-22
 * Time: 下午6:29
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class ConfigConvert extends BaseConverter<IBaseDBVO> {

    public ConfigConvert() throws IllegalAccessException, InstantiationException {
        super(TbDqdpConfigPO.class);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, BaseException {
        TbDqdpConfigPO po = new TbDqdpConfigPO();
        po.setAmont(11.2);
        po.setComponentName("abc");
        po.setCreateTime(new Date());
        po.setCount(new BigDecimal(3));
        po.setTestVO( new TestVO());
        po.getTestVO().setComponentName("xxx");
        TestVO vo = new TestVO();
//        BeanHelper.copyFormatProperties(vo,po,true);
        System.out.println(vo.getAmont());
        System.out.println(vo.getTestVO().getComponentName());
    }
}
