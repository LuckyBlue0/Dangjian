<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>编辑角色</title>
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
<form action="${baseURL}/role/role!ajaxUpdateRole.action" method="post" id="role_edit">
    <input type="hidden" name="roleVO.userIds" id="userIds" value="" />
    <input type="hidden" name="roleVO.delUserIds" id="delUserIds" value="" />
    <input type="hidden" name="roleVO.perIds" id="perIds" value="" />
    <input type="hidden" name="roleVO.delPerIds" id="delPerIds" value="" />
    <table class="tableCommon mt5" dataSrc="${baseURL}/role/role!ajaxViewRole.action?roleId=${param.roleId}" width="100%" border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="roleVO.roleId" value="" />
        <thead>
        <tr>
            <th colspan="4">编辑角色</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">角色名称：</td>
            <td colspan="3"><input class="form120px" name="roleVO.roleName" id="roleName" type="text"/></td>
        </tr>
        <tr>
            <td width="100" class="tdBlue">
                角色描述：
            </td>
            <td colspan="3">
                <textarea name="roleVO.roleDescription" id="roleDescription" style="width: 950px;height:80px"></textarea>
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

    /**
    * 用户选择
    */
    function selectUser() {
        window.open('userSelect.jsp?roleId=${param.roleId}', '用户选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    /**
    * 权限选择
    */
    function selectPermission() {
        window.open('permissionSelect.jsp?roleId=${param.roleId}', '权限选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function(){
        $('#role_edit').ajaxSubmit({
            dataType:'json',
            success:function(result){
                if ('0' == result.code) {
                    alert('修改角色信息成功');
                    window.location.href="roleList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token;
                } else {
                    alert(result.desc);
                }
            },
            error:function() {
                alert('提交角色信息时发生错误');
            }
        });
    });
</script>

</body>
</html>
