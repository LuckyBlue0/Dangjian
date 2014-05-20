<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="clientType"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看版本</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${baseURL}/css/progressBar.css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/progressBar.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/version/versionAction!ajaxUpdate.action" method="post" id="org_add" enctype="multipart/form-data">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">查看版本</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/version/versionAction!ajaxView.action?id=${param.id}">
        <input type="hidden" name="tbVersionPO.fileSize" id="fileSize"/>
        <input type="hidden" name="tbVersionPO.id"/>

        <tbody>
            <tr>
                <td class="tdBlue">客户端名称</td>
                <td name="tbVersionPO.versionName">
                </td>
            </tr>
            <tr>
                <td class="tdBlue">版本号</td>
                <td name="tbVersionPO.versionNum">
                </td>
            </tr>
            <tr>
                <td class="tdBlue">平台类型</td>
                <td name="tbVersionPO._type_desc">
                </td>
            </tr>
            
            <tr>
                <td class="tdBlue">文件下载</td>
                <td >
                 <a href="${baseURL}/@{tbVersionPO.filePath}">下载附件</a>
                 </td>
            </tr>
            <tr>
                <td class="tdBlue">更新说明</td>
                <td name="tbVersionPO.remark">
                </td>
            </tr>
        </tbody>
    </table>
   	<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
	</div>
    </div>
</form>
</body>
</html>
