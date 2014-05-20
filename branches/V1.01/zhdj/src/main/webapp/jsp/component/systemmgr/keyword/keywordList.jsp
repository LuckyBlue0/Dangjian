<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="keywordStatus"></jsp:param>
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
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
       <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <style type="text/css">
        input.text {
            margin-bottom: 12px;
            width: 95%;
            padding: .4em;
        }
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
                <h2 class="icon1">关键字管理</h2>
            </div>
        </tr>
    </table>
</div>

<form action="${baseURL}/keyword/keyword!ajaxSearch.action" method="post" id="keyword_search" onsubmit="return false;">
    <input type="hidden" name="xx">
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
                                        <td width="60" height="30">关键字：</td>
                                        <td width="140"><input class="form120px" name="searchValue.keyword" type="text"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询"
                                                   onclick="javascript:doSearch(1);"/>
                                           <input class="btnQuery" type="button" value="新增关键字" onclick="javascript:toAdd()"/>
                                           <input class="btnQuery" type="button" value="删除" onclick="javascript:_doDel('keyword_list');"/>
                                           <input class="btnQuery" type="button" value="导入关键字" onclick="javascript:importFile()"/></td>
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
    <div id="keyword_list" delUrl="${baseURL}/keyword/keyword!batchDeleteTbDqdpKeywordPO.action"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>
    <!--翻页 end-->
    <!--对表格数据的操作 end-->
</div>
    </div>
</form>
<!--角色信息搜索表单 end-->

<!--翻页 end-->



<!--主体部分 end-->

<div id="keyword_dialog" title="新增关键字">
    <form id="keyword_add" action="${baseURL}/keyword/keyword!ajaxAdd.action">
        <fieldset>
            <label>关键字</label>
            <input type="text" name="tbDqdpKeywordPO.keyword" class="text ui-widget-content ui-corner-all"/>
            <label>状态</label>
            <select name="tbDqdpKeywordPO.status" dictType="keywordStatus" defaultValue="0" width="95%"/>
            <input type="hidden" name="tbDqdpKeywordPO.keywordId" value=""/>
        </fieldset>
    </form>
</div>

<div id="file_dialog" title="导入关键字">
    <form id="file_form" action="${baseURL}/keyword/keyword!importKeywordByFile.action" type="post" enctype="multipart/form-data">
        <fieldset>
            <label>文件(txt或xls)</label>
            <br/>
            <input type="file" name="keywordFile"/>
        </fieldset>
    </form>
</div>

<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
        $("#keyword_dialog").dialog({
            autoOpen:false,
            height:300,
            width:350,
            modal:true,
            buttons:{
                '保存':function () {
                    $("#keyword_add").ajaxSubmit({
                        dataType:"json",
                        type:"post",
                        success:function (result) {
                            if ("0" == result.code) {
                                alert(result.desc);
                                $("#keyword_dialog").dialog('close');
                                doSearch(1);
                            } else {
                                alert(result.desc);
                            }
                        },
                        error:function () {
                            alert("通讯错误");
                        }
                    });
                },
                '取消':function () {
                    $(this).dialog('close');
                }
            },
            close:function () {
            }
        });

        $("#file_dialog").dialog({
            autoOpen:false,
            height:300,
            width:350,
            modal:true,
            buttons:{
                '导入':function () {
                    if (validateFileType()) {
                        $("#file_form").ajaxSubmit({
                            dataType:"json",
                            type:"post",
                            success:function (result) {
                                if ("0" == result.code) {
                                    alert(result.desc);
                                    $("#file_dialog").dialog('close');
                                    doSearch(1);
                                } else {
                                    alert(result.desc);
                                }
                            }
                        });
                    } else {
                        alert("文件类型不正确");
                    }
                },
                '取消':function () {
                    $(this).dialog('close');
                }
            },
            close:function () {
            }
        });
    });

    function toAdd() {
        cleanForm();
        $("#keyword_dialog").dialog("open");
    }

    function toEdit(content) {
        beforeEidt(content);
        $("#keyword_add").attr("action", "${baseURL}/keyword/keyword!updateTbDqdpKeywordPO.action");
        $("#keyword_dialog").dialog("open");
    }

    function beforeEidt(content) {
        $("#keyword_add [name=tbDqdpKeywordPO\\.keyword]").val(content.keyword);
        $("#keyword_add [name=tbDqdpKeywordPO\\.status]").val(content.status);
        $("#keyword_add [name=tbDqdpKeywordPO\\.keywordId]").val(content.keywordId);
    }

    function cleanForm($formId) {
        $("#" + $formId + " :input").not(":button :reset :submit").val("");
    }

    function disableKeyword(content, status) {
        beforeEidt(content);
        $("#keyword_add [name=tbDqdpKeywordPO\\.status]").val(status);
        $("#keyword_add").ajaxSubmit({
            url:"${baseURL}/keyword/keyword!updateTbDqdpKeywordPO.action",
            type:"post",
            dataType:"json",
            success:function (result) {
                alert(result.desc);
                doSearch(1);
            },
            error:function () {
                alert("通讯错误");
            }
        });
    }

    function importFile() {
        $("#file_dialog").dialog("open");
    }

    function doSearch($pageIndex) {
        $('#keyword_search').ajaxSubmit({
            data:{
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"keywordId",
                                title:[
                                    {width:"2%", name:"", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"keyword", showName:"关键字"},
                                    {width:"20%", name:"statusDesc", showName:"状态"},
                                    {width:"50%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"修改", permission:"", event:function (index, content) {
                                        toEdit(content);
                                    }},
                                    {name:"删除", permission:"", event:function (index, content) {
                                        _doSignlDel("keyword_list", content.keywordId);
                                    }},
                                    {name:"启用", permission:"", condition:function (index, content) {
                                        if (content.status == "1") return true;
                                    }, event:function (index, content) {
                                        disableKeyword(content, 0);
                                    }},
                                    {name:"禁用", permission:"", condition:function (index, content) {
                                        if (content.status == "0") return true;
                                    }, event:function (index, content) {
                                        disableKeyword(content, 1);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("keyword_list");
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

    function validateFileType() {
        var fileType = $("[name=keywordFile]").val().substring($("[name=keywordFile]").val().lastIndexOf(".") + 1).toLowerCase();
        if (fileType == "txt" || fileType == "xls")
            return true;
        else
            return false;
    }

</script>

</body>

</html>
