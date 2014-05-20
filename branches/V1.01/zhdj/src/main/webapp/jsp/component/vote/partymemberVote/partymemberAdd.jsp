<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="groups"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增优秀党员评议</title>
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
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/partymembervote/partymembervoteAction!ajaxAddPartymember.action" method="post" id="id_form_search">

<input type="hidden" name="hValue" id="hValue"/>
    <div class="text_bg" id="bt">
    <div id="memberList">
    
    </div>
    
    


<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="addTable()"  value="添加对象"/>&nbsp;
        <input class="greenbtn mr10" type="button" id="save"  value="保存"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
</div>
</form>

<!--工具栏 end-->

<script type="text/javascript">
 	 var arraylength = ${fn:length(tbVoteMemberPOList)};
     var userMap = new HashMap();
 $(document).ready(function() {
	 if(arraylength==""){
		 arraylength=0;
	 }
	
	 var html="";
	 if(arraylength==0){
	    html+="<table class=\"tableCommon mt5\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
	    "<input type=\"hidden\" name=\"tbVoteMemberPOList[0].voteTopicId\" value=\"${id}\"  />"+
	    "<thead>"+
        "<tr>"+
        "<th colspan=\"2\" onclick=\"showTbody("+arraylength+")\" id=\"th"+arraylength+"\">评议对象"+(arraylength+1)+"</th>"+
        "<th style=\"width:20px;\" ><a href=\"javascript:void(0);\"><img src=\"../themes/default/images/u122_normal.png\" onclick=\"delTable(this)\"/></a></th>"+
        "</tr>"+
        "</thead>"+
        "<tbody id=\"body0\">"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>对象姓名</td>"+
        "<td>"+
        "<input type=\"text\" id=\"userName0\"  name=\"tbVoteMemberPOList[0].userName\" valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input class=\"greenbtn mr10\" type=\"button\"  onclick=\"selectMember(0)\" value=\"选择党员\" />"+
        "<input type=\"hidden\" id=\"userId0\"  name=\"tbVoteMemberPOList[0].userId\"/>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>所属组织</td>"+
        "<td  >"+
        "<input type=\"text\" id=\"organizationName0\"  name=\"tbVoteMemberPOList[0].organizationName\" valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input type=\"hidden\" id=\"organizationId0\"  name=\"tbVoteMemberPOList[0].organizationId\"/>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >先进事迹</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList[0].advancedDeeds\" id=\"advancedDeeds\" ></textarea>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >党员述职</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList[0].partyWork\" id=\"partyWork\" ></textarea>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "</tbody>"+
        "</table>";
        
	 }else{
	 for(var i=0;i<arraylength;i++){
	    html+="<table class=\"tableCommon mt5\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
	    "<input type=\"hidden\" name=\"tbVoteMemberPOList["+i+"].voteTopicId\" value=\"${id}\"  />"+
	    "<thead>"+
        "<tr>"+
        "<th colspan=\"2\" onclick=\"showTbody("+i+")\" id=\"object"+i+"\">评议对象"+(i+1)+"</th>"+
        "</tr>"+
        "</thead>"+
        "<tbody id=\"body"+i+"\">"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>对象姓名</td>"+
        "<td>"+
        "<input type=\"text\" id=\"userName"+i+"\"  name=\"tbVoteMemberPOList["+i+"].userName\"  valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input class=\"greenbtn mr10\" type=\"button\"  onclick=\"selectMember("+i+")\" value=\"选择党员\" />"+
        "<input type=\"hidden\" id=\"userId"+i+"\"  name=\"tbVoteMemberPOList["+i+"].userId\"/>"+
        "</td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>所属组织</td>"+
        "<td  >"+
        "<input type=\"text\" id=\"organizationName"+i+"\"  name=\"tbVoteMemberPOList["+i+"].organizationName\" valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input type=\"hidden\" id=\"organizationId"+i+"\"  name=\"tbVoteMemberPOList["+i+"].organizationId\"/>"+
        "</td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >先进事迹</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList["+i+"].advancedDeeds\" id=\"advancedDeeds\" ></textarea>"+
        "</td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >党员述职</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList["+i+"].partyWork\" id=\"partyWork\" ></textarea>"+
        "</td>"+
        "</tr>"+
        "</tbody>"+
        "</table>";
        }
	 }
	 $("#memberList").html(html);
	 
	
});
     function  delTable(obj){
       var isDelete=confirm("确定删除?"); 
	   if(isDelete){
	    var tr=obj.parentNode.parentNode;  
	    var table=tr.parentNode.parentNode.parentNode;  
	    $(table).remove();
	    arraylength--;
	   }
    }
 function addTable(){
	  arraylength = arraylength+1;
	 
	 var  html="<table class=\"tableCommon mt5\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
	    "<input type=\"hidden\" name=\"tbVoteMemberPOList["+arraylength+"].voteTopicId\" value=\"${id}\" />"+
	    "<thead>"+
        "<tr>"+
        "<th colspan=\"2\" onclick=\"showTbody("+arraylength+")\" id=\"th"+arraylength+"\">评议对象"+(arraylength+1)+"</th>"+
        "<th style=\"width:20px;\" ><a href=\"javascript:void(0);\"><img src=\"../themes/default/images/u122_normal.png\" onclick=\"delTable(this)\"/></a></th>"+
        "</tr>"+
        "</thead>"+
        "<tbody id=\"body"+arraylength+"\">"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>对象姓名</td>"+
        "<td>"+
        "<input type=\"text\" id=\"userName"+arraylength+"\"  name=\"tbVoteMemberPOList["+arraylength+"].userName\" valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input class=\"greenbtn mr10\" type=\"button\"  onclick=\"selectMember("+arraylength+")\" value=\"选择党员\" />"+
        "<input type=\"hidden\" id=\"userId"+arraylength+"\"  name=\"tbVoteMemberPOList["+arraylength+"].userId\"/>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\" ><span style=\"color: red\">*</span>所属组织</td>"+
        "<td  >"+
        "<input type=\"text\" id=\"organizationName"+arraylength+"\"  name=\"tbVoteMemberPOList["+arraylength+"].organizationName\" valid=\"{must:true,tip:'对象姓名',fieldType:'pattern'}\"/>&nbsp;&nbsp;&nbsp;"+
        "<input type=\"hidden\" id=\"organizationId"+arraylength+"\"  name=\"tbVoteMemberPOList["+arraylength+"].organizationId\"/>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >先进事迹</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList["+arraylength+"].advancedDeeds\" id=\"advancedDeeds\" ></textarea>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "<tr>"+
        "<td class=\"tdBlue\"  >党员述职</td>"+
        "<td >"+
        "<textarea rows=\"5\" cols=\"80\" name=\"tbVoteMemberPOList["+arraylength+"].partyWork\" id=\"partyWork\" ></textarea>"+
        "</td>"+
        "<td></td>"+
        "</tr>"+
        "</tbody>"+
        "</table>";

	 $("#memberList").append(html);
	 flashIframe_GDZC();
 }
 function showTbody(i){
	 $("#body"+i+"").is(":hidden") ? $("#body"+i+"").slideDown() : $("#body"+i+"").slideUp(); 

 }
function getRepeatData(input){ 
         var ary = input.split(","); 
           
         var rdata = {}; 
         for(var i in ary){ 
            var c = ary[i]; 
            rdata[c] ? (rdata[c]++) : (rdata[c]= 1)  
         } 
           
         for(var k in rdata){ 
           if(rdata[k] > 1) {
              return true;
              }else{
            	  return false;
              }
         }  
      } 

 var voteLimit = "${param.voteLimit}";
 
 $('#save').click(function(){
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
    	  var string="";
    	  for(var i=0;i<=arraylength;i++){
    		   
    		  string+=$("#userId"+i+"").val()+",";
    	  }
    	  if(getRepeatData(string)){
    		  alert("选择的党员有重复,请重新选择!");
    		  return;
    	  }
    	  if(arraylength+1 < voteLimit){
    		  alert("评议党员必须大于等于"+voteLimit+"个!");
    		  return
    	  }
         document.getElementById("save").disabled = true; 
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert(result.desc);
                    window.location.href="${baseURL}/jsp/component/vote/partymemberVote/partymemberVoteList.jsp";
                   
                } else {
                    alert(result.desc);
                     document.getElementById("save").disabled =false; 
                      flashIframe_GDZC();
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
 function selectMember(type){
	  window.open("${baseURL}/jsp/component/vote/partymemberVote/selectPartyMember.jsp?type="+type,"_blank","height=600, width=800, top=100,left=200");
   }
 function flashIframe_GDZC() {
	var i = parent.document.getElementById("ifm");
	if (i != null) {
		if (document.body.scrollHeight < 700) {
			i.style.height = "730px";
		} else {
			//i.style.height = (document.body.scrollHeight + 30) + "px";
		}
	}
}
</script>

</body>
</html>
