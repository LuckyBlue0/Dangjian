<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="groups"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改优秀党员评议</title>
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
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/partymembervote/partymembervoteAction!ajaxUpdate.action" method="post" id="id_form_search">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">修改优秀党员评议</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/partymembervote/partymembervoteAction!ajaxView.action?id=${param.id}">
        <input type="hidden" name="tbPartyMemberVotePO.id" id="id"/>

        <tbody>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>评议主题</td>
            <td  >
                <input type="text" id="title"  name="tbPartyMemberVotePO.voteTopic" valid="{must:true,tip:'评议主题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
                
            </td>
        </tr>
        <tr>
                <td  class="tdBlue" ><span style="color: red">*</span>宣传图片</td>
                <td >
                    <input class="form120px" name="file" id="file" type="file" style="width: 81%;"/>
                   <input name="tbPartyMemberVotePO.voteImgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:true,tip:'宣传图片'}" />&nbsp; 
                   <input type="button" value="上 传" onClick="uploadFile();" class="greenbtn mr10">
			       <img id="img" src="" height="100px" width="100px" style="display:none;" />
				    
                </td>
        </tr>
        <tr>
            <td class="tdBlue" ><span style="color: red">*</span>评议时间</td>
             <td>开始时间
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"
					type="text" style="width: 200px"
					name="tbPartyMemberVotePO.startTime"
					valid="{must:true,tip:'开始时间',fieldType:'pattern'}" id="startTime"/>
                   &nbsp;&nbsp;&nbsp;&nbsp;结束时间
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"
					type="text" style="width: 200px"
					name="tbPartyMemberVotePO.endTime"
					valid="{must:true,tip:'结束时间',fieldType:'pattern'}" id="endTime"/>
					
            </td>
        </tr>
        <tr>
            <td class="tdBlue" >投票次数</td>
            <td >
                <input type="text" id="voteLimit"  name="tbPartyMemberVotePO.voteLimit" valid="{must:false,tip:'投票次数',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
                <br/><span style="color: red">注：投票次数指是否可以投多个人，必须为正整数，空则默认投票次数不限</span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">评议规则</td>
            <td >
               <textarea rows="5" cols="80" name="tbPartyMemberVotePO.remark" id="content" valid="{tip:'评议规则',length:'500',fieldType:'pattern'}"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
</div>


<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" id="save"  value="下一步"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 
 $(document).ready(function() {
 	var imgPath=$("#imgPath").val();
      $("#img").attr("src","${baseURL}/"+imgPath);
       $("#img").css("display","");
 });
 $('#save').click(function(){ 
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
    	 var voteLimit=$("#voteLimit").val();
    	 if(voteLimit!=''){
    		 var isNum=/^[0-9]*$/; 
             var isNumber=isNum.test(voteLimit);
             if(!isNumber){
            	 alert("投票次数必须为正整数！");
            	 return; 
             }
    	 }
         document.getElementById("save").disabled = true; 
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    window.location.href="${baseURL}/partymembervote/partymembervoteAction!ajaxTurnToEditMember.action?id="+$("#id").val()+"&voteLimit="+voteLimit; 
                   
                } else {
                	document.getElementById("save").disabled = false; 
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
 function uploadFile(){
	   $('#id_form_search').attr("action","${baseURL}/orgManage/orgManageAction!fileUpload.action");
	    $('#id_form_search').ajaxSubmit({
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
	     $('#id_form_search').attr("action","${baseURL}/partymembervote/partymembervoteAction!ajaxUpdate.action"); 
   }
 
</script>
</form>
</body>
</html>
