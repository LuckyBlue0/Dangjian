<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增角色</title>
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
<form action="${baseURL}/role/role!ajaxAddRole.action" method="post" id="role_add">
    <input type="hidden" name="roleVO.userIds" id="userIds" value="" />
    <input type="hidden" name="roleVO.delUserIds" id="delUserIds" value="" />
    <input type="hidden" name="roleVO.perIds" id="perIds" value="" />
    <input type="hidden" name="roleVO.delPerIds" id="delPerIds" value="" />
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">新增角色</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">角色名称：</td>
            <td colspan="3"><input class="form120px" name="roleVO.roleName" type="text"/></td>
        </tr>
        <tr>
            <td width="100" class="tdBlue">
                角色描述：
            </td>
            <td colspan="3">
                <textarea name="roleVO.roleDescription" style="width: 950px;height:80px"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:selectUser();" value="选择用户"/>
        <input class="btn4" type="button" onclick="javascript:selectPermission();" value="选择权限"/>
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    function selectUser() {
        window.open('userSelect.jsp', '用户选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    function selectPermission() {
        window.open('permissionSelect.jsp', '权限选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function(){
        // 把delUserIds和delPerIds清空
        $('#delUserIds').val('');
        $('#delPerIds').val('');
        // 提交数据
        $('#role_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('新增角色成功');
                    window.location.href=("roleList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert('新增角色失败');
                }
            },
            error:function(){
                alert('新增角色失败');
            }
        });
    });
</script>

</body>
</html>
