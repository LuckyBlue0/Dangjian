package com.do1.zhdj.activity.parent;

import android.content.Context;

/**
 * 收集activity列表
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class AppManager {

	public static final String PUSH_KEY = "pushJson";//推送数量保存关键字
	public static final String NEED_SEND_LOG = "needSendLog";//是否需要发送安装日志
	public static boolean HAVE_PUSH = false;
//	public static List<Activity> activityList = new ArrayList<Activity>(); // 用于存储activity(退出所用)
	public static Context contextManager;
	public static boolean needFlesh = false;//我的/创建群/图片上传页面，是否需要刷新
	public static boolean needFleshForbbs = false;//论坛，是否需要刷新
	public static boolean needFreshForCircleClass = false;//我的群组，回首页是否需要刷新
	public static boolean needFreshForCircleActivity = false;//我的活动，回首页是否需要刷新
	public static String singUrl = "";
}
