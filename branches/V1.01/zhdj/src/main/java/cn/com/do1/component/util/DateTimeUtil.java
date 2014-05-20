package cn.com.do1.component.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.log4j.Logger;

/**   
 * @ClassName: DateTimeUtil 
 * @Description: 
 * @author fengbo fengbo@do1.com.cn
 * @company 广州市道一信息技术有限公司
 * @date 2013-4-10 下午11:18:34 
 * @version V1.0   
 *
 */
public class DateTimeUtil {
	
	private static final Logger log = Logger.getLogger(DateTimeUtil.class);
	/**
	 * 得到当前日期字符串
	 * @param format 格式
	 * @return
	 */
	public static String getNowDateStr(String format){
		Date date = getNowDate();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
		
	}

	/**
	 * 得到指定天数之后的日期，days可以为负数
	 * @param days
	 * @return
	 */
	public static Date getAfterDays(int days,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		Date newDate = new Date(c.getTimeInMillis());
		
		return newDate;
	}
	
	/**
	 * 按格式化字符把日期转换成字符串型
	 * @param date
	 * @param format 指定格式
	 * @return
	 * String
	 */
	public static String date2String(Date date,String format){
		
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String s=sdf.format(date);//把时间类型转为String类型
		return s;
	}
	
	/**
	 * 将字符串转换为日期
	 * @param str 需要转换的日期字符串
	 * @param format 指定格式
	 * @return
	 */
	public static Date string2Date(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 得到两个日期间隔天数
	 * @param str1 较大的日期
	 * @param str2 较小的日期
	 * @return
	 */
	public static int getTwoDate(String str1,String str2){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date1 = sdf.parse(str1);
			Date date2 = sdf.parse(str2);
			day = (date1.getTime()-date2.getTime())/(60*60*24*1000);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int)day;
		
	}
	
	/**
	 * 得到两个日期间隔天数
	 * @param date1 较大的日期
	 * @param date2 较小的日期
	 * @return
	 */
	public static int getTwoDate(Date date1,Date date2){
		long day = (date1.getTime()-date2.getTime())/(60*60*24*1000);
		return (int)day;
	}
	
	/**
	 * 比较两个日期是否相等
	 * @param date1 
	 * @param date2
	 * @return
	 */
	public static boolean equalDate(Date date1,Date date2) {
        if (date1 == date2) {
            return true;
        } else if (null == date1) {
            return false;
        }
        return date1.equals(date2);
    }
	
	/**
	 * 比较两个日期是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalDate(String str1,String str2){
		Date date1 = string2Date(str1,"yyyy-MM-dd HH:mm:ss");
		Date date2 = string2Date(str2,"yyyy-MM-dd HH:mm:ss");
		return equalDate(date1,date2);
	}
	
	public static String getMMdd(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	
	/**
	 * 获取当前时间的周数
	 * @return
	 */
	public static Integer getNowDateWeek() {
		Calendar cal1 = Calendar.getInstance();
		  cal1.setTime(new Date());
		  Integer week = cal1.get(Calendar.WEEK_OF_YEAR);
		  return week;
	}
	/**
	 * 获取星期数
	 * @return
	 */
	public static String getDayOfWeek(Date date) {
		String[] weeks ={"7","1","2","3","4","5","6"};
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Integer week = cal1.get(Calendar.DAY_OF_WEEK)-1;
		return weeks[week];
	}
	
	/**
	 * 计算两个时间内相差多少分钟
	 * @param startTime "2013-05-22 09:20"
	 * @param endTime "2013-05-22 09:30"
	 * @param format yyyy-MM-dd HH:mm
	 * @return
	 */
	public static long dateDiffmin(String startTime, String endTime, String format){
		// 按照传入的格式生成一个simpledateformate对象
		long min = 0;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			min = diff % nd % nh / nm;// 计算差多少分钟
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return min;
	}
	
	/**
	 * 获取当前的小时
	 * @return
	 */
	public static int getDateHour() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	/**
	 * 获取两个时间相差多少小时,多少分,多少秒
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return X小时X分X秒
	 */
	public static String dateDiffSec(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象   
        SimpleDateFormat sd = new SimpleDateFormat(format);   
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
        long nm = 1000 * 60;// 一分钟的毫秒数   
        long ns = 1000;// 一秒钟的毫秒数   
        long diff;   
        long day = 0;   
        long hour = 0;   
        long min = 0;   
        long sec = 0;   
        String str = "";
        // 获得两个时间的毫秒时间差异   
        try {   
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();   
            day = diff / nd;// 计算差多少天   
            hour = diff % nd / nh + day * 24;// 计算差多少小时   
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟   
            sec = diff % nd % nh % nm / ns;// 计算差多少秒   
            // 输出结果   
            if((hour - day * 24)  > 0){
            	str = (hour - day * 24) + "小时"  + (min - day * 24 * 60) + "分" + sec + "秒";
            }else{
            	str = (min - day * 24 * 60) + "分" + sec + "秒";
            }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 获取某一天的下一天
	 * @param dateString
	 * @param sdf
	 * @return
	 */
    public static String getNextDate(String dateString,String sdf) {
        Calendar now_Time = Calendar.getInstance();
        DateFormat df=new SimpleDateFormat(sdf);
        try {
            now_Time.setTime(df.parse(dateString));
        } catch (ParseException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        now_Time.add(Calendar.DATE,1);
        SimpleDateFormat sdNowDate = new SimpleDateFormat(sdf);
        return sdNowDate.format(now_Time.getTime());
    }
    
    private static final SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    
    /**
     * 获得本天的开始时间，即2013-06-26 00:00:00
     * 
     * @return
     */
    public static Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    
    /**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		return java.sql.Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		// ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = null;
		try {
			currentTime_2 = formatter.parse(dateString);
		} catch (ParseException e) {
			currentTime_2 = new Date();
		}
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyyMMddHHmmss
	 */
	public static String getStringDate1() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		if (strDate != null) {
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		}
		return null;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static String timeStampToStringLong() {

		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(ts);
		} catch (Exception e) {
			log.error("timeStamp To String Long fail!", e);
		}
		return null;
	}

	public static Timestamp stringToTimeStampLong() {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			return Timestamp.valueOf(time);
		} catch (Exception e) {
			log.error("String to timestamp long fail!", e);
		}
		return null;
	}

	public static Timestamp stringToTimeStampLong(String time) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = df.parse(time);
			return new Timestamp(d.getTime());
		} catch (Exception e) {
			log.error("String to timestamp long fail!", e);
		}
		return null;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong1(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60 + Double.parseDouble(kk[2]);
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60 + Double.parseDouble(jj[2]);
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay_yyyyMMdd(String nowdate, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + delay * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	public static Date getSendTime(Date sendTime) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String time = sdf.format(sendTime);
		
		Date date = null;
		
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			log.error("execute getSendTime(date) fail:" + e.getMessage(), e);
		}
		
		return date;
	}
	
	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateTimeUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateTimeUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateTimeUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateTimeUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateTimeUtil.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 
	 * @param args
	 */
	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * 将formBean 里的字符时间(yyyy-MM-dd) 转换成Date类型
	 * 
	 * @param formDate
	 * @return
	 */
	public static Date formBeanDateToPODate(String formDate) {
		try {
			if (null != formDate && !"".equals(formDate.trim())) {

				return java.sql.Date.valueOf(formDate);

			}
		} catch (Exception e) {
			return new Date();
		}
		return null;
	}

	/**
	 * @Title: getNowDayBefore
	 * @Description: 获取当前日期的前一天
	 * @return:Date
	 * @author wenjianhai
	 * @date 2012-7-27
	 */
	public static Date getNowDayBefore() {

		Date date = null;

		try {

			Calendar c = Calendar.getInstance();
			Date currentTime = new Date();

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			String dateString = fmt.format(currentTime);
			date = fmt.parse(dateString);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 1);

			String dayBefore = fmt.format(c.getTime());

			date = fmt.parse(dayBefore);

			return date;

		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 活动传入日期后一天的时间
	 * 
	 * @return
	 * @author Sun Qinghai
	 * @2012-11-20
	 * @version 1.0
	 */
	public static Date getNowDayAfter(Date da) {

		Date date = null;

		try {

			Calendar c = Calendar.getInstance();

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			String dateString = fmt.format(da);
			date = fmt.parse(dateString);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + 1);

			String dayBefore = fmt.format(c.getTime());

			date = fmt.parse(dayBefore);

			return date;

		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 活动传入日期后一天的时间
	 * 
	 * @return
	 * @author Sun Qinghai
	 * @2012-11-20
	 * @version 1.0
	 */
	public static String getNowDayAfterToString(Date da) {

		Date date = null;
		String dayBefore = "";
		try {

			Calendar c = Calendar.getInstance();

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			String dateString = fmt.format(da);
			date = fmt.parse(dateString);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + 1);

			dayBefore = fmt.format(c.getTime());

			// date = fmt.parse(dayBefore);

			return dayBefore;

		} catch (Exception e) {
			return dayBefore;
		}
	}

	/**
	 * @Title: getThisMonth
	 * @Description: 获取当前月的前一月份
	 * @return:
	 * @author wenjianhai
	 * @date 2012-8-14
	 */
	public static int getBeforeThisMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month;
	}

	/**
	 * @Title: getThisMonth
	 * @Description: 获取当前月的下一个月份
	 * @return:
	 * @author wenjianhai
	 * @date 2012-8-14
	 */
	public static int getAfterThisMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 2;
		return month;
	}

	/**
	 * @Title: getThisMonth
	 * @Description: 获取当前月份
	 * @return:int
	 * @author wenjianhai
	 * @date 2012-8-14
	 */
	public static int getThisMonth() {

		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * @Title: getThisMonth
	 * @Description: 获取当前日期
	 * @return:int
	 * @author wenjianhai
	 * @date 2012-8-14
	 */
	public static int getThisYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * @Title: getMonthLastDay
	 * @Description: 得到指定月的天数
	 * @param year
	 *            当前日期
	 * @param month
	 *            当前月份
	 * @return:int
	 * @author wenjianhai
	 * @date 2012-8-14
	 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		// 把日期设置为当月第一天
		a.set(Calendar.DATE, 1);
		// 日期回滚一天，也就是最后一天
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * @Title: getDayAfter12
	 * @Description: 获取当前日期12前的日期 yyyy-MM-dd
	 * @return:
	 * @author wenjianhai
	 * @date 2012-10-16
	 */
	public static String getDayAfter12() {

		String tenDate = null;

		try {

			Calendar c = Calendar.getInstance();
			Date currentTime = new Date();

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			String dateString = fmt.format(currentTime);
			Date date = fmt.parse(dateString);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 12);

			tenDate = fmt.format(c.getTime());

		} catch (Exception e) {
			tenDate = getStringDateShort();
		}

		return tenDate;
	}

	/**
	 * @Title: handleDate
	 * @Description: sql中日期参数格式的增加或者减少
	 * @param sql
	 *            带有日期参数格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-17
	 */
	public static String handleDate(String sql) {

		log.info("Start to handle date param in sql,the sql is:\r\n" + sql);

		if (null != sql && !"".equals(sql.trim())) {

			sql = handleYear(sql);
			// log.info("Finish handle year param,the sql is:\r\n" + sql);
			sql = handleCurMonth(sql);
			// log.info("Finish handle curMonth param,the sql is:\r\n" + sql);
			sql = handleDay(sql);
			// log.info("Finish handle day param,the sql is:\r\n" + sql);
			sql = handleLastDay(sql);
			// log.info("Finish handle lastDay param,the sql is:\r\n" + sql);
			sql = handleNextDay(sql);
			// log.info("Finish handle nextDay param,the sql is:\r\n" + sql);
		}

		log.info("End to handle date param in sql,the return sql is:\r\n" + sql);

		return sql;
	}

	/**
	 * @Title: handleYear
	 * @Description: sql中日期参数格式(yyyy)的增加或者减少
	 * @param sql
	 *            带有(#@year#或#@year+n#或#@year-n#)格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-17
	 */
	public static String handleYear(String sql) {

		// @year 为当前年份,格式如: 2012

		try {
			String temp = null, year = null;
			int index = -1;
			// +n/-n 年
			int n = 0, nYear = getThisYear();

			if (sql.indexOf("#@year+") >= 0) {
				/* 当前年份+n */
				index = sql.indexOf("@year+");

				temp = sql.substring(index + 6, index + 7);

				n = Integer.parseInt(temp);

				// 当前年份+n
				year = String.valueOf(nYear + n);

				temp = "#@year+".concat(temp).concat("#");

				// 将取得的年份替换掉 #@year+n#
				sql = sql.replace(temp, year);

			}
			if (sql.indexOf("#@year-") >= 0) {
				// 当前年份-n
				index = sql.indexOf("@year-");

				temp = sql.substring(index + 6, index + 7);

				n = Integer.parseInt(temp);

				// 当前年份-n
				year = String.valueOf(nYear - n);

				temp = "#@year-".concat(temp).concat("#");

				// 将取得的年份替换掉 #@year-n#
				sql = sql.replace(temp, year);

			}
			if (sql.indexOf("#@year#") >= 0) {
				// 取当前日期
				sql = sql.replace("#@year#", String.valueOf(getThisYear()));
			}

			if (sql.indexOf("#@year+") >= 0 || sql.indexOf("#@year-") >= 0
					|| sql.indexOf("#@year#") >= 0) {
				// 如果上面执行完成后,还存在对应的日期参数格式,则递归调用
				sql = handleYear(sql);
			}

		} catch (NumberFormatException e) {
			log.error("您要加或减的年份不是数字!", e);
		} catch (Exception e) {
			log.error("handle th year param in sql fail!", e);
		}

		return sql;
	}

	/**
	 * @Title: handleCurMonth
	 * @Description: sql中月份参数格式(yyyyMM)的增加或者减少
	 * @param sql
	 *            带有(#@curMonth#或#@curMonth+n#或#@curMonth-n#)格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-17
	 */
	public static String handleCurMonth(String sql) {

		// @curMonth 为当前日期+当前月份,格式如: 201210

		try {
			String temp = null, curMonth = null, temp1 = null, year = null;
			int index = -1;
			// +n/-n 月
			int n = 0, nMonth = getThisMonth(), ncurMonth = 0;

			// 当前年份
			year = String.valueOf(getThisYear());

			if (sql.indexOf("#@curMonth+") >= 0) {

				/* 当前月份+n (#@curMonth+n#) */

				index = sql.indexOf("@curMonth+");

				temp1 = sql.substring(index + 11, index + 12);

				// 取得月份
				if (!"#".equals(temp1)) {
					// 月份是2位数
					temp = sql.substring(index + 10, index + 12);
				} else {
					// 月份是1位数
					temp = sql.substring(index + 10, index + 11);
				}
				// 月份
				n = Integer.parseInt(temp);

				// 当前月份+n
				curMonth = String.valueOf(nMonth + n);

				if (Integer.parseInt(curMonth) > 12) {
					// 超过12个月,说明到了下一年
					ncurMonth = Integer.parseInt(curMonth) - 12;
					curMonth = String.valueOf(ncurMonth);

					year = String.valueOf(getThisYear() + 1);
				}

				if (curMonth.length() == 1) {
					curMonth = "0".concat(curMonth);
				}

				temp = "#@curMonth+".concat(temp).concat("#");

				// 将取得的当前日期和月份替换掉 #@curMonth+n#
				sql = sql.replace(temp, year.concat(curMonth));

			}
			if (sql.indexOf("#@curMonth-") >= 0) {
				// 当前月份-n
				index = sql.indexOf("@curMonth-");

				temp1 = sql.substring(index + 11, index + 12);

				// 取得月份
				if (!"#".equals(temp1)) {
					temp = sql.substring(index + 10, index + 12);
				} else {
					temp = sql.substring(index + 10, index + 11);
				}

				n = Integer.parseInt(temp);

				// 当前月份-n
				curMonth = String.valueOf(nMonth - n);

				if (Integer.parseInt(curMonth) < 0) {
					// 上一年
					// curMonth 是负数
					ncurMonth = 12 + Integer.parseInt(curMonth);
					curMonth = String.valueOf(ncurMonth);

					year = String.valueOf(getThisYear() - 1);

				} else if (Integer.parseInt(curMonth) == 0) {
					// 上一年
					curMonth = "12";
					year = String.valueOf(getThisYear() - 1);
				}

				if (curMonth.length() == 1) {
					curMonth = "0".concat(curMonth);
				}

				temp = "#@curMonth-".concat(temp).concat("#");

				// 将取得的当前日期和月份替换掉 #@curMonth-n#
				sql = sql.replace(temp, year.concat(curMonth));

			}
			if (sql.indexOf("#@curMonth#") >= 0) {
				// 取当前日期+月份
				sql = sql.replace("#@curMonth#",
						year.concat(String.valueOf(getThisMonth())));
			}

			if (sql.indexOf("#@curMonth+") >= 0
					|| sql.indexOf("#@curMonth-") >= 0
					|| sql.indexOf("#@curMonth#") >= 0) {
				// 如果上面执行完成后,还存在对应的月份参数格式,则递归调用
				sql = handleCurMonth(sql);
			}

		} catch (NumberFormatException e) {
			log.error("您要加或减的月份不是数字!", e);
		} catch (Exception e) {
			log.error("handle th month param in sql fail!", e);
		}

		return sql;
	}

	/**
	 * @Title: handleDay
	 * @Description: sql中日期参数格式(yyyyMMdd)的增加或者减少
	 * @param sql
	 *            带有(#@day#或#@day+n#或#@day-n#)格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-17
	 */
	public static String handleDay(String sql) {

		// @day 为当前日期当前月份的第一天,格式如: 20121001

		try {
			String temp = null, year = null, temp1 = null, month = null, day = null;
			int index = -1;
			// +n/-n 天
			int n = 0, nYear = getThisYear(), nMonth = getThisMonth();

			if (sql.indexOf("#@day+") >= 0) {
				/* 当前日期+n */

				index = sql.indexOf("@day+");

				temp1 = sql.substring(index + 6, index + 7);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 5, index + 7);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 5, index + 6);
				}
				// 天数
				n = Integer.parseInt(temp);

				// 当前天数+n天(当前天数默认是1号)
				n = 1 + n;

				// 得到当前年当前月的天数
				int maxDay = getMonthLastDay(getThisYear(), getThisMonth());

				if (n > maxDay) {
					/*
					 * 1. 月份+1 2. 判断+1后的月份是否大于12，是，当前年份+1，修改月份 3. 修改天数
					 */
					// 到下一个月的天数
					n = n - maxDay;
					// 下一个月
					nMonth += 1;

					if (nMonth > 12) {
						nMonth = nMonth - 12;
						nYear += 1;
					}
				}

				month = String.valueOf(nMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@day+".concat(temp).concat("#");

				// 将取得的日期替换掉 #@day+n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@day-") >= 0) {
				// 当前日期-n
				index = sql.indexOf("@day-");

				temp1 = sql.substring(index + 6, index + 7);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 5, index + 7);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 5, index + 6);
				}

				n = Integer.parseInt(temp);

				// 当前天数-n(当前天数默认是1号)
				n = 1 - n;

				// 上一个月
				nMonth -= 1;

				if (nMonth == 0) {
					nMonth = 12;
					nYear -= 1;
				}

				if (0 == n) {
					// 得到nYear和nMonth的最大天数
					n = getMonthLastDay(nYear, nMonth);

				} else if (0 > n) {
					int maxDay = getMonthLastDay(nYear, nMonth);
					// -n后到上个月的天数(此时n为负数)
					n = maxDay + n;

					if (0 == n) {
						/* 20121001-31 会出现此情况 */
						// 上上个月
						nMonth -= 1;
						// 得到nYear和nMonth的最大天数
						n = getMonthLastDay(nYear, nMonth);
					}
				}

				month = String.valueOf(nMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@day-".concat(temp).concat("#");

				// 将取得的日期替换掉 #@day-n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@day#") >= 0) {
				// 取当前日期
				sql = sql.replace("#@day#", String.valueOf(getThisYear())
						.concat(getThisMonth() + "").concat("01"));
			}

			if (sql.indexOf("#@day+") >= 0 || sql.indexOf("#@day-") >= 0
					|| sql.indexOf("#@day#") >= 0) {
				// 如果上面执行完成后,还存在对应的日期参数格式,则递归调用
				sql = handleDay(sql);
			}

		} catch (NumberFormatException e) {
			log.error("您要加或减的日期不是数字!", e);
		} catch (Exception e) {
			log.error("handle th day param in sql fail!", e);
		}

		return sql;
	}

	/**
	 * @Title: handleLastDay
	 * @Description: sql中日期参数格式(yyyyMMdd)的增加或者减少
	 * @param sql
	 *            带有(#@lastDay#或#@lastDay+n#或#@lastDay-n#)格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-18
	 */
	public static String handleLastDay(String sql) {

		// @lastDay : 当前年份当前月份的上一个月的第一天

		try {
			String temp = null, year = null, temp1 = null, month = null, day = null;
			int index = -1;
			// +n/-n 天
			int n = 0, nYear = getThisYear(), curMonth = getThisMonth();

			// 上个月
			int lastMonth = curMonth - 1;

			if (lastMonth == 0) {
				lastMonth = 12;
				nYear -= 1;
			}

			if (sql.indexOf("#@lastDay+") >= 0) {
				/* 当前日期+n */

				index = sql.indexOf("@lastDay+");

				temp1 = sql.substring(index + 10, index + 11);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 9, index + 11);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 9, index + 10);
				}
				// 天数
				n = Integer.parseInt(temp);

				// 当前天数+n天(当前天数默认是1号)
				n = 1 + n;

				// 得到当前年当前月的上一个月的天数
				int maxDay = getMonthLastDay(nYear, lastMonth);

				if (n > maxDay) {
					// 大于上一个月的天数,则进入当前月
					/*
					 * 1. 月份+1 2. 判断+1后的月份是否大于12，是，当前年份+1，修改月份 3. 修改天数
					 */
					// 到当前月的天数
					n = n - maxDay;
					// 当前月
					lastMonth = lastMonth + 1;

					if (lastMonth > 12) {
						lastMonth = lastMonth - 12;
						nYear += 1;
					}
				}

				month = String.valueOf(lastMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@lastDay+".concat(temp).concat("#");

				// 将取得的日期替换掉 #@lastDay+n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@lastDay-") >= 0) {
				// 当前日期-n
				index = sql.indexOf("@lastDay-");

				temp1 = sql.substring(index + 10, index + 11);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 9, index + 11);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 9, index + 10);
				}

				n = Integer.parseInt(temp);

				// 当前天数-n(当前天数默认是1号)
				n = 1 - n;

				if (0 == n) {
					// 上上个月
					lastMonth -= 1;

					if (lastMonth == 0) {
						lastMonth = 12;
						nYear -= 1;
					}

					// 得到nYear和lastMonth的最大天数
					n = getMonthLastDay(nYear, lastMonth);

				} else if (0 > n) {
					// 上上个月
					lastMonth -= 1;

					if (lastMonth == 0) {
						lastMonth = 12;
						nYear -= 1;
					}

					int maxDay = getMonthLastDay(nYear, lastMonth);
					// -n后到上个月的天数(此时n为负数)
					n = maxDay + n;

					if (0 == n) {
						/* 20121001-31 会出现此情况 */
						// 上上个月
						lastMonth -= 1;
						// 得到nYear和lastMonth的最大天数
						n = getMonthLastDay(nYear, lastMonth);
					}
				}

				month = String.valueOf(lastMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@lastDay-".concat(temp).concat("#");

				// 将取得的日期替换掉 #@lastDay-n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@lastDay#") >= 0) {
				month = String.valueOf(lastMonth);
				if (month.length() == 1) {
					month = "0".concat(month);
				}
				// 取当前日期
				sql = sql.replace("#@lastDay#",
						String.valueOf(nYear).concat(month).concat("01"));
			}

			if (sql.indexOf("#@lastDay+") >= 0
					|| sql.indexOf("#@lastDay-") >= 0
					|| sql.indexOf("#@lastDay#") >= 0) {
				// 如果上面执行完成后,还存在对应的日期参数格式,则递归调用
				sql = handleLastDay(sql);
			}

		} catch (NumberFormatException e) {
			log.error("您要加或减的日期不是数字!", e);
		} catch (Exception e) {
			log.error("handle th lastDay param in sql fail!", e);
		}

		return sql;
	}

	/**
	 * @Title: handleNextDay
	 * @Description: sql中日期参数格式(yyyyMMdd)的增加或者减少
	 * @param sql
	 *            带有(#@nextDay#或#@nextDay+n#或#@nextDay-n#)格式的sql
	 * @return:String
	 * @author wenjianhai
	 * @date 2012-10-18
	 */
	public static String handleNextDay(String sql) {

		// @nextDay : 当前年份当前月份的下一个月的第一天

		try {
			String temp = null, year = null, temp1 = null, month = null, day = null;
			int index = -1;
			// +n/-n 天
			int n = 0, nYear = getThisYear(), curMonth = getThisMonth();

			// 下个月
			int nextMonth = curMonth + 1;

			if (nextMonth > 12) {
				nextMonth = nextMonth - 12;
				nYear += 1;
			}

			if (sql.indexOf("#@nextDay+") >= 0) {
				/* 当前日期+n */

				index = sql.indexOf("@nextDay+");

				temp1 = sql.substring(index + 10, index + 11);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 9, index + 11);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 9, index + 10);
				}
				// 天数
				n = Integer.parseInt(temp);

				// 当前天数+n天(当前天数默认是1号)
				n = 1 + n;

				// 得到当前年当前月的上一个月的天数
				int maxDay = getMonthLastDay(nYear, nextMonth);

				if (n > maxDay) {
					// 大于下一个月的天数,则进入下下个月
					/*
					 * 1. 月份+1 2. 判断+1后的月份是否大于12，是，当前年份+1，修改月份 3. 修改天数
					 */
					// 到当前月的天数
					n = n - maxDay;
					// 当前月
					nextMonth = nextMonth + 1;

					if (nextMonth > 12) {
						nextMonth = nextMonth - 12;
						nYear += 1;
					}
				}

				month = String.valueOf(nextMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@nextDay+".concat(temp).concat("#");

				// 将取得的日期替换掉 #@nextDay+n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@nextDay-") >= 0) {
				// 当前日期-n
				index = sql.indexOf("@nextDay-");

				temp1 = sql.substring(index + 10, index + 11);

				if (!"#".equals(temp1)) {
					// 天数是2位数
					temp = sql.substring(index + 9, index + 11);
				} else {
					// 天数是1位数
					temp = sql.substring(index + 9, index + 10);
				}

				n = Integer.parseInt(temp);

				// 当前天数-n(当前天数默认是1号)
				n = 1 - n;

				if (0 == n) {
					// 上上个月
					nextMonth -= 1;

					if (nextMonth == 0) {
						nextMonth = 12;
						nYear -= 1;
					}

					// 得到nYear和lastMonth的最大天数
					n = getMonthLastDay(nYear, nextMonth);

				} else if (0 > n) {
					// 上上个月
					nextMonth -= 1;

					if (nextMonth == 0) {
						nextMonth = 12;
						nYear -= 1;
					}

					int maxDay = getMonthLastDay(nYear, nextMonth);
					// -n后到上个月的天数(此时n为负数)
					n = maxDay + n;

					if (0 == n) {
						/* 20121201-31 会出现此情况 */
						// 上上个月
						nextMonth -= 1;
						// 得到nYear和lastMonth的最大天数
						n = getMonthLastDay(nYear, nextMonth);
					}
				}

				month = String.valueOf(nextMonth);
				day = String.valueOf(n);
				year = String.valueOf(nYear);

				if (month.length() == 1) {
					month = "0".concat(month);
				}

				if (day.length() == 1) {
					day = "0".concat(day);
				}

				temp = "#@nextDay-".concat(temp).concat("#");

				// 将取得的日期替换掉 #@nextDay-n#
				sql = sql.replace(temp, year.concat(month).concat(day));

			}
			if (sql.indexOf("#@nextDay#") >= 0) {
				month = String.valueOf(nextMonth);
				if (month.length() == 1) {
					month = "0".concat(month);
				}
				// 取当前日期
				sql = sql.replace("#@nextDay#",
						String.valueOf(nYear).concat(month).concat("01"));
			}

			if (sql.indexOf("#@nextDay+") >= 0
					|| sql.indexOf("#@nextDay-") >= 0
					|| sql.indexOf("#@nextDay#") >= 0) {
				// 如果上面执行完成后,还存在对应的日期参数格式,则递归调用
				sql = handleNextDay(sql);
			}

		} catch (NumberFormatException e) {
			log.error("您要加或减的日期不是数字!", e);
		} catch (Exception e) {
			log.error("handle th nextDay param in sql fail!", e);
		}

		return sql;
	}

	/**
	 * @Title: getNowDayAdd_MMdd
	 * @Description: 获取当前天数day天后的日期(格式为MMdd)
	 * @param days
	 *            day天后
	 * @return String
	 * @author wenjianhai
	 * @date 2013-3-21
	 */
	public static String getNowDayAdd_MMdd(Integer days) throws Exception {

		String sday = null;

		try {

			Calendar c = Calendar.getInstance();
			Date currentTime = new Date();

			SimpleDateFormat fmt = new SimpleDateFormat("MMdd");

			String dateString = fmt.format(currentTime);
			Date date = fmt.parse(dateString);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + days);

			sday = fmt.format(c.getTime());

		} catch (Exception e) {
			log.error("获取当前天数day天后的日期(格式为MMdd)失败:" + e.getMessage(), e);
			throw new Exception("获取当前天数day天后的日期(格式为MMdd)失败:" + e.getMessage());
		}

		return sday;
	}

    /**
     * 获得本天的结束时间，即2013-06-26 23:59:59
     * 
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
	
	public static void main(String[] args) {
		String str ="201304130000";
		
		Date date = string2Date(str,"yyyyMMddHHmm");
		
		String week = getDayOfWeek(date);
		int day = DateTimeUtil.getTwoDate(date2String(date, "yyyy-MM-dd"), date2String(new Date(), "yyyy-MM-dd"));
		
		System.out.println(day);

		
	}

}
