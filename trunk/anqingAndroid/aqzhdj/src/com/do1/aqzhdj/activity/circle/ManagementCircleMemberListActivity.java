package com.do1.aqzhdj.activity.circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ListView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.PageListView;
import com.do1.aqzhdj.activity.circle.adapter.ManagementCircleMemberListAdapter;
import com.do1.aqzhdj.util.Constants;

public class ManagementCircleMemberListActivity extends PageListView {

	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.management_circle_member);

		init();
		request();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"群资料", 0, "", this, this);

		ManagementCircleMemberListAdapter adapter = new ManagementCircleMemberListAdapter(
				ManagementCircleMemberListActivity.this, mlistMap,
				R.layout.management_circle_member_item);
		
//		String[] from = {"cname"};
//		int[] to = {R.id.name};
//		String[] asyncImagefrom = {"head_img"};
//		int[] asyncImageto = {R.id.memberImage};
//		int[] where ={R.id.add};
//		SimpleAdavaceAdapter adapter = new SimpleAdavaceAdapter(ManagementCircleMemberListActivity.this, mlistMap,
//				R.layout.management_circle_member_item, from, to, asyncImagefrom, asyncImageto, R.drawable.logo_bg, where, convertViewCallback);
		ListView listView = (ListView) findViewById(R.id.list);
		initView(listView, adapter);

	}

	private void request() {
		String url = Constants.SERVER_URL + "GetCircleApplyMemberList.aspx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", constants.userInfo.getUserId());
		map.put("community_id", Constants.circleInfo.getId());
		map.put("page_no", "1");
		map.put("page_count", "10");
		request(url, map);
	}

	@Override
	protected void changeAdapterData(List<Map<String, Object>> listMap) {
		mlistMap.addAll(listMap);
	}
	
	
//	private ConvertViewCallback convertViewCallback = new ConvertViewCallback() {
//		@Override
//		public void setOnListeners(View view, int position) {
//		}
//
//		@Override
//		public void callback(View convertView, int position) {
//		}
//	};

}
