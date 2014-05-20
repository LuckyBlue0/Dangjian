package cn.com.do1.component.shyk.meeting.service;

import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.shyk.meeting.vo.DangkeVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IDangkeService extends IBaseService{
    Pager searchDangke(Map searchMap, Pager pager) throws Exception, BaseException;
	/**
	 * 根据Id获取党课信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
	DangkeVO getDangkeById(String id)throws Exception, BaseException;
}
