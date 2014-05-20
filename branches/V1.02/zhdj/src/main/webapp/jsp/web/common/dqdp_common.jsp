<%@ page import="cn.com.do1.dqdp.core.DqdpAppContext" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String baseURL = request.getContextPath();
    String jsVer = "2.09.01";
    pageContext.setAttribute("baseURL", baseURL);
    pageContext.setAttribute("style", "default");
%>
<script>var baseURL = "${baseURL}";var dqdp_csrf_token = "${sessionScope.dqdp_csrf_token}";</script>
<%--<script src="${baseURL}/JavaScriptServlet"></script>--%>
<div id="id_dqdp_tip" style="position: absolute;"></div>
