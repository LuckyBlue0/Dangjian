package com.do1.aqzhdj.activity.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.widget.BaseAdapterWrapper;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;

/**
 * TODO:支部排名
 * User:YanFangqin
 * Date:2013-6-3
 * ProjectName:thzhd
 */
public class BranchRankActivity extends BaseListActivity implements ItemViewHandler{

	public Context context;
	private String orderType = "0";// 0:显示在我的，1：升序，2：降序
	public int scrollNo = 0;// 点击我的listView滚动到第几行
	public boolean isMineList = false;//是我的列表请求，才控制listView滑动到我的列表
	
	private String[] keys = new String[] { "branch_name", "total_int", "rank_num" };
	private int[] ids = new int[] { R.id.item1, R.id.item2, R.id.item3 };
	
	//按我的
	private SimpleAdapter adapter;
	private ListView myListView;
	private List<Map<String, Object>> myDataList = new ArrayList<Map<String,Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;
		aq = new AQuery(this);
		setCusItemViewHandler(this);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_type", orderType);
		map.put(notRequestKey, "0");
		setAdapterParams(keys, ids, R.layout.rank_item, map);
		initItems();
		fillDataMy();
	}
	
	/**
	 * 降序、升序请求
	 */
	public void fillDataRank(){
		method = getString(R.string.branch_search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_type", orderType);
		setAdapterParams(keys, ids, R.layout.rank_item, map);
		initItems();
		doSearch();
	}

	/**
	 * 按我的请求
	 */
	public void fillDataMy() {
		new Thread(){
			public void run() {
				String url = SERVER_URL + getString(R.string.my_branch_search);
				Map<String, String> map = new HashMap<String, String>();
				map.put("branch_id", constants.userInfo.getBranchId());
				doRequest(2, url, map);
			};
		}.start();
	}

	public void initItems() {
		myListView = aq.id(R.id.myListView).getListView();
		ListenerHelper.bindOnCLickListener(this, this, R.id.btn1, R.id.btn2,R.id.btn3);
	}

	public void changeTabBg(int id) {
		if (id == R.id.btn1) {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1_on);
			aq.id(R.id.btn2).background(R.drawable.rank_for_brank);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3);
		} else if (id == R.id.btn2) {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1);
			aq.id(R.id.btn2).background(R.drawable.rank_for_brank_on);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3);
		} else {
			aq.id(R.id.btn1).background(R.drawable.rank_btn1);
			aq.id(R.id.btn2).background(R.drawable.rank_for_brank);
			aq.id(R.id.btn3).background(R.drawable.rank_btn3_on);
		}
	}

	/**
	 * 获取我的位置
	 * 
	 * @return
	 */
	public void getMyselfLocation() {
		for (int i = 0; i < myDataList.size(); i++) {
			if (constants.userInfo.getBranchName().equals(myDataList.get(i).get("branch_name").toString())) {
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
			if("1".equals(orderType)){
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
			if("0".equals(orderType)){
				return;
			}
			isMineList = true;
			changeTabBg(v.getId());
			aq.id(R.id.listLayout).gone();
			aq.id(R.id.myListLayout).visible();
			getMyselfLocation();
			myListView.setSelection(scrollNo < 5 ? 0 : (scrollNo - 4));
			orderType = "0";
			break;
		case R.id.btn3:
			if("2".equals(orderType)){
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
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "组织排名", 0, "", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.branch_search);
		parentResId = R.layout.list_view_for_rank2;
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {

		if (constants.userInfo.getBranchId().equals(mSlpControll.getmListData().get(position).get("branch_id")+"")) {
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
		adapter = new SimpleAdapter(context, myDataList, R.layout.rank_item, keys, ids);
		myListView.setAdapter(new BaseAdapterWrapper(adapter,viewHandler));
		
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
			if (constants.userInfo.getBranchId().equals(myDataList.get(position).get("branch_id").toString())) {
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
	};

}
