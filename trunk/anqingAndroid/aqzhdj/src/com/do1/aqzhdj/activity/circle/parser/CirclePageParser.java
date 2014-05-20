package com.do1.aqzhdj.activity.circle.parser;

import org.json.JSONObject;

import cn.com.do1.dqdp.android.common.JSONHelper;
import cn.com.do1.dqdp.android.common.Pager;

import com.do1.aqzhdj.activity.common.ZhdjBaseDataParser;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-23
 * Time: 上午10:06
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CirclePageParser extends ZhdjBaseDataParser {
    public Pager getAvalibelData() throws Exception {
        JSONObject obj = super.getAvalibelData();
        Pager pager = new Pager(10);
        pager.setTotalPages(obj.optLong("total_pages"));
        pager.setPageData(JSONHelper.array2List(obj.getJSONArray("list")));
        return pager;
    }

}
