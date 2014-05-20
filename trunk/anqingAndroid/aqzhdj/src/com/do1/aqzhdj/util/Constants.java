package com.do1.aqzhdj.util;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;


import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CacheManager;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;
import cn.com.do1.component.content.SharedPreferencesProxy;
import cn.com.do1.component.service.DownLoadService;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.info.CircleInfo;
import com.do1.aqzhdj.info.LoginInfo;
import com.do1.aqzhdj.info.UserInfo;

public class Constants extends Application {

	public static String PINGYI_CONTENT = "";
	public static final String SHARED_NAME = "thzhd";
	public static final String MICHI = "qwdhop%k"; // 密钥
	private final static int CWJ_HEAP_SIZE = 8 * 1024 * 1024;// 设置最小heap内存为6MB大小
	private final static float TARGET_HEAP_UTILIZATION = 0.75f; // 增强程序堆内存的处理效率
	public static final int NAGIVATEBAR_ID_START = 100000;// 头部按钮的起始ID
	// public static final String MICHI="qwdhop%k";
	private MyUncaughtExceptionHandler mDefaultHandler;
	public UserInfo userInfo = UserInfo.getInstance(); // 登录保存的个人信息（不全）
	public static LoginInfo loginInfo = LoginInfo.getInstance(); // 登录保存的个人信息（不全）
	public static CircleInfo circleInfo = CircleInfo.getInstance(); // 圈子信息（不全）
	public static String SERVER_URL;
	public static List<Map<String, Object>> erjilist = new ArrayList<Map<String, Object>>();
	public static Cookie cookie; 
	public static String xietongname;
	public static String xietongpwd;
	
//	 public static String
//	 SERVER_RUL2="http://115.29.163.136:8088/zhdj/interface/";
//	 public static String
//	 SERVER_RUL2="http://web.do1mall.com:2013/zhdj/interface/";
	 public static String
	 SERVER_RUL2="http://183.63.138.178:2013/zhdj/interface/";
//	 public static String
	// SERVER_RUL2="http://183.63.138.179:2013/zhdj/interface/";
	// public static String
	// SERVER_RUL2="http://172.20.15.55:8088/zhdj/interface/";
	// public static String
	// SERVER_RUL2="http://192.168.1.48:2013/zhdj/interface/";
//	public static String SERVER_RUL2 = "http://113.108.203.98:2013/zhdj/interface/"; // 专线
	// public static String SERVER_RUL2 =
	// "http://219.136.91.63/zhdj/interface/";
	 
//	 public static String SERVER_IMG="http://115.29.163.136:8088/zhdj/";
//	 public static String SERVER_IMG="http://web.do1mall.com:2013/zhdj/";
	 public static String SERVER_IMG="http://183.63.138.178:2013/zhdj/";
	// public static String SERVER_IMG="http://183.63.138.179:2013/zhdj/";
	// public static String SERVER_IMG="http://172.20.15.55:8088/zhdj/";
	// public static String SERVER_IMG="http://192.168.1.48:2013/zhdj/";
//	public static String SERVER_IMG = "http://113.108.203.98:2013/zhdj/";
	// public static String SERVER_IMG = "http://219.136.91.63/zhdj/";

//	 public static String SERVER_HTML="http://115.29.163.136:8088/zhdj";
//	 public static String SERVER_HTML="http://web.do1mall.com:2013/zhdj";
	 public static String SERVER_HTML="http://183.63.138.178:2013/zhdj";
	// public static String SERVER_HTML="http://183.63.138.179:2013/zhdj";
	// public static String SERVER_HTML="http://172.20.15.55:8088/zhdj";
	// public static String SERVER_HTML="http://192.168.1.48:2013/zhdj";
//	public static String SERVER_HTML = "http://113.108.203.98:2013/zhdj";
	// public static String SERVER_HTML = "http://219.136.91.63/zhdj";

	public static SharedPreferencesProxy sharedProxy;
	public static int type;// 1、留言 2、活动
	// 屏幕高宽
	public static int SCR_WIDTH = 0;
	public static int SCR_HEIGHT = 0;
	// 二维码照片
	public static Bitmap mBitmap;
	public static String deviceId; // 设备号
	public static int RadioStatu;
	public int width = 0;
	public int height = 0;

	public int switType = 1;

	// 时间格式切换
	public SimpleDateFormat sdfReturn = new SimpleDateFormat(
			"yyyy/M/dd HH:mm:ss");
	public SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public TabHost indexTab;

	@Override
	public void onCreate() {
		super.onCreate();

		vmRunTime();
		AjaxCallback.setNetworkLimit(8);
		BitmapAjaxCallback.setIconCacheLimit(200);
		BitmapAjaxCallback.setCacheLimit(50);
		BitmapAjaxCallback.setPixelLimit(800 * 800);
		BitmapAjaxCallback.setMaxPixelLimit(6000000);

		mDefaultHandler = new MyUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler);
		SERVER_URL = getResources().getString(R.string.server_url);

		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		Display dispaly = windowManager.getDefaultDisplay();
		SCR_WIDTH = dispaly.getWidth();
		SCR_HEIGHT = dispaly.getHeight();

		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		deviceId = telephonyManager.getDeviceId();
		width = windowManager.getDefaultDisplay().getWidth();
		height = windowManager.getDefaultDisplay().getHeight();

		sharedProxy = SharedPreferencesProxy.getInstance(this, SHARED_NAME);
	}

	public void vmRunTime() {
		// 设置最小heap内存为6MB大小
		// VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);
		// 增强程序堆内存的处理效率
		// VMRuntime.getRuntime().setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
	}

	/**
	 * 退出程序
	 * 
	 */
	public void quit(final Context context) {

		String message = "是否退出应用程序?";
		Dialog dialog = new AlertDialog.Builder(context)
				.setTitle("程序退出确认")
				.setMessage(message)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent = new Intent(context,
								DownLoadService.class);
						context.stopService(intent);

						// for (int i = 0; i < activityList.size(); i++) {
						// if (null != activityList.get(i)) {
						// activityList.get(i).finish();
						// }
						// }
						try {
							ActivityManager activityMgr = (ActivityManager) Constants.this
									.getSystemService(ACTIVITY_SERVICE);
							activityMgr.restartPackage(getPackageName());
							File file = CacheManager.getCacheFileBaseDir();
							if (file != null && file.exists()
									&& file.isDirectory()) {
								for (File item : file.listFiles()) {
									item.delete();
								}
								file.delete();
							}
							context.deleteDatabase("webview.db");
							context.deleteDatabase("webviewCache.db");
							AQuery.clearCookies();
						} catch (Exception e) {
							Log.d("应用手动退出程序异常" + e.getMessage());
						}
						System.exit(0);
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).create();
		dialog.show();
	}

	/**
	 * 注销，退出
	 * 
	 * @param context
	 */
	public void exit(Context context) {
		// for (int i = 0; i < activityList.size(); i++) {
		// if (null != activityList.get(i)) {
		// activityList.get(i).finish();
		// }
		// }
		try {
			ActivityManager activityMgr = (ActivityManager) Constants.this
					.getSystemService(ACTIVITY_SERVICE);
			activityMgr.restartPackage(getPackageName());
			File file = CacheManager.getCacheFileBaseDir();
			if (file != null && file.exists() && file.isDirectory()) {
				for (File item : file.listFiles()) {
					item.delete();
				}
				file.delete();
			}
			context.deleteDatabase("webview.db");
			context.deleteDatabase("webviewCache.db");
			AQuery.clearCookies();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO:统一捕捉异常处理 User:YanFangqin Date:2013-5-2 ProjectName:thzhd
	 */
	private class MyUncaughtExceptionHandler implements
			UncaughtExceptionHandler {
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			ex.printStackTrace();
			Log.e("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			Log.e("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			Log.e("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			Log.e("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			if (!handleException(ex) && mDefaultHandler != null) {
				Log.e("????????????????????????????????????");
				Log.e("????????????????????????????????????");
				Log.e("????????????????????????????????????");
				Log.e("????????????????????????????????????");
				// 如果用户没有处理则让系统默认的异常处理器来处理
				mDefaultHandler.uncaughtException(thread, ex);
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Log.d("捕获全局异常信息:" + e.getMessage());
				}
				// 杀死线程，退出应用。
				Intent intent = new Intent(Constants.this,
						DownLoadService.class);
				Constants.this.stopService(intent);
				// for (int i = 0; i < activityList.size(); i++) {
				// if (null != activityList.get(i)) {
				// activityList.get(i).finish();
				// }
				// }
				/*
				 * try { ActivityManager activityMgr = (ActivityManager)
				 * BaseActivity.this .getSystemService(ACTIVITY_SERVICE);
				 * activityMgr.restartPackage(getPackageName()); } catch
				 * (Exception e) { Log.d("应用手动退出程序异常", e.getMessage()); }
				 */
				Log.e("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				Log.e("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				Log.e("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				Log.e("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				Log.e("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				int version = android.os.Build.VERSION.SDK_INT;
				ActivityManager activityMgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				if (version <= 7) {
					try {
						activityMgr.restartPackage(getPackageName());
					} catch (Exception e) {
					}
				} else {
					android.os.Process.killProcess(android.os.Process.myPid());
				}
				System.exit(0);
			}
		}

		/**
		 * @param 线程弹出自定义提示
		 * @param ex
		 * @return true:如果处理了该异常信息;否则返回false.
		 */
		private boolean handleException(Throwable ex) {
			if (ex == null) {
				return false;
			}
			// 使用Toast来显示异常信息
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					Toast.makeText(Constants.this, "sorry，程序出现异常，即将退出应用！",
							Toast.LENGTH_LONG).show();
					Looper.loop();
				}
			}.start();
			return true;
		}

	}

	/**
	 * aquery的内存控制
	 */
	@Override
	public void onLowMemory() {
		// clear all memory cached images when system is in low memory
		// note that you can configure the max image cache count, see
		// CONFIGURATION
		// clean the file cache with advance option
		long triggerSize = 2000000;
		// starts cleaning when cache size is larger than 3M
		long targetSize = 1000000;
		// remove the least recently used files until cache size is less than 2M
		AQUtility.cleanCacheAsync(this, triggerSize, targetSize);
		BitmapAjaxCallback.clearCache();
	}
}
