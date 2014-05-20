package cn.com.do1.component.team.teaminfo.vo;

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
public class TbTeamInfoVO implements IBaseVO {
	    private String id;
    
		    	    					@Validation(must=false, length=36, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="集体类别", showLength=36) 

				    	    private String teamTypeId;
    
		    	    					@Validation(must=false, length=200, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 2, showName ="集体主题名称", showLength=200) 

				    	    private String teamTitle;
    
		    	    					@Validation(must=false, length=1000, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 3, showName ="集体内容", showLength=1000) 

				    	    private String context;
    
		    	    					@Validation(must=false, length=100, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 4, showName ="集体图片", showLength=100) 

				    	    private String image;
    
		    	    					@Validation(must=false, length=50, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 5, showName ="发布人", showLength=50) 

				    	    private String author;
    
		    	    					@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 6, showName ="发布时间", showLength=7) 

				    	    private String releaseDate;
    
		    	    					@DictDesc(typeName="leaderStatus", refField="status")
				private String _status_desc;
				
				 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 7, showName ="状态", showLength=2) 

				    	    private String status;
    
		    	    					@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 8, showName ="创建时间", showLength=7) 

				    	    private String createTime;
    
    
    private List<String> tableNames;
    
	
	public void setId(String id){
	    this.id=id;
	}
	public String getId(){
	    return this.id;
	}
	
		
	
	public void setTeamTypeId(String teamTypeId){
	    this.teamTypeId=teamTypeId;
	}
	public String getTeamTypeId(){
	    return this.teamTypeId;
	}
	
								
				
	
	public void setTeamTitle(String teamTitle){
	    this.teamTitle=teamTitle;
	}
	public String getTeamTitle(){
	    return this.teamTitle;
	}
	
								
				
	
	public void setContext(String context){
	    this.context=context;
	}
	public String getContext(){
	    return this.context;
	}
	
								
				
	
	public void setImage(String image){
	    this.image=image;
	}
	public String getImage(){
	    return this.image;
	}
	
								
				
	
	public void setAuthor(String author){
	    this.author=author;
	}
	public String getAuthor(){
	    return this.author;
	}
	
								
				
	
	public void setReleaseDate(String releaseDate){
	    this.releaseDate=releaseDate;
	}
	public String getReleaseDate(){
	    return this.releaseDate;
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
    		tableNames = Arrays.asList("TB_TEAM_INFO".split(","));
    	}
        return tableNames;
    }
}
