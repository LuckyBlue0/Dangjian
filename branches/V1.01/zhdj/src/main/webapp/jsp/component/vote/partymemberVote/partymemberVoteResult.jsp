<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../../common/dqdp_common.jsp"%>
	<jsp:include page="../../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value=""></jsp:param>
		<jsp:param name="permission"
			value=""></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>投票结果统计</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
			<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>">
		</script>
		<script src="${baseURL}/js/do1/common/error1.0.js">
</script>
<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>  
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<!--头部 end-->

        <input type="hidden" id="id" value="${param.id}"/>
		<!--公告 end-->
		<div class="searchWrap">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="searchLeft"></td>
					<div class="title">
						<h2 class="icon1">
							投票结果统计
						</h2>
					</div>
				</tr>
			</table>
		</div>
		<div datasrc="${baseURL}/partymembervote/partymembervoteAction!ajaxSearchVoteResult.action?id=${param.id}">
        <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%">

        <tbody>
            <tr >
                <td class="tdBlue"  style="width:100px;border:1px solid #e0e0e0;">评议主题</td>
                <td name="resultvo.voteTopic" style="border:1px solid #e0e0e0;">
            </td>
            </tr>
            <tr >
                <td class="tdBlue"  style="width:100px;border:1px solid #e0e0e0;">投票参与人数</td>
                <td name="resultvo.totalCount" style="border:1px solid #e0e0e0;">
            </td>
            </tr>
        </tbody>
         </table>
         <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%" >
                    <thead>
                    <tr>
                        <th style="width: 40%" style="border:1px solid #e0e0e0;">姓名</th>
                        <th style="width: 30%" style="border:1px solid #e0e0e0;">获得票数</th>
                        <th style="width: 30%" style="border:1px solid #e0e0e0;">支持率</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="list">
                        <td name="userName" style="border:1px solid #e0e0e0;"></td>
                        <td name="result1" style="border:1px solid #e0e0e0;"></td>
                        <td name="result2" style="border:1px solid #e0e0e0;"></td>
                      
                    </tr>
                    </tbody>
                </table>
      </div>




	</body>
<script type="text/javascript">
$(document).ready(function() {
});

</script>
</html>
