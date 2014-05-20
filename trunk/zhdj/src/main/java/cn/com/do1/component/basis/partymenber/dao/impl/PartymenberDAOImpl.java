package cn.com.do1.component.basis.partymenber.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.partymenber.dao.IPartymenberDAO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartymenberDAOImpl extends BaseDAOImpl implements IPartymenberDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartymenberDAOImpl.class);
	final static String condSQL = " from TB_PARTY_MENBER_INFO t left join tb_organization o on t.ORGANIZATION_ID = o.id where t.state =:state " +
									"and t.USER_NAME like :userName and t.NAME like :name and t.ID_CARD like :idCard " +
									"and t.AFFAIRS_WORKER = :affairsWorker and t.ORGANIZATION_ID = :organizationId " +
									"and t.user_type = :userType and t.IS_MANAGEMENT = :isManagement order by t.user_type asc, t.id asc";
	final static String countSQL = "select count(1) " + condSQL;
	final static String searchSQL = "select t.*,o.organization_name " + condSQL;

	@Override
	public Pager searchPartyMenber(Map searchValue, Pager pager)
			throws Exception {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(PartyMenberInfoVO.class, countSQL,
				searchSQL, searchValue, pager);
	}

	@Override
	public Pager searchPartyRelation(Map searchValue, Pager pager)
			throws Exception {
		String relationCondSQL = " from TB_PARTY_MENBER_INFO t left join tb_organization o on t.ORGANIZATION_ID = o.id where t.state in(2,0) and t.NAME like :name and t.state = :state ";
		String relationCountSQL = "select count(1) " + relationCondSQL;
		String relationSearchSQL = "select t.*,o.organization_name " + relationCondSQL;
		return super.pageSearchByField(PartyMenberInfoVO.class, relationCountSQL,
				relationSearchSQL, searchValue, pager);
	}

	@Override
	public TbPartyMenberInfoPO getPartyMenberByUserName(String userName)
			throws Exception {
		String sql = "select * from TB_PARTY_MENBER_INFO t where t.USER_NAME=:userName";
		super.preparedSql(sql);
		super.setPreValue("userName", userName);
		return super.executeQuery(TbPartyMenberInfoPO.class);
	}

	@Override
	public PartyMenberInfoVO getPartyMenberById(String id) throws Exception {
		String sql = "select t.*,o.organization_name from TB_PARTY_MENBER_INFO t left join tb_organization o on t.ORGANIZATION_ID = o.id where t.ID=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(PartyMenberInfoVO.class);
	}

	@Override
	public List<TbOrganizationVO> getOrganizations() throws Exception {
		String sql = "select t.* from tb_organization t ";
		super.preparedSql(sql);
		return super.getList(TbOrganizationVO.class);
	}
}
