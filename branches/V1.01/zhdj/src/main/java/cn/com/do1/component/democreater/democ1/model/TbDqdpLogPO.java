package cn.com.do1.component.democreater.democ1.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */
public class TbDqdpLogPO implements IBaseDBVO {
    private java.lang.String logId;
    @Validation(must = false, length = 19, fieldType = "datetime", regex = "")
    @PageView(showName = "createTime", showType = "datetime", showOrder = 1, showLength = 19)
    private java.util.Date createTime;
    @Validation(must = false, length = 36, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "operationId", showType = "input", showOrder = 2, showLength = 36)
    private java.lang.String operationId;
    @Validation(must = false, length = 150, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "operationName", showType = "input", showOrder = 3, showLength = 150)
    private java.lang.String operationName;
    @Validation(must = false, length = 100, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "modelName", showType = "input", showOrder = 4, showLength = 100)
    private java.lang.String modelName;
    @Validation(must = false, length = 36, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "operationType", showType = "input", showOrder = 5, showLength = 36)
    private java.lang.String operationType;
    @Validation(must = false, length = 20, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "operationResult", showType = "input", showOrder = 7, showLength = 20)
    private java.lang.String operationResult;
    @Validation(must = false, length = 1000, fieldType = "pattern", regex = "^\\\\w+$")
    @PageView(showName = "operationDesc", showType = "editor", showOrder = 6, showLength = 1000)
    private java.lang.String operationDesc;

    public void setLogId(java.lang.String logId) {
        this.logId = logId;
    }

    public java.lang.String getLogId() {
        return this.logId;
    }


    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = ConvertUtil.cvStUtildate(createTime);
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }


    public void setOperationId(java.lang.String operationId) {
        this.operationId = operationId;
    }

    public java.lang.String getOperationId() {
        return this.operationId;
    }


    public void setOperationName(java.lang.String operationName) {
        this.operationName = operationName;
    }

    public java.lang.String getOperationName() {
        return this.operationName;
    }


    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    public java.lang.String getModelName() {
        return this.modelName;
    }


    public void setOperationType(java.lang.String operationType) {
        this.operationType = operationType;
    }

    public java.lang.String getOperationType() {
        return this.operationType;
    }


    public void setOperationResult(java.lang.String operationResult) {
        this.operationResult = operationResult;
    }

    public java.lang.String getOperationResult() {
        return this.operationResult;
    }


    public void setOperationDesc(java.lang.String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public java.lang.String getOperationDesc() {
        return this.operationDesc;
    }


    /**
     * 获取数据库中对应的表名
     *
     * @return
     */
    public String _getTableName() {
        return "TB_DQDP_LOG";
    }

    /**
     * 获取对应表的主键字段名称
     *
     * @return
     */
    public String _getPKColumnName() {
        return "logId";
    }

    /**
     * 获取主键值
     *
     * @return
     */
    public String _getPKValue() {
        return String.valueOf(logId);
    }

    /**
     * 设置主键的值
     *
     * @return
     */
    public void _setPKValue(Object value) {
        this.logId = (java.lang.String) value;
    }
}
