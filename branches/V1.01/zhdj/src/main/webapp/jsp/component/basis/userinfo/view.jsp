<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="permission" value="roleList"></jsp:param>
		<jsp:param name="mustPermission" value="userAdd"></jsp:param>
		<jsp:param name="dict"
			value="affairsWorkerStatu,organizationType,newType"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看用户信息</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
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

	<body>
		<form
			action="${baseURL}/userinfo/userinfoAction!ajaxView.action?id=${param.id}"
			method="post" id="org_add">
			<table class="tableCommon mt5" width="100%" border="0"
				dataSrc="${baseURL}/userinfo/userinfoAction!ajaxView.action?id=${param.id}"
				cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th colspan="4">
							查看用户信息
						</th>
					</tr>
				</thead>
				<tbody>
					<input type="hidden" name="userInfoVO.id" />
					<tr>
						<td colspan="4">
							用户基本信息
						</td>
					</tr>
					<input type="hidden" name="userInfoVO.id" />
					<tr>
						<td width="150" class="tdBlue">
							用户名
						</td>
						<td width="35%" name="userInfoVO.userName">
						</td>

						<td width="150" rowspan="5" class="tdBlue">
							头像
						</td>
						<td width="35%" rowspan="5">
							<input name="userInfoVO.portraitPic" id="imgPath"
								class="form150px" value="" type="hidden"
								valid="{must:false,tip:'头像'}" />
							<img id="img" src="" height="100px" width="100px"
								style="display: none;" />
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							姓名
						</td>
						<td width="35%" name="userInfoVO.name">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							登陆密码
						</td>
						<td width="35%">
							******
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							性别
						</td>
						<td width="35%" name="userInfoVO.sexDesc">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							身份证号
						</td>
						<td width="35%" name="userInfoVO.idCard">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							注册时间
						</td>
						<td width="35%" name="userInfoVO.regTime">
							<select />
						</td>
						<td width="150" class="tdBlue">
							注册渠道
						</td>
						<td width="35%" name="userInfoVO.registeredChannelsDesc">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							学历
						</td>
						<td width="35%" name="userInfoVO.degreeDesc">
						</td>
						<td width="150" class="tdBlue">
							政治面貌
						</td>
						<td width="35%" name="userInfoVO.politicalLandscapeDesc">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							出生年月
						</td>
						<td width="35%" name="userInfoVO.birthday">
						</td>
						<td width="150" class="tdBlue">
							电子邮箱
						</td>
						<td width="35%" name="userInfoVO.email">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							手机号码
						</td>
						<td width="35%" name="userInfoVO.mobile">
						</td>
						<td width="150" class="tdBlue">
							QQ号码
						</td>
						<td width="35%" name="userInfoVO.qq">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							家庭住址
						</td>
						<td width="35%" colspan="3" name="userInfoVO.homeAddress">

						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							备注
						</td>
						<td width="35%" colspan="3" name="userInfoVO.note">
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="toolBar">
			<div align="center">
				<input class="btn4" type="button"
					onclick="javascript:history.back();" value="返 回" />
			</div>
		</div>

		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	init();
	var imgPath = $("#imgPath").val();
	if ('' != imgPath) {
		$("#img").attr("src", "${baseURL}/" + imgPath);
		$("#img").css('display', 'block');
	}
});
/**
 * 初始化标签属性数据
 */
function init() {
	flashIframe_GDZC();
	$("#password").val('');
}

function setAttr(modelList) {
	var checkboxHTML = "";
	for ( var i = 0; i < modelList.length; i++) {
		checkboxHTML = checkboxHTML + "<input type=\"checkbox\" id=\"checkId_"
				+ modelList[i].labelModelId + "\" name=\"attrId\" value=\""
				+ modelList[i].labelModelId + "\" onClick=\"doCheckedClick('"
				+ modelList[i].labelModelId + "');\" />&nbsp;"
				+ modelList[i].labelName
				+ "\&nbsp;\&nbsp;\&nbsp;\&nbsp;&nbsp;\&nbsp;\&nbsp;";
		if (i / 5 == 1) {
			checkboxHTML = checkboxHTML + "</br></br>";
		}
	}
	$('#td_attr').append(checkboxHTML);
}

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
			"${baseURL}/partymenber/partymenberAction!ajaxUpdate.action");
}
</script>
	</body>
</html>