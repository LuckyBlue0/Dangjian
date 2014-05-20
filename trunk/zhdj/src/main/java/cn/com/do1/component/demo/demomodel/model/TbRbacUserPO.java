package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright ? 2010 广州市道一信息技术有限公司1
* All rights reserved.
* User: ${user}
*/

public class TbRbacUserPO implements IBaseDBVO {
        private java.lang.String id ;
        private java.lang.String account ;
        private java.lang.String password ;
        private java.math.BigDecimal userType ;
        private java.math.BigDecimal userFlag ;
        private java.lang.String userDesc ;
        private java.lang.String department ;
        private java.lang.String telephone ;
        private java.math.BigDecimal status ;
        private java.math.BigDecimal isModifyPasswordForce ;
        private java.util.Date lastSignin ;
        private java.util.Date lastModifyPasswordTime ;
        private java.lang.String merchantId ;
        private java.lang.String merchantDesc ;
        private java.util.Date createTime ;
        private java.math.BigDecimal isMerchantAccountLocked ;
        private java.util.Date deleteTime ;
        private java.lang.String email ;
        private java.lang.String remark ;
        private java.lang.String creatorId ;
        private java.lang.String ssoId ;
        private java.math.BigDecimal userFrom ;
        private java.lang.String departmentId ;

    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setAccount(java.lang.String account){
        this.account=account ;
    }
    public java.lang.String getAccount(){
        return this.account  ;
    }


    public void setPassword(java.lang.String password){
        this.password=password ;
    }
    public java.lang.String getPassword(){
        return this.password  ;
    }


    public void setUserType(java.math.BigDecimal userType){
        this.userType=userType ;
    }
    public void setUserType(java.lang.String userType){
       this.userType=ConvertUtil.cvStBD(userType) ;
   }
    public java.math.BigDecimal getUserType(){
        return this.userType  ;
    }


    public void setUserFlag(java.math.BigDecimal userFlag){
        this.userFlag=userFlag ;
    }
    public void setUserFlag(java.lang.String userFlag){
       this.userFlag=ConvertUtil.cvStBD(userFlag) ;
   }
    public java.math.BigDecimal getUserFlag(){
        return this.userFlag  ;
    }


    public void setUserDesc(java.lang.String userDesc){
        this.userDesc=userDesc ;
    }
    public java.lang.String getUserDesc(){
        return this.userDesc  ;
    }


    public void setDepartment(java.lang.String department){
        this.department=department ;
    }
    public java.lang.String getDepartment(){
        return this.department  ;
    }


    public void setTelephone(java.lang.String telephone){
        this.telephone=telephone ;
    }
    public java.lang.String getTelephone(){
        return this.telephone  ;
    }


    public void setStatus(java.math.BigDecimal status){
        this.status=status ;
    }
    public void setStatus(java.lang.String status){
       this.status=ConvertUtil.cvStBD(status) ;
   }
    public java.math.BigDecimal getStatus(){
        return this.status  ;
    }


    public void setIsModifyPasswordForce(java.math.BigDecimal isModifyPasswordForce){
        this.isModifyPasswordForce=isModifyPasswordForce ;
    }
    public void setIsModifyPasswordForce(java.lang.String isModifyPasswordForce){
       this.isModifyPasswordForce=ConvertUtil.cvStBD(isModifyPasswordForce) ;
   }
    public java.math.BigDecimal getIsModifyPasswordForce(){
        return this.isModifyPasswordForce  ;
    }


    public void setLastSignin(java.util.Date lastSignin){
        this.lastSignin=lastSignin ;
    }
    public void setLastSignin(java.lang.String lastSignin){
       this.lastSignin=ConvertUtil.cvStUtildate(lastSignin) ;
   }
    public java.util.Date getLastSignin(){
        return this.lastSignin  ;
    }


    public void setLastModifyPasswordTime(java.util.Date lastModifyPasswordTime){
        this.lastModifyPasswordTime=lastModifyPasswordTime ;
    }
    public void setLastModifyPasswordTime(java.lang.String lastModifyPasswordTime){
       this.lastModifyPasswordTime=ConvertUtil.cvStUtildate(lastModifyPasswordTime) ;
   }
    public java.util.Date getLastModifyPasswordTime(){
        return this.lastModifyPasswordTime  ;
    }


    public void setMerchantId(java.lang.String merchantId){
        this.merchantId=merchantId ;
    }
    public java.lang.String getMerchantId(){
        return this.merchantId  ;
    }


    public void setMerchantDesc(java.lang.String merchantDesc){
        this.merchantDesc=merchantDesc ;
    }
    public java.lang.String getMerchantDesc(){
        return this.merchantDesc  ;
    }


    public void setCreateTime(java.util.Date createTime){
        this.createTime=createTime ;
    }
    public void setCreateTime(java.lang.String createTime){
       this.createTime=ConvertUtil.cvStUtildate(createTime) ;
   }
    public java.util.Date getCreateTime(){
        return this.createTime  ;
    }


    public void setIsMerchantAccountLocked(java.math.BigDecimal isMerchantAccountLocked){
        this.isMerchantAccountLocked=isMerchantAccountLocked ;
    }
    public void setIsMerchantAccountLocked(java.lang.String isMerchantAccountLocked){
       this.isMerchantAccountLocked=ConvertUtil.cvStBD(isMerchantAccountLocked) ;
   }
    public java.math.BigDecimal getIsMerchantAccountLocked(){
        return this.isMerchantAccountLocked  ;
    }


    public void setDeleteTime(java.util.Date deleteTime){
        this.deleteTime=deleteTime ;
    }
    public void setDeleteTime(java.lang.String deleteTime){
       this.deleteTime=ConvertUtil.cvStUtildate(deleteTime) ;
   }
    public java.util.Date getDeleteTime(){
        return this.deleteTime  ;
    }


    public void setEmail(java.lang.String email){
        this.email=email ;
    }
    public java.lang.String getEmail(){
        return this.email  ;
    }


    public void setRemark(java.lang.String remark){
        this.remark=remark ;
    }
    public java.lang.String getRemark(){
        return this.remark  ;
    }


    public void setCreatorId(java.lang.String creatorId){
        this.creatorId=creatorId ;
    }
    public java.lang.String getCreatorId(){
        return this.creatorId  ;
    }


    public void setSsoId(java.lang.String ssoId){
        this.ssoId=ssoId ;
    }
    public java.lang.String getSsoId(){
        return this.ssoId  ;
    }


    public void setUserFrom(java.math.BigDecimal userFrom){
        this.userFrom=userFrom ;
    }
    public void setUserFrom(java.lang.String userFrom){
       this.userFrom=ConvertUtil.cvStBD(userFrom) ;
   }
    public java.math.BigDecimal getUserFrom(){
        return this.userFrom  ;
    }


    public void setDepartmentId(java.lang.String departmentId){
        this.departmentId=departmentId ;
    }
    public java.lang.String getDepartmentId(){
        return this.departmentId  ;
    }



    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_RBAC_USER";
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