package com.do1.zhdj.activity.infomation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.widget.BaseAdapterWrapper;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.common.WapViewActivity;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.DateTool;
import com.do1.zhdj.util.ImageViewTool;
import com.do1.zhdj.util.ValidUtil;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		newsInfoId = getIntent().getStringExtra("newsInfoId");
		type = getIntent().getStringExtra("newsInfoType");
		System.out.println("NoticeList.type: " + type);

		LinearLayout layout = (LinearLayout) findViewById(R.id.button_bottom);
		layout.setVisibility(View.GONE);
		setCusItemViewHandler(this);
		String[] keys = new String[] { "title", "digest", };
		int[] ids = new int[] { R.id.title, R.id.digest };
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("parentType", "1");
		map.put("newsInfoType", type); // 资讯类
		setAdapterParams(keys, ids, R.layout.notice_item3, map);
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		String time = mSlpControll.getmListData().get(position)
				.get("pushTime").toString();
		String formatStr = "yyyy-MM-dd HH:mm";
		aq = new AQuery(itemView);
		aq.id(R.id.sendTime).text(DateTool.StringToString(time, formatStr));

		ImageView img = (ImageView) itemView.findViewById(R.id.pic);
		System.out.println("img: " + img);
		System.out.println("server: " + Constants.SERVER_IMG
				+ mSlpControll.getmListData().get(position).get("imgPath"));
		ImageViewTool.getAsyncImageBg(mSlpControll.getmListData().get(position)
				.get("resizeImgPath").toString(), img, R.drawable.index_default);
		if (mSlpControll.getmListData().get(position).get("buyTop") != null) {
			if ("1".equals(mSlpControll.getmListData().get(position)
					.get("buyTop").toString())) {
				itemView.findViewById(R.id.isTop).setVisibility(View.VISIBLE);
			} else {
				itemView.findViewById(R.id.isTop).setVisibility(View.GONE);
			}
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
		intent.putExtra("newsInfoType", type);
		startActivity(intent);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		Intent intent = new Intent(context, WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("title", getTitleByType(Integer.parseInt(type)) + "详情");
		intent.putExtra("newsInfoId", newsInfoId);
		intent.putExtra("newsInfoType", type);
		startActivity(intent);
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", getTitleByType(Integer.parseInt(type)), 0, "", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.infomation_list);
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
