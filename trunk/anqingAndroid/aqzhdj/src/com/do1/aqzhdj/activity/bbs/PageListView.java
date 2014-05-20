package com.do1.aqzhdj.activity.bbs;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Entryption;

/**
 * ListView竖向分页：
 * 1、必须调用init方法
 * 2、分页
 * @author Administrator
 *
 */
public abstract class PageListView extends BaseActivity implements OnScrollListener {
	
	private boolean shouldRefresh = false; // 是否滚动到底部
	private LinearLayout loadingLayout;
	
	/** 当前页数**/
	private int pageNum = 1;
	/** 当前总页数**/
	protected int totalSize = 0;

	private ListView listView;
	private BaseAdapter adapter;
	
	private String urlFormat;
	private  Map<String, String> params;
	private int requestCode = 0;

	/**
	 * 初始化数据
	 * @param mListView
	 * @param mAdapter
	 */
	protected void initView(ListView mListView, BaseAdapter mAdapter) {
		addFootView();
		listView = mListView;
		adapter = mAdapter;
		listView.setAdapter(adapter);
		listView.addFooterView(loadingLayout);
		listView.setOnScrollListener(this);
	}
	
	protected void init2View(ListView mListView, BaseAdapter mAdapter) {
		listView = mListView;
		adapter = mAdapter;
		listView.setAdapter(adapter);
		listView.addFooterView(loadingLayout);
		listView.setOnScrollListener(this);
	}

	protected void addFootView() {
		ProgressBar progressBar;
		LinearLayout searchLayout = new LinearLayout(this);
		searchLayout.setOrientation(LinearLayout.HORIZONTAL);
		progressBar = new ProgressBar(this);
		progressBar.setPadding(0, 0, 15, 0);
		searchLayout.addView(progressBar, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));

		TextView textView = new TextView(this);
		textView.setText("加载中...");
		textView.setGravity(Gravity.CENTER_VERTICAL);
		searchLayout.addView(textView, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));
		searchLayout.setGravity(Gravity.CENTER);

		loadingLayout = new LinearLayout(this);
		loadingLayout.addView(searchLayout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		loadingLayout.setGravity(Gravity.CENTER);
	}

	public void onScroll(AbsListView v, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (firstVisibleItem + visibleItemCount == totalItemCount) {
			shouldRefresh = true;
		} else {
			shouldRefresh = false;
		}
	}

	public void onScrollStateChanged(AbsListView v, int state) {
		if (shouldRefresh && state == OnScrollListener.SCROLL_STATE_IDLE) {
			if (totalSize > 0 && pageNum <= totalSize) {
				nextPage();
			}
		}
	}
	
	protected void request(String urlFormat, Map<String, String> params){
		this.pageNum = Integer.valueOf(params.get("page_no").toString());
		this.urlFormat = urlFormat;
		this.params = params;
		request();
	}
	
	private void request(){
		try {
			String paramStr = Entryption.encode(toJsonString(params));
			doRequestPostString(requestCode, urlFormat, paramStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toJsonString(Map<String, String> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		if(requestId == 0){
			totalSize = resultObject.getTotalPage();
			pageNum++;
			params.put("page_no", String.valueOf(pageNum));
			Log.i(String.valueOf(pageNum));
			Log.i(String.valueOf(totalSize));
			List<Map<String, Object>> listMap = resultObject.getListMap();
			if(listMap.isEmpty()){
				aq.id(R.id.nodata).visible();
				return;
			}
			aq.id(R.id.nodata).gone();
			changeAdapterData(listMap);
			// 刷新listview界面
			
			if(adapter != null && listView.getFooterViewsCount() != 0){
				adapter.notifyDataSetChanged();
			}
			Log.e(listView.getFooterViewsCount()+"-----");
			if (listView.getFooterViewsCount() != 0 && totalSize <= 0) {
				listView.removeFooterView(loadingLayout);
			}
		}
	}
	
	protected abstract void changeAdapterData(List<Map<String, Object>> listMap);

	/**
	 * 下一页
	 */
	private  void nextPage(){
		setProgressMode(2);
		request();
	};

}
