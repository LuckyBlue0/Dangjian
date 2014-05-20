package cn.com.do1.component.index.wap.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IWapService extends IBaseService{
    Pager searchWap(Map searchMap, Pager pager) throws Exception, BaseException;

}
