<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="clientType"></jsp:param>
		<jsp:param name="permission"
			value="partyMenberManager,partyMenberSearch,partyMenberView,partyMenberAdd,partyMenberEdit,partyMenberDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>积分规则管理</title>
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
					积分规则管理
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
			<form action="${baseURL}/scorerule/scoreruleAction!ajaxSearch.action"
				method="post" id="form_search">

			</form>
			<!--角色信息搜索表单 end-->


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
												width : "30%",
												name : "scoreType",
												showName : "积分类型"
											}, {
												width : "30%",
												name : "scoreValue",
												showName : "分值"
											}, {
												width : "30%",
												name : "_scoreAttr_desc",
												showName : "积分属性"
											}, {
												width : "10%",
												name : "operation",
												showName : "操作",
												isOperationColumn : true
											} ],
											data : result.data.pageData,
											operations : [ {
												name : "编辑",
												permission : "",
												event : function(index, content) {
													window.location.href = 'ruleEdit.jsp?id=' + content.id;
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
			<div id="partyMenber_list"></div>
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
