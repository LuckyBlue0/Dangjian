package cn.com.do1.component.shyk.meeting.util;

import java.util.Date;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.DateTimeUtil;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 */
public class MeetingUtil {

	/**
	 * 获取当前会议的的开展状态
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getCarryOutStatus(String startTime, String endTime) {
		String carryOutStatus = "---";
		if (!AssertUtil.isEmpty(startTime) && !AssertUtil.isEmpty(endTime)) {
			Date currentDate = new Date();
			Date startTimed = DateTimeUtil.string2Date(startTime, "yyyy-MM-dd HH:mm:ss");
			Date endTimed = DateTimeUtil.string2Date(endTime, "yyyy-MM-dd HH:mm:ss");
			if (currentDate.getTime() < startTimed.getTime()) {
				carryOutStatus = "未开始";
			} else if (currentDate.getTime() > startTimed.getTime() && currentDate.getTime() < endTimed.getTime()) {
				carryOutStatus = "进行中";
			} else if (currentDate.getTime() > endTimed.getTime()) {
				carryOutStatus = "已结束";
			}
		}
		return carryOutStatus;
	}
	
	/**
	 * 获取当前会议的的开展状态
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getCarryOutStat(String startTime, String endTime) {
		int carryOutStatus = -1;
		try {
			if (!AssertUtil.isEmpty(startTime) && !AssertUtil.isEmpty(endTime)) {
				Date currentDate = new Date();
				Date startTimed = DateTimeUtil.string2Date(startTime, "yyyy-MM-dd HH:mm:ss");
				Date endTimed = DateTimeUtil.string2Date(endTime, "yyyy-MM-dd HH:mm:ss");
				if (currentDate.getTime() < startTimed.getTime()) {
					carryOutStatus = 0;
				} else if (currentDate.getTime() > startTimed.getTime() && currentDate.getTime() < endTimed.getTime()) {
					carryOutStatus = 1;
				} else if (currentDate.getTime() > endTimed.getTime()) {
					carryOutStatus = 2;
				}
			}
		} catch (Exception e) {
		}
		return carryOutStatus;
	}
}
