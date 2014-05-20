package cn.com.do1.component.news.tissuemieninfo.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.news.newsinfo.vo.NewsListInfoClientVO;
import cn.com.do1.component.news.tissuemieninfo.dao.ITissueMienInfoDAO;
import cn.com.do1.component.news.tissuemieninfo.vo.TissueMienInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class TissueMienInfoDAOImpl extends BaseDAOImpl implements ITissueMienInfoDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(TissueMienInfoDAOImpl.class);
	final static String condSQL = " from TB_TISSUE_MIEN t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.status<>0 and t.ORGANIZATION_ID = :organizationId and t.TITLE like :title and t.STATUS=:status order by t.BUY_TOP desc,t.PUSH_TIME desc,t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.title,t.id,t.type,t.status,t.buy_top,t.WHETHER_RECOMMEND,t.organization_id,t.create_user_id,t.create_time,t.push_time,t.img_path,o.ORGANIZATION_NAME  " + condSQL;
	
	final static String mobileClientCondSQL = " from TB_TISSUE_MIEN t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.status=1 and t.ORGANIZATION_ID = :organizationId order by t.BUY_TOP desc,t.PUSH_TIME desc";
	final static String mobileClientCountSQL = "select count(1)  " + mobileClientCondSQL;
	final static String mobileClientSearchSQL = "select t.title,t.id as news_info_id ,t.buy_top,t.status,t.push_time,t.img_path,o.ORGANIZATION_NAME  " + mobileClientCondSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
    	String mobileClient = (String) searchMap.get("mobileClient");
    	searchMap.remove("mobileClient");
    	if(AssertUtil.isEmpty(mobileClient)){
			logger.info(">>>>>>"+searchSQL);
			return super.pageSearchByField(TissueMienInfoVO.class, countSQL, searchSQL, searchMap, pager);
    	}else{
			logger.info(">>>>>>"+mobileClientSearchSQL);
			return super.pageSearchByField(NewsListInfoClientVO.class,  mobileClientCountSQL, mobileClientSearchSQL, searchMap, pager);
    	}
	}

	@Override
	public List<ShortNewsInfoVO> searchTop5TissueMienInfo() throws SQLException {
//		String sql = "select id news_info_id,title,img_path,push_time from TB_TISSUE_MIEN where rownum<=5 and status=1 order by buy_top desc,create_time desc";
		String sql = "SELECT * FROM (select id news_info_id,title,img_path,push_time from TB_TISSUE_MIEN where status=1 order by buy_top desc,push_time desc)where rownum<=5";
		super.preparedSql(sql);
		return super.getList(ShortNewsInfoVO.class);
	}
}
