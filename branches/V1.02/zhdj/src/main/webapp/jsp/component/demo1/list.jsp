<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
    <jsp:param name="permission" value="ROLE_user"></jsp:param>
    <jsp:param name="mustPermission" value="ROLE_user"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-列表</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/brand.js"></script>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
        <!-- .ziti { font-size: 16px; } -->
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
            <!--标题 end-->


        </tr>

    </table>
</div>

<!--标签选项卡 end-->

<form action="${baseURL}/test1/test1!testList.action" method="post" id="id_form_search">
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
                                        <td width="140"><input class="form120px" name="" type="text"/></td>

                                        <td><input class="btnQuery" name="input" type="button" value="搜索"/></td>
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
<!--标题 end-->
<div class="pageDown"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
<!--翻页 end-->

<div class="operation">
    <input class="btnS4" type="button" value="新增权限" onclick="javascript:window.location.href='新增权限.html'"/>


</div>

<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
    });
    function doSearch($pageIndex) {
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {

                                checkableColumn:"haha",
                                title:[
                                    {width:"10%", name:"haha", showName:"看看", isCheckColunm:true},
                                    {width:"20%", name:"testName", showName:"名称"},
                                    {width:"25%", name:"testDate", showName:"日期"},
                                    {width:"25%", name:"testNumber", showName:"数字"},
                                    {width:"20%", name:"haha2", showName:"看看2", isOperationColumn:true},
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查询", permission:"xx", event:function (index, content) {
                                        alert(content.testName);
                                    }}
                                ],
                                trStyle:["trWhite"],
                                trevent:{click:function (index, content) {
                                    alert(content.haha);
                                }}
                            });
                    list1.createList("test");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("idPage");
                } else {
                    $("#tip").error({title:"错误",content:result.desc,button:[{name:"确定",event:"_hideMsg()"}]});
                }
            },
            error:function () {
            }
        });
    }
</script>
<div id="test"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar" permission="">
    <!--筛选 end-->
    <div class="pageDown" id="idPage">

    </div>
    <!--翻页 end-->
    <div class="operation" >
        <input class="btnS4" type="button" value="新增权限" onclick="javascript:window.location.href='新增权限.html'"/>


    </div>
    <!--对表格数据的操作 end-->
</div>
<select id="ade" dictType="test" dictItem="" defaultValue="" >
</select>
<select id="edf" parentDict="ade" defaultValue="">
</select>
<select id="eee" parentDict="edf" defaultValue="">
</select>
<input type="checkbox" name="abc" dict="test" onclick="alert('a')">
<input type="radio" name="def" dict="test">
<!--主体部分 end-->

</body>

</html>
