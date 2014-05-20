package cn.com.do1.component.index.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-30 下午04:17:40
 * 
 *       All rights reserved
 * 
 */
public interface CheckLoginInterceptorInf extends Interceptor {

	void destroy();

	void init();

	String intercept(ActionInvocation invocation) throws Exception;

}
