package com.do1.aqzhdj.activity.study.yuancheng;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class WeihuActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_yuancheng_weihu);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "", getString(R.string.weihu), 0, "", this, null);
	}
	

}
