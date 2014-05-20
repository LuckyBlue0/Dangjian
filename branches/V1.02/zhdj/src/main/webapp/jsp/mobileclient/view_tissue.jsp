<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../common/dqdp_common.jsp" %>
<jsp:include page="../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预览</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="css/base.css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
</head>

<body>
	<div class="main" dataSrc="${baseURL}/interface/news/newsinfo!ajaxPreview.action?newsInfoId=${param.newsInfoId}">
		<p class="title mb10" name="perviewInfoVO.title"></p>
		<div class="overflow mb10">
			<div class="fl author"><em name="perviewInfoVO.pushRole"></em></div>
			<div class="date fr"><em name="perviewInfoVO.pushTime"></em></div>
		</div>
		<p class="detail">@{perviewInfoVO.content}</p>
	</div>
</form>
</body>
</html>
