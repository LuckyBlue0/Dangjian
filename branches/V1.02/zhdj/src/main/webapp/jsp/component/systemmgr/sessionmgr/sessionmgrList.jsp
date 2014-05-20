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
    <title>关键字管理</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/brand.js"></script>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<!--头部 end-->

<!--公告 end-->
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">会话管理</h2>
            </div>
        </tr>
    </table>
</div>

<form action="${baseURL}/sessionmgr/sessionmgr!listSession.action" method="post" id="session_search">
<div class="text_bg">
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
                                        <td width="60" height="30">会话ID：</td>
                                        <td width="140"><input class="form120px" name="searchValue.sessionId" type="text"/></td>
                                        <td width="60" height="30">用户名：</td>
                                        <td width="140"><input class="form120px" name="searchValue.userName" type="text"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询"
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
    <div id="session_list"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>

    <!--对表格数据的操作 end-->
</div>
    </div>
</form>
<!--角色信息搜索表单 end-->




<!--主体部分 end-->

<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
    });

    function closeSession($id) {
        $.ajax({
            url:"${baseURL}/sessionmgr/sessionmgr!removeSession.action",
            type:"post",
            dataType:"json",
            data:{
                "id":$id
            },
            success:function (result) {
                alert(result.desc);
                doSearch(1);
            },
            error:function () {
                alert("通讯错误");
            }
        });
    }

    function doSearch($pageIndex) {
        $('#session_search').ajaxSubmit({
            data:{
                'page':$pageIndex
            },
            dataType:'json',
            type:'post',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"sessionId",
                                title:[
                                    {width:"2%", name:"", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"sessionId", showName:"会话ID"},
                                    {width:"20%", name:"lastRequest", showName:"最后请求时间"},
                                    {width:"20%", name:"userName", showName:"用户"},
                                    {width:"30%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pagerData,
                                operations:[
                                    {name:"删除", permission:"", event:function (index, content) {
                                        closeSession(content.sessionId);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("session_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("downIdPage");
                } else {
                    $("#tip").error({title:"错误", content:result.desc, button:[
                        {name:"确定", event:"_hideMsg()"}
                    ]});
                }
            },
            error:function () {
            }
        });
    }

</script>

</body>

</html>
