package cn.com.do1.component.post.postinfo.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.post.postinfo.dao.IPostinfoDAO;
import cn.com.do1.component.post.postinfo.service.IPostinfoService;
import cn.com.do1.component.post.postinfo.model.TbPostInfoPO;
import cn.com.do1.component.post.postinfo.vo.TbPostInfoVO;

import org.springframework.stereotype.Service;

import cn.com.do1.common.util.security.EncryptionUtils;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
@Service("postinfoService")
public class PostinfoServiceImpl extends BaseService implements IPostinfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(PostinfoServiceImpl .class);

    private IPostinfoDAO postinfoDAO;
    
    @Resource
    public void setPostinfoDAO(IPostinfoDAO postinfoDAO) {
        this.postinfoDAO = postinfoDAO;
        setDAO(postinfoDAO);
    }

	@Override 
    public Pager searchTbPostInfo(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return postinfoDAO .searchTbPostInfo(searchMap, pager);
    }
    
    @Override
    public List<TbPostInfoVO> searchTbPostInfoList(Map<String, Object> searchValue) throws SQLException {
        return postinfoDAO .searchTbPostInfoList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbPostInfoPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        postinfoDAO .delete(po);
    }

    @Override
    public void casAdd(TbPostInfoPO po) throws Exception, DataConfictException {
        postinfoDAO .insert(po);
    }

    @Override 
    public List<TbPostInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return postinfoDAO .searchByField(TbPostInfoPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbPostInfoPO po = new TbPostInfoPO();
        po._setPKValue(id);
        postinfoDAO .delete(po);
    }

	@Override
	public Pager searchTbPostDetail(Map searchValue, Pager pager) throws Exception, BaseException {
		return postinfoDAO .searchTbPostDetail(searchValue, pager);
	}

	@Override
	public Pager searchTbPostInfoListQnh(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException{
		return postinfoDAO .searchTbPostInfoListQnh(searchValue,pager);
	}

	@Override
	public Pager searchPostData(Map searchValue, Pager pager) throws Exception, BaseException {
		return postinfoDAO .searchPostData(searchValue, pager);
	}

	@Override
	public Pager searchReplyData(Map searchValue, Pager pager)throws Exception, BaseException {
		return postinfoDAO .searchReplyData(searchValue, pager);
	}

	@Override
	public TbPostInfoVO searchTbPostInfo(String id) throws SQLException {
		// TODO Auto-generated method stub
		return postinfoDAO .searchTbPostInfo(id);
	}

}
