package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.annotation.bean.AutoReplaceBean;
import cn.com.do1.component.systemmgr.org.model.OrgVO;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-11-16
 * Time: 下午5:53
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
//@AutoReplaceBean
public class ExOrgVO extends OrgVO {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
