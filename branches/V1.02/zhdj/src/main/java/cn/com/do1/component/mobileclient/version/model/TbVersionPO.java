package cn.com.do1.component.mobileclient.version.model;

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
public class TbVersionPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="versionName",showType="input",showOrder=1,showLength=100)
        private java.lang.String versionName ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="versionNum",showType="input",showOrder=2,showLength=100)
        private java.lang.String versionNum ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=3,showLength=22)
     @DictDesc(refField = "type", typeName = "clientType")
        private java.lang.Long type ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="fileSize",showType="input",showOrder=4,showLength=100)
        private java.lang.String fileSize ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="filePath",showType="input",showOrder=5,showLength=100)
        private java.lang.String filePath ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="creator",showType="input",showOrder=6,showLength=100)
        private java.lang.String creator ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="pushTime",showType="datetime",showOrder=7,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date pushTime ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=8,showLength=22)
     @DictDesc(refField = "status", typeName = "pushType")
        private java.lang.Long status ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="remark",showType="input",showOrder=9,showLength=500)
        private java.lang.String remark ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setVersionName(java.lang.String versionName){
        this.versionName=versionName ;
    }
    public java.lang.String getVersionName(){
        return this.versionName  ;
    }


    public void setVersionNum(java.lang.String versionNum){
        this.versionNum=versionNum ;
    }
    public java.lang.String getVersionNum(){
        return this.versionNum  ;
    }


    public void setType(java.lang.Long type){
        this.type=type ;
    }
    public java.lang.Long getType(){
        return this.type  ;
    }


    public void setFileSize(java.lang.String fileSize){
        this.fileSize=fileSize ;
    }
    public java.lang.String getFileSize(){
        return this.fileSize  ;
    }


    public void setFilePath(java.lang.String filePath){
        this.filePath=filePath ;
    }
    public java.lang.String getFilePath(){
        return this.filePath  ;
    }


    public void setCreator(java.lang.String creator){
        this.creator=creator ;
    }
    public java.lang.String getCreator(){
        return this.creator  ;
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


    public void setStatus(java.lang.Long status){
        this.status=status ;
    }
    public java.lang.Long getStatus(){
        return this.status  ;
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
        return "TB_VERSION";
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
