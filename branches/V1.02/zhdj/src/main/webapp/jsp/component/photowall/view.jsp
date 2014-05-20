<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看评论列表</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body style="background: #f6ebe5;">
<form action="" method="post" id="id_form_search">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">查看留影墙详情及评论列表</h2>
            </div>
    </div>
    <div class="text_bg" id="bt">
        <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
		<tr>
			<td colspan="2">
				<div class="topTitle mb20">
					<span class="topName">留影墙基本信息</span>
				</div>
			</td>
		</tr>
        <tr>
            <td class="tdBlue">标题</td>
            <td>${photowall.title}</td>
        </tr>
        <tr>
            <td class="tdBlue">类型</td>
            <td>${photowall._type_desc}</td>
        </tr>
        <tr>
            <td class="tdBlue">创建时间</td>
            <td>${photowall.createTime}</td>
        </tr>
        <tr>
            <td class="tdBlue">创建人</td>
            <td>${photowall.userName}</td>
        </tr>
        <tr>
            <td class="tdBlue">状态</td>
            <td>${photowall._status_desc}</td>
        </tr>
        <tr>
            <td class="tdBlue">描述</td>
            <td>${photowall.des}</td>
        </tr>
        <tr>
            <td class="tdBlue">评论数</td>
            <td>${fn:length(reviewList)}</td>
        </tr>
        <tr>
            <td class="tdBlue">封面图片</td>
            <td>
            	<c:if test="${not empty photowall.imgs}">
            		<c:forEach items="${photowall.imgs}" var="imgUrl" varStatus="status">
            			<c:if test="${not empty imgUrl}">
							<img src="${baseURL}/${imgUrl}" id="pic${status.index+1}" height="100" width="150" alt="" />
							&nbsp;&nbsp;
						</c:if>            		
            		</c:forEach>
            	</c:if>
            </td>
        </tr>
        </tbody>
    </table>
    <table id="orgList" class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="3">
				<div class="topTitle mb20">
					<span class="topName">评论列表</span>
				</div>
			</td>
		</tr>
		<c:forEach items="${reviewList}" var="review" varStatus="index">
		<tr>
			<td class="tdBlue" colspan="2" >
				<div style="text-align: left;margin-left: 100px;">${review.content}</div>
			</td>
			<td width="400">
				<div style="text-align: right;margin-right: 80px;">
					<div style="float:left">
						<c:if test="${empty review.portraitPic}">
							<img align="top" id="img" src="${baseURL}/image/head.png" id="img${status.index+1}" height="80" width="120" alt="${review.userName}"/>
						</c:if>
						<c:if test="${!empty review.portraitPic}">
							<img src="${baseURL}/${review.portraitPic}" id="img${status.index+1}" height="80" width="120" alt="${review.userName}" />
						</c:if>
					</div>
					<div style="float:left;text-align: right;margin-top: 40px;">
						${review.time}
						<br/>
						${review.userName}
					</div>
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
    </div>
</form>
<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" onclick="backList()" value="返 回"/>
    </div>
</div>
<script type="text/javascript">
	
	function backList(){
		window.location.href="${baseURL}/jsp/component/photowall/list.jsp";
	}
</script>
<!--工具栏 end-->

</body>
</html>
