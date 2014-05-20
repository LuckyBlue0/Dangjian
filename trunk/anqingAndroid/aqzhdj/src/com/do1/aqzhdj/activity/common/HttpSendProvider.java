package com.do1.aqzhdj.activity.common;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.util.Log;
import cn.com.do1.dqdp.android.common.IHttpSendProvider;

import com.do1.aqzhdj.util.Entryption;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-22
 * Time: 下午4:07
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class HttpSendProvider implements IHttpSendProvider {
    @Override
    public HttpEntity generyEntity(List<NameValuePair> parm, String encode, boolean postTxtBody) throws Exception {
//        List<NameValuePair> listParam= new ArrayList<NameValuePair>(parm.size());
//        for (NameValuePair stringObjectEntry : parm) {
//            if (stringObjectEntry.getValue() != null)
//                listParam.add(new BasicNameValuePair(stringObjectEntry.getName(),Entryption.encode(stringObjectEntry.getValue().toString())));
//            else
//                listParam.add(new BasicNameValuePair(stringObjectEntry.getName(),""));
//        }
        if (postTxtBody && parm.size() > 0) {
            JSONObject paramJson = new JSONObject();
            for (NameValuePair nameValuePair : parm) {
                paramJson.put(nameValuePair.getName(), nameValuePair.getValue());
            }
            Log.d("dqdp-android", "HTTP处理过程发送了文本形式参数:" + paramJson.toString());
            return (new StringEntity(Entryption.encode(paramJson.toString()), encode));

        } else
            return (new UrlEncodedFormEntity(parm, encode));
    }
}
