/**
 * 
 */
package com.do1.aqzhdj.activity.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebView;

/**
 * 对网页JS进行处理，目前已对alert，confirm进行处理
 * @author Kaven
 * 2011-2-22
 */
public class WebChromeClientSelf extends android.webkit.WebChromeClient {
	Context context;
	//定义提示信息
	Map<String, String> msTitle=new HashMap<String, String>();
	
	
	private void replaceMap(Map<String, String> map){
		Set<String> elSet=map.keySet();
		for(String el :elSet){
			msTitle.put(el, map.get(el));
		}
	}
	public WebChromeClientSelf(Context ctx){
		super();
		this.context=ctx;
		msTitle.put("alert", "温馨提示");
		msTitle.put("confirm", "确认操作");
		msTitle.put("Prompt", "输入信息框");
	}
	public WebChromeClientSelf(Context ctx,Map<String,String> map){
		this.context=ctx;
		replaceMap(map);
	}
	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		//构建一个Builder来显示网页中的对话框
		Builder builder = new Builder(this.context);
		builder.setTitle(msTitle.get("alert"));
		builder.setMessage(message);
		final JsResult nresult=result;
		builder.setPositiveButton(android.R.string.ok,
				new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						//点击确定按钮之后,继续执行网页中的操作
						nresult.confirm();
					}
				});
		builder.setCancelable(false);
		builder.create();
		builder.show();
		return true;
	};
	public boolean onJsConfirm(WebView view, String url, String message,
			final JsResult result) 
	{
		Builder builder = new Builder(this.context);
		builder.setTitle(msTitle.get("confirm"));
		builder.setMessage(message);
		builder.setPositiveButton(android.R.string.ok,
				new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				});
		builder.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.cancel();
					}
				});
		builder.setCancelable(false);
		builder.create();
		builder.show();
		return true;
	};
	
}
