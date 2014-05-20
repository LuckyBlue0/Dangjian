package cn.com.do1.component.score.personalscore.dao.impl;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.score.personalscore.dao.IPersonalscoreDAO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreInfoVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PersonalscoreDAOImpl extends BaseDAOImpl implements
		IPersonalscoreDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PersonalscoreDAOImpl.class);
	final static String condSQL = " from tb_personal_score t left join tb_score_leave l on l.id = t.leave left join tb_party_menber_info m on m.id = t.user_id left join tb_organization o on o.id = t.organization_id where t.status = 1 and m.name like :name and t.organization_id = :organizationId order by t.ranking asc ";
	final static String countSQL = "select count(1) " + condSQL;
	final static String searchSQL = "select t.*,l.name leave_Desc,m.name,o.organization_name " + condSQL;

	final static String infoCondSQL = " from tb_personal_score_info t where t.user_id = :id and t.get_Time >= :startTime and t.get_Time <= :endTime order by t.get_time desc ";
	final static String infoCountSQL = "select count(1) " + infoCondSQL;
	final static String infoSearchSQL = "select t.* " + infoCondSQL;

	@Override
	public TbPersonalScoreInfoPO getPersonalScoreInfoByUserId(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePersonalscorePO(TbPersonalScoreInfoPO po) throws Exception,
			BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public Pager searchPersonalscore(Map searchMap, Pager pager)
	throws Exception, BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		String condSQL = " from tb_party_menber_info m,tb_personal_score s left join tb_score_leave l on l.id = s.leave left join tb_organization o on s.organization_id = o.id where s.status = 1 and m.id = s.user_id and m.name like :name and s.organization_id = :oraganization and m.id= :userid  ";
		String countSQL = " select count(1) " + condSQL;
		String searchSQL = " select s.*,l.name leave_Desc,m.name,o.organization_name " + condSQL;
		if (!AssertUtil.isEmpty(searchMap.get("type"))) {
			String type = (String) searchMap.get("type");
			if ("1".equals(type)) {
				searchSQL += " order by s.ranking asc,s.id";
			} else if ("2".equals(type)) {
				searchSQL += " order by s.ranking desc,s.id desc";
			} else if ("3".equals(type)) {
		    	String mobileClient = (String) searchMap.get("mobileClient");
		    	StringBuilder sbSearchSQL = new StringBuilder();
		    	sbSearchSQL.append(" select * from ( select * from ( select s.*,m.name,l.name leave_Desc   from tb_party_menber_info m,tb_personal_score s")
				    .append(" left join tb_score_leave l on l.id = s.leave")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1")
					.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) t where t.ranking<")
					.append(" (select num from (select m.id as mid, s.ranking as num from tb_party_menber_info m,tb_personal_score s ")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1")
					.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)")
					.append(" and t.ranking >(select num from (select m.id as mid, s.ranking as num from tb_party_menber_info m,tb_personal_score s ")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1");
					
					if (AssertUtil.isEmpty(mobileClient)) {
						sbSearchSQL.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)-5");
					} else {
						sbSearchSQL.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)-20");
					}
					sbSearchSQL.append(" union ");

					sbSearchSQL.append(" select * from ( select s.*,m.name,l.name leave_Desc   from tb_party_menber_info m,tb_personal_score s ")
					.append(" left join tb_score_leave l on l.id = s.leave")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1")
					.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) p where ")
					.append(" p.ranking>=(select num from (select m.id as mid, s.ranking as num from tb_party_menber_info m,tb_personal_score s ")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1")
					.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)")
					.append(" and p.ranking <(select num from (select m.id as mid, s.ranking as num from tb_party_menber_info m,tb_personal_score s ")
					.append(" left join tb_organization o on s.organization_id = o.id where s.status = 1");
					if (AssertUtil.isEmpty(mobileClient)) {
						sbSearchSQL.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)+5) q");
					} else {
						sbSearchSQL.append(" and m.id = s.user_id and o.id=:oraganization order by s.ranking asc) where mid =:userid)+20) q");
					}
					sbSearchSQL.append(" order by q.ranking asc");
					
					searchSQL = sbSearchSQL.toString();
					sbSearchSQL = null;
			}

		} else {
			searchSQL += " order by s.ranking asc,s.id";
		}
		searchMap.remove("type");
		searchMap.remove("mobileClient");
		return super.pageSearchByField(PersonalScoreVO.class, countSQL, searchSQL, searchMap, pager);
	}

	@Override
	public Pager searchPersonalscoreInfo(Map searchMap, Pager pager)
			throws Exception, BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(PersonalScoreInfoVO.class, infoCountSQL,
				infoSearchSQL, searchMap, pager);
	}

	@Override
	public void updatePersonalscore() throws Exception, BaseException {
		// TODO Auto-generated method stub

	}

	public PersonalScoreInfoVO getPersonalScoreInfoByid(String id)
			throws Exception {
		String sql = "select * from tb_personal_score_info t where t.id=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(PersonalScoreInfoVO.class);
	}

	public PersonalScoreVO getPersonalScoreByUserId(String userId)
			throws Exception {
		String sql = "select * from tb_personal_score t left join tb_party_menber_info m on t.user_id = m.id where t.status=1 and t.user_id = :userId";
		super.preparedSql(sql);
		super.setPreValue("userId", userId);
		return super.executeQuery(PersonalScoreVO.class);
	}

	public List<PersonalScoreVO> getPersonalScore(Map searchMap)
			throws Exception, BaseException {
		StringBuilder sb = new StringBuilder(
				"select s.*,m.name,o.organization_name,l.name leave_Desc  from tb_party_menber_info m,tb_personal_score s left join tb_score_leave l on l.id = s.leave left join tb_organization o on s.organization_id = o.id where s.status = 1 and m.id = s.user_id ");
		if (null != searchMap.get("name")) {
			sb.append(" and m.name like :name ");
		}
		if (null != searchMap.get("oraganization")) {
			sb.append(" and s.organization_id = :oraganization ");
		}
		sb.append(" order by s.ranking asc ");
		super.preparedSql(sb.toString());
		if (null != searchMap.get("name")) {
			super.setPreValue("name", "%"
					+ URLDecoder.decode(searchMap.get("name").toString(),
							"UTF-8") + "%");
		}
		if (null != searchMap.get("oraganization")) {
			super.setPreValue("oraganization", searchMap.get("oraganization"));
		}
		return super.getList(PersonalScoreVO.class);
	}

	public List<PersonalScoreInfoVO> getPersonalScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException {
		StringBuilder sb = new StringBuilder(
				"select t.* from tb_personal_score_info t where t.user_id = :id ");
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
		return super.getList(PersonalScoreInfoVO.class);
	}
}
