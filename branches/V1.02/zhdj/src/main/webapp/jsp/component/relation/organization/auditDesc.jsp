<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="permission" value="personalScoreEdit"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
		<link
			href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css"
			rel="stylesheet" type="text/css" />
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/jsp/component/systemmgr/js/CheckboxTable.js">
</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="../js/CheckboxTable.js">
</script>
	</head>

	<body>
		<form
			action="${baseURL}/organization/organizationAction!ajaxAudit.action"
			method="post" id="id_form_audit">
			<input id="id" name="personalScoreInfoVO.id" type="hidden" />
			<table class="tableCommon mt5" width="100%" border="0"
				cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td width="120" class="tdBlue">
							<textarea rows="5" cols="51" id="auditDesc" name="organizationTransferVO.auditDesc"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="toolBar">
			<div align="center">
				<input class="greenbtn mr10" type="button" permission="personalScoreEdit"
					onclick="doSave();" value="确定" />
				<input class="greenbtn mr10" type="button"
					onclick="javascript:closeWindow();" value="取消" />
			</div>
		</div>

		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	init();
});

function closeWindow() {
	window.parent.tipsWindown.close();
}

function doCheckedClick(id) {
	var chk_value = [];
	$('input[name="attrId"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	$('#attrIds').val(chk_value);
}
function doSave() {
	$('#id_form_audit').ajaxSubmit( {
		data : {
			'organizationTransferVO.id' : ${param.id},
			'organizationTransferVO.status' : 2
		},
		dataType : 'json',
		success : function(result) {
			alert(result.desc);
			closeWindow();
			window.parent.doSearch(1);
		},
		error : function() {
			alert("操作失败！");
		}
	});
}
</script>
	</body>
</html>
