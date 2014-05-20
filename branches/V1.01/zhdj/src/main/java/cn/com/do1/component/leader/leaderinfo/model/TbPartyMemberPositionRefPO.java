package cn.com.do1.component.leader.leaderinfo.model;

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
public class TbPartyMemberPositionRefPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
        private java.lang.String userId ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="partyMemberPositionId",showType="input",showOrder=2,showLength=36)
        private java.lang.String partyMemberPositionId ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="status",showType="input",showOrder=3,showLength=22)
    @DictDesc(refField = "status", typeName = "leaderStatus")
        private java.lang.Long status ;
            private java.lang.String leaderId;
    
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


    public void setPartyMemberPositionId(java.lang.String partyMemberPositionId){
        this.partyMemberPositionId=partyMemberPositionId ;
    }
    public java.lang.String getPartyMemberPositionId(){
        return this.partyMemberPositionId  ;
    }


    public void setStatus(java.lang.Long status){
        this.status=status ;
    }
    public java.lang.Long getStatus(){
        return this.status  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_PARTY_MEMBER_POSITION_REF";
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

	public void setLeaderId(java.lang.String leaderId) {
		this.leaderId = leaderId;
	}
	public java.lang.String getLeaderId() {
		return leaderId;
	}

   
}
