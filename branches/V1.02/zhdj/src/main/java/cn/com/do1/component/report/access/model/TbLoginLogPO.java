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
public class TbLoginLogPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="userName",showType="input",showOrder=1,showLength=100)
        private java.lang.String userName ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="loginTime",showType="datetime",showOrder=2,showLength=7)
        private java.util.Date loginTime ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=3,showLength=100)
        private java.lang.String status ;
    
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


    public void setLoginTime(java.util.Date loginTime){
        this.loginTime=loginTime ;
    }
    public void setLoginTime(java.lang.String loginTime){
       this.loginTime=ConvertUtil.cvStUtildate(loginTime) ;
   }
    public java.util.Date getLoginTime(){
        return this.loginTime  ;
    }


    public void setStatus(java.lang.String status){
        this.status=status ;
    }
    public java.lang.String getStatus(){
        return this.status  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_LOGIN_LOG";
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
