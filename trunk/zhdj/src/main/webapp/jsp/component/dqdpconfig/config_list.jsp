<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
    <jsp:param name="permission" value="config_edit,config_del"></jsp:param>
    <jsp:param name="mustPermission" value="ROLE_user"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>系统配置-列表</title>
    <link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>

</head>

<body style="background: #f6ebe5;">
<!--头部 end-->


<!--公告 end-->
<div class="searchWrap">
    <div class="title clearfix">
                <h2 class="icon1">配置管理</h2>
    </div>
</div>

<!--标签选项卡 end-->
<form action="${baseURL}/dqdpconfig/config!listConfig.action" method="post" id="id_form_search">
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
                                        <td width="60" height="30">模块名称：</td>
                                        <td width="140"><input class="form120px" keySearch="doSearch('1')" name="searchValue.componentName" type="text" value=""/></td>
                                        <td width="60" height="30">配置项名：</td>
                                        <td width="140"><input class="form120px" name="searchValue.configName" type="text" value=""/></td>
                                        <td><input class="btnQuery" name="input" type="button" value="查询" onclick="doSearch(1)"/>
                                        <input class="btnQuery" type="button" value="新增配置" onclick="javascript:window.location.href='config_add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
                                        <input class="btnQuery" type="button" value="删除配置" onclick="javascript:_doDel('id_list_config')"/>
                                        </td>
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
    <div id="id_list_config" delUrl="${baseURL}/dqdpconfig/config!delete.action"
     editUrl="${baseURL}/jsp/component/dqdpconfig/config_edit.jsp">
</div>

<!--表格 end-->

<div class="toolBar" permission="">
    <!--筛选 end-->
    <div class="page" id="idPage">

    </div>
    <!--翻页 end-->
    <!--对表格数据的操作 end-->
</div>
    </div>
</form>




<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
//        _showTip("hahaha1");
    });
    function doSearch($pageIndex) {
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            data:{page:$pageIndex},
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"configId",
                                title:[
                                    {width:"2%", name:"configId", showName:"配置项ID",isCheckColunm:true},
                                    {width:"15%", name:"configName", showName:"配置项名称",length:20},
                                    {width:"55%", name:"configValue", showName:"配置项值",length:70},
                                    {width:"10%", name:"componentName", showName:"模块名称"},
                                    {width:"15%", name:"operationDesc", showName:"结果描述", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"编辑", permission:"", event:function (index, content) {
                                        _doSignlEdit("id_list_config", content.configId);
                                    }},
                                    {name:"删除", permission:"", event:function (index, content) {
                                        _doSignlDel("id_list_config", content.configId);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("id_list_config");
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
</script>



</body>

</html>
