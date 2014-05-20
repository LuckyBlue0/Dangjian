<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="userStatus"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改用户</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/user/user!updateUser.action" method="post" id="user_edit">
    <input type="hidden" name="userVO.roleIds" id="roleIds" value="" />
    <input type="hidden" name="userVO.delRoleIds" id="delRoleIds" value="" />
    <table class="tableCommon mt5" dataSrc="${baseURL}/user/user!viewUser.action?userId=<%=WebUtil.getParm(request,"userId","")%>" width="100%" border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="userVO.userId" value="" />
        <thead>
        <tr>
            <th colspan="4">修改用户信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">用户名称：</td>
            <td width="280"><input class="form120px" name="userVO.userName" type="text"/></td>
            <td width="120" class="tdBlue">用户状态：</td>
            <td width="280"><select name="userVO.status" dictType="userStatus" defaultValue="" /></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">新密码：</td>
            <td width="280"><input class="form120px" name="userVO.password" type="text"/></td>
            <td width="120" class="tdBlue">确认密码：</td>
            <td width="280"><input class="form120px" name="userVO.confirmPassword" type="text"/></td>
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
        window.open('roleSelect.jsp?userId=${param.userId}', '角色选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function(){
        // 提交数据
        $('#user_edit').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('修改用户信息成功');
                    window.location.href=("userList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert('修改用户信息失败:'+result.desc);
                }
            },
            error:function(){
                alert('修改用户信息失败，请稍候再试');
            }
        });
    });
</script>

</body>
</html>
