package cn.com.do1.component.leader.leaderinfo.dao.impl;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.leader.leaderinfo.dao.ILeaderinfoDAO;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderPO;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class LeaderinfoDAOImpl extends BaseDAOImpl implements ILeaderinfoDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(LeaderinfoDAOImpl.class);
    
    
    
    
    public Pager searchLeaderinfo(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select t.id,s.organization_name as organization_name,p.name as user_name,m.party_member_position_id as duty,m.status as status from tb_learder t left join tb_organization s on t.organization_id=s.id"+
                         " left join tb_party_menber_info p on t.user_id=p.id left join tb_party_member_position_ref m on t.id=m.leader_id where t.organization_id =:organaciation and p.name like :userName order by t.create_time desc";
    	String countSQL="select count(*) from tb_learder t left join tb_organization s on t.organization_id=s.id"+
                         " left join tb_party_menber_info p on t.user_id=p.id left join tb_party_member_position_ref m on t.id=m.leader_id where t.organization_id =:organaciation and p.name like :userName";
    	return super.pageSearchByField(TbLearderVO.class, countSQL, searchSQL, searchMap, pager);
    }
    public TbLearderVO searchLeaderById(String id)throws Exception, BaseException{
    	String searchSQL="select t.id,s.organization_name as organization_name,p.name as user_name,m.party_member_position_id as duty,m.status as status from tb_learder t left join tb_organization s on t.organization_id=s.id"+
                         " left join tb_party_menber_info p on t.user_id=p.id left join tb_party_member_position_ref m on t.id=m.leader_id where t.id =:id";
    	super.preparedSql(searchSQL);
    	super.setPreValue("id", id);
    	return super.executeQuery(TbLearderVO.class);
    }
    
}
