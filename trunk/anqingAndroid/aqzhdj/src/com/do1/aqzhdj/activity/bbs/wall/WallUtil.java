package com.do1.aqzhdj.activity.bbs.wall;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 * auth:YanFangqin
 * data:2013-4-26
 * thzhd
 */
public class WallUtil {

	public static List<String> listTypeId = new ArrayList<String>();
	public static List<String> listTypeName = new ArrayList<String>();
	
	/**
	 * 根据类型名称获得类型ID
	 * @param name
	 * @return
	 */
	public static String getIdByName(String name){
		if("专题图片".equals(name)){
			return listTypeName.get(0);
		}
		for(int i = 0; i < listTypeName.size(); i++){
			if(name.equals(listTypeName.get(i))){
				return listTypeId.get(i);
			}
		}
		return "";
	}
	
	/**
	 * 根据类型名称获得类型ID
	 * @param name
	 * @return
	 */
	public static int getPositionById(String id){
		if("专题图片".equals(id)){
			return 0;
		}
		for(int i = 0; i < listTypeId.size(); i++){
			if(id.equals(listTypeId.get(i))){
				return i;
			}
		}
		return 0;
	}
}
