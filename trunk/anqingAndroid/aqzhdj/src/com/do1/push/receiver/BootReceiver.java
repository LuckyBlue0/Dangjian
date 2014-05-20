package com.do1.push.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.do1.push.client.NotificationService;
import com.do1.push.client.ServiceManager;
import com.do1.aqzhdj.R;

/**
 * 开机自启动 开启推送服务
 * @author YuanShuQiao
 *
 */
public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ServiceManager serviceManager = new ServiceManager(context);
		serviceManager.setNotificationIcon(R.drawable.ic_launcher);
		serviceManager.startService();
		serviceManager.stopService();
		//自启动推送服务
		intent = new Intent(context,NotificationService.class);
		context.startService(intent);
	}

}
