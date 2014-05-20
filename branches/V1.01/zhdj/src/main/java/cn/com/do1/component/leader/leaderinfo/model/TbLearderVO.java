package cn.com.do1.component.leader.leaderinfo.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbLearderVO  {
        private java.lang.String id ;
        private java.lang.String userId ;
        private java.lang.String organizationId ;
        private java.lang.String duty ;
        private java.lang.String organizationName ;
        private java.lang.String userName ;
        @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.lang.String createTime ;
        @DictDesc(refField = "duty", typeName = "partyPosition")
        private java.lang.String dutyDesc;
        private java.lang.String status;
        @DictDesc(refField = "status", typeName = "leaderStatus")
        private java.lang.String statusDesc;
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

		public java.lang.String getDuty() {
			return duty;
		}
		public void setDuty(java.lang.String duty) {
			this.duty = duty;
		}

		public java.lang.String getUserName() {
			return userName;
		}
		public void setUserName(java.lang.String userName) {
			this.userName = userName;
		}
		public java.lang.String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(java.lang.String createTime) {
			this.createTime = createTime;
		}
		public void setDutyDesc(java.lang.String dutyDesc) {
			this.dutyDesc = dutyDesc;
		}
		public java.lang.String getDutyDesc() {
			return dutyDesc;
		}

		public void setOrganizationId(java.lang.String organizationId) {
			this.organizationId = organizationId;
		}
		public java.lang.String getOrganizationId() {
			return organizationId;
		}
		public void setOrganizationName(java.lang.String organizationName) {
			this.organizationName = organizationName;
		}
		public java.lang.String getOrganizationName() {
			return organizationName;
		}
		public void setStatus(java.lang.String status) {
			this.status = status;
		}
		public java.lang.String getStatus() {
			return status;
		}
		public void setStatusDesc(java.lang.String statusDesc) {
			this.statusDesc = statusDesc;
		}
		public java.lang.String getStatusDesc() {
			return statusDesc;
		}
       
        
   


}
