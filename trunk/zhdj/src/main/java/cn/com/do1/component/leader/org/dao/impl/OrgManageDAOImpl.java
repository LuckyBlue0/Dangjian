package cn.com.do1.component.leader.org.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.leader.org.dao.IOrgManageDAO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class OrgManageDAOImpl extends BaseDAOImpl implements IOrgManageDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(OrgManageDAOImpl.class);
    

    public Pager searchOrgManage(Map searchMap, Pager pager) throws Exception, BaseException{
    	String selectSQL="select * from tb_organization where organization_name like :organizationName and parent_id = :parentId  order by create_date asc";
    	String countSQL="select count(*) from tb_organization where organization_name like :organizationName and parent_id = :parentId ";
    	return super.pageSearchByField(TbOrganizationVO.class, countSQL, selectSQL, searchMap, pager);
    }
    
    public List<TbOrganizationVO> listOrgRoot()throws SQLException{
    	String sql="select * from tb_organization order by create_date asc";
        return super.getList(sql, TbOrganizationVO.class);
     }

    public boolean findChildOrg(String orgId)throws SQLException{
    	String sql="select count(*) from tb_organization where parent_id = :orgId";
    	super.preparedSql(sql);
    	super.setPreValue("orgId", orgId);
    	int count=super.executeQuery(Integer.class);
    	if(count>=1){
    		return true;
    	}else{
    		return false;
    	}
    }

    public boolean findOrgMember(String orgId)throws SQLException{
    	String sql="select count(*) from tb_party_menber_info where ORGANIZATION_ID = :orgId";
    	super.preparedSql(sql);
    	super.setPreValue("orgId", orgId);
    	int count=super.executeQuery(Integer.class);
    	if(count>=1){
    		return true;
    	}else{
    		return false;
    	}
    }
    public List<TbPartyMenberInfoPO> searchLeaderByOrgId(String orgId)throws SQLException{
    	String sql="select * from tb_party_menber_info where ORGANIZATION_ID = :orgId and POSITION =5";
    	super.preparedSql(sql);
    	super.setPreValue("orgId", orgId);
    	return getList(TbPartyMenberInfoPO.class);
    }

    public List<TbPartyMenberInfoPO> searchEditorByOrgId(String orgId)throws SQLException{
    	String sql="select * from tb_party_menber_info where ORGANIZATION_ID = :orgId and AFFAIRS_WORKER =1";
    	super.preparedSql(sql);
    	super.setPreValue("orgId", orgId);
    	return getList(TbPartyMenberInfoPO.class);
    	
    }
}   
