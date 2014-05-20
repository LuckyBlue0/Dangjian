<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看权限信息</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
    <table class="tableCommon mt5" dataSrc='${baseURL}/permissionmgr/permissionmgr!viewPermission.action?perId=<%=WebUtil.getParm(request, "perId", "")%>'
           width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">权限信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">权限名称：</td>
            <td width="280" name="permissionMgrVO.permissionName"></td>
            <td width="120" class="tdBlue">权限代码：</td>
            <td width="280" name="permissionMgrVO.permissionCode"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">组件名称：</td>
            <td width="280" name="permissionMgrVO.component"></td>
            <td width="120" class="tdBlue">模块名称：</td>
            <td width="280" name="permissionMgrVO.modelName"></td>
        </tr>
        <tr>
            <td width="100" class="tdBlue">
                备注：
            </td>
            <td colspan="3" name="permissionMgrVO.memo">
            </td>
        </tr>
        </tbody>
    </table>

<div id="role_list"></div>
<div class="pageDown" id="rolePage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
<input class="form24px border999" type="text" value="1"/>
页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function() {
        listRole(1);
    });

    function listRole($pageIndx) {
        $.ajax({
            type:'post',
            url:'${baseURL}/role/role!listRoleByPerView.action',
            dataType:'json',
            data:{'perId':'${param.perId}'},
            success:function(result){
                if (result.code == '0') {
                    var roleList = new ListTable({
                        title:[
                            {width:"30%", name:"roleName", showName:"角色名称"},
                            {width:"50%", name:"roleDescription", showName:"角色描述"}
                        ],
                        data:result.data.pagerData,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    roleList.createList("role_list");
                    var rolePager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listRole"});
                    rolePager.createPageBar("rolePage");
                }
            }
        });
    }

</script>

</body>
</html>
