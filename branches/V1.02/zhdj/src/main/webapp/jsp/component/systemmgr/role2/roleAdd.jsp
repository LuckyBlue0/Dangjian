<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value="roleList,permissionList"></jsp:param>
    <jsp:param name="mustPermission" value="roleAdd"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增角色</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>

    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/HashMap.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    .btnQuery {
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    height: 24px;
    line-height: 24px;
    padding-bottom: 3px;
    width: 68px;
     }
    </style>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/role/role!ajaxAddRole.action" method="post" id="role_add">
    <input type="hidden" name="roleVO.userIds" id="userIds" value=""/>
    <input type="hidden" name="roleVO.perIds" id="perIds" value=""/>
     <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">新增角色</h2>
            </div>
        </div>
    <div class="text_bg" id="bt" style="padding-bottom: 50px">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tbody>
            <tr>
                <td width="120" class="tdBlue">角色名称</td>
                <td colspan="3"><input class="form120px" name="roleVO.roleName" type="text"
                                       valid="{must:true,fieldType:'pattern', regex:'^.{1,20}$',tip:'角色名称'}"/><span><font color="red">*</font></span>
                    请控制在1到20字以内
                </td>
            </tr>
            <tr>
                <td width="100" class="tdBlue">
                    角色描述
                </td>
                <td colspan="3">
                    <textarea name="roleVO.roleDescription" id="roleDescription" style="width: 800px;height:70px"
                              valid="{must:false,fieldType:'pattern', regex:'^.{1,150}$',tip:'角色描述'}"></textarea>
                    <br/>请控制在150字以内
                </td>
            </tr>
            <td width="100" height="70" class="tdBlue" >
                角色用户
            </td>
            <td colspan="3">
                <textarea readonly="readonly"  style="width: 800px; height: 70px" id="user_list"></textarea>
                <input class="btnQuery" type="button" onclick="javascript:selectUser();" permission="roleList" value="选择用户"/>
            </td>
            </tr>
            <td width="100" height="70" class="tdBlue">
                角色权限
            </td>
            <td colspan="3">
                <div id="permission_list"></div>
                <table id="permission_tb"></table>
            </td>
            </tr>
        </tbody>
    </table>
<div class="toolBar">
    <div align="center">
        <input class="btnQuery" id="save" type="button" value="保存"/>
        <input class="btnQuery" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</div>
</form>



<!--工具栏 end-->

<script type="text/javascript">

    var userMap = new HashMap();
    var permissionMap = new HashMap();

    $(document).ready(function () {
        listPermission();
    });

    function selectUser() {
        window.open('userSelect.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token, '用户选择', 'height=480, width=700, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
    }

    function selectPermission() {
        window.open('permissionSelect.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token, '权限选择', 'height=480, width=700, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
    }

    function listPermission() {
        $.ajax({
            url:'${baseURL}/permissionmgr/permissionmgr!listAllPermission.action',
            type:'post',
            dataType:'json',
            data:{'dqdp_csrf_token': dqdp_csrf_token},
            success:function (result) {
                if ("0" ==  result.code) {
                    var perTable = new CheckboxTable({
                        data:result.data.permissionList,
                        typeRow:"modelName",
                        colsCount:5,
                        checkboxValue:"permissionId",
                        checkboxName:"permissionName"
                    });
                    perTable.createTable("permission_list");

                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert("通讯错误");
            }
        });
    }

    $('#save').click(function () {
        var checkDqdp = new Dqdp();
        if (checkDqdp.submitCheck('role_add')) {
            // 获取选择的权限
            var permissionIds = '';
            $.each($('[name$=Ids]'), function () {
                if ($(this).prop('checked'))
                    permissionIds += $(this).val() + ',';
            });
            $('#perIds').val(permissionIds);
            // 提交数据
            $('#role_add').ajaxSubmit({
                dataType:'json',
                data:{'dqdp_csrf_token': dqdp_csrf_token},
                success:function (result) {
                    if ("0" == result.code) {
                        alert('新增角色成功');
                        window.location.href=("roleList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                    } else {
                        alert(result.desc);
                    }
                },
                error:function () {
                    alert('新增角色失败');
                }
            });
        }
    });

    function getUserMap() {
        return userMap;
    }

    function getPermissionMap() {
        return permissionMap;
    }

    function updateUser() {
        $('#userIds').val(userMap.keys().join(","));
        $('#user_list').val(userMap.values().join(","));
    }

    function updatePermission() {
        $('#perIds').val(permissionMap.keys().join(","));
    }

</script>

</body>
</html>
