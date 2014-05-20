package cn.com.do1.component.leader.leaderinfo.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderPO;
import cn.com.do1.component.leader.leaderinfo.model.TbLearderVO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface ILeaderinfoService extends IBaseService{
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
     * 新增领导
     * @param po
     */
    public void addLeader(TbLearderVO po)throws Exception, BaseException;
    /**
     * 修改领导
     * @param po
     */
    public void editLeader(TbLearderVO po)throws Exception, BaseException;
    /**
     * 查看领导
     * @param po
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbLearderVO searchLeaderById(String id)throws Exception, BaseException;
    /**
     * 删除领导
     * @param po
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public void deleteLeader(String[] ids)throws Exception, BaseException;
    /**
     * 启用，禁用
     * @param po
     */
    public void editLeaderStatus(TbLearderVO po)throws Exception, BaseException;
}
