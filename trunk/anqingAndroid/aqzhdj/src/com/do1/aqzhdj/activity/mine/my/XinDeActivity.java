package com.do1.aqzhdj.activity.mine.my;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.com.do1.component.util.ListenerHelper;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.DateTool;
import com.do1.aqzhdj.util.ImageViewTool;

public class XinDeActivity extends BaseListActivity implements ItemViewHandler {
	
	Intent intent;
	LinearLayout button_bottom;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		button_bottom=(LinearLayout)findViewById(R.id.button_bottom);
		button_bottom.setVisibility(View.GONE);
		setCusItemViewHandler(this);
		String keys[] = new String[] { "userName", "createTime", "sourceDesc",
				"content", "createTime" };
		int ids[] = new int[] { R.id.name, R.id.time, R.id.laizi, R.id.content };
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("activityId", getIntent().getStringExtra("id"));
		System.out.println("type: " + getIntent().getStringExtra("type"));
		//// 0 心语  1 心得
		if("3".equals(getIntent().getStringExtra("type"))){  // 判断传过来的type  4: 志愿活动 3 
		map.put("type", "1");
		}else if("4".equals(getIntent().getStringExtra("type"))){
			map.put("type", "0");
		}

		setAdapterParams(keys, ids, R.layout.xinde_item, map);
		ListenerHelper.bindOnCLickListener(this, this, R.id.leftImage,R.id.rightImage);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		doSearch();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.leftImage:
			finish();
			break;
		case R.id.rightImage:
			intent=new Intent(this,XiexinDeActivity.class);
			System.out.println("title: " + getIntent().getStringExtra("title"));
			intent.putExtra("title", getIntent().getStringExtra("title"));
			intent.putExtra("id", getIntent().getStringExtra("id"));
			if("3".equals(getIntent().getStringExtra("type"))){
				intent.putExtra("type", "1");
			}else if("4".equals(getIntent().getStringExtra("type"))){
				intent.putExtra("type", "0");
			}
			startActivity(intent);
			break;
		}
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String time=mSlpControll.getmListData().get(position).get("createTime").toString();
		
		String formatStr = "yyyy-MM-dd HH:mm";
		aq = new AQuery(itemView);
		aq.id(R.id.time).text(
				DateTool.StringToString(time, formatStr));
		
		
		ImageView img=(ImageView)itemView.findViewById(R.id.touxiang);
		String path=mSlpControll.getmListData().get(position).get("portraitPic").toString();
		ImageViewTool.getAsyncImageBg(path, img, 0);
	}

	@Override
	public void setHeadMethod() {
		// TODO Auto-generated method stub
		if("3".equals(getIntent().getStringExtra("type"))){
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
					"", "查看心得体会", R.drawable.btn_head_4, "写心得", null, null);
		}else if("4".equals(getIntent().getStringExtra("type"))){
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
					"", "查看志愿心语", R.drawable.btn_head_4, "写心语", null, null);
		}
		
	}

	@Override
	public void setRequestMethod() {
		// TODO Auto-generated method stub
		method = getString(R.string.xinde);
	}

}
