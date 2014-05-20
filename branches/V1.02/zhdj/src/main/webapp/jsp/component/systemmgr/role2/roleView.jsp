<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value="roleView"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看角色</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.tabs.js"></script>
    <style type="text/css">
        #userTable td {
            border: 1px solid #C1DAD7;
            font-size: 15px;
            padding: 6px 6px 6px 12px;
            text-align: center;
            height: 18px;
        }

        #userTable tr {
            background-color: #fff;
        }

        #tabs {
            padding-top: 10px;
            padding-bottom: 20px;
            margin: 10px 0px 0px 0px;
        }

    </style>
</head>

<body style="background: #f6ebe5;">
<div class="searchWrap">
            <div class="title clearfix">
                <h2 class="icon1">角色信息</h2>
            </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td class="searchLeft"></td>
            </tr>
            </tbody></table>
 </div>
 <div class="text_bg">
<table class="tableCommon mt5" dataSrc="${baseURL}/role/role!ajaxViewRole.action?roleId=${param.roleId}" width="100%" border="0" cellspacing="0" cellpadding="0">

    <tbody>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">角色名称</td>
            <td colspan="3" name="roleVO.roleName" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" height="70" class="tdBlue" style="border:1px solid #e0e0e0;">
                角色描述
            </td>
            <td colspan="3" style="border:1px solid #e0e0e0;">
                <div name="roleVO.roleDescription" style="overflow: auto;"></div>
            </td>
        </tr>
    </tbody>
</table>

<!--用户、权限tabs -->
<div id="tabs">
    <ul>
        <li><a href="#userTab">角色用户</a></li>
        <li><a href="#permissionTab">角色权限</a></li>
    </ul>
    <div id="userTab">
        <div id="user_list"></div>
        <div class="page" id="userPage"></div>
    </div>
    <div id="permissionTab">
        <div id="permission_list"></div>
        <div class="page" id="permissionPage"></div>
    </div>
</div>
<!--用户、权限tabs end-->

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</div>
<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function () {
        listUsers(1);
        listPermissions(1);
        $('#tabs').tabs({
            show:function(event,ui) {
               _resetFrameHeight();
            }
        });
    });

    /**
     * 分页查询角色用户列表
     * @param $pageIndex
     */
    function listUsers($pageIndex) {
        $.ajax({
            type:'post',
            url:'${baseURL}/person/person!listBaseUserByRoleId.action',
            data:{
                'roleId':'${param.roleId}',
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var userList = new ListTable({
                        title:[
                            {width:"20%", name:"userName", showName:"用户账号"},
                            {width:"20%", name:"personName", showName:"用户名称"},
                            {width:"20%", name:"statusDesc", showName:"用户状态"},
                            {width:"20%", name:"sexDesc", showName:"用户性别"}
                        ],
                        data:result.data.userList,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    userList.createList("user_list");
                    var perPager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listUsers"});
                    perPager.createPageBar("userPage");
                }
                _resetFrameHeight();
            }
        });
    }

    /**
     * 分页查询角色权限列表
     * @param $pageIndex
     */
    function listPermissions($pageIndex) {
        $.ajax({
            type:'post',
            url:'${baseURL}/permissionmgr/permissionmgr!listPerByRoleIdView.action',
            data:{
                'roleId':'${param.roleId}',
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var permissionList = new ListTable({
                        title:[
                            {width:"20%", name:"permissionName", showName:"权限名称"},
                            {width:"20%", name:"permissionCode", showName:"权限代码"},
                            {width:"20%", name:"modelName", showName:"所属模块"},
                            {width:"20%", name:"component", showName:"所属组件"}
                        ],
                        data:result.data.permissionPagerData,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    permissionList.createList("permission_list");
                    var perPager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listPermissions"});
                    perPager.createPageBar("permissionPage");
                }

            }
        });
    }

</script>

</body>
</html>
