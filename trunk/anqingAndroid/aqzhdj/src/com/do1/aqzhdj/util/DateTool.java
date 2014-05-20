package com.do1.aqzhdj.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {

	private static Date dateNow = new Date();
	private static Calendar calendar = Calendar.getInstance();
	/**
	 * 
	 * @param date
	 * @param formatStr需要格式的目标字符串
	 *            举例格式yy-MM-dd HH:mm:ss / yy/MM/dd HH:mm:ss pm / yy/MM/dd
	 *            HH:mm:ss
	 * @return
	 */
	public static String dateToString(Date date, String formatStr) {
		DateFormat formatDate = new SimpleDateFormat(formatStr);
		return formatDate.format(date);
	}

	/**
	 * 
	 * @param date
	 * @param formatStr需要格式的目标字符串
	 *            举例格式yy-MM-dd HH:mm:ss / yy/MM/dd HH:mm:ss pm / yy/MM/dd
	 *            HH:mm:ss
	 * @return
	 */
	public static String StringToString(String dateStr, String formatStr) {
		DateFormat formatDate = new SimpleDateFormat(formatStr);
		return formatDate.format(StringToDate(dateStr, formatStr));
	}

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int getSeconds(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 12 * 30 * 24 * 60 * 60 + 
				calendar.get(Calendar.MONTH) * 30 * 24 * 60 * 60 + 
				calendar.get(Calendar.DAY_OF_MONTH)* 24 * 60 * 60 +
				calendar.get(Calendar.HOUR_OF_DAY)* 60 * 60 +
				calendar.get(Calendar.MINUTE)* 60 +
				calendar.get(Calendar.SECOND);
	}

	/**
	 * 获取相差年数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenYear(Date date) {
		return betweenMonth(date) / 12;
	}

	/**
	 * 获取相差月数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenMonth(Date date) {
		return betweenDay(date) / 30;
	}

	/**
	 * 获取相差天数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenDay(Date date) {
		return betweenHour(date) / 24;
	}

	/**
	 * 获取相差时数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenHour(Date date) {
		return betweenMinute(date) / 60;
	}

	/**
	 * 获取相差分数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenMinute(Date date) {
		return betweenSecond(date) / 60;
	}

	/**
	 * 获取相差秒数
	 * 
	 * @param date
	 * @return
	 */
	public static int betweenSecond(Date date) {
		return getSeconds(dateNow) - getSeconds(date);
	}

	/**
	 * 把日期转化为几秒前、几分前等
	 * 
	 * @param date
	 * @return
	 */
	public static String transition(Date date) {
		if (betweenSecond(date) < 60 && betweenSecond(date) > 0) {
			return betweenSecond(date) + "刚刚";
		} else if (betweenMinute(date) < 60) {
			return betweenMinute(date) + "分之前";
		} else if (betweenHour(date) < 24) {
			return betweenHour(date) + "小时之前";
		} else if (betweenDay(date) < 30) {
			return betweenDay(date) + "天之前";
		} else if (betweenMonth(date) < 12) {
			return betweenMonth(date) + "个月之前";
		} else {
			return betweenYear(date) + "年之前";
		}
	}

	/**
	 * yy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String transition(String dateStr) {
		return transition(StringToDate(dateStr, "yyyy/MM/dd HH:mm:ss"));
	}

}
