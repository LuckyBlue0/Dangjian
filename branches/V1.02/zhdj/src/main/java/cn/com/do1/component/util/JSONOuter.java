package cn.com.do1.component.util;

import cn.com.do1.common.framebase.struts.IBodyOuter;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: apple
 * Date: 13-7-3
 * Time: 下午3:36
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class JSONOuter implements IBodyOuter {

    static JSONOuter outer;
    static final Object lokcer = new Object();

    static {
        synchronized (lokcer) {
            if (outer == null) outer = new JSONOuter();

        }
    }

    public static JSONOuter getInstance() {
        return outer;
    }

    @Override
    public String getBody(String str) {
        return str;
    }

}
