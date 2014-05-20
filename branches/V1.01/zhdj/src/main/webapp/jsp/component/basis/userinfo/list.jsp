<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="affairsWorkerStatu"></jsp:param>
		<jsp:param name="permission"
			value="userInfoManager,userInfoSearch,userInfoView,userInfoDel,userInfoEdit"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户信息管理</title>
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
							用户信息管理
						</h2>
					</div>
				</tr>
			</table>
		</div>

		<!-- 新闻类型信息搜索表单 -->
		<form action="${baseURL}/userinfo/userinfoAction!ajaxSearch.action"
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
													用户名：
												</td>
												<td width="140">
													<input name="searchValue.userName" />
												</td>
												<td>
													<input class="btnQuery" name="search" type="button"
														value="查询" permission="userInfoSearch"
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
		<!-- 
		<div class="operation">
			<input class="btnS4" type="button" value="删除"
				onclick="javascript:_doDel('userInfo_list');doSearch(1);" />
		</div>
		-->
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
														name : "userName",
														showName : "用户名"
													},
													{
														width : "5%",
														name : "sexDesc",
														showName : "性别"
													},
													{
														width : "10%",
														name : "idCard",
														showName : "身份证号"
													},
													{
														width : "10%",
														name : "mobile",
														showName : "手机号"
													},
													{
														width : "10%",
														name : "regTime",
														showName : "注册时间"
													},
													{
														width : "10%",
														name : "registeredChannelsDesc",
														showName : "注册渠道"
													},
													{
														width : "5%",
														name : "stateDesc",
														showName : "状态"
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
														permission : "userInfoView",
														event : function(index,
																content) {
															window.location.href = 'view.jsp?id='
																	+ content.id
																	+ '&dqdp_csrf_token='
																	+ dqdp_csrf_token;
														}
													},
													{
														name : "启用",
														permission : "userInfoEdit",
														condition : function(
																index, content) {
															return content.state == 0;
														},
														event : function(index,
																content) {
															_doSignlChange(
																	"userInfo_list",
																	content, 1);
														}
													},
													{
														name : "禁用",
														permission : "userInfoEdit",
														condition : function(
																index, content) {
															return content.state == 1;
														},
														event : function(index,
																content) {
															_doSignlChange(
																	"userInfo_list",
																	content, 0);
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
function _doSignlChange(formId, content, state) {
	$
			.ajax( {
				url : '${baseURL}/userinfo/userinfoAction!ajaxChange.action?tbUserInfoPO.id='
						+ content.id + '&tbUserInfoPO.state=' + state,
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
		<div id="userInfo_list"
			delUrl="${baseURL}/userinfo/userinfoAction!ajaxBatchDelete.action"></div>
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
