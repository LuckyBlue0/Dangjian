package com.do1.aqzhdj.activity.mine.box;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.common.WapViewActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;

/**
 * 党章党徽党旗首页
 * auth:YanFangqin
 * data:2013-4-24
 * thzhd
 */
public class BoxDangActivity extends BaseActivity{

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.box_dang);
		context = this;
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "党章党徽党旗", 0,"", null, null);
		ListenerHelper.bindOnCLickListener(this, click, R.id.dangzhangLayout,R.id.danghuiLayout,R.id.dangqiLayout);
	}
	
	OnClickListener click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context,WapViewActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			switch (v.getId()) {
				case R.id.dangzhangLayout:
					intent.putExtra("url", "file:///android_asset/dangzhang.html");
					intent.putExtra("title", "党章");
					break;
				case R.id.danghuiLayout:
					intent.putExtra("url", "file:///android_asset/danghui.html");
					intent.putExtra("title", "党徽");
					break;
				case R.id.dangqiLayout:
					intent.putExtra("url", "file:///android_asset/dangqi.html");
					intent.putExtra("title", "党旗");
					break;
			}
			startActivity(intent);
		}
	};
}
