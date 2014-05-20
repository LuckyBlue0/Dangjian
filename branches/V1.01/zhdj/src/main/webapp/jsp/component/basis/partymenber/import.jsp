<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict"
			value="affairsWorkerStatu,volunteersStatus,behalfStatus,hardPartyMembers,partyPosition,personSex,degree,politicalLandscape"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>批量导入党员信息类型</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
		<link
			href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css"
			rel="stylesheet" type="text/css" />
		<script src="${baseURL}/js/DatePicker/WdatePicker.js">
</script>
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>">
		</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script
			src="${baseURL}/js/3rd-plug/jquery-ui-1.8/development-bundle/ui/jquery.ui.progressbar.js">
</script>
		<style type="text/css">
#BgDiv {
	background-color: #e3e3e3;
	position: absolute;
	z-index: 99;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 1000px;
	opacity: 0.5;
	filter: alpha(opacity =                                     50);
	-moz-opacity: 0.5;
}
</style>
	</head>

	<body style="background: #f6ebe5;">
		<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					批量导入党员信息
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
		<div class="text_bg" id="bt">
			<form
				action="${baseURL}/partymenber/partymenberAction!fileUpload.action"
				method="post" id="id_form_add">
				<input name="tbPartyMenberInfoPO.userType" type="hidden"
					value="${param.userType}" />
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="20%" class="tdBlue">
								导入模板：
							</td>
							<td width="80%">
								<c:if test="${param.userType == 3}">
									<input type="button" value="下载模板"
										onclick="window.location.href='${baseURL}/jsp/template/历史党员信息表.xls'"
										class="greenbtn mr10" />
								</c:if>
								<c:if test="${param.userType != 3}">
									<input type="button" value="下载模板"
										onclick="window.location.href='${baseURL}/jsp/template/党员信息表.xls'"
										class="greenbtn mr10" />
								</c:if>
							</td>
						</tr>
						<tr>
							<td width="20%" class="tdBlue">
								<span style="color: red">*</span> 选择文件：
							</td>
							<td width="80%">
								<input name="file" id="file" type="file" />
								<input name="filePath" id="filePath" value="" type="hidden"
									valid="{must:false,tip:'模板文件'}" />
								<input type="button" value="开始导入" onclick="importFile();"
									class="greenbtn mr10" />
								<br />
								<span style="color: red">
									备注：先【下载模板】，按照excel模板中的内容填充，然后【开始导入】，导入需要20秒至几分钟不等，请耐心等候。 </span>
							</td>
						</tr>
						<tr>
							<td width="20%" class="tdBlue">
								导入结果：
							</td>
							<td width="80%">
								<div id="msg"></div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="toolBar">
					<div align="center">
						<input class="greenbtn mr10" type="button"
							onclick="javascript:history.back();" value="返 回" />
					</div>
				</div>
			</form>
		</div>
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {

});
function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=200");
}

function importFile() {

	$('#id_form_add').ajaxSubmit( {
		dataType : 'json',
		beforeSend : function() {
			ShowDIV('BgDiv2');
		},
		success : function(result) {
			alert(result.desc);
			closeDiv('BgDiv2');
			$("#msg").html(result.data.msg);
		},
		error : function() {
			alert("导入失败!");
			closeDiv('BgDiv2');
		}
	});
}
function ShowDIV(thisObjID) {
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#" + thisObjID).css("top", "100px");
	$("#" + thisObjID).css("display", "block");
	document.documentElement.scrollTop = 0;
}

function closeDiv(thisObjID) {
	$("#BgDiv").css("display", "none");
	$("#" + thisObjID).css("display", "none");
}
</script>
		<div id="BgDiv"></div>
		<div id="BgDiv2" align="center" style="display: none">
			<img src="${baseURL}/jsp/web/images/loading.gif" alt="" />
			<br />
			<font size="2">正在导入，请稍后……</font>
		</div>
	</body>
</html>
