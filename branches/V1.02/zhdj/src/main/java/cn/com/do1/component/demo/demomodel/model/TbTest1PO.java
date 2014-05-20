package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.util.Date;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public class TbTest1PO implements IBaseDBVO {
        private java.lang.String pkId ;
        private java.lang.String name1 ;
        private Date date ;

    public void setPkId(java.lang.String pkId){
        this.pkId=pkId ;
    }
    public java.lang.String getPkId(){
        return this.pkId  ;
    }


    public void setName1(java.lang.String name1){
        this.name1=name1 ;
    }
    public java.lang.String getName1(){
        return this.name1  ;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_TEST1";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "pkId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(pkId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        setPkId((java.lang.String)value);
    }
}
