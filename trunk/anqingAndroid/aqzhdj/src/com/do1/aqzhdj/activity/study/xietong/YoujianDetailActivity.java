package com.do1.aqzhdj.activity.study.xietong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class YoujianDetailActivity extends BaseActivity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_xietong_youjian_detail);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				getString(R.string.youjiandetail), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.huifubt);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		super.onClick(v);
		switch (v.getId()) {
		case R.id.huifubt:
			intent = new Intent(YoujianDetailActivity.this, HuifuYoujianActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
