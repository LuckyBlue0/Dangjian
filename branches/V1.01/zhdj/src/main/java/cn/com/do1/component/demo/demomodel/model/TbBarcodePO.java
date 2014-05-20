package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import java.util.Date;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public class TbBarcodePO implements IBaseDBVO {
    private java.lang.String id ;
    private java.lang.String orderNumber ;
    private java.lang.String mobile ;
    private java.lang.String merchantCode ;
    private java.lang.String pospMerchantCode ;
    private java.lang.String sendType ;
    private java.lang.String notesSms ;
    private java.lang.String notesMms ;
    private java.lang.String infoTitle ;
    private java.math.BigDecimal amount ;
    private java.lang.String batchNo ;
    private java.util.Date validateStart ;
    private java.util.Date validateEnd ;
    private java.lang.String printTitle ;
    private java.lang.String printText ;
    private java.util.Date createTime ;
    private java.math.BigDecimal submitStatus ;
    private java.lang.String retId ;
    private java.lang.String respCode ;
    private java.lang.String respDesc ;
    private java.util.Date respTime ;
    private java.lang.String additionNumber ;
    private java.lang.String deliverStatusCode ;
    private java.lang.String deliverStatusDesc ;
    private java.math.BigDecimal verifyStatus ;
    private java.lang.String seqNum ;
    private java.math.BigDecimal barcodeSendType ;
    
    public void setId(java.lang.String id){
    this.id=id ;
    }
    public java.lang.String getId(){
    return this.id  ;
    }


    public void setOrderNumber(java.lang.String orderNumber){
    this.orderNumber=orderNumber ;
    }
    public java.lang.String getOrderNumber(){
    return this.orderNumber  ;
    }


    public void setMobile(java.lang.String mobile){
    this.mobile=mobile ;
    }
    public java.lang.String getMobile(){
    return this.mobile  ;
    }


    public void setMerchantCode(java.lang.String merchantCode){
    this.merchantCode=merchantCode ;
    }
    public java.lang.String getMerchantCode(){
    return this.merchantCode  ;
    }


    public void setPospMerchantCode(java.lang.String pospMerchantCode){
    this.pospMerchantCode=pospMerchantCode ;
    }
    public java.lang.String getPospMerchantCode(){
    return this.pospMerchantCode  ;
    }


    public void setSendType(java.lang.String sendType){
    this.sendType=sendType ;
    }
    public java.lang.String getSendType(){
    return this.sendType  ;
    }


    public void setNotesSms(java.lang.String notesSms){
    this.notesSms=notesSms ;
    }
    public java.lang.String getNotesSms(){
    return this.notesSms  ;
    }


    public void setNotesMms(java.lang.String notesMms){
    this.notesMms=notesMms ;
    }
    public java.lang.String getNotesMms(){
    return this.notesMms  ;
    }


    public void setInfoTitle(java.lang.String infoTitle){
    this.infoTitle=infoTitle ;
    }
    public java.lang.String getInfoTitle(){
    return this.infoTitle  ;
    }


    public void setAmount(java.math.BigDecimal amount){
    this.amount=amount ;
    }
    public java.math.BigDecimal getAmount(){
    return this.amount  ;
    }


    public void setBatchNo(java.lang.String batchNo){
    this.batchNo=batchNo ;
    }
    public java.lang.String getBatchNo(){
    return this.batchNo  ;
    }


    public void setValidateStart(java.util.Date validateStart){
    this.validateStart=validateStart ;
    }
    public java.util.Date getValidateStart(){
    return this.validateStart  ;
    }


    public void setValidateEnd(java.util.Date validateEnd){
    this.validateEnd=validateEnd ;
    }
    public java.util.Date getValidateEnd(){
    return this.validateEnd  ;
    }


    public void setPrintTitle(java.lang.String printTitle){
    this.printTitle=printTitle ;
    }
    public java.lang.String getPrintTitle(){
    return this.printTitle  ;
    }


    public void setPrintText(java.lang.String printText){
    this.printText=printText ;
    }
    public java.lang.String getPrintText(){
    return this.printText  ;
    }


    public void setCreateTime(java.util.Date createTime){
    this.createTime=createTime ;
    }
    public java.util.Date getCreateTime(){
    return this.createTime  ;
    }


    public void setSubmitStatus(java.math.BigDecimal submitStatus){
    this.submitStatus=submitStatus ;
    }
    public java.math.BigDecimal getSubmitStatus(){
    return this.submitStatus  ;
    }


    public void setRetId(java.lang.String retId){
    this.retId=retId ;
    }
    public java.lang.String getRetId(){
    return this.retId  ;
    }


    public void setRespCode(java.lang.String respCode){
    this.respCode=respCode ;
    }
    public java.lang.String getRespCode(){
    return this.respCode  ;
    }


    public void setRespDesc(java.lang.String respDesc){
    this.respDesc=respDesc ;
    }
    public java.lang.String getRespDesc(){
    return this.respDesc  ;
    }


    public void setRespTime(java.util.Date respTime){
    this.respTime=respTime ;
    }
    public java.util.Date getRespTime(){
    return this.respTime  ;
    }


    public void setAdditionNumber(java.lang.String additionNumber){
    this.additionNumber=additionNumber ;
    }
    public java.lang.String getAdditionNumber(){
    return this.additionNumber  ;
    }


    public void setDeliverStatusCode(java.lang.String deliverStatusCode){
    this.deliverStatusCode=deliverStatusCode ;
    }
    public java.lang.String getDeliverStatusCode(){
    return this.deliverStatusCode  ;
    }


    public void setDeliverStatusDesc(java.lang.String deliverStatusDesc){
    this.deliverStatusDesc=deliverStatusDesc ;
    }
    public java.lang.String getDeliverStatusDesc(){
    return this.deliverStatusDesc  ;
    }


    public void setVerifyStatus(java.math.BigDecimal verifyStatus){
    this.verifyStatus=verifyStatus ;
    }
    public java.math.BigDecimal getVerifyStatus(){
    return this.verifyStatus  ;
    }


    public void setSeqNum(java.lang.String seqNum){
    this.seqNum=seqNum ;
    }
    public java.lang.String getSeqNum(){
    return this.seqNum  ;
    }


    public void setBarcodeSendType(java.math.BigDecimal barcodeSendType){
    this.barcodeSendType=barcodeSendType ;
    }
    public java.math.BigDecimal getBarcodeSendType(){
    return this.barcodeSendType  ;
    }



    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_BARCODE";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "id";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(id);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.id=(java.lang.String)value;
    }
}
