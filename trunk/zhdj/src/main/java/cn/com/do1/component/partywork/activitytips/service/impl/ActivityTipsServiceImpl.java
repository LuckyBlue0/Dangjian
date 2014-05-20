package cn.com.do1.component.partywork.activitytips.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.partywork.activitytips.dao.IActivityTipsDAO;
import cn.com.do1.component.partywork.activitytips.model.TbActivityTipsPO;
import cn.com.do1.component.partywork.activitytips.service.IActivityTipsService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("activityTipsService")
public class ActivityTipsServiceImpl extends BaseService implements IActivityTipsService {
    private final static transient Logger logger = LoggerFactory.getLogger(ActivityTipsServiceImpl.class);

    private IActivityTipsDAO activityTipsDAO;
    @Resource
    public void setActivityTipsDAO(IActivityTipsDAO activityTipsDAO) {
        this.activityTipsDAO = activityTipsDAO;
        setDAO(activityTipsDAO);
    }

    public Pager searchActivityTips(Map searchMap,Pager pager) throws Exception, BaseException {
        return activityTipsDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public TbActivityTipsPO getActivityTipsByActivityIdAndUserId(String activityId, String userId)throws Exception, BaseException {
		return activityTipsDAO.getActivityTipsByActivityIdAndUserId(activityId,userId);
	}
}
