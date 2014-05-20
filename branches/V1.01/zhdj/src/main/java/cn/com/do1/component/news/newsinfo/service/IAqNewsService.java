package cn.com.do1.component.news.newsinfo.service;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface IAqNewsService  extends IBaseService{

	/**
	 *获取安庆党建官网的新闻数据 
	 */
	void process()throws Exception, BaseException;

}
