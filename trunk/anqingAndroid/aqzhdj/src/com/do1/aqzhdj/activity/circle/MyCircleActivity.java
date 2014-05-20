package com.do1.aqzhdj.activity.circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ListView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.PageListView;
import com.do1.aqzhdj.activity.circle.adapter.MyCircleAdapter;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.util.Listenertool;

public class MyCircleActivity extends PageListView {

	public static String circleName;
	private String circleId;

	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> imagelistMap = new ArrayList<Map<String, Object>>();

	private ListView listView;
	private MyCircleAdapter adapter;

	private final static int TYPE = 1000;

	private boolean flag = false;
	ColorStateList color1;
	ColorStateList color2;

	public static boolean change = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_circle);

		init();
		request(1);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (change) {
			change = false;
			mlistMap.clear();
			request(2);
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	private void init() {
		if(null != getIntent()){
			circleId = getIntent().getExtras().getString("id");
			circleName = getIntent().getExtras().getString("circleName");
		}
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				circleName, R.drawable.bt_more, "", this, this);

		int[] resourceIds = { R.id.btMyCircle, R.id.btMyActivity, R.id.request,
				R.id.buttom2 };
		Listenertool.bindOnCLickListener(this, this, resourceIds);
		
		color1 = aq.id(R.id.btMyCircle).getButton().getTextColors();
		color2 = aq.id(R.id.btMyActivity).getButton().getTextColors();

		adapter = new MyCircleAdapter(MyCircleActivity.this, mlistMap);
		listView = (ListView) findViewById(R.id.list);
		initView(listView, adapter);

		Constants.type = 1;
		adapter.notifyDataSetChanged();
	}

	private void request(int type) {
		String url = Constants.SERVER_URL + "GetCircleDynamicInfoList.aspx";
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", constants.userInfo.getUserId());
		map.put("community_id", circleId);
		map.put("type", type + "");
		map.put("page_no", "1");
		map.put("page_count", "10");
		request(url, map);
	}

	private void request() {
		try {
			String url = Constants.SERVER_URL + "GetCircleAdvertList.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", constants.userInfo.getUserId());
			map.put("community_type", Constants.circleInfo.getCircletype());
			String param = Entryption.encode(xxtoJsonString(map));
			doRequestPostString(1, url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public String xxtoJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	@Override
	public void onClick(View v) {
		Button btCircle = aq.id(R.id.btMyCircle).getButton();
		Button btActivity = aq.id(R.id.btMyActivity).getButton();
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.btMyCircle:
			if (flag) {
				aq.id(R.id.guanggao).visible();
				flag = false;
				btActivity.setBackgroundDrawable(btCircle.getBackground());
				btActivity.setTextColor(color2);
				btCircle.setBackgroundResource(R.drawable.circle_midle_on);
				btCircle.setTextColor(color1);

				Constants.type = 1;
				aq.id(R.id.buttom1).visible();
				aq.id(R.id.buttom2).gone();
				mlistMap.clear();
				adapter.notifyDataSetChanged();
				request(1);
			}

			break;

		case R.id.btMyActivity:
			if (!flag) {
				flag = true;
				aq.id(R.id.guanggao).gone();
				btCircle.setBackgroundDrawable(btActivity.getBackground());
				btCircle.setTextColor(color2);
				btActivity.setBackgroundResource(R.drawable.circle_midle_on);
				btActivity.setTextColor(color1);

				Constants.type = 2;
				aq.id(R.id.buttom1).gone();
				aq.id(R.id.buttom2).visible();
				mlistMap.clear();
				adapter.notifyDataSetChanged();
				request(2);
			}

			break;

		case R.id.request:
			String content = aq.id(R.id.edit_request).getEditText().getText()
					.toString();
			if (content == null || "".equals(content)) {
				ToastUtil.showShortMsg(this, "回复内容不能为空");
				return;
			}
			try {
				String url = Constants.SERVER_URL
						+ "PublishCircleComments.aspx";
				Map<String, String> map = new HashMap<String, String>();
				map.put("community_id", circleId);
				map.put("user_id", constants.userInfo.getUserId());
				map.put("content", content);
				map.put("img", "");
				String param = Entryption.encode(toJsonString(map));
				setProgressMode(0);
				((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(MyCircleActivity.this
								.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				doRequestPostString(TYPE, url, param);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case R.id.buttom2:
			intent = new Intent(this, SendCircleActivity.class);
			intent.putExtra("circleId", circleId);
			startActivity(intent);
			break;

		case R.id.rightImage:
			intent = new Intent(this, CirclInfoActivity.class);
			intent.putExtra("circleId", circleId);
			startActivityForResult(intent, 1);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			finish();
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		if(requestId == 0){
			Message msg = new Message();
			msg.arg1 =101;
			handler.sendMessage(msg);
		}
		switch (requestId) {
		case TYPE:
			aq.id(R.id.edit_request).text("");
			ToastUtil.showShortMsg(this, "内容不能为空");
			mlistMap.clear();
			request(1);
			break;
			
		case 1:
			imagelistMap = resultObject.getListMap();
			Log.e(imagelistMap.toString());
			Gallery gallery = (Gallery) findViewById(R.id.guanggao);
			gallery.setAdapter(mAdapter);
			gallery.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(MyCircleActivity.this, GuanggaoDetailActivty.class);
					intent.putExtra("ad_id", imagelistMap.get(position).get("ad_id").toString());
					startActivity(intent);
				}
			});
			break;

		default:
			break;
		}
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		super.onExecuteFail(requestId, resultObject);
		switch (requestId) {
		case TYPE:
			ToastUtil.showShortMsg(this, "回复内容不能为空");
			break;

		default:
			break;
		}
	}

	@Override
	protected void changeAdapterData(List<Map<String, Object>> listMap) {
		mlistMap.addAll(listMap);
	}
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 101:
				request();
				break;

			default:
				break;
			}
		}
	};

	private BaseAdapter mAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return imagelistMap.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
			}
			AQuery mAq = new AQuery(convertView);
			String type = imagelistMap.get(position).get("circleType").toString().trim();
			if(Constants.circleInfo.getCircletype().trim().equals(type)){
				mAq.id(R.id.image).image(imagelistMap.get(position).get("photo_bar").toString(), true, true);
			}
			return convertView;
		}
		
	};

}
