package cn.com.do1.component.basis.newstype.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.po.SafeInsert;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbNewsColumnTypePO implements IBaseDBVO {
            private java.lang.String newsTypeId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="name",showType="input",showOrder=1,showLength=50)
        private java.lang.String name ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="parentColunmName",showType="input",showOrder=2,showLength=50)
        private java.lang.String parentColunmName ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
            @SafeInsert
    @PageView(showName="orderValue",showType="input",showOrder=3,showLength=22)
        private java.lang.Integer orderValue ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="des",showType="input",showOrder=4,showLength=500)
        private java.lang.String des ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=5,showLength=22)
        private java.lang.Integer type ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="link",showType="input",showOrder=6,showLength=200)
        private java.lang.String link ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="createType",showType="input",showOrder=7,showLength=22)
        private java.lang.Integer createType ;
            @Validation(must=false,length=1000,fieldType="pattern",regex="^.*$")
    @PageView(showName="attributeId",showType="input",showOrder=8,showLength=1000)
        private java.lang.String attributeId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="createUserId",showType="input",showOrder=9,showLength=50)
        private java.lang.String createUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=10,showLength=7)
        private java.util.Date createTime ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="lastModifyUserId",showType="input",showOrder=11,showLength=50)
        private java.lang.String lastModifyUserId ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="lastModifyTime",showType="datetime",showOrder=12,showLength=7)
        private java.util.Date lastModifyTime ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="useStatus",showType="input",showOrder=13,showLength=22)
        private java.lang.Integer useStatus ;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "parentId", showType = "input", showOrder = 23, showLength = 36)
	private java.lang.String parentId;
 

    public java.lang.String getParentId() {
		return parentId;
	}
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	public java.lang.String getNewsTypeId() {
				return newsTypeId;
			}
			public void setNewsTypeId(java.lang.String newsTypeId) {
				this.newsTypeId = newsTypeId;
			}
	public void setName(java.lang.String name){
        this.name=name ;
    }
    public java.lang.String getName(){
        return this.name  ;
    }


    public void setParentColunmName(java.lang.String parentColunmName){
        this.parentColunmName=parentColunmName ;
    }
    public java.lang.String getParentColunmName(){
        return this.parentColunmName  ;
    }


    public void setOrderValue(java.lang.Integer orderValue){
        this.orderValue=orderValue ;
    }
    public java.lang.Integer getOrderValue(){
        return this.orderValue  ;
    }


    public void setDes(java.lang.String des){
        this.des=des ;
    }
    public java.lang.String getDes(){
        return this.des  ;
    }


    public void setType(java.lang.Integer type){
        this.type=type ;
    }
    public java.lang.Integer getType(){
        return this.type  ;
    }


    public void setLink(java.lang.String link){
        this.link=link ;
    }
    public java.lang.String getLink(){
        return this.link  ;
    }


    public void setCreateType(java.lang.Integer createType){
        this.createType=createType ;
    }
    public java.lang.Integer getCreateType(){
        return this.createType  ;
    }


    public void setAttributeId(java.lang.String attributeId){
        this.attributeId=attributeId ;
    }
    public java.lang.String getAttributeId(){
        return this.attributeId  ;
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

    public java.lang.String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
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


    public void setUseStatus(java.lang.Integer useStatus){
        this.useStatus=useStatus ;
    }
    public java.lang.Integer getUseStatus(){
        return this.useStatus  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_NEWS_COLUMN_TYPE";
    }

    /**
    * 获取对应表的主键字段名称
    *
    * @return
    */
    public String _getPKColumnName() {
        return "newsTypeId";
    }

    /**
    * 获取主键值
    *
    * @return
    */
    public String _getPKValue() {
        return String.valueOf(newsTypeId);
    }

    /**
    * 设置主键的值
    *
    * @return
    */
    public void _setPKValue(Object value) {
        this.newsTypeId=(java.lang.String)value;
    }

    /**
     * 重写默认的toString方法，使其调用输出的内容更有意义
     */
     public String toString() {
 		StringBuffer buf = new StringBuffer("TbNewColumnTypePO:[").append("\r\n");
 		buf.append("newsTypeId=").append(newsTypeId).append("\r\n");
 		buf.append("name=").append(name).append("\r\n");
 		buf.append("parentColunmName=").append(parentColunmName).append("\r\n");
 		buf.append("orderValue=").append(orderValue).append("\r\n");
 		buf.append("des=").append(des).append("\r\n");
 		buf.append("type=").append(type).append("\r\n");
 		buf.append("link=").append(link).append("\r\n");
 		buf.append("createType=").append(createType).append("\r\n");
 		buf.append("useStatus=").append(useStatus).append("\r\n");
 		buf.append("attributeId=").append(attributeId).append("\r\n");
 		buf.append("createUserId=").append(createUserId).append("\r\n");
 		buf.append("createTime=").append(createTime).append("\r\n");
 		buf.append("lastModifyUserId=").append(lastModifyUserId).append("\r\n");
 		buf.append("lastModifyTime=").append(lastModifyTime).append("\r\n");
 		buf.append("]");
 		return buf.toString();
     }
}
