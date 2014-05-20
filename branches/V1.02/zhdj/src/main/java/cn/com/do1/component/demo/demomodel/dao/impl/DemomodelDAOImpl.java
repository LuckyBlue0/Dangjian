package cn.com.do1.component.demo.demomodel.dao.impl;

import cn.com.do1.common.annotation.dao.PageQuery;
import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.demo.demomodel.dao.IDemomodelDAO;
import cn.com.do1.component.demo.demomodel.model.TbTest1PO;
import cn.com.do1.component.demo.demomodel.model.TbTestPO;
import cn.com.do1.component.dqdploger.log.model.TbDqdpLogPO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright ? 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */

public class DemomodelDAOImpl extends BaseDAOImpl implements IDemomodelDAO {
    final static String condSQL =
            "         from" +
                    "       tb_test test," +
                    "       tb_user user," +
                    "       tb_user_permission permission" +
                    " where " +
                    "       user.user_id=permission.user_id and test.user_id=user.user_id" +
                    "       and test_date=:testDate and test_number=:testNumber and testString=:testString and testa in (:testa)";
    final static String countSQL = "select count(1)  " + condSQL;
    final static String searchSQL = "select test.*  " + condSQL;

    @PageQuery(countSQL=countSQL,searchSQL=searchSQL)
    public Pager customSearchDemomodel(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
        return super.pageSearchByField(TbTestPO.class, countSQL, searchSQL, searchValue, pager);
    }

    public List customSearchDemomodel2(Map searchValue) throws Exception {
//        preparedSql(searchSQL);
//        setPreValues(searchValue);//将参数设置进预置语句
        return searchByField(TbTest1PO.class,"select * from tb_test1 where name1 = :name1",searchValue);
    }

    @Override
    public void customSearchDemomodel3(Map map) throws BaseException {
        System.out.println(getSqlByID("SQL_TEST",map));
    }

    @Override
    public void customSearchDemomodel4(IBaseDBVO po) throws BaseException {
        System.out.println(getSqlByID("SQL_TEST1",po));
    }

    @Override
    public Pager testLot() throws Exception, BaseException {
        return super.pageSearchByField(TbDqdpLogPO.class,new HashMap<String, Object>(),null,new Pager(100000));
    }
}
