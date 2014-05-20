package com.do1.zhdj.activity.mine;

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
import android.widget.SimpleAdapter;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.common.WapViewActivity;
import com.do1.zhdj.activity.parent.BaseActivity;

/**
 * 无线生活
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class FreeLifeActivity extends BaseActivity implements OnItemClickListener{

	private SimpleAdapter mAdapter;
	private ListView listView;
	private String[] urls = {"http://guangzhou.wap.wxcs.cn/m/wxzw?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/shfw?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/pxjy?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/qzjy?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/jtcx?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/lyzn?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/gwxx?areaName=guangzhou&jumpType=1",
							 "http://guangzhou.wap.wxcs.cn/m/ylbj?areaName=guangzhou&jumpType=1"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_for_activity);
		aq = new AQuery(this);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "无线生活",0, "", null, null);

		initItem();
		setListView();
	}

	public void initItem() {
		listView = aq.id(android.R.id.list).getListView();
		listView.setOnItemClickListener(this);
	}

	public void setListView() {
		String[] titles = { "无线政务", "生活服务", "教育培训", "求职就业", "交通出行","旅游指南","购物休闲", "医疗保健" };
		String[] from = { "name" };
		int[] to = { R.id.name,  };
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < titles.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", titles[i]);
			data.add(map);
		}
		mAdapter = new SimpleAdapter(this, data,
				R.layout.free_life_item, from, to);
		listView.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("url", urls[position]);
		intent.putExtra("title", "无线生活");
		startActivity(intent);
	}
}
