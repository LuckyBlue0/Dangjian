package cn.com.do1.component.basis.label.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbLabelModelItemDataPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=true,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="newsInfoId",showType="input",showOrder=1,showLength=36)
        private java.lang.String newsInfoId ;
            @Validation(must=true,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="labelModelId",showType="input",showOrder=2,showLength=36)
        private java.lang.String labelModelId ;
            @Validation(must=true,length=4000,fieldType="pattern",regex="^.*$")
    @PageView(showName="itemValue",showType="input",showOrder=3,showLength=4000)
        private java.lang.String itemValue ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setnewsInfoId(java.lang.String newsInfoId){
        this.newsInfoId=newsInfoId ;
    }
    public java.lang.String getnewsInfoId(){
        return this.newsInfoId  ;
    }


    public void setLabelModelId(java.lang.String labelModelId){
        this.labelModelId=labelModelId ;
    }
    public java.lang.String getLabelModelId(){
        return this.labelModelId  ;
    }


    public void setItemValue(java.lang.String itemValue){
        this.itemValue=itemValue ;
    }
    public java.lang.String getItemValue(){
        return this.itemValue  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_LABEL_MODEL_ITEM_DATA";
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
		StringBuffer buf = new StringBuffer("TbLabelModelPO:[").append("\r\n");
		buf.append("id=").append(id).append("\r\n");
		buf.append("newsInfoId=").append(newsInfoId).append("\r\n");
		buf.append("labelModelId=").append(labelModelId).append("\r\n");
		buf.append("itemValue=").append(itemValue).append("\r\n");
		return buf.toString();
    }
}
