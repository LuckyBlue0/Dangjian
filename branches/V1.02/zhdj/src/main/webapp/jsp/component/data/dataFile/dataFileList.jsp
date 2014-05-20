<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="dataType"></jsp:param>
		<jsp:param name="permission"
			value="dataFileManage,dataFileAdd,dataFileDel,dataFileEdit"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>资料文件管理</title>
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
                <h2 class="icon1">资料文件管理</h2>
            </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <input type="hidden" id="filePath" value="${param.filePath}"/>
			<input type="hidden" id="fileName" value="${param.fileName}"/>
            <tbody><tr>
                <td class="searchLeft"></td>
            </tr>
            </tbody></table>
        </div>
		<!--公告 end-->


		<!-- 新闻类型信息搜索表单 -->
		<form action="${baseURL}/datafile/datafileAction!ajaxSearch.action"
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
												<td width="60" height="30">
													文件名：
												</td>
												<td width="140">
													<input name="searchValue.fileName" />
												</td>
												<td width="60" height="30">
													文件类型：
												</td>
												<td width="140">
													<select  name="searchValue.type" dictType="dataType"></select>
												</td>
												<td>
													<input class="btnQuery" name="search" type="button"
														value="查询" permission=""
														onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="新增" permission="dataFileAdd" onclick="javascript:doVideoFileAdd()"/>&nbsp;
			                                        <input class="btnQuery" type="button" value="删除" permission="dataFileDel" onclick="javascript:_doDel('userInfo_list');doSearch(1);" />
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
			<div id="userInfo_list" delUrl="${baseURL}/datafile/datafileAction!ajaxBatchDelete.action"></div>
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
	 if(""!=$("#filePath").val()&&""!=$("#fileName").val()){
	 $.ajax({
            url:'${baseURL}/datafile/datafileAction!uploadFile.action',
            data:{"tbDatafilePO.filePath":$("#filePath").val(),"tbDatafilePO.fileName":$("#fileName").val()}, 
            type:'post',                                     
            dataType:'json',
            success:function (result) {
            }
     });
	 }
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
															
																return true;
															
														}
													},

													{
														width : "30%",
														name : "fileName",
														showName : "文件名"
													},
													{
														width : "10%",
														name : "fileSize",
														showName : "文件大小(M)"
													},
													{
														width : "10%",
														name : "_type_desc",
														showName : "文件类型" 
													},
													{
														width : "10%",
														name : "pushTime",
														showName : "发布时间"
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
														name : "查看",
														permission : "",
														event : function(index,
																content) {
															window.location.href = 'dataFileView.jsp?id='+ content.id;
														}
													},
													{
														name : "修改",
														permission : "dataFileEdit",
														event : function(index, 
																content) {
															window.location.href = 'dataFileEdit.jsp?id='+ content.id;
																
														}
													},
													{
														name : "删除",
														permission : "dataFileDel",
														event : function(index,
																content) {
															_doSignlDel(
																	"userInfo_list",
																	content.id);
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
function doVideoFileAdd(){
	window.location.href="${baseURL}/jsp/component/data/dataFile/dataFileAdd.jsp"; 
}
</script>
		
		<!--主体部分 end-->

	</body>

</html>
