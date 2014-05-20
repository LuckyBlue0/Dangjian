package cn.com.do1.component.mobileclient.version.dao;
import java.sql.SQLException;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.mobileclient.version.vo.VersionVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IVersionDAO extends IBaseDAO {
	/**
	 * 分页查询版本列表
	 * @param searchMap
	 * @param pager
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
    Pager searchVersion(Map searchMap, Pager pager) throws Exception, BaseException;

    VersionVO getLastNewVersion(Map searchMap)throws SQLException;
    
    /**
     * 下架所有历史版本
     * @throws Exception
     * @throws BaseException
     */
    void invalidVersion()throws Exception, BaseException;

}
