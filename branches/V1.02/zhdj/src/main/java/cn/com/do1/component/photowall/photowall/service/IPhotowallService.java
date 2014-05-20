package cn.com.do1.component.photowall.photowall.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.photowall.photowall.model.TbPhotoWallPO;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: tanshaoqi
*/
public interface IPhotowallService extends IBaseService{
    
	Pager searchTbPhotoWall(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbPhotoWallVO> searchTbPhotoWallList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbPhotoWallPO po) throws Exception, DataConfictException;
    List<TbPhotoWallPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

	/**
	 * 留影墙发布
	 * @param request
	 */
	void pushPhotowall(PhotowallTypeRequest request)throws Exception, BaseException;

	
	/**
	 * 留影墙详情
	 * @param id
	 * @return
	 */
	PhotoWallDetailsResponse findPhotoWallById(String id)throws Exception, BaseException;

	/**
	 * 留影墙评论列表
	 * @param searchValue
	 * @param pager
	 * @return
	 */
	Pager searchTbPhotoWallReview(Map searchValue, Pager pager)throws Exception, BaseException;

	
	/**
	 * 留影墙评论
	 * @param request
	 */
	void photowallReview(PhotowallTypeRequest request)throws Exception, BaseException;

	/**
	 * 留影墙详情
	 * @param id
	 * @return
	 */
	TbPhotoWallVO findPhotoWallVOById(String id)throws Exception, BaseException;

	/**
	 * 留影墙评论列表(包含所有状态)
	 * @param id
	 * @return
	 */
	ArrayList<TbPhotoWallReviewVO> findPhotowallListById(String id)throws Exception, BaseException;
}
