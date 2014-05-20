<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人员管理</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/brand.js"></script>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
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
                <h2 class="icon1">人员管理</h2>
            </div>
        </tr>
    </table>
</div>

<!-- 人员信息搜索表单 -->
<form action="${baseURL}/person/person!listPerson.action" method="post" id="person_search">
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
                                        <td width="60" height="30">人员名称：</td>
                                        <td width="140"><input class="form120px" name="searchValue.personName" type="text"/></td>
                                        <td width="40" height="30">性别：</td>
                                        <td width="70"><select name="searchValue.sex" dictType="personSex" defaultValue=""/></td>
                                        <td width="40" height="30">年龄：</td>
                                        <td width="80"><input class="form24px" name="searchValue.minAge"/>-<input class="form24px" name="searchValue.maxAge"/></td>
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
<!--人员信息搜索表单 end-->

<div class="pageDown" id="topIdPage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>
<!--翻页 end-->

<div class="operation">
    <input class="btnS4" type="button" value="新增人员" onclick="javascript:window.location.href='personAdd.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
    <input class="btnS4" type="button" value="删除" onclick="javascript:_doDel('person_list');doSearch(1);" />
</div>

<div id="person_list" delUrl="${baseURL}/person/person!delPerson.action"></div>
<div id="tip"></div>

<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="pageDown" id="downIdPage">

    </div>
    <!--翻页 end-->
    <div class="operation" >
        <input class="btnS4" type="button" value="新增人员" onclick="javascript:window.location.href='personAdd.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
        <input class="btnS4" type="button" value="删除" onclick="javascript:_doDel('person_list');doSearch(1);" />
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
     * 查询人员信息分页列表
     * @param $pageIndex
     */
    function doSearch($pageIndex) {
        $('#person_search').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"personId",
                                title:[
                                    {width:"10%", name:"", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"personName", showName:"人员名称"},
                                    {width:"10%", name:"sexDesc", showName:"性别"},
                                    {width:"10%", name:"age", showName:"年龄"},
                                    {width:"30%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.personPagerData,
                                operations:[
                                    {name:"查看", permission:"", event:function (index, content) {
                                        window.location.href = 'personView.jsp?personId='+content.personId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"", event:function (index, content) {
                                        window.location.href = 'personEdit.jsp?personId='+content.personId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"删除", permission:"", event:function (index, content) {
                                        _doSignlDel('person_list',content.personId);
                                        doSearch(1);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("person_list");
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

</script>

</body>

</html>
