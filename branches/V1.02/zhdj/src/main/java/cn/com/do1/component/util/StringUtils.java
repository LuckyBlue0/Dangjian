package cn.com.do1.component.util;

public class StringUtils {

	public static boolean isBlank(String str){
		if(str!=null&&!"".equals(str.trim())){
			return false;
		}else{
			return true;
		}
	}
}
