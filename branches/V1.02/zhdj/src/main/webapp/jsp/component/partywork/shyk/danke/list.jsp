<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="meetingWhetherEnd,meetingWhetherRecommend"></jsp:param>
    <jsp:param name="permission" value="dangkeManager,dangkeSearch,dangkeView,dangkeAdd,dangkeEdit,dangkeDel,dangkeRecommended,dangkeRecord"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>党课管理</title>
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
<form action="${baseURL}/meeting/dangkeAction!ajaxSearch.action" method="post" id="form_search">
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">党课管理</h2>
            </div>
        </tr>
    </table>
</div>

<!-- 新闻类型信息搜索表单 -->

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
                                        <td>标题：</td>
                                        <td>
                                        	<input type="text" name="searchValue.title" />
                                        </td>
                                        <td>发布组织：</td>
                                        <td>
                                        	<input type="text"  id="parentName" readonly="readonly"/>&nbsp;<img src="${baseURL}/image/ss_bt.png" onclick="selectParent()" />
                                        	<input type="hidden" id="parentId" name="searchValue.organizationId"/>
                                        </td>
                                        <td>是否结束：</td>
                                        <td><select name="searchValue.whetherEnd" dictType="meetingWhetherEnd" defaultTip="全部"/></td>
                                        <td>是否推荐：</td>
                                        <td><select name="searchValue.whetherRecommend" dictType="meetingWhetherRecommend" defaultTip="全部"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询" permission="dangkeSearch"
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

<!--翻页 end-->

    <div class="operation">
       <input class="btnS4" type="button" permission="dangkeAdd" value="新增" onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
       <input class="btnS4" type="button" permission="dangkeDel" value="删除" onclick="javascript:_doDel('dangkeInfo_list');doSearch(1);"/>
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
                             	checkableColumn:"id",
                                title:[
                                    {width:"2%", name:"id", showName:"", isCheckColunm:true},
                                    {width:"20%", name:"title", showName:"标题"},
                                    {width:"6%", name:"typeDesc", showName:"党课类型"},
                                    {width:"10%", name:"organizationName", showName:"发布组织"},
                                    {width:"8%", name:"startTime", showName:"上课时间"},
                                    {width:"8%", name:"whetherEndDesc", showName:"是否结束"},
                                    {width:"8%", name:"recordKey", showName:"党课记录",href:function (index, content) {
                                        window.location.href="record.jsp?id="+content.id;
                                    }},
                                    {width:"8%", name:"qrCode", showName:"签到二维码",href:function (index, content) {
                                        //window.location.href="record.jsp?id="+content.id;
                                    }},
                                    {width:"30%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"推荐", permission:"dangkeRecommended",condition:function (index, content) {
											return content.whetherRecommend == 0;
											},event:function (index, content) {
                                        recommend(content.id);
                                    }},
                                    {name:"查看", permission:"dangkeView", event:function (index, content) {
                                        window.location.href = 'view.jsp?id=' + content.id+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"dangkeEdit", event:function (index, content) {
                                    	window.location.href = 'edit.jsp?id=' + content.id+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }}, 
	                                {name:"删除", permission:"dangkeDel",event:function (index, content) {
	                                    _doSignlDel("dangkeInfo_list", content.id);
	                                }}
                                    
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("dangkeInfo_list");
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
 
 function recommend(id){
	if(confirm("确定推荐")){
	    $.ajax({
	        url: "${baseURL}/meeting/dangkeAction!ajaxUpdateMeeting.action",
	        data: {"id": id},
	        type: "post",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	                alert("推荐成功");
	                doSearch(1);
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
    }
 }
</script>
<div id="dangkeInfo_list" delUrl="${baseURL}/meeting/dangkeAction!ajaxBatchDelete.action"></div>
<div id="tip"></div>

<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="pageDown" id="downIdPage">

    </div>
    <!--翻页 end-->
</div>
<!--主体部分 end-->

</body>

</html>
