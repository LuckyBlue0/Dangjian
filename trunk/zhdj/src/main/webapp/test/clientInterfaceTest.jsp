<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/jsp/common/dqdp_common.jsp"%>
<jsp:include page="/jsp/common/dqdp_vars.jsp">
	<jsp:param name="dict" value=""></jsp:param>
	<jsp:param name="permission" value=""></jsp:param>
	<jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>接口测试</title>
<link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet"
	type="text/css" />
<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
<script src="${baseURL}/js/do1/common/common.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<link
	href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>

<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
</head>

<body templateId="SQLPager">

	<form method="post" id="testForm" templateId="default">
		<table class="tableCommon mt5" width="100%" border="0" cellspacing="0"
			cellpadding="0" id="id_body">
			<thead>
				<tr>
					<th colspan="2">测试手机客户端接口</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="120" class="tdBlue">接口地址</td>
					<td width="280"><textarea rows="2" cols="50" id="interfaceUrl" ></textarea>
					</td>
				</tr>
				<tr dqdpCheckPoint="add_field_flightNo">
					<td width="120" class="tdBlue">requestJson请求参数</td>
					<td width="280"><textarea rows="8" cols="200" name="requestJson" ></textarea>
					</td>
				</tr>
				<tr>
					<td>响应内容</td>
					<td>
					<textarea rows="10" cols="200" id="content" ></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="id" value="${param.id}" />
	</form>
	<script type="text/javascript">
		function doCommit() {
			var commitUrl = $("#interfaceUrl").val();
			$("#testForm").attr("action","${baseURL}/"+commitUrl);
	        $('#testForm').ajaxSubmit({
	            dataType:'json',
	            success:function(result) {
	                if ("0"==result.code) {
	                    alert(result.desc);
	                    $("#content").val(result.data);
	                } else {
	                    alert(result.desc);
	                }
	            },
	            error:function(){
	                alert('保存失败，请稍候再试');    
	            } 
	        });
		}
	</script>
	<div class="toolBar" templateId="default">
		<div align="center">
			<input class="btn4" type="button"
				id="00c0a7aad2674f2da63f7cd7902c4c2d" onclick="doCommit()"
				value="提交" /> <input class="btn4" type="button"
				id="dee3e9de99644d0fb845185cafc70dda"
				onclick="javascript:history.go(-1)" value="返回" />
		</div>
	</div>

</body>
</html>
