<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict"
			value=""></jsp:param>
		<jsp:param name="permission" value="indexshowAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增首页信息</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/imgslide.css"
			rel="stylesheet" type="text/css" />

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
		<form action="${baseURL}/indexshow/indexshowAction!addTbProjectInfoPO.action"
			method="post" id="id_form_add">
			<div class="searchWrap">
				<div class="title">
					<h2 class="icon1">
						新增首页信息
					</h2>
				</div>
			</div>
			<div class="text_bg noneborder" id="bt">

				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td  class="tdBlue">
								<span style="color: red">*</span> 项目名称
							</td>
							<td >
								<input type="text" name="tbProjectInfoPO.proName"
									valid="{must:true,length:100,fieldType:'pattern',tip:'项目名称'}" />
							</td>
							<%-- <td rowspan="6" align="center">
								<img align="top" id="img" src="${baseURL}/image/head.png" height="150px" />
								<br /><font size="1" color="gray">图片建议尺寸：200像素*300像素</font><br />
								
								<input type="button" value="选择头像" onClick="file.click();"
									class="greenbtn mr10">
							</td> --%>
						</tr>
						<tr>
							<td class="tdBlue">
								项目描述
							</td>
							<td >
								<input  name="tbProjectInfoPO.proDesc"  type="text"

									valid="{must:false, length:400,fieldType:'pattern', tip:'项目描述'}" />
							</td>
						</tr>
						<!-- <tr style="display: none;">
							<td width="130" class="tdBlue">
								用户头像
							</td>
							<td width="30%" colspan="2">
								<input name="file" id="file" type="file" onchange="uploadFile();" placeholder="图片建议尺寸：360像素 * 200像素" />
								<input name="tbPartyMenberInfoPO.portraitPic" id="imgPath"
									value="" type="hidden" valid="{must:false,tip:'头像'}" />
								<input type="button" value="上 传" onClick="uploadFile();"
									class="greenbtn mr10">
							</td>
						</tr> -->
					</tbody>
				</table>
				<div class="toolBar">
					<div align="center">
						<input class="greenbtn mr10" type="button" onclick="doSave()" value="保存" />
						<input class="greenbtn mr10" type="button"
							onclick="javascript:history.back();" value="返 回" />
					</div>
				</div>
			</div>
		</form>

		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
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

function flashIframe_GDZC() {
	var i = parent.document.getElementById("ifm");
	if (i != null) {
		if (document.body.scrollHeight < 700) {
			i.style.height = "730px";
		} else {
			i.style.height = (document.body.scrollHeight + 80) + "px";
		}
	}
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
			"${baseURL}/indexshow/indexshowAction!addTbProjectInfoPO.action");
}
</script>
	</body>
</html>
