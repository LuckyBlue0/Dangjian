<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="shareType,leaderStatus"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    
        <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
            </head>

<body templateId="SQLPager">

<table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body" datasrc="local:var_3a48e0aa9e174ff780e7f7bd9b0a98d7" templateId="default" dqdpCheckPoint="view_fields">
    <thead>
        <tr>
            <th colspan="4">新增报备</th>
        </tr>
    </thead>
    <tbody>
                                <tr dqdpCheckPoint="view_field_title">
                        <td width="120" class="tdBlue">标题：</td>
                        <td width="280" name="tbShareInfoPO.title" />
                                                                                                    <td width="120" class="tdBlue">类型：</td>
                        <td width="280" name="tbShareInfoPO.type" />
                                                                                </tr>
                                <tr dqdpCheckPoint="view_field_status">
                        <td width="120" class="tdBlue">状态：</td>
                        <td width="280" name="tbShareInfoPO.status" />
                                                                                                    <td width="120" class="tdBlue">创建时间：</td>
                        <td width="280" name="tbShareInfoPO.createTime" />
                                                                                </tr>
                    </tbody>
</table>
<script type="text/javascript">
var var_3a48e0aa9e174ff780e7f7bd9b0a98d7=_doGetDataSrc("${baseURL}/sharemanager/sharemanagerAction!ajaxView.action?id=${param.id}","");
</script>

<div class="toolBar" templateId="default">
    <div align="center">
                <input class="btn4" type="button" id="7c00fb4a7bc24b50a78ef469005603c2" onclick="javascript:history.go(-1)" value="返回"/>
            </div>
</div>

</body>
</html>
