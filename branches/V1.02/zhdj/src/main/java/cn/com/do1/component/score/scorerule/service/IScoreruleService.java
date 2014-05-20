package cn.com.do1.component.score.scorerule.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IScoreruleService extends IBaseService{
	/**
	 * 分页查询规则
	 * @param searchMap
	 * @param pager
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
    Pager searchScorerule(Map searchMap, Pager pager) throws Exception, BaseException;
    
    /**
     * 根据字典id查规则
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbScoreRulePO  searchByType(String itemid)throws Exception, BaseException;
    /**
     * 查找字典
     * @param id
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public String searchDictDesc(String id)throws Exception, BaseException;
}
