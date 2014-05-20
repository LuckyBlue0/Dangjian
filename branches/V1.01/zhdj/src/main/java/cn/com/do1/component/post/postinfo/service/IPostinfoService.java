package cn.com.do1.component.post.postinfo.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;

import java.util.Map;

import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.post.postinfo.model.TbPostInfoPO;
import cn.com.do1.component.post.postinfo.vo.TbPostInfoVO;

import java.sql.SQLException;
import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface IPostinfoService extends IBaseService{
    
	Pager searchTbPostInfo(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbPostInfoVO> searchTbPostInfoList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbPostInfoPO po) throws Exception, DataConfictException;
    List<TbPostInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

	Pager searchTbPostDetail(Map searchValue, Pager pager) throws Exception, BaseException;
	
	Pager searchTbPostInfoListQnh(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;


	Pager searchPostData(Map searchValue, Pager pager) throws Exception, BaseException;

	Pager searchReplyData(Map searchValue, Pager pager) throws Exception, BaseException;
	
	TbPostInfoVO searchTbPostInfo(String id) throws SQLException;
}