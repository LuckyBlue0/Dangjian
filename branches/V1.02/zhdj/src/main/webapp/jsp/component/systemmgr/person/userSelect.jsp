<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户选择</title>
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
                <h2 class="icon1">用户选择</h2>
            </div>
            <!--标题 end-->
        </tr>
    </table>
</div>

<!--标签选项卡 end-->

<form action="${baseURL}/person/person!listUserByPersonEdit.action?personId=${param.personId}" method="post" id="user_search">
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
                                        <td width="60" height="30">用户名称：</td>
                                        <td width="140"><input class="form120px" name="searchValue.userName" type="text"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="搜索"
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
<!--标题 end-->
<div class="pageDown" id="topIdPage">
    <a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
          <input class="form24px border999" type="text" value="1"/>
          页</span>
</div>
<!--翻页 end-->

<!--工具栏 end-->
<script type="text/javascript">
    var users = ''; //选中的用户id值
    var delUsers = ''; //未选中的用户id值
    $(document).ready(function () {
        doSearch(1);
    });
    function doSearch($pageIndex) {
        $('#user_search').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"userId",
                                title:[
                                    {width:"10%", name:"", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"userName", showName:"用户名称"}
                                ],
                                data:result.data.userPagerData,
                                operations:[],
                                trStyle:["trWhite"]
                            });
                    list1.createList("user_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");
                    // 将角色已有的用户选中
                    $.each(result.data.userPagerData, function(index, record){
                        if (record['selectByPerson']=="0") {
                            $('[name=ids]:checkbox:eq(' + index + ')').prop('checked',true);
                        }
                    });
                } else {
                    $("#tip").error({title:"错误",content:result.desc,button:[{name:"确定",event:"_hideMsg()"}]});
                }
            },
            error:function () {
            }
        });
    }

</script>
<div id="user_list"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="pageDown" id="downIdPage">
    </div>
    <div class="operation">
        <input class="btnQuery" type="button" value="全选" id="selectAllBtn" />
        <input class="btnQuery" type="button" value="反选" id="selectRevBtn" />
        <input class="btnQuery" type="button" value="确定选择" id="confirmBtn" />
    </div>
    <!--翻页 end-->
    <!--对表格数据的操作 end-->
</div>
<!--主体部分 end-->
<script type="text/javascript">
    $('#confirmBtn').click(function(){
        $('[name=ids]:checkbox').each(function(){
            if ($(this).prop('checked')) {
                users += $(this).val()+",";
            } else {
                delUsers += $(this).val()+",";
            }
        });
        trans();
    });

    $('#selectAllBtn').click(function(){
        $('[name=ids]:checkbox').prop('checked',true);
    });

    $('#selectRevBtn').click(function(){
        $('[name=ids]:checkbox').each(function(){
            $(this).prop('checked', !$(this).prop('checked'));
        });
    });

    function trans() {
        window.opener.document.getElementById('userIds').value = users;
        window.opener.document.getElementById('delUserIds').value = delUsers;
        window.close();
    }
</script>

</body>

</html>
