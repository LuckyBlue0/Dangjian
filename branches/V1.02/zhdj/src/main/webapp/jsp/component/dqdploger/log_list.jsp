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
    <title>用户日志-列表</title>
    <link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>


    <style type="text/css">
        <!--
        .ziti {
            font-size: 16px;
        }

        -->
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
                <h2 class="icon1">日志管理</h2>
            </div>
            <!--标题 end-->
        </tr>
    </table>
</div>

<!--标签选项卡 end-->

<form action="${baseURL}/dqdploger/dqdploger!ajaxSearch.action" method="post" id="id_form_search">
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
                                        <td  >操作时间：</td>
                                        <td >
                                            <input class="form120px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}',autoPickDate:true});" name="searchValue.startDate" id="startTime" type="text" value=""/>-
                                            <input class="form120px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d',autoPickDate:true});" name="searchValue.endDate" type="text" value="" id="endTime"/>
                                        </td>
                                    
                                        <td >操作账号：</td>
                                        <td ><input class="form120px" name="searchValue.operationId" type="text" value=""/></td>
                                        <td>操作模块：</td>
                                        <td ><input class="form120px" name="searchValue.modelName" type="text" value=""/></td>
                                        <td><input class="btnQuery" name="input" type="button" value="搜索" onclick="doSearch(1)"/></td>
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
    <div id="test" delUrl="${baseURL}/demomodel/demomodel!batchDelete.action"
     editUrl="${baseURL}/demomodel/demomodel!testList.action">
</div>

<!--表格 end-->

<div class="toolBar" permission="">
    <!--筛选 end-->
    <div class="page" id="idPage">

    </div>


    </div>
    <!--对表格数据的操作 end-->
</div>
    </div>
</form>
<!--标题 end-->




<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
//        _showTip("hahaha1");
    });
    function test() {
        alert("我是测试文本");
    }
    function doSearch($pageIndex) {
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            data:{page:$pageIndex},
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                title:[
                                    {width:"10%", name:"operationId", showName:"操作账号"},
                                    {width:"15%", name:"createTime", showName:"操作时间"},
                                    {width:"15%", name:"modelName", showName:"操作模块"},
                                    {width:"10%", name:"operationTypeDict", showName:"操作类型"},
                                    {width:"5%", name:"operationResult", showName:"结果"},
                                    {width:"37%", name:"operationDesc", showName:"结果描述", length:50},
                                    {width:"8%", name:"", showName:"操作", isOperationColumn:true}
                                ],
                                operations:[
                                    {name:"详情", permission:"", event:function (index, content) {
                                        document.location.href ="log_view.jsp?id="+content.logId;
                                    }}
                                ],
                                data:result.data.pageData,
                                trStyle:["trWhite"]
                            });
                    list1.createList("test");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("idPage");
                } else {
                    $("#tip").error({title:"信息提示层", content:result.desc, button:[
                        {text:"确定", events:"test"},
                        {text:"取消", events:"test"}
                    ]});
                }
            },
            error:function () {
            }
        });
    }
    function doLoginTest() {
        var formSubmit = document.getElementById("id_form_search");
        formSubmit.action = "";
        formSubmit.submit();
    }
</script>



</body>

</html>
