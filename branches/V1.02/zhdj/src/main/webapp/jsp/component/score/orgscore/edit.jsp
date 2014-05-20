<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="permission" value="orgScoreEdit"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>编辑积分信息</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
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
		<form action="${baseURL}/orgscore/orgscoreAction!ajaxUpdate.action"
			method="post" id="id_form_edit">
			<input id="id" name="tbOrganizationScoreInfoPO.id" type="hidden" />
			<table class="tableCommon mt5" width="100%" border="0"
				cellspacing="0" cellpadding="0"
				dataSrc="${baseURL}/orgscore/orgscoreAction!ajaxView.action?id=${param.id}">
				<tbody>
					<tr>
						<td width="120" class="tdBlue">
							加分类型
						</td>
						<td width="65%" id="scoreTypeDesc">
						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							获得积分
						</td>
						<td width="65%">
							<input id="score"
								onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
								maxlength="5"
								valid="{must:false, length:5,fieldType:'pattern', tip:'积分'}"
								name="tbOrganizationScoreInfoPO.score" />
						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							获得方式
						</td>
						<td width="65%" id="scoreFrom">

						</td>
					</tr>
					<tr>
						<td width="120" class="tdBlue">
							录入时间
						</td>
						<td width="65%" id="getTime">

						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="toolBar">
			<div align="center">
				<input class="btn4" type="button" permission="orgScoreEdit"
					onclick="doSave();" value="确认修复" />
				<input class="btn4" type="button"
					onclick="javascript:closeWindow();" value="取消" />
			</div>
		</div>

		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	init();
});
/**
 * 初始化标签属性数据
 */
function init() {
	var organizationScoreInfoVO;
	$
			.ajax( {
				data : {
					'id' : '${param.id}'
				},
				url : '${baseURL}/orgscore/orgscoreAction!ajaxView.action',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ('0' == result.code) {
						organizationScoreInfoVO = result.data.organizationScoreInfoVO;
						$("#id").val(organizationScoreInfoVO.id);
						$("#score").val(organizationScoreInfoVO.score);
						$("#scoreTypeDesc").text(
								organizationScoreInfoVO.scoreTypeDesc);
						$("#getTime").text(organizationScoreInfoVO.getTime);
						$("#scoreFrom").text(organizationScoreInfoVO.scoreFrom);
					} else {
						alert(result.desc);
					}
				}
			});
}

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
	$.ajax( {
		data : {
			'tbOrganizationScoreInfoPO.id' : $("#id").val(),
			'tbOrganizationScoreInfoPO.score' : $("#score").val()
		},
		url : '${baseURL}/orgscore/orgscoreAction!ajaxUpdate.action',
		dataType : 'json',
		success : function(result) {
			alert(result.desc);
			closeWindow();
			window.parent.doSearch(1);
		},
		error : function() {
			alert("修改失败！");
		}
	});
}
</script>
	</body>
</html>
