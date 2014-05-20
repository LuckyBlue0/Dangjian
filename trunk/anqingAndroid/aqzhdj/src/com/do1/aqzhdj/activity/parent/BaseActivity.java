package com.do1.aqzhdj.activity.parent;

import java.text.ParseException;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.com.do1.component.core.IRequest;
import cn.com.do1.component.core.RequestManager;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.ValidUtil;
import com.do1.aqzhdj.widght.DefaultDataParser;

/**
 * 实现请求的父类
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class BaseActivity extends Activity implements IRequest,OnClickListener{

	private IRequest mRequest;
	public AQuery aq;
	public String SERVER_URL;
	public Constants constants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		mRequest = RequestManager.getDefaultRequstImpl(this);
		aq = new AQuery(this);
		constants = (Constants) getApplication();
//		constants.activityList.add(this);
		SERVER_URL = Constants.SERVER_RUL2;
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	@Override
	protected void onStart() {
		super.onStart();
//		if(com.maxicn.map.MaxicnMapActivity.flag == 1){
//			constants.indexTab.setCurrentTabByTag("党讯");
//		}else if(com.maxicn.map.MaxicnMapActivity.flag == 2){
//			constants.indexTab.setCurrentTabByTag("我的");
//		}else if(com.maxicn.map.MaxicnMapActivity.flag == 3){
//			constants.indexTab.setCurrentTabByTag("论坛");
//		}else if(com.maxicn.map.MaxicnMapActivity.flag == 4){
//			constants.indexTab.setCurrentTabByTag("群组");
//		}  
//		if(com.maxicn.map.MaxicnMapActivity.flag != 5){
//			clearCache();
//		}
//		com.maxicn.map.MaxicnMapActivity.flag = 5;
	}
	
	 
	
	@Override
	public boolean doRequest(int requestId, String url) {
		
		return mRequest.doRequest(requestId, url);
	}

	@Override
	public boolean doRequest(int requestId, String url,
			Map<String, String> params) {
		
		String key;
		try {
			Log.i("提交请求时间...");
//			key = Entryption.encode(Entryption.getJsonKey2(params));
//			return mRequest.doRequestPostString(requestId, url, key);
			System.out.println("requesturl: " + url);
			return mRequest.doRequest(requestId, url, params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		mRequest.onExecuteFail(requestId, resultObject);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
//		mRequest.onExecuteSuccess(requestId, resultObject);
	}

	@Override
	public void onNetworkError(int requestId) {
		
		mRequest.onNetworkError(requestId);
		Log.e("网络异常");
		new AlertDialog.Builder(this)
		.setTitle("温馨提示")
		.setMessage("网络加载速度慢，请稍后重试!")
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface dialog,
							int whichButton) {
						dialog.dismiss();
					}
				}).create().show();
	}

	@Override
	public ResultObject parseData(int requestId, String data) {
		return DefaultDataParser.getInstance().parseData(data);
	}

	@Override
	public void setProgressDialog(Dialog dialog) {
		
		mRequest.setProgressDialog(dialog);
	}

	@Override
	public void setProgressDialog(int dialogId) {
		
		mRequest.setProgressDialog(dialogId);
	}

	@Override
	public void setProgressMode(int mode) {
		
		mRequest.setProgressMode(mode);
	}

	@Override
	public void setProgressMsg(String title, String msg) {
		
		mRequest.setProgressMsg(title, msg);
	}

	@Override
	public void setRequestMode(int mode) {
		
		mRequest.setRequestMode(mode);
	}

	@Override
	public boolean doRequestPostString(int requestId, String url, String params) {
		return mRequest.doRequestPostString(requestId, url, params);
	}
	
	/**
	 * 设置头部
	 * @param headView			头部布局
	 * @param leftDrawable      头部左边按钮，传0默认不显示按钮
	 * @param title				头部标题
	 * @param rightDrawable		头部右边按钮，传0默认不显示按钮
	 * @param leftListener		头部左边按钮监听器，传null默认为返回
	 * @param rightListener     头部右边按钮监听器
	 */
	public void setHeadView(View headView,int leftDrawable,String leftText,String centerTitle,int rightDrawable,String rightText,OnClickListener leftListener,OnClickListener rightListener){
		if(headView != null){
			
			if(leftDrawable != 0){
				headView.findViewById(R.id.leftImage).setVisibility(View.VISIBLE);
				((TextView)headView.findViewById(R.id.leftImage)).setText(leftText);
				headView.findViewById(R.id.leftImage).setBackgroundResource(leftDrawable);
				headView.findViewById(R.id.leftImage).setOnClickListener(leftListener == null ? this : leftListener);
			}
			if(rightDrawable != 0){
				headView.findViewById(R.id.rightImage).setVisibility(View.VISIBLE);
				((TextView)headView.findViewById(R.id.rightImage)).setText(rightText);
				headView.findViewById(R.id.rightImage).setBackgroundResource(rightDrawable);
				headView.findViewById(R.id.rightImage).setOnClickListener(leftListener == null ? this : leftListener);
			}
			if(rightDrawable == R.drawable.btn_head_5 && centerTitle.length() > 6){
				centerTitle = centerTitle.substring(0, 5) + "...";
			}else if(rightDrawable == R.drawable.btn_head_4 && centerTitle.length() > 7){
				centerTitle = centerTitle.substring(0, 6) + "...";
			}else if(centerTitle.length() > 8){
				centerTitle = centerTitle.substring(0, 7) + "...";
			}
			((TextView)headView.findViewById(R.id.centerTitle)).setText(centerTitle);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.leftImage:
			finish();
			break;
		}
		
	}
	
	/**
	 * 将返回来的时间转换成yyyy-MM-dd HH:mm格式
	 * @param returnTime
	 * @return
	 */
	public String toTime(String returnTime){
		try {
			return constants.sdfTime.format(constants.sdfReturn.parse(returnTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将返回来的时间转换成yyyy-MM-dd格式
	 * @param returnTime
	 * @return
	 */
	public String toDate(String returnTime){
		try {
			return constants.sdfDate.format(constants.sdfReturn.parse(returnTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * time类型转Date类型
	 * @param returnTime
	 * @return
	 */
	public String timeToDate(String returnTime){
		try {
			return constants.sdfDate.format(constants.sdfTime.parse(returnTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据文字长度设置悬浮背景
	 * @param num
	 * @param view
	 */
	public void setFrameBg(String num,View view){
		if(!ValidUtil.isNullOrEmpty(num)){
			if(num.trim().length() == 1){
				view.setBackgroundResource(R.drawable.short_tool);
			}else if (num.trim().length() == 2){
				view.setBackgroundResource(R.drawable.mid_tool);
			}else{
				view.setBackgroundResource(R.drawable.long_tool);
			}
			((TextView)view).setText(num);
		}
	}
	
	/**
	 * 根据文字长度设置悬浮背景 超过10显示10+
	 * @param num
	 * @param view
	 */
	public void setFrameBgByTrim(String num,View view){
		if(!ValidUtil.isNullOrEmpty(num)){
			if(num.trim().length() == 1){
				view.setBackgroundResource(R.drawable.short_tool);
				((TextView)view).setText(num);
			}else if ("10".equals(num.trim())){
				view.setBackgroundResource(R.drawable.mid_tool);
				((TextView)view).setText(num);
			}else{
				view.setBackgroundResource(R.drawable.long_tool);
				((TextView)view).setText("10+");
			}
		}
	}
	
	@Override
	public void onLowMemory() { // 低内存消耗时
		System.gc();
		BitmapAjaxCallback.clearCache(); 
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.gc();
		BitmapAjaxCallback.clearCache(); 
	}
	
	public void clearCache(){
		long triggerSize = 2000000; 
		long targetSize = 1000000;   
		System.gc();
		AQUtility.cleanCacheAsync(this, triggerSize, targetSize); 
		BitmapAjaxCallback.clearCache();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		 long triggerSize = 2000000; 
		 long targetSize = 1000000;      
		 AQUtility.cleanCacheAsync(this, triggerSize, targetSize); 
		 BitmapAjaxCallback.clearCache(); 
	}
	
	public void register(){
		Constants.sharedProxy.putString("keyId", "");
		Constants.sharedProxy.putString("keyType", "");
		Constants.sharedProxy.putString("userId", "");
		Constants.sharedProxy.putString("password", "");
		Constants.sharedProxy.putBoolean("isAuto", false);
		Constants.sharedProxy.commit();
	}

	public void tankuan(String shenqing, String isqueren, String queren,
			Map<String, Object> map, String url) {
		// TODO Auto-generated method stub
		
	}
}
