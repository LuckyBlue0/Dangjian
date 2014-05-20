<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="clientType"></jsp:param>
		<jsp:param name="permission"
			value="suggestionManage,suggestionExp,suggestionDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>意见反馈</title>
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
                <h2 class="icon1">意见反馈</h2>
            </div>
        </div>
        
		<!-- 新闻类型信息搜索表单 -->
		<form
			action="${baseURL}/suggestion/suggestionAction!ajaxSearch.action"
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
													平台类型：
												</td>
												<td width="140">
													<select  dictType="clientType" id="type"  name="searchValue.type" />
												</td>
												
												
												<td>
													<input class="btnQuery" name="search" type="button" value="查询" permission="" onclick="javascript:doSearch(1);" />
													<input class="btnQuery" type="button" permission="suggestionExp" value="导出CSV" onclick="exportData();" />
		                                            <input class="btnQuery" type="button" value="删除" permission="suggestionDel" onclick="javascript:_doDel('partyMenber_list');doSearch(1);" />
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
			<div id="partyMenber_list"
			delUrl="${baseURL}/suggestion/suggestionAction!ajaxBatchDelete.action"></div>
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


		<!--工具栏 end-->
		<script type="text/javascript">


$(document).ready(function() {
	doSearch(1);

});
function exportData(){
	$.ajax( {   
		        data : {
					'searchValue.type' : $("#type").val()
				},
				url : '${baseURL}/suggestion/suggestionAction!ajaxBatchExport.action',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ("0" == result.code) {
						window.location.href = '${baseURL}/personalscore/personalscoreAction!downloadFile.action?searchValue.filePath=' + result.data.filePath;
					}
				}
			})
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
														isCheckColunm : true
														
													},

													{
														width : "10%",
														name : "userName",
														showName : "用户名"
													},
													{
														width : "30%",
														name : "suggestion", 
														showName : "意见反馈" 
													},
													{
														width : "10%",
														name : "_type_desc",
														showName : "平台类型"
													},
													
													{
														width : "10%",
														name : "createTime", 
														showName : "提交时间" 
													} 
													,
													{
														width : "10%",
														name : "operation",
														showName : "操作",
														isOperationColumn : true
													}],
											data : result.data.pageData,
											operations : [
													{
														name : "查看",
														permission : "",
														event : function(index,
																content) {
															window.location.href = 'suggestionView.jsp?id='+ content.id;
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
		
		<!--主体部分 end-->

	</body>

</html>
