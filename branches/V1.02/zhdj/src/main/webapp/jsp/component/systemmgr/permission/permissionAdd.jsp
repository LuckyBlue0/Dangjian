<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增权限</title>
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
<form action="${baseURL}/permissionmgr/permissionmgr!addPermission.action" method="post" id="permission_add">
    <input type="hidden" name="permissionMgrVO.roleIds" id="roleIds" value="" />
    <input type="hidden" name="permissionMgrVO.delRoleIds" id="delRoleIds" value="" />
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">新增权限</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">权限名称：</td>
            <td width="280">
                <input class="form120px" name="permissionMgrVO.permissionName" type="text"
                        valid="{must:'true',tip:'权限名称不能为空',validType='pattern',regex='^[\s]{0,}'}"/>
            </td>
            <td width="120" class="tdBlue">权限代码：</td>
            <td width="280"><input class="form120px" name="permissionMgrVO.permissionCode" type="text"/></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">组件名称：</td>
            <td width="280"><input class="form120px" name="permissionMgrVO.component" type="text"/></td>
            <td width="120" class="tdBlue">模块名称：</td>
            <td width="280"><input class="form120px" name="permissionMgrVO.modelName" type="text"/></td>
        </tr>
        <tr>
            <td width="100" class="tdBlue">
                备注：
            </td>
            <td colspan="3">
                <textarea name="permissionMgrVO.memo" style="width: 950px;height:80px"></textarea>
            </td>
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
        window.open('roleSelect.jsp'+'?dqdp_csrf_token='+dqdp_csrf_token, '角色选择', 'height=480, width=700, toolbar=no, menubar=no, resizable=yes,location=no, status=no');
    }

    $('#save').click(function(){
        // 把delRoleIds清空
        $('#delRoleIds').val('');
        // 提交数据
        $('#permission_add').ajaxSubmit({
            dataType:'json',
            data: {'dqdp_csrf_token': dqdp_csrf_token},
            success:function(result) {
                if ("0"==result.code) {
                    alert('新增权限成功');
                    window.location.href=("permissionList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                } else {
                    alert(result.desc);
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
