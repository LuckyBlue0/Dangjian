<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="groups"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看民主机关评议</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
</head>

<body style="background: #f6ebe5;">
<form action="" method="post" id="id_form_search">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">查看民主机关评议</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
        <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
		<tr>
			<td colspan="2">
				<div class="topTitle mb20">
					<span class="topName">基本信息</span>
				</div>
			</td>
		</tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>活动专题</td>
            <td>${tbOrgVotePO.topic}</td>
        </tr>
        <tr>
            <td class="tdBlue"  ><span style="color: red">*</span>评议时间</td>
             <td>开始时间&nbsp;<fmt:formatDate value="${tbOrgVotePO.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   &nbsp;&nbsp;&nbsp;&nbsp;结束时间
				<fmt:formatDate value="${tbOrgVotePO.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>备注</td>
            <td>${tbOrgVotePO.remark}"</td>
        </tr>
        </tbody>
    </table>
    <table id="orgList" class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="3">
				<div class="topTitle mb20">
					<span class="topName">被评选机关列表</span>
				</div>
			</td>
		</tr>
		<c:forEach items="${tbVoteOrgList}" var="voteOrg" varStatus="index">
		<tr>
			<td class="tdBlue"></td>
			<td>${voteOrg.voteOrg}</td>
			<td><a href="javascript:void(0)" onClick="getResult('${voteOrg.id}')">查看投票结果</a></td>
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
	function getResult(voteOrgListId){
		window.location.href="${baseURL}/jsp/component/vote/orgVote/orgVoteResult.jsp?id="+ voteOrgListId+"&voteOrgId="+"${tbOrgVotePO.id}";
	}
	
	function backList(){
		window.location.href="${baseURL}/jsp/component/vote/orgVote/orgVoteList.jsp";
	}
</script>
<!--工具栏 end-->

</body>
</html>
