<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="affairsWorkerStatu"></jsp:param>
		<jsp:param name="permission"
			value="personalScoreManager,personalScoreSearch,personalScoreView,personalScoreExport,personalScoreEdit"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>个人积分明細管理</title>
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
		<script src="${baseURL}/js/do1/common/tipswindown.js">
</script>
		<script src="${baseURL}/js/DatePicker/WdatePicker.js">
</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="${baseURL}/js/do1/common/pop_up1.0.js">
</script>
		<style type="text/css">
#windownbg {
	display: none;
	position: absolute;
	width: 100%;
	height: 100%;
	background: #000; /*body-bg*/
	top: 0;
	left: 0;
}

#windown-box {
	position: fixed;
	border: 5px solid #E9F3FD;
	background: #FFF;
	text-align: left;
}

#windown-title {
	position: relative;
	height: 30px;
	border: 1px solid #A6C9E1;
	overflow: hidden;
	background: url(../jquery/images/bg.png) 0 -330px repeat-x;
}

#windown-title h2 {
	position: relative;
	left: 10px;
	top: 5px;
	font-size: 14px;
	color: #666;
}

#windown-close {
	position: absolute;
	right: 10px;
	top: 8px;
	width: 10px;
	height: 16px;
	text-indent: -10em;
	overflow: hidden;
	background: url('../../../../themes/default/images/icon/icon20.gif')
		-820px -720px no-repeat;
	cursor: pointer;
}

#windown-content-border {
	position: relative;
	top: -1px;
	border: 1px solid #A6C9E1;
	padding: 5px 0 5px 5px;
	background: url(../jquery/images/bg.png) 0 -380px repeat-x;
}

#windown-content {
	position: relative;
	overflow: auto;
	text-align: center;
}

#windown-content img,#windown-content iframe {
	display: block;
}

#windown-content .loading {
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -8px;
	margin-top: -8px;
}

blockquote {
	width: 500px;
	margin: 10px 0;
	padding: 10px;
	border: 2px dashed #F8B3D0;
	background-color: #FFF5FA;
}

.tb {
	
}

.tb td {
	line-height: 150%;
}
</style>
	</head>

	<body style="background: #f6ebe5;">
		<!--头部 end-->
		<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					个人积分明細管理
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
				action="${baseURL}/personalscore/personalscoreAction!ajaxInfo.action?searchValue.id=${param.id}"
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
														开始时间：
													</td>
													<td width="140">
														<input
															onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}',dateFmt:'yyyy-MM-dd',autoPickDate:true,readOnly:true,isShowWeek:true})"
															name="searchValue.startTime" id="startTime" />
													</td>
													<td width="60" height="30">
														结束时间：
													</td>
													<td width="140">
														<input
															onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01',dateFmt:'yyyy-MM-dd',autoPickDate:true,readOnly:true,isShowWeek:true})"
															name="searchValue.endTime" id="endTime" />
													</td>
													<td align="right">
														<input class="btnQuery" name="search" type="button"
															value="查询" permission="personalScoreSearch"
															onclick="javascript:doSearch(1);" />
														<input class="btnQuery" type="button" value="积分修正"
															onclick="javascript:window.location.href='add.jsp'+ '?id=${param.id}&dqdp_csrf_token='+dqdp_csrf_token;" />
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
			<b
				datasrc="${baseURL}/personalscore/personalscoreAction!getPersonalScoreByUserId.action?id=${param.id}">&nbsp;&nbsp;&nbsp;&nbsp;姓名：<font
				name="personalScoreVO.name"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;累计积分：<font
				name="personalScoreVO.accumulativeScore"></font> </b>

			<!--工具栏 end-->
			<script type="text/javascript">

function _doExport() {
	$
			.ajax( {
				data : {
					'searchValue.id' : '${param.id}',
					'searchValue.startTime' : document
							.getElementById("startTime").value,
					'searchValue.endTime' : document.getElementById("endTime").value
				},
				url : '${baseURL}/personalscore/personalscoreAction!ajaxBatchExportInfo.action',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ("0" == result.code) {
						window.location.href = '${baseURL}/personalscore/personalscoreAction!downloadFile.action?searchValue.filePath=' + result.data.filePath;
					}
				}
			});
}
function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=200");
}
$(document).ready(function() {
	doSearch(1);
});
function doSearch($pageIndex) {
	$('#form_search').ajaxSubmit(
			{
				data : {
					'page' : $pageIndex
				},
				dataType : 'json',
				success : function(result) {
					if ("0" == result.code) {
						var list1 = new ListTable( {
							checkableColumn : "id",
							title : [
									{
										width : "2%",
										name : "id",
										showName : "",
										isCheckColunm : true,
										checkAble : function(index, content) {
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
										name : "scoreTypeDesc",
										showName : "积分类型"
									}, {
										width : "5%",
										name : "score",
										showName : "获得积分"
									}, {
										width : "8%",
										name : "getTime",
										showName : "获得时间"
									}, {
										width : "30%",
										name : "scoreDesc",
										showName : "获得积分说明"
									}, {
										width : "10%",
										name : "operation",
										showName : "积分修复",
										isOperationColumn : true
									} ],
							data : result.data.pageData,
							operations : [
									{
										name : "修改",
										permission : "personalScoreView",
										event : function(index, content) {
											updateScore(content.id);
										}
									},
									{
										name : "删除",
										permission : "personalScoreEdit",
										event : function(index, content) {
											_doSignlDel("personalScore_list",
													content.id);
										}
									}

							],
							trStyle : [ "trWhite" ]
						});
						list1.createList("personalScore_list");
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

function updateScore(id) {
	//$("#personalScore_list[name=ids][value=" + id + "]").attr("checked", "true");
	//$("#personalScore_list[name=ids][value!=" + id + "]").removeAttr("checked");
	tipsWindown("编辑积分信息", "iframe:edit.jsp?id=" + id, "400", "150", "true", "",
			"true", "text");
}
</script>
			<div id="personalScore_list"
				delUrl="${baseURL}/personalscore/personalscoreAction!ajaxBatchDelete.action"></div>
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
