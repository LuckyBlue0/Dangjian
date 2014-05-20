package com.do1.aqzhdj.activity.bbs.zhitongche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class ZhitongcheDiaochaActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhitongche_diaocha);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				 "民意调查", 0, "", this, null);
		ListenerHelper.bindOnCLickListener(this, this,R.id.layout_item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.layout_item:
//			Toast.makeText(this, "建设中...", 0).show();
			intent=new Intent(this,ZhitongcheDetailActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	
}
