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
 * Date: 13-4-24
 * Time: 上午10:05
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class ActivityHolder implements IlistItem {
    @ResultMap(namePath = "name")
    @ViewID(R.id.activityName)
    private TextView activityName;
    @ResultMap(namePath = "creater")
    @ViewID(R.id.sponser)
    private TextView sponser;
//    @ResultMap(namePath = "community_name")
//    @ViewID(R.id.circleName)
//    private TextView circleName;
    @ResultMap(namePath = "address")
    @ViewID(R.id.activityAddr)
    private TextView activityAddr;
    @ResultMap(namePath = "time")
    @ViewID(R.id.activityTime)
    private TextView activityTime;
    @ResultMap(namePath = "content")
    @ViewID(R.id.activityRole)
    private TextView activityRole;

    @Override
    public int getItemLayoutID() {
        return R.layout.circle_myactivity_item;
    }
}
