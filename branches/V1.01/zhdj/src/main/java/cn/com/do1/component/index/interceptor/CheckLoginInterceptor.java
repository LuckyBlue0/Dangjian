package cn.com.do1.component.index.interceptor;

import java.util.Map;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.framebase.struts.BaseAction;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * @copyright 2012 广州市道一信息技术有限公司
 * @version 1.0
 * @date 创建时间：2013-10-30 下午04:20:19
 * 
 *       All rights reserved
 * 
 */
public class CheckLoginInterceptor extends BaseAction implements
		CheckLoginInterceptorInf {
	public static final String USER_KEY = "uservo";
	public static final String LOGIN_STATUS_KEY = "isMemberLogin";
	public static final String LOGIN_PAGE = "loginPage";

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "操作成功", faileMsg = "操作失败"))
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// 只拦截OrgIndexAction
		Map session = actionInvocation.getInvocationContext().getSession();
		if (session.get(LOGIN_STATUS_KEY) != null
				&& (Boolean) session.get(LOGIN_STATUS_KEY)) {
			// 已经登录的情况下进行后续操作。
			System.out.println("already login!");
			return actionInvocation.invoke();
		} else {
			// 否则终止后续操作，返回LOGIN
			System.out.println("no login, forward login page!");
			setActionResult("0", "查询成功");
			doJsonOut();
			return LOGIN_PAGE;
		}
	}

}
