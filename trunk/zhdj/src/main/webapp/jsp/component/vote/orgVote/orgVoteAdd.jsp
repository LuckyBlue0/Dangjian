<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增民主机关评议</title>
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
<form action="${baseURL}/orgvote/orgvoteAction!ajaxSave.action" method="post" id="id_form_search">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">新增民主机关评议</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
		<tr>
			<td colspan="2">
				<div class="topTitle mb20">
					<span class="topName">基本信息</span>
				</div>
			</td>
		</tr>
        <tr>
            <td class="tdBlue"  ><span style="color: red">*</span>活动专题</td>
            <td >
            	<input type="hidden" id="pushStatus" name="tbOrgVotePO.pushStatus"/>
                <input type="text" id="topic"  name="tbOrgVotePO.topic" valid="{must:true,tip:'所属专题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <tr>
            <td class="tdBlue"  ><span style="color: red">*</span>评议时间</td>
             <td>开始时间
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"
					type="text" style="width: 200px"
					name="tbOrgVotePO.startTime"
					valid="{must:true,tip:'开始时间',fieldType:'pattern'}" id="startTime"/>
                   &nbsp;&nbsp;&nbsp;&nbsp;结束时间
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"
					type="text" style="width: 200px"
					name="tbOrgVotePO.endTime"
					valid="{must:true,tip:'结束时间',fieldType:'pattern'}" id="endTime"/>
					
            </td>
        </tr>
        <tr>
            <td class="tdBlue"><span style="color: red">*</span>备注</td>
            <td>
                 <textarea id="topic"  name="tbOrgVotePO.remark" rows="5" cols="50"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    <table id="orgList" class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2">
				<div class="topTitle mb20">
					<span class="topName">被评选机关列表</span>
				</div>
			</td>
		</tr>
	</table>
	<div class="toolBar">
	    <div align="center">
	    	<input class="greenbtn mr10" type="button" onclick="addTable()"  value="添加机关"/>
	        <input class="greenbtn mr10" type="button" id="save1"  value="保存为草稿"/>&nbsp;
	    	<input class="greenbtn mr10" type="button" id="save2"  value="保存并发布"/>&nbsp;
	    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
	    </div>
	</div>
    </div>
</form>




<!--工具栏 end-->

<script type="text/javascript">
  	 var arraylength = ${fn:length(tbVoteOrgList)};
	 $(document).ready(function(){
		 var html="";
		 if(arraylength==0){
	        html += "<tr>"+
			        "<td class=\"tdBlue\"></td>"+
			        "<td>"+
			        "<input type=\"hidden\" id=\"voteOrgId"+arraylength+"\"  name=\"tbVoteOrgList["+arraylength+"].organizationId\"/>"+
			        "<input type=\"text\" id=\"voteOrgName"+arraylength+"\" name=\"tbVoteOrgList["+arraylength+"].voteOrg\" valid=\"{must:true,tip:'被评选机关',fieldType:'pattern'}\"/>"+
			       // "&nbsp;<img src=\"${baseURL}/image/ss_bt.png\" onclick=\"selectParent("+arraylength+")\" />"+
			       	"&nbsp;<a href=\"javascript:void(0);\"><img src=\"${baseURL}/themes/default/images/u122_normal.png\" onclick=\"delTable(this)\"/></a>"+
			        "</td>"+
			        "</tr>";
		 }
		 $("#orgList").append(html);
	 });
    
    function delTable(obj){
       var isDelete=confirm("确定删除?"); 
	   if(isDelete){
	    var tr=obj.parentNode.parentNode.parentNode;  
	    var tbody=tr.parentNode;  
	    $(tr).remove();
	    arraylength--;
	   }
    }
    
 function addTable(){
	 arraylength = arraylength+1;
	 var html = "<tr>"+
		        "<td class=\"tdBlue\"></td>"+
		        "<td>"+
		        "<input type=\"hidden\" id=\"voteOrgId"+arraylength+"\"  name=\"tbVoteOrgList["+arraylength+"].organizationId\"/>"+
			    "<input type=\"text\" id=\"voteOrgName"+arraylength+"\" name=\"tbVoteOrgList["+arraylength+"].voteOrg\" valid=\"{must:true,tip:'被评选机关',fieldType:'pattern'}\"/>"+
			        //"&nbsp;<img src=\"${baseURL}/image/ss_bt.png\" onclick=\"selectParent("+arraylength+")\" />"+
			       	"&nbsp;<a href=\"javascript:void(0);\"><img src=\"${baseURL}/themes/default/images/u122_normal.png\" onclick=\"delTable(this)\"/></a>"+
		        "</td>"+
		        "</tr>";
 	 $("#orgList").append(html);
 }
 
 var thisIndex = 0;
 function selectParent(index){
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp","_blank","width=800,height=400");
	thisIndex = index;
 } 
 
 function selectOrg(id,name){
	document.getElementById("voteOrgId"+thisIndex).value = id;
	document.getElementById("voteOrgName"+thisIndex).value = name ;
 } 
 
 $('#save1').click(function(){ 
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
         //document.getElementById("save1").disabled = true; 
         $("#pushStatus").val("0");
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert(result.desc);
                    window.location.href="${baseURL}/jsp/component/vote/orgVote/orgVoteList.jsp";
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
  $('#save2').click(function(){ 
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
         document.getElementById("save2").disabled = true; 
          $("#pushStatus").val("1");
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert(result.desc);
                    window.location.href="${baseURL}/jsp/component/vote/orgVote/orgVoteList.jsp";
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

</body>
</html>
