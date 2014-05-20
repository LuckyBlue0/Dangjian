package cn.com.do1.component.news.newsinfo.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface INewsInfoDAO extends IBaseDAO{

	/**
	 * 新闻通用查询分页列表
	 * @param searchMap
	 * @param pager
	 * @return
	 */
	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	/**
	 * 根据新闻类型获取新闻信息
	 * @param type
	 * @return
	 */
	TbNewsInfoPO getNewsInfoByType(String type)throws SQLException;

	/**
	 * 根据新闻种类获取预览详情
	 * @param newsInfoId
	 * @param type
	 * @return
	 */
	NewsPreviewInfoVO getNewsPreviewInfo(String newsInfoId, int type)throws SQLException;

	Pager searchAqNewsinfo(Map searchValue, Pager pager)throws SQLException;



}
