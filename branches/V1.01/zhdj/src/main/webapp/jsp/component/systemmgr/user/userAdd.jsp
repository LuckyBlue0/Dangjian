<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增用户</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
        <!--
        .ziti {
            font-size: 16px;
        }
        -->
    </style>
</head>

<body>
<form action="${baseURL}/user/user!addUser.action" method="post" id="user_add">
    <input type="hidden" name="userVO.roleIds" id="roleIds" value="" />
    <input type="hidden" name="userVO.delRoleIds" id="delRoleIds" value="" />
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">新增用户</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">用户名称：</td>
            <td width="280"><input class="form120px" name="userVO.userName" type="text"/></td>
            <td width="120" class="tdBlue"></td>
            <td width="280"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">登录密码：</td>
            <td width="280"><input class="form120px" name="userVO.password" type="text"/></td>
            <td width="120" class="tdBlue">确认密码：</td>
            <td width="280"><input class="form120px" type="text"/></td>
        </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:selectRole();" value="选择角色"/>
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    function selectRole() {
        window.open('roleSelect.jsp', '角色选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function(){
        // 把delUserIds和delPerIds清空
        $('#delRoleIds').val('');
        // 提交数据
        $('#user_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('新增用户成功');
                    window.location.href=("userList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert('新增用户失败');
                }
            },
            error:function(){
                alert('新增用户失败，请稍候再试');
            }
        });
    });
</script>

</body>
</html>
