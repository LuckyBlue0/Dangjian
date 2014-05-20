package cn.com.do1.component.index.index.dao.impl;

import java.util.List;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.index.index.dao.IOrgIndexDAO;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.partywork.volunteer.vo.VolunteerVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-23 上午11:37:16
 * 
 *       All rights reserved
 * 
 */
public class OrgIndexADOImpl extends BaseDAOImpl implements IOrgIndexDAO {

	@Override
	public List<ShortNewsInfoVO> getOrgAnnouncements(String newInfoType,
			String orgId) throws Exception, BaseException {
		String sql = "select news_info_id,title,img_path from tb_news_info where  news_info_type = :newInfoType and ORGANIZATION_ID = :orgId and rownum<=6 and status=1 order by buy_top desc,create_time desc";
		super.preparedSql(sql);
		super.setPreValue("newInfoType", newInfoType);
		super.setPreValue("orgId", orgId);
		return super.getList(ShortNewsInfoVO.class);
	}

	@Override
	public List<BranchActivityVO> getOrgDynamic(String orgId) throws Exception,
			BaseException {
		String sql = "select t.* from TB_BRANCH_ACTIVITY t where t.ORGANIZATION_ID=:orgId and t.WHETHER_END= 1 and rownum<=5 order by t.CREATE_TIME desc ";
		super.preparedSql(sql);
		super.setPreValue("orgId", orgId);
		return super.getList(BranchActivityVO.class);
	}

	@Override
	public List<OrganizationScoreVO> getOrgRanking() throws Exception,
			BaseException {
		String sql = "select s.*,o.organization_name from tb_organization_score s left join tb_organization o on o.id = s.organization_id where s.status = 1 and rownum <= 5 order by s.ranking asc ";
		super.preparedSql(sql);
		return super.getList(OrganizationScoreVO.class);
	}

	@Override
	public List<VolunteerVO> getVolunteer(String status, String orgId)
			throws Exception, BaseException {
		String sql = "select t.* from TB_VOLUNTEER_ACTIVITY t where t.status = :status and rownum<=5 and t.ORGANIZATION_ID = :orgId order by t.create_time desc ";
		super.preparedSql(sql);
		super.setPreValue("status", status);
		super.setPreValue("orgId", orgId);
		return super.getList(VolunteerVO.class);
	}

	@Override
	public List<ShortNewsInfoVO> searchTopPic(String orgId) throws Exception,
			BaseException {
		String sql = "select id news_info_id,title,img_path from tb_hot_news where img_path is not null and ORGANIZATION_ID = :orgId and  rownum<=5 and status=1 order by buy_top desc,create_time desc";
		super.preparedSql(sql);
		super.setPreValue("orgId", orgId);
		return super.getList(ShortNewsInfoVO.class);
	}

	@Override
	public BranchActivityVO searchNextDynamic(BranchActivityVO po)
			throws Exception, BaseException {
		String sql = "select * from TB_BRANCH_ACTIVITY where id=(select c.p from (select id,lag(id,1,0)  over (order by CREATE_TIME asc) as p from TB_BRANCH_ACTIVITY where WHETHER_END = :whetherEnd and ORGANIZATION_ID = :organizationId) c where c.id = :id) ";
		super.preparedSql(sql);
		super.setPreValue("whetherEnd", po.getWhetherEnd());
		super.setPreValue("organizationId", po.getOrganizationId());
		super.setPreValue("id", po.getId());
		return super.executeQuery(BranchActivityVO.class);
	}

	@Override
	public ShortNewsInfoVO searchNextNews(
			cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO news)
			throws Exception, BaseException {
		String sql = "select * from tb_news_info where news_info_id=(select c.p from (select news_info_id,lag(news_info_id,1,0)  over (order by buy_top asc,LAST_MODIFY_TIME asc,PUSH_TIME asc,create_time asc) as p from tb_news_info where news_info_type = :newsInfoType and ORGANIZATION_ID = :organizationId) c where c.news_info_id = :id)";
		super.preparedSql(sql);
		super.setPreValue("newsInfoType", news.getNewsInfoType());
		super.setPreValue("organizationId", news.getOrganizationId());
		super.setPreValue("id", news.getNewsInfoId());
		return super.executeQuery(ShortNewsInfoVO.class);
	}

	@Override
	public VolunteerVO searchNextVolunteer(VolunteerVO po) throws Exception,
			BaseException {
		String sql = "select * from TB_VOLUNTEER_ACTIVITY where id=(select c.p from (select id,lag(id,1,0)  over (order by CREATE_TIME asc) as p from TB_VOLUNTEER_ACTIVITY where status = :status and ORGANIZATION_ID = :organizationId) c where c.id = :id)";
		super.preparedSql(sql);
		super.setPreValue("status", po.getStatus());
		super.setPreValue("organizationId", po.getOraganizationId());
		super.setPreValue("id", po.getId());
		return super.executeQuery(VolunteerVO.class);
	}

	@Override
	public BranchActivityVO searchPartalDynamic(BranchActivityVO po)
			throws Exception, BaseException {
		String sql = "select * from TB_BRANCH_ACTIVITY where id=(select c.p from (select id,lag(id,1,0)  over (order by CREATE_TIME desc) as p from TB_BRANCH_ACTIVITY where WHETHER_END = :whetherEnd and ORGANIZATION_ID = :organizationId) c where c.id = :id) ";
		super.preparedSql(sql);
		super.setPreValue("whetherEnd", po.getWhetherEnd());
		super.setPreValue("organizationId", po.getOrganizationId());
		super.setPreValue("id", po.getId());
		return super.executeQuery(BranchActivityVO.class);
	}

	@Override
	public VolunteerVO searchPartalVolunteer(VolunteerVO po) throws Exception,
			BaseException {
		String sql = "select * from TB_VOLUNTEER_ACTIVITY where id=(select c.p from (select id,lag(id,1,0)  over (order by CREATE_TIME desc) as p from TB_VOLUNTEER_ACTIVITY where status = :status and ORGANIZATION_ID = :organizationId) c where c.id = :id)";
		super.preparedSql(sql);
		super.setPreValue("status", po.getStatus());
		super.setPreValue("organizationId", po.getOraganizationId());
		super.setPreValue("id", po.getId());
		return super.executeQuery(VolunteerVO.class);
	}

	@Override
	public ShortNewsInfoVO searchPartalNews(
			cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO news)
			throws Exception, BaseException {
		String sql = "select * from tb_news_info where news_info_id=(select c.p from (select news_info_id,lag(news_info_id,1,0)  over (order by buy_top desc,LAST_MODIFY_TIME desc,PUSH_TIME desc,create_time desc) as p from tb_news_info where news_info_type = :newsInfoType and ORGANIZATION_ID = :organizationId) c where c.news_info_id = :id) ";
		super.preparedSql(sql);
		super.setPreValue("newsInfoType", news.getNewsInfoType());
		super.setPreValue("organizationId", news.getOrganizationId());
		super.setPreValue("id", news.getNewsInfoId());
		return super.executeQuery(ShortNewsInfoVO.class);
	}

	public ShortNewsInfoVO searchPartalHotNews(TbHotNewsPO news)
			throws Exception, BaseException {
		String sql = "select id news_info_id,title from tb_hot_news where id=(select c.p from (select id,lag(id,1,0) over (order by buy_top asc,LAST_MODIFY_TIME asc,PUSH_TIME asc,create_time asc) as p from tb_hot_news where ORGANIZATION_ID = :orgId) c where c.id = :id)";
		super.preparedSql(sql);
		super.setPreValue("id", news.getId());
		super.setPreValue("orgId", news.getOrganizationId());
		return super.executeQuery(ShortNewsInfoVO.class);
	}

	/**
	 * 查询某个热点新闻下一条
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public ShortNewsInfoVO searchNextHotNews(TbHotNewsPO news)
			throws Exception, BaseException {
		String sql = "select id news_info_id,title from tb_hot_news where id=(select c.p from (select id,lag(id,1,0) over (order by buy_top desc,LAST_MODIFY_TIME desc,PUSH_TIME desc,create_time desc) as p from tb_hot_news where  ORGANIZATION_ID = :orgId) c where c.id = :id) ";
		super.preparedSql(sql);
		super.setPreValue("id", news.getId());
		super.setPreValue("orgId", news.getOrganizationId());
		return super.executeQuery(ShortNewsInfoVO.class);
	}
}
