package com.do1.aqzhdj.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class LhqStringUtil {

	public static String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json + "";
	}

	public static Map<String, String> jiami(Map<String, Object> map) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		System.out.println("params:::" + map);
		Object key; // 从map中取得的单个key
		String keys = ""; // 需要拼接的所有的参数
		String digest = ""; // 加密值
		Set set = map.keySet();
		Iterator ite = set.iterator();
		while (ite.hasNext()) {
			try {
				key = ite.next();
				keys += map.get(key) + "";
				if (key.equals("userIds")) {
					System.out.println("map.get(key):" + map.get(key));// [16aff461-25f9-4c50-890a-6e7befb2a4e1,
																		// f42a5eae-6f02-447d-aa35-9f764a254f7f]
					String substr = map.get(key).toString()
							.substring(1, map.get(key).toString().length() - 1);
					String[] userIds = substr.split(",");
					JSONArray a = new JSONArray();
					for (int i = 0; i < userIds.length; i++) {
						System.out.println("userIds:" + userIds[i] + "");
						String str = SecurityDes3Util.encode(userIds[i]
								.toString().trim());
						a.put(i, str);
					}
					params.put(key + "", a);
				} else {
					System.out.println("111111111111111111111");
					System.out.println("key: " + key + ";根据key 获取到的值: "
							+ map.get(key) + ";加密后的值: "
							+ SecurityDes3Util.encode(map.get(key) + ""));
					params.put(key + "",
							SecurityDes3Util.encode(map.get(key) + ""));
				}
				// ["["3a91babe-d5a3-47a3-9e1e-85892a10ee6c","
				// 051231e2-e637-440b-b091-7910584575b"]"]
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("所有key拼接后的keys: " + keys);
		params.put("digest", SecurityUtil.encryptToMD5(keys).toLowerCase());
		System.out.println("params: " + params);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("requestJson", toJsonString(params));
		System.out.println("最后生成的requestjson: " + map2);
		return map2;
	}
}
