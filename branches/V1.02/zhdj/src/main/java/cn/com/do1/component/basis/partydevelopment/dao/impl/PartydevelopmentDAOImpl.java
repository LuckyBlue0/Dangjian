package cn.com.do1.component.basis.partydevelopment.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.basis.partydevelopment.dao.IPartydevelopmentDAO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevApplyForVO;
import cn.com.do1.component.basis.partydevelopment.vo.PartyDevelopmentMenberVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class PartydevelopmentDAOImpl extends BaseDAOImpl implements
		IPartydevelopmentDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartydevelopmentDAOImpl.class);
	final static String condSQL = " from tb_party_dev_menber_info t left join tb_organization o on t.organization_id = o.id where t.state = 1 and t.organization_Identity != 7 and t.NAME like :name and t.Organization_identity = :organizationIdentity  and t.organization_id = :organizationId ";
	final static String countSQL = "select count(1) " + condSQL;
	final static String searchSQL = "select t.*,o.organization_name " + condSQL;

	@Override
	public List<TbOrganizationVO> getOrganizations() throws Exception {
		String sql = "select t.* from tb_organization t ";
		super.preparedSql(sql);
		return super.getList(TbOrganizationVO.class);
	}

	@Override
	public PartyDevelopmentMenberVO getPartyDevelopmentById(String id)
			throws Exception {
		String sql = "select t.*,o.organization_name from tb_party_dev_menber_info t left join tb_organization o on t.organization_id = o.id where t.ID=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(PartyDevelopmentMenberVO.class);
	}

	@Override
	public Pager searchPartyDevelopment(Map searchValue, Pager pager)
			throws Exception {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(PartyDevelopmentMenberVO.class,
				countSQL, searchSQL, searchValue, pager);
	}

	@Override
	public PartyDevApplyForVO getPartyDevApplyForLastByUserId(String userId)
			throws Exception {
		String sql = "select t.* from tb_party_dev_apply_for t where t.user_id = :id and status = 0 order by t.create_time desc ";
		super.preparedSql(sql);
		super.setPreValue("id", userId);
		List<PartyDevApplyForVO> list = super.getList(PartyDevApplyForVO.class);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PartyDevApplyForVO> getPartyDevApplyForPassByUserId(
			String userId) throws Exception {
		String sql = "select t.*,p.name audit_User_Name from tb_party_dev_apply_for t left join tb_party_menber_info p on p.id = t.audit_user_id where t.user_id = :id and status = 1 order by t.apply_for_org_identity asc,t.create_time desc ";
		super.preparedSql(sql);
		super.setPreValue("id", userId);
		return super.getList(PartyDevApplyForVO.class);
	}

}
