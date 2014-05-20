package cn.com.do1.component.news.newsinfo.model;

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
public class TbNewsInfoPO implements IBaseDBVO {
            private java.lang.String newsInfoId ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="title",showType="input",showOrder=1,showLength=100)
        private java.lang.String title ;
            @Validation(must=false,length=4000,fieldType="pattern",regex="^\\w*$")
    @PageView(showName="content",showType="editor",showOrder=2,showLength=4000)
    	@LargeObject
        private java.lang.String content ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=3,showLength=22)
        private java.lang.Integer status ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="buyTop",showType="input",showOrder=4,showLength=22)
        private java.lang.Integer buyTop ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="solidTop",showType="input",showOrder=5,showLength=22)
        private java.lang.Integer solidTop ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="importance",showType="input",showOrder=6,showLength=22)
        private java.lang.Integer importance ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="newsInfoType",showType="input",showOrder=7,showLength=36)
        private java.lang.String newsInfoType ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationId",showType="input",showOrder=8,showLength=36)
        private java.lang.String organizationId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="createUserId",showType="input",showOrder=9,showLength=50)
        private java.lang.String createUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=10,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="lastModifyUserId",showType="input",showOrder=11,showLength=36)
        private java.lang.String lastModifyUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="lastModifyTime",showType="datetime",showOrder=12,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date lastModifyTime ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="pushUserId",showType="input",showOrder=11,showLength=36)
        private java.lang.String pushUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="pushTime",showType="datetime",showOrder=13,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date pushTime ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="imgPath",showType="input",showOrder=14,showLength=100)
        private java.lang.String imgPath ;
            
            
    
    @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="projectType",showType="projectType",showOrder=1,showLength=100)
        private java.lang.String projectType ;

    @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="bodyDigest",showType="bodyZaiyao",showOrder=1,showLength=100)
        private java.lang.String bodyDigest ;
    
    @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="tabBar",showType="tabBar",showOrder=1,showLength=100)
        private java.lang.String tabBar ;
    
    @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="source",showType="source",showOrder=1,showLength=100)
        private java.lang.String source ;
    
    @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="newsInfoTypeId",showType="source",showOrder=1,showLength=100)
        private java.lang.String newsInfoTypeId ;
    
    
    public java.lang.String getNewsInfoTypeId() {
		return newsInfoTypeId;
	}
	public void setNewsInfoTypeId(java.lang.String newsInfoTypeId) {
		this.newsInfoTypeId = newsInfoTypeId;
	}
	public java.lang.String getProjectType() {
		return projectType;
	}
	public void setProjectType(java.lang.String projectType) {
		this.projectType = projectType;
	}
	public java.lang.String getBodyDigest() {
		return bodyDigest;
	}
	public void setBodyDigest(java.lang.String bodyDigest) {
		this.bodyDigest = bodyDigest;
	}
	public java.lang.String getTabBar() {
		return tabBar;
	}
	public void setTabBar(java.lang.String tabBar) {
		this.tabBar = tabBar;
	}
	public java.lang.String getSource() {
		return source;
	}
	public void setSource(java.lang.String source) {
		this.source = source;
	}
	public void setNewsInfoId(java.lang.String newsInfoId){
        this.newsInfoId=newsInfoId ;
    }
    public java.lang.String getNewsInfoId(){
        return this.newsInfoId  ;
    }


    public void setTitle(java.lang.String title){
        this.title=title ;
    }
    public java.lang.String getTitle(){
        return this.title  ;
    }

	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getNewsInfoType() {
		return newsInfoType;
	}
	public void setNewsInfoType(java.lang.String newsInfoType) {
		this.newsInfoType = newsInfoType;
	}
	public void setStatus(java.lang.Integer status){
        this.status=status ;
    }
    public java.lang.Integer getStatus(){
        return this.status  ;
    }


    public void setBuyTop(java.lang.Integer buyTop){
        this.buyTop=buyTop ;
    }
    public java.lang.Integer getBuyTop(){
        return this.buyTop  ;
    }


    public void setSolidTop(java.lang.Integer solidTop){
        this.solidTop=solidTop ;
    }
    public java.lang.Integer getSolidTop(){
        return this.solidTop  ;
    }


    public void setImportance(java.lang.Integer importance){
        this.importance=importance ;
    }
    public java.lang.Integer getImportance(){
        return this.importance  ;
    }




    public void setOrganizationId(java.lang.String organizationId){
        this.organizationId=organizationId ;
    }
    public java.lang.String getOrganizationId(){
        return this.organizationId  ;
    }


    public java.lang.String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
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
    public java.lang.String getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(java.lang.String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public void setLastModifyTime(java.util.Date lastModifyTime){
        this.lastModifyTime=lastModifyTime ;
    }
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime=ConvertUtil.cvStUtildate(lastModifyTime) ;
   }
    public java.util.Date getLastModifyTime(){
        return this.lastModifyTime  ;
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


    public void setImgPath(java.lang.String imgPath){
        this.imgPath=imgPath ;
    }
    public java.lang.String getImgPath(){
        return this.imgPath  ;
    }
    
    

    public java.lang.String getPushUserId() {
		return pushUserId;
	}
	public void setPushUserId(java.lang.String pushUserId) {
		this.pushUserId = pushUserId;
	}
	/**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_NEWS_INFO";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "newsInfoId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(newsInfoId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.newsInfoId=(java.lang.String)value;
    }

 
}
