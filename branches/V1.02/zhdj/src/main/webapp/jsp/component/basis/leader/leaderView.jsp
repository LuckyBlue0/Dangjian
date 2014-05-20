<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="partyPosition"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/leaderinfo/leaderinfoAction!ajaxUpdate.action" method="post" id="org_add">
 <div class="searchWrap" style="display: none;">
            <div class="title">
                <h2 class="icon1">查看领导班子</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/leaderinfo/leaderinfoAction!ajaxView.action?id=${param.id}">
        <input type="hidden" name="tbLearderVO.id"/>

        <tbody>
            <tr>
                <td  class="tdBlue">领导姓名</td>
                <td >
                    <input class="form120px"  id="memberName" name="tbLearderVO.userName" type="text" disabled="disabled" onclick="selectMember()"
                           valid="{must:true,tip:'组织名称',fieldType:'pattern',length:100}"/>
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">所属组织</td>
                <td >
                    <input class="form120px" id="parentName" name="tbLearderVO.organizationName"   type="text" disabled="disabled" onclick="selectParent()"
                           valid="{must:false,tip:'所属组织',fieldType:'pattern',length:100}"/>
                    
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">党内职务</td>
                <td >
                   <select name="tbLearderVO.duty" dictType="partyPosition" valid="{must:true,length:50,tip:'党内职务'}" />
                </td>
            </tr>

          
         
        </tbody>
    </table>
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

   

  

   function showType($this){
	   var type=$($this).val();
	   if(type=='3'){
		   $("#newType").css("display","");
	   }else{
		   $("#newType").css("display","none");
	   }
   }
 
</script>

</body>
</html>
