package com.do1.aqzhdj.activity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.dqdp.android.common.HttpHelper;
import cn.com.do1.dqdp.android.control.InterfaceManager;

import com.do1.push.client.ServiceManager;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.user.LoginActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.anim.RotateEffect;
import com.do1.aqzhdj.info.AppInfo;
import com.do1.aqzhdj.info.UserInfo;
import com.do1.aqzhdj.info.VersionUpdateCusService;
import com.do1.aqzhdj.info.VersionUpdateCusService.VersionInfo;
import com.do1.aqzhdj.util.AppTool;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.StringUtil;
import com.do1.aqzhdj.widght.scoll.SwitchViewDemoActivity;

/**
 * 启动页面 auth:YanFangqin data:2013-4-28 thzhd
 */
public class StartActivity extends BaseActivity {

	public RotateEffect rotateEffect;
	public RelativeLayout startLayout;
	private Context context;

	private String userId;
	private String password;
	private boolean isAuto;

	private boolean isUnis;

	private static final int LOGIN = 99999;

	public Timer timer;
	public TimerTask task;
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// rotateEffect = new RotateEffect(StartActivity.this, 1);
			// rotateEffect.applyRotation(startLayout, 0, -90);//动画90度
			naviToIndex();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		context = this;
		startLayout = (RelativeLayout) this.findViewById(R.id.startLayout);
		InterfaceManager.initManager(this, R.xml.dqdp_interface);
		HttpHelper.setHttpPort(80);
		HttpHelper.setHttpPort(8081);

//		System.out.println("============install: "
//				+ Constants.sharedProxy.getString("install", ""));
//		String install = Constants.sharedProxy.getString("install", "");
//		if (!install.equals("1")) {
//			// DeviceIstall();
//
//			
//
//		}
		
		

		// 检测更新
		handler.post(new Runnable() {
			@Override
			public void run() {
				versionUpate();
			}
		});
		// isAt();
		isUnis = isAt();
		if (!isUnis) {
			// startAnim();
			startService();
		}
	}

	public void startService() {
		// 启动推送
		ServiceManager serviceManager = new ServiceManager(this);
		serviceManager.setNotificationIcon(R.drawable.ic_launcher);
		serviceManager.startService();
	}

	// 设备注册
	public void devicereg() {
		AppTool appTool = AppTool.getInstance(context);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Build bd = new Build();
		String model = bd.MODEL;
		try {
			map.put("osType", "1");
			map.put("deviceId", Constants.deviceId);
			map.put("osVersion", appTool.getSystemVersionName());
			map.put("appVersion", appTool.getVersionName());
			map.put("toKen", "2");
			map.put("deviceModel", model);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String url = Constants.SERVER_RUL2
				+ getResources().getString(R.string.installreg);
		System.out.println("url: " + url);
		doRequest(1, url, StringUtil.jiami(map));
	}

	public void DeviceIstall() {// 设备安装记录
		// AppTool appTool = new AppTool(context);
		AppTool appTool = AppTool.getInstance(context);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Build bd = new Build();
		String model = bd.MODEL;
		try {
			map.put("type", "1");
			map.put("equipmentNum", Constants.deviceId);// 设备ID
			map.put("versionNum", appTool.getSystemVersionName());// 系统版本号
			map.put("appVersionNum", appTool.getVersionName());// 客户端版本号
			map.put("deviceModel", model);
			map.put("deviceModel", appTool.getVersionName());//设备型号
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String url = Constants.SERVER_RUL2
				+ getResources().getString(R.string.installRecord);
		System.out.println("url: " + url);
		doRequest(1, url, StringUtil.jiami(map));
		System.out.println("DeviceIstall()被执行了");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		startAnim();
		startService();
	}

	public void startAnim() {
		if (timer == null)
			timer = new Timer();
		if (task == null)
			task = new MyTimerTask();
		timer.schedule(task, 500, 500);
	}

	private void stopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		if (task != null) {
			task.cancel();
			task = null;
		}
	}

	public void naviToIndex() {
		isAuto = Constants.sharedProxy.getBoolean("isAuto", isAuto);
//		if (Constants.sharedProxy.getBoolean("isFirst", true)) {
//			
//			DeviceIstall();// 安装记录
////			Constants.sharedProxy.putBoolean("isFirst", false);
////			Constants.sharedProxy.commit();
//			Intent intent = new Intent(context, SwitchViewDemoActivity.class);
////			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////			constants.switType = 1;
//////			startActivity(intent);
////			finish();
//		} else {
			if (isAuto) {
				userId = Constants.sharedProxy.getString("userId", userId);
				System.out.println("userId: " + userId);
				password = Constants.sharedProxy
						.getString("password", password);
				// aq.id(R.id.userId).getEditText().setText(userId);
				// aq.id(R.id.pwd).getEditText().setText(password);
				// setProgressMode(0);
				// setProgressMsg("提示:", "正在登陆,请稍后...");
				request();
				// Intent intent = new Intent(this, IndexActivity.class);
				// startActivity(intent);
				// finish();
			} else {
				Intent intent = new Intent(context, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			
				if (isAuto) {
					userId = Constants.sharedProxy.getString("userId", userId);
					System.out.println("userId: " + userId);
					password = Constants.sharedProxy.getString("password",
							password);
					// aq.id(R.id.userId).getEditText().setText(userId);
					// aq.id(R.id.pwd).getEditText().setText(password);
					// setProgressMode(0);
					// setProgressMsg("提示:", "正在登陆,请稍后...");
					request();
					// Intent intent = new Intent(this, IndexActivity.class);
					// startActivity(intent);
					// finish();
				} else {
					intent = new Intent(context, LoginActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();

				}
			}
		}
		// }

//	}

	public void request() {
		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			// map.put("login_name", userId);
			// map.put("pwd", password);

			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("platformType",
			// SecurityUtil.encryptToDES(Constants.MICHI, "1"));
			// map.put("username",
			// SecurityUtil.encryptToDES(Constants.MICHI, userId));
			// map.put("userPwd",
			// SecurityUtil.encryptToDES(Constants.MICHI, password));
			// map.put("digest", SecurityUtil
			// .encryptToMD5("1" + userId + password).toLowerCase());

			// String param = Entryption.encode(toJsonString(map));
			map.put("platformType", "1");
			map.put("username", userId);
			map.put("userPwd", password);
			map.put("deviceId", Constants.deviceId);
			// Map<String, String> map2 = new HashMap<String, String>();
			// map2.put("requestJson", toJsonString(map));
			String url = Constants.SERVER_RUL2
					+ getResources().getString(R.string.login);
			doRequest(LOGIN, url, StringUtil.jiami(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json + "";
	}

	public class MyTimerTask extends TimerTask {
		public void run() {
			timer.cancel();
			timer.purge();
			timer = null;
			handler.sendEmptyMessage(0);
		}
	}

	public void versionUpate() {
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("type", "1");
		// 注：默认自动安装，可通过VersionUpdateService.autoInstall方法来设置
		VersionUpdateCusService updateService = VersionUpdateCusService
				.getInstance(this);
		// updateService.autoInstall(true);
		// updateService.force(true);
		String url = Constants.SERVER_RUL2 + getString(R.string.update_version);
		updateService.checkVersion(DATAPARSER, StringUtil.jiami(dataMap), url);
		// doRequest(2, url, StringUtil.jiami(dataMap));
	}

	public static DataParser<String> DATAPARSER = new DataParser<String>() {

		@Override
		public ResultObject parseData(String datakey) {
			ResultObject result = new ResultObject();
			try {
				// Entryption.encode("");
				// String data = Entryption.decode(datakey);
				// Log.e("检测版本更新：" + datakey);
				VersionInfo versionInfo = new VersionUpdateCusService.VersionInfo();
				result.setOther(versionInfo);
				Map<String, Object> map = json2Map(new JSONObject(datakey));
				for (String key : map.keySet()) {
					if ("code".equals(key)) {
						String code = getValueFromMap(map, key, "");
						if ("0".equals(code))
							result.setSuccess(true);
						else
							result.setSuccess(false);
					} else if ("desc".equals(key)) {
						String desc = getValueFromMap(map, key, "");
						result.setDesc(desc);
					} else if ("data".equals(key)) {
						try {
							Object dataObject = getValueFromMap(map, key, null);
							Map<String, Object> dataMap = json2Map((JSONObject) dataObject); // datamap
							result.addDataMap(dataMap);

							// Map<String, Object> mapValues = json2Map(new
							// JSONObject(
							// map.get(key) + ""));
							// for (String keyValues : mapValues.keySet()) {
							// if ("fileSize".equals(keyValues)) {
							// String desc = getValueFromMap(mapValues,
							// keyValues, "");
							// versionInfo.setDesc(desc);
							// } else if ("filePath".equals(keyValues)) {
							// String url = getValueFromMap(mapValues,
							// keyValues, "");
							// versionInfo.setUrl(url);
							// } else if ("remark".equals(keyValues)) {
							// String version = getValueFromMap(mapValues,
							// keyValues, "1.0.0");
							// versionInfo.setVersion(version);
							// }
							// }
						} catch (Exception e) {
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	};

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		findViewById(R.id.progressBar).setVisibility(View.GONE);
		devicereg();
		if (requestId == LOGIN) {
			System.out.println("11111111111111");
			ToastUtil.showShortMsg(this, "登录成功");
			setAutoLogin();
			// Map<String, Object> resultMap = json2Map(new
			// JSONObject(dataKey));
			Map<String, Object> map = resultObject.getDataMap();
			try {
				Map<String, Object> userMap = JsonUtil.json2Map(new JSONObject(
						map.get("loginUserInfo") + ""));
				// Object dataObject = getValueFromMap(resultMap, key, null);
				System.out.println("map: " + map);
				constants.userInfo = UserInfo.getInstance();
				Constants.sharedProxy.putString("keyId", userMap.get("userId")
						+ "");
				Constants.sharedProxy.putString("keyType",
						userMap.get("userType") + "");
				Constants.sharedProxy.commit();
				Constants.loginInfo.setLogin(true);
				constants.userInfo.setUserId(userMap.get("userId") + "");
				constants.userInfo.setUserName(userMap.get("userName") + "");
				System.out.println("name: " + userMap.get("name"));
				constants.userInfo.setPassword(password);
				constants.userInfo.setBranchId(userMap.get("organizationId")
						.toString());
				constants.userInfo.setUser_type(userMap.get("userType")
						.toString());
				// constants.userInfo.setIs_can_test(map.get("is_can_test").toString());

				// List<Map<String, Object>> birthdays = new
				// ArrayList<Map<String,
				// Object>>();
				// birthdays =
				// JsonUtil.jsonArray2List(map.get("birthdays").toString());
				// constants.userInfo.setBirthdayMap(birthdays);
				constants.userInfo.setLogin(true);
				// HttpHelper.setGlobalParam(
				// getResources().getString(R.string.server_url), "user_id",
				// constants.userInfo.getUserId());
				// if (!birthdays.isEmpty()) {
				// Intent intent = new Intent(this, BirthdayActivity.class);
				// startActivity(intent);
				// } else {
				Intent intent = new Intent(this, IndexActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				// }
				finish();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
//		else if (requestId == 1) {
//			Constants.sharedProxy.putString("install", "1");
//			Constants.sharedProxy.commit();
//		}

		//
	}

	@Override
	public void onNetworkError(int requestId) {
		super.onNetworkError(requestId);
		stopTimer();
//		Constants.SERVER_RUL2="http://183.63.138.179:2013/zhdj/interface/";
//		Constants.SERVER_IMG="http://183.63.138.179:2013/zhdj/";
//		Constants.SERVER_HTML="http://183.63.138.179:2013/zhdj";
//		devicereg();
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		super.onExecuteFail(requestId, resultObject);
		ToastUtil.showLongMsg(context, resultObject.getDesc());
		Intent intent = new Intent(StartActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void setAutoLogin() {
		Constants.sharedProxy.putString("userId", userId);
		Constants.sharedProxy.putString("password", password);
		Constants.sharedProxy.putBoolean("isAuto", true);
		Constants.sharedProxy.commit();
	}

	public boolean isAt() {
		AppTool app = AppTool.getInstance(this);
		List<AppInfo> list = app.queryAllTheFirstAppInfo();
		String packName;
		final String oldPackName = "tianhe.cndatacom.com";
		for (int i = 0; i < list.size(); i++) {
			packName = list.get(i).getPkgName();
			if (packName.equals(oldPackName)) {
				uninstall(this, oldPackName);
				return true;
			}
		}
		return false;
	}

	public void uninstall(Context ctx, String packageName) {
		Uri packageURI = Uri.parse("package:" + packageName);
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
		((Activity) ctx).startActivity(uninstallIntent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopTimer();
	}

}