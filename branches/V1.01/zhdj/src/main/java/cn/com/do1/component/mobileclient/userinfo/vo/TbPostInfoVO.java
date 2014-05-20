package cn.com.do1.component.mobileclient.userinfo.vo;

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
public class TbPostInfoVO implements IBaseVO {
	    private String id;
    
		    	    					@Validation(must=false, length=36, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="发帖人id", showLength=36) 

				    	    private String postUserId;
    
		    	    					@Validation(must=false, length=36, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 2, showName ="分享标识", showLength=36) 

				    	    private String shareId;
    
		    	    					public String getName() {
											return name;
										}
										public void setName(String name) {
											this.name = name;
										}
										public String getReply() {
											return reply;
										}
										public void setReply(String reply) {
											this.reply = reply;
										}




										@Validation(must=false, length=500, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 3, showName ="分享内容", showLength=500) 

				    	    private String context;
		    	    					@Validation(must=false, length=500, fieldType = "pattern",  regex = "^.*$") 
		    	    					@PageView(showType = "input", showOrder = 3, showName ="用户名称", showLength=500) 

		    	    									    	    private String name;   
		    	    					@Validation(must=false, length=22, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 4, showName ="查看数", showLength=22) 

				    	    private String viewNum;
		    	    					@Validation(must=false, length=22, fieldType = "pattern",  regex = "^.*$") 
		    	    					@PageView(showType = "input", showOrder = 4, showName ="回复数", showLength=22) 

		    	    									    	    private String reply;
    
		    	    					@DictDesc(typeName="leaderStatus", refField="status")
				private String _status_desc;
				
				 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 5, showName ="状态", showLength=2) 

				    	    private String status;
    
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
	
		
	
	public void setPostUserId(String postUserId){
	    this.postUserId=postUserId;
	}
	public String getPostUserId(){
	    return this.postUserId;
	}
	
								
				
	
	public void setShareId(String shareId){
	    this.shareId=shareId;
	}
	public String getShareId(){
	    return this.shareId;
	}
	
								
				
	
	public void setContext(String context){
	    this.context=context;
	}
	public String getContext(){
	    return this.context;
	}
	
								
				
	
	public void setViewNum(String viewNum){
	    this.viewNum=viewNum;
	}
	public String getViewNum(){
	    return this.viewNum;
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
    		tableNames = Arrays.asList("TB_POST_INFO".split(","));
    	}
        return tableNames;
    }
}
