package cn.com.do1.component.photowall.photowall.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.photowall.photowall.dao.IPhotowallDAO;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallListResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallReviewListResponse;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallImgVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: tanshaoqi
*/
public class PhotowallDAOImpl extends BaseDAOImpl implements IPhotowallDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(PhotowallDAOImpl .class);
    
    final static String searchSQL = "SELECT t.*,m.name user_name FROM TB_PHOTO_WALL t left join TB_PARTY_MENBER_INFO m on m.id=t.CREATE_USER_ID" +
    		" where t.type=:type and t.title like :title order by t.CREATE_TIME desc";
    final static String countSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
    
    final static String moblieclient_searchSQL = "SELECT t.*,m.name user_name,t2.img_url FROM TB_PHOTO_WALL t left join TB_PARTY_MENBER_INFO m on m.id=t.CREATE_USER_ID" +
	" left join (select * from (select i.img_url,i.photo_wall_id from tb_photo_wall_img i order by i.sort asc ) where rownum=1) t2 on t.id=t2.photo_wall_id"+ 
	" where t.type=:type and t.status=0 and (1=1 or t.title like :keyword or t.des like :keyword )  order by t.CREATE_TIME desc";
    final static String moblieclient_countSQL = "select count(1) from (" + moblieclient_searchSQL+"  ) a ";
     
	@Override 
    public Pager searchTbPhotoWall(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
    	String mobileClient = (String) searchValue.get("mobileClient");
    	searchValue.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
    		return super.pageSearchByField(TbPhotoWallVO.class, countSQL, searchSQL, searchValue, pager);
    	}else{
    		System.out.println(moblieclient_searchSQL);
    		System.out.println(moblieclient_countSQL);
    		return super.pageSearchByField(PhotoWallListResponse.class, moblieclient_countSQL, moblieclient_searchSQL, searchValue, pager);
    	}
    }

    @Override
    public List<TbPhotoWallVO> searchTbPhotoWallList(Map<String, Object> searchValue) throws SQLException {
        preparedSql(searchSQL);
        setPreValues(searchValue);//将参数设置进预置语句
        return getList(TbPhotoWallVO.class);
    }
	@Override
	public PhotoWallDetailsResponse findPhotoWallById(String id) throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.title,t.create_time as push_time,m.name as author,t3.review_count From TB_PHOTO_WALL t left join TB_PARTY_MENBER_INFO m on m.id=t.CREATE_USER_ID")
		.append(" ,(select count(*) as review_count from tb_photo_wall_review t where t.photo_wall_id=:id and t.status=0) t3")
		.append(" where t.id=:id and t.status=0");
		preparedSql(sb.toString());
		setPreValue("id", id);
		return executeQuery(PhotoWallDetailsResponse.class);
	}

    final static String revicewSearchSQL = "select t.content,t.time as review_time,t.img_url,m.name as user_name,m.portrait_pic from tb_photo_wall_review t left join TB_PARTY_MENBER_INFO m on m.id=t.USER_ID"+
    			" where t.status=0 and t.photo_wall_id=:id order by t.time desc"; 
    final static String revicewCountSQL = "select count(1) from (" + revicewSearchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
	@Override
	public Pager searchTbPhotoWallReview(Map searchValue, Pager pager) throws Exception, BaseException {
		// TODO Auto-generated method stub
    	String mobileClient = (String) searchValue.get("mobileClient");
    	searchValue.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
    		return super.pageSearchByField(TbPhotoWallReviewVO.class, revicewCountSQL, revicewSearchSQL, searchValue, pager);
    	}else{
    		return super.pageSearchByField(PhotoWallReviewListResponse.class, revicewCountSQL, revicewSearchSQL, searchValue, pager);
    	}
	}

	@Override
	public TbPhotoWallVO findPhotoWallVOById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.*,m.name as user_name,t3.review_count From TB_PHOTO_WALL t left join TB_PARTY_MENBER_INFO m on m.id=t.CREATE_USER_ID")
		.append(" ,(select * from (select i.img_url from tb_photo_wall_img i where i.photo_wall_id=:id order by i.sort asc ) where rownum=1) t2")
		.append(" ,(select count(*) as review_count from tb_photo_wall_review t where t.photo_wall_id=:id) t3")
		.append(" where t.id=:id");
		preparedSql(sb.toString());
		setPreValue("id", id);
		return executeQuery(TbPhotoWallVO.class);
	}

	final static String findPhotowallListById_sql = "select t.*,m.name as user_name,m.portrait_pic from tb_photo_wall_review t left join TB_PARTY_MENBER_INFO m on m.id=t.USER_ID"+
	" where t.photo_wall_id=:id order by t.time desc";
	@Override
	public ArrayList<TbPhotoWallReviewVO> findPhotowallListById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
	    preparedSql(findPhotowallListById_sql);
	    setPreValue("id",id);
	    return (ArrayList)getList(TbPhotoWallReviewVO.class);
	}
	@Override
	public List<TbPhotoWallImgVO> findPhotowallImgsById(String id)throws Exception, BaseException{
	    preparedSql("select * from tb_photo_wall_img where photo_wall_id=:id order by sort asc");
	    setPreValue("id",id);
	    return getList(TbPhotoWallImgVO.class);
	}
}
