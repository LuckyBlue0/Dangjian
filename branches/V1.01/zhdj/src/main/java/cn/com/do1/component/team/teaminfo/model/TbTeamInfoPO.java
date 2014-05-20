package cn.com.do1.component.team.teaminfo.model;

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
public class TbTeamInfoPO implements IBaseDBVO {

	    private java.lang.String id;
    
    
						@Validation(must=false, length=36, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 1, showName ="集体类别", showLength=36) 

			    private java.lang.String teamTypeId;
    
    
						@Validation(must=false, length=200, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 2, showName ="集体主题名称", showLength=200) 

			    private java.lang.String teamTitle;
    
    
						@Validation(must=false, length=1000, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 3, showName ="集体内容", showLength=1000) 

			    private java.lang.String context;
    
    
						@Validation(must=false, length=100, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 4, showName ="集体图片", showLength=100) 

			    private java.lang.String image;
    
    
						@Validation(must=false, length=50, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "input", showOrder = 5, showName ="发布人", showLength=50) 

			    private java.lang.String author;
    
    
						@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 6, showName ="发布时间", showLength=7) 

			    private java.util.Date releaseDate;
    
    
						@DictDesc(typeName="leaderStatus", refField="status") 
@Validation(must=false, length=2, fieldType = "pattern",  regex = "^.*$") 
@PageView(showType = "select", showOrder = 7, showName ="状态", showLength=2) 

			    private java.lang.String status;
    
    
						@Validation(must=false, length=7, fieldType = "datetime") 
@PageView(showType = "datetime", showOrder = 8, showName ="创建时间", showLength=7) 

			    private java.util.Date createTime;
    
    


    public void setId(java.lang.String id){
        this.id=id;
    }
			
    public java.lang.String getId(){
        return this.id;
    }
    

    public void setTeamTypeId(java.lang.String teamTypeId){
        this.teamTypeId=teamTypeId;
    }
			
    public java.lang.String getTeamTypeId(){
        return this.teamTypeId;
    }
    

    public void setTeamTitle(java.lang.String teamTitle){
        this.teamTitle=teamTitle;
    }
			
    public java.lang.String getTeamTitle(){
        return this.teamTitle;
    }
    

    public void setContext(java.lang.String context){
        this.context=context;
    }
			
    public java.lang.String getContext(){
        return this.context;
    }
    

    public void setImage(java.lang.String image){
        this.image=image;
    }
			
    public java.lang.String getImage(){
        return this.image;
    }
    

    public void setAuthor(java.lang.String author){
        this.author=author;
    }
			
    public java.lang.String getAuthor(){
        return this.author;
    }
    

    public void setReleaseDate(java.util.Date releaseDate){
        this.releaseDate=releaseDate;
    }
	    public void setReleaseDate(java.lang.String releaseDate){
       this.releaseDate=ConvertUtil.cvStUtildate(releaseDate);
    }
			
    public java.util.Date getReleaseDate(){
        return this.releaseDate;
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
        return "TB_TEAM_INFO";
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
