<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="scoreLeave"></jsp:param>
		<jsp:param name="permission"
			value="scoreLeaveManager,scoreLeaveSearch,scoreLeaveView,scoreLeaveExport,scoreLeaveEdit,scoreLeaveDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>积分星级管理</title>
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
		<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					积分星级管理
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

			<form
				action="${baseURL}/scoreLeave/scoreLeaveAction!ajaxSearch.action"
				method="post" id="form_search">
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
													<td width="60" height="30">
														属性：
													</td>
													<td width="240">
														<select name="searchValue.type" dictType="scoreLeave"
															id="scoreLeaveType" />
													</td>
													<td align="right">
														<input class="btnQuery" name="search" type="button"
															value="查询" permission="scoreLeaveSearch"
															onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="新增"
															onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
														<input class="btnQuery" type="button" value="删除"
															onclick="javascript:_doDel('scoreLeave_list');doSearch(1);" />
														<input class="btnQuery" type="button" value="导出CSV"
															onclick="javascript:_doExport();" />
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

			<!--工具栏 end-->
			<script type="text/javascript">
$(document).ready(function() {
	doSearch(1);
});
function _doExport(formId) {
	$
			.ajax( {
				data : {
					'searchValue.type' : document
							.getElementById("scoreLeaveType").value
				},
				url : '${baseURL}/scoreLeave/scoreLeaveAction!ajaxBatchExport.action',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ("0" == result.code) {
						window.location.href = '${baseURL}/personalscore/personalscoreAction!downloadFile.action?searchValue.filePath=' + result.data.filePath;
					}
				}
			});

}

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
															if (content.createType == 0
																	|| content.useStatus == 1) {
																return false;
															} else {
																return true;
															}
														}
													},

													{
														width : "10%",
														name : "name",
														showName : "星级名称"
													},
													{
														width : "10%",
														name : "typeDesc",
														showName : "属性"
													},
													{
														width : "10%",
														name : "scoreDesc",
														showName : "分值区间"
													},
													{
														width : "20%",
														name : "operation",
														showName : "操作",
														isOperationColumn : true
													} ],
											data : result.data.pageData,
											operations : [
													{
														name : "编辑",
														permission : "scoreLeaveEdit",
														event : function(index,
																content) {
															window.location.href = 'edit.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "删除",
														permission : "scoreLeaveDel",
														event : function(index,
																content) {
															_doSignlDel(
																	"scoreLeave_list",
																	content.id);
														}
													}

											],
											trStyle : [ "trWhite" ]
										});
								list1.createList("scoreLeave_list");
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
</script>
			<div id="scoreLeave_list"
				delUrl="${baseURL}/scoreLeave/scoreLeaveAction!ajaxBatchDelete.action"></div>
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
