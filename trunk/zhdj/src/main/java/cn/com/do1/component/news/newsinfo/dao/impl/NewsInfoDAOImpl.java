package cn.com.do1.component.news.newsinfo.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.news.newsinfo.dao.INewsInfoDAO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.vo.NewsInfoVO;
import cn.com.do1.component.news.newsinfo.vo.NewsListInfoClientVO;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class NewsInfoDAOImpl extends BaseDAOImpl implements INewsInfoDAO{
	private final static transient Logger logger = LoggerFactory.getLogger(NewsInfoDAOImpl.class);
	
	final static String condSQL = " from TB_NEWS_INFO t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.ORGANIZATION_ID = :organizationId and t.NEWS_INFO_TYPE=:newsInfoType and t.TITLE like :title and t.STATUS=:status order by t.BUY_TOP desc,t.LAST_MODIFY_TIME desc,t.PUSH_TIME desc,t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.title,t.news_info_id,t.status,t.solid_top,t.buy_top,t.importance,t.news_info_type,t.organization_id,t.create_user_id,t.create_time,t.last_modify_user_id,t.last_modify_time,t.push_time,t.img_path,t.project_type,t.body_digest,t.tab_bar,t.source,o.ORGANIZATION_NAME  " + condSQL;
	
	
	final static String mobileClientCondSQL = " from TB_NEWS_INFO t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.status=1 and t.ORGANIZATION_ID = :organizationId and t.NEWS_INFO_TYPE=:newsInfoType order by t.BUY_TOP desc,t.PUSH_TIME desc";
	final static String mobileClientCountSQL = "select count(1)  " + mobileClientCondSQL;
	final static String mobileClientSearchSQL = "select t.title,t.news_info_id,t.status,t.solid_top,t.buy_top,t.importance,t.news_info_type,t.organization_id,t.create_user_id,t.create_time,t.last_modify_user_id,t.last_modify_time,t.push_time,t.img_path,t.project_type,t.body_digest,t.tab_bar,t.source,o.ORGANIZATION_NAME  " + mobileClientCondSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
    	String mobileClient = (String) searchMap.get("mobileClient");
    	searchMap.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
    		logger.info(">>>>>>"+searchSQL);
    		return super.pageSearchByField(NewsInfoVO.class, countSQL, searchSQL, searchMap, pager);
    	}else{
    		logger.info(">>>>>>"+mobileClientSearchSQL);
    		return super.pageSearchByField(NewsListInfoClientVO.class, mobileClientCountSQL, mobileClientSearchSQL, searchMap, pager);
    	}
	}

	@Override
	public TbNewsInfoPO getNewsInfoByType(String type) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from TB_NEWS_INFO t where t.NEWS_INFO_TYPE=:type";
		this.preparedSql(sql);
		this.setPreValue("type", type);
		return super.executeQuery(TbNewsInfoPO.class);
	}

	@Override
	public NewsPreviewInfoVO getNewsPreviewInfo(String newsInfoId, int type) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "";
		switch(type){
			case 0://热点新闻
				sql  = "select t.*,m.name pushUser,o.ORGANIZATION_NAME pushOrganizationName,o.ORGANIZATION_NAME pushRole from TB_HOT_NEWS t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on m.id=t.PUSH_USER_ID where t.id=:id";
				break;
			case 1:
			case 2://普通新闻
				sql  = "select t.*,m.name pushUser,o.ORGANIZATION_NAME pushOrganizationName,o.ORGANIZATION_NAME pushRole from TB_NEWS_INFO t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on m.id=t.PUSH_USER_ID where t.news_info_id=:id";
				break;
			case 3://组织风采新闻
				sql  = "select t.*,m.name pushUser,o.ORGANIZATION_NAME pushOrganizationName,o.ORGANIZATION_NAME pushRole from TB_TISSUE_MIEN t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on m.id=t.PUSH_USER_ID where t.id=:id";
				break;
			default:
//				throw new SQLException("type不在范围内!");
				sql  = "select t.*,m.name pushUser,o.ORGANIZATION_NAME pushOrganizationName,o.ORGANIZATION_NAME pushRole from TB_NEWS_INFO t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id left join TB_PARTY_MENBER_INFO m on m.id=t.PUSH_USER_ID where t.news_info_id=:id";
		}
		this.preparedSql(sql);
		this.setPreValue("id", newsInfoId);
		return super.executeQuery(NewsPreviewInfoVO.class);
	}

	final static String mobileAqClientCondSQL = " from TB_NEWS_INFO t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.status=1 and t.news_info_type_id=:newsInfoTypeId and t.ORGANIZATION_ID = :organizationId and t.NEWS_INFO_TYPE=:newsInfoType order by t.BUY_TOP desc,t.PUSH_TIME desc";
	final static String mobileAqClientCountSQL = "select count(1)  " + mobileAqClientCondSQL;
	final static String mobileAqClientSearchSQL = "select t.title,t.news_info_id,t.status,t.solid_top,t.buy_top,t.importance,t.news_info_type,t.organization_id,t.create_user_id,t.create_time,t.last_modify_user_id,t.last_modify_time,t.push_time,t.img_path,t.project_type,t.body_digest,t.tab_bar,t.source,o.ORGANIZATION_NAME  " + mobileAqClientCondSQL;

	@Override
	public Pager searchAqNewsinfo(Map searchValue, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
		return super.pageSearchByField(NewsListInfoClientVO.class, mobileAqClientCountSQL, mobileAqClientSearchSQL, searchValue, pager);
	}

}
