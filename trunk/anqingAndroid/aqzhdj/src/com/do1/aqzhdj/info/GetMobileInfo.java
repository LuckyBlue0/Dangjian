package com.do1.aqzhdj.info;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * 获取手机信息类
 * @author yanfangqin
 * @Date:2012-9-28
 * <qyoa>企业OA
 */
public class GetMobileInfo {
	
	/**
	 * 获取手机机型（手机名称）
	 * @return
	 */
	public static String getSystemName(){
		return android.os.Build.MODEL;
	}
	
	/**
	 * 获取手机操作系统
	 * @return
	 */
	public static String getSystem(){
		return "Android" + Build.VERSION.RELEASE;
	}
	
	/**
	 * 获取手机屏幕分辨率
	 * @return
	 */
	public static String getResolution(Context context){
		DisplayMetrics dm = new DisplayMetrics();   
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);   
		return dm.widthPixels + "*" + dm.heightPixels;
	}
	
	/**
	 * 获取手机CPU
	 * @return
	 */
	public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";   
        String str2="";
        String[] cpuInfo={"",""};   
        String[] arrayOfString;   
        try {   
            FileReader fr = new FileReader(str1);   
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);   
            str2 = localBufferedReader.readLine();   
            arrayOfString = str2.split("\\s+");   
            for (int i = 2; i < arrayOfString.length; i++) {   
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";   
            }   
            str2 = localBufferedReader.readLine();   
            arrayOfString = str2.split("\\s+");   
            cpuInfo[1] += arrayOfString[2];   
            localBufferedReader.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpuInfo;   
    }
	
	/**
	 * 获取手机内存
	 * @return
	 */
	public static String getMeMory(){
		
		String s = "/proc/meminfo";//系统内存信息文件
		String s2;
		String[] arrayOfString;
		long memorySize = 0;
		try{
			FileReader fileRead = new FileReader(s);
			BufferedReader bufRead = new BufferedReader(fileRead,8192);
			s2 = bufRead.readLine();
			arrayOfString = s2.split("\\s+");
			memorySize = Integer.parseInt(arrayOfString[1]) / 1024;//获取系统总内存，单位是KB,除以1024,转换成MB
			bufRead.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return memorySize + "";
	}
}
