<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="roleList"></jsp:param>
    <jsp:param name="mustPermission" value="userEdit"></jsp:param>
    <jsp:param name="dict" value="userStatus,personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改用户</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/HashMap.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/person/person!updateBaseUser.action" method="post" id="user_edit">
    <table class="tableCommon mt5" dataSrc="${baseURL}/person/person!viewBaseUserVO.action?personId=${param.personId}" width="100%" border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="baseUserVO.userId" value=""/>
        <input type="hidden" name="baseUserVO.personId" value=""/>
        <thead>
            <tr>
                <th colspan="4">修改用户信息</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">用户名称</td>
                <td width="280"><input class="form120px" name="baseUserVO.personName" type="text"
                                       valid="{must:true,tip:'用户名称',fieldType:'pattern',length:20}"/><span><font color="red">*</font></span></td>
                <td width="120" class="tdBlue">用户状态</td>
                <td width="280"><select name="baseUserVO.status" dictType="userStatus" defaultValue=""/></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">年龄</td>
                <td width="280"><input class="form120px" name="baseUserVO.age" type="text"
                                       valid="{must:false, fieldType:'pattern', regex:'^\\d{0,3}$', tip:'年龄'}"/></td>
                <td width="120" class="tdBlue">性别</td>
                <td width="280"><select name="baseUserVO.sex" dictType="personSex" defaultValue=""/></td>
            </tr>
            <tr>
                <td width="120" height="70" class="tdBlue">角色</td>
                <td colspan="3">
                    <div id="role_list"></div>
                </td>
                <input type="hidden" name="baseUserVO.roleIds" id="roleIds"/>
            </tr>
        </tbody>
    </table>
    <div class="toolBar">
    <div align="center">
        <input class="btnQuery" id="save" type="button" value="保存"/>
        <input class="btnQuery" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">
    $(document).ready(function () {
        listRole();
    });

    function updateRole() {
        var roleIdStr = "";
        $("[name$=Ids]").each(function() {
            if ($(this).prop("checked")) {
                roleIdStr += $(this).val() + ",";
            }
        });
        $("#roleIds").val(roleIdStr);
    }

    function listRole() {
        $.ajax({
            url:'${baseURL}/role/role!listAllRole.action',
            data:{},
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var roleCheckTable = new CheckboxTable({
                        data:result.data.roleList,
                        colsCount:4,
                        checkboxName:"roleName",
                        checkboxValue:"roleId"
                    });
                    roleCheckTable.createTable("role_list");
                    $.ajax({
                        url:"${baseURL}/role/role!listRoleByUserId.action",
                        data:{'userId':'${param.userId}'},
                        type:"post",
                        dataType:"json",
                        success:function(result) {
                            if ("0" == result.code) {
                                $("[name$=Ids]").each(function(){
                                    var roleList = result.data.roleList;
                                    for (var i = 0; i < roleList.length; i++) {
                                        if ($(this).val() == roleList[i].roleId) {
                                            $(this).prop("checked", true);
                                            break;
                                        }
                                    }
                                });
                            }
                        }
                    });
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
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("user_edit")) {
            // 提交数据
            updateRole();
            $('#user_edit').ajaxSubmit({
                dataType:'json',
                success:function (result) {
                    if ("0" == result.code) {
                        alert('修改用户信息成功');
                        window.location.href=("orgUserList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                    } else {
                        alert('修改用户信息失败');
                    }
                },
                error:function () {
                    alert('修改用户信息失败，可能是由网络原因引起的，请稍候再试');
                }
            });
        }
    });

    function listUser() {
        window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
    }

    function cleanForm() {}
</script>

</body>
</html>
