<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="affairsWorkerStatu"></jsp:param>
		<jsp:param name="permission"
			value="partyMenberManager,partyMenberSearch,partyMenberView,partyMenberAdd,partyMenberEdit,partyMenberDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>流动党员管理</title>
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
					流动党员管理
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
				action="${baseURL}/partymenber/partymenberAction!ajaxSearch.action"
				method="post" id="form_search">
				<div class="searchWrap">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="searchLeft">
								<input name="searchValue.userType" value="2" type="hidden" />
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
														姓名：
													</td>
													<td width="35">
														<input name="searchValue.name" />
													</td>
													<!-- 
												<td width="60" height="30">
													身份证号：
												</td>
												<td width="50">
													<input name="searchValue.idCard" size="10" />
												</td>
												<td width="100" height="30">
													是否党务工作者：
												</td>
												<td width="50">
													<select name="searchValue.affairsWorker"
														dictType="affairsWorkerStatu" />
												</td>
												<td width="60" height="30">
													所属组织：
												</td>
												<td width="100">
													<input class="for50px" id="parentName" style="width: 50px"
														type="text" size="10" readonly="readonly"
														valid="{must:false,tip:'所属组织',fieldType:'pattern',length:60}" />
													<a href="javascript:selectParent()"><img
															src="/zhdj/image/ss_bt.png" />
													</a>
													<input type="hidden" name="searchValue.organizationId"
														id="parentId" value="${searchValue.organizationId}" />
												</td>
											 -->
													<td align="right">
														<input class="btnQuery" name="search" type="button"
															value="查询" permission="partyMenberSearch"
															onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="新增"
															onclick="javascript:window.location.href='add.jsp'+ '?userType=2&dqdp_csrf_token='+dqdp_csrf_token" />
														<input class="btnQuery" type="button" value="批量导入"
															onclick="javascript:window.location.href='import.jsp'+ '?userType=2&dqdp_csrf_token='+dqdp_csrf_token" />
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
						<tr>
							<td class="searchButtomLeft"></td>
							<td class="searchButtom"></td>
							<td class="searchButtoRight"></td>
						</tr>
					</table>
				</div>
			</form>
			<!--角色信息搜索表单 end-->

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
														width : "3%",
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
														width : "8%",
														name : "sexDesc",
														showName : "性别"
													},
													{
														width : "8%",
														name : "national",
														showName : "民族"
													},
													{
														width : "10%",
														name : "nativePlace",
														showName : "籍贯"
													},
													{
														width : "8%",
														name : "partyTime",
														showName : "入党时间"
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
														permission : "partyMenberView",
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
														permission : "partyMenberEdit",
														event : function(index,
																content) {
															window.location.href = 'edit.jsp?id='
																	+ content.id
																	+ '&userType=2&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "删除",
														permission : "partyMenberDel",
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
</script>
			<div id="partyMenber_list"
				delUrl="${baseURL}/partymenber/partymenberAction!ajaxBatchDelete.action"></div>
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
