package com.do1.aqzhdj.activity.bbs;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.util.Listenertool;

public class DuesBBSDetailActivity extends BaseActivity {
	
	private WebView mweb;
	private String id;
	private String title;
	private String webUrl;
	private int page = 1;
	private String pageCount;
	
	public static boolean flag = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_bbs_detail);
		
		init();
//		AppManager.needFleshForbbs = true;
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
//		if(flag){
//			flag = false;
//			request(page);
//		}
	}

	private void init() {
		id = getIntent().getExtras().getString("id");
		title = getIntent().getExtras().getString("title");
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "帖子详情",R.drawable.btn_head_2, "回帖", this, this);
		mweb = (WebView) findViewById(R.id.web1);
		mweb.loadUrl("file:///android_asset/tie_detail.html");
		int[] ids = {R.id.start,R.id.up,R.id.next,R.id.down};
		Listenertool.bindOnCLickListener(this, this, ids);
		
	}
	
	private void request(int pageNo){
		try {
			String url = Constants.SERVER_URL +"GetPartyForumDital.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("forum_id", id);
			map.put("page_no", pageNo);
			String param = Entryption.encode(toJsonString(map));
			doRequestPostString(0, url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public String toJsonString(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		switch (requestId) {
		case 0:
			webUrl = resultObject.getDataMap().get("content_url").toString().trim();
			pageCount = resultObject.getDataMap().get("total_pages").toString().trim();
			break;
			
		default:
			break;
		}
		aq.id(R.id.content).text(page+"/"+pageCount);
		
		Log.e(webUrl);
		mweb.loadUrl(webUrl);
		mweb.getSettings().setJavaScriptEnabled(true);//设置支撑javascript
//		mweb.getSettings().setAllowFileAccess(true);//设置可以反问文件
//		mweb.getSettings().setBuiltInZoomControls(true);//设置支持缩放
		
		mweb.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		
		mweb.setWebChromeClient(new WebChromeClient(){
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				Builder builder = new Builder(DuesBBSDetailActivity.this); 
				builder.setTitle("信息提示");
				builder.setMessage(message); 
				builder.setPositiveButton("确定", new AlertDialog.OnClickListener(){  				
					public void onClick(DialogInterface arg0, int arg1) {
						result.confirm(); 
					}                       
				});  
				builder.setCancelable(false);  
				builder.create();  
				builder.show();  
				return true; 
			}

			public boolean onJsConfirm(WebView view, String url,String message, final JsResult result) {
				Builder builder = new Builder(DuesBBSDetailActivity.this); 
				builder.setTitle("信息确认");
				builder.setMessage(message); 
				builder.setPositiveButton("确定", new AlertDialog.OnClickListener(){  				
					public void onClick(DialogInterface arg0, int arg1) {
						result.confirm(); 
					}                       
				});  
				builder.setNeutralButton("取消", new AlertDialog.OnClickListener(){  				
					public void onClick(DialogInterface arg0, int arg1) {
						result.cancel();
					}                       
				});
				builder.setCancelable(false);  
				builder.create();  
				builder.show(); 
				return true;
			}
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				DuesBBSDetailActivity.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress*100);
				super.onProgressChanged(view, newProgress);
			}
			
			@Override
			public void onReceivedTitle(WebView view, String title) {
				DuesBBSDetailActivity.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			Intent intent = new Intent(DuesBBSDetailActivity.this,SendRequestActivty.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("title", title);
			intent.putExtra("id", id);
			startActivity(intent);
			break;
			
//		case R.id.start:
//			page = 1;
//			aq.id(R.id.content).text(page+"/"+pageCount);
//			request(1);
//			break;
//			
//		case R.id.up:
//			if(page == 1){
//				ToastUtil.showShortMsg(this, "当前页面是首页");
//				return;
//			}
//			page--;
//			request(page);
//			break;
//			
//		case R.id.next:
//			if(page == Integer.valueOf(pageCount)){
//				ToastUtil.showShortMsg(this, "没有下一页");
//				return;
//			}
//			if(page > Integer.valueOf(pageCount)){
//				return;
//			}
//			page++;
//			request(page);
//			break;
//			
//		case R.id.down:
//			if(page == Integer.valueOf(pageCount)){
//				ToastUtil.showShortMsg(this, "已经是最后一页");
//				return;
//			}
//			page = Integer.valueOf(pageCount);
//			aq.id(R.id.content).text(page+"/"+pageCount);
//			request(Integer.valueOf(pageCount));
//			break;

		default:
			break;
		}
	}
	

}
