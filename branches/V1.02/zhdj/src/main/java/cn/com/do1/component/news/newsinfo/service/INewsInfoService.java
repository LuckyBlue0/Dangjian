package cn.com.do1.component.news.newsinfo.service;

import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface INewsInfoService extends IBaseService{
    Pager searchNewsinfo(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 保存新闻对象
	 * @param tbNewsInfoPO
	 */
	void saveNewsInfo(TbNewsInfoPO tbNewsInfoPO) throws Exception,BaseException;

	
	/**
	 * 根据类型获取数据字典
	 * @param fsType
	 * @return
	 * @throws Exception
	 */
	List<ExItemObj> getDictsByType(String fsType)throws Exception,BaseException;
	
	
	/**
	 * 根据新闻类型获取新闻
	 * @param type
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	TbNewsInfoPO getNewsInfoByType(String type)throws Exception,BaseException;

	/**
	 * 根据新闻种类获取预览详情
	 * @param newsInfoId  新闻ID
	 * @param type 新闻种类(0:热点新闻,1/2:普通新闻,3:组织风采新闻,其它:普通新闻)
	 * @return
	 */
	NewsPreviewInfoVO getNewsPreviewInfo(String newsInfoId, int type)throws Exception,BaseException;

	/**
	 * 获取新闻资讯列表
	 * @param searchValue
	 * @param pager
	 * @return
	 */
	Pager searchAqNewsinfo(Map searchValue, Pager pager)throws Exception,BaseException;


}