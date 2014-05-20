<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="meetingType,meetingWay,meetingSmsNotice"></jsp:param>
    <jsp:param name="permission" value="meetingAdd"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>二维码生成</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body style="background: #f6ebe5;" >
<form action="" method="post" id="id_form_search">
    <div class="searchWrap">
        <div class="title">
            <h2 class="icon1">查看二维码</h2>
        </div>
    </div>
    <div class="text_bg noneborder" id="bt">
                	<input type="hidden" name="id" id="id" value="${param.id}"/>
            	<input type="hidden" name="type" id="type" value="${param.type}"/>
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/meeting/meetingAction!qRCodeView.action?id=${param.id}&type=${param.type}">
        <tbody>
        <tr>
            <td class="tdBlue">标题</td>
            <td name="title">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">二维码</td>
            <td>
            	<input type="hidden" name="qrCode" id="qrCode"/>
            	<img id="qrCodeImg" style="display: none;"/>
            </td>
        </tr>
        </tbody>
    </table>
	</div>


<div class="toolBar">
    <div align="center">
    	<!-- 
    	<input class="greenbtn mr10" type="button" id="print" permission="" value="打印二维码"/>&nbsp;
    	 -->
    	<input class="greenbtn mr10" type="button" id="export" permission="" value="导出二维码"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 
  $(document).ready(function(){
	  var imgPath = $("#qrCode").val();
   		if($("#qrCode").val() != ""){
   			$("#qrCodeImg").attr("src","${baseURL}/"+imgPath);
      		$("#qrCodeImg").css("display","");
   		}
  });
    
    
 $('#export').click(function(){ 
		var id = $('#id').val();
		var type = $('#type').val();
        window.location.href="${baseURL}/meeting/meetingAction!exportRecode.action?id="+id+"&type="+type;
  
  });
 
</script>
</form>
</body>
</html>
