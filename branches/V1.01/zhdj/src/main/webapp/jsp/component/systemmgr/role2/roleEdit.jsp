<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value="roleList,permissionList"></jsp:param>
    <jsp:param name="mustPermission" value="roleEdit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>编辑角色</title>
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
<form action="${baseURL}/role/role!ajaxUpdateRole.action" method="post" id="role_edit">
    <input type="hidden" name="roleVO.userIds" id="userIds" value=""/>
    <input type="hidden" name="roleVO.perIds" id="perIds" value=""/>
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">修改角色</h2>
            </div>
        </div>
    <div class="text_bg" id="bt" style="padding-bottom: 50px">
    <table class="tableCommon mt5" dataSrc="${baseURL}/role/role!ajaxViewRole.action?roleId=${param.roleId}" width="100%" border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="roleVO.roleId" value=""/>
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
                    <textarea name="roleVO.roleDescription" id="roleDescription" style="width: 950px;height:70px"
                              valid="{must:false,fieldType:'pattern', regex:'^.{1,150}$',tip:'角色描述'}"></textarea>
                    <br/>请控制在150字以内
                </td>
            </tr>
            <tr>
                <td width="100" height="80" class="tdBlue">
                    角色用户
                </td>
                <td colspan="3">
                    <textarea readonly="readonly" style="width: 800px; height: 70px" id="user_list"></textarea>
                    <input class="btnQuery" type="button" onclick="javascript:selectUser();" permission="roleList" value="选择用户"/>
                </td>
            </tr>
            <tr>
                <td width="100" height="80" class="tdBlue">
                    角色权限
                </td>
                <td colspan="3">
                    <div id="permission_list"></div>
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
        listUserByRole();
//        listPermissionByRole();
        listPermission();
    });

    /**
     * 用户选择
     */
    function selectUser() {
        window.open('userSelect.jsp?roleId=${param.roleId}', '用户选择', 'height=480, width=700, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
    }

    /**
     * 权限选择
     */
    function selectPermission() {
        window.open('permissionSelect.jsp?roleId=${param.roleId}', '权限选择', 'height=480, width=700, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
    }

    function listPermission() {
        $.ajax({
            url:'${baseURL}/permissionmgr/permissionmgr!listAllPermission.action',
            type:'post',
            dataType:'json',
            data:{},
            success:function (result) {
                if ("0" == result.code) {
                    var perTable = new CheckboxTable({
                        data:result.data.permissionList,
                        typeRow:"modelName",
                        colsCount:5,
                        checkboxValue:"permissionId",
                        checkboxName:"permissionName"
                    });
                    perTable.createTable("permission_list");
                    initPermissionSelect();
                    _resetFrameHeight();
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert("通讯错误");
            }
        });

    }

    function initPermissionSelect() {
        $.ajax({
            url:'${baseURL}/permissionmgr/permissionmgr!listPermissionByRoleId.action?roleId=${param.roleId}',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var permissionArray = result.data.permissionList;
                    $('[name$=Ids]').each(function () {
                        for (var i = 0; i < permissionArray.length; i++) {
                            if ($(this).val() == permissionArray[i].permissionId) {
                                $(this).prop("checked", true);
                                break;
                            }
                        }
                    });
                    updatePermission();
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('通讯错误');
            }
        });
    }

    $('#save').click(function () {
        updatePermission();
        var dqdp = new Dqdp();
        if (dqdp.submitCheck()) {
            $('#role_edit').ajaxSubmit({
                dataType:'json',
                success:function (result) {
                    if ('0' == result.code) {
                        alert('修改角色信息成功');
                        window.location.href = "roleList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token;
                    } else {
                        alert(result.desc);
                    }
                },
                error:function () {
                    alert('提交角色信息时发生错误');
                }
            });
        }
    });

    function listUserByRole() {
        $.ajax({
            url:'${baseURL}/user/user!listUserByRole.action?roleId=${param.roleId}',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var userArray = result.data.userList;
                    for (var i = 0; i < userArray.length; i++) {
                        userMap.put(userArray[i].userId, userArray[i].userName);
                    }
                    updateUser();
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('通讯错误');
            }
        });
    }

    function listPermissionByRole() {
        $.ajax({
            url:'${baseURL}/permissionmgr/permissionmgr!listPermissionByRoleId.action?roleId=${param.roleId}',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var permissionArray = result.data.permissionList;
                    for (var i = 0; i < permissionArray.length; i++) {
                        permissionMap.put(permissionArray[i].permissionId, permissionArray[i].permissionName);
                    }
                    updatePermission();
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('通讯错误');
            }
        });
    }

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
        var permissionIds = "";
        $.each($('[name$=Ids]'), function () {
            if ($(this).prop("checked"))
                permissionIds += $(this).val() + ",";
        });
        $("#perIds").val(permissionIds);
    }
</script>

</body>
</html>
