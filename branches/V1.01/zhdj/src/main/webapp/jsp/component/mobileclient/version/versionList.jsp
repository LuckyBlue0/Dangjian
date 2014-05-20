<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="clientType"></jsp:param>
		<jsp:param name="permission"
			value="versionManage,versionAdd,versionEdit,versionDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>版本管理</title>
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
                <h2 class="icon1">版本管理</h2>
            </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <input type="hidden" id="filePath" value="${param.filePath}"/>
			<input type="hidden" id="fileName" value="${param.fileName}"/>
            <tbody><tr>
                <td class="searchLeft"></td>
            </tr>
            </tbody></table>
        </div>


		<!-- 新闻类型信息搜索表单 -->
		<form
			action="${baseURL}/version/versionAction!ajaxSearch.action"
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
													客户端名称：
												</td>
												<td width="140">
													<input name="searchValue.versionName" />
												</td>
												
												<td width="100" height="30">
													平台类型：
												</td>
												<td width="100">
													<select name="searchValue.type" dictType="clientType" />
												</td>
												
												<td>
													<input class="btnQuery" name="search" type="button" value="查询" permission="" onclick="javascript:doSearch(1);" />
													<input class="btnQuery" type="button" value="新增"  permission="versionAdd" onclick="javascript:window.location.href='versionAdd.jsp'" />
			                                        <input class="btnQuery" type="button" value="删除"  permission="versionDel" onclick="javascript:_doDel('partyMenber_list');doSearch(1);" />
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
			delUrl="${baseURL}/version/versionAction!ajaxBatchDelete.action"></div>
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


		<!--工具栏 end-->
		<script type="text/javascript">


$(document).ready(function() {
	doSearch(1);
	if(""!=$("#filePath").val()&&""!=$("#fileName").val()){
	 $.ajax({
            url:'${baseURL}/version/versionAction!uploadFile.action',
            data:{"tbVersionPO.filePath":$("#filePath").val(),"tbVersionPO.versionName":$("#fileName").val()}, 
            type:'post',                                     
            dataType:'json',
            success:function (result) {
            }
     });
	 }
});
function changeStatus(id,type){
	$.ajax({
            url:'${baseURL}/version/versionAction!ajaxUpdate.action',
            data:{"tbVersionPO.id":id,"tbVersionPO.status":type}, 
            type:'post',                                     
            dataType:'json',
            success:function (result) {
            	if("0"==result.code){
            		alert("操作成功!");
            		doSearch(1);
            	}else{
            		alert(result.desc);
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
														isCheckColunm : true
													
													},

													{
														width : "10%",
														name : "versionNum",
														showName : "版本号"
													},
													{
														width : "10%",
														name : "_type_desc",
														showName : "平台类型"
													},
													{
														width : "10%",
														name : "versionName", 
														showName : "客户端名称"
													},
													{
														width : "10%",
														name : "fileSize",
														showName : "文件大小（M）"
													},
													{
														width : "10%",
														name : "_status_desc",
														showName : "发布状态" 
													},
													{
														width : "10%",
														name : "pushTime",
														showName : "发布时间"
													},
													{
														width : "10%",
														name : "creator",
														showName : "发布人"
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
															window.location.href = 'versionView.jsp?id='+ content.id;

														}
													},
													{
														name : "修改",
														permission : "versionEdit",
														event : function(index,
																content) {
															window.location.href = 'versionEdit.jsp?id='+ content.id;

														}
													},
													{
														name : "发布",
														permission : "versionEdit",
														permission : "",condition:function (index, content) {
                                                           return content.status != 1;
                                                       }, 
														event : function(index,
																content) {
															changeStatus(content.id,"1");
														}
													},
													{
														name : "下架", 
														permission : "versionEdit",
														permission : "",condition:function (index, content) {
                                                           return content.status == 1;
                                                       },
														event : function(index,
																content) {
															changeStatus(content.id,"2");
														}
													},
													{
														name : "删除",
														permission : "versionDel",
														condition : function(
																index, content) {
															return content.status != 1;
														},
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
		
		<!--主体部分 end-->

	</body>

</html>
