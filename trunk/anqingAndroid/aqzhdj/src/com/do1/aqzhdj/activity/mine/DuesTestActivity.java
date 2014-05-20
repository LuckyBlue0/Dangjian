package com.do1.aqzhdj.activity.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.adapter.DuesTestAdapter;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.widght.MyListView;

public class DuesTestActivity extends BaseActivity {

	public List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();

	private MyListView listView;
	private DuesTestAdapter adapter;

	public static int exam_num;
	public static List<Map<String, Object>> ids = new ArrayList<Map<String, Object>>();
	
	public static String afterIds;
	public static List<Map<String, Object>> afterListMap = new ArrayList<Map<String, Object>>();

	private String examIds = "";
	private String optionIds = "";
	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_test);
		ListenerHelper.bindOnCLickListener(this, this, R.id.rightImage);
		init();

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("user_id", constants.userInfo.getUserId());
//		request(0, "GetMyExaminTopicList.aspx", map);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			Toast.makeText(this, "提交成功", 1).show();
			finish();
			break;

		}
	}


	private void init() {
//		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
//				"", "学习测试", 0, "", this, null);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "在线考试",
				R.drawable.btn_head_4, "提交", this, this);
//		listView = (MyListView) findViewById(R.id.list);
//
//		Button btSubmit = (Button) findViewById(R.id.submit);
//		btSubmit.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (flag) {
//					
//					for(Map<String, Object> id : ids){
//						if(id.get("option_id") == null || "".equals(id.get("option_id")+"")){
//							ToastUtil.showLongMsg(DuesTestActivity.this, "请将题目答完整");
//							return;
//						}
//					}
//					flag = false;
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("user_id", constants.userInfo.getUserId());
//					afterIds = toJson(ids);
//					map.put("exam",afterIds );
//					request(1, "ExaminTopicSubimt.aspx", map);
//				}
//			}
//		});
	}

//	public String toJson(List<Map<String, Object>> listMap) {
//		String json = "";
//		for (int i = 0; i < listMap.size(); i++) {
//			JSONObject jsonObject = new JSONObject(ids.get(i));
//			if (i == 0) {
//				json = "[" + jsonObject.toString();
//			} else if (i == (listMap.size() - 1)) {
//				json = json + ","+jsonObject.toString() + "]";
//			} else {
//				json = json + ","+jsonObject.toString();
//			}
//		}
//		return json;
//	}
//
//	private void request(int requestId, String urlFomat, Map<String, Object> map) {
//		try {
//			String url = Constants.SERVER_URL + urlFomat;
//			Log.e(toJsonString(map));
//			String param = Entryption.encode(toJsonString(map));
//			setProgressMode(3);
//			doRequestPostString(requestId, url, param);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	};
//
//	public String toJsonString(Map<String, Object> map) {
//		JSONObject json = new JSONObject(map);
//		String data = json.toString().replace("\\", "").replace("\"[", "[")
//				.replace("]\"", "]").replace(",\"}", "\"}");
//		return data;
//	}
//
//	@Override
//	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
//		switch (requestId) {
//		case 0:
//			mlistMap = JsonUtil.jsonArray2List(resultObject.getDataMap()
//					.get("list").toString());
//			if (mlistMap.isEmpty()) {
//				ToastUtil.showShortMsg(DuesTestActivity.this, "暂无考试信息");
//				flag = false;
//				return;
//			}
//			flag = true;
//			adapter = new DuesTestAdapter(DuesTestActivity.this, mlistMap,
//					R.layout.dues_test_item,0);
//			listView.setAdapter(adapter);
//			adapter.notifyDataSetChanged();
//			break;
//
//		case 1:
//			aq.id(R.id.submit).gone();
//			aq.id(R.id.tishi).visible().text(resultObject.getDesc());
//			afterListMap = JsonUtil.jsonArray2List(resultObject.getDataMap()
//					.get("list").toString());
//			adapter = new DuesTestAdapter(DuesTestActivity.this, mlistMap, afterListMap, afterIds, true, R.layout.dues_test_item,1);
//			listView.setAdapter(adapter);
//			adapter.notifyDataSetChanged();
////			ToastUtil.showShortMsg(DuesTestActivity.this,
////					resultObject.getDesc());
////			finish();
//			break;
//
//		default:
//			break;
//		}
//	}
//	
//	@Override
//	public void onNetworkError(int requestId) {
//		super.onNetworkError(requestId);
//		flag = true;
//	}
//
//	@Override
//	public void onExecuteFail(int requestId, ResultObject resultObject) {
//		flag = true;
//		ToastUtil.showShortMsg(DuesTestActivity.this, resultObject.getDesc());
//		finish();
//	}

}
