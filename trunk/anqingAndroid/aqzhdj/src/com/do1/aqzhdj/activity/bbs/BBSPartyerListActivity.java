package com.do1.aqzhdj.activity.bbs;

import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.DateTool;
import com.do1.aqzhdj.util.ImageViewTool;

/**
 * 点击优秀党员评议进入界面：优秀党员评选
 * 
 * @author LHQ
 * 
 */
public class BBSPartyerListActivity extends BaseListActivity implements
		ItemViewHandler {
	private LinkedHashMap<String, Object> map;
	private String[] keys;
	private int[] ids;
	private String startTime;
	private String endTime;
	private String remark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = (LinearLayout) findViewById(R.id.button_bottom);
		layout.setVisibility(View.GONE);

		setCusItemViewHandler(this);
		keys = new String[] { "voteImg", "voteTopic", "totalCount",
				"tv_starttime", "tv_endtime" };
		ids = new int[] { R.id.voteImg, R.id.voteTopic, R.id.totalCount,
				R.id.tv_starttime, R.id.tv_endtime };
		map = new LinkedHashMap<String, Object>();
		setAdapterParams(keys, ids, R.layout.bbs_list_item_view2, map);
	}

	// Adapter调用完getView之后调用，提供对ItemView进行额外处理的功能，如绑定监听等
	@Override
	public void handleItemView(BaseAdapter adapter, final int position,
			View itemView, ViewGroup parent) {
		System.out.println("handleItemView被调用");
		aq = new AQuery(itemView);
		ImageView img = (ImageView) itemView.findViewById(R.id.voteImg);
		String imgPath = mSlpControll.getmListData().get(position)
				.get("voteImgPath").toString();
		ImageViewTool.getAsyncImageBg(imgPath, img, 0);
		// 评议描述
		remark = mSlpControll.getmListData().get(position).get("remark") + "";
		System.out.println("remark:" + remark);
		aq.id(R.id.tv_activity_rules).text(remark);
		// 显示时间
		startTime = mSlpControll.getmListData().get(position).get("startTime")
				.toString();
		endTime = mSlpControll.getmListData().get(position).get("endTime")
				.toString();
		String formatStr = "yyyy-MM-dd HH:mm";
		aq.id(R.id.tv_starttime).text(
				DateTool.StringToString(startTime, formatStr));
		aq.id(R.id.tv_endtime)
				.text(DateTool.StringToString(endTime, formatStr));
		// 监听
		aq.id(R.id.vote_btn_confirm).getView()
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(BBSPartyerListActivity.this,
								BBSPartyerVoteActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("id", listMap.get(position).get("id")
								+ "");
						intent.putExtra("voteStatus", listMap.get(position)
								.get("voteStatus") + "");
						intent.putExtra("voteTopic",
								listMap.get(position).get("voteTopic") + "");
						startActivity(intent);
					}
				});
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "优秀党员评选", 0, "", this, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.partymemberVoteList);
		parentResId = R.layout.dues_bbs_list;
	}
}

// @Override
// public void success(Object data) {
// super.success(data);
// System.out.println("success被调用了");
// try {
// Map<String, Object> datamap = JsonUtil.json2Map(new JSONObject(data
// + ""));
// Object object = datamap.get("pageData");
// JSONArray pageData = new JSONArray(object.toString());
// mlistMap = JsonUtil.jsonArray2List(pageData);
// System.out.println("mlistMap:" + mlistMap);
// String[] keys = { "voteImg", "voteTopic", "totalCount",
// "voteStatus" };
// int[] ids = { R.id.voteImg, R.id.voteTopic, R.id.totalCount,
// R.id.voteStatus };
// SimpleAdapter adapter = new SimpleAdapter(this, mlistMap,
// R.layout.bbs_list_item_view, keys, ids);
// mListView.setAdapter(adapter);
// getFooterView().setVisibility(View.GONE);
// } catch (JSONException e) {
// e.printStackTrace();
// }
// }
// 已监听
// @Override
// protected void itemClick(AdapterView<?> parent, View view, int position,
// long id) {
// super.itemClick(parent, view, position, id);
// System.out.println("跳转到BBSVoteActivity");
// Intent intent = new Intent(BBSPartyerListActivity.this,
// BBSVoteActivity.class);
//
// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
// intent.putExtra("id", listMap.get(position).get("id") + "");
// intent.putExtra("voteStatus", listMap.get(position).get("voteStatus")
// + "");
// intent.putExtra("voteTopic", listMap.get(position).get("voteTopic")
// + "");
// startActivity(intent);
// }