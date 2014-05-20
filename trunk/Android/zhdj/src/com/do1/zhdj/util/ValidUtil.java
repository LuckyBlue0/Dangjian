package com.do1.zhdj.util;

import java.util.regex.Pattern;

/**
 * 验证工具类
 * auth:YanFangqin
 * data:2013-4-22
 * thzhd
 */
public class ValidUtil {

	/*
	 * 验证手机号，11位，且必须含全是数字
	 */
	public static boolean isMoble(String mobile) {
		return mobile.matches("^\\d{11}$");
	}
	
	/*
	 * 验证是否为空
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s.trim());
	}
	
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 判断是否为邮箱
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		Pattern pattren=Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattren.matcher(str).matches();
	}
}
