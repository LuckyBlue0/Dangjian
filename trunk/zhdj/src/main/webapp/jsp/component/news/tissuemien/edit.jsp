<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="hotNewsEdit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
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
    <style type="text/css">
    
    </style>
</head>

<body style="background: #f6ebe5;" >
<form action="${baseURL}/news/tissueMienInfo/tissueMienInfoAction!ajaxSave.action" method="post" id="id_form_add">
    <div class="searchWrap">
        <div class="title">
            <h2 class="icon1">总结上报</h2>
        </div>
    </div>
    <div class="text_bg noneborder" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/news/tissueMienInfo/tissueMienInfoAction!ajaxView.action?id=${param.id}&type=${param.type}">
        <tbody>
			    <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;标题</td>
		            <td >
		            	<input type="hidden" name="tbTissueMienPO.id" id="tId"/>
		            	<input type="hidden" name="tbTissueMienPO.type" id="type"/>
		            	<input type="hidden" name="tbTissueMienPO.status" id="status"/>
		                <input type="text" id="title" name="tbTissueMienPO.title" valid="{must:true,tip:'标题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
		            </td>
			    </tr>
		        <tr>
		            <td width="120" class="tdBlue"><span style="color: red">*</span>&nbsp;封面图片</td>
		            <td>
		                <input class="form120px" name="file" id="file" type="file"/>
		                <input name="tbTissueMienPO.imgPath" id="imgPath" class="form120px" type="hidden"  valid="{must:false,tip:'图片'}" /> 
						<img id="img" src="" height="100px" width="100px" style="display:none;" />
						<input type="button" value="上 传" onClick="uploadFile();" class="greenbtn mr10">&nbsp;
		            </td>
		        </tr>
				<tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;正文</td>
		            <td colspan="3">
		               <textarea rows="6" cols="80" name="tbTissueMienPO.content" id="content" ></textarea>
		            </td>
		        </tr>
        </tbody>
    </table>
	</div>
<div id="role_list"></div>
<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="save" permission="" value="保存草稿"/>&nbsp;
    	<input class="greenbtn mr10" type="button" id="push" permission="" value="保存并发布"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function(){
      document.getElementById("save").disabled = false;
      if($("#imgPath").val() != null && $("#imgPath").val() != ""){
         $("#img").css("display","");
         $("#img").attr("src","${baseURL}/"+$("#imgPath").val());
      }
      $("#type").val("${param.type}");
      backURL = "${param.backURL}";
      $("#tId").val("${param.id}");
      
      if($("#status").val()==1){
    	  $("#save,#push").hide();
      }
	  var editor1; 
	  KindEditor.ready(function(K) {
			editor1 = K.create('textarea[name="tbTissueMienPO.content"]', {
			cssPath : '${baseURL}/js/3rd-plug/kindeditor/plugins/code/prettify.css',
			uploadJson : '${baseURL}/KindEditUpload?method=upload',
			fileManagerJson : '${baseURL}/KindEditUpload?method=',
			allowFileManager : true,
			filterMode : false,
			items : [
				'bold', 'italic', 'underline','|','insertunorderedlist', 'insertorderedlist', '|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright','|',
				'image', 'link','insertfile'] ,
			afterCreate : function() {
				this.sync(); 
			},afterBlur:function(){ 
	            this.sync(); 
	            $('#id_form_add').find('textarea[name="tbTissueMienPO.content"]').val(K('textarea[name="tbTissueMienPO.content"]').val());
	        }  
		})});
    });
    var backUrl = "";
 $('#save').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_add")) {
         document.getElementById("save").disabled = true;
         document.getElementById("push").disabled = true; 
         $("#status").val(0);
        //提交数据
        $('#id_form_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert("保存成功");
                    window.location.href = backURL;
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
  $('#push').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_add")) {
         document.getElementById("save").disabled = true;
         document.getElementById("push").disabled = true; 
         $("#status").val(1);
        //提交数据
        $('#id_form_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('发布成功');
                    window.location.href = backURL;
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('发布失败，请稍候再试');    
            } 
        });
    }});
 
   
  
   function uploadFile(){
	   $('#id_form_add').attr("action","${baseURL}/news/tissueMienInfo/tissueMienInfoAction!fileUpload.action");
	    $('#id_form_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('文件上传成功！');  
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
	     $('#id_form_add').attr("action","${baseURL}/news/tissueMienInfo/tissueMienInfoAction!ajaxSave.action");
   }
   

</script>
</form>
</body>
</html>
