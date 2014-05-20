package cn.com.do1.component.basis.userinfo.service;

import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.component.basis.userinfo.vo.UserInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IUserinfoService extends IBaseService{
    Pager searchUserinfo(Map searchMap, Pager pager) throws Exception, BaseException;
    UserInfoVO getUserInfoById(String id) throws Exception, BaseException;
}
