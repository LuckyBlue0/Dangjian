<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="permission" value=""></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
		<jsp:param name="dict"
			value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看用户信息</title>
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
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="../js/CheckboxTable.js">
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<div class="searchWrap">
			<div class="title">
				<h2 class="icon1">
					查看分享信息
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form
				action="${baseURL}/sharemanager/sharemanagerAction!ajaxView.action?id=${param.id}"
				method="post" id="org_add">
				<table class="tableCommon mt5" width="100%" border="0"
					dataSrc="${baseURL}/sharemanager/sharemanagerAction!ajaxView.action?id=${param.id}"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td class="tdBlue">
								主题
								<input name="tbShareInfoVO.id" type="hidden" />
							</td>
							<td  name="tbShareInfoVO.title">
							</td>
						</tr>
						
						
					</tbody>
				</table>
			</form>

			<div class="toolBar">
				<div align="center">
					<input class="greenbtn mr10" type="button"
						onclick="javascript:history.back();" value="返 回" />
				</div>
			</div>
		</div>
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
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
			window.location.href = "list.jsp";
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
				alert('文件上传成功！');
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