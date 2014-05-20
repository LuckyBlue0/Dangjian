package cn.com.do1.component.basis.newstype.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.newstype.dao.INewsTypeDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("newsTypeService")
public class NewsTypeServiceImpl extends BaseService implements INewsTypeService {
    private final static transient Logger logger = LoggerFactory.getLogger(NewsTypeServiceImpl.class);

    private INewsTypeDAO newsTypeDAO;
    @Resource
    public void setNewsTypeDAO(INewsTypeDAO newsTypeDAO) {
        this.newsTypeDAO = newsTypeDAO;
        setDAO(newsTypeDAO);
    }

    @Override
    public Pager searchNewsType(Map searchMap,Pager pager) throws Exception, BaseException {
        return newsTypeDAO.searchNewsType(searchMap,pager);
    }
    
	@Override
	public List<TbNewsColumnTypePO> getNewsType() throws Exception {
		// TODO Auto-generated method stub
		return newsTypeDAO.getNewsType();
	}
    

	@Override
	public void saveNewsTypePO(TbNewsColumnTypePO tbNewsColumnTypePO) throws Exception{
		// TODO Auto-generated method stub
		TbNewsColumnTypePO po = this.newsTypeDAO.getNewsTypeByType(tbNewsColumnTypePO.getOrderValue());
		
		if(po != null ){
			if(!AssertUtil.isEmpty(tbNewsColumnTypePO.getNewsTypeId()) && 
					!tbNewsColumnTypePO.getNewsTypeId().equals(po.getNewsTypeId())){
				throw new Exception("栏目排序\""+tbNewsColumnTypePO.getOrderValue()+"\"已经被占用,请重新填写,谢谢!");
			}
			
			if(po.getOrderValue().equals(tbNewsColumnTypePO.getOrderValue())
					&& !tbNewsColumnTypePO.getNewsTypeId().equals(po.getNewsTypeId())){
				throw new Exception("栏目排序\""+tbNewsColumnTypePO.getOrderValue()+"\"已经被占用,请重新填写,谢谢!");
			}
			
		}
		
		//默认为可用
		if(tbNewsColumnTypePO.getUseStatus()==null){
			tbNewsColumnTypePO.setUseStatus(1);
		}
		
		IUser user = (IUser) DqdpAppContext.getCurrentUser();// 获取当前登录用户信息
		if(!AssertUtil.isEmpty(tbNewsColumnTypePO.getAttributeId())){
			tbNewsColumnTypePO.setAttributeId(tbNewsColumnTypePO.getAttributeId().replace(" ",""));
		}
		if(tbNewsColumnTypePO.getParentId() != null){
			tbNewsColumnTypePO.setLink("/jsp/component/news/newsinfo/list.jsp?type="+tbNewsColumnTypePO.getOrderValue());
		}
		tbNewsColumnTypePO.setCreateType(1);//管理员创建
		if(AssertUtil.isEmpty(tbNewsColumnTypePO.getNewsTypeId())){
			tbNewsColumnTypePO.setNewsTypeId(UUID.randomUUID().toString());
			tbNewsColumnTypePO.setType(tbNewsColumnTypePO.getOrderValue());
			tbNewsColumnTypePO.setCreateUserId(user.getUsername());
			tbNewsColumnTypePO.setCreateTime(new Date());
			this.newsTypeDAO.insertData(tbNewsColumnTypePO);
		}else{
			tbNewsColumnTypePO.setLastModifyUserId(user.getUsername());
			tbNewsColumnTypePO.setLastModifyTime(new Date());
			this.newsTypeDAO.update(tbNewsColumnTypePO, false);
		}
	}

	@Override
	public TbNewsColumnTypePO getNewsTypeByType(String type) throws Exception {
		// TODO Auto-generated method stub
		return this.newsTypeDAO.getNewsTypeByType(Integer.parseInt(type));
	}

	@Override
	public List<AqNewsListTabVO> searchAqNewsListTab(String parentId) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return newsTypeDAO.searchAqNewsListTab(parentId);
	}
}
