package cn.com.do1.component.indexmanager.indexshow.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import java.util.Date;
import oracle.sql.TIMESTAMP;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class TbProjectInfoPO implements IBaseDBVO {

	    private java.lang.String id;
    
    
						@Validation(must=false, length=100, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="项目名称", showLength=100) 

			    private java.lang.String proName;
    
    
						@Validation(must=false, length=400, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 2, showName ="项目描述", showLength=400) 

			    private java.lang.String proDesc;
    
    
						@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 3, showName ="项目开始时间", showLength=7) 

			    private java.util.Date proDate;
    
    
						@Validation(must=false, length=200, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 4, showName ="图标", showLength=200) 

			    private java.lang.String icon;
    
    
						@DictDesc(typeName="leaderStatus", refField="status") 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 5, showName ="状态", showLength=2) 

			    private java.lang.String status;
    
    
						@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 6, showName ="创建时间", showLength=7) 

			    private java.util.Date createTime;
    
    


    public void setId(java.lang.String id){
        this.id=id;
    }
			
    public java.lang.String getId(){
        return this.id;
    }
    

    public void setProName(java.lang.String proName){
        this.proName=proName;
    }
			
    public java.lang.String getProName(){
        return this.proName;
    }
    

    public void setProDesc(java.lang.String proDesc){
        this.proDesc=proDesc;
    }
			
    public java.lang.String getProDesc(){
        return this.proDesc;
    }
    

    public void setProDate(java.util.Date proDate){
        this.proDate=proDate;
    }
	    public void setProDate(java.lang.String proDate){
       this.proDate=ConvertUtil.cvStUtildate(proDate);
    }
			
    public java.util.Date getProDate(){
        return this.proDate;
    }
    

    public void setIcon(java.lang.String icon){
        this.icon=icon;
    }
			
    public java.lang.String getIcon(){
        return this.icon;
    }
    

    public void setStatus(java.lang.String status){
        this.status=status;
    }
			
    public java.lang.String getStatus(){
        return this.status;
    }
    

    public void setCreateTime(java.util.Date createTime){
        this.createTime=createTime;
    }
	    public void setCreateTime(java.lang.String createTime){
       this.createTime=ConvertUtil.cvStUtildate(createTime);
    }
			
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_PROJECT_INFO";
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
