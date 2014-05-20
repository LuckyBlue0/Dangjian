package cn.com.do1.component.vote.partymembervote.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import java.util.Date;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbVoteMemberResultPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
        private java.lang.String userId ;//投票对象id
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
            @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
                private java.lang.String voteUserId ;//投票人id
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteId",showType="input",showOrder=2,showLength=36)
        private java.lang.String voteId ;//投票主题
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="voteTime",showType="datetime",showOrder=3,showLength=7)
        private java.util.Date voteTime ;
            @Validation(must=false,length=10,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteUserType",showType="input",showOrder=4,showLength=10)
        private java.lang.String voteUserType ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setUserId(java.lang.String userId){
        this.userId=userId ;
    }
    public java.lang.String getUserId(){
        return this.userId  ;
    }


    public void setVoteId(java.lang.String voteId){
        this.voteId=voteId ;
    }
    public java.lang.String getVoteId(){
        return this.voteId  ;
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


    public void setVoteUserType(java.lang.String voteUserType){
        this.voteUserType=voteUserType ;
    }
    public java.lang.String getVoteUserType(){
        return this.voteUserType  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_VOTE_MEMBER_RESULT";
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
	public void setVoteUserId(java.lang.String voteUserId) {
		this.voteUserId = voteUserId;
	}
	public java.lang.String getVoteUserId() {
		return voteUserId;
	}

}
