package com.do1.zhdj.widght;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.do1.component.parse.DataParser;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.NumberUtil;

/**
 * 数据解析
 * auth:YanFangqin
 * data:2013-4-16
 * thzhd
 */
public class DefaultDataParser extends DataParser<String> {
	
	private static DefaultDataParser sInstance = null;

	@Override
	public ResultObject parseData(String dataKey) {
		Log.i("解析数据开始...");
		String data = "";
		ResultObject result = new ResultObject();
		try {
//			Entryption.encode("");
//			data = Entryption.decode(dataKey);
//			Log.e(data);
			System.out.println("1111111111111dataKey: " + dataKey);
			Map<String, Object> resultMap = json2Map(new JSONObject(dataKey));
			for (String key : resultMap.keySet()) {
				if ("code".equals(key)) {// code
					int code = Integer.parseInt(getValueFromMap(resultMap, key, -1)+"");
					result.setCode(code);
					result.setSuccess(0 == code);
				} else if ("desc".equals(key)) {// desc
					String desc = getValueFromMap(resultMap, key, "");
					result.setDesc(desc);
				} else if ("data".equals(key)) {// data
					Object dataObject = getValueFromMap(resultMap, key, null);
					if (dataObject instanceof JSONArray) {// listMap
						List<Map<String, Object>> listMap = jsonArray2List((JSONArray) dataObject);
						result.addListMap(listMap);
					} else if (dataObject instanceof JSONObject) {// Map
						Map<String, Object> dataMap = json2Map((JSONObject) dataObject);
						result.addDataMap(dataMap);
						//解析一下数据
                        if(dataMap.get("list") != null && !"".equals(dataMap.get("list").toString())){
                            List<Map<String, Object>> listMap = jsonArray2List(((JSONObject) dataObject).getJSONArray("list"));
                            result.addListMap(listMap);
                            if(dataMap.get("total_pages") == null && listMap.size() > 0)
                            	result.setTotalPage(1);
                            else if(dataMap.get("total_pages") == null && listMap.size() == 0)
                            	result.setTotalPage(0);
                            else
                            	result.setTotalPage(NumberUtil.string2Int(dataMap.get("total_pages").toString()));
                        }
					}
				} else {
					Object value = getValueFromMap(resultMap, key, null);
					result.put2Map(key, value);
				}
			}
			Log.i("解析数据结束...");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static DataParser<String> getInstance() {
		if (sInstance == null) {
			sInstance = new DefaultDataParser();
		}
		return sInstance;
	}
	
}
