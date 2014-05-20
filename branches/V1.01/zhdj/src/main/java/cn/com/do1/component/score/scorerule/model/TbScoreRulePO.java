package cn.com.do1.component.score.scorerule.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbScoreRulePO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="scoreType",showType="input",showOrder=1,showLength=36)
        private java.lang.String scoreType ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="scoreValue",showType="input",showOrder=2,showLength=50)
        private java.lang.String scoreValue ;
            @Validation(must=false,length=1000,fieldType="pattern",regex="^.*$")
    @PageView(showName="scoreDesc",showType="input",showOrder=3,showLength=1000)
        private java.lang.String scoreDesc ;
        @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date createTime;
        @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
        @PageView(showName="scoreAttr",showType="input",showOrder=2,showLength=50)
         @DictDesc(refField = "scoreAttr", typeName = "scoreAttr")
            private java.lang.String scoreAttr ;
    public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setScoreType(java.lang.String scoreType){
        this.scoreType=scoreType ;
    }
    public java.lang.String getScoreType(){
        return this.scoreType  ;
    }


    public void setScoreValue(java.lang.String scoreValue){
        this.scoreValue=scoreValue ;
    }
    public java.lang.String getScoreValue(){
        return this.scoreValue  ;
    }


    public void setScoreDesc(java.lang.String scoreDesc){
        this.scoreDesc=scoreDesc ;
    }
    public java.lang.String getScoreDesc(){
        return this.scoreDesc  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_SCORE_RULE";
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
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setScoreAttr(java.lang.String scoreAttr) {
		this.scoreAttr = scoreAttr;
	}
	public java.lang.String getScoreAttr() {
		return scoreAttr;
	}

   
}
