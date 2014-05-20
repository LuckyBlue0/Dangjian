package com.do1.aqzhdj.activity.mine.box;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.widght.pager.ViewPageControll;

/**
 * 党史上的今天
 * auth:YanFangqin
 * data:2013-4-24
 * thzhd
 */
public class DangHistoryActivity extends BaseListActivity implements ItemViewHandler{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setCusItemViewHandler(this);
		String[] keys = new String[]{"content"};
        int[] ids = new int[]{R.id.text3};
        Map<String, Object> map = new HashMap<String, Object>();
        ViewPageControll.needReflesh = false;
        setAdapterParams(keys,ids,R.layout.dang_history_item,map);
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "党史上的今天", 0,"", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.dang_history);
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		TextView v = (TextView) itemView.findViewById(R.id.text3);
		v.setText("        " + v.getText());
	}
}
