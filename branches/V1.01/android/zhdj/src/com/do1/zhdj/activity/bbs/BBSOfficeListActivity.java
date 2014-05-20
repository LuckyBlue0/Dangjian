package com.do1.zhdj.activity.bbs;

import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.DateTool;

/**
 * 机关民主评议页面
 * @author LHQ
 * 
 */
public class BBSOfficeListActivity extends BaseListActivity implements
		ItemViewHandler {
	private String startTime;
	private String endTime;
	private String voteId;
	private String nonCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = (LinearLayout) findViewById(R.id.button_bottom);
		layout.setVisibility(View.GONE);
		setCusItemViewHandler(this);
		String[] keys = { "topic", "startTime", "endTime", "orgVoteCount" };
		int[] ids = { R.id.office_title, R.id.tv_start_time, R.id.tv_end_time,
				R.id.vote_orgVoteCount };
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("voteUserId", constants.userInfo.getUserId());
		setAdapterParams(keys, ids, R.layout.bbs_office_list_item, map);
	}

	@Override
	protected void onResume() {
		super.onResume();
		doSearch();
	}

	// 自动执行
	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		System.out.println("handleItemView被调用了、、");
		aq = new AQuery(itemView);
		// 显示时间
		startTime = listMap.get(position).get("startTime") + "";
		endTime = listMap.get(position).get("endTime") + "";
		String formatStr = "yyyy-MM-dd HH:mm";
		aq.id(R.id.tv_start_time).text(
				DateTool.StringToString(startTime, formatStr));
		aq.id(R.id.tv_end_time).text(
				DateTool.StringToString(endTime, formatStr));
		// 未评议数量
		nonCount = listMap.get(position).get("nonCount") + "";
		aq.id(R.id.tv_noncount).text("(" + nonCount + "个未评议)");
	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		Intent intent = new Intent(BBSOfficeListActivity.this,
				BBSOfficeVoteActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		voteId = listMap.get(position).get("id") + "";
		intent.putExtra("voteId", voteId);
		startActivity(intent);
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "机关民主评议", 0, "", this, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.office_orgVoteList);
	}
}