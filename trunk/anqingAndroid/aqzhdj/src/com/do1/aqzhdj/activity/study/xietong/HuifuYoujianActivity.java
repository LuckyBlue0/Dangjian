package com.do1.aqzhdj.activity.study.xietong;

import android.os.Bundle;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class HuifuYoujianActivity extends BaseActivity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_xietong_huifuyoujian);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_head_2, "取消",
				getString(R.string.huifuyoujian), R.drawable.btn_head_2, "发送", this, null);
	}
}
