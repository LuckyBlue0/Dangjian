package com.do1.aqzhdj.activity.circle.parser;

import java.util.List;

import org.json.JSONObject;

import cn.com.do1.dqdp.android.common.JSONHelper;

import com.do1.aqzhdj.activity.common.ZhdjBaseDataParser;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-22
 * Time: 上午11:55
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleListParser extends ZhdjBaseDataParser {
    public List getAvalibelData() throws Exception {
        JSONObject obj = super.getAvalibelData();
        return JSONHelper.array2List(obj.getJSONArray("list"));
    }

}
