<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value="ideologyEdit,ideologyAudit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>思想汇报详情</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
		<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet"
			type="text/css" />
		<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet"
			type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/HashMap.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
</head>

<body style="background: #f6ebe5;">
		<div class="searchWrap">
			<div class="title">
				<h2 class="icon1">
					思想汇报详情
				</h2>
			</div>
		</div>
		<div class="text_bg noneborder" id="bt">
<form action="${baseURL}/ideologyReport/ideologyReportAction!ajaxSave.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/ideologyReport/ideologyReportAction!ajaxView.action?id=${param.id}">
    	<!-- 
        <tr>
            <td><input type="hidden" name="tbIdeologyReportPO.id"/>
            	<input type="hidden" id="status" name="tbIdeologyReportPO.status"/>
				 <span style="font-size: 16px;font-weight:blod;" name="tbIdeologyReportPO.title"></span></br></br></br>
				  提交人:&nbsp;<span name="tbIdeologyReportPO.name"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交时间:&nbsp;<span name="tbIdeologyReportPO.createTime"></span></br></br></br>
				  附件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${baseURL}/@{tbIdeologyReportPO.accessoryPath}" name="tbIdeologyReportPO.accessoryName"></a></br></br></br>
            </td>
        </tr>
         -->
        <tr>
        	<td width="10%" align="right">标题</td>
        	<td name="tbIdeologyReportPO.title">
        	</td>
        </tr>
        <tr>
        	<td width="10%" align="right">提交人</td>
        	<td name="tbIdeologyReportPO.name">
        	</td>
        </tr>
        <tr>
        	<td align="right">提交时间</td>
        	<td name="tbIdeologyReportPO.createTime"></td>
        </tr>
        <tr>
        	<td align="right" valign="top">提交内容</td>
        	<td ><p name="tbIdeologyReportPO.content"></p></td>
        </tr>
        <tr>
        	<td align="right">领导批注</td>
            <td style="font-size: 14px;font-weight:blod;"> 
            	<textarea rows="5" cols="100">@{tbIdeologyReportPO.postil}</textarea>
            </td>
            <input type="hidden" name="tbIdeologyReportPO.id"/>
            <input type="hidden" id="status" name="tbIdeologyReportPO.status"/>
        </tr>
    </table>



<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="back" onclick="save('2')" permission="ideologyAudit" value="退回"/>&nbsp;
    	<input class="greenbtn mr10" type="button" id="auditOk" onclick="save('1')" permission="ideologyAudit" value="审核通过"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
<!--工具栏 end-->

<script type="text/javascript">
 
  $(document).ready(function(){
      if($('#status').val() == "0"){
    	  $('#back,#auditOk').show();
      }else{
    	  $('#back,#auditOk').hide();
      }
  });
    
    
    
    
 function save(status){ 
	 if(status=="1"){
		 document.getElementById("auditOk").disabled = true; 
	 }else if(status=="2"){
		 document.getElementById("back").disabled = true; 
	 }
	 var alertDesc = status == "2" ? "退回成功!" : "审核通过成功!";
	 $("#status").val(status);
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
        // 提交数据
        
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
	        success: function (result) {
                if ("0"==result.code) {
                    alert(alertDesc);
                    window.location.href="list.jsp";
                   
                } else {
                    alert(result.desc);
                }
	        },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }
 }

   
    function flashIframe_GDZC(){
    	var i=parent.document.getElementById("ifm");
    	if(i!=null)
    	{	
    		if(document.body.scrollHeight<700)
    		{
    			i.style.height ="730px";
    		}else
    		{
    			i.style.height = (document.body.scrollHeight+30)+"px";
    		}
    	}
    }
</script>
</form></div>
</body>
</html>
