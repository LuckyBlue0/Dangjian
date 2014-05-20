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
		<title>组织积分修正</title>
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
					组织积分修正
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form
				action="${baseURL}/orgscore/orgscoreAction!ajaxAdd.action?searchValue.id=${param.id}"
				method="post" id="id_form_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tbody>
						<input type="hidden" name="tbNewsColumnTypePO.newsTypeId" />
						<tr
							datasrc="${baseURL}/orgManage/orgManageAction!ajaxView.action?id=${param.id}">
							<td width="120" class="tdBlue">
								组织名称
							</td>
							<td>
								<input style="width: 200px" type="text" id="organization"
									disabled="disabled" name="tbOrganizationPO.organizationName"
									valid="{must:false,length:30,fieldType:'pattern',tip:'组织名称'}" />
								<input name="tbOrganizationPO.id" id="orgId" type="hidden" />
								<input name="tbOrganizationScoreInfoPO.organizationId"
									id="organizationId" type="hidden" />

							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								积分类型
							</td>
							<td id="scoreTypeTd">
								<select style="width: 200px"
									name="tbOrganizationScoreInfoPO.scoreType" defaultTip=""
									dictType="scoreType" valid="{must:true,length:50,tip:'积分类型'}" />
							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								获得分数
							</td>
							<td>
								<input maxlength="18" type="text" style="width: 200px"
									name="tbOrganizationScoreInfoPO.score"
									valid="{must:true, length:18,fieldType:'pattern', tip:'分数'}" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								获得时间
							</td>
							<td>
								<input
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true})"
									maxlength="22" type="text" style="width: 130px"
									name="tbOrganizationScoreInfoPO.getTime"
									valid="{must:false, length:30,fieldType:'pattern', tip:'获得时间'}" />
							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								获得积分说明
							</td>
							<td>
								<textarea rows="4" cols="80"
									name="tbOrganizationScoreInfoPO.note"></textarea>
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
function selectParent() {
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp",
			"_blank", "height=600, width=800, top=100,left=200");
}

function doCheckedClick(id) {
	var chk_value = [];
	$('input[name="attrId"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	$('#attrIds').val(chk_value);
}
function doSave() {
	$("#organizationId").val($("#orgId").val());
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
