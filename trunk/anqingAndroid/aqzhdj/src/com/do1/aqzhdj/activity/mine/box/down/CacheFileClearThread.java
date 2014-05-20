package com.do1.aqzhdj.activity.mine.box.down;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 程序清除缓存用，比如下载的附件，皮肤压缩包等等
 * auth:YanFangqin
 * data:2013-4-24
 * thzhd
 */
public class CacheFileClearThread{
	public static final String TAG = "CacheClear";
	private static List<String> files = new ArrayList<String>();
	
	public static void addCacheFile(String file){
		files.add(file);
	}
	
	private static Runnable workThread = new Runnable() {
		@Override
		public void run() {
			for (String fileName:files) {
				File file = new File(fileName);
				if (file.exists()){
					file.delete();
				}
			}
			files.clear();
		}
	};
	
	public static void clearCacheFile(){
		new Thread(workThread).start();
	}
}
