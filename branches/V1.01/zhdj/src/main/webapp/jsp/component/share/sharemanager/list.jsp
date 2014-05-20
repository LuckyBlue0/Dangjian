<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="affairsWorkerStatu"></jsp:param>
		<jsp:param name="permission"
			value="indexshowManager,indexshowSearch,indexshowView,indexshowAdd,indexshowEdit,indexshowDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页管理</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
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


		<!--公告 end-->
		<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					首页管理
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
			<!-- 新闻类型信息搜索表单 -->
			<form
				action="${baseURL}/sharemanager/sharemanagerAction!ajaxSearch.action"
				method="post" id="form_search">
				<div class="searchWrap">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="searchLeft">
								<!-- <input name="searchValue.userType" value="1" type="hidden" /> -->
							</td>
							<td class="searchBg">
								<table class="search" width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td>
											<table class="search" width="100%" border="0" cellspacing="0"
												cellpadding="0">

												<tr>
													<td width="40" height="30">
														主题：
													</td>
													<td width="35">
														<input name="searchValue.title" />
													</td>
													<td align="right">
														<input class="btnQuery" name="search" type="button"
															value="查询" permission="indexshowSearch"
															onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="新增"
															onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
														<input class="btnQuery" type="button" value="删除"
															onclick="javascript:_doDel('partyMenber_list');doSearch(1);" />
													</td>
												</tr>

											</table>
										</td>
									</tr>
								</table>
							</td>
							<td class="searchRight"></td>
						</tr>
					</table>
				</div>
			</form>
			<!--角色信息搜索表单 end-->
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
														width : "3%",
														name : "id",
														showName : "",
														isCheckColunm : true
													},

													{
														width : "6%",
														name : "proName",
														showName : "主题"
													},
													{
														width : "4%",
														name : "_type_desc",
														showName : "类型"
													},
													{
														width : "4%",
														name : "_status_desc",
														showName : "状态"
													},
													{
														width : "8%",
														name : "createTime",
														showName : "创建时间"
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
														permission : "indexshowView",
														event : function(index,
																content) {
															window.location.href = 'view.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "修改",
														permission : "indexshowEdit",
														event : function(index,
																content) {
															window.location.href = 'edit.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "启用",
														permission : "indexshowEdit",
														condition : function(
																index, content) {
															return content.status == 0;
														},
														event : function(index,
																content) {
															_doSignlChange(
																	"partyMenber_list",
																	content, 1);
														}
													},
													{
														name : "禁用",
														permission : "indexshowEdit",
														condition : function(
																index, content) {
															return content.status == 1;
														},
														event : function(index,
																content) {
															_doSignlChange(
																	"partyMenber_list",
																	content, 0);
														}
													},
													{
														name : "删除",
														permission : "indexshowDel",
														event : function(index,
																content) {
															_doSignlDel(
																	"partyMenber_list",
																	content.id);
														}
													}

											],
											trStyle : [ "trWhite" ]
										});
								list1.createList("partyMenber_list");
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
function _doSignlChange(formId, content, status) {
	$.ajax({
				url : '${baseURL}/sharemanager/sharemanagerAction!ajaxChangeState.action?tbShareInfoPO.id='
						+ content.id + '&tbShareInfoPO.status=' + status,
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ('0' == result.code) {
						alert(result.desc);
						doSearch(1);
					} else {
				  		alert(result.desc);
					}
				}
			});
}
</script>
			<div id="partyMenber_list"
				delUrl="${baseURL}/sharemanager/sharemanagerAction!ajaxBatchDelete.action"></div>
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
