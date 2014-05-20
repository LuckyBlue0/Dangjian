package cn.com.do1.component.news.newsinfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.newstype.dao.INewsTypeDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.news.newsinfo.dao.INewsInfoDAO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
@Service("newsInfoService")
public class NewsInfoServiceImpl extends BaseService implements INewsInfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(NewsInfoServiceImpl.class);

    private INewsInfoDAO newsInfoDAO;
    @Resource
    private INewsTypeDAO newsTypeDAO;
    @Resource
    public void setNewsinfoDAO(INewsInfoDAO newsInfoDAO) {
        this.newsInfoDAO = newsInfoDAO;
        setDAO(newsInfoDAO);
    }
    @Resource
    private IOrgService orgService;

    public Pager searchNewsinfo(Map searchMap,Pager pager) throws Exception, BaseException {
        return newsInfoDAO.pageSearchByField(searchMap,pager);
    }

	@Override
	public void saveNewsInfo(TbNewsInfoPO tbNewsInfoPO) throws Exception, BaseException {
		// TODO Auto-generated method stub
		IUser user = (IUser)DqdpAppContext.getCurrentUser();
		if(AssertUtil.isEmpty(tbNewsInfoPO.getStatus())){
			tbNewsInfoPO.setStatus(0);
		}
		if(!AssertUtil.isEmpty(tbNewsInfoPO.getStatus()) && tbNewsInfoPO.getStatus() == 1){
			tbNewsInfoPO.setPushTime(new Date());
			tbNewsInfoPO.setPushUserId(user.getPersonId());
		}
		
		
		OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
		if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
			tbNewsInfoPO.setOrganizationId(orgVO.getOrganizationId());
		}else if(user.getUsername().equals("admin")){
			logger.info("系统管理员没有归属组织,不需要记录组织");
		}
		
		if(AssertUtil.isEmpty(tbNewsInfoPO.getNewsInfoId())){
			if(AssertUtil.isEmpty(tbNewsInfoPO.getBuyTop())){
				tbNewsInfoPO.setBuyTop(0);
			}
			if(AssertUtil.isEmpty(tbNewsInfoPO.getSolidTop())){
				tbNewsInfoPO.setSolidTop(0);
			}
			if(AssertUtil.isEmpty(tbNewsInfoPO.getImportance())){
				tbNewsInfoPO.setImportance(0);
			}
			String uuid = UUID.randomUUID().toString();
			tbNewsInfoPO.setNewsInfoId(uuid);
			tbNewsInfoPO.setCreateTime(new Date());
			tbNewsInfoPO.setCreateUserId(user.getPersonId());
			tbNewsInfoPO.setLastModifyTime(new Date());
			tbNewsInfoPO.setLastModifyUserId(user.getPersonId());
			
			TbNewsColumnTypePO newsTypePO = newsTypeDAO.getNewsTypeByType(Integer.parseInt(tbNewsInfoPO.getNewsInfoType()));
			tbNewsInfoPO.setNewsInfoTypeId(newsTypePO.getNewsTypeId());
			this.newsInfoDAO.insertData(tbNewsInfoPO);
		}else{
			tbNewsInfoPO.setLastModifyTime(new Date());
			tbNewsInfoPO.setLastModifyUserId(user.getPersonId());
			this.newsInfoDAO.update(tbNewsInfoPO, false);
		}
		
	}

	@Override
	public List<ExItemObj> getDictsByType(String fsType) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return DictOperater.getAllItemByType(fsType);
	}

	@Override
	public TbNewsInfoPO getNewsInfoByType(String type) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.newsInfoDAO.getNewsInfoByType(type);
	}

	@Override
	public NewsPreviewInfoVO getNewsPreviewInfo(String newsInfoId, int type) throws Exception, BaseException {
		// TODO Auto-generated method stub
		NewsPreviewInfoVO VO = newsInfoDAO.getNewsPreviewInfo(newsInfoId,type);
		return VO;
	}

	@Override
	public Pager searchAqNewsinfo(Map searchValue, Pager pager) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return newsInfoDAO.searchAqNewsinfo(searchValue,pager);
	}

}
