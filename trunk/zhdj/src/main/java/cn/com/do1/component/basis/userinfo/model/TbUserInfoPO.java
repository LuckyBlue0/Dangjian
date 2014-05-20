package cn.com.do1.component.basis.userinfo.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import java.util.Date;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbUserInfoPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="userName",showType="input",showOrder=1,showLength=50)
        private java.lang.String userName ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="password",showType="input",showOrder=2,showLength=50)
        private java.lang.String password ;
            @Validation(must=false,length=25,fieldType="pattern",regex="^.*$")
    @PageView(showName="name",showType="input",showOrder=3,showLength=25)
        private java.lang.String name ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="sex",showType="input",showOrder=4,showLength=22)
        private java.lang.Long sex ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="idCard",showType="input",showOrder=5,showLength=20)
        private java.lang.String idCard ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="regTime",showType="datetime",showOrder=6,showLength=7)
        private java.util.Date regTime ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="registeredChannels",showType="input",showOrder=7,showLength=20)
        private java.lang.String registeredChannels ;
            @Validation(must=false,length=10,fieldType="pattern",regex="^.*$")
    @PageView(showName="degree",showType="input",showOrder=8,showLength=10)
        private java.lang.String degree ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="politicalLandscape",showType="input",showOrder=9,showLength=20)
        private java.lang.String politicalLandscape ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="birthday",showType="input",showOrder=10,showLength=20)
        private java.lang.String birthday ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="email",showType="input",showOrder=11,showLength=50)
        private java.lang.String email ;
            @Validation(must=false,length=15,fieldType="pattern",regex="^.*$")
    @PageView(showName="mobile",showType="input",showOrder=12,showLength=15)
        private java.lang.String mobile ;
            @Validation(must=false,length=15,fieldType="pattern",regex="^.*$")
    @PageView(showName="qq",showType="input",showOrder=13,showLength=15)
        private java.lang.String qq ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="homeAddress",showType="input",showOrder=14,showLength=100)
        private java.lang.String homeAddress ;
            @Validation(must=false,length=2000,fieldType="pattern",regex="^.*$")
    @PageView(showName="note",showType="input",showOrder=15,showLength=2000)
        private java.lang.String note ;
            @Validation(must=false,length=300,fieldType="pattern",regex="^.*$")
    @PageView(showName="portraitPic",showType="input",showOrder=16,showLength=300)
        private java.lang.String portraitPic ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="state",showType="input",showOrder=17,showLength=22)
        private java.lang.Long state ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setUserName(java.lang.String userName){
        this.userName=userName ;
    }
    public java.lang.String getUserName(){
        return this.userName  ;
    }


    public void setPassword(java.lang.String password){
        this.password=password ;
    }
    public java.lang.String getPassword(){
        return this.password  ;
    }


    public void setName(java.lang.String name){
        this.name=name ;
    }
    public java.lang.String getName(){
        return this.name  ;
    }


    public void setSex(java.lang.Long sex){
        this.sex=sex ;
    }
    public java.lang.Long getSex(){
        return this.sex  ;
    }


    public void setIdCard(java.lang.String idCard){
        this.idCard=idCard ;
    }
    public java.lang.String getIdCard(){
        return this.idCard  ;
    }


    public void setRegTime(java.util.Date regTime){
        this.regTime=regTime ;
    }
    public void setRegTime(java.lang.String regTime){
       this.regTime=ConvertUtil.cvStUtildate(regTime) ;
   }
    public java.util.Date getRegTime(){
        return this.regTime  ;
    }


    public void setRegisteredChannels(java.lang.String registeredChannels){
        this.registeredChannels=registeredChannels ;
    }
    public java.lang.String getRegisteredChannels(){
        return this.registeredChannels  ;
    }


    public void setDegree(java.lang.String degree){
        this.degree=degree ;
    }
    public java.lang.String getDegree(){
        return this.degree  ;
    }


    public void setPoliticalLandscape(java.lang.String politicalLandscape){
        this.politicalLandscape=politicalLandscape ;
    }
    public java.lang.String getPoliticalLandscape(){
        return this.politicalLandscape  ;
    }


    public void setBirthday(java.lang.String birthday){
        this.birthday=birthday ;
    }
    public java.lang.String getBirthday(){
        return this.birthday  ;
    }


    public void setEmail(java.lang.String email){
        this.email=email ;
    }
    public java.lang.String getEmail(){
        return this.email  ;
    }


    public void setMobile(java.lang.String mobile){
        this.mobile=mobile ;
    }
    public java.lang.String getMobile(){
        return this.mobile  ;
    }


    public void setQq(java.lang.String qq){
        this.qq=qq ;
    }
    public java.lang.String getQq(){
        return this.qq  ;
    }


    public void setHomeAddress(java.lang.String homeAddress){
        this.homeAddress=homeAddress ;
    }
    public java.lang.String getHomeAddress(){
        return this.homeAddress  ;
    }


    public void setNote(java.lang.String note){
        this.note=note ;
    }
    public java.lang.String getNote(){
        return this.note  ;
    }


    public void setPortraitPic(java.lang.String portraitPic){
        this.portraitPic=portraitPic ;
    }
    public java.lang.String getPortraitPic(){
        return this.portraitPic  ;
    }


    public void setState(java.lang.Long state){
        this.state=state ;
    }
    public java.lang.Long getState(){
        return this.state  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_USER_INFO";
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
