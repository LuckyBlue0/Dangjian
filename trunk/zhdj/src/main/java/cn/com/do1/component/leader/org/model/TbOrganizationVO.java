package cn.com.do1.component.leader.org.model;

import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TbOrganizationVO{
            private java.lang.String id ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationName",showType="input",showOrder=1,showLength=100)
        private java.lang.String organizationName ;
            @Validation(must=true,length=36,fieldType="pattern",regex="^.*$")
    @PageView(showName="parentId",showType="input",showOrder=2,showLength=36)
        private java.lang.String parentId ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationSign",showType="input",showOrder=3,showLength=2)
        private java.lang.String organizationSign ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="organizationType",showType="input",showOrder=4,showLength=2)
        private java.lang.String organizationType ;
            @Validation(must=true,length=2,fieldType="pattern",regex="^.*$")
    @PageView(showName="newType",showType="input",showOrder=5,showLength=2)
        private java.lang.String newType ;
            @Validation(must=true,length=100,fieldType="pattern",regex="^.*$")
    @PageView(showName="imgPath",showType="input",showOrder=6,showLength=100)
        private java.lang.String imgPath ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="longitude",showType="input",showOrder=7,showLength=22)
        private java.lang.String longitude ;
            @Validation(must=false,length=50,fieldType="pattern",regex="^.*$")
    @PageView(showName="latitude",showType="input",showOrder=8,showLength=22)
        private java.lang.String latitude ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="administrator",showType="input",showOrder=9,showLength=200)
        private java.lang.String administrator ;
            @Validation(must=false,length=200,fieldType="pattern",regex="^.*$")
    @PageView(showName="editor",showType="input",showOrder=10,showLength=200)
        private java.lang.String editor ;
            @Validation(must=false,length=22,fieldType="pattern",regex="^.*$")
    @PageView(showName="telephone",showType="input",showOrder=11,showLength=22)
        private java.lang.String telephone ;
            @Validation(must=true,length=7,fieldType="datetime",regex="")
    @PageView(showName="createDate",showType="datetime",showOrder=12,showLength=7)
    @FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
        private java.lang.String createDate ;
            @Validation(must=false,length=500,fieldType="pattern",regex="^.*$")
    @PageView(showName="remark",showType="input",showOrder=13,showLength=500)
        private java.lang.String remark ;
        private java.lang.String sort;
        private java.lang.String leader ;

			public java.lang.String getId() {
				return id;
			}
			public void setId(java.lang.String id) {
				this.id = id;
			}
			public java.lang.String getOrganizationName() {
				return organizationName;
			}
			public void setOrganizationName(java.lang.String organizationName) {
				this.organizationName = organizationName;
			}
			public java.lang.String getParentId() {
				return parentId;
			}
			public void setParentId(java.lang.String parentId) {
				this.parentId = parentId;
			}
			public java.lang.String getOrganizationSign() {
				return organizationSign;
			}
			public void setOrganizationSign(java.lang.String organizationSign) {
				this.organizationSign = organizationSign;
			}
			public java.lang.String getOrganizationType() {
				return organizationType;
			}
			public void setOrganizationType(java.lang.String organizationType) {
				this.organizationType = organizationType;
			}
			public java.lang.String getNewType() {
				return newType;
			}
			public void setNewType(java.lang.String newType) {
				this.newType = newType;
			}
			public java.lang.String getImgPath() {
				return imgPath;
			}
			public void setImgPath(java.lang.String imgPath) {
				this.imgPath = imgPath;
			}
			public java.lang.String getLongitude() {
				return longitude;
			}
			public void setLongitude(java.lang.String longitude) {
				this.longitude = longitude;
			}
			public java.lang.String getLatitude() {
				return latitude;
			}
			public void setLatitude(java.lang.String latitude) {
				this.latitude = latitude;
			}
			public java.lang.String getAdministrator() {
				return administrator;
			}
			public void setAdministrator(java.lang.String administrator) {
				this.administrator = administrator;
			}
			public java.lang.String getEditor() {
				return editor;
			}
			public void setEditor(java.lang.String editor) {
				this.editor = editor;
			}
			public java.lang.String getTelephone() {
				return telephone;
			}
			public void setTelephone(java.lang.String telephone) {
				this.telephone = telephone;
			}
			public java.lang.String getCreateDate() {
				return createDate;
			}
			public void setCreateDate(java.lang.String createDate) {
				this.createDate = createDate;
			}
			public java.lang.String getRemark() {
				return remark;
			}
			public void setRemark(java.lang.String remark) {
				this.remark = remark;
			}
			public void setSort(java.lang.String sort) {
				this.sort = sort;
			}
			public java.lang.String getSort() {
				return sort;
			}
			public void setLeader(java.lang.String leader) {
				this.leader = leader;
			}
			public java.lang.String getLeader() {
				return leader;
			}

    


}
