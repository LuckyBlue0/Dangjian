package com.do1.aqzhdj.activity.bbs.zhitongche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.TestIndexActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class ZhitongcheDetailActivity extends BaseActivity {
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhitongche_minyidiaocha);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "民意调查详情", R.drawable.btn_head_2, "提交", this,
				this);
		ListenerHelper.bindOnCLickListener(this, this,R.id.rightImage);
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	super.onClick(v);
	switch (v.getId()) {
	case R.id.rightImage:
		Toast.makeText(this, "提交成功", 1).show();
		finish();
		break;

	}
}
}
