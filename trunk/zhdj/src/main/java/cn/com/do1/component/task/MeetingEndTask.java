package cn.com.do1.component.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.time.StopWatch;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.shyk.meeting.service.IMeetingService;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 * 会议、活动结束任务(将结束时间小于当前系统时间的会议或活动设置为已结束)
 */
public class MeetingEndTask implements Job{
	private final static transient Logger log = LoggerFactory.getLogger(MeetingEndTask.class);
	private static final Lock lock = new ReentrantLock();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("会议、活动结束任务开始>>>>>>");
		process();
		log.info("会议、活动结束任务结束：耗时为：" + sw.getTime() + "毫秒");
		sw.stop();
	}
	public void process(){
		boolean isLock = false;
		try {
			if(!lock.tryLock(3, TimeUnit.SECONDS)){
				log.info("---会议、活动结束任务......已有一个线程在执行");
				return;
			}
			isLock = true;
			IMeetingService meetingService = (IMeetingService)DqdpAppContext.getSpringContext().getBean("meetingService");
			try {
				meetingService.endMeetingProcess();
			} catch (BaseException e) {
				log.error("---会议、活动结束任务",e);
			}
		}catch(Exception e){
			log.error("---会议、活动结束任务",e);
		} finally {
			if(isLock) lock.unlock();
		}
	}
}
