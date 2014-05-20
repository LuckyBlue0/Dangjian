package cn.com.do1.component.post.postinfo.dao;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import cn.com.do1.common.annotation.po.Security;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.post.postinfo.vo.TbPostInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface IPostinfoDAO extends IBaseDAO {
	Pager searchTbPostInfo (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbPostInfoVO> searchTbPostInfoList(Map<String, Object> searchValue) throws SQLException;


	Pager searchTbPostDetail(Map searchValue, Pager pager) throws Exception,
			BaseException;
	
	Pager searchTbPostInfoListQnh(Map<String, Object> searchValue, Pager pager)  throws Exception, BaseException;
	
	Pager searchPostData(Map searchValue, Pager pager) throws Exception,
			BaseException;

	Pager searchReplyData(Map searchValue, Pager pager) throws Exception, BaseException;

	TbPostInfoVO searchTbPostInfo(String id) throws SQLException;
}
