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
	<link type="text/css" rel="stylesheet" href="${baseURL}/jsp/web/css/base.css" />
	<link type="text/css" rel="stylesheet" href="${baseURL}/jsp/web/css/style.css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
</head>

<body>
<form method="post" id="id_form_search" dataSrc="${baseURL}/news/tissueMienInfo/tissueMienInfoAction!ajaxPreview.action?id=${param.id}">
    <div class="wrapper">
        <div class="article pt40">
            <h1 name="perviewInfoVO.title"></h1>
            <div class="tc art_detail mt5">
                <span class="mr20">发布时间：<em name="perviewInfoVO.pushTime"></em></span>
                <span>发布人：<em name="perviewInfoVO.pushRole"></em></span>
            </div>
            <div class="article-bd pt20">
          
                <p class="t2">@{perviewInfoVO.content}</p>
            </div>
            
         </div>
    </div>
<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</form>
</body>
</html>
