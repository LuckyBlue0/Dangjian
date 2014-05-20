<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="partyRelationStatu"></jsp:param>
		<jsp:param name="permission"
			value="partyRelationManager"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>组织关系管理</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
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

	<body>
		<!--头部 end-->


		<!--公告 end-->
		<div class="searchWrap">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="searchLeft"></td>
					<div class="title">
						<h2 class="icon1">
							组织关系管理
						</h2>
					</div>
				</tr>
			</table>
		</div>

		<!-- 新闻类型信息搜索表单 -->
		<form
			action="${baseURL}/partymenber/partymenberAction!ajaxSearchRelation.action"
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
												<td width="40" height="30">
													姓名：
												</td>
												<td width="150">
													<input name="searchValue.name" />
												</td>
												<td width="40" height="30">
													状态：
												</td>
												<td width="100">
													<select name="searchValue.state"
														dictType="partyRelationStatu" />
												</td>
												<td>
													<input class="btnQuery" name="search" type="button"
														value="查询" permission="partyRelationManager"
														onclick="javascript:doSearch(1);" />
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

		<div class="operation">
			<input class="btnS4" type="button" value="导出CSV"
				onclick="javascript:window.location.href='import.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
		</div>

		<!--工具栏 end-->
		<script type="text/javascript">

function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=200");
}
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
														showName : "姓名"
													},
													{
														width : "10%",
														name : "organizationName",
														showName : "所属组织"
													},
													{
														width : "10%",
														name : "leaveTime",
														showName : "转出申请时间"
													},
													{
														width : "10%",
														name : "stateDesc",
														showName : "状态"
													},
													{
														width : "10%",
														name : "operation",
														showName : "操作",
														isOperationColumn : true
													} ],
											data : result.data.pageData,
											operations : [
													{
														name : "审批",
														permission : "partyRelationManager",
														event : function(index,
																content) {
															window.location.href = 'view.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
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
</script>
		<div id="partyMenber_list"
			delUrl="${baseURL}/partymenber/partymenberAction!ajaxBatchDelete.action"></div>
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
