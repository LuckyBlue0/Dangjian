<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="mustPermission" value="userChangePassword"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改用户</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/HashMap.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/user/user!changePassword.action" method="post" id="user_edit">
    <table class="tableCommon mt5" dataSrc="${baseURL}/user/user!viewUser.action?userId=${param.userId}" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
            <tr>
                <th colspan="4">用户密码修改</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">账户名称：</td>
                <td width="280" name="userVO.userName"></td>
                <td width="120" class="tdBlue">用户状态：</td>
                <td width="280" name="userVO.statusDesc"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">新密码：</td>
                <td width="280"><input class="form120px" name="newPassword" type="password"
                                       valid="{must:true, fieldType:'pattern', regex:'^.{6,20}$', tip:'密码'}"/></td>
                <td width="120" class="tdBlue">确认密码：</td>
                <td width="280"><input class="form120px" name="confirmPassword" type="password"
                                       valid="{must:true, fieldType:'pattern', regex:'^.{6,20}$', tip:'密码'}"/></td>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">

    $('#save').click(function () {
        var dqdp = new Dqdp();
        if (dqdp.submitCheck('user_edit')) {
            if (checkPassword()) {
                // 提交数据
                $('#user_edit').ajaxSubmit({
                    data:{
                        'userId':'${param.userId}'
                    },
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('密码修改成功');
                            window.location.href=("orgUserList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('密码修改失败，可能是由网络原因引起的，请稍候再试');
                    }
                });
            } else {
                alert('两次输入的密码不一致！');
            }
        }
    });

    function checkPassword() {
        if ($('[name=newPassword]').val() == $('[name=confirmPassword]').val())
            return true;
        else
            return false;
    }

    function listUser() {
        window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
    }

    function cleanForm() {}

</script>

</body>
</html>
