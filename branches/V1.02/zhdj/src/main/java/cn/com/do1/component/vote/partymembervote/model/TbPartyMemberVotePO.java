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
public class TbPartyMemberVotePO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteTopic",showType="input",showOrder=1,showLength=500)
        private java.lang.String voteTopic ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="startTime",showType="datetime",showOrder=2,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date startTime ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="endTime",showType="datetime",showOrder=3,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date endTime ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteStatus",showType="input",showOrder=4,showLength=22)
    @DictDesc(refField = "voteStatus", typeName = "voteStatus")
        private java.lang.Long voteStatus ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="pushStatus",showType="input",showOrder=5,showLength=22)
    @DictDesc(refField = "pushStatus", typeName = "pushStatus")
        private java.lang.Long pushStatus ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="pushTime",showType="datetime",showOrder=6,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date pushTime ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="createTime",showType="datetime",showOrder=7,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="remark",showType="input",showOrder=8,showLength=500)
        private java.lang.String remark ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="voteLimit",showType="input",showOrder=9,showLength=50)
        private java.lang.String voteLimit ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
            @PageView(showName="voteImgPath",showType="input",showOrder=10,showLength=500)
        private java.lang.String voteImgPath ;
    
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setVoteTopic(java.lang.String voteTopic){
        this.voteTopic=voteTopic ;
    }
    public java.lang.String getVoteTopic(){
        return this.voteTopic  ;
    }


    public void setStartTime(java.util.Date startTime){
        this.startTime=startTime ;
    }
    public void setStartTime(java.lang.String startTime){
       this.startTime=ConvertUtil.cvStUtildate(startTime) ;
   }
    public java.util.Date getStartTime(){
        return this.startTime  ;
    }


    public void setEndTime(java.util.Date endTime){
        this.endTime=endTime ;
    }
    public void setEndTime(java.lang.String endTime){
       this.endTime=ConvertUtil.cvStUtildate(endTime) ;
   }
    public java.util.Date getEndTime(){
        return this.endTime  ;
    }


    public void setVoteStatus(java.lang.Long voteStatus){
        this.voteStatus=voteStatus ;
    }
    public java.lang.Long getVoteStatus(){
        return this.voteStatus  ;
    }


    public void setPushStatus(java.lang.Long pushStatus){
        this.pushStatus=pushStatus ;
    }
    public java.lang.Long getPushStatus(){
        return this.pushStatus  ;
    }


    public void setPushTime(java.util.Date pushTime){
        this.pushTime=pushTime ;
    }
    public void setPushTime(java.lang.String pushTime){
       this.pushTime=ConvertUtil.cvStUtildate(pushTime) ;
   }
    public java.util.Date getPushTime(){
        return this.pushTime  ;
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


    public void setRemark(java.lang.String remark){
        this.remark=remark ;
    }
    public java.lang.String getRemark(){
        return this.remark  ;
    }


    public void setVoteLimit(java.lang.String voteLimit){
        this.voteLimit=voteLimit ;
    }
    public java.lang.String getVoteLimit(){
        return this.voteLimit  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_PARTY_MEMBER_VOTE";
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
	public void setVoteImgPath(java.lang.String voteImgPath) {
		this.voteImgPath = voteImgPath;
	}
	public java.lang.String getVoteImgPath() {
		return voteImgPath;
	}

  
}
