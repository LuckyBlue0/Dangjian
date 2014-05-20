package cn.com.do1.component.news.tissuemieninfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.tissuemieninfo.dao.ITissueMienInfoDAO;
import cn.com.do1.component.news.tissuemieninfo.service.ITissueMienInfoService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("tissueMienInfoService")
public class TissueMienInfoServiceImpl extends BaseService implements ITissueMienInfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(TissueMienInfoServiceImpl.class);

    private ITissueMienInfoDAO tissueMienInfoDAO;
    @Resource
    public void setTissueMienInfoDAO(ITissueMienInfoDAO tissueMienInfoDAO) {
        this.tissueMienInfoDAO = tissueMienInfoDAO;
        setDAO(tissueMienInfoDAO);
    }

    public Pager searchTissueMienInfo(Map searchMap,Pager pager) throws Exception, BaseException {
        return tissueMienInfoDAO.pageSearchByField( searchMap,pager);
    }

	@Override
	public List<ShortNewsInfoVO> searchTop5TissueMienInfo() throws Exception, BaseException {
		// TODO Auto-generated method stub
		return tissueMienInfoDAO.searchTop5TissueMienInfo();
	}
}
