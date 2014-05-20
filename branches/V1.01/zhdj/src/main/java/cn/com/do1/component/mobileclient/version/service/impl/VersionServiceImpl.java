package cn.com.do1.component.mobileclient.version.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.mobileclient.version.dao.IVersionDAO;
import cn.com.do1.component.mobileclient.version.service.IVersionService;
import cn.com.do1.component.mobileclient.version.vo.VersionVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("versionService")
public class VersionServiceImpl extends BaseService implements IVersionService {
    private final static transient Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);

    private IVersionDAO versionDAO;
    @Resource
    public void setVersionDAO(IVersionDAO versionDAO) {
        this.versionDAO = versionDAO;
        setDAO(versionDAO);
    }

    public Pager searchVersion(Map searchMap,Pager pager) throws Exception, BaseException {
        return versionDAO.searchVersion( searchMap,pager);
    }

	@Override
	public VersionVO getLastNewVersion(Map searchMap) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return versionDAO.getLastNewVersion(searchMap);
	}

	@Override
	public void invalidVersion() throws Exception, BaseException {
		this.versionDAO.invalidVersion();
	}
}
