package cn.com.do1.component.index.index.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.index.index.dao.IIndexDAO;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class IndexDAOImpl extends BaseDAOImpl implements IIndexDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(IndexDAOImpl.class);

	public List<ShortNewsInfoVO> searchTopPic() throws Exception, BaseException {
		String sql = "SELECT * FROM (select news_info_id,title,img_path from TB_NEWS_INFO where img_path is not null and status=1 order by buy_top desc,push_time desc)where rownum<=5";
		super.preparedSql(sql);
		return super.getList(ShortNewsInfoVO.class);
	}
	
	@Override
	public List<ShortNewsInfoVO> searchTopHotNews() throws Exception, BaseException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM (select id as news_info_id, title, img_path,push_time from tb_hot_news where img_path is not null and status = 1 order by buy_top desc, push_time desc)where rownum <= 5";
		super.preparedSql(sql);
		return super.getList(ShortNewsInfoVO.class);
	}

	public List<ShortNewsInfoVO> ajaxSearchNews(String newInfoType)
			throws Exception, BaseException {
		String sql = "";
		if ("1".equals(newInfoType) || "2".equals(newInfoType)) {
			sql = "SELECT * FROM (select news_info_id,title,img_path from TB_NEWS_INFO where news_info_type = :newInfoType and img_path is not null and status=1 order by buy_top desc,push_time desc)where rownum<=6";
		} else {
			sql = "SELECT * FROM (select news_info_id,title,img_path from TB_NEWS_INFO where news_info_type = :newInfoType and img_path is not null and status=1 order by buy_top desc,push_time desc)where rownum<=5";
		}
		super.preparedSql(sql);
		super.setPreValue("newInfoType", newInfoType);
		return super.getList(ShortNewsInfoVO.class);
	}

	public TbNewsColumnTypePO searchNewsType(String typeName) throws Exception,
			BaseException {
		String sql = "select * from tb_news_column_type where name = :typeName";
		super.preparedSql(sql);
		super.setPreValue("typeName", typeName);
		return super.executeQuery(TbNewsColumnTypePO.class);

	}

	public LoginUserVO loginUser(LoginUserVO vo, String type) throws Exception,
			BaseException {
		LoginUserVO userVO = null;
		if ("1".equals(type)) {
			String sql = "select id user_Id,user_name username,password user_Pwd,name,ORGANIZATION_ID ORGANIZATION_ID from TB_PARTY_MENBER_INFO where (USER_NAME = :username or mobile = :username) and PASSWORD = :password";
			super.preparedSql(sql);
			super.setPreValue("username", vo.getUsername());
			super.setPreValue("password", vo.getUserPwd());
			userVO = super.executeQuery(LoginUserVO.class);
		} else if ("2".equals(type)) {
			String sql = "select id user_Id,user_name username,pass_word user_Pwd,name from TB_PARTY_DEV_MENBER_INFO where (USER_NAME = :username or mobile = :username) and pass_word = :password and state = 1";
			super.preparedSql(sql);
			super.setPreValue("username", vo.getUsername());
			super.setPreValue("password", vo.getUserPwd());
			userVO = super.executeQuery(LoginUserVO.class);
		} else if ("3".equals(type)) {
			String sql1 = "select id user_Id,user_name username,password user_Pwd,name,ORGANIZATION_ID ORGANIZATION_ID from TB_PARTY_MENBER_INFO where (USER_NAME = :username or mobile = :username) and PASSWORD = :password";
			String sql2 = "select id user_Id,user_name username,pass_word user_Pwd,name from TB_PARTY_DEV_MENBER_INFO where (USER_NAME = :username or mobile = :username) and pass_word = :password and state = 1";
			super.preparedSql(sql1);
			super.setPreValue("username", vo.getUsername());
			super.setPreValue("password", vo.getUserPwd());
			LoginUserVO userVO1 = super.executeQuery(LoginUserVO.class);
			super.preparedSql(sql2);
			super.setPreValue("username", vo.getUsername());
			super.setPreValue("password", vo.getUserPwd());
			LoginUserVO userVO2 = super.executeQuery(LoginUserVO.class);
			if (userVO1 != null) {
				userVO1.setUserType("1");
				return userVO1;
			} else {
				if(userVO2 != null){
					userVO2.setUserType("2");
					return userVO2;
				}
				
			}
		}
		if(AssertUtil.isEmpty(userVO)){
			return null;
		}
		if(userVO.getOrganizationId() != null){
			userVO.setUserType("1");		//党员
		}else{
			userVO.setUserType("2");		//群众 用户
		}
		return userVO;
	}

	public TbNewsColumnTypePO searchNewsTypeId(String name) throws Exception,
			BaseException {
		String sql = "select * from tb_news_column_type where name = :name";
		super.preparedSql(sql);
		super.setPreValue("name", name);
		List<TbNewsColumnTypePO> list = super.getList(TbNewsColumnTypePO.class);
		if (!AssertUtil.isEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Pager ajaxSearch(Map searchMap, Pager pager) throws Exception,
			BaseException {
		String selectSQL = "select news_info_id,title,img_path,push_time from tb_news_info where news_info_type = :newInfoType "
				+ " and status=1 order by buy_top desc,push_time desc";
		String countSQL = "select count(*) from tb_news_info where news_info_type = :newInfoType "
				+ " and status=1 order by buy_top desc,push_time desc";
		return super.pageSearchByField(ShortNewsInfoVO.class, countSQL,
				selectSQL, searchMap, pager);

	}

	public boolean isUserNameExist(String username) throws Exception,
			BaseException {
		String countSQL = "select count(*) from TB_PARTY_DEV_MENBER_INFO where user_name = :username";
		super.preparedSql(countSQL);
		super.setPreValue("username", username);
		int count = super.executeQuery(Integer.class);
		if (count >= 1) {
			return true;
		} else {
			return false;
		}

	}

	public ShortNewsInfoVO searchPartalNews(String id) throws Exception,
			BaseException {

		// String
		// sql="select * from (select rownum as rn,t.* from tb_news_info t order by create_time desc ) q "+
		// " where rn=((select rownum  from tb_news_info  where news_info_id = :id )-1)";
		// String
		// sql="select * from tb_news_info where create_time =(select max(a.create_time) from tb_news_info a where a.news_info_type =(select b.news_info_type  from tb_news_info b  where b.news_info_id = :id)"+
		// " and a.create_time <( select c.create_time  from tb_news_info c where c.news_info_id = :id))";
		String sql = "select * from tb_news_info where news_info_id=(select c.p from (select news_info_id,lag(news_info_id,1,0)  over (order by buy_top desc,push_time desc) as p from tb_news_info"
				+ " where news_info_type =(select b.news_info_type  from tb_news_info b  where b.news_info_id = :id) ) c where c.news_info_id = :id)";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(ShortNewsInfoVO.class);
	}

	public ShortNewsInfoVO searchNextNews(String id) throws Exception,
			BaseException {
		// String
		// sql="select * from (select rownum as rn,t.* from tb_news_info t order by create_time desc ) q "+
		// " where rn=((select rownum  from tb_news_info  where news_info_id = :id )+1)";
		// String
		// sql="select * from tb_news_info where create_time =(select min(a.create_time) from tb_news_info a where a.news_info_type =(select b.news_info_type  from tb_news_info b  where b.news_info_id = :id)"+
		// " and a.create_time >( select c.create_time  from tb_news_info c where c.news_info_id = :id))";
		String sql = "select * from tb_news_info where news_info_id=(select c.p from (select news_info_id,lead(news_info_id,1,0)  over (order by buy_top desc,push_time desc) as p from tb_news_info"
				+ " where news_info_type =(select b.news_info_type  from tb_news_info b  where b.news_info_id = :id) ) c where c.news_info_id = :id)";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(ShortNewsInfoVO.class);
	}

	public boolean checkLonginScoreToday(String userId, String scoreType) throws Exception, BaseException{
		String countSQL = "select count(*) from tb_personal_score_info t where to_char(t.get_time,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd') and t.user_id = :userId and t.score_type = :scoreType ";
		super.preparedSql(countSQL);
		super.setPreValue("userId", userId);
		super.setPreValue("scoreType", scoreType);
		int count = super.executeQuery(Integer.class);
		if (count >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public String searchTypeIdByType(String type)throws Exception, BaseException{
		String sql="select news_type_id from tb_news_column_type where type =:type";
		super.preparedSql(sql);
		super.setPreValue("type", type);
		return super.executeQuery(String.class);
	}
	
	public List<ShortNewsInfoVO> searchNewsByType(String type, int num) throws Exception, BaseException {
		String sql = "SELECT * FROM (select news_info_id,title,img_path,push_time,body_digest from TB_NEWS_INFO where news_info_type = :type and status=1 order by buy_top desc,push_time desc)where rownum<=:num";
		super.preparedSql(sql);
		super.setPreValue("type", type);
		super.setPreValue("num", num);
		return super.getList(ShortNewsInfoVO.class);
	}

	@Override
	public List<ShortNewsInfoVO> searchAqNewsIndexList(String parentId, int num) throws Exception, BaseException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM (select news_info_id, title, img_path, push_time, body_digest from TB_NEWS_INFO ")
				.append(" where news_info_type = (select tt.type from (select t.type from tb_news_column_type t ")
				.append(" where t.parent_id = :parentId order by t.order_value asc) tt where rownum <= 1)")
				.append(" and status = 1 order by buy_top desc, push_time desc) where rownum <= :num ");
		super.preparedSql(sql.toString());
		super.setPreValue("parentId", parentId);
		super.setPreValue("num", num);
		return super.getList(ShortNewsInfoVO.class);
	}

}
