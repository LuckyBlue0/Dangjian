package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public class TbDqdpConfigPO implements IBaseDBVO {
    private java.lang.String configId ;
    private java.lang.String componentName ;
    private java.lang.String modelCode ;
    private java.lang.String configName ;
    private java.lang.String configValue ;
    private Date createTime;
    private BigDecimal count;
    private Double amont;
    private TestVO testVO;


    public TestVO getTestVO() {
        return testVO;
    }

    public void setTestVO(TestVO testVO) {
        this.testVO = testVO;
    }

    public void setConfigId(java.lang.String configId){
    this.configId=configId ;
    }
    public java.lang.String getConfigId(){
    return this.configId  ;
    }


    public void setComponentName(java.lang.String componentName){
    this.componentName=componentName ;
    }
    public java.lang.String getComponentName(){
    return this.componentName  ;
    }


    public void setModelCode(java.lang.String modelCode){
    this.modelCode=modelCode ;
    }
    public java.lang.String getModelCode(){
    return this.modelCode  ;
    }


    public void setConfigName(java.lang.String configName){
    this.configName=configName ;
    }
    public java.lang.String getConfigName(){
    return this.configName  ;
    }


    public void setConfigValue(java.lang.String configValue){
    this.configValue=configValue ;
    }
    public java.lang.String getConfigValue(){
    return this.configValue  ;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Double getAmont() {
        return amont;
    }

    public void setAmont(Double amont) {
        this.amont = amont;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_DQDP_CONFIG";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "configId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(configId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.configId=(java.lang.String)value;
    }

    public static void main(String[] args) throws BaseException, InvocationTargetException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        TbDqdpConfigPO po = new TbDqdpConfigPO();
        po.setCount(new BigDecimal(0));
        TestVO vo = new TestVO();
        vo.setCount("0");
        vo.setConfigName("abc");
        vo.setCreateTime("2002-10-11");
//         BeanHelper.copyFormatProperties(po,vo,true);
        System.out.println(vo.getCount());
    }
}
