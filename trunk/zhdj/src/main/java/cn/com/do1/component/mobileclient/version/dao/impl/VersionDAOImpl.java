package cn.com.do1.component.mobileclient.version.dao.impl;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.version.dao.IVersionDAO;
import cn.com.do1.component.mobileclient.version.model.TbVersionPO;
import cn.com.do1.component.mobileclient.version.vo.VersionVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class VersionDAOImpl extends BaseDAOImpl implements IVersionDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(VersionDAOImpl.class);
	
    public Pager searchVersion(Map searchMap, Pager pager) throws Exception, BaseException{
    	String searchSQL="select * from tb_version where VERSION_NAME like :versionName and type = :type and status =:status order by push_time desc ";
    	String countSQL="select count(*) from tb_version where VERSION_NAME like :versionName and type = :type and status =:status";
    	return super.pageSearchByField(TbVersionPO.class, countSQL, searchSQL, searchMap, pager);
    }

	@Override
	public VersionVO getLastNewVersion(Map searchMap) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select t.* from tb_version t where t.status=1 and t.type=:type order by t.push_time desc";
		this.preparedSql(sql);
		this.setPreValue("type",searchMap.get("type"));
		return super.executeQuery(VersionVO.class);
	}
	
	/**
     * 下架所有历史版本
     * @throws Exception
     * @throws BaseException
     */
    public void invalidVersion()throws Exception, BaseException{
    	String sql = "update tb_version t set t.status = 2 where t.status = 1 ";
    	this.preparedSql(sql);
    	super.executeUpdate();
    }
}
