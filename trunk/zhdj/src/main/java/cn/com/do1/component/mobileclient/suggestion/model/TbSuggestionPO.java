package cn.com.do1.component.mobileclient.suggestion.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbSuggestionPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="userName",showType="input",showOrder=1,showLength=100)
        private java.lang.String userName ;
            @Validation(must=true,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=2,showLength=22)
    @DictDesc(refField = "type", typeName = "clientType")
        private java.lang.Integer type ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="suggestion",showType="input",showOrder=3,showLength=500)
        private java.lang.String suggestion ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=4,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime ;
    private String mobile;
    public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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

    public java.lang.Integer getType() {
		return type;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	public void setSuggestion(java.lang.String suggestion){
        this.suggestion=suggestion ;
    }
    public java.lang.String getSuggestion(){
        return this.suggestion  ;
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

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_SUGGESTION";
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
