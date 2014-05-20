package com.do1.aqzhdj.activity.mine;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class DuesLearnDetailActivity extends BaseActivity{
	
	private WebView mweb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_learn);
		mweb = (WebView) findViewById(R.id.web1);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "党员学习",0, "", this, this);
		
		String url = constants.userInfo.getPartyStuUrl();
		if(null == url || "".equals(url)){
			ToastUtil.showShortMsg(this, "暂时无法找到该页面");
			return;
		}
		mweb.loadUrl(url);
		mweb.getSettings().setJavaScriptEnabled(true);//设置支撑javascript
//		mweb.getSettings().setAllowFileAccess(true);//设置可以反问文件
		mweb.getSettings().setBuiltInZoomControls(true);//设置支持缩放
		
		mweb.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		
		mweb.setWebChromeClient(new WebChromeClient(){
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				Builder builder = new Builder(DuesLearnDetailActivity.this); 
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
				Builder builder = new Builder(DuesLearnDetailActivity.this); 
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
				DuesLearnDetailActivity.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress*100);
				super.onProgressChanged(view, newProgress);
			}
			
			@Override
			public void onReceivedTitle(WebView view, String title) {
				DuesLearnDetailActivity.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
	}

}
