<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="orgIdentity"></jsp:param>
		<jsp:param name="permission"
			value="partyDevelopmentManager,partyDevelopmentSearch,partyDevelopmentView,partyDevelopmentAdd,partyDevelopmentEdit,partyDevelopmentDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>党员发展信息管理</title>
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
					党员发展管理
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
				action="${baseURL}/partydevelopment/partydevelopmentAction!ajaxSearch.action"
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
													<td width="50" height="30">
														姓名：
													</td>
													<td width="140">
														<input name="searchValue.name" size="15" />
													</td>
													<td width="80" height="30">
														组织身份：
													</td>
													<td width="100">
														<select name="searchValue.organizationIdentity"
															id="organizationIdentity" dictType="orgIdentity" />
													</td>
													<td width="80" height="30">
														所属组织：
													</td>
													<td width="240">
														<input class="form100px" id="parentName"
															style="width: 100px" type="text" readonly="readonly"
															valid="{must:false,tip:'所属组织',fieldType:'pattern',length:100}" />
														<a href="javascript:selectParent()"><img
																src="/zhdj/image/ss_bt.png" /> </a>
														<input type="hidden" name="searchValue.organizationId"
															id="parentId" value="${searchValue.organizationId}" />
													</td>
													<td align="right">
														<input class="btnQuery" name="search" type="button"
															value="查询" permission="partyDevelopmentSearch"
															onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="新增"
															onclick="javascript:window.location.href='add.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
														<input class="btnQuery" type="button" value="批量导入"
															onclick="javascript:window.location.href='import.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token" />
														<input class="btnQuery" type="button" value="删除"
															onclick="javascript:_doDel('partyDevelopment_list');doSearch(1);" />
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

function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=200");
}
$(document).ready(function() {
	doSearch(1);
	jQuery("#organizationIdentity option[value='6']").remove();
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
														width : "5%",
														name : "sexDesc",
														showName : "性别"
													},
													{
														width : "15%",
														name : "organizationName",
														showName : "所属组织"
													},
													{
														width : "15%",
														name : "organizationIdentityDesc",
														showName : "组织身份"
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
														name : "基本资料",
														permission : "partyDevelopmentView",
														event : function(index,
																content) {
															window.location.href = 'view.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "党员发展管理",
														permission : "partyDevelopmentView",
														condition : function(
																index, content) {
															return content.organizationIdentity != 1;
														},
														event : function(index,
																content) {
															window.location.href = 'management.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "修改",
														permission : "partyDevelopmentEdit",
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
														permission : "partyDevelopmentDel",
														event : function(index,
																content) {
															_doSignlDel(
																	"partyDevelopment_list",
																	content.id);
														}
													}

											],
											trStyle : [ "trWhite" ]
										});
								list1.createList("partyDevelopment_list");
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
			<div id="partyDevelopment_list"
				delUrl="${baseURL}/partydevelopment/partydevelopmentAction!ajaxBatchDelete.action"></div>
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
