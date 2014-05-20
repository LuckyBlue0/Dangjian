package com.do1.zhdj.activity.common;

import java.util.Map;

import cn.com.do1.dqdp.android.common.IReqSecurity;
import cn.com.do1.dqdp.android.exception.BaseException;

import com.do1.zhdj.util.Entryption;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: Saron
 * Date: 13-1-15
 * Time: 下午8:00
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class HttpSecurityProvider implements IReqSecurity {
    //    private SecurityProvider provider = new SecurityProvider();
    private String[] paramName;
    private static String secKey;

    public HttpSecurityProvider(String... paramName) {
        if (paramName == null)
            this.paramName = new String[0];
        else
            this.paramName = paramName;
    }

    public static void setKey(String key) {
        secKey = key;
    }

    public String rewriteUrl(String url, String[] append) {
        return url;
    }

    /**
     * 比如有个请求有A、B、C三个参数，则在初始化时传入了这三个名称，
     * 在提交的时候就会在原有的的参数里多出一个以key为名的参数，其值是这三个参数的MD5值
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String[] paramConvert(Map<String, Object> param) throws Exception {
        for (Map.Entry<String, Object> stringObjectEntry : param.entrySet()) {
            if (stringObjectEntry.getValue() != null)
                stringObjectEntry.setValue(Entryption.encode(stringObjectEntry.getValue().toString()));
        }
//        String[] tempStr = new String[paramName.length + 1];
//        for (int i = 0; i < paramName.length; i++) {
//            if (i == paramName.length - 1) {
//                tempStr[i] = secKey;
//            } else {
//                Object value = param.get(paramName[i]);
//                tempStr[i] = value == null ? "" : value.toString();
//            }
//        }
//        param.put("key", new MD5().getMD5ofStr(StringUtil.uniteArry(tempStr, "")));
//        return new String[]{param.get("key").toString()};
        return null;
    }

    @Override
    public String secValue(String value) throws BaseException, Exception {
        return value;
    }

    public static void main(String[] args) throws Exception {
        Entryption.encode("");
        System.out.println("xx:"+Entryption.decode("bU2Qj6SHMuY="));
    }
}
