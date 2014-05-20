package com.do1.aqzhdj.activity.mine.box;

import android.os.Bundle;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

/**
 * 入党誓词
 * auth:YanFangqin
 * data:2013-4-24
 * thzhd
 */
public class DangLogoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.box_dangci);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "入党誓词", 0,"", null, null);
	}
	
}
