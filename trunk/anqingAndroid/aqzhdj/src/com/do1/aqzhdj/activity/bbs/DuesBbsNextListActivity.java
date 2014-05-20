package com.do1.aqzhdj.activity.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.DuesBBSDetailActivity;
import com.do1.aqzhdj.activity.bbs.PageListView;
import com.do1.aqzhdj.activity.bbs.SendBBSActivity;
import com.do1.aqzhdj.activity.bbs.adpter.DuesNextListAdapter;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Listenertool;

/**
 * dues_bbs_next_list_item.xml
 * @author boy
 *
 */

public class DuesBbsNextListActivity extends PageListView{
	
	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String,Object>>();
	private String id;
	private String title;
	private DuesNextListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_bbs_next_list);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "论坛", R.drawable.btn_head_2, "发帖", this, this);
//		init();
		ListenerHelper.bindOnCLickListener(this, this, R.id.layout);
//		request("");
	}

	@Override
	protected void changeAdapterData(List<Map<String, Object>> listMap) {
		// TODO Auto-generated method stub
		
	}
	
	
//	private void init() {
//		id = getIntent().getExtras().getString("id");
//		title = getIntent().getExtras().getString("title");
//		
//		if(id.equals("1") || id.equals("2")){
//			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", title, 0, "", this, this);
//		}else{
//			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", title, R.drawable.btn_head_2, "发帖", this, this);
//		}
//		
//		int[] resourceIds = {R.id.bt_search};
//		Listenertool.bindOnCLickListener(this, this, resourceIds);
//		
//		adapter = new DuesNextListAdapter(DuesBbsNextListActivity.this, mlistMap,
//				R.layout.dues_bbs_next_list_item);
//		ListView listView = (ListView) findViewById(R.id.list);
//		initView(listView, adapter);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Intent intent = new Intent(DuesBbsNextListActivity.this,DuesBBSDetailActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				intent.putExtra("id", mlistMap.get(position).get("forum_id").toString());
//				intent.putExtra("title", mlistMap.get(position).get("title").toString());
//				DuesBBSDetailActivity.flag = true;
//				startActivity(intent);
//			}
//		});
//	}
//	
//	private void request(String searchStr){
//		mlistMap.clear();
//		String url = Constants.SERVER_URL +"GetPartyForumList.aspx";
//		Map<String, String> map = new HashMap<String, String>();
//		if(id.equals("3"))
//			map.put("type_id", "B00101");
//		else
//			map.put("type_id", id);
//		map.put("keyword", searchStr);
//		map.put("page_no", "1");
//		map.put("page_count", "10");
//		request(url, map);
//	}
//
//
//	@Override
//	protected void changeAdapterData(List<Map<String, Object>> listMap) {
//		mlistMap.addAll(listMap);
//	}
//	
	@Override
	public void onClick(View v) {
		super.onClick(v); 
		Intent intent;
		switch (v.getId()) {
		case R.id.bt_search:
//			String content =  aq.id(R.id.edit_search).getTextView().getText().toString();
//			mlistMap.clear();
//			adapter.notifyDataSetChanged();
//			request(content);
			break;
			
		case R.id.rightImage:
			intent = new Intent(DuesBbsNextListActivity.this,SendBBSActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("id", id);
			startActivity(intent);
			break;

		case R.id.layout:
			intent = new Intent(DuesBbsNextListActivity.this,DuesBBSDetailActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("id", id);
			startActivity(intent);
			break;
		}
	}
//	
//	@Override
//	protected void onStart() {
//		super.onStart();
//		if(AppManager.needFleshForbbs){
//			AppManager.needFleshForbbs = false;
//			request("");
//		}
//	}

}
