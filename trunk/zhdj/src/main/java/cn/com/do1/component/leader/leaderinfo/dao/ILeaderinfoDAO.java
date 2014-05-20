package cn.com.do1.component.leader.leaderinfo.dao;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface ILeaderinfoDAO extends IBaseDAO {
	/**
	 * 分页查询领导表
	 * @param searchMap
	 * @param pager
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
    Pager searchLeaderinfo(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查看领导
     * @param po
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbLearderVO searchLeaderById(String id)throws Exception, BaseException;
}
