package cn.com.do1.component.report.access.model;

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
public class TbAccessInfoVO  {
            private java.lang.String id ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="userId",showType="input",showOrder=1,showLength=36)
        private java.lang.String userId ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="ipAddress",showType="input",showOrder=2,showLength=20)
        private java.lang.String ipAddress ;
            @Validation(must=false,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="accessType",showType="input",showOrder=3,showLength=36)
        private java.lang.String accessType ;
            @DictDesc(refField = "accessType", typeName = "registeredChannels")
            private java.lang.String accessTypeDesc ;
            @Validation(must=false,length=7,fieldType="datetime",regex="")
    @PageView(showName="accessTime",showType="datetime",showOrder=4,showLength=7)
     @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.lang.String accessTime ;
            @Validation(must=false,length=20,fieldType="pattern",regex="^.*$")
    @PageView(showName="accessItem",showType="input",showOrder=5,showLength=20)
        private java.lang.String accessItem ;
            private java.lang.String lastTime;
			public java.lang.String getId() {
				return id;
			}
			public void setId(java.lang.String id) {
				this.id = id;
			}
			public java.lang.String getUserId() {
				return userId;
			}
			public void setUserId(java.lang.String userId) {
				this.userId = userId;
			}
			public java.lang.String getIpAddress() {
				return ipAddress;
			}
			public void setIpAddress(java.lang.String ipAddress) {
				this.ipAddress = ipAddress;
			}
			public java.lang.String getAccessType() {
				return accessType;
			}
			public void setAccessType(java.lang.String accessType) {
				this.accessType = accessType;
			}
			public java.lang.String getAccessTime() {
				return accessTime;
			}
			public void setAccessTime(java.lang.String accessTime) {
				this.accessTime = accessTime;
			}
			public java.lang.String getAccessItem() {
				return accessItem;
			}
			public void setAccessItem(java.lang.String accessItem) {
				this.accessItem = accessItem;
			}
			public void setLastTime(java.lang.String lastTime) {
				this.lastTime = lastTime;
			}
			public java.lang.String getLastTime() {
				return lastTime;
			}
			public void setAccessTypeDesc(java.lang.String accessTypeDesc) {
				this.accessTypeDesc = accessTypeDesc;
			}
			public java.lang.String getAccessTypeDesc() {
				return accessTypeDesc;
			}
       
    
  
}
