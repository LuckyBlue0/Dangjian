package cn.com.do1.component.index.index.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.component.index.index.dao.IIndexDAO;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.index.index.service.IIndexService;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("indexService")
public class IndexServiceImpl extends BaseService implements IIndexService {
    private final static transient Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    private IIndexDAO indexDAO;
    @Resource
    public void setIndexDAO(IIndexDAO indexDAO) {
        this.indexDAO = indexDAO;
        setDAO(indexDAO);
    }
    
    @Resource
    private INewsTypeService newsTypeService;

    public Pager searchIndex(Map searchMap,Pager pager) throws Exception, BaseException {
        return indexDAO.pageSearchByField( IBaseDBVO.class,searchMap,null,pager);
    }
    public List<ShortNewsInfoVO> searchTopPic()throws Exception, BaseException{
    	return indexDAO.searchTopPic();
    }
    
    public List<ShortNewsInfoVO> searchTopHotNews()throws Exception, BaseException{
    	return indexDAO.searchTopHotNews();
    }
    
    public List<ShortNewsInfoVO> ajaxSearchNews(String newInfoType)throws Exception, BaseException{
    	return indexDAO.ajaxSearchNews(newInfoType);
    }
    public TbNewsColumnTypePO searchNewsType(String typeName)throws Exception, BaseException{
    	return indexDAO.searchNewsType(typeName);
    }
    public LoginUserVO loginUser(LoginUserVO vo,String type)throws Exception, BaseException{
    	return indexDAO.loginUser(vo,type);
    }
    public TbNewsColumnTypePO searchNewsTypeId(String name)throws Exception, BaseException{
    	return indexDAO.searchNewsTypeId(name);
    }
    public Pager ajaxSearch(Map searchMap, Pager pager) throws Exception, BaseException{
    	return indexDAO.ajaxSearch(searchMap,pager);
    }
    public boolean isUserNameExist(String username)throws Exception, BaseException{
    	return indexDAO.isUserNameExist(username);
    }

    public ShortNewsInfoVO searchPartalNews(String id)throws Exception, BaseException{
    	return indexDAO.searchPartalNews(id);
    }

    public ShortNewsInfoVO searchNextNews(String id)throws Exception, BaseException{
    	return indexDAO.searchNextNews(id);
    }

	@Override
	public boolean checkLonginScoreToday(String userId, String scoreType) throws Exception,
			BaseException {
		return indexDAO.checkLonginScoreToday(userId, scoreType);
	}
	/**
	 * 根据新闻的type查询新闻类型的ID
	 * @param type
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public String searchTypeIdByType(String type)throws Exception, BaseException{
		return indexDAO.searchTypeIdByType(type);
	}

	@Override
	public List<ShortNewsInfoVO> searchNewsByType(String type, int num) throws Exception, BaseException {
		return indexDAO.searchNewsByType(type,num);
	}

	@Override
	public List<ShortNewsInfoVO> searchAqNewsIndexList(String parentId, int num) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return indexDAO.searchAqNewsIndexList(parentId,num);
	}
    
}
