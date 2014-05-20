package com.do1.aqzhdj.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;

public class SDCardUtil {
	/**
	 * sd卡是否可用
	 * @return true 如果sd卡可读可写
	 */
	public static boolean isAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取存放图片的地址
	 * @return
	 */
	public static File getPhotoDir() {
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "thzhd" + File.separator + "Camera");
		if (!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * sd卡中的文件夹
	 * @return
	 */
	public static File getPackInSdcard() {
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "thzhd");
		if (!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * 获取存放随手拍图片的地址
	 * @return
	 */
	public static File getTakingPhotoDir() {
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "thzhd" + File.separator + "takingPhoto");
		if (!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		return file;
	}
	
	public static String getFileName(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return dateFormat.format(date);
		
	}
	
	public static String getPhotoFileName(){
		return "IMAG_" + getFileName() + ".jpg";
	}
	
	
}
