package cn.com.do1.component.report.access.model;

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
public class TbAccessInfoPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
        private java.lang.String userId ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="ipAddress",showType="input",showOrder=2,showLength=20)
        private java.lang.String ipAddress ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="accessType",showType="input",showOrder=3,showLength=36)
    @DictDesc(refField = "accessType", typeName = "registeredChannels")
        private java.lang.String accessType ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="accessTime",showType="datetime",showOrder=4,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date accessTime ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="accessItem",showType="input",showOrder=5,showLength=20)
        private java.lang.String accessItem ;
    
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


    public void setIpAddress(java.lang.String ipAddress){
        this.ipAddress=ipAddress ;
    }
    public java.lang.String getIpAddress(){
        return this.ipAddress  ;
    }


    public void setAccessType(java.lang.String accessType){
        this.accessType=accessType ;
    }
    public java.lang.String getAccessType(){
        return this.accessType  ;
    }


    public void setAccessTime(java.util.Date accessTime){
        this.accessTime=accessTime ;
    }
    public void setAccessTime(java.lang.String accessTime){
       this.accessTime=ConvertUtil.cvStUtildate(accessTime) ;
   }
    public java.util.Date getAccessTime(){
        return this.accessTime  ;
    }


    public void setAccessItem(java.lang.String accessItem){
        this.accessItem=accessItem ;
    }
    public java.lang.String getAccessItem(){
        return this.accessItem  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_ACCESS_INFO";
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
