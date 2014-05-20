package com.do1.zhdj.activity.common;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.IndexActivity;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.info.UserInfo;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.StringUtil;

/**
 * TODO:公用Webview User:YanFangqin Date:2013-6-7 ProjectName:thzhd
 */
public class WapViewActivity extends BaseActivity {

	private String url = "";
	private String title = "";
	private WebView webView;
	private AnimationDrawable anim = null;
	private ImageView iv;
	public String newsInfoId, newsInfoType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wap_view);
		newsInfoId = getIntent().getStringExtra("newsInfoId");
		newsInfoType = getIntent().getStringExtra("newsInfoType");
		System.out.println("newsInfoId: " + newsInfoId);
		System.out.println("wapView.newsInfoType: " + newsInfoType);
		// url = getIntent().getStringExtra("url");
		// Log.i("url:" + url);
		title = getTitleByType(Integer.parseInt(newsInfoType));

		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", title, 0, "", null, null);
		request();

	}

	public void request() {
		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("newsInfoId", getIntent().getStringExtra("newsInfoId"));
			map.put("newsInfoType", getIntent().getStringExtra("newsInfoType"));
			String url = Constants.SERVER_RUL2
					+ getResources().getString(R.string.newdetail);
			// doRequestPostString(LOGIN, url,"requestJson="+toJsonString(map));
			doRequest(1, url, StringUtil.jiami(map));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		// ToastUtil.showShortMsg(this, "成功");

		Map<String, Object> map = resultObject.getDataMap();
		url = Constants.SERVER_HTML + map.get("contentUrl");
		System.out.println("url: " + url);
		initItems();
		// Map<String, Object> resultMap = json2Map(new JSONObject(dataKey));
		// Map<String, Object> map = resultObject.getDataMap();
		// try {
		// Map<String, Object> userMap = JsonUtil.json2Map(new JSONObject(map
		// .get("loginUserInfo") + ""));
		// // Object dataObject = getValueFromMap(resultMap, key, null);
		// System.out.println("map: " + map);
		// finish();
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		//
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
		// 设置自适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		// 设置优先使用缓存
		// webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);// 设置去掉页面滚动条占用的空隙
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
		webView.setWebChromeClient(new WebChromeClientSelf(WapViewActivity.this));
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

	public String getTitleByType(int type) {
		String title = "";
		switch (type) {
		case 0:
			title = "热点新闻详情";
			break;
		case 1:
			title = "通知公告详情";
			break;
		case 2:
			title = "工作动态详情";
			break;
		case 3:
			title = "组织风采详情";
			break;
		case 4:
			title = "党员先锋";
			break;
		case 6:
			title = "干部工作";
			break;
		case 5:
			title = "组织风采";
			break;
		case 7:
			title = "人才天地";
			break;
		}
		return title;
	}

	// web视图客户端
	public class MyWebViewClient extends WebViewClient {
		public boolean shouldOverviewUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
