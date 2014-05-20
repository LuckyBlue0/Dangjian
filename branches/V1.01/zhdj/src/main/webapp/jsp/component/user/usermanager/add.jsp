<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict"
			value="affairsWorkerStatu,volunteersStatus,degreeIn,userType,behalfStatus,hardPartyMembers,partyPosition,personSex,degree,politicalLandscape,isManagement"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增用户信息</title>
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
		<form action="${baseURL}/partymenber/partymenberAction!ajaxAdd.action"
			method="post" id="id_form_add">
			<div class="searchWrap">
				<div class="title">
					<h2 class="icon1">
						新增用户信息
					</h2>
				</div>
			</div>
			<div class="text_bg noneborder" id="bt">

				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td colspan="4">
								<div class="topTitle mb20">
									<span class="topName">基本资料</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 登录名
							</td>
							<td width="30%" colspan="2">
								<input type="text" name="tbPartyMenberInfoPO.userName"
									valid="{must:true,length:30,fieldType:'pattern',tip:'登录名'}" />
							</td>
							<td rowspan="6" align="center">
								<img align="top" id="img" src="${baseURL}/image/head.png" height="150px" />
								<br /><font size="1" color="gray">图片建议尺寸：200像素*300像素</font><br />
								
								<input type="button" value="选择头像" onClick="file.click();"
									class="greenbtn mr10">
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								手机号码
							</td>
							<td width="30%" colspan="2">
								<input maxlength="11" name="tbPartyMenberInfoPO.mobile"
									id="mobile" type="text"
									onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
									valid="{must:false, length:20,fieldType:'pattern', tip:'手机号码'}" />
								<p class="error" id="mobileError" style="display: none;"></p>
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 姓名
							</td>
							<td width="30%" colspan="2">
								<input class="form330px" name="tbPartyMenberInfoPO.name"
									type="text" valid="{must:true,length:50,tip:'姓名'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								性别
							</td>
							<td width="30%" colspan="2">
								<input type="radio" class="radio" name="tbPartyMenberInfoPO.sex"
									defaultValue="1" dictType="personSex"
									style="width: 13px; height: 13px; background: #000;"
									valid="{must:true,tip:'性别'}" />
							</td>
						</tr>
						<tr style="display: none;">
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
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 登录密码
							</td>
							<td width="30%" colspan="2">
								<input type="password" id="password"
									name="tbPartyMenberInfoPO.password"
									valid="{must:true, length:100,fieldType:'pattern', tip:'登陆密码'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 确认密码
							</td>
							<td width="30%" colspan="2">
								<input type="password" id="password2"
									valid="{must:true, length:100,fieldType:'pattern', tip:'确认密码'}" />
								<p class="error" id="pwdTip" style="display: none;"></p>
							</td>
						</tr>
						<tr>
						<td width="130" class="tdBlue">
								是否管理员
							</td>
							<td width="30%">
								<input type="radio" name="tbPartyMenberInfoPO.isManagement"
									defaultValue="0" dictType="isManagement"
									valid="{must:true,tip:'是否管理员'}" />
							</td>
						</tr>
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
function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=130");
}

function doCheckedClick(id) {
	var chk_value = [];
	$('input[name="attrId"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	$('#attrIds').val(chk_value);
}
function doSave() {

	//验证1开头的手机号码是否合法
	if ($('#mobile').val() != '' && !/^[1]\d{10}$/.test($('#mobile').val())) {
		$("#mobileError").text("请输入正确的手机号");
		$("#mobileError").show();
		return;
	} else {
		$("#mobileError").hide();
	}
	//验证密码
	if ($("#password").val() != ""
			&& $("#password").val() != $("#password2").val()) {
		$("#pwdTip").text("两次输入的密码不一致，请重新输入");
		$("#pwdTip").show();
		return;
	} else {
		$("#pwdTip").hide();
	}

alert("1")
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
			"${baseURL}/partymenber/partymenberAction!ajaxAdd.action");
}
</script>
	</body>
</html>
