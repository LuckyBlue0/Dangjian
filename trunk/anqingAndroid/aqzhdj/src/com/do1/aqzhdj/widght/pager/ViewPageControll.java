package com.do1.aqzhdj.widght.pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.com.do1.component.net.DefaultAjaxCallBack;
import cn.com.do1.component.net.OnRequestListener;
import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.StringUtil;

/**
 * 处理促销信息滑动分页数据
 * 
 * @author YuanShuQiao
 * 
 */
public abstract class ViewPageControll {

	private static final String SCROLLED_BOTTOM = "scrolledBottom";
	private Activity mActivity;
	private int mPage = 1;
	public int mPageCount = 10;
	private int mTotalPage = 0;
	private String mUrl;
	private AbsListView mListView;
	private View mEmptyView;
	private View mFooterView;
	private AQuery mAQuery;
	private DefaultAjaxCallBack<String> mAjaxCallBack;
	private List<Map<String, Object>> mListData;
	private boolean mLoading = false;
	final String PAGE = "pageIndex";
	final String PAGE_SIZE = "pageSize";
	public static boolean needReflesh = true;// 是否分页加载

	public ViewPageControll(Activity activity, AbsListView listView,
			List<Map<String, Object>> listData,
			DefaultAjaxCallBack<String> ajaxCallBack) {
		this.mActivity = activity;
		this.mListView = listView;
		this.setmListData(listData);
		this.mAjaxCallBack = ajaxCallBack;
		Log.e("--------------------eugene5--------------------" + mAjaxCallBack);
		mAjaxCallBack.setOnRequestListener(mRequestListenerAdapter);
		Log.e("--------------------eugene6  mRequestListenerAdapter--------------------"
				+ mRequestListenerAdapter);
		mAQuery = new AQuery(activity);
		mAQuery.id(mListView).scrolledBottom(this, SCROLLED_BOTTOM);
	}

	public ViewPageControll(Activity activity, AbsListView listView,
			List<Map<String, Object>> listData, DataParser<String> dataParser) {
		this(activity, listView, listData, new DefaultAjaxCallBack<String>(
				dataParser));
		Log.e("--------------------eugene4--------------------");
	}

	/**
	 * 
	 * @param view
	 * @param scrollState
	 */
	protected abstract void scrolledBottom(AbsListView view, int scrollState);

	/**
	 * 
	 * @param resultObject
	 * @param footerView
	 */
	protected abstract void onExecuteSuccess(ResultObject resultObject,
			View footerView);

	/**
	 * 
	 * @param resultObject
	 * @param emptyView
	 * @param page
	 */
	protected abstract void onExecuteFail(ResultObject resultObject,
			View emptyView, int page);

	/**
	 * 
	 * @param emptyView
	 * @param page
	 */
	protected abstract void onNetworkError(View emptyView, int page);

	private OnRequestListener mRequestListenerAdapter = new OnRequestListener() {

		@Override
		public void onNetworkError() {
			completed();
			ViewPageControll.this.onNetworkError(mEmptyView, mPage);
			// ToastUtil.showLongMsg(mActivity, "chaoshila");
			((TextView) mFooterView).setText("正在加载...");
			((BaseListActivity) mActivity).nullText.setVisibility(View.GONE);
		}

		@Override
		public void onExecuteSuccess(ResultObject resultObject) {
			completed();
			ViewPageControll.this.onExecuteSuccess(resultObject, mFooterView);
			if (resultObject.getListMap() == null
					|| resultObject.getListMap().size() == 0) {
				// ((TextView)mFooterView).setText("您还没有添加任何数据哦，赶紧去添加吧");
				((TextView) mFooterView).setText("");
				((BaseListActivity) mActivity).nullText
						.setVisibility(View.VISIBLE);
			} else {
				((TextView) mFooterView).setText("正在加载...");
				((BaseListActivity) mActivity).nullText
						.setVisibility(View.GONE);
			}

			if (hasNextPage() || resultObject.getTotalPage() == 0)
				footer(mFooterView);
		}

		@Override
		public void onExecuteFail(ResultObject resultObject) {
			completed();
			ViewPageControll.this
					.onExecuteFail(resultObject, mEmptyView, mPage);
		}

	};

	/**
	 * 
	 * @param params
	 */
	final public void loadPage(Map<String, Object> params, String method) {
		if (mLoading) {
			return;
		}
		mLoading = true;
		footer(mFooterView);
		if (method
				.contains("http://219.136.91.63:8200/anqingOA/gongwenguanli.mobo?iswap=1&cmd=request")
				|| method
						.contains("http://219.136.91.63:8200/anqingOA/xinxiguanli.mobo?iswap=1&cmd=request")
				|| method
						.contains("http://219.136.91.63:8200/anqingOA/wenjianhuiji.mobo?iswap=1&cmd=request")
				|| method
						.contains("http://219.136.91.63:8200/anqingOA/neibuyoujian.mobo?iswap=1&cmd=request")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", Constants.xietongname);
			map.put("userPassword", Constants.xietongpwd);
			// mAQuery.cookie("cookie", Constants.cookie.toString());
			// mAjaxCallBack.cookie("cookie", Constants.cookie.toString());
			// System.out.println("cookie: " + Constants.cookie.toString());
			mAQuery.ajax(method, map, String.class, mAjaxCallBack);
		} else {

			if (null == params)
				params = new HashMap<String, Object>();
			if (needReflesh) {
				// String mpage=SecurityUtil.encryptToDES(Constants.MICHI,
				// mPage+"");
				// String mpagecount=SecurityUtil.encryptToDES(Constants.MICHI,
				// mPageCount+"");
				params.put("pageIndex", mPage);
				params.put("pageSize", mPageCount);
			} else {
				needReflesh = true;
			}

			System.out.println("url: " + Constants.SERVER_RUL2 + method);
			mAQuery.ajax(Constants.SERVER_RUL2 + method,
					StringUtil.jiami(params), String.class, mAjaxCallBack);
		}
	}

	final protected boolean isLoading() {
		return mLoading;
	}

	private void completed() {
		mLoading = false;
	}

	/**
	 */
	final protected void increment() {
		mPage++;
	}

	final protected Context getContext() {
		return mActivity;
	}

	/**
	 * 
	 * @return
	 */
	final protected boolean hasNextPage() {
		return mPage <= mTotalPage;
	}

	final public ViewPageControll reset() {
		mPage = 1;
		mTotalPage = 0;
		getmListData().clear();
		if (!hasFooter()) {
			footer(mFooterView);
		}
		notifyDataSetChanged();
		return this;
	}

	/**
	 */
	final protected void notifyDataSetChanged() {
		ListAdapter adapter = mListView.getAdapter();
		if (adapter instanceof HeaderViewListAdapter) {
			adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
		}
		if (adapter instanceof BaseAdapter) {
			((BaseAdapter) adapter).notifyDataSetChanged();
		}
	}

	protected void addData(List<Map<String, Object>> listMap) {
		getmListData().addAll(listMap);
	}

	/**
	 */
	final public ViewPageControll pageCount(int pageCount) {
		this.mPageCount = pageCount;
		return this;
	}

	final protected void removeFooterView() {
		if (mListView instanceof ListView) {
			((ListView) mListView).removeFooterView(mFooterView);
		}
	}

	final public ViewPageControll url(String url) {
		this.mUrl = url;
		return this;
	}

	/**
	 * 
	 * @param mTotalPage
	 */
	final protected ViewPageControll totalPage(int totalPage) {
		this.mTotalPage = totalPage;
		return this;
	}

	private boolean hasFooter() {
		if (mListView instanceof ListView) {
			return ((ListView) mListView).getFooterViewsCount() > 0;
		}
		return false;
	}

	final public ViewPageControll progress(int id) {
		mAQuery.progress(id);
		return this;
	}

	final public ViewPageControll progress(Object view) {
		mAQuery.progress(view);
		return this;
	}

	final public ViewPageControll empty(View emptyView) {
		mListView.setEmptyView(emptyView);
		return this;
	}

	final public ViewPageControll empty(int id) {
		View emptyView = mActivity.findViewById(id);
		return empty(emptyView);
	}

	final public ViewPageControll removeFooter(View footerView) {
		mFooterView = footerView;
		if (mListView instanceof ListView) {
			ListView listView = (ListView) mListView;
			if (listView.getFooterViewsCount() > 0) {
				listView.removeFooterView(footerView);
			}
		}
		return this;
	}

	final public ViewPageControll footer(View footerView) {
		mFooterView = footerView;
		if (mListView instanceof ListView) {
			ListView listView = (ListView) mListView;
			if (listView.getFooterViewsCount() == 0) {
				listView.addFooterView(footerView);
			}
		}
		return this;
	}

	final public ViewPageControll footer(int id) {
		View footerView = mActivity.findViewById(id);
		return footer(footerView);
	}

	final public ViewPageControll adapter(ListAdapter adapter) {
		mListView.setAdapter(adapter);
		return this;
	}

	@Override
	public String toString() {
		return "ScrollAdpter [mPage=" + mPage + ", mPageCount=" + mPageCount
				+ ", mTotalPage=" + mTotalPage + ", mUrl=" + mUrl
				+ ", mListView=" + mListView + ", mAQuery=" + mAQuery
				+ ", mAjaxCallBack=" + mAjaxCallBack + "]";
	}

	public List<Map<String, Object>> getmListData() {
		return mListData;
	}

	public void setmListData(List<Map<String, Object>> mListData) {
		this.mListData = mListData;
	}
}
