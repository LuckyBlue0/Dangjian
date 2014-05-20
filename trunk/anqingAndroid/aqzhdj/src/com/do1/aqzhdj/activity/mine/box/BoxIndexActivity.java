package com.do1.aqzhdj.activity.mine.box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.com.do1.component.widget.BaseAdapterWrapper;
import cn.com.do1.component.widget.BaseAdapterWrapper.ItemViewHandler;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.widght.pager.SimpleViewBinder;

/**
 * 百宝箱首页
 * auth:YanFangqin
 * data:2013-4-23
 * thzhd
 */
public class BoxIndexActivity extends BaseActivity implements ItemViewHandler,OnItemClickListener{

	private SimpleAdapter mAdapter;
	private int[] imgIds = {R.drawable.box_btn1,R.drawable.box_btn2,R.drawable.box_btn3,R.drawable.box_btn4,R.drawable.box_btn5,R.drawable.box_btn7,R.drawable.box_btn6,R.drawable.near_myself_icon,R.drawable.box_btn6};
	private ListView listView;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_for_activity);
		context = this;
		aq = new AQuery(this);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "百宝箱", 0,"", null, null);
		initItem();
		setListView();
	}
	
	public void initItem(){
		listView = aq.id(android.R.id.list).getListView();
		listView.setOnItemClickListener(this);
	}
	
	public void setListView(){
		String[] titles = {"党章党徽党旗","入党誓词","党史上的今天","礼仪歌曲","党务流程指引","党务公文模版","党情速递","身边党史","政策文件"};
		String[] from = { "title"};
		int[] to = { R.id.title };
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < titles.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", titles[i]);
			data.add(map);
		}
		mAdapter = new SimpleAdapter(this, data, R.layout.box_item, from, to);
		Bitmap presetBtiMap = BitmapFactory.decodeResource(getResources(), R.drawable.item_default_image);//加默认图片
		mAdapter.setViewBinder(new SimpleViewBinder(presetBtiMap));//提供RatingBar以及图片加载
		listView.setAdapter(new BaseAdapterWrapper(mAdapter,this));
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		itemView.findViewById(R.id.image).setBackgroundResource(imgIds[position]);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		switch (position) {
			case 0:
				intent.setClass(context, BoxDangActivity.class);
				break;
			case 1:
				intent.setClass(context, DangLogoActivity.class);
				break;
			case 2:
				intent.setClass(context, DangHistoryActivity.class);	
				break;
			case 3:
				intent.setClass(context, BoxSingActivity.class);
				AppManager.singUrl = getString(R.string.box_sing2);
				intent.putExtra("type", "1");
				break;
			case 4:
				intent.setClass(context, BoxSingActivity.class);
				AppManager.singUrl = getString(R.string.box_sing);
				intent.putExtra("type", "2");
				break;
			case 5:
				intent.setClass(context, DangwgwListActivity.class);
				break;
			case 6:
				intent.setClass(context, DangqsdListActivity.class);
				break;
			case 7:
				intent.setClass(context, ShenbdsListActivity.class);
				break;
			case 8:
				intent.setClass(context, ZcwjListActivity.class);
				break;
		}
		startActivity(intent);
	}
}
