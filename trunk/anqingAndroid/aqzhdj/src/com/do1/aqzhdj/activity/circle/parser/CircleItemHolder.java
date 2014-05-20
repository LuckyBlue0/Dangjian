package com.do1.aqzhdj.activity.circle.parser;

import android.widget.TextView;
import cn.com.do1.dqdp.android.common.IlistItem;
import cn.com.do1.dqdp.android.common.ResultMap;
import cn.com.do1.dqdp.android.common.ViewID;

import com.do1.aqzhdj.R;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-22
 * Time: 下午12:30
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleItemHolder implements IlistItem {
    @ResultMap(namePath = "name")
    @ViewID(R.id.text_circle_name)
    private TextView textCircleName;
    @ResultMap(namePath = "numbers")
       @ViewID(R.id.text_circle_personcount)
    private TextView personCount;

    @Override
    public int getItemLayoutID() {
        return R.layout.circle_list_item;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
