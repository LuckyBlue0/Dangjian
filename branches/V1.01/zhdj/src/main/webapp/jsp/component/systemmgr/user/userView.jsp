<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="userStatus"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看用户</title>
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
    <table class="tableCommon mt5" dataSrc="${baseURL}/user/user!viewUser.action?userId=${param.userId}"
           width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="4">用户信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="120" class="tdBlue">用户名称：</td>
            <td width="280" name="userVO.userName"></td>
            <td width="120" class="tdBlue">用户状态：</td>
            <td width="280" name="userVO.statusDesc"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">最后登录时间：</td>
            <td width="280" name="userVO.lastLoginTime"></td>
            <td width="120" class="tdBlue"></td>
            <td width="280"></td>
        </tr>
        </tbody>
    </table>

<div id="role_list"></div>
<div class="pageDown" id="rolePage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
<div id="tip"></div>

<!--工具栏 -->
<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function(){
        listRole(1);
    });

    /**
    * 分页查询用户角色列表
    * @param $pageIndex 页数
    */
    function listRole($pageIndex) {
        $.ajax({
            type:'post',
            url:'${baseURL}/role/role!listRoleByUserView.action',
            data:{
                'userId':'${param.userId}',
                'pageSize':10
            },
            dataType:'json',
            success:function(result) {
                if ('0' == result.code) {
                    var roleList = new ListTable({
                        title:[
                            {width:"30%", name:"roleName", showName:"角色名称"},
                            {width:"70%", name:"roleDescription", showName:"角色描述"}
                        ],
                        data:result.data.pagerData,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    roleList.createList("role_list");
                    var rolePager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listRole"});
                    rolePager.createPageBar("rolePage");
                } else {
                    $("#tip").error({title:"错误",content:result.desc,button:[{name:"确定",event:"_hideMsg()"}]});
                }
            }
        });
    }

</script>

</body>
</html>
