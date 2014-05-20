package cn.com.do1.component.indexmanager.indexshow.vo;

import cn.com.do1.common.framebase.dqdp.IBaseVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class TbProjectInfoVO implements IBaseVO {
	    private String id;
    
		    	    					@Validation(must=false, length=100, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="项目名称", showLength=100) 

				    	    private String proName;
    
		    	    					@Validation(must=false, length=400, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 2, showName ="项目描述", showLength=400) 

				    	    private String proDesc;
		    	    					@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")   
		    	    					@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 3, showName ="项目开始时间", showLength=7) 

				    	    private String proDate;
    
		    	    					@Validation(must=false, length=200, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 4, showName ="图标", showLength=200) 

				    	    private String icon;
    
		    	    					@DictDesc(typeName="leaderStatus", refField="status")
				private String _status_desc;
				
				 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 5, showName ="状态", showLength=2) 

				    	    private String status;
@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm")
		    	    					@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 6, showName ="创建时间", showLength=7) 

				    	    private String createTime;
    
    
    private List<String> tableNames;
    
	
	public void setId(String id){
	    this.id=id;
	}
	public String getId(){
	    return this.id;
	}
	
		
	
	public void setProName(String proName){
	    this.proName=proName;
	}
	public String getProName(){
	    return this.proName;
	}
	
								
				
	
	public void setProDesc(String proDesc){
	    this.proDesc=proDesc;
	}
	public String getProDesc(){
	    return this.proDesc;
	}
	
								
				
	
	public void setProDate(String proDate){
	    this.proDate=proDate;
	}
	public String getProDate(){
	    return this.proDate;
	}
	
								
				
	
	public void setIcon(String icon){
	    this.icon=icon;
	}
	public String getIcon(){
	    return this.icon;
	}
	
								
				
	
	public void setStatus(String status){
	    this.status=status;
	}
	public String getStatus(){
	    return this.status;
	}
	
										/** 字典关联属性 */
				public void set_status_desc(String _status_desc){
			        this._status_desc=_status_desc;
			    }
				
				public String get_status_desc(){
			        return this._status_desc;
			    }	
					
				
	
	public void setCreateTime(String createTime){
	    this.createTime=createTime;
	}
	public String getCreateTime(){
	    return this.createTime;
	}
	
								
				

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public List<String> _getTableNames() {
    	if(tableNames == null || tableNames.isEmpty()){
    		tableNames = Arrays.asList("TB_PROJECT_INFO".split(","));
    	}
        return tableNames;
    }
}
