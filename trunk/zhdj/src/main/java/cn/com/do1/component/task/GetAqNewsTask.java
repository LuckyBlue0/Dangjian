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
import cn.com.do1.component.news.newsinfo.service.IAqNewsService;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 * 获取安庆党建新闻任务
 * 每天上午9点和14点执行
 */
public class GetAqNewsTask implements Job{
	private final static transient Logger log = LoggerFactory.getLogger(GetAqNewsTask.class);
	private static final Lock lock = new ReentrantLock();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("获取安庆党建新闻任务开始>>>>>>");
		process();
		log.info("获取安庆党建新闻任务结束：耗时为：" + sw.getTime() + "毫秒");
		sw.stop();
	}
	
	public void process(){
		boolean isLock = false;
		try {
			if(!lock.tryLock(3, TimeUnit.SECONDS)){
				log.info("---获取安庆党建新闻任务......已有一个线程在执行");
				return;
			}
			isLock = true;
			IAqNewsService aqNewsService = (IAqNewsService)DqdpAppContext.getSpringContext().getBean("aqNewsService");
			try {
				aqNewsService.process();
			} catch (BaseException e) {
				log.error("---获取安庆党建新闻任务",e);
			}
		}catch(Exception e){
			log.error("---获取安庆党建新闻任务",e);
		} finally {
			if(isLock) lock.unlock();
		}
	}
}
