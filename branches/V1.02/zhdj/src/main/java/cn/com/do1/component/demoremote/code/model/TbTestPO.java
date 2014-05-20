package cn.com.do1.component.demoremote.code.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import java.util.Date;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public class TbTestPO implements IBaseDBVO {
        private java.lang.String testId ;
        private java.lang.String testName ;
        private java.util.Date testTime ;
        private java.math.BigDecimal testCount ;
        private java.math.BigDecimal testCount1 ;
    
    public void setTestId(java.lang.String testId){
        this.testId=testId ;
    }
    public java.lang.String getTestId(){
        return this.testId  ;
    }


    public void setTestName(java.lang.String testName){
        this.testName=testName ;
    }
    public java.lang.String getTestName(){
        return this.testName  ;
    }


    public void setTestTime(java.util.Date testTime){
        this.testTime=testTime ;
    }
    public void setTestTime(java.lang.String testTime){
       this.testTime=ConvertUtil.cvStUtildate(testTime) ;
   }
    public java.util.Date getTestTime(){
        return this.testTime  ;
    }


    public void setTestCount(java.math.BigDecimal testCount){
        this.testCount=testCount ;
    }
    public void setTestCount(java.lang.String testCount){
       this.testCount=ConvertUtil.cvStBD(testCount) ;
   }
    public java.math.BigDecimal getTestCount(){
        return this.testCount  ;
    }


    public void setTestCount1(java.math.BigDecimal testCount1){
        this.testCount1=testCount1 ;
    }
    public void setTestCount1(java.lang.String testCount1){
       this.testCount1=ConvertUtil.cvStBD(testCount1) ;
   }
    public java.math.BigDecimal getTestCount1(){
        return this.testCount1  ;
    }



    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_TEST";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "testId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(testId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.testId=(java.lang.String)value;
    }
}
