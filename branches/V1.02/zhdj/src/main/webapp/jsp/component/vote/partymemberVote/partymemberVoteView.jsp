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
    <title>查看优秀党员评议</title>
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
                <h2 class="icon1">查看优秀党员评议</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/partymembervote/partymembervoteAction!ajaxView.action?id=${param.id}">
        <input type="hidden" name="tbPartyMemberVotePO.id" id="id"/>

        <tbody>
        <tr>
            <td class="tdBlue">评议主题</td>
            <td  >
                <input type="text" id="title"  name="tbPartyMemberVotePO.voteTopic" valid="{must:true,tip:'评议主题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
                
            </td>
        </tr>
        <tr>
                <td  class="tdBlue" >宣传图片</td>
                <td >
                   <input name="tbPartyMemberVotePO.voteImgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:true,tip:'宣传图片'}" />&nbsp; 
                
			       <img id="img" src="" height="100px" width="100px" style="display:none;" />
				    
                </td>
        </tr>
        <tr>
            <td class="tdBlue" >评议时间</td>
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
            </td>
        </tr>
        <tr>
            <td class="tdBlue"  >内容</td>
            <td >
               <textarea rows="5" cols="80" name="tbPartyMemberVotePO.remark" id="content" ></textarea>
            </td>
        </tr>
        </tbody>
    </table>
</div>


<div class="toolBar">
    <div align="center">
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

 
</script>
</form>
</body>
</html>
