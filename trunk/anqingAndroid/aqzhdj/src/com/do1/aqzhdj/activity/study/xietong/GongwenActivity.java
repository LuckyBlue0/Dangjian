package com.do1.aqzhdj.activity.study.xietong;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.common.WapViewActivity2;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.Constants;

public class GongwenActivity extends BaseListActivity implements
		ItemViewHandler {
	Context context;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.study_xietong_gongwen);

		context = this;

		// 请求列表
		String[] keys = new String[] { "title", "wenhao", "nigaodanwei",
				"fawendate" };
		int[] ids = new int[] { R.id.title, R.id.txt_wenhao, R.id.author,
				R.id.sendTime };
		setCusItemViewHandler(this);
		Map<String, Object> map = new HashMap<String, Object>();
		setAdapterParams(keys, ids, R.layout.masses_xietong_item, map);

	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AQuery a = new AQuery(itemView);
		a.find(R.id.txt_wenhao).getTextView().setVisibility(View.VISIBLE);
		TextView txttitle = a.find(R.id.title).getTextView();
		TextView txtwenhao = a.find(R.id.txt_wenhao).getTextView();
		TextView txtauthor = a.find(R.id.author).getTextView();
		TextView txttime = a.find(R.id.sendTime).getTextView();

		String title = mSlpControll.getmListData().get(position).get("title")
				.toString();
		String wenhao = mSlpControll.getmListData().get(position).get("wenhao")
				.toString();
		String author = mSlpControll.getmListData().get(position)
				.get("nigaodanwei").toString();
		String time = mSlpControll.getmListData().get(position)
				.get("fawendate").toString();

		txttitle.setText(Html.fromHtml(title));
		txtwenhao.setText(Html.fromHtml(wenhao));
		txtauthor.setText(Html.fromHtml(author));
		txttime.setText(Html.fromHtml(time));
	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		// http://219.136.91.63:8200/anqingOA/gongwenguanliDetail.mobo?iswap=1&cmd=request
		// &url=editId%3D13958%26tableId%3D178%26receiveFileSendFileUnit%3D%E7%94%AF%E5%82%9B%E6%96%82%E6%90%B4%E0%80%BF%E9%8D%99%E5%91%8A%E7%A1%B6%E7%81%9E%E2%82%AC%E5%A8%89%E6%9B%9E%E5%9F%97%E7%80%B9%EF%BD%84%E7%B4%B6%E7%BB%89%E0%80%BF
		// http://219.136.91.63:8200/anqingOA/gongwenguanli.mobo?iswap=1&cmd=request
		String url = mSlpControll.getmListData().get(position).get("url")
				.toString();
		// map.put("userName", Constants.xietongname);
		// map.put("userPassword", Constants.xietongpwd);
		url = "http://219.136.91.63:8200/anqingOA/gongwenguanliDetail.mobo?iswap=1&cmd=request&userName="
				+ Constants.xietongname
				+ "&userPassword="
				+ Constants.xietongpwd + "&url=" + url;
		Intent intent = new Intent(context, WapViewActivity2.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("url", url);
		intent.putExtra("title", getString(R.string.gongwen) + "详情");
		intent.putExtra("typeid", "1");
		startActivity(intent);
	}

	@Override
	public void setHeadMethod() {
		// TODO Auto-generated method stub
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.gongwen), 0, "", this, null);
	}

	@Override
	public void setRequestMethod() {
		// TODO Auto-generated method
		method = getString(R.string.gongwen_list);
		setQueryUrl(method);
	}
}
