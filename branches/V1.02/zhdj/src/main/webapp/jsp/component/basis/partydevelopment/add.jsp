<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="orgIdentity,personSex,degree,degreeIn"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增发展党员信息</title>
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
					新增发展党员信息
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form
				action="${baseURL}/partydevelopment/partydevelopmentAction!ajaxAdd.action"
				method="post" id="id_form_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tbody>
						<input type="hidden" name="tbNewsColumnTypePO.newsTypeId" />
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
								<input type="text" name="tbPartyDevelopmentMenberPO.userName"
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
								<span style="color: red">*</span> 登录密码
							</td>
							<td width="30%" colspan="2">
								<input type="password" id="passWord"
									name="tbPartyDevelopmentMenberPO.passWord"
									valid="{must:true, length:100,fieldType:'pattern', tip:'登陆密码'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 确认密码
							</td>
							<td width="30%" colspan="2">
								<input type="password" id="passWord2"
									valid="{must:true, length:100,fieldType:'pattern', tip:'确认密码'}" />
								<span style="color: red" id="pwdTip"></span>

							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 姓名
							</td>
							<td width="30%" colspan="2">
								<input name="tbPartyDevelopmentMenberPO.name" type="text"
									valid="{must:true,length:50,tip:'姓名'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								<span style="color: red">*</span> 手机号码
							</td>
							<td width="30%" colspan="2">
								<input maxlength="11" name="tbPartyDevelopmentMenberPO.mobile"
									onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
									valid="{must:true, length:20,fieldType:'pattern', tip:'手机号码'}" />
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								性别
							</td>
							<td width="35%" colspan="2">
								<input type="radio" name="tbPartyDevelopmentMenberPO.sex"
									defaultValue="1" dictType="personSex"
									valid="{must:true,tip:'性别'}" />
							</td>
						</tr>
						<tr style="display: none;">
							<td width="130" class="tdBlue">
								用户头像
							</td>
							<td width="30%" colspan="2">
								<input name="file" id="file" onchange="uploadFile();" type="file" style="width: 300px;" />
								<input name="tbPartyDevelopmentMenberPO.portraitPic"
									id="imgPath" class="form230px" value="" type="hidden"
									valid="{must:false,tip:'头像'}" />
								<input type="button" value="上 传" onClick="uploadFile();"
									class="greenbtn mr10">
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="topTitle mb20">
									<span class="topName">详细资料</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								身份证号
							</td>
							<td width="35%">
								<input maxlength="18" type="text"
									name="tbPartyDevelopmentMenberPO.idCard"
									valid="{must:false, length:18,fieldType:'pattern', tip:'身份证号'}" />
							</td>
							<td width="150" class="tdBlue">
								民族
							</td>
							<td width="35%">
								<input maxlength="18" type="text"
									name="tbPartyDevelopmentMenberPO.national"
									valid="{must:false, length:18,fieldType:'pattern', tip:'民族'}" />
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								学历
							</td>
							<td width="35%">
								<select defaultTip="" onchange="changeDegree(this);"
									name="tbPartyDevelopmentMenberPO.degree" dictType="degree"
									valid="{must:false,length:50,tip:'学历'}" />
							</td>
							<td width="150" class="tdBlue" id="degreeInTD1"
								style="display: none;">
								学位
							</td>
							<td width="35%" id="degreeInTD2" style="display: none;">
								<select id="degreeIn" name="tbPartyDevelopmentMenberPO.degreeIn"
									dictType="degreeIn" valid="{must:false,length:50,tip:'学位'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								组织身份
							</td>
							<td width="30%" colspan="3">
								<select defaultTip="" defaultValue="0"
									name="tbPartyDevelopmentMenberPO.organizationIdentity"
									dictType="orgIdentity" valid="{must:true,length:50,tip:'组织身份'}" />
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								所属组织
							</td>
							<td width="30%" colspan="3">
								<input id="parentName" name="parentName" value="${param.name}"
									type="text" readonly="readonly"
									valid="{must:false,tip:'所属组织',fieldType:'pattern',length:100}" />
								<a href="javascript:selectParent()"><img
										src="/zhdj/image/ss_bt.png" /> </a>
								<input type="hidden"
									name="tbPartyDevelopmentMenberPO.organizationId" id="parentId" />
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								籍贯
							</td>
							<td width="35%" colspan="3">
								<input name="tbPartyDevelopmentMenberPO.nativePlace"
									valid="{must:false, length:100,fieldType:'pattern', tip:'籍贯'}" />
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								出生年月
							</td>
							<td width="35%" colspan="3">
								<input onclick=""
									onFocus="WdatePicker({dateFmt:'yyyy-MM',autoPickDate:true,readOnly:true,isShowWeek:true})"
									name="tbPartyDevelopmentMenberPO.birthday"
									valid="{must:false, length:30,fieldType:'pattern', tip:'出生年月'}" />
							</td>
						</tr>

						<tr>
							<td width="150" class="tdBlue">
								单位、职务或职业
							</td>
							<td width="35%" colspan="3">
								<input type="text" name="tbPartyDevelopmentMenberPO.unit"
									valid="{must:false, length:300,fieldType:'pattern', tip:'所在单位'}" />
							</td>
						</tr>

						<tr>
							<td width="150" class="tdBlue">
								现居住地
							</td>
							<td width="35%" colspan="3">
								<input type="text" name="tbPartyDevelopmentMenberPO.homeAddress"
									valid="{must:false, length:50,fieldType:'pattern', tip:'现居住地'}" />
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								备注说明
							</td>
							<td colspan="3">
								<textarea rows="4" cols="60"
									name="tbPartyDevelopmentMenberPO.note"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="toolBar">
					<div align="center">
						<input class="greenbtn mr10" type="button"
							permission="newsTypeAdd" onclick="doSave();" value="保存" />
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
	if ($("#passWord2").val() != ""
			&& $("#passWord").val() != $("#passWord2").val()) {
		$("#pwdTip").html("<br/>两次输出密码不一致，请重新输入！");
		return;
	} else {
		$("#pwdTip").html("");
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
