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
		<title>党员发展管理</title>
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
					党员发展管理
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form action="" method="post" id="id_form_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0"
					dataSrc="${baseURL}/partydevelopment/partydevelopmentAction!ajaxManagementView.action?id=${param.id}">
					<tbody>
						<tr>
							<td colspan="4">
								<input type="hidden" name="partyDevApplyForVO.id" id="poId" />
								<input type="hidden"
									name="partyDevApplyForVO.applyForOrgIdentity"
									id="applyForOrgIdentity" />
								<input type="hidden" name="tbPartyDevelopmentMenberPO.id"
									id="userId" />
								<table class="search" width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td width="50" height="30" class="m10">
											&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
											<b> 组织身份：<font
												name="partyDevApplyForVO.organizationIdentityDesc"></font> </b>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input class="gbtn2"
												style="width: 130px; height: 30px; color: white;"
												name="partyDevApplyForVO.applyForOrgIdentityDesc"
												type="button" onclick="changeIdentity(1);" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input class="gbtn2" value="不同意" type="button"
												style="width: 130px; height: 30px; color: white;"
												onclick="changeIdentity(2);" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="topTitle mb20">
									<span class="topName">入党申请资料</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								提交申请书时间
							</td>
							<td width="30%" colspan="3" name="partyDevApplyForVO.createTime">
							</td>
						</tr>
						<tr>
							<td width="130" class="tdBlue">
								姓名
							</td>
							<td width="30%" name="tbPartyDevelopmentMenberPO.name">
							</td>
							<td width="150" class="tdBlue">
								性别
							</td>
							<td width="35%" name="tbPartyDevelopmentMenberPO.sexDesc">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								民族
							</td>
							<td width="35%" name="tbPartyDevelopmentMenberPO.national">
							</td>
							<td width="150" class="tdBlue">
								出生年月
							</td>
							<td width="35%" name="tbPartyDevelopmentMenberPO.birthday">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								学历
							</td>
							<td width="35%" name="tbPartyDevelopmentMenberPO.degreeDesc">
							</td>
							<td width="150" class="tdBlue" id="degreeInTD1">
								学位
							</td>
							<td width="35%" id="degreeInTD2"
								name="tbPartyDevelopmentMenberPO.degreeInDesc">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								籍贯
							</td>
							<td width="35%" colspan="3"
								name="tbPartyDevelopmentMenberPO.nativePlace">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								单位、职务或职业
							</td>
							<td width="35%" colspan="3"
								name="tbPartyDevelopmentMenberPO.unit">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								现居住地
							</td>
							<td width="35%" colspan="3"
								name="tbPartyDevelopmentMenberPO.homeAddress">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								身份证号
							</td>
							<td width="35%" colspan="3"
								name="tbPartyDevelopmentMenberPO.idCard">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								有何特长
							</td>
							<td colspan="3" name="partyDevApplyForVO.specialty">
							</td>
						</tr>
						<tr>
							<td width="150" class="tdBlue">
								入党志愿
							</td>
							<td colspan="3" name="partyDevApplyForVO.applyForDesc">
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="topTitle mb20">
									<span class="topName">审核记录</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="130" colspan="4">
								<table name="partyDevApplyForList">
									<tr>
										<td>
											列为
											<font name="applyForOrgIdentityDesc"></font>时间
										</td>
										<td name="auditTime"></td>
										<td>
											审核人
										</td>
										<td name="auditUserName"></td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="toolBar">
					<div align="center">
						<input class="greenbtn mr10" type="button"
							onclick="javascript:history.back();" value="返 回" />
					</div>
				</div>
			</form>


		</div>
		<!--工具栏 end-->
		<script type="text/javascript">

function changeIdentity(status) {
	$
			.ajax( {
				data : {
					'tbPartyDevApplyForPO.id' : $("#poId").val(),
					'tbPartyDevApplyForPO.status' : status,
					'tbPartyDevApplyForPO.applyForOrgIdentity' : $(
							"#applyForOrgIdentity").val(),
					'tbPartyDevApplyForPO.userId' : $("#userId").val()
				},
				url : '${baseURL}/partydevelopment/partydevelopmentAction!ajaxChangeIdentity.action',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if ('0' == result.code) {
						alert('操作成功！');
						history.back();
					} else {
						alert(result.desc);
					}
				}
			});

}

$(document).ready(function() {
	var imgPath = $("#imgPath").val();
	if ('' != imgPath) {
		$("#img").attr("src", "${baseURL}/" + imgPath);
		$("#img").css('display', 'block');
	}
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
