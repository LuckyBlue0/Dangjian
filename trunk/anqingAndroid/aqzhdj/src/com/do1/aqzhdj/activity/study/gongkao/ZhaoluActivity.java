package com.do1.aqzhdj.activity.study.gongkao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class ZhaoluActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_zhaolu);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.zhaolu), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.baoming,
				R.id.jiaofei, R.id.chaxun, R.id.xiugai, R.id.zhengjian);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		int id = v.getId();
		Intent intent;
		switch (id) {
		// 在线报名
		case R.id.baoming:
			intent = new Intent(ZhaoluActivity.this, BaomingActivity.class);
			startActivity(intent);
			break;

		// 信息修改
		case R.id.xiugai:
			intent = new Intent(ZhaoluActivity.this, XiugaiActivity.class);
			startActivity(intent);
			break;

		// 审核查询
		case R.id.chaxun:
			intent = new Intent(ZhaoluActivity.this, ShenheActivity.class);
			startActivity(intent);
			break;

		// 网上缴费
		case R.id.jiaofei:
			intent = new Intent(ZhaoluActivity.this, JiaofeiActivity.class);
			startActivity(intent);
			break;

		// 准考证件
		case R.id.zhengjian:
			intent = new Intent(ZhaoluActivity.this, ZhengjianActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
