<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsInfoStatus,newsInfoBuyTop"></jsp:param>
    <jsp:param name="permission" value="newsManager,newsSearch,newsView,newsAdd,newsEdit,newsDel,newsOut,newsTop,newsPush"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
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
<form action="${baseURL}/news/newsinfo/newsInfoAction!ajaxSearch.action" method="post" id="form_search">
<div class="searchWrap">
    <div class="title clearfix">
        <h2 class="icon1" id="newsTypeName"></h2>
    </div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tbody>
    		<tr>
        		<td class="searchLeft"></td>
    		</tr>
    	</tbody>
    </table>
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
                                        <td>标题：</td>
                                        <td>
                                        	<input type="hidden" id="newsInfoType" name="searchValue.newsInfoType" value="${param.type}"/>
                                        	<input type="text" name="searchValue.title" />
                                        </td>
                                        <td>所属组织：</td>
                                        <td>
                                        	<input type="text" id="parentName" readonly="readonly"/>&nbsp;<img src="${baseURL}/image/ss_bt.png" onclick="selectParent()" />
                                        	<input type="hidden" id="parentId" name="searchValue.organizationId"/>
                                        </td>
                                        <td>发布状态：</td>
                                        <td><select name="searchValue.status" dictType="newsInfoStatus" defaultTip="全部"/></td>
                                        <td>
                                        	<input class="btnQuery" name="search" type="button" value="查询" permission="newsSearch" onclick="javascript:doSearch(1);"/>
       										<input class="btnQuery" type="button" permission="newsAdd" value="新增" onclick="javascript:window.location.href='${baseURL}/news/newsinfo/newsInfoAction!toAdd.action?newsType=${param.type}'"/>
       										<input class="btnQuery" type="button" permission="newsDel" value="删除" onclick="javascript:_doDel('newsInfo_list');doSearch(1);"/>
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
	<div id="newsInfo_list" delUrl="${baseURL}/news/newsinfo/newsInfoAction!ajaxBatchDelete.action"></div>
	<div id="tip"></div>
	<div class="toolBar">
	    <div class="page" id="downIdPage">
	    </div>
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
                             	checkableColumn:"newsInfoId",
                                title:[
                                    {width:"2%", name:"newsInfoId", showName:"", isCheckColunm:true,
                                    	checkAble:function(index,content){
                                    		if(content.status == 1){
                                    			return false;
                                    		}else{
												return true;
											}
                                    	}
                                    },
                                    {width:"28%", name:"title", showName:"标题",length:34},
                                    {width:"15%", name:"organizationName", showName:"所属组织"},
                                    {width:"7%", name:"statusDesc", showName:"发布状态"},
                                    {width:"7%", name:"buyTopDesc", showName:"是否置顶"},
                                    {width:"15%", name:"pushTime", showName:"发布时间"},
                                    {width:"28%", name:"operation", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"预览", permission:"newsView", event:function (index, content) {
                                        window.location.href = 'view.jsp?newsInfoId=' + content.newsInfoId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"编辑", permission:"newsEdit", event:function (index, content) {
                                    	window.location.href = "${baseURL}/news/newsinfo/newsInfoAction!toEdit.action?newsInfoId="+content.newsInfoId+"&newsType=${param.type}&dqdp_csrf_token="+dqdp_csrf_token;
                                    }},
                                    {name:"发布", permission:"newsPush", condition:function (index, content) {
											return content.status != 1;
											},event:function (index, content) {
                                    	updateStatus(content.newsInfoId,1);
                                    }},
                                    {name:"下架", permission:"newsOut", condition:function (index, content) {
											return content.status == 1;
											},event:function (index, content) {
                                    	updateStatus(content.newsInfoId,2);
                                    }},
                                    {name:"置顶", permission:"newsTop", condition:function (index, content) {
											return content.status == 1 && content.buyTop == 0;
											},event:function (index, content) {
                                    	updateTop(content.newsInfoId,1);
                                    }},
                                    {name:"取消置顶", permission:"newsTop", condition:function (index, content) {
											return content.buyTop == 1;
											},event:function (index, content) {
                                    	updateTop(content.newsInfoId,2);
                                    }},
	                                {name:"删除", permission:"newsDel", condition:function (index, content) {
											return content.status != 1;
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
                    $("#newsTypeName").text(result.data.newsTypePO.name);

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
 
 function updateStatus(id,status){
	   if(confirm(status == 1 ? "确定发布?" : "确定下架?")){
	       $.ajax({
	    	   url:"${baseURL}/news/newsinfo/newsInfoAction!ajaxPushOrOut.action",
	    	   data:{"tbNewsInfoPO.newsInfoId":id,"tbNewsInfoPO.status":status},
	    	   type:"post",
	           dataType:'json',
	           success:function(result) {
	               if ("0"==result.code) {
	                   alert(result.desc);
	                   window.location.href="${baseURL}/jsp/component/news/newsinfo/list.jsp?type="+$('#newsInfoType').val();
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
 
 function updateTop(id,buyTop){
	   if(confirm(buyTop == 1 ? "确定置顶?" : "确定取消置顶?")){
	       $.ajax({
	    	   url:"${baseURL}/news/newsinfo/newsInfoAction!ajaxTop.action",
	    	   data:{"tbNewsInfoPO.newsInfoId":id,"tbNewsInfoPO.buyTop":buyTop},
	    	   type:"post",
	           dataType:'json',
	           success:function(result) {
	               if ("0"==result.code) {
	                   alert(result.desc);
	                   window.location.href="${baseURL}/jsp/component/news/newsinfo/list.jsp?type="+$('#newsInfoType').val();
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
