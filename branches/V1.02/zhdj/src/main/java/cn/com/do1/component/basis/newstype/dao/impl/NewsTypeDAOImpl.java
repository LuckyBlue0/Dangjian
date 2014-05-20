package cn.com.do1.component.basis.newstype.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.newstype.dao.INewsTypeDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.vo.NewsTypeVO;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class NewsTypeDAOImpl extends BaseDAOImpl implements INewsTypeDAO {
	private final static transient Logger logger = LoggerFactory.getLogger(NewsTypeDAOImpl.class);
	final static String condSQL = " from TB_NEWS_COLUMN_TYPE t left join TB_NEWS_COLUMN_TYPE t2 on t.parent_id=t2.news_type_id" +
			" where t.name like :name and t.parent_id=:parentId and t.CREATE_TYPE=:createType and t.USE_STATUS=:useStatus order by t.ORDER_VALUE asc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.*,t2.name parent_name  " + condSQL;

	@Override
	public Pager searchNewsType(Map searchValue, Pager pager) throws Exception, BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(NewsTypeVO.class, countSQL, searchSQL, searchValue, pager);
	}
	
	public List<TbNewsColumnTypePO> getNewsType()throws Exception{
		String sql = "select * from TB_NEWS_COLUMN_TYPE t where t.USE_STATUS=1 and t.CREATE_TYPE=1 order by t.ORDER_VALUE asc";
		super.preparedSql(sql);
		return super.getList(TbNewsColumnTypePO.class);
	}
	
	@Override
	public TbNewsColumnTypePO getNewsTypeByType(Integer orderValue) throws Exception{
		String sql = "select * from TB_NEWS_COLUMN_TYPE t where t.ORDER_VALUE=:orderValue";
		super.preparedSql(sql);
		super.setPreValue("orderValue", orderValue);
		return super.executeQuery(TbNewsColumnTypePO.class);
	}
	
	@Override
	public List<AqNewsListTabVO> searchAqNewsListTab(String parentId) throws SQLException {
		String sql = "select t.news_type_id news_info_type_id,t.name title from TB_NEWS_COLUMN_TYPE t where t.USE_STATUS=1 and t.PARENT_ID=:parentId order by t.ORDER_VALUE asc";
		super.preparedSql(sql);
		super.setPreValue("parentId", parentId);
		return super.getList(AqNewsListTabVO.class);
	}

	@Override
	public List<TbNewsColumnTypePO> getChildrenTypesByParentTypeName(String parentTypeName) throws SQLException {
		String sql = "select t1.* from tb_news_column_type t1 where t1.parent_id"+
					" in(select t2.news_type_id from tb_news_column_type t2 where t2.name=:name)"+
					" order by t1.order_value";
		super.preparedSql(sql);
		super.setPreValue("name", parentTypeName);
		return super.getList(TbNewsColumnTypePO.class);
	}

}
