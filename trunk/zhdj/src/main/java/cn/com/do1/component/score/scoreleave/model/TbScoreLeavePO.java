package cn.com.do1.component.score.scoreleave.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbScoreLeavePO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="name",showType="input",showOrder=1,showLength=36)
        private java.lang.String name ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=2,showLength=36)
        private java.lang.String type ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="startScore",showType="input",showOrder=3,showLength=22)
        private java.lang.Long startScore ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="endScore",showType="input",showOrder=4,showLength=22)
        private java.lang.Long endScore ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=5,showLength=7)
        private java.util.Date createTime ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setName(java.lang.String name){
        this.name=name ;
    }
    public java.lang.String getName(){
        return this.name  ;
    }


    public void setType(java.lang.String type){
        this.type=type ;
    }
    public java.lang.String getType(){
        return this.type  ;
    }


    public void setStartScore(java.lang.Long startScore){
        this.startScore=startScore ;
    }
    public java.lang.Long getStartScore(){
        return this.startScore  ;
    }


    public void setEndScore(java.lang.Long endScore){
        this.endScore=endScore ;
    }
    public java.lang.Long getEndScore(){
        return this.endScore  ;
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

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_SCORE_LEAVE";
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
