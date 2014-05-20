<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body style="background: #f6ebe5;">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">查看日志</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0"
           dataSrc="${baseURL}/dqdploger/dqdploger!getLog.action?id=${param.id}">

        <tbody>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">操作时间：</td>
                <td width="85%"  name="tbDqdpLogPO.createTime" style="border:1px solid #e0e0e0;"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">操作人名：</td>
                <td width="85%" name="tbDqdpLogPO.operationId" style="border:1px solid #e0e0e0;"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">模块名：</td>
                <td width="85%" name="tbDqdpLogPO.modelName" style="border:1px solid #e0e0e0;"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">操作类型：</td>
                <td width="85%" name="tbDqdpLogPO._operationType_desc" style="border:1px solid #e0e0e0;"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">操作结果CODE：</td>
                <td width="85%" name="tbDqdpLogPO._operationResult_desc" style="border:1px solid #e0e0e0;"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">操作描述：</td>
                <td width="85%" name="tbDqdpLogPO.operationDesc" style="border:1px solid #e0e0e0;"></td>
            </tr>
        </tbody>
    </table>
    </div>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

</body>
</html>
