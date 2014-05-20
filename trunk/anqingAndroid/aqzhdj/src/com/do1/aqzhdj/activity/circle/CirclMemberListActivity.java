package com.do1.aqzhdj.activity.circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.PageListView;
import com.do1.aqzhdj.activity.circle.adapter.CircleMemberListAdapter;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Listenertool;

public class CirclMemberListActivity extends PageListView {

	
	public static int state = 0;
	
	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();
	private CircleMemberListAdapter adapter;
	
	private String postUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_member);

		init();
		request();
	}

	private void init() {
//		int code = Integer.valueOf(Constants.circleInfo.getCircletype());
		int code = Integer.valueOf(Constants.circleInfo.getCircletype());
		switch (code) {
		case 0:
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
					"群成员",0, "", this, this);
			break;

		case 1:
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
					"群成员", 0, "", this, this);
			break;

		case 2:
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
					"群成员", R.drawable.btn_head_2, "管理", this, this);
			break;

		default:
			break;
		}
		
		int[] resourceIds = {};
		Listenertool.bindOnCLickListener(this, this, resourceIds);

		adapter = new CircleMemberListAdapter(CirclMemberListActivity.this, mlistMap,R.layout.circle_member_item,code);
		ListView listView = (ListView) findViewById(R.id.list);
		initView(listView, adapter);
	}
	
	private void request() {
		String url = Constants.SERVER_URL + "GetCircleMemberList.aspx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", constants.userInfo.getUserId());
		map.put("community_id", Constants.circleInfo.getId());
		map.put("page_no", "1");
		map.put("page_count", "100");
		request(url, map);
	}

	@Override
	protected void changeAdapterData(List<Map<String, Object>> listMap) {
		mlistMap.addAll(listMap);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			String text = aq.id(R.id.rightImage).getText().toString();
			if("完成".equals(text)){
				aq.id(R.id.rightImage).text("管理");
				state = 0;
			}else if("管理".equals(text)){
				aq.id(R.id.rightImage).text("完成");
				state = 1;
			}
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		CirclMemberListActivity.state = 0;
	}
}
