<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="scoreType"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>积分修正</title>
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
	</head>

	<body style="background: #f6ebe5;">
	<div class="searchWrap">
				<div class="title">
					<h2 class="icon1">
						积分修正
					</h2>
				</div>
			</div>
			<div class="text_bg noneborder" id="bt">
		<form
			action="${baseURL}/personalscore/personalscoreAction!ajaxAdd.action?searchValue.id=${param.id}"
			method="post" id="id_form_add">
			<table class="tableCommon mt5" width="100%" border="0"
				cellspacing="0" cellpadding="0">
				<tbody>
					<tr
						datasrc="${baseURL}/partymenber/partymenberAction!ajaxView.action?id=${param.id}">
						<td width="120" class="tdBlue">
							用户姓名
						</td>
						<td>
							<input style="width: 200px" type="text" id="organization"
								disabled="disabled" name="partyMenberInfoVO.name"
								valid="{must:false,length:30,fieldType:'pattern',tip:'用户姓名'}" />
							<input name="partyMenberInfoVO.id" id="pmId" type="hidden" />
							<input name="tbPersonalScoreInfoPO.userId" id="userId"
								type="hidden" />

						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							积分类型
						</td>
						<td id="scoreTypeTd">
							<select style="width: 200px"
								name="tbPersonalScoreInfoPO.scoreType" defaultTip=""
								dictType="scoreType" valid="{must:true,length:50,tip:'积分类型'}" />
						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							分数
						</td>
						<td>
							<input maxlength="18" type="text" style="width: 200px"
								name="tbPersonalScoreInfoPO.score"
								valid="{must:true, length:18,fieldType:'pattern', tip:'分数'}" />
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							获得积分说明
						</td>
						<td>
							<textarea rows="4" cols="80"
								name="tbPersonalScoreInfoPO.scoreDesc"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="toolBar">
			<div align="center">
				<input class="greenbtn mr10" type="button" permission="newsTypeAdd"
					onclick="doSave();" value="保存" />
				<input class="greenbtn mr10" type="button"
					onclick="javascript:history.back();" value="返 回" />
			</div>
		</div>
</div>
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {

});
function doSave() {
	$("#userId").val($("#pmId").val());
	_doCommonSubmit("id_form_add", null, {
		ok : function() {
			window.location.href = "info.jsp?id=${param.id}";
		},
		fail : function() {
		}
	});
}
</script>
	</body>
</html>
