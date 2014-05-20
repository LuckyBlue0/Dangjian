package cn.com.do1.component.mobileclient.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.index.interceptor.CheckLoginInterceptorInf;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class MobileClientCheckLoginInterceptor extends BaseAction implements CheckLoginInterceptorInf{
	private final static transient Logger logger = LoggerFactory.getLogger(MobileClientCheckLoginInterceptor.class);
	public static final String LOGIN_STATUS = "mobileMemberLogin";
	public static List<String> NO_CHECK_URIS = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		NO_CHECK_URIS = new ArrayList<String>();
		NO_CHECK_URIS.add("/interface/userinfo/userinfo!login.action");
	}

	/**
	 * 目前手机端此功能可暂时忽略
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// TODO Auto-generated method stub
		String uri = ServletActionContext.getRequest().getRequestURI();
		uri = uri.substring(ServletActionContext.getRequest().getContextPath().length());
		Map session = invocation.getInvocationContext().getSession();
		if(NO_CHECK_URIS.contains(uri)){
			//过滤不需要判断是否已登录的接口
			return invocation.invoke();
		}
		
		if (session.get(LOGIN_STATUS) != null && (Boolean) session.get(LOGIN_STATUS)) {
			// 已经登录的情况下进行后续操作。
			System.out.println("already login!");
			return invocation.invoke();
		} else {
			System.out.println("no login, forward login page!");
			setActionResult("0", "请先进行登录操作,谢谢!");
			doJsonOut();
		}
		return null;
	}
}
