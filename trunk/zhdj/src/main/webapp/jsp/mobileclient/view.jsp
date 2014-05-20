<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String baseURL = request.getContextPath();
    pageContext.setAttribute("baseURL", baseURL);
    pageContext.setAttribute("style", "default");
%>

<head>
<script>var baseURL = "${baseURL}";var dqdp_csrf_token = "${sessionScope.dqdp_csrf_token}";</script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预览</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="css/base.css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
</head>

<body>
	<div class="main" dataSrc="${baseURL}/interface/news/newsinfo!ajaxPreview.action?newsInfoId=${param.newsInfoId}&newsInfoType=${param.newsInfoType}">
		<p class="title mb10" name="perviewInfoVO.title"></p>
		<div class="overflow mb10">
			<div class="fl author"><em name="perviewInfoVO.source"></em></div>
			<div class="date fr"><em name="perviewInfoVO.pushTime"></em></div>
		</div>
		<p class="detail" id="detail">@{perviewInfoVO.content}</p>
	</div>
</form>
<script type="text/javascript">
  $(document).ready(function(){
	  $("#detail").css("display","");
  }
</body>
</html>
