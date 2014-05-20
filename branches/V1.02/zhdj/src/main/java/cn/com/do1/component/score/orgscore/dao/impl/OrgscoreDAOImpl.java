package cn.com.do1.component.score.orgscore.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.score.orgscore.dao.IOrgscoreDAO;
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreInfoVO;
import cn.com.do1.component.score.orgscore.vo.OrganizationScoreVO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrgscoreDAOImpl extends BaseDAOImpl implements IOrgscoreDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrgscoreDAOImpl.class);
	final static String condSQL = " from tb_organization_score s left join tb_score_leave l on l.id = s.leave left join tb_organization o on s.organization_id = o.id where s.status = 1 and s.organization_id = :oraganization order by s.ranking asc ";
	final static String countSQL = "select count(1) " + condSQL;
	final static String searchSQL = "select s.*,o.organization_name,l.name leave_desc " + condSQL;

	final static String infoCondSQL = " from tb_organization_score_info t where T.ORGANIZATION_ID = :id and t.get_Time >= :startTime and t.get_Time <= :endTime order by t.get_time desc ";
	final static String infoCountSQL = "select count(1) " + infoCondSQL;
	final static String infoSearchSQL = "select t.* " + infoCondSQL;

	@Override
	public List<OrganizationScoreVO> getOrgScore(Map<?, ?> searchMap)
			throws Exception, BaseException {
		StringBuilder sb = new StringBuilder(
				"select s.*,o.organization_name,l.name leave_desc from tb_organization_score s left join tb_score_leave l on l.id = s.leave left join tb_organization o on s.organization_id = o.id where s.status = 1 ");
		if (null != searchMap.get("oraganization")) {
			sb.append(" and s.organization_id = :oraganization  ");
		}
		sb.append(" order by s.ranking asc ");
		super.preparedSql(sb.toString());
		if (null != searchMap.get("oraganization")) {
			super.setPreValue("oraganization", searchMap.get("oraganization"));
		}
		return super.getList(OrganizationScoreVO.class);
	}

	@Override
	public List<OrganizationScoreInfoVO> getOrgScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException {
		StringBuilder sb = new StringBuilder(
				"select t.* from tb_organization_score_info t where T.ORGANIZATION_ID = :id  ");
		if (null != searchMap.get("startTime")) {
			sb.append(" and t.get_Time >= :startTime ");
		}
		if (null != searchMap.get("endTime")) {
			sb.append(" and t.get_Time <= :endTime ");
		}
		sb.append(" order by t.get_time desc ");
		super.preparedSql(sb.toString());
		super.setPreValue("id", searchMap.get("id").toString());
		if (null != searchMap.get("startTime")) {
			super.setPreValue("startTime", searchMap.get("startTime"));
		}
		if (null != searchMap.get("endTime")) {
			super.setPreValue("endTime", searchMap.get("endTime"));
		}
		return super.getList(OrganizationScoreInfoVO.class);
	}

	@Override
	public TbPersonalScoreInfoPO getOrgScoreInfoByUserId(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrgscoreInfoPO(TbOrganizationScoreInfoPO po)
			throws Exception, BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public Pager searchOrgscore(Map searchMap, Pager pager) throws Exception,
			BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(OrganizationScoreVO.class, countSQL,
				searchSQL, searchMap, pager);
	}

	@Override
	public Pager searchOrgscoreInfo(Map searchMap, Pager pager)
			throws Exception, BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(OrganizationScoreInfoVO.class,
				infoCountSQL, infoSearchSQL, searchMap, pager);
	}

	@Override
	public void updateOrgscore() throws Exception, BaseException {
		// TODO Auto-generated method stub

	}

	public OrganizationScoreInfoVO getOrgScoreById(String id) throws Exception {
		String sql = "select * from tb_organization_score_info t where t.id=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(OrganizationScoreInfoVO.class);
	}

	@Override
	public OrganizationScoreVO getOrgScoreByOrgId(String orgId)
			throws Exception {
		String sql = "select t.*,o.organization_name from tb_organization_score t left join tb_organization o on o.id = t.organization_id where t.status = 1 and t.organization_id = :orgId ";
		super.preparedSql(sql);
		super.setPreValue("orgId", orgId);
		return super.executeQuery(OrganizationScoreVO.class);
	}

	@Override
	public Pager searchOrgScoreRank(Map searchValue, Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
