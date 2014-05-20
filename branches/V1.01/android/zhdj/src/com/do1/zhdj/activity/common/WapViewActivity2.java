package com.do1.zhdj.activity.common;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import cn.com.do1.component.util.Log;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;

/**
 * TODO:webView支持放大缩小
 * User:YanFangqin
 * Date:2013-6-7
 * ProjectName:thzhd
 */
public class WapViewActivity2 extends BaseActivity{

	private String url = "";
	private String title = "";
	private WebView webView;
	private AnimationDrawable anim = null;
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wap_view);
		url = getIntent().getStringExtra("url");
		Log.i("url:" + url);
		title = getIntent().getStringExtra("title");
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", title, 0, "", null, null);
		initItems();
	}
	
	@Override
	public void onClick(View v) {
		finish();
	}
	
	public void initItems() {
		webView = (WebView) findViewById(R.id.webView);
		iv = (ImageView) this.findViewById(R.id.imageView1);
		Object ob = iv.getBackground();
		anim = (AnimationDrawable) ob;
		startAnim(); 

		// 设置WebView属性，能够执行JavaScript脚本
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);// 支持js
		webSettings.setAllowFileAccess(true);
		webSettings.setSupportZoom(true); // 是否支持页面放大功能
		webSettings.setBuiltInZoomControls(true);//放大缩小控件
		//设置自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		// 设置优先使用缓存
//		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//设置去掉页面滚动条占用的空隙
		webView.setWebViewClient(new MyWebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				view.getSettings().setBlockNetworkImage(false);
				stopAnim();
				// 结束
				super.onPageFinished(view, url);
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				view.getSettings().setBlockNetworkImage(true);
				startAnim();
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
			
		});

		// 自定义重写WebChromeClient
		webView.setWebChromeClient(new WebChromeClientSelf(WapViewActivity2.this));
		webView.loadUrl(url);
	}

	public void stopAnim() {
		iv.setVisibility(View.GONE);
		iv.post(new Runnable() {
			public void run() {
				anim.stop();
			}
		});
	}

	public void startAnim() {
		iv.setVisibility(View.VISIBLE);
		// 启动时就开户动画
		iv.post(new Runnable() {
			public void run() {
				anim.start();
			}
		});
	}

	// web视图客户端
	public class MyWebViewClient extends WebViewClient {
		public boolean shouldOverviewUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
	
}
