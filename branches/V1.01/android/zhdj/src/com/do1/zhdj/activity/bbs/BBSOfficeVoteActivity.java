package com.do1.zhdj.activity.bbs;

import java.util.LinkedHashMap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import cn.com.do1.component.parse.ResultObject;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;

/**
 * 机关民主评议投票页面
 * 
 * @author LHQ
 * 
 */
public class BBSOfficeVoteActivity extends BaseListActivity implements
		ItemViewHandler {
	private int OFFICELIST_REQUESTCODE = 1;
	private Object status;
	private String isflag;
	private String alreadyCount;
	private String nonCount;
	private String voteId;
	private String voteOrg;
	private String voteOrgId;
	private int isVote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		voteId = getIntent().getStringExtra("voteId");
		aq.id(R.id.btn_all_office).getButton().setTextColor(Color.WHITE);

		LinearLayout layout = (LinearLayout) findViewById(R.id.button_bottom);
		layout.setVisibility(View.GONE);
		isflag = "0";
		status = "0";
		request();
		aq.id(R.id.btn_all_office).getView().setOnClickListener(this);
		aq.id(R.id.btn_office_noassess).getView().setOnClickListener(this);
		aq.id(R.id.btn_office_assessover).getView().setOnClickListener(this);
	}

	private void request() {
		setCusItemViewHandler(this);
		String[] keys = { "voteOrg", "voteStatus" };
		int[] ids = { R.id.tv_voteOrg, R.id.voteStatus };
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("voteUserId", constants.userInfo.getUserId());
		map.put("voteId", voteId);
		map.put("status", status);
		setAdapterParams(keys, ids, R.layout.bbs_office_vote_item, map);
	}

	@Override
	public void success(ResultObject data) {
		super.success(data);
		System.out.println("success...执行了。。");
		alreadyCount = data.getDataMap().get("alreadyCount").toString();
		nonCount = data.getDataMap().get("nonCount").toString();
		aq.id(R.id.btn_office_assessover).text("已评议(" + alreadyCount + ")");
		aq.id(R.id.btn_office_noassess).text("未评议(" + nonCount + ")");
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		System.out.println("handleItemView....执行了。。。");
		AQuery aq = new AQuery(itemView);
		// 是全部，才显示字
		if (isflag.equals("0")) {// 全部
			isVote = Integer.parseInt(listMap.get(position).get("isVote") + "");
			System.out.println("isVote:::" + isVote);
			if (isVote == 1) {
				aq.id(R.id.voteStatus).text("未评议");
				aq.id(R.id.voteStatus).textColor(Color.RED);
			} else if (isVote == 0) {
				aq.id(R.id.voteStatus).text("已评议");
				aq.id(R.id.voteStatus).textColor(Color.GRAY);
			}
		}
	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		if (isflag == "0") {// 全部
			isVote = Integer.parseInt(listMap.get(position).get("isVote") + "");
			System.out.println("isVote:::" + isVote);
		} else if (isflag == "1") {// 已
			isVote = 0;// 已投
		} else if (isflag == "2") {// 未投
			isVote = 1;// 未投
		}
		voteOrgId = listMap.get(position).get("id") + "";
		voteOrg = listMap.get(position).get("voteOrg") + "";
		Intent intent = new Intent(BBSOfficeVoteActivity.this,
				BBSOfficeAssessActivity.class);
		intent.putExtra("isVote", isVote);
		intent.putExtra("voteOrg", voteOrg);
		intent.putExtra("voteOrgId", voteOrgId);
		if (isVote == 1) {
			startActivityForResult(intent, OFFICELIST_REQUESTCODE);
		} else {
			startActivity(intent);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_all_office:
			System.out.println("onClick(),btn_all_office被执行了");
			aq.id(R.id.btn_all_office).getView()
					.setBackgroundResource(R.drawable.btn_office_group_green);
			aq.id(R.id.btn_all_office).getButton().setTextColor(Color.WHITE);
			aq.id(R.id.btn_office_noassess).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_office_noassess).getButton()
					.setTextColor(Color.GRAY);
			aq.id(R.id.btn_office_assessover).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_office_assessover).getButton()
					.setTextColor(Color.GRAY);
			aq.id(R.id.btn_all_office).getButton().setTextColor(Color.WHITE);
			isflag = "0";
			status = "0";
			maps.put("status", status);
			doSearch();// 请求全部
			break;
		case R.id.btn_office_assessover:
			System.out.println("onClick(),btn_office_noassess被执行了");
			aq.id(R.id.btn_all_office).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_all_office).getButton().setTextColor(Color.GRAY);
			aq.id(R.id.btn_office_assessover).getView()
					.setBackgroundResource(R.drawable.btn_office_group_green);
			aq.id(R.id.btn_office_assessover).getButton()
					.setTextColor(Color.WHITE);
			aq.id(R.id.btn_office_noassess).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_office_noassess).getButton()
					.setTextColor(Color.GRAY);
			isflag = "1";
			status = "2";// 已评
			maps.put("status", status);
			doSearch();
			break;
		case R.id.btn_office_noassess:
			System.out.println("onClick(),btn_office_assessover被执行了");
			aq.id(R.id.btn_all_office).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_all_office).getButton().setTextColor(Color.GRAY);
			aq.id(R.id.btn_office_assessover).getView()
					.setBackgroundResource(R.drawable.btn_office_group_gray);
			aq.id(R.id.btn_office_assessover).getButton()
					.setTextColor(Color.GRAY);
			aq.id(R.id.btn_office_noassess).getView()
					.setBackgroundResource(R.drawable.btn_office_group_green);
			aq.id(R.id.btn_office_noassess).getButton()
					.setTextColor(Color.WHITE);
			isflag = "2";
			status = "1";// 未评
			maps.put("status", status);
			doSearch();
			break;
		}
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "我要评议", 0, "", this, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.office_appraisalOrgVoteList);
		parentResId = R.layout.bbs_office_vote_list;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("onActivityResult:doSearch()执行了");
		if (requestCode == OFFICELIST_REQUESTCODE
				&& resultCode == BBSOfficeAssessActivity.OFFICEASSESS_RESULTCODE) {
			doSearch(); // 刷新
		}
	}
}
