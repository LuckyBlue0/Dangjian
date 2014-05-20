package com.do1.aqzhdj.activity.infomation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.widget.BaseAdapterWrapper;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.common.WapViewActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.DateTool;
import com.do1.aqzhdj.util.ImageViewTool;
import com.do1.aqzhdj.util.StringUtil;

/**
 * 资讯下的分类，我的公告，新闻等等 auth:YanFangqin data:2013-4-19 thzhd
 */
public class NoticeListActivity extends BaseListActivity implements
		ItemViewHandler {

	private Context context;
	public static String parenttype = "1";
	public static String type = "1";
	public String newsInfoId;
	List<Map<String, Object>> newslist;
	List<Map<String, Object>> erjilist = new ArrayList<Map<String, Object>>();
	List<Button> buttonlist = new ArrayList<Button>();

	private SimpleAdapter adapter;
	private ListView myListView;
	private List<Map<String, Object>> myDataList = new ArrayList<Map<String, Object>>();
	String[] keys = new String[] { "title", "digest", };
	int[] ids = new int[] { R.id.title, R.id.digest };
	Button btn1, btn2, btn3, btn4, btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		newsInfoId = getIntent().getStringExtra("newsInfoId");
		// type = getIntent().getStringExtra("newsInfoType");
		// System.out.println("NoticeList.type: " + type);
		myListView = aq.id(R.id.myListView).getListView();
		btn1 = aq.find(R.id.btn1).getButton();
		btn2 = aq.find(R.id.btn2).getButton();
		btn3 = aq.find(R.id.btn3).getButton();
		btn4 = aq.find(R.id.btn4).getButton();
		btn5 = aq.find(R.id.btn5).getButton();

		if (Constants.erjilist.size() > 0) {
			aq.find(R.id.btn1).text(
					Constants.erjilist.get(0).get("title").toString());
			aq.find(R.id.btn2).text(
					Constants.erjilist.get(1).get("title").toString());
			aq.find(R.id.btn3).text(
					Constants.erjilist.get(2).get("title").toString());
			aq.find(R.id.btn4).text(
					Constants.erjilist.get(3).get("title").toString());
			aq.find(R.id.btn5).text(
					Constants.erjilist.get(4).get("title").toString());

			// 请求列表
			setCusItemViewHandler(this);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("newsInfoTypeId",
					Constants.erjilist.get(0).get("newsInfoTypeId")); // 资讯类
			setAdapterParams(keys, ids, R.layout.notice_item3, map);

			ListenerHelper.bindOnCLickListener(this, this, R.id.btn1,
					R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5);
		}
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		String time = mSlpControll.getmListData().get(position).get("pushTime")
				.toString();
		String formatStr = "yyyy-MM-dd HH:mm";
		aq = new AQuery(itemView);
		if(!"".equals(time))
		aq.id(R.id.sendTime).text(DateTool.StringToString(time, formatStr));

		ImageView img = (ImageView) itemView.findViewById(R.id.pic);
		// 获取压缩的图片
		String resizeImgPath = mSlpControll.getmListData().get(position)
				.get("imgPath").toString();
		System.out.println("resizeImgPath: " + resizeImgPath);
		System.out.println("server: " + Constants.SERVER_IMG + resizeImgPath);
		// 加载图片
		if (!"".equals(resizeImgPath)) {
			ImageViewTool.getAsyncImageBg(resizeImgPath, img, 0);
		} else {
			img.setVisibility(View.GONE);
		}
		if (mSlpControll.getmListData().get(position).get("buyTop") != null) {
			if ("1".equals(mSlpControll.getmListData().get(position)
					.get("buyTop").toString())) {
				itemView.findViewById(R.id.isTop).setVisibility(View.VISIBLE);
			} else {
				itemView.findViewById(R.id.isTop).setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 降序、升序请求
	 */
	public void search() {

		doSearch();
	}

	// 选择图片
	public void selectbg(List<Button> list) {
		for (int i = 0; i < list.size(); i++) {
			Button bt = list.get(i);
			if (i == 0) {
				bt.setBackgroundResource(R.drawable.bg_on);
			} else {
				bt.setBackgroundResource(R.drawable.bg);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn1:
			buttonlist.clear();
			buttonlist.add(btn1);
			buttonlist.add(btn2);
			buttonlist.add(btn3);
			buttonlist.add(btn4);
			buttonlist.add(btn5);
			selectbg(buttonlist);
			maps.put("newsInfoTypeId",
					Constants.erjilist.get(0).get("newsInfoTypeId")); // 资讯类
			search();
			break;
		case R.id.btn2:
			buttonlist.clear();
			buttonlist.add(btn2);
			buttonlist.add(btn1);
			buttonlist.add(btn3);
			buttonlist.add(btn4);
			buttonlist.add(btn5);
			selectbg(buttonlist);
			maps.put("newsInfoTypeId",
					Constants.erjilist.get(1).get("newsInfoTypeId")); // 资讯类
			search();
			break;
		case R.id.btn3:
			buttonlist.clear();
			buttonlist.add(btn3);
			buttonlist.add(btn1);
			buttonlist.add(btn2);
			buttonlist.add(btn4);
			buttonlist.add(btn5);
			selectbg(buttonlist);
			maps.put("newsInfoTypeId",
					Constants.erjilist.get(2).get("newsInfoTypeId")); // 资讯类
			search();
			break;
		case R.id.btn4:
			buttonlist.clear();
			buttonlist.add(btn4);
			buttonlist.add(btn2);
			buttonlist.add(btn3);
			buttonlist.add(btn1);
			buttonlist.add(btn5);
			selectbg(buttonlist);
			maps.put("newsInfoTypeId",
					Constants.erjilist.get(3).get("newsInfoTypeId")); // 资讯类
			search();
			break;
		case R.id.btn5:
			buttonlist.clear();
			buttonlist.add(btn5);
			buttonlist.add(btn2);
			buttonlist.add(btn3);
			buttonlist.add(btn4);
			buttonlist.add(btn1);
			selectbg(buttonlist);
			maps.put("newsInfoTypeId",
					Constants.erjilist.get(4).get("newsInfoTypeId")); // 资讯类
			search();
			break;

		default:
			break;
		}
	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);

		Intent intent = new Intent(context, WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("title", getTitleByType(Integer.parseInt(type)) + "详情");
		System.out.println("newslist: " + newslist);
		intent.putExtra("newsInfoId", mSlpControll.getmListData().get(position)
				.get("newsInfoId").toString());
		// intent.putExtra("title", type);
		intent.putExtra("typeid", getIntent().getStringExtra("typeid"));
		startActivity(intent);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

//		myDataList = resultObject.getListMap();
//		adapter = new SimpleAdapter(context, myDataList, R.layout.rank_item,
//				keys, ids);
//		myListView
//				.setAdapter(new BaseAdapterWrapper(adapter, mItemViewHandler));

		Intent intent = new Intent(context, WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("title", getIntent().getStringExtra("title") + "详情");
		intent.putExtra("newsInfoId", newsInfoId);
		intent.putExtra("newsInfoType", type);
		startActivity(intent);
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", getIntent().getStringExtra("title"), 0, "", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.infomation_list);
		parentResId = R.layout.list_view_for_sonlist;
	}

	/**
	 * 设置列表标题
	 * 
	 * @param type
	 * @return
	 */
	public String getTitleByType(int type) {
		String title = "";
		switch (type) {
		case 1:
			title = "通知公告";
			break;
		case 2:
			title = "工作动态";
			break;
		case 3:
			title = "组织风采";
			break;
		case 4:
			title = "党员先锋";
			break;
		case 6:
			title = "干部工作";
			break;
		case 5:
			title = "组织风采";
			break;
		case 7:
			title = "人才天地";
			break;
		}
		return title;
	}

}
