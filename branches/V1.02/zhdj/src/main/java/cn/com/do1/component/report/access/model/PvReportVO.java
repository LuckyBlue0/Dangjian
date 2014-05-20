package cn.com.do1.component.report.access.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class PvReportVO implements Comparable<PvReportVO> {

	private String showDate;// 统计的日期
	private Integer userCnt;// 用户数

	public Integer getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String getShowDate() {
		return showDate;
	}
	   /*  
	    * 按照id从大到到小的顺序排序。 自己可以在方法里面编写任意的排序算法。  
	     */  
	    public int compareTo(PvReportVO o) {   
	        // 根据名字从小到大顺序：if(String.CASE_INSENSITIVE_ORDER.compare(getName(), o.getName()) > 0)   
	        // 根据生日从小到大顺序：if (this.birthday.compareTo(o.getBirthday()) > 0)    
	       // 根据ID从小到大顺序。   
	    	DateFormat df = new SimpleDateFormat("MM月dd");
	    	try {
				Date newDate=df.parse(showDate);
				if (newDate.compareTo(df.parse(o.getShowDate())) > 0) { 
		            return 1;   
		        }else {   
		            return -1;   
		        } 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 1;
			}
	    	  
	   }  
    
}
