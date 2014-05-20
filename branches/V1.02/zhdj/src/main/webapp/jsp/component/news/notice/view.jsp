<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预览</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
</head>

<body>
<form method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/news/newsinfo/newsInfoAction!ajaxView.action?newsInfoId=${param.newsInfoId}">
        <thead>
        </thead>
        <tbody>
        <tr>
            <td width="200" align="center">@{tbNewsInfoPO.title}</td>
        </tr>
        <tr>
            <td width="200" align="center">@{tbNewsInfoPO.content}</td>
        </tr>
        </tbody>
    </table>
<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</form>
</body>
</html>
