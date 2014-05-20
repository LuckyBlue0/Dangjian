<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="affairsWorkerStatu"></jsp:param>
		<jsp:param name="permission"
			value="partymemberVoteAdd,partymemberVoteEdit,partymemberVoteDel,partymemberVotePushOrOut"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>优秀党员评议</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>">
		</script>
		<script src="${baseURL}/js/do1/common/error1.0.js">
</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="${baseURL}/js/do1/common/pop_up1.0.js">
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<!--头部 end-->

        <div class="searchWrap">
            <div class="title clearfix">
                <h2 class="icon1">优秀党员评议</h2>
            </div>
        </div>


		<!-- 新闻类型信息搜索表单 -->
		<form action="${baseURL}/partymembervote/partymembervoteAction!ajaxSearch.action"
			method="post" id="form_search">
			<div class="text_bg" style="height: 450px;">
			<div class="searchWrap">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="searchLeft"></td>
						<td class="searchBg">
							<table class="search" width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td>
										<table class="search" width="100%" border="0" cellspacing="0"
											cellpadding="0">

											<tr>
												<td width="100" height="30">
													评议主题：
												</td>
												<td width="140">
													<input name="searchValue.voteTopic" />
												</td>
												<td>
													<input class="btnQuery" name="search" type="button" value="查询" onclick="javascript:doSearch(1);" />
													<input class="btnQuery" type="button" value="新增" permission="partymemberVoteAdd" onclick="window.location.href='partymemberVoteAdd.jsp'" />
		                                            <input class="btnQuery" type="button" value="删除" permission="partymemberVoteEdit" onclick="javascript:_doDel('userInfo_list');doSearch(1);" />
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
			<div id="userInfo_list" delUrl="${baseURL}/partymembervote/partymembervoteAction!ajaxBatchDelete.action"></div>
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
		<!--角色信息搜索表单 end-->
		
		<div class="operation">
		   
		</div>
		
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	doSearch(1);
});
function doSearch($pageIndex) {
	$('#form_search')
			.ajaxSubmit(
					{
						data : {
							'page' : $pageIndex
						},
						dataType : 'json',
						success : function(result) {
							if ("0" == result.code) {
								var list1 = new ListTable(
										{
											checkableColumn : "id",
											title : [
													{
														width : "2%",
														name : "id",
														showName : "",
														isCheckColunm : true,
														checkAble : function(
																index, content) {
															if (content.pushStatus == 1) {
																return false;
															} else {
																return true;
															}
														}
													},

													{
														width : "25%",
														name : "voteTopic",
														showName : "评议主题"
													},
													
													{
														width : "8%",
														name : "_voteStatus_desc",
														showName : "评议状态"
													},
													{
														width : "8%",
														name : "_pushStatus_desc",
														showName : "发布状态"
													},
													{
														width : "8%",
														name : "pushTime",
														showName : "发布时间"
													},
													{
														width : "15%",
														name : "operation",
														showName : "操作",
														isOperationColumn : true
													} ],
											data : result.data.pageData,
											operations : [
													{
														name : "查看",
														permission : "",
														event : function(index,
																content) {
															window.location.href = 'partymemberVoteView.jsp?id='+ content.id;
														}
													},
													{
														name : "编辑",
														permission : "partymemberVoteEdit",
														condition : function(
																index, content) {
															return content.pushStatus == 0;
														},
														event : function(index,
																content) {
															window.location.href = 'partymemberVoteEdit.jsp?id='+ content.id;
														}
													},
													{
														name : "发布",
														permission : "partymemberVotePushOrOut",
														condition : function(
																index, content) {
															return content.pushStatus == 0||content.pushStatus == 2;
														},
														event : function(index,
																content) {
															pushOrOut(content.id,1);
														}
													},
													{
														name : "下架",
														permission : "partymemberVotePushOrOut",
														condition : function(
																index, content) {
															return content.pushStatus == 1;
														},
														event : function(index,
																content) {
															pushOrOut(content.id,2);
														}
													},
													{
														name : "删除",
														permission : "partymemberVoteDel",
														condition : function(
																index, content) {
															return content.pushStatus == 0||content.pushStatus == 2;
														},
														event : function(index,
																content) {
															_doSignlDel('userInfo_list',content.id);
														}
													},
													{
														name : "投票结果统计",
														permission : "",
														condition : function(
																index, content) {
															return content.pushStatus == 1;
														},
														event : function(index,
																content) {
															window.location.href = 'partymemberVoteResult.jsp?id='+ content.id;
														}
													}

											],
											trStyle : [ "trWhite" ]
										});
								list1.createList("userInfo_list");
								var pager = new Pager( {
									totalPages : result.data.maxPage,
									currentPage : result.data.currPage,
									funcName : "doSearch"
								});
								pager.createPageBar("topIdPage");
								pager.createPageBar("downIdPage");

							} else {
								$("#tip").error( {
									title : "错误",
									content : result.desc,
									button : [ {
										name : "确定",
										event : "_hideMsg()"
									} ]
								});
							}
						},
						error : function() {
						}
					});
}
function pushOrOut(id,type){
		 var alertDesc = type == 1 ? "确定发布?" :"确定下架?";
		if(confirm(alertDesc)){
	    $.ajax({
	        url: "${baseURL}/partymembervote/partymembervoteAction!pushOrOut.action",
	        data: {"tbPartyMemberVotePO.id": id,"tbPartyMemberVotePO.pushStatus":type},
	        type: "post",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	                alert(result.desc);
	                doSearch(1);
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
    }
}
</script>
		
		<!--主体部分 end-->

	</body>

</html>
