<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String baseURL = request.getContextPath();
    String jsVer = "2.09.01";
    pageContext.setAttribute("baseURL", baseURL);
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<title>
</title>
<link rel="stylesheet" type="text/css" href="${baseURL}/qnh/postInfo/style/common.css" />
<script>var baseURL = "${baseURL}";var dqdp_csrf_token = "${sessionScope.dqdp_csrf_token}";</script>
</head>

<body >
	
	<div class="main">
	<p class="title tie_title mb10"><s:property value="tbPostInfoPO.context"/></p>
	
	<s:if test="tbReplyVOs.size > 0 ">
	<s:iterator value="tbReplyVOs" status="tbStu" >
		<div class="list">
			<table style="width: 100%;">
			<tr><td style="width: 10%;">
			<div class="overflow pb10">
				<div class="img_bg fl" style="display: inline; float: left;">
					<img src="${baseURL}/qnh/postInfo/images/user-head.png" alt="" />
					<p class="name"><s:property value="userName"/></p>
				</div>
				</td><td style="width: 90%;">
				<div class="dialog" style="display: inline-block; width: 80%;padding-left: 20px;">
					<div class="dialog_top" style="top: 18%;height: 27px;width: 27px;"></div>
					<div class="dialog_content" style="margin-left: 17px">
						<p class="detail"><s:property value="context"/></p>
						<p style="text-align:right">
						<span class=" floor"><s:date name="createTime" format="yyyy-MM-dd"/></span>
						<span class=" floor"><s:if test="onNum == 1 ">【楼主】</s:if><s:else>【<s:property value="onNum"/>楼】</s:else></span>
						</p>
					</div>
					
				</div>
				</div>
				</td></tr>
			</table>
		</div> 
	</s:iterator>
	</s:if>
		
		
		
	</div>
   
</html>
