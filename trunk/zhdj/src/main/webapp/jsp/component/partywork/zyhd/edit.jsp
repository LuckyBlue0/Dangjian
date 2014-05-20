<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="volunteerType,volunteerStatus"></jsp:param>
    <jsp:param name="permission" value="volunteerEdit,volunteerAudit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>志愿活动详情</title>
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
<form action="${baseURL}/volunteer/volunteerAction!ajaxSave.action" method="post" id="id_form_search">
    <div class="searchWrap">
        <div class="title">
            <h2 class="icon1">志愿活动编辑</h2>
        </div>
    </div>
    <div class="text_bg noneborder" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/volunteer/volunteerAction!ajaxView.action?id=${param.id}">
        <tbody>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>&nbsp;志愿活动主题</td>
            <td>
            	<input type="hidden" name="tbVolunteerActivityPO.id"/>
            	<input type="hidden" id="status" name="tbVolunteerActivityPO.status"/>
                <input type="text" id="title" name="tbVolunteerActivityPO.title" valid="{must:true,tip:'志愿活动主题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>&nbsp;活动类型</td>
            <td >
                <select dictType="volunteerType" defaultTip="" name="tbVolunteerActivityPO.type" id="type" valid="{must:true,tip:'活动类型',fieldType:'pattern'}"/>
                
            </td>
        </tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>&nbsp;活动时间</td>
            <td>开始
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"
					type="text" style="width: 200px"
					name="tbVolunteerActivityPO.startTime"
					valid="{must:true,tip:'开始时间',fieldType:'pattern'}" id="startTime"/>
            	&nbsp;&nbsp;结束 
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"
					type="text" style="width: 200px"
					name="tbVolunteerActivityPO.endTime"
					valid="{must:true,tip:'结束时间',fieldType:'pattern'}" id="endTime"/>
            </td>
        </tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>&nbsp;活动地点</td>
            <td  >
            	<input type="text" id="address" name="tbVolunteerActivityPO.address" valid="{must:true,tip:'活动地点',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
            	
            </td>
        </tr>
        <tr>
            <td class="tdBlue">主题图片</td>
            <td >
            	<img src="${baseURL}/jsp/web/images/default.jpg" id="img" height="150" width="200" alt="" />
            	<span class="popbtn"><a href="#" onClick="file.click();"><img src="${baseURL}/themes/default/css/images/up.png" /></a></span>
                <input class="form60px" name="file" id="file" type="file" style="display: none;" onchange="uploadFile();"/>
               	<input name="tbVolunteerActivityPO.themeImgPath" id="imgPath" class="form120px" value="" type="hidden"  /> 
            </td>
        </tr>
        <tr>
            <td class="tdBlue">志愿活动内容</td>
            <td >
               <textarea rows="5" cols="75" name="tbVolunteerActivityPO.content" id="content" ></textarea>
            </td>
        </tr>
        </tbody>
    </table>
	</div>
<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="draft" onclick="save()" permission="volunteerEdit" value="保存"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 
  $(document).ready(function(){
	  //updateUser();
   	  var imgPath=$("#imgPath").val();
	  if(imgPath != ""){
      	$("#img").attr("src","${baseURL}/"+imgPath);
      	$("#img").css("display","");
      };
      
  });
    
    
    
    
 function save(){ 
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
        // 提交数据
        
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
	        success: function (result) {
                if ("0"==result.code) {
                    alert(result.desc);
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
 
 function selectUser() {
     window.open('${baseURL}/jsp/component/basis/partymenber/multiSelecttPartyMember.jsp', '用户选择', 'height=600, width=1000, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
 }
    
   function uploadFile(){
	   $('#id_form_search').attr("action","${baseURL}/volunteer/volunteerAction!fileUpload.action");
	    $('#id_form_search').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            $("#img").attr("src","${baseURL}/"+result.desc);
                            $("#img").css("display","");
                            $("#imgPath").val(result.desc);
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('文件上传失败，请稍候再试');  
                    }
                });
	     $('#id_form_search').attr("action","${baseURL}/volunteer/volunteerAction!ajaxSave.action");
	     
   }
</script>
</form>
</body>
</html>
