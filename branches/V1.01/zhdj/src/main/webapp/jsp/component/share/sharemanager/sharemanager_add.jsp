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

<form action="${baseURL}/sharemanager/sharemanagerAction!addTbShareInfoPO.action" method="post" id="adf5269611eb43938e3c0c4b91f3f772" onsubmit="return false;" templateId="default" dqdpCheckPoint="add_form">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body" >
        <thead>
            <tr>
                <th colspan="4">新增</th>
            </tr>
        </thead>
        <tbody>
                                                <tr dqdpCheckPoint="add_field_title">
                                <td width="120" class="tdBlue">标题：</td>
                                <td width="280">
                    <input class="form120px" name="tbShareInfoPO.title" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'标题'}"/>
                </td>
                                                                                                                                                                                                        <td width="120" class="tdBlue">类型：</td>
                                                                                <td width="280">
                    <select name="tbShareInfoPO.type"  dictType="shareType" ></select>
                </td>
                                                                                                                                            </tr>
                                                            <tr dqdpCheckPoint="add_field_status">
                                <td width="120" class="tdBlue">状态：</td>
                                                                                <td width="280">
                    <select name="tbShareInfoPO.status"  dictType="leaderStatus" ></select>
                </td>
                                                                                                                                                        <td width="120" class="tdBlue">创建时间：</td>
                                                                                                                                <td width="280">
                    <input class="form120px" type="text" name="tbShareInfoPO.createTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
                           valid="{must:false,fieldType:'datetime',regex:'',tip:'创建时间'}"/>
                </td>
                                                                                            </tr>
                                            </tbody>
    </table>
    <input type="hidden" name="id" value="${param.id}"/>
</form>
<script type="text/javascript">

	    function func_adf5269611eb43938e3c0c4b91f3f772() {
        _doCommonSubmit("adf5269611eb43938e3c0c4b91f3f772", "", "");
    }
</script>
<div class="toolBar" templateId="default">
    <div align="center">
                <input class="btn4" type="button" id="7efebab15b5d4c7998474d5750bb830e" onclick="func_adf5269611eb43938e3c0c4b91f3f772()" value="提交"/>
                <input class="btn4" type="button" id="707fa80f25734fe3b3bba9e6e64df9f7" onclick="javascript:history.go(-1)" value="返回"/>
            </div>
</div>

</body>
</html>
