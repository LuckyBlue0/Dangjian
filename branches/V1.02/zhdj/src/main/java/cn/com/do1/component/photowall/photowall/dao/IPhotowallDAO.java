package cn.com.do1.component.photowall.photowall.dao;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallImgVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: tanshaoqi
*/
public interface IPhotowallDAO extends IBaseDAO {
	Pager searchTbPhotoWall (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbPhotoWallVO> searchTbPhotoWallList(Map<String, Object> searchValue) throws SQLException;

	PhotoWallDetailsResponse findPhotoWallById(String id)throws SQLException;
  
	Pager searchTbPhotoWallReview(Map searchValue, Pager pager)throws Exception, BaseException;

	TbPhotoWallVO findPhotoWallVOById(String id)throws Exception, BaseException;

	ArrayList<TbPhotoWallReviewVO> findPhotowallListById(String id)throws Exception, BaseException;

	List<TbPhotoWallImgVO> findPhotowallImgsById(String id)throws Exception, BaseException;
	
	
}
