package com.do1.aqzhdj.activity.bbs;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.ImageViewTool;

/**
 * 查看结果页面
 * 
 * @author LHQ
 * 
 */
public class BBSPartyerVoteResultActivity extends BaseListActivity implements
		ItemViewHandler {
	private String id;
	private Map<String, Object> data;
	private Map<String, Object> result;
	private List<Map<String, Object>> list;
	private Map<String, Object> dataMap;
	private int totalCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
		LinearLayout layout = (LinearLayout) findViewById(R.id.button_bottom);
		layout.setVisibility(View.GONE);

		setCusItemViewHandler(this);
		String[] keys = { "userImgPath", "userName", "result1", "result2" };
		int[] ids = { R.id.person_img, R.id.person_name, R.id.ticket_number,
				R.id.person_percent };
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);// 投票主题id
		setAdapterParams(keys, ids, R.layout.bbs_vote_result_item, map);
	}

	@Override
	public void success(ResultObject data) {
		super.success(data);
		dataMap = data.getDataMap();
		//System.out.println("dataMap:" + dataMap);
		JSONObject object1 = (JSONObject) dataMap.get("result");
		JSONArray object2 = (JSONArray) dataMap.get("list");
		//System.out.println("object2:" + object2);

		result = JsonUtil.json2Map(object1);
		list = JsonUtil.jsonArray2List(object2 + "");
		System.out.println("list:" + list);
		totalCount = Integer.parseInt(result.get("totalCount").toString());
		aq.id(R.id.result_title).text(result.get("voteTopic").toString());
		aq.id(R.id.result_count).text(result.get("totalCount").toString());
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		System.out.println("handleItemView被执行了");
		AQuery aQuery = new AQuery(itemView);
		aQuery.id(R.id.person_name).text(
				list.get(position).get("userName").toString());
		aQuery.id(R.id.ticket_number).text(
				list.get(position).get("result1").toString());
		aQuery.id(R.id.person_percent).text(
				list.get(position).get("result2").toString());
		ImageView img = aQuery.id(R.id.person_img).getImageView();

		String imgPath = mSlpControll.getmListData().get(position)
				.get("userImgPath").toString();
		ImageViewTool.getAsyncImageBg(imgPath, img, R.drawable.default_m_104);
		ProgressBar bar = aQuery.id(R.id.person_progress).getProgressBar();
		bar.setMax(totalCount);
		bar.setProgress(Integer.parseInt(list.get(position).get("result1")
				.toString()));
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "查看结果", 0, "", this, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.searchVoteResult);
		parentResId = R.layout.bbs_vote_result;
	}

	// @Override
	// public void onExecuteSuccess(int requestId, ResultObject resultObject) {
	// super.onExecuteSuccess(requestId, resultObject);
	// System.out.println("onExecuteSuccess被执行了");
	// Map<String, Object> dataMap = resultObject.getDataMap();
	// // dataMap = data.getDataMap();
	// System.out.println("dataMap:"+dataMap);
	// JSONObject object1 = (JSONObject) dataMap.get("result");
	// JSONArray object2 = (JSONArray) dataMap.get("list");
	// System.out.println("object2:"+object2);
	//
	// result = JsonUtil.json2Map(object1);
	// list = JsonUtil.jsonArray2List(object2 + "");
	// System.out.println("list:"+list);
	// totalCount = Integer.parseInt(result.get("totalCount").toString());
	// aq.id(R.id.result_title).text(result.get("voteTopic").toString());
	// aq.id(R.id.result_count).text(totalCount + "");
	//
	// // new SimpleAdapter(this, data, resource, from, to)
	// // aq.id(R.id.person_result_list).adapter(adapter);
	// }
}
