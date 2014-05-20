<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsProjectType"></jsp:param>
    <jsp:param name="permission" value="noticeAfficheEdit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工作动态信息编辑</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
    <style type="text/css">
    
    </style>
</head>

<body>
<form action="${baseURL}/news/newsinfo/newsInfoAction!ajaxSave.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/news/newsinfo/newsInfoAction!ajaxView.action?newsInfoId=${param.newsInfoId}">
        <thead>
        <tr>
            <th colspan="4">工作动态信息编辑</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="200" class="tdBlue">标题：</td>
            <td width="100" >
            	<input type="hidden" name="tbNewsInfoPO.newsInfoId"/>
            	<input type="hidden" name="tbNewsInfoPO.newsInfoType" id="newsInfoType" value="${param.newsInfoType}"/>
                <input type="text" id="title" style="width:400px" name="tbNewsInfoPO.title" valid="{must:true,tip:'标题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
                <input id="status" name="tbNewsInfoPO.status" type="checkbox" value="22">&nbsp;已发布&nbsp;&nbsp;&nbsp;
                <input id="buyTop" name="tbNewsInfoPO.buyTop" type="checkbox" >&nbsp;置顶&nbsp;&nbsp;&nbsp;
              	<input id="solidTop" name="tbNewsInfoPO.solidTop" type="checkbox" >&nbsp;固顶&nbsp;&nbsp;&nbsp;
              	<input id="importance" name="tbNewsInfoPO.importance" type="checkbox" >&nbsp;重要
            </td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">封面图片：</td>
            <td width="500">
                <input class="form60px" name="file" id="file" type="file" style="width: 400px"/>
               <input name="tbNewsInfoPO.imgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:false,tip:'图片'}" /> 
				<img id="img" src="" height="100px" width="100px" style="display:none;" />
				<input type="button" value="上 传" onClick="uploadFile();" class="btn4">&nbsp;
            </td>
        </tr>
        <tr>
            <td width="120" class="tdBlue">专题类型</td>
            <td width="500">
            	<select dictType="newsProjectType" name="tbNewsInfoPO.projectType" id="projectType"/ >
            </td>
        </tr>
        <tr>
            <td width="200" class="tdBlue">正文摘要：</td>
            <td colspan="3">
               <textarea rows="5" cols="70" name="tbNewsInfoPO.bodyZaiyao" id="bodyZaiyao" ></textarea>
            </td>
        </tr>
        <tr>
            <td width="200" class="tdBlue">正文：</td>
            <td colspan="3">
               <textarea rows="5" cols="120" name="tbNewsInfoPO.content" id="content" ></textarea>
            </td>
        </tr>
        </tbody>
    </table>

<div id="role_list"></div>


<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" id="save" permission="noticeAfficheEdit" value="保存并预览"/>&nbsp;
    	<input class="btn4" type="button" id="push" permission="noticeAfficheEdit" value="发布"/>&nbsp;
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
  var editor1; 
  KindEditor.ready(function(K) {
		editor1 = K.create('textarea[name="tbNewsInfoPO.content"]', {
		cssPath : '${baseURL}/js/3rd-plug/kindeditor/plugins/code/prettify.css',
		uploadJson : '${baseURL}/KindEditUpload?method=upload',
		fileManagerJson : '${baseURL}/KindEditUpload?method=',
		allowFileManager : true,
		filterMode : false,
		afterCreate : function() {
			this.sync(); 
		},afterBlur:function(){ 
            this.sync(); 
            $('#id_form_search').find('textarea[name="tbNewsInfoPO.content"]').val(K('textarea[name="tbNewsInfoPO.content"]').val());
        }  
	})});
    $(document).ready(function(){
      document.getElementById("save").disabled = false;
      document.getElementById("push").disabled = false; 
      initChecked();
      
      var imgPath=$("#imgPath").val();
      $("#img").attr("src","${baseURL}/"+imgPath);
       $("#img").css("display","");
    });
    
    
    
    
    function setStatusAtt(){
   		if($('#status').attr("checked")==undefined){
   			$('#status').val(0);  
   		}else{
   			$('#status').val(1);
   		}
   		
   		if($('#buyTop').attr("checked")==undefined){
   			$('#buyTop').val(0);
   		}else{
   			$('#buyTop').val(1);
   		}
   		
   		if($('#solidTop').attr("checked")==undefined){
   			$('#solidTop').val(0);
   		}else{
   			$('#solidTop').val(1);
   		}
   		
   		if($('#importance').attr("checked")==undefined){
   			$('#importance').val(0);
   		}else{
   			$('#importance').val(1);
   		}
    }
    
    function initChecked(){
		 $("input[type='checkbox']").each(function(){
			if($(this).attr("defaultValue")=="1"){
				$(this).attr("checked",true);
				$(this).val(1);
			}else{
				$(this).val(0);
			}
		});
    }
    
    
 $('#save').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
         document.getElementById("save").disabled = true; 
         document.getElementById("push").disabled = true; 
         setStatusAtt();
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('保存成功');
                    window.location.href="list.jsp?type="+$('#newsInfoType').val();
                   
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
    if (dqdp.submitCheck("id_form_search")) {
         document.getElementById("save").disabled = true;
         document.getElementById("push").disabled = true; 
         $('#status').attr("checked",true);
         setStatusAtt();
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('保存成功');
                    window.location.href="list.jsp?type="+$('#newsInfoType').val();
                   
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
   

</script>
</form>
</body>
</html>
