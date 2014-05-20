<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="meetingType,meetingWay,meetingSmsNotice"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>志愿活动审核</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/HashMap.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
</head>

<body style="background: #f6ebe5;" >
<form method="post" id="id_form_search" action="${baseURL}/volunteer/volunteerAction!ajaxAudit.action">
    <div class="searchWrap">
        <div class="title">
            <h2 class="icon1">志愿活动详情</h2>
        </div>
    </div>
    <div class="text_bg noneborder" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/volunteer/volunteerAction!ajaxView.action?id=${param.id}">
        <tbody>
        <tr>
            <td class="tdBlue">志愿活动主题</td>
            <td name="tbVolunteerActivityPO.title" ></td>
        </tr>
        <tr>
            <td class="tdBlue">活动类型</td>
            <td name="tbVolunteerActivityPO.typeDesc"></td>
        </tr>
        <tr>
            <td class="tdBlue">活动时间</td>
			<td>开始&nbsp;&nbsp;<span name="tbVolunteerActivityPO.startTime"></span>
            	&nbsp;&nbsp;
            	结束 &nbsp;&nbsp;<span name="tbVolunteerActivityPO.endTime"></span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">活动地点</td>
            <td  name="tbVolunteerActivityPO.address">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">志愿活动内容</td>
            <td name="tbVolunteerActivityPO.content">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">审核意见</td>
            <td>
            	<input type="hidden" name="tbVolunteerActivityPO.id"/>
            	<input type="hidden" id="status" name="tbVolunteerActivityPO.status"/>
            	<textarea rows="5" cols="80" id="opinion" name="tbVolunteerActivityPO.opinion"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
	</div>


<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="auditOk" onclick="audit(2)" permission="" value="审核通过"/>&nbsp;
    	<input class="greenbtn mr10" type="button" id="back" onclick="audit(3)" permission="" value="审核不通过"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 
  $(document).ready(function(){
  });
 function audit(type){
	 $("#status").val(type);
	 if(type == "3" && $("#opinion").val() == ""){
		 alert("请填写审核意见,谢谢!");
		 $("#opinion").focus();
		 return;
	 }
	 
	if(confirm(type==2 ? "确定审核通过?" : "确定审核不通过?")){
	    $('#id_form_search').ajaxSubmit({
	        url: "${baseURL}/volunteer/volunteerAction!ajaxAudit.action",
	        type: "post",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	                alert(result.desc);
	                 window.location.href = "list.jsp";
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
    }
 }
</script>
</form>
</body>
</html>
