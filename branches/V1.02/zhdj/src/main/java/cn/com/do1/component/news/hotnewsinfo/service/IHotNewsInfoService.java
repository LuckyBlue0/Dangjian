package cn.com.do1.component.news.hotnewsinfo.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IHotNewsInfoService extends IBaseService{
    Pager searchHotNewsInfo(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 新增或修改热点新闻
	 * @param tbHotNewsPO
	 */
	void saveHotNewsInfo(TbHotNewsPO tbHotNewsPO) throws Exception, BaseException;

}
