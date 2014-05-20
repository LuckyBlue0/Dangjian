package com.do1.aqzhdj.activity.study.yuancheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class YuanchengActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_yuancheng);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				"远程管理", 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.paizhao,
				R.id.fankui, R.id.jiankong, R.id.saomiao, R.id.weihu);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		int id = v.getId();
		Intent intent;
		switch (id) {
		// 现场拍照
		case R.id.paizhao:
			intent = new Intent(YuanchengActivity.this, PaizhaoActivity.class);
			startActivity(intent);
			break;

		// 学习反馈
		case R.id.fankui:
			intent = new Intent(YuanchengActivity.this, FankuiActivity.class);
			startActivity(intent);
			break;

		// 站点维护
		case R.id.weihu:
			intent = new Intent(YuanchengActivity.this, WeihuActivity.class);
			startActivity(intent);
			break;

		// 站点扫描
		case R.id.saomiao:
			intent = new Intent(YuanchengActivity.this, SaomiaoActivity.class);
			startActivity(intent);
			break;

		// 3G监控
		case R.id.jiankong:
			intent = new Intent(YuanchengActivity.this, JiankongActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
