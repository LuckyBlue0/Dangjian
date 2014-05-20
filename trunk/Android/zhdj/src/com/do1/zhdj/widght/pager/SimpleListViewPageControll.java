package com.do1.zhdj.widght.pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;

import com.androidquery.AQuery;
import com.do1.zhdj.activity.parent.BaseListActivity;

/**
 * 分页类--通知
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class SimpleListViewPageControll extends ViewPageControll{
	
	private Map<String,Object> mapValues = new HashMap<String, Object>();
	private String method;
	private Activity activity;
	
	public SimpleListViewPageControll(Activity activity,String method, AbsListView listView,
			List<Map<String, Object>> listData, DataParser<String> dataParser) {
		super(activity, listView, listData, dataParser);
		this.activity = activity;
		this.method = method;
	}

	@Override
	public void onExecuteSuccess(ResultObject resultObject, View footerView) {
		increment();
		System.out.println("111111111111111111 simplistVIew onexecuteSucess");
		addData(resultObject.getListMap());
		totalPage(resultObject.getTotalPage());
		if (!hasNextPage()) {
			removeFooterView();
		}
		notifyDataSetChanged();
		
		((BaseListActivity)activity).success(resultObject);
	}

	@Override
	public void onExecuteFail(ResultObject resultObject, View emptyView,
			int page) {
		if (emptyView instanceof TextView) {
			if (page == 1) {
				new AQuery(emptyView).id(android.R.id.text1).text(resultObject.getDesc());
			}
		}
		Toast.makeText(getContext(), resultObject.getDesc(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNetworkError(View emptyView, int page) {
		if (emptyView instanceof TextView) {
			if (page == 1) {
				new AQuery(emptyView).id(android.R.id.text1).text("网络加载速度慢，请稍后重试!");
			}
		}
		Toast.makeText(getContext(), "网络加载速度慢，请稍后重试!", Toast.LENGTH_SHORT).show();
//		ToastUtil.showDialog(activity, "网络异常，是否打开网络？");
	}

	@Override
	public void scrolledBottom(AbsListView view, int scrollState) {
		if (hasNextPage() && !isLoading()) {
			loadPage(mapValues,method);
		}
	}

	public Map<String,Object> getMapValues() {
		return mapValues;
	}

	/**
	 * 设置值
	 * @param mapValues
	 */
	public void setMapValues(Map<String,Object> mapValues) {
		this.mapValues = mapValues;
	}
}
