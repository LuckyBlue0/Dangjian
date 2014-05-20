<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="permission" value=""></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
		<jsp:param name="dict" value="dataType"></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改积分规则</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
		<link type="text/css" rel="stylesheet"
			href="${baseURL}/css/progressBar.css" />
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>">
		</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="${baseURL}/js/progressBar.js">
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<div class="searchWrap">
			<div class="title">
				<h2 class="icon1">
					修改积分规则
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
			<form action="${baseURL}/scorerule/scoreruleAction!ajaxUpdate.action"
				method="post" id="org_add">
				<table class="tableCommon mt5" width="100%" border="0"
					cellspacing="0" cellpadding="0"
					datasrc="${baseURL}/scorerule/scoreruleAction!ajaxView.action?id=${param.id}">
					<input type="hidden" name="tbScoreRulePO.id" />
					<tbody>
						<tr>
							<td width="120" class="tdBlue">
								积分类型
							</td>
							<td width="500" name="scoreType">

							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								分值
							</td>
							<td width="500">
								<input class="form120px" name="tbScoreRulePO.scoreValue"
									id="value" style="width: 400px" type="text"
									valid="{must:true,tip:'分值',fieldType:'pattern',length:100}" />
								<span style="color: red" id="tip"></span>
								<span><font color="red">*（不能为0）</font>
								</span>
							</td>
						</tr>
						<tr>
							<td width="120" class="tdBlue">
								积分说明
							</td>
							<td width="500">
								<textarea rows="3" name="tbScoreRulePO.scoreDesc"
									style="width: 400px"
									valid="{must:false,tip:'积分说明',length:2000}"></textarea>
							</td>
						</tr>


					</tbody>
				</table>
			</form>

			<div class="toolBar">
				<div align="center">
					<input class="greenbtn mr10" id="save" type="button" value="保存" />
					<input class="greenbtn mr10" type="button"
						onclick="javascript:history.back();" value="返 回" />
				</div>
			</div>

			<!--工具栏 end-->
		</div>
		<script type="text/javascript">

$('#save').click(function() {

	var dqdp = new Dqdp();
	if (dqdp.submitCheck("org_add")) {
		var value = $("#value").val();
		var reNum = /^[0-9]+.?[0-9]*$/;
		if (!reNum.test(value) || "0" == value) {
			$("#tip").text("分值格式不正确!");
			return;
		} else {
			$("#tip").text("");
		}
		$('#org_add').ajaxSubmit( {
			dataType : 'json',
			success : function(result) {
				if ("0" == result.code) {
					alert('修改成功');

					window.location.href = ("ruleList.jsp");
				} else {
					alert(result.desc);
				}
			},
			error : function() {
				alert('新增失败，请稍候再试');
			}
		});

	}
});
</script>

	</body>
</html>
