package cn.com.do1.component.demoremote.code.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import java.util.Map;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public interface ICodeService extends IBaseService{

    Pager searchCode(Map searchMap,Pager pager) throws Exception, BaseException;

}
