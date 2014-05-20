package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

/**
 * Copyright ? 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */

public class TbTest2PO extends TbTest1PO implements IBaseDBVO {
    private java.lang.String pkId;
//    @Security(encode = "{T(cn.com.do1.common.util.security.EncryptionUtils).encrypt('123456789012345678901234',toString())}",decode = "{T(cn.com.do1.common.util.security.EncryptionUtils).decrypt('123456789012345678901234',toString())}")
    private java.lang.String name2;

    public void setPkId(java.lang.String pkId) {
        super.setPkId(pkId);
        this.pkId = pkId;
    }

    public java.lang.String getPkId() {
        return this.pkId;
    }


    public void setName2(java.lang.String name2) {
        this.name2 = name2;
    }

    public java.lang.String getName2() {
        return this.name2;
    }


    /**
     * 获取数据库中对应的表名
     *
     * @return
     */
    public String _getTableName() {
        return "TB_TEST2";
    }

    /**
     * 获取对应表的主键字段名称
     *
     * @return
     */
    public String _getPKColumnName() {
        return "pkId";
    }

    /**
     * 获取主键值
     *
     * @return
     */
    public String _getPKValue() {
        return String.valueOf(pkId);
    }

    /**
     * 设置主键的值
     *
     * @return
     */
    public void _setPKValue(Object value) {
        this.pkId = (java.lang.String) value;
    }
}
