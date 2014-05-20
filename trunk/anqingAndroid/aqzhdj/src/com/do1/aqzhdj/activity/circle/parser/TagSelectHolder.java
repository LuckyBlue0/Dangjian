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
 * Date: 13-4-20
 * Time: 下午5:22
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TagSelectHolder implements IlistItem {
//    @ResultMap(namePath = "label_id")
//    @ViewID(R.id.tagId)
//    private TextView tagId;
    @ResultMap(namePath = "name")
    @ViewID(R.id.tagName)
    private TextView tagName;

    @Override
    public int getItemLayoutID() {
        return R.layout.circle_tag_select_item;
    }
}
