package cn.com.do1.component.vote.orgvote.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbMinzhuVoteResultPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteId",showType="input",showOrder=1,showLength=36)
        private java.lang.String voteId ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="result1",showType="input",showOrder=2,showLength=22)
        private java.lang.Long result1 ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="result2",showType="input",showOrder=3,showLength=22)
        private java.lang.Long result2 ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="result3",showType="input",showOrder=4,showLength=22)
        private java.lang.Long result3 ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="result4",showType="input",showOrder=5,showLength=22)
        private java.lang.Long result4 ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="reason",showType="input",showOrder=6,showLength=500)
        private java.lang.String reason ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="voteTime",showType="datetime",showOrder=7,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date voteTime ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=8,showLength=36)
        private java.lang.String userId ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteUserType",showType="input",showOrder=9,showLength=22)
        private java.lang.Long voteUserType ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setVoteId(java.lang.String voteId){
        this.voteId=voteId ;
    }
    public java.lang.String getVoteId(){
        return this.voteId  ;
    }


    public void setResult1(java.lang.Long result1){
        this.result1=result1 ;
    }
    public java.lang.Long getResult1(){
        return this.result1  ;
    }


    public void setResult2(java.lang.Long result2){
        this.result2=result2 ;
    }
    public java.lang.Long getResult2(){
        return this.result2  ;
    }


    public void setResult3(java.lang.Long result3){
        this.result3=result3 ;
    }
    public java.lang.Long getResult3(){
        return this.result3  ;
    }


    public void setResult4(java.lang.Long result4){
        this.result4=result4 ;
    }
    public java.lang.Long getResult4(){
        return this.result4  ;
    }


    public void setReason(java.lang.String reason){
        this.reason=reason ;
    }
    public java.lang.String getReason(){
        return this.reason  ;
    }


    public void setVoteTime(java.util.Date voteTime){
        this.voteTime=voteTime ;
    }
    public void setVoteTime(java.lang.String voteTime){
       this.voteTime=ConvertUtil.cvStUtildate(voteTime) ;
   }
    public java.util.Date getVoteTime(){
        return this.voteTime  ;
    }


    public void setUserId(java.lang.String userId){
        this.userId=userId ;
    }
    public java.lang.String getUserId(){
        return this.userId  ;
    }


    public void setVoteUserType(java.lang.Long voteUserType){
        this.voteUserType=voteUserType ;
    }
    public java.lang.Long getVoteUserType(){
        return this.voteUserType  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_MINZHU_VOTE_RESULT";
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
