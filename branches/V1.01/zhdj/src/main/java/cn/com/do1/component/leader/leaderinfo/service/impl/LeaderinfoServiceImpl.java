package cn.com.do1.component.leader.leaderinfo.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.leader.leaderinfo.dao.*;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderPO;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderVO;
import cn.com.do1.component.leader.leaderinfo.model.TbPartyMemberPositionRefPO;
import cn.com.do1.component.leader.leaderinfo.service.ILeaderinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
@Service("leaderinfoService")
public class LeaderinfoServiceImpl extends BaseService implements ILeaderinfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(LeaderinfoServiceImpl.class);

    private ILeaderinfoDAO leaderinfoDAO;
    @Resource
    public void setLeaderinfoDAO(ILeaderinfoDAO leaderinfoDAO) {
        this.leaderinfoDAO = leaderinfoDAO;
        setDAO(leaderinfoDAO);
    }

    public Pager searchLeaderinfo(Map searchMap,Pager pager) throws Exception, BaseException {
        return leaderinfoDAO.searchLeaderinfo( searchMap,pager);
    }
    public void addLeader(TbLearderVO tbLearderVO)throws Exception, BaseException{
    	 TbLearderPO tbLearderPO=new TbLearderPO();
    	 tbLearderPO.setId(UUID.randomUUID().toString());
    	 tbLearderPO.setOrganizationId(tbLearderVO.getOrganizationId());
    	 tbLearderPO.setUserId(tbLearderVO.getUserId());
    	 tbLearderPO.setCreateTime(new Date());
	     TbPartyMemberPositionRefPO refPO=new TbPartyMemberPositionRefPO();
	     refPO.setUserId(tbLearderVO.getUserId());
	     refPO.setPartyMemberPositionId(tbLearderVO.getDuty());
	     refPO.setStatus(1l);
	     refPO.setLeaderId(tbLearderPO.getId());
	     this.insertPO(refPO, true);
	     this.insertPO(tbLearderPO, false);
    }
    public void editLeader(TbLearderVO tbLearderVO)throws Exception, BaseException{
    	TbPartyMemberPositionRefPO refPO=this.getPartyMemberPositionRefPO(tbLearderVO.getId());
    	refPO.setPartyMemberPositionId(tbLearderVO.getDuty());
    	this.updatePO(refPO,false);
   }
    private TbPartyMemberPositionRefPO getPartyMemberPositionRefPO(String id)throws Exception, BaseException{
    	String sql="select * from tb_party_member_position_ref where leader_id = :id" ;
    	this.leaderinfoDAO.preparedSql(sql);
    	this.leaderinfoDAO.setPreValue("id", id);
    	return this.leaderinfoDAO.executeQuery(TbPartyMemberPositionRefPO.class);
    }
    public TbLearderVO searchLeaderById(String id)throws Exception, BaseException{
    	return this.leaderinfoDAO.searchLeaderById(id);
    }
    public void deleteLeader(String[] ids)throws Exception, BaseException{
    	 if(!AssertUtil.isEmpty(ids)){
       	   for(String id:ids){
       		  TbLearderPO xxPO = this.searchByPk(TbLearderPO.class, id);
       		  this.delPO(xxPO);
       		TbPartyMemberPositionRefPO refPO=this.getPartyMemberPositionRefPO(id);
       		this.delPO(refPO);
       	   }
          }
    }
    public void editLeaderStatus(TbLearderVO tbLearderVO)throws Exception, BaseException{
    	TbPartyMemberPositionRefPO refPO=this.getPartyMemberPositionRefPO(tbLearderVO.getId());
    	refPO.setStatus(Long.valueOf(tbLearderVO.getStatus()));
    	this.updatePO(refPO,false);
   }
}
