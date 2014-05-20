package com.do1.zhdj.activity.bbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cn.com.do1.component.util.ToastUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Listenertool;

/**
 * 民主评议主界面 ,只有党员可进
 * 
 * @author LHQ
 * 
 */
public class BBSindexActivity extends BaseActivity {
	public static final String TAG = "BBSindexActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbs_democratic);

		setHeadView(findViewById(R.id.headLayout), 0, "", "民主评议", 0, "", this,
				this);
		int[] resourceIds = { R.id.youxiuDY, R.id.jiguanMZ };
		// 监听工具类
		Listenertool.bindOnCLickListener(this, this, resourceIds);
	}

	@Override
	public void onClick(View v) {
		// 党员账户，有权限可进
		if (!"1".equals(constants.userInfo.getUser_type())) {
			ToastUtil.showLongMsg(BBSindexActivity.this, "您不是党员用户，暂时无法访问！");
			return;
		}
		Intent intent;
		switch (v.getId()) {
		case R.id.youxiuDY:// 优秀党员评议
			Log.i(TAG, "点的优秀党员评议");
			intent = new Intent(this, BBSPartyerListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.jiguanMZ:// 机关民主评议
			Log.i(TAG, "点的机关民主评议");
			intent = new Intent(this, BBSOfficeListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
