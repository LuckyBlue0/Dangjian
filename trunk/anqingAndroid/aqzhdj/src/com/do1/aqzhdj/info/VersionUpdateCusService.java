package com.do1.aqzhdj.info;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.com.do1.component.net.DefaultAjaxCallBack;
import cn.com.do1.component.net.OnRequestListener;
import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.service.DownLoadService;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.StartActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.ValidUtil;

/**
 */
public class VersionUpdateCusService {

	private Activity act;
	protected AQuery aq;
	private boolean fileCache;
	private int progress;
	private long expire = 12 * 60 * 1000;
	private String updateUrl;//
	private boolean fetch;
	private boolean completed;
	private boolean force;
	private static VersionUpdateCusService sInstance;
	private boolean autoInstall = true;//
	CustomerDialog dialog;
	String version;
	public VersionInfo info = new VersionInfo();

	/**
	 * Instantiates a new MarketService.
	 * 
	 * @param act
	 *            Current activity.
	 */

	private VersionUpdateCusService(Activity act) {
		this.act = act;
		this.aq = new AQuery(act);
	}

	public static VersionUpdateCusService getInstance(Activity act) {
		// if (sInstance == null) {
		sInstance = new VersionUpdateCusService(act);
		// }
		return sInstance;
	}

	/**
	 * 
	 * @param autoInstall
	 *            url
	 * @return self
	 */
	public VersionUpdateCusService autoInstall(boolean autoInstall) {
		this.autoInstall = autoInstall;
		return this;
	}

	/**
	 * 
	 * @return self
	 */
	public VersionUpdateCusService force(boolean force) {
		this.force = force;
		return this;
	}

	/**
	 * Display a progress view during version check.
	 * 
	 * @param id
	 *            view id
	 * @return self
	 */
	public VersionUpdateCusService progress(int id) {
		this.progress = id;
		return this;
	}

	/**
	 * Set ajax request to be file cached.
	 * 
	 * @param cache
	 *            the cache
	 * @return self
	 */
	public VersionUpdateCusService fileCache(boolean cache) {
		this.fileCache = cache;
		return this;
	}

	/**
	 * The time duration which last version check expires. Default is 10 hours.
	 * 
	 * @param expire
	 *            expire time in milliseconds
	 * @return self
	 */

	public VersionUpdateCusService expire(long expire) {
		this.expire = expire;
		return this;
	}

	private static ApplicationInfo ai;

	private ApplicationInfo getApplicationInfo() {

		if (ai == null) {
			ai = act.getApplicationInfo();
		}

		return ai;
	}

	private static PackageInfo pi;

	private PackageInfo getPackageInfo() {

		if (pi == null) {
			try {
				pi = act.getPackageManager().getPackageInfo(getAppId(), 0);

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return pi;
	}

	private String getAppId() {

		return getApplicationInfo().packageName;
	}

	private Drawable getAppIcon() {
		Drawable d = getApplicationInfo().loadIcon(act.getPackageManager());
		return d;
	}

	public String getVersion() {
		return getPackageInfo().versionName;
	}

	public int getVersionCode() {
		return getPackageInfo().versionCode;
	}

	/**
	 * Perform a version check.
	 * 
	 * @param cb
	 */
	public void checkVersion(DefaultAjaxCallBack<String> cb,
			Map<String, String> maps, String url) {
		cb.setOnRequestListener(mOnRequestListener);
		// String key = "";
		// try {
		// key = Entryption.encode(Entryption.getJsonKey2(maps));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// cb.url(url).params(key).type(String.class).fileCache(fileCache).expire(expire);
		cb.url(url).params(maps).type(String.class).fileCache(fileCache)
				.expire(expire);
		aq.progress(progress).ajax(cb);
	}

	public void checkVersion(DataParser<String> dataParser,
			Map<String, String> maps, String url) {
		checkVersion(new DefaultAjaxCallBack<String>(dataParser,
				mOnRequestListener), maps, url);
	}

	/**
	 * @param desc
	 */
	protected void showUpdateDialog(String desc) {
		if (!isActive())
			return;

		if (TextUtils.isEmpty(desc)) {
			desc = "";
		}
		Context context = act;
		dialog = new CustomerDialog(context, Html.fromHtml(patchBody(desc))
				.toString(), version);
		/*
		 * final AlertDialog dialog = new AlertDialog.Builder(context)
		 * .setIcon(icon).setTitle(title) .setPositiveButton(updateLabel,
		 * mOnRequestListener) // .setNeutralButton(skip, mOnRequestListener)
		 * .setNegativeButton(cancelLabel, mOnRequestListener).create();
		 * dialog.setMessage(Html.fromHtml(patchBody(desc)));
		 */
		dialog.setBtnCancelListener(queryListener);
		dialog.setBtnOkListener(queryListener);
		aq.show(dialog);
	}

	private OnClickListener queryListener = new OnClickListener() {
		@Override
		public void onClick(View paramView) {
			switch (paramView.getId()) {
			case R.id.later_btn:
				if (act instanceof StartActivity)
					((StartActivity) act).startAnim();
				dialog.dismiss();
				break;
			case R.id.now_btn:
				if (act instanceof StartActivity)
					((StartActivity) act).startAnim();
				dialog.dismiss();
				// updateUrl =
				// "http://172.20.15.29:8080/testweb/upload/thzhd.apk";
				// updateUrl = "http://www.ith71.cn/phone/ith71_android.apk";
				Log.e("下载安装包的URL：" + updateUrl);
				handlePositiveClick(updateUrl);
				break;
			default:
				break;
			}
		}
	};

	private static String patchBody(String body) {
		return "<small>" + body + "</small>";
	}

	private boolean isActive() {
		if (act.isFinishing())
			return false;
		return true;
	}

	/**
	 */
	protected void noNeedUpdate() {
		if (force) {
			if (!(act instanceof StartActivity)) {
				Toast.makeText(act, "您的客户端是最新版本，不需要更新", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	/**
	 * @param url
	 */
	protected void handlePositiveClick(String url) {
		Intent service = new Intent(act, DownLoadService.class);
		service.putExtra(DownLoadService.URL, updateUrl);
		service.putExtra(DownLoadService.AUTO_INSTALL, autoInstall);
		act.startService(service);
	}

	protected void handleNeutralclick() {

	}

	protected void handleNegativeClick() {

	}

	protected OnRequestListenerWrapper mOnRequestListener = new OnRequestListenerWrapper();

	private class OnRequestListenerWrapper implements OnRequestListener {

		@Override
		public void onNetworkError() {
			if (act instanceof StartActivity) {
				((StartActivity) act).startAnim();
			}
			if (force) {
				// Toast.makeText(act, "您的网络出现异常，请检查",Toast.LENGTH_LONG).show();
				Log.i("网络异常，没有获取到检测版本更新数据");
				noNeedUpdate();
			}
		}

		@Override
		public void onExecuteSuccess(ResultObject resultObject) {
			Log.d("返回数据成功");
			// Object other = resultObject.getOther();
			// Object ojb=resultObject.getDataMap().get("newVersion");
			if (resultObject.getDataMap().get("newVersion") != null) {
				try {
					Map<String, Object> map = JsonUtil.json2Map(new JSONObject(
							resultObject.getDataMap().get("newVersion") + ""));
					version = map.get("versionNum").toString();
					if (compareVersion(getVersion(), map.get("versionNum")
							.toString())) {
						updateUrl = Constants.SERVER_RUL2
								+ map.get("filePath").toString();
						showUpdateDialog(map.get("remark").toString());
					} else {
						if (act instanceof StartActivity) {
							((StartActivity) act).startAnim();
						}
						noNeedUpdate();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// noNeedUpdate();
				}
			}else {
				if (act instanceof StartActivity) {
					((StartActivity) act).startAnim();
				}
				noNeedUpdate();
			}
			// if (other instanceof VersionInfo){
			// info = (VersionInfo) other;
			// if(compareVersion(getVersion(), info.getVersion())){
			// updateUrl = info.getUrl();
			// showUpdateDialog(info.getDesc());
			// }else{
			// if(act instanceof StartActivity){
			// ((StartActivity)act).startAnim();
			// }
			// noNeedUpdate();
			// }
			// }
			//
			// else{
			// Log.w("解析出现问题");
			// }
		}

		@Override
		public void onExecuteFail(ResultObject resultObject) {
			Log.d("返回数据失败");
			if (act instanceof StartActivity) {
				((StartActivity) act).startAnim();
			}
			noNeedUpdate();
		}

	};

	/**
	 * 匹配版本
	 * 
	 * @param curV
	 *            当前版本
	 * @param newV
	 *            最新版本
	 * @return
	 */
	public static boolean compareVersion(String curV, String newV) {
		String[] curVs = curV.split("[.]");
		String[] netVs = newV.split("[.]");
		int cSize = curVs.length;
		int nSize = netVs.length;

		int size = (cSize > nSize) ? nSize : cSize;

		for (int i = 0; i < size; i++) {
			if (!ValidUtil.isNumeric(curVs[i])
					|| !ValidUtil.isNumeric(netVs[i])) {
				return false;
			}
			int c = Integer.valueOf(curVs[i]);
			int n = Integer.valueOf(netVs[i]);
			System.out.println(c + ":" + n);
			if (c < n)
				return true;
			else if (c > n)
				return false;
		}
		if (nSize > cSize)
			return true;
		else
			return false;
	}

	public static class VersionInfo {
		private String version;
		String url;
		String desc;

		String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

	}
}
