package com.do1.aqzhdj.activity.study.gongkao;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class Zhaolu_detailActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_zhaolu_detail);
		setHeadView(findViewById(R.id.headLayout), R.drawable.back_btn, "",
				"详情", 0, "", this, null);
	}

}
