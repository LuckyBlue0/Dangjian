<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value="userView"></jsp:param>
    <jsp:param name="dict" value="personSex,userStatus"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看用户信息</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
     <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="../js/DataTable.js"></script>
    <style type="text/css">
        #tabs {
            padding-top: 10px;
            padding-bottom: 20px;
            margin: 10px 0px 0px 0px;
        }
    </style>
</head>

<body>
<div class="searchWrap">
            <div class="title">
                <h2 class="icon1">用户信息</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
<table class="tableCommon mt5" dataSrc='${baseURL}/person/person!viewBaseUserVO.action?personId=${param.personId}'
       width="100%" border="0" cellspacing="0" cellpadding="0">

    <tbody>
        <tr>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">用户名称</td>
            <td width="280" name="baseUserVO.personName"  style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">性别</td>
            <td width="280" name="baseUserVO.sexDesc"  style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">账户名称</td>
            <td width="280" name="baseUserVO.userName"  style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">状态</td>
            <td width="280" name="baseUserVO.statusDesc"  style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">年龄</td>
            <td width="280" name="baseUserVO.age"  style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue"  style="border:1px solid #e0e0e0;">最后登录时间</td>
            <td width="280" name="baseUserVO.lastLoginTime"  style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" height="70" class="tdBlue"  style="border:1px solid #e0e0e0;">用户角色</td>
            <td colspan="3"  style="border:1px solid #e0e0e0;">
                <div id="role_list"></div>
            </td>
        </tr>
        <tr>
            <td width="120" height="70" class="tdBlue"  style="border:1px solid #e0e0e0;">用户权限</td>
            <td colspan="3"  style="border:1px solid #e0e0e0;">
                <div id="permission_list"></div>
            </td>
        </tr>
    </tbody>
</table>
<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</div>



<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function () {
        listRole();
        listPermissionByUser();
    });

    function listRole() {
        $.ajax({
            type:'post',
            url:'${baseURL}/role/role!listRoleByPersonId.action',
            dataType:'json',
            data:{'personId':'${param.personId}'},
            success:function (result) {
                if ('0' == result.code) {
                    var roleTable = new DataTable({
                        data:result.data.roleList,
                        colsCount:5,
                        fieldName:"roleName"
                    });
                    roleTable.createTable("role_list");
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('通讯错误');
            }
        });
    }

    function listPermissionByUser() {
        $.ajax({
            url:"${baseURL}/permissionmgr/permissionmgr!listPermissionByUserId.action",
            type:"post",
            dataType:"json",
            data:{
                "userId":"${param.userId}"
            },
            success:function (result) {
                if ("0" == result.code) {
                    var permissionTable = new DataTable({
                        data:result.data.permissionList,
                        colsCount:5,
                        fieldName:"permissionName"
                    });
                    permissionTable.createTable("permission_list");
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

    function listUser() {
        window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
    }

    function cleanForm() {}
     function flashIframe_GDZC(){
    	var i=parent.document.getElementById("text");
    	
    	i.style.height = ($("#bt").height()+130)+"px";
    		
    	
    }

</script>

</body>
</html>
