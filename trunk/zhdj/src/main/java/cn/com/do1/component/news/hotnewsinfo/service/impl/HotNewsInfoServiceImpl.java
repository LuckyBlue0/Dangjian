package cn.com.do1.component.news.hotnewsinfo.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.news.hotnewsinfo.dao.IHotNewsInfoDAO;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.hotnewsinfo.service.IHotNewsInfoService;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("hotNewsInfoService")
public class HotNewsInfoServiceImpl extends BaseService implements IHotNewsInfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(HotNewsInfoServiceImpl.class);

    private IHotNewsInfoDAO hotNewsInfoDAO;
    @Resource
    public void setHotNewsInfoDAO(IHotNewsInfoDAO hotNewsInfoDAO) {
        this.hotNewsInfoDAO = hotNewsInfoDAO;
        setDAO(hotNewsInfoDAO);
    }
    @Resource
    private IOrgService orgService;

    public Pager searchHotNewsInfo(Map searchMap,Pager pager) throws Exception, BaseException {
        return hotNewsInfoDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public void saveHotNewsInfo(TbHotNewsPO tbHotNewsPO) throws Exception, BaseException {
		// TODO Auto-generated method stub
		IUser user = (IUser)DqdpAppContext.getCurrentUser();
		if(!AssertUtil.isEmpty(tbHotNewsPO.getStatus()) && tbHotNewsPO.getStatus() == 1){
			tbHotNewsPO.setPushTime(new Date());
			tbHotNewsPO.setPushUserId(user.getPersonId());
		}
		
		OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
		if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
			tbHotNewsPO.setOrganizationId(orgVO.getOrganizationId());
		}else if(user.getUsername().equals("admin")){
			logger.info("系统管理员没有归属组织,不需要记录组织");
		}
		
		if(AssertUtil.isEmpty(tbHotNewsPO.getId())){
			if(AssertUtil.isEmpty(tbHotNewsPO.getStatus())){
				tbHotNewsPO.setStatus(0L);
			}
			if(AssertUtil.isEmpty(tbHotNewsPO.getBuyTop())){
				tbHotNewsPO.setBuyTop(0L);
			}
			String uuid = UUID.randomUUID().toString();
			tbHotNewsPO.setId(uuid);
			tbHotNewsPO.setCreateTime(new Date());
			tbHotNewsPO.setCreateUserId(user.getPersonId());
			tbHotNewsPO.setLastModifyTime(new Date());
			tbHotNewsPO.setLastModifyUserId(user.getPersonId());
			this.hotNewsInfoDAO.insertData(tbHotNewsPO);
		}else{
			tbHotNewsPO.setLastModifyTime(new Date());
			tbHotNewsPO.setLastModifyUserId(user.getPersonId());
			this.hotNewsInfoDAO.update(tbHotNewsPO, false);
		}
	}
}
