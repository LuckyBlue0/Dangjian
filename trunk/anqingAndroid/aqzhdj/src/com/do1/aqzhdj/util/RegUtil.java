package com.do1.aqzhdj.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	public static boolean isPictureUrl(String url){
		try {
				String searchImgReg2 = "(http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-]+)*(/[\\w-]+\\.(jpeg|JPEG|bmp|BMP|jpg|JPG|png|PNG|gif|GIF)))";
			   Pattern p = Pattern.compile(searchImgReg2);
			   Matcher m = p.matcher(url);
			   if(m.find())
			   {
				   return true;
			   }
			   return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
