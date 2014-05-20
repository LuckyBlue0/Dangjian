package com.do1.aqzhdj.activity.study.gongkao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class BaomingActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_zhaolu_baoming);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "", getString(R.string.zaixinbaoming), 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this, R.id.one1,R.id.one2,R.id.one3,R.id.one4,R.id.one5);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.one1:
			intent=new Intent(BaomingActivity.this,Zhaolu_detailActivity.class);
			startActivity(intent);
			break;
		case R.id.one2:
			intent=new Intent(BaomingActivity.this,Zhaolu_detailActivity.class);
			startActivity(intent);
			break;
		case R.id.one3:
			intent=new Intent(BaomingActivity.this,Zhaolu_detailActivity.class);
			startActivity(intent);
			break;
		case R.id.one4:
			intent=new Intent(BaomingActivity.this,Zhaolu_detailActivity.class);
			startActivity(intent);
			break;
		case R.id.one5:
			intent=new Intent(BaomingActivity.this,Zhaolu_detailActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	

}
