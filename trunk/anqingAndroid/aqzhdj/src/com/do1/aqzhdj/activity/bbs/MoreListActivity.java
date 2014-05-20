package com.do1.aqzhdj.activity.bbs;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.AppTool;
import com.do1.aqzhdj.util.Listenertool;
import com.do1.aqzhdj.widght.scoll.SwitchViewDemoActivity;

public class MoreListActivity extends BaseActivity {
	public static final String TAG = "MoreActivity";
	private AppTool appTool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_main_view);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "关于我们", 0, "", this, this);

		appTool = new AppTool(this);
		try {
			aq.id(R.id.vesion_num).text(appTool.getVersionName());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		int[] resourceIds = {R.id.leftImage, R.id.welcome, R.id.about_us, R.id.feedback,
				R.id.clean_cache };
		// 监听工具类
		Listenertool.bindOnCLickListener(this, this, resourceIds);
	}

	// private Handler handler = new Handler() {
	// public void handleMessage(Message msg) {
	// // 判断服务器版本号和客户端的版本号是否相同
	// try {
	// if (appTool.getSystemVersionName().equals(
	// appTool.getVersionName())) {
	// aq.id(R.id.vesion_num).text(appTool.getSystemVersionName());
	// }
	// } catch (NameNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	// };
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.leftImage:
			finish();
			break;
		case R.id.welcome:
			Log.i(TAG, "点的欢迎页");
			constants.switType = 4;
			intent = new Intent(this, SwitchViewDemoActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// Toast.makeText(this, "该栏目正在建设中...", 1).show();
			break;

		case R.id.about_us:
			Log.i(TAG, "关于我们");
			intent = new Intent(this, OurInfoActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// Toast.makeText(this, "该栏目正在建设中...", 1).show();
			break;

		case R.id.feedback:
			Log.i(TAG, "意见反馈");
			intent = new Intent(this, MoreIdeaBackActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.clean_cache:
			Log.i(TAG, "清空缓存");
			new AlertDialog.Builder(MoreListActivity.this)
					.setTitle("温馨提示")
					.setMessage("您确认要清空缓存吗？")
					.setPositiveButton("确认",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									clearCacheFolder(
											MoreListActivity.this.getCacheDir(),
											System.currentTimeMillis());
									MoreListActivity.this
											.deleteDatabase("webview.db");
									MoreListActivity.this
											.deleteDatabase("webviewCache.db");
									ToastUtil.showShortMsg(
											MoreListActivity.this, "清空缓存成功");
								}
							}).setNeutralButton("取消", null).create().show();
			break;
		default:
			break;
		}
	}

	/**
	 * 清空缓存
	 * 
	 * @param dir
	 * @param time
	 * @return
	 */
	private int clearCacheFolder(File dir, long time) {
		int deletedFiles = 0; // 清除的文件数
		if (dir != null && dir.isDirectory()) {
			try {
				for (File child : dir.listFiles()) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, time);
					}
					if (child.lastModified() < time) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}
}

// private boolean isNeedUpdate(String versiontext) {
// UpdataInfoService infoService = new UpdataInfoService(this);
// try {
// UpdataInfo info = infoService.getUpdataInfo(R.string.updataurl);
// String version = info.getVersion();
// if (versiontext.equals(version)) {
// Log.i(TAG, "版本相同,无需升级, 进入主界面");
// loadMainUI();
// return false;
// } else {
// Log.i(TAG, "版本不同,需要升级");
// return true;
// }
// } catch (Exception e) {
// e.printStackTrace();
// Toast.makeText(this, "获取更新信息异常", 0).show();
// Log.i(TAG, "获取更新信息异常, 进入主界面");
// loadMainUI();
// return false;
// }
// }