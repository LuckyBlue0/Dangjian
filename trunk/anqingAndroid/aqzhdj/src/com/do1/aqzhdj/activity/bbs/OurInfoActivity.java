package com.do1.aqzhdj.activity.bbs;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.webkit.WebView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.AppTool;

public class OurInfoActivity extends BaseActivity {
	private AppTool appTool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_about_us);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "关于我们", 0, "", this, this);
		appTool = new AppTool(this);
		try {
			aq.id(R.id.vesion_num).text(appTool.getVersionName());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		// WebView vebView = (WebView) findViewById(R.id.vebView);
		// vebView.loadUrl("file:///android_asset/aboutus.html");
	}
}
