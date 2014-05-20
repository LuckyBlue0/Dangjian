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
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.dqdp.core.DqdpAppContext;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @author 作者：罗日朗
 * @Email luorilang@do1.com.cn
 * @version 1.0
 * @date 创建时间：2013-10-16 上午11:51:05
 * 
 *       All rights reserved
 * 
 */
public class ScoreRankingTask implements Job {
	private final static transient Logger logger = LoggerFactory
			.getLogger(ScoreRankingTask.class);
	private static final Lock lock = new ReentrantLock();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		StopWatch sw = new StopWatch();
		sw.start();
		logger.info("更新积分排名时任务开始>>>>>>");
		process();
		logger.info("更新积分排名时任务结束：耗时为：" + sw.getTime() + "毫秒");
		sw.stop();

	}
	
	public void process(){
		boolean isLock = false;
		try {
			if(!lock.tryLock(3, TimeUnit.SECONDS)){
				logger.info("---更新积分排名时任务......已有一个线程在执行");
				return;
			}
			isLock = true;
			try {
				IPersonalscoreService personalscoreService = (IPersonalscoreService) DqdpAppContext
						.getSpringContext().getBean("personalscoreService");
				personalscoreService.updatePersonalscore();
			} catch (Exception e) {
				logger.error("定时更新积分排名时发生异常:" + e.getMessage());
			} catch (BaseException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			logger.error("---更新积分排名时结束任务",e);
		} finally {
			if(isLock) lock.unlock();
		}
	}

}
