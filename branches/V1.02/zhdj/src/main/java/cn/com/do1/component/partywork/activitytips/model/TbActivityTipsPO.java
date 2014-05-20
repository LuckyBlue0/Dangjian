package cn.com.do1.component.partywork.activitytips.model;

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
public class TbActivityTipsPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=1,showLength=22)
        private java.lang.Long type ;
            @Validation(must=false,length=4000,fieldType="pattern",regex="^\\w*$")
    @PageView(showName="content",showType="editor",showOrder=2,showLength=4000)
    @LargeObject
        private String content ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationId",showType="input",showOrder=3,showLength=36)
        private java.lang.String organizationId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="createUserId",showType="input",showOrder=4,showLength=50)
        private java.lang.String createUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=5,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="activityId",showType="input",showOrder=6,showLength=36)
        private java.lang.String activityId ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="source",showType="input",showOrder=7,showLength=20)
        private java.lang.String source ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
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
	public void setOrganizationId(java.lang.String organizationId){
        this.organizationId=organizationId ;
    }
    public java.lang.String getOrganizationId(){
        return this.organizationId  ;
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


    public void setActivityId(java.lang.String activityId){
        this.activityId=activityId ;
    }
    public java.lang.String getActivityId(){
        return this.activityId  ;
    }


    public void setSource(java.lang.String source){
        this.source=source ;
    }
    public java.lang.String getSource(){
        return this.source  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_ACTIVITY_TIPS";
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
        return id;
    }
}
