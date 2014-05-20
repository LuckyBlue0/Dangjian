<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsTypeCreateWay,newsTypeUseStatus"></jsp:param>
    <jsp:param name="permission" value="newsTypeManager,newsTypeSearch,newsTypeView,newsTypeAdd,newsTypeEdit,newsTypeDel"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新闻类型管理</title>
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

<body  style="background: #f6ebe5;">
<!--头部 end-->


<!--公告 end-->


<!-- 新闻类型信息搜索表单 -->
<form action="${baseURL}/basis/newsType/newsTypeAction!ajaxSearch.action" method="post" id="form_search">
<div class="searchWrap">
            <div class="title clearfix">
                <h2 class="icon1">新闻类型管理</h2>
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
                                        <td width="60" height="30">栏目名称：</td>
                                        <td width="140"><input type="text" name="searchValue.name" /></td>
                                        <td width="60" height="30">父栏目：</td>
                                        <td width="140"><select name="searchValue.parentId" id="parentNewsType"/></td>
                                        <td width="60" height="30">创建方式：</td>
                                        <td width="140"><select name="searchValue.createType" dictType="newsTypeCreateWay" defaultTip="全部"/></td>
                                        <td width="60" height="30">使用状态：</td>
                                        <td width="140"><select name="searchValue.useStatus" dictType="newsTypeUseStatus" defaultTip="全部"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询" permission="newsTypeSearch"
                                                   onclick="javascript:doSearch(1);"/>
                                           <input class="btnQuery" type="button" permission="newsTypeAdd" value="新增" onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
                                           <input class="btnQuery" type="button" permission="newsTypeDel" value="删除" onclick="javascript:_doDel('newsType_list');doSearch(1);"/>
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
    <div id="newsType_list" delUrl="${baseURL}/basis/newsType/newsTypeAction!ajaxBatchDelete.action"></div> 
<div id="newsType_list"></div>
<div id="tip"></div>

<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>
    <!--翻页 end-->
</div>
    </div>
</form>



<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
        init();
    });
    
    function init(){
    	initParentNewsType();
    }
    
    function initParentNewsType(){
       $.ajax({
            url:'${baseURL}/basis/newsType/newsTypeAction!ajaxNewsTypeList.action',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var newsTypeList = result.data.newsTypeList;
                    if(newsTypeList != null){
                    	var html = "<option value=\"\">全部</option>";
                    	for(var i=0;i<newsTypeList.length;i++){
                    		var newsType = newsTypeList[i];
                    		html+="<option value="+newsType.newsTypeId+">"+newsType.name+"</option>";
                    	}
                    	$("#parentNewsType").append(html);
                    }
                }
            }
        });
    }
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
                                checkableColumn:"newsTypeId",
                                title:[
                                    {width:"2%", name:"newsTypeId", showName:"", isCheckColunm:true,
                                    	checkAble:function(index,content){
                                    		if(content.createType == 0 || content.useStatus==1){
                                    			return false;
                                    		}else{
												return true;
											}
                                    	}
                                    },

                                    {width:"10%", name:"name", showName:"栏目名称"},
                                    {width:"10%", name:"parentName", showName:"父栏目名称"},
                                    {width:"15%", name:"createTypeDesc", showName:"创建方式"},
                                    {width:"15%", name:"useStatusDesc", showName:"使用状态"},
                                    {width:"10%", name:"orderValue", showName:"栏目排序"},
                                    {width:"20%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查看", permission:"newsTypeView", event:function (index, content) {
                                        window.location.href = 'view.jsp?newsTypeId=' + content.newsTypeId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"newsTypeEdit",  condition:function (index, content) {
											return content.createType != 0;
											},event:function (index, content) {
                                        window.location.href = 'edit.jsp?newsTypeId=' + content.newsTypeId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
	                                {name:"删除", permission:"newsTypeDel", condition:function (index, content) {
											return content.useStatus == 0 && content.createType != 0;
											},event:function (index, content) {
	                                    _doSignlDel("newsType_list", content.newsTypeId);
	                                }}
                                    
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("newsType_list");
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

</script>

<!--主体部分 end-->

</body>

</html>
