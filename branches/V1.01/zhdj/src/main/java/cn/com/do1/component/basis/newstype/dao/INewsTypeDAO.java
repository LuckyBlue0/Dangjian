package cn.com.do1.component.basis.newstype.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.news.newsinfo.model.AqNewsListTabVO;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface INewsTypeDAO extends IBaseDAO {

	public Pager searchNewsType(Map searchValue, Pager pager) throws Exception, BaseException;

	/**
	 * @param orderValue
	 * @return
	 */
	public TbNewsColumnTypePO getNewsTypeByType(Integer orderValue) throws Exception;

	/**
	 * @return
	 */
	public List<TbNewsColumnTypePO> getNewsType()throws Exception;
	
	/**
	 * 根据父新闻类型Id获取子新闻类型列表
	 * @param parentId
	 * @return
	 */
	List<AqNewsListTabVO> searchAqNewsListTab(String parentId)throws SQLException;

	/**
	 * 根据父类型获取子新闻类型
	 * @param parentTypeName
	 * @return
	 */
	public List<TbNewsColumnTypePO> getChildrenTypesByParentTypeName(String parentTypeName)throws SQLException;
}
