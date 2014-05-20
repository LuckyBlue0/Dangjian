<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="orgIdentity,personSex,degree,degreeIn"></jsp:param>
		<jsp:param name="permission" value="newsTypeAdd"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看转入用户信息</title>
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
					查看转入用户信息
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
		<form
			action="${baseURL}/organization/organizationAction!ajaxAudit.action"
			method="post" id="id_form_audit">
			<table class="tableCommon mt5" width="100%" border="0"
				cellspacing="0" cellpadding="0"
				dataSrc="${baseURL}/organization/organizationAction!ajaxViewTransferOut.action?id=${param.id}">
				<tbody>
					<input type="hidden" name="organizationTransferVO.id" />
					<input type="hidden" id="status"
						name="organizationTransferVO.status" />
					<tr>
						<td width="150" class="tdBlue">
							申请人姓名
						</td>
						<td width="35%" name="organizationTransferVO.userName">
						</td>
						<td width="150" class="tdBlue">
							年龄
						</td>
						<td width="35%" name="organizationTransferVO.birthday">
						</td>

					</tr>
					<tr>
						<td width="150" class="tdBlue">
							性别
						</td>
						<td width="35%" name="organizationTransferVO.sexDesc">
						</td>
						<td width="150" class="tdBlue">
							民族
						</td>
						<td width="35%" name="organizationTransferVO.national">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							手机号码
						</td>
						<td width="35%" name="organizationTransferVO.mobile">
						</td>
						<td width="150" class="tdBlue">
							身份证
						</td>
						<td width="35%" name="organizationTransferVO.idCard">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							政治面貌
						</td>
						<td width="35%"
							name="organizationTransferVO.politicalLandscapeDesc">
						</td>
						<td width="150" class="tdBlue">
							拟转入党组织
						</td>
						<td width="35%" name="organizationTransferVO.organizationNameInto">
						</td>
					</tr>

					<tr>
						<td width="150" class="tdBlue">
							住址
						</td>
						<td width="35%" colspan="3" name="organizationTransferVO.address">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							申请原因
						</td>
						<td width="35%" colspan="3"
							name="organizationTransferVO.intoDesc">
						</td>
					</tr>
					<tr>
						<td width="150" class="tdBlue">
							党组织意见
						</td>
						<c:if test="${param.status == 0}">
							<td width="35%" colspan="3">
								<textarea name="organizationTransferVO.auditDesc" rows="3"
									id="desc" cols="50"></textarea>
							</td>
						</c:if>
						<c:if test="${param.status != 0}">
							<td width="35%" colspan="3"
								name="organizationTransferVO.auditDesc" />
						</c:if>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="toolBar">
			<div align="center">
				<c:if test="${param.status == 0}">
					<input class="btn4" type="button" onclick="javascript:doAudit(1);"
						value="同意转出" />
					<input class="btn4" type="button" onclick="javascript:doAudit(2);"
						value="不同意" />
				</c:if>
				<input class="greenbtn mr10" type="button"
					onclick="javascript:history.back();" value="返 回" />
			</div>
		</div>
</div>
		<!--工具栏 end-->
		<script type="text/javascript">

$(document).ready(function() {
	flashIframe_GDZC();
});

function doAudit(status) {
	if (2 == status && $("#desc").val() == "") {
		alert("不同意请填写意见！");
		return;
	}
	if (1 == status) {
		msg = "确定同意其转出组织?";
	}
	if (2 == status) {
		msg = "确定不同意其转出组织?";
	}
	if (confirm(msg)) {
		$('#status').val(status);
		$('#id_form_audit').ajaxSubmit( {
			dataType : 'json',
			success : function(result) {
				if ("0" == result.code) {
					alert('操作成功！');
					history.back();
				} else {
					alert(result.desc);
				}
			},
			error : function() {
				alert('操作失败，请稍候再试');
			}
		});
	}

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
