package cn.com.do1.component.mobileclient.installrecord.model;

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
public class TbInstallRecordPO implements IBaseDBVO {
            private java.lang.String id ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="versionNum",showType="input",showOrder=1,showLength=100)
        private java.lang.String versionNum ;
            @Validation(must=true,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="type",showType="input",showOrder=2,showLength=22)
        @DictDesc(refField = "type", typeName = "clientType")
        private java.lang.Long type ;
            @Validation(must=false,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="equipmentNum",showType="input",showOrder=3,showLength=100)
        private java.lang.String equipmentNum ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="installTime",showType="datetime",showOrder=4,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.util.Date installTime ;
            
        private java.lang.String appVersionNum;
        
        private java.lang.String deviceModel;
        
    public java.lang.String getDeviceModel() {
			return deviceModel;
		}
		public void setDeviceModel(java.lang.String deviceModel) {
			this.deviceModel = deviceModel;
		}
	public java.lang.Long getType() {
			return type;
		}
		public void setType(java.lang.Long type) {
			this.type = type;
		}
	public java.lang.String getAppVersionNum() {
			return appVersionNum;
		}
		public void setAppVersionNum(java.lang.String appVersionNum) {
			this.appVersionNum = appVersionNum;
		}
	public void setId(java.lang.String id){
        this.id=id ;
    }
    public java.lang.String getId(){
        return this.id  ;
    }


    public void setVersionNum(java.lang.String versionNum){
        this.versionNum=versionNum ;
    }
    public java.lang.String getVersionNum(){
        return this.versionNum  ;
    }

	public void setEquipmentNum(java.lang.String equipmentNum){
        this.equipmentNum=equipmentNum ;
    }
    public java.lang.String getEquipmentNum(){
        return this.equipmentNum  ;
    }


    public void setInstallTime(java.util.Date installTime){
        this.installTime=installTime ;
    }
    public void setInstallTime(java.lang.String installTime){
       this.installTime=ConvertUtil.cvStUtildate(installTime) ;
   }
    public java.util.Date getInstallTime(){
        return this.installTime  ;
    }

    /**
    * 获取数据库中对应的表名
    *
    * @return
    */
    public String _getTableName() {
        return "TB_INSTALL_RECORD";
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
