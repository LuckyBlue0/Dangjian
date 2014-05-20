<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增人员</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/person/person!addPerson.action" method="post" id="person_add">
    <input type="hidden" name="personVO.userIds" id="userIds" value=""/>
    <input type="hidden" name="personVO.delUserIds" id="delUserIds" value=""/>
    <input type="hidden" name="personVO.orgId"  value="${param.orgId}"/>
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
            <tr>
                <th colspan="4">新增人员</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">人员名称：</td>
                <td width="280">
                    <input class="form120px" name="personVO.personName" type="text"
                           valid="{must:'true',tip:'人员名称不能为空',validType='pattern',regex='^[\s]{0,}'}"/>
                </td>
                <td width="120" class="tdBlue">登陆账号：</td>
                <td width="280">
                    <input class="form120px" name="userVO.userName" type="text"
                           valid="{must:'true',tip:'人员名称不能为空',validType='pattern',regex='^[\s]{0,}'}"/>
                </td>

            </tr>
            <tr>
                <td width="120" class="tdBlue">密码：</td>
                <td width="280">
                    <input class="form120px" name="userVO.password" type="password"
                           valid="{must:'true',tip:'密码'"/>
                </td>
                <td width="120" class="tdBlue">确认密码：</td>
                <td width="280">
                    <input class="form120px" name="userVO.confirmPassword" type="password"
                           valid="{must:'true',tip:'确认密码'"/>
                </td>

            </tr>
            <tr>
                <td width="120" class="tdBlue">年龄：</td>
                <td width="280"><input class="form120px" name="personVO.age" type="text"/></td>
                <td width="120" class="tdBlue">性别：</td>
                <td width="280"><select name="personVO.sex" dictType="personSex"/></td>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <%--<input class="btn4" type="button" onclick="javascript:selectUser();" value="选择用户"/>--%>
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    function selectUser() {
        window.open('userSelect.jsp', '用户选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function () {
        // 提交数据
        $('#person_add').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    alert('新增人员信息成功');
                    window.location.href=("personList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('新增人员失败，请稍候再试');
            }
        });
    });
</script>

</body>
</html>
