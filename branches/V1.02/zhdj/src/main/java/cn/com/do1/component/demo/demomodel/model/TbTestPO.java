package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.util.Date;

/**
 * Copyright ? 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */

public class TbTestPO implements IBaseDBVO {
    private java.lang.String testId;
    private java.lang.String testName;
    private java.util.Date testTime;
    private Float testFloat;
    private Double testDouble;
    private Integer testInt;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Float getTestFloat() {
        return testFloat;
    }

    public void setTestFloat(Float testFloat) {
        this.testFloat = testFloat;
    }

    public Double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
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
        this.testId = (java.lang.String) value;
    }

}
