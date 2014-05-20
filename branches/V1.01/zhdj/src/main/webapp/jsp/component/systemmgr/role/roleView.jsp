<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看角色</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
    <table class="tableCommon mt5" dataSrc="${baseURL}/role/role!ajaxViewRole.action?roleId=${param.roleId}" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">角色信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">角色名称：</td>
            <td colspan="3" name="roleVO.roleName"></td>
        </tr>
        <tr>
            <td width="100" class="tdBlue">
                角色描述：
            </td>
            <td colspan="3" name="roleVO.roleDescription">
            </td>
        </tr>
        </tbody>
    </table>

<div>
    <div id="user_list"></div>
    <div class="pageDown" id="userPage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
    <div id="permission_list"></div>
    <div class="pageDown" id="permissionPage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
</div>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function(){
        listUsers(1);
        listPermissions(1);
    });

    /**
    * 分页查询角色用户列表
    * @param $pageIndex
    */
    function listUsers($pageIndex) {
        $.ajax({
            type:'post',
            url:'${baseURL}/user/user!ajaxListUserByRoleView.action',
            data:{
                'roleId':'${param.roleId}',
                'pageSize':10
            },
            dataType:'json',
            success:function(result) {
                if ('0' == result.code) {
                    var userList = new ListTable({
                        title:[
                            {width:"100%", name:"userName", showName:"用户名称"}
                        ],
                        data:result.data.userPagerData,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    userList.createList("user_list");
                    var userPager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listUsers"});
                    userPager.createPageBar("userPage");
                }
            }
        });
    }

    /**
    * 分页查询角色权限列表
    * @param $pageIndex
    */
    function listPermissions($pageIndex){
        $.ajax({
            type:'post',
            url:'${baseURL}/permissionmgr/permissionmgr!listPerByRoleIdView.action',
            data:{'roleId':'${param.roleId}'},
            dataType:'json',
            success:function(result) {
                if ('0' == result.code) {
                    var permissionList = new ListTable({
                        title:[
                            {width:"20%", name:"permissionName", showName:"权限名称"},
                            {width:"20%", name:"permissionCode", showName:"权限代码"},
                            {width:"20%", name:"modelName", showName:"模块名称"}
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
