package com.do1.aqzhdj.activity.study.cunguang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.activity.study.yuancheng.PaizhaoActivity;
import com.do1.aqzhdj.activity.study.yuancheng.YuanchengActivity;

public class CunguangActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_cunguang);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.cunguang), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.rizhi,
				R.id.qiandao, R.id.xiangmu, R.id.jihua, R.id.jianyi);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		// 村官日志
		case R.id.rizhi:
			intent = new Intent(CunguangActivity.this, LogActivity.class);
			startActivity(intent);
			break;
		// 在岗签到
		case R.id.qiandao:
			intent = new Intent(CunguangActivity.this, QiandaoActivity.class);
			startActivity(intent);
			break;
		// 项目管理
		case R.id.xiangmu:
			intent = new Intent(CunguangActivity.this, XiangmuActivity.class);
			startActivity(intent);
			break;
		// 工作计划
		case R.id.jihua:
			intent = new Intent(CunguangActivity.this, JihuaActivity.class);
			startActivity(intent);
			break;

		// 意见建议
		case R.id.jianyi:
			intent = new Intent(CunguangActivity.this, JianyiActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
