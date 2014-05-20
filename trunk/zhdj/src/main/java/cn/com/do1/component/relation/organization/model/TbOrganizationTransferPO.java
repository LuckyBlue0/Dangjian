package cn.com.do1.component.relation.organization.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbOrganizationTransferPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
        private java.lang.String userId ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationFrom",showType="input",showOrder=2,showLength=36)
        private java.lang.String organizationFrom ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=3,showLength=7)
        private java.util.Date createTime ;
            @Validation(must=false,length=2000,fieldType="pattern",regex="^.*$")
    @PageView(showName="leaveDesc",showType="input",showOrder=4,showLength=2000)
        private java.lang.String leaveDesc ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationTo",showType="input",showOrder=5,showLength=200)
        private java.lang.String organizationTo ;
            @Validation(must=false,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=6,showLength=2)
        private java.lang.String status ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="intoTime",showType="datetime",showOrder=7,showLength=7)
        private java.util.Date intoTime ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationInto",showType="input",showOrder=8,showLength=200)
        private java.lang.String organizationInto ;
            @Validation(must=false,length=2000,fieldType="pattern",regex="^.*$")
    @PageView(showName="intoDesc",showType="input",showOrder=9,showLength=2000)
        private java.lang.String intoDesc ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="auditUserInfo",showType="input",showOrder=10,showLength=36)
        private java.lang.String auditUserInfo ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="auditTime",showType="datetime",showOrder=11,showLength=7)
        private java.util.Date auditTime ;
            @Validation(must=false,length=1000,fieldType="pattern",regex="^.*$")
    @PageView(showName="auditDesc",showType="input",showOrder=12,showLength=1000)
        private java.lang.String auditDesc ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=13,showLength=22)
        private java.lang.Long type ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="userName",showType="input",showOrder=14,showLength=50)
        private java.lang.String userName ;
            @Validation(must=false,length=15,fieldType="pattern",regex="^.*$")
    @PageView(showName="mobile",showType="input",showOrder=15,showLength=15)
        private java.lang.String mobile ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="idCard",showType="input",showOrder=16,showLength=20)
        private java.lang.String idCard ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="politicalLandscape",showType="input",showOrder=17,showLength=36)
        private java.lang.String politicalLandscape ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="national",showType="input",showOrder=18,showLength=20)
        private java.lang.String national ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="age",showType="input",showOrder=19,showLength=22)
        private java.lang.Long age ;
            @Validation(must=false,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="sex",showType="input",showOrder=20,showLength=2)
        private java.lang.String sex ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationId",showType="input",showOrder=21,showLength=36)
        private java.lang.String organizationId ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationTel",showType="input",showOrder=22,showLength=20)
        private java.lang.String organizationTel ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationZipCode",showType="input",showOrder=23,showLength=20)
        private java.lang.String organizationZipCode ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationAddress",showType="input",showOrder=24,showLength=100)
        private java.lang.String organizationAddress ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="address",showType="input",showOrder=24,showLength=100)
        private java.lang.String address ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setUserId(java.lang.String userId){
        this.userId=userId ;
    }
    public java.lang.String getUserId(){
        return this.userId  ;
    }


    public void setOrganizationFrom(java.lang.String organizationFrom){
        this.organizationFrom=organizationFrom ;
    }
    public java.lang.String getOrganizationFrom(){
        return this.organizationFrom  ;
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


    public void setLeaveDesc(java.lang.String leaveDesc){
        this.leaveDesc=leaveDesc ;
    }
    public java.lang.String getLeaveDesc(){
        return this.leaveDesc  ;
    }


    public void setOrganizationTo(java.lang.String organizationTo){
        this.organizationTo=organizationTo ;
    }
    public java.lang.String getOrganizationTo(){
        return this.organizationTo  ;
    }


    public void setStatus(java.lang.String status){
        this.status=status ;
    }
    public java.lang.String getStatus(){
        return this.status  ;
    }


    public void setIntoTime(java.util.Date intoTime){
        this.intoTime=intoTime ;
    }
    public void setIntoTime(java.lang.String intoTime){
       this.intoTime=ConvertUtil.cvStUtildate(intoTime) ;
   }
    public java.util.Date getIntoTime(){
        return this.intoTime  ;
    }


    public void setOrganizationInto(java.lang.String organizationInto){
        this.organizationInto=organizationInto ;
    }
    public java.lang.String getOrganizationInto(){
        return this.organizationInto  ;
    }


    public void setIntoDesc(java.lang.String intoDesc){
        this.intoDesc=intoDesc ;
    }
    public java.lang.String getIntoDesc(){
        return this.intoDesc  ;
    }


    public void setAuditUserInfo(java.lang.String auditUserInfo){
        this.auditUserInfo=auditUserInfo ;
    }
    public java.lang.String getAuditUserInfo(){
        return this.auditUserInfo  ;
    }


    public void setAuditTime(java.util.Date auditTime){
        this.auditTime=auditTime ;
    }
    public void setAuditTime(java.lang.String auditTime){
       this.auditTime=ConvertUtil.cvStUtildate(auditTime) ;
   }
    public java.util.Date getAuditTime(){
        return this.auditTime  ;
    }


    public void setAuditDesc(java.lang.String auditDesc){
        this.auditDesc=auditDesc ;
    }
    public java.lang.String getAuditDesc(){
        return this.auditDesc  ;
    }


    public void setType(java.lang.Long type){
        this.type=type ;
    }
    public java.lang.Long getType(){
        return this.type  ;
    }


    public void setUserName(java.lang.String userName){
        this.userName=userName ;
    }
    public java.lang.String getUserName(){
        return this.userName  ;
    }


    public void setMobile(java.lang.String mobile){
        this.mobile=mobile ;
    }
    public java.lang.String getMobile(){
        return this.mobile  ;
    }


    public void setIdCard(java.lang.String idCard){
        this.idCard=idCard ;
    }
    public java.lang.String getIdCard(){
        return this.idCard  ;
    }


    public void setPoliticalLandscape(java.lang.String politicalLandscape){
        this.politicalLandscape=politicalLandscape ;
    }
    public java.lang.String getPoliticalLandscape(){
        return this.politicalLandscape  ;
    }


    public void setNational(java.lang.String national){
        this.national=national ;
    }
    public java.lang.String getNational(){
        return this.national  ;
    }


    public void setAge(java.lang.Long age){
        this.age=age ;
    }
    public java.lang.Long getAge(){
        return this.age  ;
    }


    public void setSex(java.lang.String sex){
        this.sex=sex ;
    }
    public java.lang.String getSex(){
        return this.sex  ;
    }


    public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public void setOrganizationId(java.lang.String organizationId){
        this.organizationId=organizationId ;
    }
    public java.lang.String getOrganizationId(){
        return this.organizationId  ;
    }


    public void setOrganizationTel(java.lang.String organizationTel){
        this.organizationTel=organizationTel ;
    }
    public java.lang.String getOrganizationTel(){
        return this.organizationTel  ;
    }


    public void setOrganizationZipCode(java.lang.String organizationZipCode){
        this.organizationZipCode=organizationZipCode ;
    }
    public java.lang.String getOrganizationZipCode(){
        return this.organizationZipCode  ;
    }


    public void setOrganizationAddress(java.lang.String organizationAddress){
        this.organizationAddress=organizationAddress ;
    }
    public java.lang.String getOrganizationAddress(){
        return this.organizationAddress  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_ORGANIZATION_TRANSFER";
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
