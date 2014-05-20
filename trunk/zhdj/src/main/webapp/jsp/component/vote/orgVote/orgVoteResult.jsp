<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value=""></jsp:param>
		<jsp:param name="permission"
			value=""></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>投票结果统计</title>
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
<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>  
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<!--头部 end-->

        <input type="hidden" id="id" value="${param.id}"/>
		<!--公告 end-->
		<div class="searchWrap">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="searchLeft"></td>
					<div class="title">
						<h2 class="icon1">
							投票结果统计
						</h2>
					</div>
				</tr>
			</table>
		</div>
		<div datasrc="${baseURL}/orgvote/orgvoteAction!ajaxSearchVoteResult.action?id=${param.id}">
       <div>
        <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%">

        <tbody>
            <tr >
                <td class="tdBlue"  style="width:100px;border:1px solid #e0e0e0;">被评议机关名称</td>
                <td name="resultVO.voteOrg" style="border:1px solid #e0e0e0;">
            </td>
            </tr>
            <tr >
                <td class="tdBlue"  style="width:100px;border:1px solid #e0e0e0;">参与人数</td>
                <td name="resultVO.totalCount" style="border:1px solid #e0e0e0;">
            </td>
            </tr>
        </tbody>
         </table>
      </div>
       <div>
        <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%">
        <thead>
            <tr>
                <th width="25%">满意</th>
                <th width="25%">基本满意</th>
                <th width="25%">不了解</th>
                <th width="25%">不满意</th>
            </tr>
        </thead>
        <tbody>
            <tr class="trWhite">
                <td class="tc" name="resultVO.result1" style="border:1px solid #e0e0e0;"></td>
                <td class="tc" name="resultVO.result2" style="border:1px solid #e0e0e0;"></td>
                <td class="tc" name="resultVO.result3" style="border:1px solid #e0e0e0;"></td>
                <td class="tc" name="resultVO.result4" style="border:1px solid #e0e0e0;"></td>

            </tr>
        </tbody>
         </table>
      </div>
      </div>
     <div id="reason"></div>
     <div class="toolBar">
			<!--筛选 end-->
			<div class="page" id="downIdPage">

			</div>
			<!--翻页 end-->
		</div>
		<div class="toolBar">
		    <div align="center">
		    	<input class="greenbtn mr10" type="button" onclick="backView();" value="返 回"/>
		    </div>
		</div>
	</body>
<script type="text/javascript">
	function backView(){
		window.location.href = '${baseURL}/orgvote/orgvoteAction!view.action?id=${param.voteOrgId}';
	}
$(document).ready(function() {
	doSearch(1);
});
function doSearch($pageIndex) {
	var id=$("#id").val();
	$.ajax(
					{  
						url:'${baseURL}/orgvote/orgvoteAction!ajaxSearchReason.action',
						data : {
							'page' : $pageIndex,
							'searchValue.voteId':id
						},
						dataType : 'json',
						success : function(result) {
							if ("0" == result.code) {
								var list1 = new ListTable(
										{
											checkableColumn : "id",
											title : [
											
													{
														width : "100%",
														name : "reason",
														showName : "不满意理由"
													} ],
											data : result.data.pageData,
											operations : [
													

											],
											trStyle : [ "trWhite" ]
										});
								list1.createList("reason");
								var pager = new Pager( {
									totalPages : result.data.maxPage,
									currentPage : result.data.currPage,
									funcName : "doSearch"
								});
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
</html>
