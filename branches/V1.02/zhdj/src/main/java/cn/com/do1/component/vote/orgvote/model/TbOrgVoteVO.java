package cn.com.do1.component.vote.orgvote.model;

import cn.com.do1.common.annotation.bean.FormatMask;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbOrgVoteVO  {
        private java.lang.String id ;
        @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.lang.String startTime ;
        @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.lang.String endTime ;
        private String topic;
        private String orgVoteCount;
        private String alreadyCount;
        private String nonCount;
        
        
		public String getOrgVoteCount() {
			return orgVoteCount;
		}
		public void setOrgVoteCount(String orgVoteCount) {
			this.orgVoteCount = orgVoteCount;
		}
		public String getAlreadyCount() {
			return alreadyCount;
		}
		public void setAlreadyCount(String alreadyCount) {
			this.alreadyCount = alreadyCount;
		}
		public String getNonCount() {
			return nonCount;
		}
		public void setNonCount(String nonCount) {
			this.nonCount = nonCount;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public java.lang.String getId() {
			return id;
		}
		public void setId(java.lang.String id) {
			this.id = id;
		}
		public java.lang.String getStartTime() {
			return startTime;
		}
		public void setStartTime(java.lang.String startTime) {
			this.startTime = startTime;
		}
		public java.lang.String getEndTime() {
			return endTime;
		}
		public void setEndTime(java.lang.String endTime) {
			this.endTime = endTime;
		}
 
}
