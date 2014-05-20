package com.do1.zhdj.activity.parent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.NumberUtil;
import cn.com.do1.component.util.ViewUtil;
import cn.com.do1.component.widget.BaseAdapterWrapper;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.bbs.adpter.BBSVoteGridViewAdapter;
import com.do1.zhdj.widght.pager.SimpleListViewPageControll;

/**
 * 提供列表查询页面--父类 auth:YanFangqin data:2013-4-28 thzhd
 */
public abstract class BaseListActivity extends BaseActivity {
	protected static final Class<?>[] ON_CLICK_SIG = null;
	public ListView mListView;
	public GridView mgredview;
	public BaseAdapter mAdapter;
	private BaseAdapter mAdapter2;
	public SimpleListViewPageControll mSlpControll;
	private EditText mSearchEdit;
	private String[] mKeys;
	private int[] mIds;
	private int resourseId;
	private String mQueryUrl;
	private ProgressDialog mDialog;
	public Map<String, Object> maps = new HashMap<String, Object>();
	public TextView nullText;
	public View view;
	public String method;
	public int parentResId = R.layout.list_view_for_activity;
	public List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	private boolean loadDataOnResume = false;
	public String notRequestKey = "notRequest";

	/**
	 * 用户自定义处理View接口，需要实现该接口
	 */
	private ItemViewHandler cusItemViewHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestMethod();
		setContentView(parentResId);

		mListView = (ListView) findViewById(android.R.id.list);
		mgredview = (GridView) findViewById(R.id.grid_view);
		if (mgredview != null) {
			// mListView.setOnItemClickListener(onItemClick);
			mgredview.setOnItemClickListener(onItemClick);
		} else {
			System.out.println("mListView:" + mListView);
			mListView.setOnItemClickListener(onItemClick);
			// mgredview.setOnItemClickListener(onItemClick);
		}

		nullText = (TextView) findViewById(R.id.nullText);

		mDialog = new ProgressDialog(this);
		mDialog.setTitle("温馨提示");
		mDialog.setMessage(getString(R.string.loading_message));
	}

	/**
	 * 设置头文件
	 */
	public abstract void setHeadMethod();

	/**
	 * 配置请求方法和页面布局文件 请求方法参数：method： 布局文件参数：parentResId：
	 */
	public abstract void setRequestMethod();

	@Override
	protected void onResume() {
		super.onResume();
		setHeadMethod();
		if (mAdapter == null) {

			listMap.add(new HashMap<String, Object>());
			listMap.add(new HashMap<String, Object>());
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, listMap,
					resourseId, mKeys, mIds);
			BBSVoteGridViewAdapter gridViewAdapter = new BBSVoteGridViewAdapter(
					this, listMap, resourseId, mKeys, mIds);

			mAdapter = new BaseAdapterWrapper(simpleAdapter, mItemViewHandler);// 包装类，提供对itemView进行额外处理的功能
			mAdapter2 = new BaseAdapterWrapper(gridViewAdapter,
					mItemViewHandler);// 包装类，提供对itemView进行额外处理的功能
			if (mgredview != null) {
				 System.out.println("mgredview执行了。。。");
				 mgredview.setAdapter(mAdapter2);
			} else {
				mListView.setAdapter(mAdapter);
			}

			// System.out.println("scontroll.listmap: " + listMap);
			if (mgredview != null) {
				mSlpControll = new SimpleListViewPageControll(this, method,
						mgredview, listMap, DATAPARSER);
			} else {
				mSlpControll = new SimpleListViewPageControll(this, method,
						mListView, listMap, DATAPARSER);
				mSlpControll.footer(getFooterView()).adapter(mAdapter);// 适配器
			}

			doSearch();
		} else {
			if (loadDataOnResume) {
				doSearch();
			}
		}
	}

	public View getFooterView() {
		TextView footer = new TextView(this);
		footer.setText("正在加载...");
		footer.setPadding(0, 10, 0, 10);
		footer.setLayoutParams(new android.widget.AbsListView.LayoutParams(
				android.widget.AbsListView.LayoutParams.FILL_PARENT,
				android.widget.AbsListView.LayoutParams.WRAP_CONTENT));
		footer.setGravity(Gravity.CENTER);
		return footer;
	}

	public void setQueryUrl(String queryUrl) {
		this.mQueryUrl = queryUrl;
	}

	public void setAdapterParams(String[] keys, int[] ids, int resourseId,
			Map<String, Object> maps) {
		this.mKeys = keys;
		this.mIds = ids;
		this.resourseId = resourseId;
		this.maps = maps;
	}

	public void setSearchHint(String hint) {
		mSearchEdit.setHint(hint);
	}

	/**
	 * 开始搜索 传入请求的参数即可 不需要传入page和pageSize参数，组件将会覆盖
	 */
	protected void doSearch() {
		ViewUtil.hideKeyboard(this);
		System.out.println("doSearch刷新");
		if (maps.containsKey(notRequestKey)) {
			maps.remove(notRequestKey);
			return;
		}
		mSlpControll.setMapValues(maps);
		mSlpControll.reset().progress(mDialog).url(mQueryUrl)
				.loadPage(mSlpControll.getMapValues(), method);
	}

	private OnItemClickListener onItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			itemClick(parent, view, position, id);
		}
	};

	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}

	// //单击详情
	// protected abstract void infoClick(BaseAdapter adapter, int position,
	// final View itemView);

	// 对itemView进行绑定监听等功能
	public BaseAdapterWrapper.ItemViewHandler mItemViewHandler = new BaseAdapterWrapper.ItemViewHandler() {
		@Override
		public void handleItemView(final BaseAdapter adapter,
				final int position, final View itemView, final ViewGroup parent) {

			if (null != cusItemViewHandler)// 用户自定义处理对象
				cusItemViewHandler.handleItemView(adapter, position, itemView,
						parent);
			// final OnClickListener clickListener = new OnClickListener() {
			// @Override
			// public void onClick(View v) {
			//
			// switch (v.getId()) {
			// case R.id.layout_item:
			// infoClick(adapter, position, itemView);
			// break;
			// }
			// }
			// };
			// bindClickListener(itemView,clickListener,R.id.layout_item);
		}

		// private void bindClickListener(View item,OnClickListener
		// clickListener,int... ids){
		// for(int id:ids){
		// item.findViewById(id).setOnClickListener(clickListener);
		// }
		// }
	};

	/**
	 * 列表请求成功
	 * 
	 * @param requestId
	 * @param resultObject
	 */
	public void success(ResultObject data) {
	}

	public void success(Object data) {
	}

	public DataParser<String> DATAPARSER = new DataParser<String>() {

		@Override
		public ResultObject parseData(String dataKey) {
			Log.e("--------------------eugene1--------------------");
			String data = "";
			ResultObject result = new ResultObject();
			try {
				// Entryption.encode("");
				// data = Entryption.decode(dataKey);
				// Log.e(data);
				Map<String, Object> resultMap = json2Map(new JSONObject(dataKey));
				for (String key : resultMap.keySet()) {
					if ("code".equals(key)) {// code
						int code = Integer.parseInt(getValueFromMap(resultMap,
								key, -1) + "");
						result.setCode(code);
						result.setSuccess(0 == code);
					} else if ("desc".equals(key)) {// desc
						String desc = getValueFromMap(resultMap, key, "");
						result.setDesc(desc);
					} else if ("data".equals(key)) {// data
						// success(resultMap.get(key));
						Object dataObject = getValueFromMap(resultMap, key,
								null);
						// ==============resultMap 整个字符串 key== data===========

						// =================dataobject 之前是包含的list=============
						if (dataObject instanceof JSONArray) {// listMap
							List<Map<String, Object>> listMap = jsonArray2List((JSONArray) dataObject);
							result.addListMap(listMap);
							// ==================object对象=================
						} else if (dataObject instanceof JSONObject) {// Map
							Map<String, Object> dataMap = json2Map((JSONObject) dataObject); // datamap
							result.addDataMap(dataMap);
							if (dataMap.get("totalPage") == null)
								result.setTotalPage(1);
							else
								result.setTotalPage(NumberUtil
										.string2Int(dataMap.get("totalPage")
												.toString()));
							// ========datamap 中包含了 list 和 total_pages======
							// 解析一下数据
							if (dataMap.get("pageData") != null
									&& !"".equals(dataMap.get("pageData")
											.toString())) {
								List<Map<String, Object>> listMap = jsonArray2List(((JSONObject) dataObject)
										.getJSONArray("pageData"));
								result.addListMap(listMap);
								// System.out.println("result.listmap: " +
								// result.getListMap());
								if (dataMap.get("totalPage") == null
										&& listMap.size() > 0)
									result.setTotalPage(1);

								else if (dataMap.get("totalPage") == null
										&& listMap.size() == 0)
									result.setTotalPage(0);
								else
									result.setTotalPage(NumberUtil
											.string2Int(dataMap
													.get("totalPage")
													.toString()));
							}
							if (dataMap.get("list") != null
									&& !"".equals(dataMap.get("list")
											.toString())) {
								List<Map<String, Object>> listMap = jsonArray2List(((JSONObject) dataObject)
										.getJSONArray("list"));
								result.addListMap(listMap);
								// System.out.println("result.listmap: " +
								// result.getListMap());
								if (dataMap.get("totalPage") == null
										&& listMap.size() > 0)
									result.setTotalPage(1);

								else if (dataMap.get("totalPage") == null
										&& listMap.size() == 0)
									result.setTotalPage(0);
								else
									result.setTotalPage(NumberUtil
											.string2Int(dataMap
													.get("totalPage")
													.toString()));
							}
							if (dataMap.get("memberList") != null
									&& !"".equals(dataMap.get("memberList")
											.toString())) {
								List<Map<String, Object>> listMap = jsonArray2List(((JSONObject) dataObject)
										.getJSONArray("memberList"));
								result.addListMap(listMap);
								// System.out.println("result.listmap: " +
								// result.getListMap());
								if (dataMap.get("totalPage") == null
										&& listMap.size() > 0)
									result.setTotalPage(1);

								else if (dataMap.get("totalPage") == null
										&& listMap.size() == 0)
									result.setTotalPage(0);
								else
									result.setTotalPage(NumberUtil
											.string2Int(dataMap
													.get("totalPage")
													.toString()));
							}

							if (dataMap.get("newsInfos") != null
									&& !"".equals(dataMap.get("newsInfos")
											.toString())) {
								List<Map<String, Object>> listMap = jsonArray2List(((JSONObject) dataObject)
										.getJSONArray("newsInfos"));
								result.addListMap(listMap);
								// System.out.println("result.listmap: " +
								// result.getListMap());
								if (dataMap.get("totalPage") == null
										&& listMap.size() > 0)
									result.setTotalPage(1);

								else if (dataMap.get("totalPage") == null
										&& listMap.size() == 0)
									result.setTotalPage(0);
								else
									result.setTotalPage(NumberUtil
											.string2Int(dataMap
													.get("totalPage")
													.toString()));
							}

						}
					} else {
						Object value = getValueFromMap(resultMap, key, null);
						result.put2Map(key, value);
					}
				}

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;

		}
	};

	public ItemViewHandler getCusItemViewHandler() {
		return cusItemViewHandler;
	}

	public void setCusItemViewHandler(ItemViewHandler cusItemViewHandler) {
		this.cusItemViewHandler = cusItemViewHandler;
	}

	/**
	 * Adapter调用完getView之后调用，提供对ItemView进行额外处理的功能，如绑定监听等
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface ItemViewHandler {
		/**
		 * 提供对ItemView进行额外处理的功能，如绑定监听等
		 * 
		 * @param adapter
		 * @param position
		 *            he position of the item within the adapter's data set of
		 *            the item whose view we want.
		 * @param itemView
		 * @param parent
		 *            itemView所在的父类，如为ListView
		 */
		void handleItemView(BaseAdapter adapter, int position, View itemView,
				ViewGroup parent);
	}

	public boolean isLoadDataOnResume() {
		return loadDataOnResume;
	}

	public void setLoadDataOnResume(boolean loadDataOnResume) {
		this.loadDataOnResume = loadDataOnResume;
	}

}
