<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict"
			value="leaderStatus"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>编辑首页信息</title>
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
					编辑用户信息
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form
				action="${baseURL}/sharemanager/sharemanagerAction!modifyTbShareInfoPO.action"
				method="post" id="id_form_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0"
					dataSrc="${baseURL}/sharemanager/sharemanagerAction!ajaxView.action?id=${param.id}">
					<tbody>
						<tr>
							<td class="tdBlue">
								<span style="color: red">*</span> 主题
								<input name="tbShareInfoVO.id" type="hidden" />
							</td>
							<td>
								<input type="text" name="tbShareInfoVO.title"
									valid="{must:true,length:30,fieldType:'pattern',tip:'主题'}" />
							</td>
						</tr>
					</tbody>
				</table>
				<div class="toolBar">
					<div align="center">
						<input class="greenbtn mr10" type="button" permission=""
							onclick="doSave();" value="保存" />
						<input class="greenbtn mr10" type="button"
							onclick="javascript:history.back();" value="返 回" />
					</div>
				</div>
			</form>


		</div>
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	$("#passWord").val('******');
	$("#passWord2").val('******');
	var imgPath = $("#imgPath").val();
	if ('' != imgPath) {
		$("#img").attr("src", "${baseURL}/" + imgPath);
		$("#img").css('display', 'block');
	}
});


function doCheckedClick(id) {
	var chk_value = [];
	$('input[name="attrId"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	$('#attrIds').val(chk_value);
}
function doSave() {
	_doCommonSubmit("id_form_add", null, {
		ok : function() {
			history.back();
		},
		fail : function() {
		}
	});
}

function uploadFile() {
	$('#id_form_add').attr("action",
			"${baseURL}/orgManage/orgManageAction!fileUpload.action");
	$('#id_form_add').ajaxSubmit( {
		dataType : 'json',
		success : function(result) {
			if ("0" == result.code) {
				$("#img").attr("src", "${baseURL}/" + result.desc);
				$("#img").css("display", "");
				$("#imgPath").val(result.desc);
			} else {
				alert(result.desc);
			}
		},
		error : function() {
			alert('文件上传失败，请稍候再试');
		}
	});
	$('#id_form_add').attr("action",
			"${baseURL}/sharemanager/sharemanagerAction!modifyTbShareInfoPO.action");
}
</script>
	</body>
</html>
