package cn.com.do1.component.relation.organization.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.relation.organization.dao.IOrganizationDAO;
import cn.com.do1.component.relation.organization.vo.OrganizationTransferVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class OrganizationDAOImpl extends BaseDAOImpl implements
		IOrganizationDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(OrganizationDAOImpl.class);
	@Override
	public OrganizationTransferVO getOrganizationTransferById(String id)
			throws Exception {
		String sql = "select o.organization_name organization_Name_From,o2.organization_name organization_Name_TO,o3.organization_name organization_Name_INTO,t.*  from TB_ORGANIZATION_TRANSFER t left join tb_organization o on o.id = t.organization_from left join tb_organization o2 on o2.id = t.organization_to left join tb_organization o3 on o3.id = t.organization_into where t.ID=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(OrganizationTransferVO.class);
	}

	@Override
	public Pager searchOrganization(Map searchMap, Pager pager)
			throws Exception, BaseException {
		String condSQL = " from TB_ORGANIZATION_TRANSFER t left join tb_organization o on o.id = t.organization_from left join tb_organization o2 on o2.id = t.organization_to left join tb_organization o3 on o3.id = t.organization_into where t.type = :type and t.user_Name like :userName order by t.status asc ";
		String countSQL = "select count(1) " + condSQL;
		String searchSQL = "select o.organization_name organization_Name_From,o2.organization_name organization_Name_TO,o3.organization_name organization_Name_INTO,t.* " + condSQL;
		return super.pageSearchByField(OrganizationTransferVO.class, countSQL,
				searchSQL, searchMap, pager);
	}

}
