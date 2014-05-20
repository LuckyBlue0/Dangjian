<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>权限管理</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
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
<!--头部 end-->


<!--公告 end-->
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">权限管理</h2>
            </div>
        </tr>
    </table>
</div>

<!-- 角色信息搜索表单 -->
<form action="${baseURL}/permissionmgr/permissionmgr!listPermission.action" method="post" id="per_search">
    <div class="searchWrap">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="searchLeft"></td>
                <td class="searchBg">
                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="60" height="30">权限名称：</td>
                                        <td width="140"><input class="form120px" name="searchValue.perName" type="text"/></td>
                                        <td width="60" height="30">权限代码：</td>
                                        <td width="140"><input class="form120px" name="searchValue.perCode" type="text"/></td>
                                        <td><input class="btnQuery" type="button" value="搜索"
                                                   onclick="javascript:doSearch(1);"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <td class="searchRight"></td>
            </tr>
            <tr>
                <td class="searchButtomLeft"></td>
                <td class="searchButtom"></td>
                <td class="searchButtoRight"></td>
            </tr>
        </table>
    </div>
</form>
<!--角色信息搜索表单 end-->

<div class="pageDown" id="topIdPage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
<!--翻页 end-->

<div class="operation">
    <input class="btnS4" type="button" value="新增权限" onclick="javascript:window.location.href='permissionAdd.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
    <input class="btnS4" type="button" value="删除" onclick="javascript:_doDel('per_list')" />
</div>

<div id="per_list" delUrl="${baseURL}/permissionmgr/permissionmgr!delPermission.action"></div>
<div id="tip"></div>

<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="pageDown" id="downIdPage">

    </div>
    <!--翻页 end-->
    <div class="operation" >
        <input class="btnS4" type="button" value="新增权限" onclick="javascript:window.location.href='permissionAdd.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
        <input class="btnS4" type="button" value="删除" onclick="javascript:_doDel('per_list');doSearch(1);" />
    </div>
    <!--对表格数据的操作 end-->
</div>
<!--主体部分 end-->

<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
    });

    /**
    * 查询权限分页列表
    * @param $pageIndex
    */
    function doSearch($pageIndex) {
        $('#per_search').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"permissionId",
                                title:[
                                    {width:"2%", name:"", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"permissionName", showName:"权限名称"},
                                    {width:"10%", name:"permissionCode", showName:"权限代码"},
                                    {width:"30%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pagerData,
                                operations:[
                                    {name:"查看", permission:"", event:function (index, content) {
                                        window.location.href = 'permissionView.jsp?perId='+content.permissionId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"", event:function (index, content) {
                                        window.location.href = 'permissionEdit.jsp?perId='+content.permissionId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"删除", permission:"", event:function (index, content) {
                                        _doSignlDel('per_list',content.permissionId);
                                        doSearch(1);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("per_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");
                } else {
                    $("#tip").error({title:"错误",content:result.desc,button:[{name:"确定",event:"_hideMsg()"}]});
                }
            },
            error:function () {
            }
        });
    }

    function delPermission(delId) {
        if (confirm('确定删除该权限？')) {
            $.ajax({
                url:'${baseURL}/permissionmgr/permissionmgr!delPermission.action',
                type:'post',
                data:{'ids':delId},
                dataType:'json',
                success:function(result){
                    alert(result.desc);
                    doSearch(1);
                }
            });
        }
    }

</script>

</body>

</html>
