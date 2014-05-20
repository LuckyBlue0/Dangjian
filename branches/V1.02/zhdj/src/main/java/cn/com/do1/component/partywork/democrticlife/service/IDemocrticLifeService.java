package cn.com.do1.component.partywork.democrticlife.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.partywork.democrticlife.model.TbDemocraticLifePO;
import cn.com.do1.component.partywork.democrticlife.vo.DemocrticLifeVO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IDemocrticLifeService extends IBaseService{
    Pager searchDemocrticLife(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 新增或修改民主生活会信息
	 * @param tbDemocraticLifePO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> saveDemocrticLifeServiceInfo(TbDemocraticLifePO tbDemocraticLifePO, String userIds)throws Exception, BaseException;

	/**
	 * 保存民主生活会记录
	 * @param tbDemocraticLifePO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> saveDemocrticLifeRecordInfo(TbDemocraticLifePO tbDemocraticLifePO, String userIds)throws Exception, BaseException;

	/**
	 * 根据ID获取民主生活会记录
	 * @param id
	 * @return
	 */
	DemocrticLifeVO getDemocrticLifeById( String id)throws Exception, BaseException;

}
