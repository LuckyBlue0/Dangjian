package com.do1.aqzhdj.activity.bbs.wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ListenerHelper;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ViewUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.StringUtil;

/**
 * 留影墙--图片相册列表 auth:YanFangqin data:2013-4-25 aqdj
 */
public class WallImageListActivity extends BaseActivity {

	private Context context;
	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	private ListView listView;
	private FootViewLinearLayout footerView;
	private NineFrameAdapter adapter;
	private int currentPage = 1;
	private int totalPage = -1;
	private int pageSize = 18;
	private Map<String, Object> map = new HashMap<String, Object>();// 参数map
//	private String type = "";
//	private String title = "";
	private boolean isFlesh = false;

	private Handler mHandler;
	private boolean mRunning = false;
	private int requestId = 1;
	TextView righttext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_for_wall);
		righttext = aq.find(R.id.rightImage).getTextView();
		righttext.setOnClickListener(this);
//		type = getIntent().getStringExtra("typeId") != null ? getIntent()
//				.getStringExtra("typeId") : "";
//		title = getIntent().getStringExtra("title") != null ? getIntent()
//				.getStringExtra("title") : "";
//		AppManager.title=title;
		// 设置head
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", AppManager.title, R.drawable.btn_head_2, "上传", null, null);
		context = this;
		listView = (ListView) findViewById(android.R.id.list);
		listView.setOnScrollListener(onScrollListener);
		aq.id(R.id.searchbtn).clicked(this);
		addFooterView();
		HandlerThread thread = new HandlerThread("MyHandlerThread");
		thread.start();// 创建一个HandlerThread并启动它
		mHandler = new Handler(thread.getLooper());// 使用HandlerThread的looper对象创建Handler，如果使用默认的构造方法，很有可能阻塞UI线程

		// refresh(1);
		fillData(requestId);
	}

	@Override
	protected void onResume() {
		super.onStart();
		if (AppManager.needFlesh) {
			AppManager.needFlesh = false;
			aq.find(R.id.centerTitle).text(AppManager.title);
			refresh(1);
		}
	}

	// 实现耗时操作的线程
	Runnable mBackgroundRunnable = new Runnable() {
		@Override
		public void run() {
			while (!mRunning) {
				mRunning = true;
				refresh(requestId);
			}
		}
	};

	/**
	 * 启动线程请求数据
	 */
	public void fillData(int requestId) {
		this.requestId = requestId;
		mHandler.post(mBackgroundRunnable);// 将线程post到Handler中
	}

	protected void refresh(final int requestId) {
		if (requestId == 1) {
			currentPage = 1;
		}
		String url = SERVER_URL
				+ getString(R.string.wall_list_by_type_add_search);
		map.put("typeId", AppManager.typeId);
		map.put("keyword", aq.id(R.id.keyword).getText().toString().trim());
		map.put("pageIndex", currentPage + "");
		map.put("pageSize", pageSize + "");
		// String key = Entryption.getJsonKey(map);
		// doRequestPostString(requestId, url, StringUtil.jiami(map));
		doRequest(requestId, url, StringUtil.jiami(map));
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.searchbtn:
			ViewUtil.hideInputMethod(this);
			if (mRunning) {
				mRunning = false;
				fillData(1);
			}
			break;
		case R.id.rightImage:
			Intent intent = new Intent(context, ImageLoadActivity.class);
			intent.putExtra("typeId", AppManager.typeId);
			startActivity(intent);
			break;
		}
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		super.onExecuteFail(requestId, resultObject);

		if (listView.getFooterViewsCount() > 0) {
			listView.removeFooterView(footerView);
		}
	}

	@Override
	public void onNetworkError(int requestId) {
		super.onNetworkError(requestId);
		if (listView.getFooterViewsCount() > 0) {
			listView.removeFooterView(footerView);
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);

		this.totalPage = resultObject.getTotalPage();
		Log.i("图片总页数：" + totalPage);
		if (requestId == 1) {
			dataList.clear();
		}
		dataList.addAll(resultObject.getListMap());
		if (dataList.size() > 0) {
			aq.find(R.id.nullText).getView().setVisibility(View.GONE);
		}
		// if (adapter != null) {
		// adapter.notifyDataSetChanged();
		// } else {
		adapter = new NineFrameAdapter(context, dataList);
		listView.setAdapter(adapter);
		// }
		isFlesh = false;
		if (currentPage == totalPage) {
			if (listView.getFooterViewsCount() > 0) {
				listView.removeFooterView(footerView);
			}
		}
	}

	public void addFooterView() {
		if (footerView == null) {
			footerView = new FootViewLinearLayout(this);
		}
		if (listView.getFooterViewsCount() == 0) {
			listView.addFooterView(footerView);
		}
		footerView.init();
	}

	private OnScrollListener onScrollListener = new OnScrollListener() {

		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (firstVisibleItem + visibleItemCount == totalItemCount) {
				if (currentPage < totalPage && !isFlesh) {
					isFlesh = true;
					addFooterView();
					footerView.setVisibility(View.VISIBLE);
					currentPage++;
					// refresh(2);
					if (mRunning) {
						mRunning = false;
						fillData(2);
					}
				}
			}
		}
	};

	// OnClickListener onclick = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// Intent intent = new Intent(context, ImageLoadActivity.class);
	// intent.putExtra("typeId", type);
	// startActivity(intent);
	// }
	// };

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 销毁线程
		mHandler.removeCallbacks(mBackgroundRunnable);
	}

}
