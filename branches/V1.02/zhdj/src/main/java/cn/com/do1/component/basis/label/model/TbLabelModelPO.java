package cn.com.do1.component.basis.label.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbLabelModelPO implements IBaseDBVO {
            private java.lang.String labelModelId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="labelName",showType="input",showOrder=1,showLength=50)
        private java.lang.String labelName ;
            @Validation(must=false,length=1,fieldType="pattern",regex="^.*$")
    @PageView(showName="itemType",showType="input",showOrder=2,showLength=1)
        private java.lang.String itemType ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="selectList",showType="input",showOrder=3,showLength=500)
        private java.lang.String selectList ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="sortValue",showType="input",showOrder=4,showLength=22)
        private java.lang.Integer sortValue ;
    
    public void setLabelModelId(java.lang.String labelModelId){
        this.labelModelId=labelModelId ;
    }
    public java.lang.String getLabelModelId(){
        return this.labelModelId  ;
    }


    public void setLabelName(java.lang.String labelName){
        this.labelName=labelName ;
    }
    public java.lang.String getLabelName(){
        return this.labelName  ;
    }


    public void setItemType(java.lang.String itemType){
        this.itemType=itemType ;
    }
    public java.lang.String getItemType(){
        return this.itemType  ;
    }


    public void setSelectList(java.lang.String selectList){
        this.selectList=selectList ;
    }
    public java.lang.String getSelectList(){
        return this.selectList  ;
    }


    public void setSortValue(java.lang.Integer sortValue){
        this.sortValue=sortValue ;
    }
    public java.lang.Integer getSortValue(){
        return this.sortValue  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_LABEL_MODEL";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "labelModelId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(labelModelId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.labelModelId=(java.lang.String)value;
    }

    /**
    * 重写默认的toString方法，使其调用输出的内容更有意义
    */
    public String toString() {
		StringBuffer buf = new StringBuffer("TbLabelModelPO:[").append("\r\n");
		buf.append("labelModelId=").append(labelModelId).append("\r\n");
		buf.append("labelName=").append(labelName).append("\r\n");
		buf.append("itemType=").append(itemType).append("\r\n");
		buf.append("selectList=").append(selectList).append("\r\n");
		buf.append("sortValue=").append(sortValue).append("\r\n");
		buf.append("]");
		return buf.toString();
    }
}
