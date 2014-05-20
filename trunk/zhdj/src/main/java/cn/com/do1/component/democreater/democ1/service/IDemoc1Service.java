package cn.com.do1.component.democreater.democ1.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IDemoc1Service extends IBaseService{
    Pager searchDemoc1(Map searchMap, Pager pager) throws Exception, BaseException;

}
