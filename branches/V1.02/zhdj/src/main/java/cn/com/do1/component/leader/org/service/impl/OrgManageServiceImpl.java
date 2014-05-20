package cn.com.do1.component.leader.org.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.leader.org.dao.IOrgManageDAO;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.leader.org.service.IOrgManageService;
import cn.com.do1.component.systemmgr.org.model.TbDqdpOrgPO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("orgManageService")
public class OrgManageServiceImpl extends BaseService implements IOrgManageService {
    private final static transient Logger logger = LoggerFactory.getLogger(OrgManageServiceImpl.class);

    private IOrgManageDAO orgManageDAO;
    @Resource
    public void setOrgManageDAO(IOrgManageDAO orgManageDAO) {
        this.orgManageDAO = orgManageDAO;
        setDAO(orgManageDAO);
    }

    public Pager searchOrgManage(Map searchMap,Pager pager) throws Exception, BaseException {
        return orgManageDAO.searchOrgManage(searchMap,pager);
    }
    public List<TbOrganizationVO> listOrgRoot()throws SQLException{
    return this.orgManageDAO.listOrgRoot();
    }

    public boolean findChildOrg(String orgId)throws SQLException{
    	return this.orgManageDAO.findChildOrg(orgId);
    }

    public boolean findOrgMember(String orgId)throws SQLException{
    	return this.orgManageDAO.findOrgMember(orgId);
    }

    public List<TbPartyMenberInfoPO> searchLeaderByOrgId(String orgId)throws SQLException{
    	return this.orgManageDAO.searchLeaderByOrgId(orgId);
    }

    public List<TbPartyMenberInfoPO> searchEditorByOrgId(String orgId)throws SQLException{
    	return this.orgManageDAO.searchEditorByOrgId(orgId);
    }
    public void insertOrg(TbOrganizationPO tbOrganizationPO)throws BaseException, Exception{
    	tbOrganizationPO.setCreateDate(new Date());
    	tbOrganizationPO.setId(UUID.randomUUID().toString());
        TbDqdpOrgPO orgPo=new TbDqdpOrgPO();
        orgPo.setOrganizationId(tbOrganizationPO.getId());
        orgPo.setOrganizationName(tbOrganizationPO.getOrganizationName());
        orgPo.setLeftvalue(this.getLeftValue());
        orgPo.setParentId(tbOrganizationPO.getParentId());
        orgPo.setRightvalue(0);
        orgPo.setLayer(0);
        this.insertPO(orgPo, false);
        this.insertPO(tbOrganizationPO, false);
    }
    public void updateOrg(TbOrganizationPO tbOrganizationPO)throws BaseException, Exception{
    	tbOrganizationPO.setCreateDate(new Date());
        TbDqdpOrgPO orgPo=this.searchByPk(TbDqdpOrgPO.class, tbOrganizationPO.getId());
        orgPo.setOrganizationName(tbOrganizationPO.getOrganizationName());
        orgPo.setParentId(tbOrganizationPO.getParentId());
        this.updatePO(orgPo, false);
        this.updatePO(tbOrganizationPO, false);
    }
    public void deleteOrg(String[] ids)throws BaseException, Exception{
    	  if(!AssertUtil.isEmpty(ids)){
       	   for(String id:ids){
       		   TbOrganizationPO xxPO = this.searchByPk(TbOrganizationPO.class, id);
       		   TbDqdpOrgPO orgPO = this.searchByPk(TbDqdpOrgPO.class, id);
       		   boolean existOrg=this.findChildOrg(id);
       		   boolean existMember=this.findOrgMember(id);
       		   if(existOrg){
       			   throw new BaseException("机构："+xxPO.getOrganizationName()+"下存在子机构，删除失败！");
       		   }
       		   if(existMember){
       			   throw new BaseException("机构："+xxPO.getOrganizationName()+"下存有党员信息，删除失败！");
       		   }
       		   this.delPO(xxPO);
       		   this.delPO(orgPO);
       	   }
          }
    }
    public int getLeftValue() throws SQLException{
		Integer res = 0;

	    String sql = "select SEQ_ORG_NUM.nextval from dual";
		this.orgManageDAO.preparedSql(sql);
		res=this.orgManageDAO.executeQuery(Integer.class);
			
		return res;
			

    }
}
