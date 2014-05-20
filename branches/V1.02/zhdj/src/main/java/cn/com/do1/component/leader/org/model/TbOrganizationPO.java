package cn.com.do1.component.leader.org.model;

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
public class TbOrganizationPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationName",showType="input",showOrder=1,showLength=100)
        private java.lang.String organizationName ;
            @Validation(must=true,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="parentId",showType="input",showOrder=2,showLength=36)
        private java.lang.String parentId ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationSign",showType="input",showOrder=3,showLength=2)
    @DictDesc(refField = "organizationSign", typeName = "organizationSign")
        private java.lang.String organizationSign ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationType",showType="input",showOrder=4,showLength=2)
    @DictDesc(refField = "organizationType", typeName = "organizationType")
        private java.lang.String organizationType ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="newType",showType="input",showOrder=5,showLength=2)
    @DictDesc(refField = "newType", typeName = "newType")
        private java.lang.String newType ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="imgPath",showType="input",showOrder=6,showLength=100)
        private java.lang.String imgPath ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="longitude",showType="input",showOrder=7,showLength=22)
        private java.lang.String longitude ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="latitude",showType="input",showOrder=8,showLength=22)
        private java.lang.String latitude ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="administrator",showType="input",showOrder=9,showLength=200)
        private java.lang.String administrator ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="editor",showType="input",showOrder=10,showLength=200)
        private java.lang.String editor ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
            @PageView(showName="leader",showType="input",showOrder=10,showLength=50)
                private java.lang.String leader ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="telephone",showType="input",showOrder=11,showLength=22)
        private java.lang.String telephone ;
            @Validation(must=true,length=7,fieldType="datetime",regex="")
    @PageView(showName="createDate",showType="datetime",showOrder=12,showLength=7)
        private java.util.Date createDate ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="remark",showType="input",showOrder=13,showLength=500)
        private java.lang.String remark ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setOrganizationName(java.lang.String organizationName){
        this.organizationName=organizationName ;
    }
    public java.lang.String getOrganizationName(){
        return this.organizationName  ;
    }


    public void setParentId(java.lang.String parentId){
        this.parentId=parentId ;
    }
    public java.lang.String getParentId(){
        return this.parentId  ;
    }


    public void setOrganizationSign(java.lang.String organizationSign){
        this.organizationSign=organizationSign ;
    }
    public java.lang.String getOrganizationSign(){
        return this.organizationSign  ;
    }


    public void setOrganizationType(java.lang.String organizationType){
        this.organizationType=organizationType ;
    }
    public java.lang.String getOrganizationType(){
        return this.organizationType  ;
    }


    public void setNewType(java.lang.String newType){
        this.newType=newType ;
    }
    public java.lang.String getNewType(){
        return this.newType  ;
    }


    public void setImgPath(java.lang.String imgPath){
        this.imgPath=imgPath ;
    }
    public java.lang.String getImgPath(){
        return this.imgPath  ;
    }


    public void setLongitude(java.lang.String longitude){
        this.longitude=longitude ;
    }
    public java.lang.String getLongitude(){
        return this.longitude  ;
    }


    public void setLatitude(java.lang.String latitude){
        this.latitude=latitude ;
    }
    public java.lang.String getLatitude(){
        return this.latitude  ;
    }


    public void setAdministrator(java.lang.String administrator){
        this.administrator=administrator ;
    }
    public java.lang.String getAdministrator(){
        return this.administrator  ;
    }


    public void setEditor(java.lang.String editor){
        this.editor=editor ;
    }
    public java.lang.String getEditor(){
        return this.editor  ;
    }


    public void setTelephone(java.lang.String telephone){
        this.telephone=telephone ;
    }
    public java.lang.String getTelephone(){
        return this.telephone  ;
    }


    public void setCreateDate(java.util.Date createDate){
        this.createDate=createDate ;
    }
    public void setCreateDate(java.lang.String createDate){
       this.createDate=ConvertUtil.cvStUtildate(createDate) ;
   }
    public java.util.Date getCreateDate(){
        return this.createDate  ;
    }


    public void setRemark(java.lang.String remark){
        this.remark=remark ;
    }
    public java.lang.String getRemark(){
        return this.remark  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_ORGANIZATION";
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
	public void setLeader(java.lang.String leader) {
		this.leader = leader;
	}
	public java.lang.String getLeader() {
		return leader;
	}



}
