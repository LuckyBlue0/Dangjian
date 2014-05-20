package com.do1.aqzhdj.activity.circle;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import cn.com.do1.dqdp.android.component.DqdpEditText;
import cn.com.do1.dqdp.android.component.MutexVisibility;
import cn.com.do1.dqdp.android.control.DataReqListActivity;
import cn.com.do1.dqdp.android.data.IDataBundler;
import cn.com.do1.dqdp.android.data.IDataParser;
import cn.com.do1.dqdp.android.data.dqdp.DataReqListAdapter;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.circle.parser.CircleJoinItemHolder;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司 All rights reserved. User: saron Date: 13-4-20
 * Time: 下午5:40 ★★★★★★★★★★★★★★★★★★★★★★★★★★ ★ Saron出品 ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleJoinListActivity extends DataReqListActivity {
	DataReqListAdapter listAdapter = new DataReqListAdapter();
	String circleListId = "getJoinCircleList";
	MutexVisibility mutextView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_join_list);
		setHead();
		super.getListView(R.id.listView).setAdapter(listAdapter);
		initBind();
		updateData();
		mutextView = new MutexVisibility(createIntArry(R.id.btn_join),
				createIntArry(R.id.btn_joind),
				createIntArry(R.id.btn_join_state));
	}

	private void updateData() {
		listAdapter.updatePageData(circleListId, CircleJoinItemHolder.class,
				"page_no");
	}

	private void initBind() {
		super.bindAdapter(listAdapter, this);
		((DqdpEditText) findViewById(R.id.searchText)).getDqdpAttrs()
				.addBindDS(circleListId, "keyword");
		getDataSourceById(circleListId).batchBindParams(
				new String[] { "circleType", "page_count" },
				new String[] { "1", "10" });
	}

	@Override
	public void onHttpOK(IDataBundler dataBundler, IDataParser dataParser)
			throws Exception {
		if (dataParser.getId().equals("joinCircle")) {
			Toast.makeText(this, "申请加入成功", Toast.LENGTH_LONG).show();
			mutextView.setViewVisbility(currentJoin, R.id.btn_join_state, View.VISIBLE);
			listAdapter.clearDataList();
			updateData();
		} else
			super.onHttpOK(dataBundler, dataParser);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case Constants.NAGIVATEBAR_ID_START:
			finish();
			break;
		// case Constants.NAGIVATEBAR_ID_START + 1:
		// startActivity(CircleCreateMainActivity.class, null, null);
		// break;
		case R.id.image_search_btn:
			listAdapter.clearDataList();
			updateData();
			break;
		}
	}

	View currentJoin;

	@Override
	public void defaultChoose(final View view1, final Object data) {
		View btnView = null;
		switch (Integer.valueOf(((JSONObject) data).optString("have_join"))) {
		case 1:
			btnView = view1.findViewById(R.id.btn_joind);
			mutextView.setViewVisbility(view1, btnView.getId(), View.VISIBLE);
			btnView.setVisibility(View.VISIBLE);

			break;
		case 2:
			btnView = view1.findViewById(R.id.btn_join);
			mutextView.setViewVisbility(view1, btnView.getId(), View.VISIBLE);
			btnView.setVisibility(View.VISIBLE);
			btnView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					currentJoin = view1;
					getDataSourceById("joinCircle").bindParam("community_id",
							((JSONObject) data).optString("community_id"));
					runHttpReq("joinCircle");
				}
			});
			break;
		case 3:
			btnView = view1.findViewById(R.id.btn_join_state);
			mutextView.setViewVisbility(view1, btnView.getId(), View.VISIBLE);
			btnView.setVisibility(View.VISIBLE);
			break;
		}
		// View btnView = isNotJoined ? view1.findViewById(R.id.btn_join) :
		// view1.findViewById(R.id.btn_joind);

		view1.findViewById(R.id.text_circle_name).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Constants.circleInfo.setId(((JSONObject) data).optString("community_id"));
                Constants.circleInfo.setName(((JSONObject) data).optString("name"));
                Constants.circleInfo.setNumbers(((JSONObject) data).optString("numbers"));
                Constants.circleInfo.setLabels(((JSONObject) data).optString("labels"));
                Constants.circleInfo.setInfo(((JSONObject) data).optString("info"));
                Constants.circleInfo.setCircletype("0");//只能够查看群资料
                Constants.circleInfo.setCreateUserName(((JSONObject) data).optString("createName"));
                
				Intent intent = new Intent(CircleJoinListActivity.this, CirclInfoActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		
	}

	private void setHead() {
		Object[] left = { "添加与创建" };
		Object[] right = null;
		NagivateBar.initBarById(R.id.include_head, this, "加入群", left, right);
	}
}