package cn.com.do1.component.share.sharemanager.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;

import java.util.Map;

import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.share.sharemanager.model.TbShareInfoPO;
import cn.com.do1.component.share.sharemanager.vo.ShareRequest;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;

import java.sql.SQLException;
import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface ISharemanagerService extends IBaseService{
    
	Pager searchTbShareInfo(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbShareInfoVO> searchTbShareInfoList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbShareInfoPO po) throws Exception, DataConfictException;
    List<TbShareInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

	void postShare(String postUserId, String shareId, String context,
			String title) throws Exception, BaseException;

	void replyShare(String replyUserId, String postId, String context)
			throws Exception, BaseException;
}
