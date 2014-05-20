package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.annotation.bean.AutoReplaceBean;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.component.systemmgr.org.model.TbDqdpOrgPO;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-11-16
 * Time: 下午6:07
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
//@AutoReplaceBean
public class ExOrgPO extends TbDqdpOrgPO{
    private Integer type;

       public Integer getType() {
           return type;
       }

       public void setType(Integer type) {
           this.type = type;
       }
       public void setType(String type) {
           this.type = ConvertUtil.cvStIntg(type);
       }
}
