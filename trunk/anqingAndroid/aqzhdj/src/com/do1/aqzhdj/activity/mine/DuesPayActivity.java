package com.do1.aqzhdj.activity.mine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.DateTool;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.util.Listenertool;

public class DuesPayActivity extends BaseActivity{

	private static final int PAY_SEARCH = 0;

	private List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

	// private Spinner timeSpinner;
	// private Spinner typeSpinner;

	private Dialog dialogTime;
	private Dialog dialogType;

	private ListView listView;
	private AQuery aq = new AQuery(this);

	private String time;
	private int type;
	private String[] typearray = { "全部", "已缴纳", "未缴纳" };
	private String[] timearray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_pay);
		init();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"党费查询", 0, "", this, null);

		int[] onclickListnerIds = { R.id.search, R.id.time, R.id.type };
		Listenertool.bindOnCLickListener(this, this, onclickListnerIds);

		listView = aq.id(R.id.listview).getListView();
		listView.setAdapter(mBaseAdapter);
		
		
		time = DateTool.dateToString(new Date(), "yyyy");
		aq.id(R.id.time).text(time);
		type = 0;
		aq.id(R.id.type).text(typearray[0]);
		try {
			request();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.search:
			try {
				request();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case R.id.time:
			int count = Integer.valueOf(time);
			int index = 1901;
			int len = count - index;
			timearray = new String[len];
			for (int i = 0; i < len; i++) {
				timearray[i] = String.valueOf(index+(len - i));
			}
			dialogTime = new AlertDialog.Builder(this).setTitle("设置时间")
					.setItems(timearray, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							time = timearray[which];
							aq.id(R.id.time).text(time);
						}
					}).setNegativeButton("取消", null).show();
			break;

		case R.id.type:
			dialogType = new AlertDialog.Builder(this).setTitle("设置类别")
			.setItems(typearray, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					type = which;
					aq.id(R.id.type).text(typearray[which]);
				}
			}).setNegativeButton("取消", null).show();
			break;

		default:
			break;
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		Log.e(resultObject.getDataMap().toString());
		Map<String, Object> map = resultObject.getDataMap();
		listMap = JsonUtil.jsonArray2List(map.get("list").toString());
		mBaseAdapter.notifyDataSetChanged();
		if(listMap.isEmpty()){
			aq.id(R.id.nodata).visible();
		}else{
			aq.id(R.id.nodata).gone();
		}
	}
	
	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		super.onExecuteFail(requestId, resultObject);
		listMap.clear();
		mBaseAdapter.notifyDataSetChanged();
	}
	
	private void request() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", constants.userInfo.getUserId());
		map.put("year", time);
		map.put("status", type);
		String url = Constants.SERVER_URL
				+ getResources().getString(R.string.dues_pay);
		String param = Entryption.encode(toJsonString(map));
		doRequestPostString(PAY_SEARCH, url, param);
	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	private BaseAdapter mBaseAdapter = new BaseAdapter() {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(DuesPayActivity.this)
						.inflate(R.layout.dues_pay_item, null);
			}
			AQuery mAq = new AQuery(convertView);
			mAq.id(R.id.itemName).text(constants.userInfo.getName());
			mAq.id(R.id.itemTime).text(
					listMap.get(position).get("month").toString() + "月");
			mAq.id(R.id.itemCount).text("￥"+
					listMap.get(position).get("money").toString());
			int typeNum = Integer.valueOf(listMap.get(position).get("status")
					.toString());
			mAq.id(R.id.itemStatus).text(typearray[typeNum]);
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public int getCount() {
			return listMap.size();
		}
	};

}
