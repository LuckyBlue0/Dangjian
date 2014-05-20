package com.do1.aqzhdj.activity.circle.parser;

import android.widget.TextView;
import cn.com.do1.dqdp.android.common.IlistItem;
import cn.com.do1.dqdp.android.common.ResultMap;
import cn.com.do1.dqdp.android.common.ViewID;

import com.do1.aqzhdj.R;

public class CircleJoinItemHolder implements IlistItem {
	
	@ResultMap(namePath = "name")
    @ViewID(R.id.text_circle_name)
    private cn.com.do1.dqdp.android.component.DqdpTextView textCircleName;
    @ResultMap(namePath = "numbers")
       @ViewID(R.id.text_circle_personcount)
    private TextView personCount;

	@Override
	public int getItemLayoutID() {
		return R.layout.circle_join_list_item;
	}

}
