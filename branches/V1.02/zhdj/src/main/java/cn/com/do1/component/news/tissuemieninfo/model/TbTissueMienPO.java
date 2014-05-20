package cn.com.do1.component.news.tissuemieninfo.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.po.LargeObject;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbTissueMienPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="title",showType="input",showOrder=1,showLength=100)
        private java.lang.String title ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=2,showLength=22)
        private java.lang.Long type ;
            @LargeObject
            @Validation(must=false,length=4000,fieldType="pattern",regex="^\\w*$")
    @PageView(showName="content",showType="editor",showOrder=3,showLength=4000)
        private String content ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=4,showLength=22)
        private java.lang.Long status ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="whetherRecommend",showType="input",showOrder=5,showLength=22)
        private java.lang.Long whetherRecommend ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="buyTop",showType="input",showOrder=6,showLength=22)
        private java.lang.Long buyTop ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationId",showType="input",showOrder=7,showLength=36)
        private java.lang.String organizationId ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="imgPath",showType="input",showOrder=8,showLength=200)
        private java.lang.String imgPath ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="createUserId",showType="input",showOrder=9,showLength=50)
        private java.lang.String createUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=10,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="pushUserId",showType="input",showOrder=11,showLength=36)
        private java.lang.String pushUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="pushTime",showType="datetime",showOrder=12,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date pushTime ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setTitle(java.lang.String title){
        this.title=title ;
    }
    public java.lang.String getTitle(){
        return this.title  ;
    }


    public void setType(java.lang.Long type){
        this.type=type ;
    }
    public java.lang.Long getType(){
        return this.type  ;
    }

    public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setStatus(java.lang.Long status){
        this.status=status ;
    }
    public java.lang.Long getStatus(){
        return this.status  ;
    }


    public void setWhetherRecommend(java.lang.Long whetherRecommend){
        this.whetherRecommend=whetherRecommend ;
    }
    public java.lang.Long getWhetherRecommend(){
        return this.whetherRecommend  ;
    }


    public void setBuyTop(java.lang.Long buyTop){
        this.buyTop=buyTop ;
    }
    public java.lang.Long getBuyTop(){
        return this.buyTop  ;
    }


    public void setOrganizationId(java.lang.String organizationId){
        this.organizationId=organizationId ;
    }
    public java.lang.String getOrganizationId(){
        return this.organizationId  ;
    }


    public void setImgPath(java.lang.String imgPath){
        this.imgPath=imgPath ;
    }
    public java.lang.String getImgPath(){
        return this.imgPath  ;
    }


    public void setCreateUserId(java.lang.String createUserId){
        this.createUserId=createUserId ;
    }
    public java.lang.String getCreateUserId(){
        return this.createUserId  ;
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


    public void setPushUserId(java.lang.String pushUserId){
        this.pushUserId=pushUserId ;
    }
    public java.lang.String getPushUserId(){
        return this.pushUserId  ;
    }


    public void setPushTime(java.util.Date pushTime){
        this.pushTime=pushTime ;
    }
    public void setPushTime(java.lang.String pushTime){
       this.pushTime=ConvertUtil.cvStUtildate(pushTime) ;
   }
    public java.util.Date getPushTime(){
        return this.pushTime  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_TISSUE_MIEN";
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

    /**
    * 重写默认的toString方法，使其调用输出的内容更有意义
    */
    public String toString() {
        return id+title;
    }
}
