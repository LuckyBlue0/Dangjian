<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="scoreLeave"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增积分星级信息</title>
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
					编辑积分星级信息
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form
				action="${baseURL}/scoreLeave/scoreLeaveAction!ajaxUpdate.action"
				method="post" id="id_form_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0"
					dataSrc="${baseURL}/scoreLeave/scoreLeaveAction!ajaxView.action?id=${param.id}">
					<tbody>
						<input type="hidden" name="tbScoreLeavePO.id" />
						<tr>
							<td width="50" class="tdBlue">
								星级名称
							</td>
							<td width="80%" colspan="3">
								<input style="width: 230px" type="text"
									name="tbScoreLeavePO.name"
									valid="{must:true,length:30,fieldType:'pattern',tip:'登录名'}" />
								<span style="color: red">*</span>
							</td>
						</tr>

						<tr>
							<td width="50" class="tdBlue">
								属性
							</td>
							<td width="80%" colspan="3">
								<select style="width: 240px" defaultTip=""
									name="tbScoreLeavePO.type" dictType="scoreLeave"
									valid="{must:false,length:50,tip:'属性'}" />
							</td>
						</tr>
						<tr>
							<td width=50 " class="tdBlue">
								分值区间
							</td>
							<td width="80%" colspan="3">
								大于等于
								<input style="width: 150px" maxlength="11"
									name="tbScoreLeavePO.startScore"
									onkeyup="this.value=this.value.replace(/[^\d]/g,'')" id="start"
									valid="{must:true, length:10,fieldType:'pattern', tip:'最小值'}" />
								<span style="color: red">*</span> 小于
								<input style="width: 150px" maxlength="11"
									name="tbScoreLeavePO.endScore"
									onkeyup="this.value=this.value.replace(/[^\d]/g,'')" id="end"
									valid="{must:true, length:10,fieldType:'pattern', tip:'最大值'}" />
								<span style="color: red">*</span>
								<br />
								<br />
								<p id="errorMsgText" class="error" style="display: none;"></p>
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
	$('#id_form_add')
			.attr("action",
					"${baseURL}/partydevelopment/partydevelopmentAction!ajaxAdd.action");
}
function doCheckedClick(id) {
	var chk_value = [];
	$('input[name="attrId"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	$('#attrIds').val(chk_value);
}
function doSave() {
	if ($("#start").val() == '') {
		$("#errorMsgText").text("最小分值不能为空");
		$("#errorMsgText").show();
		return;
	} else if ($("#end").val() == '') {
		$("#errorMsgText").text("最大分值不能为空");
		$("#errorMsgText").show();
		return;
	} else if ($("#start").val() != '' && $("#end").val() != ''
			&& $("#start").val() >= $("#end").val()) {
		$("#errorMsgText").text("最大分值必须大于最小分值");
		$("#errorMsgText").show();
		return;
	} else {
		$("#errorMsgText").hide();
	}
	_doCommonSubmit("id_form_add", null, {
		ok : function() {
			history.back();
		},
		fail : function() {
		}
	});
}
function changeDegree(obj) {
	if (obj.value == '6' || obj.value == '7') {
		$("#degreeInTD1").show();
		$("#degreeInTD2").show();
	} else {
		$("#degreeIn").val('');
		$("#degreeInTD1").hide();
		$("#degreeInTD2").hide();
	}
}
function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=130");
}
function flashIframe_GDZC() {
	var i = parent.document.getElementById("ifm");
	if (i != null) {
		if (document.body.scrollHeight < 700) {
			i.style.height = "730px";
		} else {
			i.style.height = (document.body.scrollHeight + 30) + "px";
		}
	}
}
</script>
	</body>
</html>
