package cn.com.do1.component.share.sharemanager.vo;

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
public class TbShareInfoVO implements IBaseVO {
	    private String id;
    
		    	    					@Validation(must=false, length=100, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="标题", showLength=100) 

				    	    private String title;
    
		    	    					@DictDesc(typeName="shareType", refField="type")
				private String _type_desc;
				
				 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 2, showName ="类型", showLength=2) 

				    	    private String type;
    
		    	    					@DictDesc(typeName="leaderStatus", refField="stauts")
				private String _status_desc;
				
				 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 3, showName ="状态", showLength=2) 

				    	    private String status;
    
		    	    					@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 4, showName ="创建时间", showLength=7) 

				    	    private String createTime;
    
    
    private List<String> tableNames;
    
	
	public void setId(String id){
	    this.id=id;
	}
	public String getId(){
	    return this.id;
	}
	
		
	
	public void setTitle(String title){
	    this.title=title;
	}
	public String getTitle(){
	    return this.title;
	}
	
								
				
	
	public void setType(String type){
	    this.type=type;
	}
	public String getType(){
	    return this.type;
	}
	
										/** 字典关联属性 */
				public void set_type_desc(String _type_desc){
			        this._type_desc=_type_desc;
			    }
				
				public String get_type_desc(){
			        return this._type_desc;
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
    		tableNames = Arrays.asList("TB_SHARE_INFO".split(","));
    	}
        return tableNames;
    }
}
