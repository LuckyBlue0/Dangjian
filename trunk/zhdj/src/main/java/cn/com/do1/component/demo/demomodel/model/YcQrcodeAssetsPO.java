package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.bean.FormatMask;
import cn.com.do1.common.annotation.bean.PageView;
import cn.com.do1.common.annotation.bean.Validation;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.util.Date;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-11-13
 * Time: 下午3:13
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class YcQrcodeAssetsPO implements IBaseDBVO {
    private java.lang.String id;	// 主键
    	// 资产名称
    	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "assetsName", showType = "input", showOrder = 1, showLength = 50)
    	private java.lang.String assetsName;	// 资产名称
    	@Validation(must = false, length = 36, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "assetsNo", showType = "input", showOrder = 2, showLength = 36)
    	private java.lang.String assetsNo;	// 资产编号
    	@Validation(must = false, length = 1, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "assetsType", showType = "input", showOrder = 3, showLength = 1)
    	@DictDesc(refField = "assetsType", typeName = "assertType")
    	private java.lang.String assetsType;	// 资产类型
    	//
    	@Validation(must = false, length = 1, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "departement", showType = "input", showOrder = 4, showLength = 1)
    	@DictDesc(refField = "departement", typeName = "department")
    	private java.lang.String departement;	// 所属部门
    	@Validation(must = false, length = 50, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "reviewer", showType = "input", showOrder = 5, showLength = 50)
    	private java.lang.String reviewer;	// 审核人
    	@Validation(must = false, length = 100, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "fileName", showType = "input", showOrder = 6, showLength = 100)
    	private java.lang.String fileName;	// 二维码图片路径
    	@Validation(must = false, length = 11, fieldType = "pattern", regex = "^.*$")
    	@PageView(showName = "createDate", showType = "input", showOrder = 7, showLength = 19)
    	@FormatMask(type = "date", value = "yyyy-MM-dd HH:mm:ss")
    	private Date createDate;	// 制码时间

    	@Override
    	public String toString() {

    		StringBuffer buf = new StringBuffer("YcQrcodeAssetsPO:[")
    				.append("\r\n");
    		buf.append("id=").append(id).append("\r\n");
    		buf.append("assetsName=").append(assetsName).append("\r\n");
    		buf.append("assetsNo=").append(assetsNo).append("\r\n");
    		buf.append("assetsType=").append(assetsType).append("\r\n");
    		buf.append("departement=").append(departement).append("\r\n");
    		buf.append("reviewer=").append(reviewer).append("\r\n");
    		buf.append("fileName=").append(fileName).append("\r\n");
    		buf.append("createDate=").append(createDate).append("\r\n");
    		buf.append("]");

    		return buf.toString();
    	}

    	public void setId(java.lang.String id) {
    		this.id = id;
    	}

    	public java.lang.String getId() {
    		return this.id;
    	}

    	public void setAssetsName(java.lang.String assetsName) {
    		this.assetsName = assetsName;
    	}

    	public java.lang.String getAssetsName() {
    		return this.assetsName;
    	}

    	public void setAssetsNo(java.lang.String assetsNo) {
    		this.assetsNo = assetsNo;
    	}

    	public java.lang.String getAssetsNo() {
    		return this.assetsNo;
    	}

    	public void setAssetsType(java.lang.String assetsType) {
    		this.assetsType = assetsType;
    	}

    	public java.lang.String getAssetsType() {
    		return this.assetsType;
    	}

    	public void setDepartement(java.lang.String departement) {
    		this.departement = departement;
    	}

    	public java.lang.String getDepartement() {
    		return this.departement;
    	}

    	public void setReviewer(java.lang.String reviewer) {
    		this.reviewer = reviewer;
    	}

    	public java.lang.String getReviewer() {
    		return this.reviewer;
    	}

    	public void setFileName(java.lang.String fileName) {
    		this.fileName = fileName;
    	}

    	public java.lang.String getFileName() {
    		return this.fileName;
    	}

    	public void setCreateDate(Date createDate) {
    		this.createDate = createDate;
    	}

    	public Date getCreateDate() {
    		return this.createDate;
    	}

    	/**
    	 * 获取数据库中对应的表名
    	 *
    	 * @return
    	 */
    	public String _getTableName() {
    		return "YC_QRCODE_ASSETS";
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
