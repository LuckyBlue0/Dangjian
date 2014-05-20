/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.do1.push.client;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;

import com.do1.zhdj.activity.IndexActivity;
import com.do1.zhdj.activity.StartActivity;
import com.do1.zhdj.activity.infomation.InfomationActivity;
import com.do1.zhdj.activity.parent.AppManager;

/** 
 * This class is to notify the user of messages with NotificationManager.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class Notifier {

    private static final Random random = new Random(System.currentTimeMillis());

    private Context context;

    private SharedPreferences sharedPrefs;

    private NotificationManager notificationManager;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Notifier(Context context) {
        this.context = context;
        this.sharedPrefs = context.getSharedPreferences(
                Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    /**
     * 将推送信息写入日志
     * @param strcontent
     * @param strFilePath
     */
    public static void WriteTxtFile(String strcontent, String strFilePath) {
		// 每次写入时，都换行写
		String strContent = strcontent + "\n";
		try {
			File file = new File(strFilePath);
			if (!file.exists()) {
				Log.d("TestFile:" + "Create the file:" + strFilePath);
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(file.length());
			raf.write(strContent.getBytes());
			raf.close();
		} catch (Exception e) {
			Log.e("TestFile:" + "Error on write File.");
		}
	}

    public void notify(String notificationId, String apiKey, String title,
            String message, String uri) {
        Log.d("notify()...");
        Log.d("notificationId=" + notificationId);
        Log.d("notificationApiKey=" + apiKey);
        Log.d("notificationTitle=" + title);
        Log.d("notificationMessage=" + message);
        Log.d("notificationUri=" + uri);

        if (isNotificationEnabled()) {
            // Show the toast
            if (isNotificationToastEnabled()) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

            // Notification
            Notification notification = new Notification();
            notification.icon = getNotificationIcon();
            notification.defaults = Notification.DEFAULT_LIGHTS;
            if (isNotificationSoundEnabled()) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            if (isNotificationVibrateEnabled()) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.when = System.currentTimeMillis();
            notification.tickerText = message;
            //分解uri
            String type = "";//类型
            String url = "";//url
         
//            String path = Environment.getExternalStorageDirectory().getPath();
            WriteTxtFile(uri.toString()+ "--" + sdf.format(new Date()) + "\r\n     ", "/sdcard/pushContent.txt");
            try { 
				if(uri.contains("{")&&uri.contains("}")){ //如果满足此条件 则为正常的消息
					Map<String, Object> maps = JsonUtil.json2Map(new JSONObject(uri));
					type = maps.get("type")+"";
					url = maps.get("url")+"";
				}
			} catch (Exception e) {
				Log.e(e.getMessage());
			}
			
			if("".equals(type.trim())){
				Log.i("======================无效推送信息：类型为空=====================");
        		return; 
			}
			
			String mapjson = com.do1.zhdj.util.Constants.sharedProxy.getString(AppManager.PUSH_KEY, "{}");
			try {
				Map<String, Object> map = JsonUtil.json2Map(new JSONObject(mapjson));
				if(null == map.get(type.trim()) || "".equals(map.get(type.trim()))){
					map.put(type.trim(), 1+"");//如果第一次接收到推送信息，则该类别设置为第一条
				}else{
					map.put(type.trim(), (Integer.parseInt(map.get(type.trim())+"")+1)+"");//如果不是第一次推送改列别信息，则添加一条该类别信息数量
				}
				JSONObject json = new JSONObject(map);
				com.do1.zhdj.util.Constants.sharedProxy.putString(AppManager.PUSH_KEY, json.toString());
				com.do1.zhdj.util.Constants.sharedProxy.commit();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			if(AppManager.contextManager != null){
				Intent myIntent = new Intent();
			    myIntent.setAction(InfomationActivity.ACTION_INTENT_PUSH);
			    myIntent.putExtra("type", type);
			    context.sendBroadcast(myIntent);
			}else{
				AppManager.HAVE_PUSH = true;
			}
			
			Intent intent;
			if(com.do1.zhdj.util.Constants.loginInfo != null && com.do1.zhdj.util.Constants.loginInfo.isLogin()){
				intent = new Intent(context,IndexActivity.class);
			}else{
				intent = new Intent(context,StartActivity.class);
			}
			
//	        intent.putExtra("url", url);
//	        intent.putExtra("title", getTitleByType(Integer.parseInt(type)));
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
	        PendingIntent contentIntent = PendingIntent.getActivity(context, random.nextInt(),intent, PendingIntent.FLAG_UPDATE_CURRENT);
	        notification.setLatestEventInfo(context, title, message,contentIntent);
	        notificationManager.notify(random.nextInt(), notification);
        } else {
            Log.w("Notificaitons disabled.");
        }
    }
    
    /**
	 * 设置列表标题
	 * @param type
	 * @return
	 */
	public String getTitleByType(int type){
		String title = "";
		switch (type) {
			case 1:
				title = "通知公告";
				break;
			case 2:
				title = "热点新闻";	
				break;
			case 3:
				title = "学习十八大";
				break;
			case 4:
				title = "党员先锋 ";
				break;
			case 6:
				title = "干部工作 ";
				break;
			case 7:
				title = "人才天地 ";
				break;
		}
		return title;
	}

    private int getNotificationIcon() {
        return sharedPrefs.getInt(Constants.NOTIFICATION_ICON, 0);
    }

    private boolean isNotificationEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_NOTIFICATION_ENABLED,
                true);
    }

    private boolean isNotificationSoundEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_SOUND_ENABLED, true);
    }

    private boolean isNotificationVibrateEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_VIBRATE_ENABLED, true);
    }

    private boolean isNotificationToastEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_TOAST_ENABLED, false);
    }

}
