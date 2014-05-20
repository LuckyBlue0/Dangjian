package com.do1.aqzhdj.activity.bbs.zhitongche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class ZhitongcheMainActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhitongchemain);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				 "党代表", 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.ziaxianzixun,R.id.item1,R.id.item2);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.ziaxianzixun:
			intent=new Intent(this,ZhitongcheZixunActivity.class);
			startActivity(intent);
			break;
		case R.id.item1:
			intent=new Intent(this,ZhitongcheDiaochaActivity.class);
			startActivity(intent);
			break;
		case R.id.item2:
			Toast.makeText(this, "此模块正在建设中....", 0).show();
			break;
		}
	}

	
}
