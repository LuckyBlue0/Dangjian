package com.do1.zhdj.activity.mine;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import cn.com.do1.component.util.JsonUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.DateTool;

/**
 * 个人积分详情
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class PersonIntegralDetailActivity extends BaseListActivity implements ItemViewHandler{

	public Context context;
	List<Map<String, Object>> newslist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		aq = new AQuery(this);
		setCusItemViewHandler(this);
		String[] keys = new String[]{"scoreDesc","score","getTime"};
        int[] ids = new int[]{R.id.item1,R.id.item2,R.id.item3};
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("type", "1");
        map.put("userId", constants.userInfo.getUserId());
//        map.put("organizationId", "");
        setAdapterParams(keys,ids,R.layout.person_integral_item,map);
        aq.id(R.id.tabOne).text(Html.fromHtml(constants.userInfo.getUserName() + "的个人总积分(当天积分不计入个人总积分)：" + "<font color='#c90100'>" + getIntent().getStringExtra("gerenzongjifen")+"</font>"));
	}
	
	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"","积分明细", 0,"", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.person_integral_detail);
		parentResId = R.layout.list_view_for_person_integral_detail;
	}
	
//	@Override
//	public void success(Object data) {
//		// TODO Auto-generated method stub
//		super.success(data);
//		System.out.println("成功了.............");
////		Map<String, Object> map = resultObject.getDataMap();
//		try {
//			Map<String, Object> datamap= JsonUtil.json2Map(new JSONObject(data+""));
//			// Object dataObject = getValueFromMap(resultMap, key, null);
//			System.out.println("datamap: " + datamap);
////			Map<String, Object> userMap = JsonUtil.json2Map(new JSONObject(datamap
////					.get("newsInfos") + ""));
//			
//			Object object = datamap.get("pageData");
//			JSONArray array = new JSONArray(object.toString());
//			newslist = JsonUtil.jsonArray2List(array);
//			
//			
//			String[] keys = new String[]{"scoreFrom","score","getTime"};
//	        int[] ids = new int[]{R.id.item1,R.id.item2,R.id.item3};
//	        SimpleAdapter adapter = new SimpleAdapter(this, newslist, R.layout.person_integral_item, keys, ids);
//	        mListView.setAdapter(adapter);
//	        getFooterView().setVisibility(View.GONE);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		
		TextView date = (TextView) itemView.findViewById(R.id.item3);
			
//			String time=mSlpControll.getmListData().get(position).get("createTime").toString();
			String s=date.getText().toString();
			String formatStr = "yyyy-MM-dd";
			aq = new AQuery(itemView);
			aq.id(R.id.item3).text(
					DateTool.StringToString(s, formatStr));
	}

}
