package com.do1.aqzhdj.activity.circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ListView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.PageListView;
import com.do1.aqzhdj.activity.circle.adapter.BaomingListAdapter;
import com.do1.aqzhdj.util.Constants;

public class BaomingLisetActivity extends PageListView{
	
	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();
	private BaomingListAdapter adapter;
	
	private String activityId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baoming_list);

		init();
		request();
	}
	
	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"报名名单",0, "", this, this);
		
		activityId = getIntent().getExtras().getString("activity_id");
		
		adapter = new BaomingListAdapter(BaomingLisetActivity.this, mlistMap,R.layout.baoming_list_item);
		ListView listView = (ListView) findViewById(R.id.list);
		initView(listView, adapter);
		
	}
	
	private void request() {
		String url = Constants.SERVER_URL + "GetCircleActivityMemberList.aspx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("activity_id",activityId);
		map.put("page_no", "1");
		map.put("page_count", "10");
		request(url, map);
	}

	@Override
	protected void changeAdapterData(List<Map<String, Object>> listMap) {
		mlistMap.addAll(listMap);
	}

}
