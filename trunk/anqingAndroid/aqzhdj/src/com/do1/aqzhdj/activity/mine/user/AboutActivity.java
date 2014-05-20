package com.do1.aqzhdj.activity.mine.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.info.VersionUpdateCusService;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.widght.scoll.SwitchViewDemoActivity;

public class AboutActivity extends BaseActivity {

	private RelativeLayout relViewManage;
	private RelativeLayout relViewStart;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		initView();
		
		VersionUpdateCusService updateService = VersionUpdateCusService.getInstance(this);
		String versionCode = updateService.getVersion();
		((TextView)findViewById(R.id.versionCode)).setText("v" + versionCode);
	}

	private void initView() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"关于我们", 0, "", null, null);
		
		relViewStart = (RelativeLayout) findViewById(R.id.relViewStart);
		relViewStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Constants.sharedProxy.putBoolean("isFirst", false);
				Constants.sharedProxy.commit();
				System.gc();
				Intent intent = new Intent(AboutActivity.this, SwitchViewDemoActivity.class);
				constants.switType = 2;
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});

//		relViewManage = (RelativeLayout) findViewById(R.id.relViewManage);
//		relViewManage.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//				intent = new Intent(AboutActivity.this,
//						ViewMessageActivity.class);
//				startActivity(intent);
//			} 
//		});
		
		LinearLayout linear = (LinearLayout) findViewById(R.id.phone);
		linear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toCall("87712371");
			}
		});
	}
	
	public void toCall(String phone) {
		Intent mobileIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ phone));
		startActivity(mobileIntent);
	}
}
