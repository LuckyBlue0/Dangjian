package com.do1.aqzhdj.util;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

/**
 * 请求参数加密工具类
 * @author LHQ
 *
 */
public class LHQSecurityUtil {
	
	/**
	 * @param map
	 * @return
	 */
	public static Map<String,String>  encryption(Map<String,String> map){
		String digest ="";
		for (String key:map.keySet()) {
			digest+=map.get(key);
			//对值进行DES加密
			map.put(key, SecurityUtil.encryptToDES(Constants.MICHI, map.get(key)));
		}
		map.put("digest", SecurityUtil.encryptToMD5(digest).toLowerCase());
		Log.i("LHQSecurityUtil", (Entryption.getJsonKey2(map)));
		HashMap<String, String> map2 = new HashMap<String,String>();
		map2.put("requestJson",Entryption.getJsonKey2(map));
		return map2;
	}

}
