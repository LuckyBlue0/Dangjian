package com.do1.zhdj.util;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * ������������
 * @author xiongsheng
 *
 */
public class Listenertool {
	public static void bindBackListener(final Activity activity,int backId){
		View view = activity.findViewById(backId);
		if (view !=null){
			view.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					activity.finish();
				}
			});
		}
	}
	
	/**
	 * ��Intent����
	 * @param activity
	 * @param resourceId
	 * @param intent ����
	 */
	public static void bindIntentListener(final Activity activity,int resourceId,final Intent intent){
		View view = activity.findViewById(resourceId);
		if (view !=null){
			view.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					activity.startActivity(intent);
				}
			});
		}
	}
	
	/**
	 * ����ת����
	 * @param activity
	 * @param resourceId ���������Դid
	 * @param toActivityClass Ҫ��ת��activityĿ��
	 */
	public static void bindJumpListener(final Activity activity,int resourceId,final Class<? extends Activity> toActivityClass){
		Intent intent = new Intent(activity,toActivityClass);
		bindJumpListener(activity, resourceId, intent);
	}
	
	public static void bindJumpListenerWithData(final Activity activity,int resourceId,final Class<? extends Activity> toActivityClass, Map<String, String> bindData){
		Intent intent = new Intent(activity,toActivityClass);
		for(String key : bindData.keySet()) {
			intent.putExtra(key, bindData.get(key));
		}
		bindJumpListener(activity, resourceId, intent);
	}
	
	/**
	 * ����ת����
	 * @param activity
	 * @param resourceId ���������Դid
	 * @param toActivityClass Ҫ��ת��activityĿ��
	 */
	public static void bindJumpListener(final Activity activity,int resourceId,final Intent intent){
		activity.findViewById(resourceId).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(intent);
			}
		});
	}
	
	
	/**
	 * �󶨵����¼�
	 * @param activity
	 * @param clickListener
	 * @param resourceIds
	 */
	public static void bindOnCLickListener(Activity activity,final OnClickListener clickListener,int... resourceIds){
		for (int resourceId:resourceIds) {
			View view = activity.findViewById(resourceId);
			view.setOnClickListener(clickListener);
		}
	}
	/**
	 * �󶨵����¼�
	 * @param activity
	 * @param clickListener
	 * @param resourceIds
	 */
	public static void bindOnCLickListener(final View parent,final OnClickListener clickListener,int... resourceIds){
		for (int resourceId:resourceIds) {
			View view = parent.findViewById(resourceId);
			view.setOnClickListener(clickListener);
		}
	}
}
