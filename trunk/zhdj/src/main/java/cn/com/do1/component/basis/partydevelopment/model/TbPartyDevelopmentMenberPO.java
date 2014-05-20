package cn.com.do1.component.basis.partydevelopment.model;

import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.reflation.ConvertUtil;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TbPartyDevelopmentMenberPO implements IBaseDBVO {
	private java.lang.String id;
	@Validation(must = false, length = 30, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "name", showType = "input", showOrder = 1, showLength = 30)
	private java.lang.String name;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "sex", showType = "input", showOrder = 2, showLength = 36)
	private java.lang.String sex;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationIdentity", showType = "input", showOrder = 3, showLength = 36)
	private java.lang.String organizationIdentity;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "organizationId", showType = "input", showOrder = 4, showLength = 36)
	private java.lang.String organizationId;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "department", showType = "input", showOrder = 5, showLength = 36)
	private java.lang.String department;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "birthday", showType = "input", showOrder = 6, showLength = 20)
	private java.lang.String birthday;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "national", showType = "input", showOrder = 7, showLength = 36)
	private java.lang.String national;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "nativePlace", showType = "input", showOrder = 8, showLength = 100)
	private java.lang.String nativePlace;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "idCard", showType = "input", showOrder = 9, showLength = 20)
	private java.lang.String idCard;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "degree", showType = "input", showOrder = 10, showLength = 36)
	private java.lang.String degree;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "degreeIn", showType = "input", showOrder = 11, showLength = 36)
	private java.lang.String degreeIn;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "unit", showType = "input", showOrder = 12, showLength = 100)
	private java.lang.String unit;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "workTime", showType = "input", showOrder = 13, showLength = 20)
	private java.lang.String workTime;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "health", showType = "input", showOrder = 14, showLength = 50)
	private java.lang.String health;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "school", showType = "input", showOrder = 15, showLength = 50)
	private java.lang.String school;
	@Validation(must = false, length = 20, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "oldName", showType = "input", showOrder = 16, showLength = 20)
	private java.lang.String oldName;
	@Validation(must = false, length = 15, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "mobile", showType = "input", showOrder = 17, showLength = 15)
	private java.lang.String mobile;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "administrativeDuties", showType = "input", showOrder = 18, showLength = 50)
	private java.lang.String administrativeDuties;
	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "cultivatingContacts", showType = "input", showOrder = 21, showLength = 36)
	private java.lang.String cultivatingContacts;
	@Validation(must = false, length = 2000, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "note", showType = "input", showOrder = 22, showLength = 2000)
	private java.lang.String note;
	@Validation(must = false, length = 7, fieldType = "datetime", regex = "")
	@PageView(showName = "createTime", showType = "datetime", showOrder = 23, showLength = 7)
	private java.util.Date createTime;
	@Validation(must = false, length = 22, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "state", showType = "input", showOrder = 24, showLength = 22)
	private java.lang.Long state;
	@Validation(must = false, length = 300, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "portraitPic", showType = "input", showOrder = 24, showLength = 300)
	private java.lang.String portraitPic;
	@Validation(must = false, length = 200, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "homeAddress", showType = "input", showOrder = 24, showLength = 200)
	private java.lang.String homeAddress;
	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "userName", showType = "input", showOrder = 1, showLength = 50)
	private java.lang.String userName;
	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
	@PageView(showName = "passWord", showType = "input", showOrder = 2, showLength = 100)
	private java.lang.String passWord;

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getPassWord() {
		return passWord;
	}

	public void setPassWord(java.lang.String passWord) {
		this.passWord = passWord;
	}

	public java.lang.String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(java.lang.String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public java.lang.String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(java.lang.String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public java.lang.String getOrganizationIdentity() {
		return organizationIdentity;
	}

	public void setOrganizationIdentity(java.lang.String organizationIdentity) {
		this.organizationIdentity = organizationIdentity;
	}

	public java.lang.String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(java.lang.String organizationId) {
		this.organizationId = organizationId;
	}

	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	public java.lang.String getDepartment() {
		return this.department;
	}

	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
	}

	public java.lang.String getBirthday() {
		return this.birthday;
	}

	public void setNational(java.lang.String national) {
		this.national = national;
	}

	public java.lang.String getNational() {
		return this.national;
	}

	public void setNativePlace(java.lang.String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public java.lang.String getNativePlace() {
		return this.nativePlace;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getIdCard() {
		return this.idCard;
	}

	public void setDegree(java.lang.String degree) {
		this.degree = degree;
	}

	public java.lang.String getDegree() {
		return this.degree;
	}

	public void setDegreeIn(java.lang.String degreeIn) {
		this.degreeIn = degreeIn;
	}

	public java.lang.String getDegreeIn() {
		return this.degreeIn;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.String getUnit() {
		return this.unit;
	}

	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	public java.lang.String getWorkTime() {
		return this.workTime;
	}

	public void setHealth(java.lang.String health) {
		this.health = health;
	}

	public java.lang.String getHealth() {
		return this.health;
	}

	public void setSchool(java.lang.String school) {
		this.school = school;
	}

	public java.lang.String getSchool() {
		return this.school;
	}

	public void setOldName(java.lang.String oldName) {
		this.oldName = oldName;
	}

	public java.lang.String getOldName() {
		return this.oldName;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setAdministrativeDuties(java.lang.String administrativeDuties) {
		this.administrativeDuties = administrativeDuties;
	}

	public java.lang.String getAdministrativeDuties() {
		return this.administrativeDuties;
	}

	public void setCultivatingContacts(java.lang.String cultivatingContacts) {
		this.cultivatingContacts = cultivatingContacts;
	}

	public java.lang.String getCultivatingContacts() {
		return this.cultivatingContacts;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.String getNote() {
		return this.note;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = ConvertUtil.cvStUtildate(createTime);
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setState(java.lang.Long state) {
		this.state = state;
	}

	public java.lang.Long getState() {
		return this.state;
	}

	/**
	 * 获取数据库中对应的表名
	 * 
	 * @return
	 */
	public String _getTableName() {
		return "TB_PARTY_DEV_MENBER_INFO";
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
		this.id = (java.lang.String) value;
	}

}
