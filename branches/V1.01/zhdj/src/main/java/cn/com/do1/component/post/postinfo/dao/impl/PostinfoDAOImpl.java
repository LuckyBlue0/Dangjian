package cn.com.do1.component.post.postinfo.dao.impl;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.post.postinfo.dao.IPostinfoDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.component.post.postinfo.vo.TbPostInfoVO;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;
import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class PostinfoDAOImpl extends BaseDAOImpl implements IPostinfoDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(PostinfoDAOImpl .class);
    
    final static String searchSQL = "select t.*,m.name ,(select count(1) from tb_reply_info r where t.id=r.post_id) reply from TB_post_INFO t left join TB_PARTY_MENBER_INFO m on m.id=t.post_user_id where t.id=:id and t.share_id=:shareId and t.context like :keyword order by t.create_time desc";
    final static String countSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
    /*final static String condSQL = "select t.*,(select count(1) from tb_reply_info r where t.id=r.post_id) reply from TB_post_INFO t" ;
    final static String countSQL = "select count(1)  " + condSQL;
    final static String searchSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";*/
	@Override
    public Pager searchTbPostInfo(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
        return super.pageSearchByField(TbPostInfoVO.class, countSQL, searchSQL, searchValue, pager);
    }
	 final static String revicewSearchSQL = "select t.id,t.post_user_id,t.context as title,r.create_time as replayTime,r.context ,r.id as replayId,m.name as user_name from TB_POST_INFO t left join TB_REPLY_INFO r on r.reply_user_id=t.post_user_id left join TB_PARTY_MENBER_INFO m on m.id=t.post_user_id"+
 			" where t.status=1 and t.id=:id order by t.create_time desc"; 
 final static String revicewCountSQL = "select count(1) from (" + revicewSearchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
	@Override
 public Pager searchTbPostDetail(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
     /**
      * 自己写好查询总页数以及查询语句后调用框架方法
      * TbDqdpUserPO 查询结果封装类
      * countSQL统计总条数的语句
      * searchSQL 查询数据的语句
      * searchValue 查询条件
      * pager分页信息
      */
     return super.pageSearchByField(TbPostInfoVO.class, revicewCountSQL, revicewSearchSQL, searchValue, pager);
 }
	
final static String reSearchSQL = "select t.* from TB_POST_INFO t where t.post_user_id=:userId order by t.create_time desc"; 
final static String reCountSQL = "select count(1) from (" + reSearchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
	@Override
 public Pager searchPostData(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
    
     return super.pageSearchByField(TbPostInfoVO.class, reCountSQL, reSearchSQL, searchValue, pager);
 }
    @Override
    public List<TbPostInfoVO> searchTbPostInfoList(Map<String, Object> searchValue) throws SQLException {
        preparedSql(searchSQL);
        setPreValues(searchValue);//将参数设置进预置语句
        return getList(TbPostInfoVO.class);
    }

    
    
    
    public Pager searchTbPostInfoListQnh(Map<String, Object> searchValue,@Security(encode = "")Pager pager) throws Exception, BaseException{
    	String searchPostSQL = "select t.* ,pi.name user_name from tb_reply_info t left join tb_party_menber_info pi on t.reply_user_id=pi.id where t.post_id = :id order by t.create_time asc "; 
        String countPostSQL = "select count(1) from (" + searchPostSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
        return super.pageSearchByField(TbReplyVO.class, countPostSQL, searchPostSQL , searchValue, pager);
    }
    @Override
    public TbPostInfoVO searchTbPostInfo(String id) throws SQLException{
    	String sql = "select t.*,pi.name from tb_post_info t left join tb_party_menber_info pi on t.post_user_id=pi.id where t.id = '"+id+"' ";
    	return super.executeQuery(sql, TbPostInfoVO.class);
    }
    
    
    final static String replySearchSQL = "select p.* from tb_post_info p where id in (select distinct r.post_id from tb_reply_info r where r.reply_user_id =:userId)";

    final static String replyCountSQL = "select count(1) from (" + replySearchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
	@Override
	public Pager searchReplyData(Map searchValue, Pager pager)throws Exception, BaseException{
		 return super.pageSearchByField(TbPostInfoVO.class, replyCountSQL, replySearchSQL, searchValue, pager);
	}

}
