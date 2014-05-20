package com.do1.aqzhdj.activity.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.adpter.DuesListAdapter;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class BBStypeListActivity extends BaseActivity implements
		OnItemClickListener {

	public static List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();
	private ListView listView;
	private DuesListAdapter adapter;

	// private String[] str = { "今日聚焦", "热帖排行", "党员论坛", "支部论坛", "智慧党建平台问题反馈" };
	private String[] str = { "今日聚焦", "党员论坛", "智慧党建平台问题反馈" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_bbs_list);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "先锋论坛", 0, "", null, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.retie, R.id.item1,
				R.id.item2, R.id.item3);
		// init();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.retie:
			intent = new Intent(this, DuesBbsNextListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// // intent.putExtra("id", "2");
			// // intent.putExtra("title",
			// mlistMap.get(position).get("type_name")
			// // .toString());
			startActivity(intent);
			break;
		case R.id.item1:
			intent = new Intent(this, DuesBbsNextListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.item2:
			intent = new Intent(this, DuesBbsNextListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.item3:
			intent = new Intent(this, DuesBbsNextListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		}
	}

	// @Override
	// protected void onDestroy() {
	// super.onDestroy();
	// mlistMap.clear();
	// }
	//
	// private void init() {
	// setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
	// "党员论坛", 0, "", this, null);
	//
	// for (int i = 0; i < str.length; i++) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("type_name", str[i]);
	// mlistMap.add(map);
	// }
	//
	// listView = (ListView) findViewById(R.id.list);
	// adapter = new DuesListAdapter(BBStypeListActivity.this, mlistMap,
	// R.layout.dues_bbs_list_item);
	// listView.setAdapter(adapter);
	// listView.setOnItemClickListener(this);
	// }
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// if (constants.userInfo.getUser_type().equals("1")) {
	// if(position == 0){
	// Intent intent = new Intent(this, DuesBbsNextListActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// intent.putExtra("id", "1");
	// intent.putExtra("title", mlistMap.get(position).get("type_name")
	// .toString());
	// startActivity(intent);
	// }
	// // //热帖排行
	// // else if(position == 1){
	// // Intent intent = new Intent(this, DuesBbsNextListActivity.class);
	// // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// // intent.putExtra("id", "2");
	// // intent.putExtra("title", mlistMap.get(position).get("type_name")
	// // .toString());
	// // startActivity(intent);
	// // }
	// else if(position == 2){
	// Intent intent = new Intent(this, DuesBbsNextListActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// intent.putExtra("id", "3");
	// intent.putExtra("title", mlistMap.get(position).get("type_name")
	// .toString());
	// startActivity(intent);
	// }else if(position == 1){
	// Intent intent = new Intent(this, DuesBbsListActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// intent.putExtra("id", "1");
	// intent.putExtra("title", mlistMap.get(position).get("type_name")
	// .toString());
	// intent.putExtra("type", 0);
	// startActivity(intent);
	// }
	// // //党员论坛
	// // else if(position == 3){
	// // Intent intent = new Intent(this, DuesBbsListActivity.class);
	// // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// // intent.putExtra("id", "2");
	// // intent.putExtra("title", mlistMap.get(position).get("type_name")
	// // .toString());
	// // intent.putExtra("type", 1);
	// // startActivity(intent);
	// // }
	// } else {
	// new AlertDialog.Builder(BBStypeListActivity.this).setTitle("提示")
	// .setMessage("该功能只开发给党员使用,如果您还不是党员,可以联系天河党委申请入党，获取党员权限")
	// .setPositiveButton("知道了", new OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// dialog.dismiss();
	// BBStypeListActivity.this.finish();
	// }
	// });
	// }
	//
	// }

}
