package com.do1.zhdj.activity.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.widget.BaseAdapterWrapper;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.Constants;

/**
 * 个人积分排行列表、我的支部排行列表、我的排行列表 auth:YanFangqin data:2013-4-28 thzhd
 */
public class PersonIntegralRankActivity extends BaseListActivity implements
		ItemViewHandler {

	public Context context;
	private String orderType = "3";// 3:显示在我的，1：升序，2：降序
	private String from = "1";// 1：个人排名；2：支部排名
	public int scrollNo = 0;// 点击我的listView滚动到第几行
	public boolean isMineList = false;// 是我的列表请求，才控制listView滑动到我的列表
	List<Map<String, Object>> newslist;
//	private String[] keys = new String[] { "party_name", "total_int",
//			"rank_num" };
//	private int[] ids = new int[] { R.id.item1, R.id.item2, R.id.item3 };
	
	private String[] keys = new String[] { "name", "accumulativeScore","ranking" };
	private int[] ids = new int[] { R.id.item1, R.id.item2, R.id.item3 };
	String name;

	// 按我的
	private SimpleAdapter adapter;
	private ListView myListView;
	private List<Map<String, Object>> myDataList = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;
		aq = new AQuery(this);
		setCusItemViewHandler(this);
		from = getIntent().getStringExtra("from");
		if(from.equals("2")){
			keys = new String[] { "name", "accumulativeScore","branchRanking" };
		}

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// String type = SecurityUtil.encryptToDES(Constants.MICHI, "1");
		// String
		// ordertype=SecurityUtil.encryptToDES(Constants.MICHI,orderType);
		// String
		// userid=SecurityUtil.encryptToDES(Constants.MICHI,constants.userInfo.getUserId());
		// String organizationid=SecurityUtil.encryptToDES(Constants.MICHI,"");
		// map.put("type", "1".equals(from) ? "1" : "2");// 1：个人积分排名，2：所在支部排名
		map.put("type", from);
		map.put("orderType", orderType);
		map.put("userId", constants.userInfo.getUserId());
		if(from.equals("2")){
		map.put("organizationId", constants.userInfo.getBranchId());
		}
		// map.put("branch_id",
		// "1".equals(from) ? "" : constants.userInfo.getBranchId());
		// map.put(notRequestKey, "0");
		setAdapterParams(keys, ids, R.layout.rank_item, map);
		initItems();
		// fillDataMy();
	}

//	@Override
//	public void success(Object data) {
//		// TODO Auto-generated method stub
//		super.success(data);
//		System.out.println("成功了.............");
//		// Map<String, Object> map = resultObject.getDataMap();
//		try {
//			Map<String, Object> datamap = JsonUtil.json2Map(new JSONObject(data
//					+ ""));
//			// Object dataObject = getValueFromMap(resultMap, key, null);
//			System.out.println("datamap: " + datamap);
//			// Map<String, Object> userMap = JsonUtil.json2Map(new
//			// JSONObject(datamap
//			// .get("newsInfos") + ""));
//
//			Object object = datamap.get("pageData");
//			JSONArray array = new JSONArray(object.toString());
//			newslist = JsonUtil.jsonArray2List(array);
//
//			keys = new String[] { "name", "accumulativeScore",
//					"branchRanking" };
//			ids = new int[] { R.id.item1, R.id.item2, R.id.item3 };
//			SimpleAdapter adapter = new SimpleAdapter(this, newslist,
//					R.layout.rank_item, keys, ids);
//			mListView.setAdapter(adapter);
//			getFooterView().setVisibility(View.GONE);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 降序、升序请求
	 */
	public void fillDataRank() {
		method = getString(R.string.person_rank);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("type", from);// 1：个人积分排名，2：所在支部排名
		map.put("orderType", orderType);
		map.put("userId", constants.userInfo.getUserId());
		if(from.equals("2")){
			map.put("organizationId", constants.userInfo.getBranchId());
			}
		setAdapterParams(keys, ids, R.layout.rank_item, map);
		initItems();
		doSearch();
	}

	/**
	 * 按我的请求
	 */
	public void fillDataMy() {
		new Thread() {
			public void run() {
				String url = Constants.SERVER_RUL2
						+ getString(R.string.my_rank);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("type", from);// 1：个人积分排名，2：所在支部排名
				map.put("order_type", orderType);
				map.put("userId", constants.userInfo.getUserId());
				if(from.equals("2")){
					map.put("organizationId", constants.userInfo.getBranchId());
					}
				doRequest(2, url, map);
			};
		}.start();
	}

	public void initItems() {
		myListView = aq.id(R.id.myListView).getListView();
		ListenerHelper.bindOnCLickListener(this, this, R.id.btn1, R.id.btn2,
				R.id.btn3);
	}

	public void changeTabBg(int id) {
		if (id == R.id.btn1) {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1_on);
			aq.id(R.id.btn2).background(R.drawable.rank_btn2);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3);
		} else if (id == R.id.btn2) {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1);
			aq.id(R.id.btn2).background(R.drawable.rank_btn2_on);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3);
		} else {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1);
			aq.id(R.id.btn2).background(R.drawable.rank_btn2);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3_on);
		}
	}

	/**
	 * 获取我的位置
	 * 
	 * @return
	 */
	public void getMyselfLocation() {
		newslist=mSlpControll.getmListData();
		for (int i = 0; i < newslist.size(); i++) {
			System.out.println("name: " + constants.userInfo.getName());
			if (constants.userInfo.getName().equals(
					newslist.get(i).get("name").toString())) {
				scrollNo = i;
				Log.i("我排名位置：" + scrollNo);
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn1:
			if ("1".equals(orderType)) {
				return;
			}
			aq.id(R.id.listLayout).visible();
			aq.id(R.id.myListLayout).gone();
			isMineList = false;
			changeTabBg(v.getId());
			orderType = "1";
			fillDataRank();
			break;
		case R.id.btn2:
			if ("3".equals(orderType)) {
				return;
			}
			isMineList = true;
			changeTabBg(v.getId());
			aq.id(R.id.listLayout).visible();
			aq.id(R.id.myListLayout).gone();
			getMyselfLocation();
			myListView.setSelection(scrollNo < 5 ? 0 : (scrollNo - 4));
			orderType = "3";
			break;
		case R.id.btn3:
			if ("2".equals(orderType)) {
				return;
			}
			aq.id(R.id.listLayout).visible();
			aq.id(R.id.myListLayout).gone();
			isMineList = false;
			changeTabBg(v.getId());
			orderType = "2";
			fillDataRank();
			break;
		}
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "1".equals(from) ? "积分排行榜" : "积分排行榜", 0, "", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.person_rank);
		parentResId = R.layout.list_view_for_rank;
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		if (constants.userInfo.getUserId().equals(mSlpControll.getmListData().get(position).get("userId")+"")) {
			itemView.setBackgroundResource(R.color.jifenpaiming_bg2);
			((TextView)itemView.findViewById(R.id.item1)).setTextColor(getResources().getColor(R.color.caution_personcount));
			((TextView)itemView.findViewById(R.id.item2)).setTextColor(getResources().getColor(R.color.caution_personcount));
			((TextView)itemView.findViewById(R.id.item3)).setTextColor(getResources().getColor(R.color.caution_personcount));
		} else {
			itemView.setBackgroundResource(R.color.jifenpaiming_bg1);
			((TextView)itemView.findViewById(R.id.item1)).setTextColor(getResources().getColor(R.color.drak_grey));
			((TextView)itemView.findViewById(R.id.item2)).setTextColor(getResources().getColor(R.color.drak_grey));
			((TextView)itemView.findViewById(R.id.item3)).setTextColor(getResources().getColor(R.color.drak_grey));
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

		myDataList = resultObject.getListMap();
		adapter = new SimpleAdapter(context, myDataList, R.layout.rank_item,
				keys, ids);
		myListView.setAdapter(new BaseAdapterWrapper(adapter, viewHandler));

		isMineList = true;
		changeTabBg(R.id.btn2);
		aq.id(R.id.listLayout).gone();
		aq.id(R.id.myListLayout).visible();
		getMyselfLocation();
		myListView.setSelection(scrollNo < 5 ? 0 : (scrollNo - 4));
	}

	public cn.com.do1.component.widget.BaseAdapterWrapper.ItemViewHandler viewHandler = new cn.com.do1.component.widget.BaseAdapterWrapper.ItemViewHandler() {
		@Override
		public void handleItemView(BaseAdapter adapter, int position,
				View itemView, ViewGroup parent) {
			if (constants.userInfo.getUserId().equals(
					myDataList.get(position).get("userId").toString())) {
				itemView.setBackgroundResource(R.color.jifenpaiming_bg2);
				((TextView) itemView.findViewById(R.id.item1))
						.setTextColor(getResources().getColor(
								R.color.caution_personcount));
				((TextView) itemView.findViewById(R.id.item2))
						.setTextColor(getResources().getColor(
								R.color.caution_personcount));
				((TextView) itemView.findViewById(R.id.item3))
						.setTextColor(getResources().getColor(
								R.color.caution_personcount));
			} else {
				itemView.setBackgroundResource(R.color.jifenpaiming_bg1);
				((TextView) itemView.findViewById(R.id.item1))
						.setTextColor(getResources()
								.getColor(R.color.drak_grey));
				((TextView) itemView.findViewById(R.id.item2))
						.setTextColor(getResources()
								.getColor(R.color.drak_grey));
				((TextView) itemView.findViewById(R.id.item3))
						.setTextColor(getResources()
								.getColor(R.color.drak_grey));
			}
		}
	};

}
