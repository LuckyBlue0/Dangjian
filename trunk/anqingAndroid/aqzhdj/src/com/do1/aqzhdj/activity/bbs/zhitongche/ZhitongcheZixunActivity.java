package com.do1.aqzhdj.activity.bbs.zhitongche;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class ZhitongcheZixunActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhitongche_zixun);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "在线咨询", R.drawable.btn_head_2, "提交", this, this);
		ListenerHelper.bindOnCLickListener(this, this, R.id.rightImage);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			Toast.makeText(this, "提交成功", 0).show();
			finish();
			break;
		}
	}
	
	
	
	

}
