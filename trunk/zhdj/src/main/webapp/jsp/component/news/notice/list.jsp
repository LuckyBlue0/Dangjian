<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsInfoStatus,newsInfoBuyTop"></jsp:param>
    <jsp:param name="permission" value="noticeAfficheSearch,noticeAfficheView,noticeAfficheAdd,noticeAfficheEdit,noticeAfficheDel"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>通知公告管理</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
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
                <h2 class="icon1">通知公告管理</h2>
            </div>
        </tr>
    </table>
</div>

<!-- 新闻类型信息搜索表单 -->
<form action="${baseURL}/news/newsinfo/newsInfoAction!ajaxSearch.action" method="post" id="form_search">
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
                                        <td width="60" height="30">标题：</td>
                                        <td width="140">
                                        	<input type="hidden" name="searchValue.newsInfoType" value="${param.type}"/>
                                        	<input type="text" name="searchValue.title" />
                                        </td>
                                        <td width="80" height="30">所属组织：</td>
                                        <td width="180">
                                        	<input type="text" id="parentName" readonly="readonly"/>&nbsp;<img src="${baseURL}/image/ss_bt.png" onclick="selectParent()" />
                                        	<input type="hidden" id="parentId" name="searchValue.organizationId"/>
                                        </td>
                                        <td width="60" height="30">发布状态：</td>
                                        <td width="140"><select name="searchValue.status" dictType="newsInfoStatus" defaultTip="全部"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询" permission="noticeAfficheSearch"
                                                   onclick="javascript:doSearch(1);"/>
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
</form>
<!--角色信息搜索表单 end-->

<div class="pageDown" id="topIdPage"></div>
<!--翻页 end-->

    <div class="operation">
       <input class="btnS4" type="button" permission="noticeAfficheAdd" value="新增" onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token+'&newsInfoType=${param.type}'"/>
       <input class="btnS4" type="button" permission="noticeAfficheDel" value="删除" onclick="javascript:_doDel('newsInfo_list');doSearch(1);"/>
   </div>

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
                             	checkableColumn:"newsInfoId",
                                title:[
                                    {width:"10%", name:"newsInfoId", showName:"", isCheckColunm:true,
                                    	checkAble:function(index,content){
                                    		if(content.status == 1){
                                    			return false;
                                    		}else{
												return true;
											}
                                    	}
                                    },

                                    {width:"20%", name:"title", showName:"标题"},
                                    {width:"15%", name:"organizationName", showName:"所属组织"},
                                    {width:"10%", name:"statusDesc", showName:"发布状态"},
                                    {width:"10%", name:"buyTopDesc", showName:"是否置顶"},
                                    {width:"10%", name:"pushTime", showName:"发布时间"},
                                    {width:"10%", name:"createTime", showName:"创建时间"},
                                    {width:"20%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"点击预览", permission:"noticeAfficheView", event:function (index, content) {
                                        window.location.href = 'view.jsp?newsInfoId=' + content.newsInfoId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"noticeAfficheEdit", event:function (index, content) {
                                        window.location.href = 'edit.jsp?newsInfoId=' + content.newsInfoId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
	                                {name:"删除", permission:"noticeAfficheDel", condition:function (index, content) {
											return content.status == 0;
											},event:function (index, content) {
	                                    _doSignlDel("newsInfo_list", content.newsInfoId);
	                                }}
                                    
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("newsInfo_list");
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

 function selectParent(){
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp","_blank","width=800,height=400");
 } 
 
 function selectOrg(id,name){
	document.getElementById("parentId").value = id;
	document.getElementById("parentName").value = name ;
 } 
</script>
<div id="newsInfo_list" delUrl="${baseURL}/news/newsinfo/newsInfoAction!ajaxBatchDelete.action"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="pageDown" id="downIdPage">

    </div>
    <!--翻页 end-->
    <div class="operation">
       <input class="btnS4" type="button" permission="noticeAfficheAdd" value="新增" onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
       <input class="btnS4" type="button" permission="noticeAfficheDel" value="删除" onclick="javascript:_doDel('newsInfo_list');doSearch(1);"/>
   </div>
    <!--对表格数据的操作 end-->
</div>
<!--主体部分 end-->

</body>

</html>
