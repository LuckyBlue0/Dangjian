<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
</head>

<body>
<table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body" datasrc="local:var_e3f4e49c435f4947b1e804c479f69eac">
    <thead>
        <tr>
            <th colspan="4">新增报备</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td width="120" class="tdBlue">createTime：</td>
            <td width="280" name="test.createTime"/>
            <td width="120" class="tdBlue">operationId：</td>
            <td width="280" name="test.operationId"/>
        </tr>
        <tr>
            <td width="120" class="tdBlue">operationName：</td>
            <td width="280" name="test.operationName"/>
            <td width="120" class="tdBlue">modelName：</td>
            <td width="280" name="test.modelName"/>
        </tr>
        <tr>
            <td width="120" class="tdBlue">operationType：</td>
            <td width="280" name="test.operationType"/>
            <td width="120" class="tdBlue">operationResult：</td>
            <td width="280" name="test.operationResult"/>
        </tr>
        <tr>
            <td width="120" class="tdBlue">operationDesc：</td>
            <td width="280" colspan="3"><textarea rows="3" cols="120" name="test.operationDesc" editor="{'soft':'kindeditor','param':'',readonly:true}"></textarea></td>

        </tr>
    </tbody>
</table>
<script type="text/javascript">
    var var_e3f4e49c435f4947b1e804c479f69eac = _doGetDataSrc("${baseURL}/xxx.action", "");
</script>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" id="39822a20ff7746cb9f5a3c2ee4e7e4d7" onclick="func_d4bacc83e4714eb49fbfae1219eb9216()" value="测试按钮"/>
    </div>
</div>

</body>
</html>
