<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value="ideologySearch,ideologyView,ideologyEdit,ideologyDel,ideologyAudit"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>思想汇报</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;"> 
<!--头部 end-->
<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					思想汇报管理
				</h2>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td class="searchLeft"></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="text_bg">

<!--公告 end-->
<form action="${baseURL}/ideologyReport/ideologyReportAction!ajaxSearch.action" method="post" id="form_search">

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
                                        <td width="60" height="30">标题：</td>
                                        <td width="140">
                                        	<input type="text" name="searchValue.title" />
                                        </td>
                                        <td width="60" height="30">提交人：</td>
                                        <td width="180">
                                        	<input type="hidden" name="searchValue.createUserId" id="memberId"/>
                                        	<input type="text" id="memberName" readonly="readonly"/><img src="${baseURL}/image/ss_bt.png" onclick="selectMenber()" />
                                        </td>
                                        <td width="80" height="30">所属组织：</td>
                                        <td width="180">
                                        	<input type="text" id="parentName" readonly="readonly"/>&nbsp;<img src="${baseURL}/image/ss_bt.png" onclick="selectParent()" />
                                        	<input type="hidden" id="parentId" name="searchValue.organizationId"/>
                                        </td>
                                        <td align="right"><input class="btnQuery" name="search" type="button" value="查询" permission="ideologySearch"
                                                   onclick="javascript:doSearch(1);"/> <input class="btnQuery" type="button" permission="ideologyDel" value="删除" onclick="javascript:_doDel('dangkeInfo_list');doSearch(1);"/>
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
                                    {width:"25%", name:"title", showName:"标题"},
                                    {width:"8%", name:"name", showName:"提交人"},
                                    {width:"15%", name:"organizationName", showName:"所属组织"},
                                    {width:"8%", name:"statusDesc", showName:"审阅状态"},
                                    {width:"13%", name:"createTime", showName:"提交时间"},
                                    {width:"13%", name:"reviewTime", showName:"审阅时间"},
                                    {width:"15%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查看", permission:"ideologyView", event:function (index, content) {
                                        window.location.href = 'edit.jsp?id=' + content.id+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
	                                {name:"删除", permission:"ideologyEdit",event:function (index, content) {
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
 
 function selectMenber(){
	window.open("${baseURL}/jsp/component/basis/partymenber/selectPartyMember.jsp","_blank","width=800,height=400");
 } 
 
 function selectOrg(id,name){
	document.getElementById("parentId").value = id;
	document.getElementById("parentName").value = name ;
 } 
 
</script>
<div id="dangkeInfo_list" delUrl="${baseURL}/ideologyReport/ideologyReportAction!ajaxBatchDelete.action"></div>
<div id="tip"></div>

<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>
    <!--翻页 end-->
</div>
<!--主体部分 end-->
</div>
</body>

</html>
