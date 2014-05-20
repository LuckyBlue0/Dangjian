package com.do1.zhdj.activity;

import android.content.Intent;
import android.os.Bundle;
import cn.com.do1.component.tab.BaseTabActivity;

import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.bbs.BBSindexActivity;
import com.do1.zhdj.activity.bbs.MoreListActivity;
import com.do1.zhdj.activity.infomation.InfomationActivity;
import com.do1.zhdj.activity.mine.MineNewActivity;
import com.do1.zhdj.activity.mine.PersonInfoActivity;
import com.do1.zhdj.util.Constants;

/**
 * 首页 auth:YanFangqin data:2013-4-28 thzhd
 */
public class IndexActivity extends BaseTabActivity {

	public static String mTabId = "党讯";
	public Constants constants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		constants = (Constants) getApplication();
		constants.indexTab = getTabHost();
		// constants.activityList.add(this);
		mTabId = getIntent().getStringExtra("tabId") == null ? "党讯"
				: getIntent().getStringExtra("tabId");
		setupTab();
		getTabHost().setOnTabChangedListener(this);
	}

	private void setupTab() {
		Intent intent1 = new Intent(this, InfomationActivity.class);
		// intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		addTab("党讯", R.id.tab1, intent1);

		Intent intent2 = new Intent();
		// intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		if ("2".equals(constants.userInfo.getUser_type())) {// type=2为群众用户，1为党员用户
			intent2.setClass(this, PersonInfoActivity.class);
		} else {
			intent2.setClass(this, MineNewActivity.class);
		}
		addTab("我的", R.id.tab2, intent2);

		Intent intent3 = new Intent(this, BBSindexActivity.class);
		// intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		addTab("民主评议", R.id.tab3, intent3);

		Intent intent4 = new Intent(this, MoreListActivity.class);
		// intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		addTab("更多", R.id.tab4, intent4);

		// Intent mapIntent = new
		// Intent(this,com.maxicn.map.MaxicnMapActivity.class);
		// mapIntent.putExtra("userId", constants.userInfo.getUserId());//用户名
		// mapIntent.putExtra("branchId",
		// constants.userInfo.getBranchId());//支部ID
		// mapIntent.putExtra("userType",
		// constants.userInfo.getUser_type());//1：党员账号、2：群众账号
		// mapIntent.putExtra("ismap", "yes");
		// mapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// addTab("地图", R.id.tab5,new Intent(this,MapActivity.class));
		// addTab("地图", R.id.tab5,mapIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// if(!"".equals(mTabId)){
		// getTabHost().setCurrentTabByTag(mTabId);
		// }else{
		// mTabId = "党讯";
		// }
	}

	@Override
	public void onTabChanged(String tabId) {
		if ("地图".equals(tabId)) {

			long triggerSize = 2000000;
			long targetSize = 1000000;
			System.gc();
			AQUtility.cleanCacheAsync(IndexActivity.this, triggerSize,
					targetSize);
			BitmapAjaxCallback.clearCache();

			// Intent mapIntent = new
			// Intent(this,com.maxicn.map.MaxicnMapActivity.class);
			// mapIntent.putExtra("userId",
			// constants.userInfo.getUserId());//用户名
			// mapIntent.putExtra("branchId",
			// constants.userInfo.getBranchId());//支部ID
			// mapIntent.putExtra("userType",
			// constants.userInfo.getUser_type());//1：党员账号、2：群众账号
			// mapIntent.putExtra("ismap", "yes");
			//
			// Log.e("传递给地图的userId：" + constants.userInfo.getUserId());
			// Log.e("传递给地图的branchId：" + constants.userInfo.getBranchId());
			// Log.e("传递给地图的userType：" + constants.userInfo.getUser_type());
			// Log.e("传递给地图的ismap：yes");
			// startActivity(mapIntent);
			// Intent intent=new Intent();
			// intent.setComponent(new ComponentName("com.cat.activity",
			// "com.cat.activity.SplashActivity"));
			// startActivity(intent);
		} else {
			mTabId = tabId;
		}
	}

}