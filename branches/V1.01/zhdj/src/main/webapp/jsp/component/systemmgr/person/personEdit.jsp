<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改人员信息</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/person/person!updatePerson.action" method="post" id="person_edit">
    <input type="hidden" name="personVO.userIds" id="userIds" value=""/>
    <input type="hidden" name="personVO.delUserIds" id="delUserIds" value=""/>
    <table class="tableCommon mt5" dataSrc='${baseURL}/person/person!viewPerson.action?personId=${param.personId}'
           width="100%" border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="personVO.personId" value=""/>
        <thead>
            <tr>
                <th colspan="4">修改人员信息</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">人员名称：</td>
                <td width="280"><input class="form120px" name="personVO.personName" type="text"/></td>
                <td width="120" class="tdBlue">性别：</td>
                <td width="280"><select name="personVO.sex" dictType="personSex"/></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">年龄：</td>
                <td width="280"><input class="form120px" name="personVO.age" type="text"/></td>
                <td width="120" class="tdBlue"></td>
                <td width="280"></td>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:selectUser();" value="选择用户"/>
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    function selectUser() {
        window.open('userSelect.jsp?personId=${param.personId}', '角色选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function () {
        // 提交数据
        $('#person_edit').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                if ("0" == result.code) {
                    alert('修改人员信息成功');
                    window.location.href=("personList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert(result.desc);
                }
            },
            error: function () {
                alert('修改人员信息失败，请稍候再试');
            }
        });
    });
</script>

</body>
</html>
