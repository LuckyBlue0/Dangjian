<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="photowallStatus,photowallType"></jsp:param>
    <jsp:param name="permission" value="photowallList,photowallAdd,photowallUpdate,photowallDel,photowallView,photowallEnable,photowallDisable"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>留影墙</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;" >
<!--头部 end-->


<!--公告 end-->
<form action="${baseURL}/photowall/photowallAction!ajaxSearch.action" method="post" id="form_search">
<div class="searchWrap">
            <div class="title clearfix">
                <h2 class="icon1">留影墙管理</h2>
            </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td class="searchLeft"></td>
            </tr>
            </tbody></table>
 </div>

<!-- 新闻类型信息搜索表单 -->
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
                                        <td width="50">标题：</td>
                                        <td width="200">
                                        	<input type="text" name="searchValue.title" />
                                        </td>
                                        <td width="50">类型：</td>
                                        <td width="200">
                                        	<select name="searchValue.type" dictType="photowallType" defaultTip="全部"/>
                                        </td>
                                        <td width="50">状态：</td>
                                        <td width="200"><select name="searchValue.status" dictType="photowallStatus" defaultTip="全部"/></td>
                                        <td>
                                        	<input class="btnQuery" name="search" type="button" value="查询" permission="photowallList" onclick="javascript:doSearch(1);"/>
                                        	<!-- 
                                            <input class="btnQuery" type="button" permission="photowallAdd" value="新增" onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
                                   			<input class="btnQuery" type="button" permission="photowallDel" value="删除" onclick="javascript:_doDel('photowall_list');doSearch(1);"/
                                   			 -->
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
    <div id="photowall_list" delUrl="${baseURL}/news/photowallInfo/photowallInfoAction!ajaxBatchDelete.action"></div>
    <div id="tip"></div>
    <div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>
    <!--翻页 end-->
</div>
    </div>
</form>
<!--角色信息搜索表单 end-->

<!--翻页 end-->



<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
    });
    function doSearch($pageIndex) {
        $('#form_search').ajaxSubmit({
            data:{
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                             	checkableColumn:"id",
                                title:[
                                    {width:"2%", name:"id", showName:"", isCheckColunm:true,
                                    	checkAble:function(index,content){
                                    		if(content.status == 1){
                                    			return false;
                                    		}else{
												return true;
											}
                                    	}
                                    },
                                    {width:"28%", name:"title", showName:"标题",length:34},
                                    {width:"15%", name:"_type_desc", showName:"类型"},
                                    {width:"7%", name:"userName", showName:"发布人"},
                                    {width:"15%", name:"createTime", showName:"发布时间"},
                                    {width:"7%", name:"_status_desc", showName:"状态"},
                                    {width:"28%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查看", permission:"photowallView", event:function (index, content) {
                                    	window.location.href = '${baseURL}/photowall/photowallAction!photowallView.action?id='+content.id;
                                        //window.location.href = 'photowall/photowall_list.jsp?id=' + content.id+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"编辑", permission:"photowallEdit", event:function (index, content) {
                                    	window.location.href = 'edit.jsp?id=' + content.id+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"启用", permission:"photowallEnable", condition:function (index, content) {
											return content.status == 1;
											},event:function (index, content) {
                                    	updateEnableOrDisable(content.id,0);
                                    }},
                                    {name:"禁用", permission:"photowallDisable", condition:function (index, content) {
											return content.status == 0;
											},event:function (index, content) {
                                    	updateEnableOrDisable(content.id,1);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("photowall_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
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

 
 function updateEnableOrDisable(id,status){
	   if(confirm(status == 0 ? "确定启用?" : "确定禁用?")){
	       $.ajax({
	    	   url:"${baseURL}/photowall/photowallAction!updateEnableOrDisable.action",
	    	   data:{"tbPhotoWallPO.id":id,"tbPhotoWallPO.status":status},
	    	   type:"post",
	           dataType:'json',
	           success:function(result) {
	               if ("0"==result.code) {
	                   alert(result.desc);
	                   window.location.href="${baseURL}/jsp/component/photowall/list.jsp";
	               } else {
	                   alert(result.desc);
	               }
	           },
	           error:function(){
	               alert('操作失败，请稍候再试');    
	           } 
	       });
       }
 }
</script>
</body>
</html>
