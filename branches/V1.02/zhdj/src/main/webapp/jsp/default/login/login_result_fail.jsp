<%@ page import="cn.com.do1.common.framebase.struts.BaseAction" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    String ajaxHeader = request.getHeader("X-Requested-With");

    if (ajaxHeader != null && ajaxHeader.contains("XMLHttpRequest")) {
        BaseAction action = new BaseAction();
        action.setActionResult("800000002", "登陆失败");
        action.addJsonObj("userKey", session.getAttribute("_dqdp_session_key"));
        action.doJsonOut();
    } else {
        request.getRequestDispatcher("/jsp/default/login/login_in.jsp?login_error=1").forward(request, response);
    }
%>