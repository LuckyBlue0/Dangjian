<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="orgManage,orgAdd,orgEdit,orgDel"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="userStatus,personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>机构列表</title>

    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
      <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <style type="text/css">
    </style>
    <script type="text/javascript">
    </script>
</head>

<body id="mybody">
<!--头部 end-->

<div>
    <form action="" method="post" id="user_search">
    <input type="hidden" name="orgId" id="orgId" value="${param.id}"/>
    <input type="hidden" name="orgName" id="orgName" value="${param.name}"/>
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
                                            <td width="60" height="30">机构名称：</td>
                                            <td width="140"><input class="form120px" id="searchUserName" type="text" /></td>
                                            <td>
                                            <input class="btnQuery" type="button" value="查询" permission="" onclick="javascript:doSearch(1);"/>
                                            <input class="btnQuery" type="button" value="新增" permission="orgAdd" onclick="javascript:doOrgAdd()"/>&nbsp;
                                            <input class="btnQuery" type="button" value="删除" permission="orgDel" onclick="javascript:_doDel('user_list');doSearch(1);"/></td>
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
    
    <div class="operation" id="id_user_toolbar" style="display: none">
        
    </div>
    <div id="user_list" delUrl="${baseURL}/orgManage/orgManageAction!ajaxBatchDelete.action"></div>
    <div class="page" id="topIdPage"></div>
    </div>
    </form>
</div>

<script type="text/javascript">
    function doSearch(pageIndex) {
    	$("#id_user_toolbar").css("display", "block");
        $.ajax({
            url:'${baseURL}/orgManage/orgManageAction!ajaxSearch.action',
            type:'post',
            data:{
            	'page':pageIndex,
            	'searchValue.parentId':$('#orgId').val(),
                'searchValue.organizationName':$('#searchUserName').val()
            },
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var userList = new ListTable({
                        checkableColumn:"id",
                        title:[
                            {width:"5%", name:"", showName:"", isCheckColunm:true},
                            {width:"45%", name:"organizationName", showName:"机构名称"},
                            {width:"10%", name:"sort", showName:"排序"},
                            {width:"40%", name:"operation", showName:"操作", isOperationColumn:true}
                        ],
                        data:result.data.pageData,
                        operations:[
                            {name:"查看", permission:"", event:function (index, content) {
                                window.location.href = 'orgView.jsp?id=' + content.id;
                            }},
                            {name:"修改", permission:"orgEdit", event:function (index, content) {
                                window.location.href = 'orgEdit.jsp?id=' + content.id;
                            }}
                        ],
                        trStyle:["trWhite"]
                    });
                    userList.createList("user_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");
                    window.parent.parent._resetFrameHeight(1);
                }
            }
        });
    }


    $(document).ready(function () {
      doSearch(1);
    });

    function cleanForm() {
        $("#user_search :input").not(":button,:reset,:submit,:hidden").val("");
    }
    function doOrgAdd(){
    	var id=$('#orgId').val();
    	var name=$("#orgName").val();
    	window.location.href="orgAdd.jsp?parentId="+id+"&name="+name;
    }
    function doReflash(){
    	parent.location.reload();
    }

</script>
</body>

</html>
