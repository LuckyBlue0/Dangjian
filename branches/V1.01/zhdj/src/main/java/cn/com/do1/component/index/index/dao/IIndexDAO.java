package cn.com.do1.component.index.index.dao;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.index.index.model.LoginUserVO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IIndexDAO extends IBaseDAO {
	/**
     * 首页图片展示
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<ShortNewsInfoVO> searchTopPic()throws Exception, BaseException;
    /**
     * 首页新闻展示展示前五条
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<ShortNewsInfoVO> ajaxSearchNews(String newInfoType)throws Exception, BaseException;
    /**
     * 查询前五种显示在前台的新闻类型
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbNewsColumnTypePO searchNewsType(String typeName)throws Exception, BaseException;
    /**
     * 前台登录
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public LoginUserVO loginUser(LoginUserVO vo,String type)throws Exception, BaseException;
    /**
     * 查询新闻类型id
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbNewsColumnTypePO searchNewsTypeId(String name)throws Exception, BaseException;
    /**
     * 分页查询新闻
     * @param searchMap
     * @param pager
     * @return
     * @throws Exception
     * @throws BaseException
     */
    Pager ajaxSearch(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 判断用户名是否存在
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public boolean isUserNameExist(String username)throws Exception, BaseException;
    /**
     * 查询某个新闻前一条
     * @param id
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public ShortNewsInfoVO searchPartalNews(String id)throws Exception, BaseException;
    /**
     * 查询某个新闻下一条
     * @param id
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public ShortNewsInfoVO searchNextNews(String id)throws Exception, BaseException;
    /**
	 * 检查用户今天是否登陆加分过
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public boolean checkLonginScoreToday(String userId, String scoreType) throws Exception, BaseException;
	/**
	 * 根据新闻的type查询新闻类型的ID
	 * @param type
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	public String searchTypeIdByType(String type)throws Exception, BaseException;
	
	/**
	 * 获取前5条热点新闻
	 * @return
	 */
	public List<ShortNewsInfoVO> searchTopHotNews()throws Exception, BaseException;
	
	/**
	 * @param type
	 * @param num
	 * @return
	 */
	public List<ShortNewsInfoVO> searchNewsByType(String type,int num)throws Exception, BaseException;
	
	/**
	 * 根据一级新闻菜单Id获取第一个二级菜单的num条新闻
	 * @param parentId
	 * @param num
	 * @return
	 */
	public List<ShortNewsInfoVO> searchAqNewsIndexList(String parentId, int num)throws Exception, BaseException;
}
