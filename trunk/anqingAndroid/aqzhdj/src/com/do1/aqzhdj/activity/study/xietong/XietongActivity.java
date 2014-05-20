package com.do1.aqzhdj.activity.study.xietong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class XietongActivity extends BaseActivity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_xietong);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.xietong), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.xinxi,
				R.id.gongwen, R.id.youjian, R.id.wenjian);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		int id = v.getId();
		Intent intent;
		switch (id) {
		// 信息管理
		case R.id.xinxi:
			intent = new Intent(XietongActivity.this, XinxiActivity.class);
			startActivity(intent);
			break;

		// 公文管理
		case R.id.gongwen:
			intent = new Intent(XietongActivity.this, GongwenActivity.class);
			startActivity(intent);
			break;

		// 内部邮件
		case R.id.youjian:
			intent = new Intent(XietongActivity.this, YoujianActivity.class);
			startActivity(intent);
			break;

		// 文件汇集
		case R.id.wenjian:
			intent = new Intent(XietongActivity.this, WenjianActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
