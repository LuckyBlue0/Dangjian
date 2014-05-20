package cn.com.do1.component.report.access.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.report.access.model.AccessDataVO;
import cn.com.do1.component.report.access.model.PvReportVO;
import cn.com.do1.component.report.access.model.TbAccessInfoVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IAccessService extends IBaseService{
	/**
	 * 分页查询用户访问信息
	 * @param searchMap
	 * @param pager
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
    Pager searchAccess(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 查询访问和登录信息
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public AccessDataVO searchAccessData()throws Exception, BaseException;
    /**
     * 查询近半月的用户访问数
     * @param date
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<PvReportVO> searchPvDataByDate(Date date)throws Exception, BaseException;
    /**
     * 查询近半月的用户登录数
     * @param date
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public List<PvReportVO> searchPlDataByDate(Date date)throws Exception, BaseException;
    /**
     * 查询用户访问记录
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public TbAccessInfoVO searchAccessByUsername(String username,Date date)throws Exception, BaseException;
}
