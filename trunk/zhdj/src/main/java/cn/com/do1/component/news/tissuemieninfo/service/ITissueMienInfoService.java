package cn.com.do1.component.news.tissuemieninfo.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;

import java.util.List;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface ITissueMienInfoService extends IBaseService{
    Pager searchTissueMienInfo(Map searchMap, Pager pager) throws Exception, BaseException;

    /**
     * 获取前5条的组织风采信息
     * @return
     * @throws Exception
     * @throws BaseException
     */
    List<ShortNewsInfoVO>searchTop5TissueMienInfo()throws Exception, BaseException;
}
