package cn.com.do1.component.basis.newstype.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;

import java.util.List;
import java.util.Map;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface INewsTypeService extends IBaseService{
    Pager searchNewsType(Map searchMap, Pager pager) throws Exception, BaseException;

    /**
     * 获取所有新闻类型
     * @return
     * @throws Exception
     */
    List<TbNewsColumnTypePO> getNewsType()throws Exception;
	/**
	 * 新增新闻类型对象
	 * @param tbNewColumnTypePO
	 */
	void saveNewsTypePO(TbNewsColumnTypePO tbNewsColumnTypePO)throws Exception;
	
	/**
	 * 根据新闻类型查询对象
	 * @param type
	 * @return
	 */
	TbNewsColumnTypePO getNewsTypeByType(String type)throws Exception;
	
	/**
	 * 根据父新闻类型Id获取子新闻类型列表(安庆)
	 * @param parentId
	 * @return
	 */
	List<AqNewsListTabVO> searchAqNewsListTab(String parentId)throws Exception,BaseException;


}
