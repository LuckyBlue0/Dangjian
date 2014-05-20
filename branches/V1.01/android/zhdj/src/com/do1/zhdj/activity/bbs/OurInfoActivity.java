package com.do1.zhdj.activity.bbs;

import android.os.Bundle;
import android.webkit.WebView;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;

public class OurInfoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_about_us);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "关于我们", 0, "", this, this);
		WebView vebView = (WebView) findViewById(R.id.vebView);
		vebView.loadUrl("file:///android_asset/aboutus.html");
	}
}
