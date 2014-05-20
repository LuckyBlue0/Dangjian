package cn.com.do1.component.report.access.dao.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.report.access.dao.IAccessDAO;
import cn.com.do1.component.report.access.model.AccessDataVO;
import cn.com.do1.component.report.access.model.PvReportVO;
import cn.com.do1.component.report.access.model.TbAccessInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class AccessDAOImpl extends BaseDAOImpl implements IAccessDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(AccessDAOImpl.class);
    public Pager searchAccess(Map searchMap,Pager pager) throws Exception, BaseException {
    	String searchSQL="select * from tb_access_info where user_id = :userid order by access_time desc ";
    	String countSQL="select count(*) from tb_access_info where user_id = :userid";
    	return super.pageSearchByField(TbAccessInfoVO.class, countSQL, searchSQL, searchMap, pager);
    }
    
    public AccessDataVO searchAccessData()throws Exception, BaseException{
    	String sql="select  (select count from view_access_today) as access_num,(select sum(count) count from view_access_all) as access_all," +
    			" (select count from view_login_today) as login_num, (select sum(count) count from view_login_all) as login_all from dual";
    	super.preparedSql(sql);
    	return super.executeQuery(AccessDataVO.class);
    }
    public List<PvReportVO> searchPvDataByDate(Date date)throws Exception, BaseException{
    	String sql="select count as user_cnt, to_char(access_time,'mm-dd') as show_date  from view_access_all where to_date(access_time) >= :date order by  access_time asc";
    	super.preparedSql(sql);
    	super.setPreValue("date", date);
    	return super.getList(PvReportVO.class);
    }
    public List<PvReportVO> searchPlDataByDate(Date date)throws Exception, BaseException{
    	String sql="select count as user_cnt, to_char(login_time,'mm-dd') as show_date  from view_login_all where to_date(login_time) >= :date order by  login_time asc";
    	super.preparedSql(sql);
    	super.setPreValue("date", date);
    	return super.getList(PvReportVO.class);
    }
    public TbAccessInfoVO searchAccessByUsername(String username,Date date)throws Exception, BaseException{
    	String sql="select * from tb_access_info where user_id = :username and access_time < :date order by access_time desc";
    	super.preparedSql(sql);
    	super.setPreValue("username", username);
    	super.setPreValue("date", date);
    	List<TbAccessInfoVO> list=super.getList(TbAccessInfoVO.class);
    	if(list.size()>=1){
    	    return list.get(0);
    	}else{
    		return null;
    	}
    }
}
