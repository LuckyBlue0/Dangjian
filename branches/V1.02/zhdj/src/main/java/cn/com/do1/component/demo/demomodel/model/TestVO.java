package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-2-23
 * Time: 上午11:50
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TestVO {
    private java.lang.String configId ;
    private java.lang.String componentName ;
    private java.lang.String modelCode ;
    private java.lang.String configName ;
    private java.lang.String configValue ;
    @FormatMask("yyyy-MM-dd")
    private String createTime;
    @FormatMask("#00.0")
    private String count;
    @FormatMask("#00.0%")
    @PageView(showType = "input",showOrder = 1,showName = "测试")
    @Validation(must = true,length = 50,fieldType = "pattern",regex = "xxx")
    private String amont;
    @DictDesc(typeName = "ii",refField = "componentName1")
    private String sexDesc;
    private TestVO testVO;


    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAmont() {
        return amont;
    }

    public void setAmont(String amont) {
        this.amont = amont;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public TestVO getTestVO() {
        return testVO;
    }

    public void setTestVO(TestVO testVO) {
        this.testVO = testVO;
    }
}
