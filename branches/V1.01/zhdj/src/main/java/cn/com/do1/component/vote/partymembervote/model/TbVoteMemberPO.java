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
public class TbVoteMemberPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteTopicId",showType="input",showOrder=1,showLength=36)
        private java.lang.String voteTopicId ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
            @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
                private java.lang.String userId ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="userName",showType="input",showOrder=2,showLength=50)
        private java.lang.String userName ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationId",showType="input",showOrder=3,showLength=36)
        private java.lang.String organizationId ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
            @PageView(showName="organizationName",showType="input",showOrder=2,showLength=100)
                private java.lang.String organizationName ;
            @Validation(must=false,length=1000,fieldType="pattern",regex="^.*$")
    @PageView(showName="advancedDeeds",showType="input",showOrder=4,showLength=1000)
        private java.lang.String advancedDeeds ;
            @Validation(must=false,length=1000,fieldType="pattern",regex="^.*$")
    @PageView(showName="partyWork",showType="input",showOrder=5,showLength=1000)
        private java.lang.String partyWork ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setVoteTopicId(java.lang.String voteTopicId){
        this.voteTopicId=voteTopicId ;
    }
    public java.lang.String getVoteTopicId(){
        return this.voteTopicId  ;
    }


    public void setUserName(java.lang.String userName){
        this.userName=userName ;
    }
    public java.lang.String getUserName(){
        return this.userName  ;
    }


    public void setOrganizationId(java.lang.String organizationId){
        this.organizationId=organizationId ;
    }
    public java.lang.String getOrganizationId(){
        return this.organizationId  ;
    }


    public void setAdvancedDeeds(java.lang.String advancedDeeds){
        this.advancedDeeds=advancedDeeds ;
    }
    public java.lang.String getAdvancedDeeds(){
        return this.advancedDeeds  ;
    }


    public void setPartyWork(java.lang.String partyWork){
        this.partyWork=partyWork ;
    }
    public java.lang.String getPartyWork(){
        return this.partyWork  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_VOTE_MEMBER";
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
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setOrganizationName(java.lang.String organizationName) {
		this.organizationName = organizationName;
	}
	public java.lang.String getOrganizationName() {
		return organizationName;
	}


}
