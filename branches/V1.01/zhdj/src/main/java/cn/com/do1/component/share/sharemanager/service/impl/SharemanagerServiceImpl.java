package cn.com.do1.component.share.sharemanager.service.impl;

import java.sql.SQLException;
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
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.post.postinfo.model.TbPostInfoPO;
import cn.com.do1.component.share.sharemanager.dao.ISharemanagerDAO;
import cn.com.do1.component.share.sharemanager.model.TbReplyPO;
import cn.com.do1.component.share.sharemanager.model.TbShareInfoPO;
import cn.com.do1.component.share.sharemanager.service.ISharemanagerService;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
@Service("sharemanagerService")
public class SharemanagerServiceImpl extends BaseService implements ISharemanagerService {
    private final static transient Logger logger = LoggerFactory.getLogger(SharemanagerServiceImpl .class);

    private ISharemanagerDAO sharemanagerDAO;
    
    @Resource
    public void setSharemanagerDAO(ISharemanagerDAO sharemanagerDAO) {
        this.sharemanagerDAO = sharemanagerDAO;
        setDAO(sharemanagerDAO);
    }

	@Override 
    public Pager searchTbShareInfo(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return sharemanagerDAO .searchTbShareInfo(searchMap, pager);
    }
    
    @Override
    public List<TbShareInfoVO> searchTbShareInfoList(Map<String, Object> searchValue) throws SQLException {
        return sharemanagerDAO .searchTbShareInfoList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbShareInfoPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        sharemanagerDAO .delete(po);
    }

    @Override
    public void casAdd(TbShareInfoPO po) throws Exception, DataConfictException {
        sharemanagerDAO .insert(po);
    }

    @Override 
    public List<TbShareInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return sharemanagerDAO .searchByField(TbShareInfoPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbShareInfoPO po = new TbShareInfoPO();
        po._setPKValue(id);
        sharemanagerDAO .delete(po);
    }
    
    @Override
	public void postShare(String postUserId,String shareId,String context,String title) throws Exception, BaseException {
	
    	TbPostInfoPO po = new TbPostInfoPO();	
		//发帖信息
		String id = UUID.randomUUID().toString().toLowerCase();
		po.setId(id);
		po.setTitle(title);
		po.setCreateTime(new Date());
		po.setPostUserId(postUserId);
		po.setShareId(shareId);
		po.setViewNum(0L);
		po.setStatus("1");

		po.setContext(context);
		this.sharemanagerDAO.insertData(po);
		
	}
    @Override
   	public void replyShare(String replyUserId,String postId,String context) throws Exception, BaseException {
   	
       	TbReplyPO po = new TbReplyPO();	
   		//回帖信息
   		String id = UUID.randomUUID().toString().toLowerCase();
   		po.setId(id);
   		po.setCreateTime(new Date());
   		po.setPostId(postId);;
   		po.setStatus("1");
   		po.setReplyUserId(replyUserId);
   		po.setContext(context);
   		Long num = 1l;
   		try{
   			TbReplyVO vo = sharemanagerDAO.getTbReplyMaxNum(postId);
   			num = vo.getOnNum()+1;
   		}catch(Exception e){
   			
   		}
   		po.setOnNum(num);
   		this.sharemanagerDAO.insertData(po);
   		
   	
   	}
   	
}
