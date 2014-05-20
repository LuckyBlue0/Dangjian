<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="dangkeType,meetingWay,meetingSmsNotice"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>党课详情</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/HashMap.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
</head>

<body>
<form method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/meeting/dangkeAction!ajaxView.action?id=${param.id}">
        <thead>
        <tr>
            <th colspan="2">党课详情</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="tdBlue" >标题</td>
            <td name="tbMeetingPO.title"></td>
        </tr>
        <tr>
            <td class="tdBlue">党课类型<input type="hidden" name="tbMeetingPO.id" id="meetingId"/></td>
            <td name="tbMeetingPO.typeDesc">
            </td>
        </tr>
        <tr>
            <td  class="tdBlue">上课方式</td>
            <td name="tbMeetingPO.wayDesc"></td>
        </tr>
        <tr>
            <td class="tdBlue">上课时间</td>
            <td>开始&nbsp;&nbsp;<span name="tbMeetingPO.startTime"></span>
            	&nbsp;&nbsp;
            	结束 &nbsp;&nbsp;<span name="tbMeetingPO.endTime"></span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">上课地点</td>
            <td name="tbMeetingPO.address" >
            </td>
        </tr>
        <tr>
            <td class="tdBlue">发布组织</td>
            <td name="tbMeetingPO.organizationName" id="organizationName">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">参与人员</td>
            <td>
            	<div id="user_list"></div>
            	<input id="userIds" type="hidden" name="userIds"/>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">党课内容</td>
            <td name="tbMeetingPO.content">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">短信通知</td>
            <td name="tbMeetingPO.smsNoticeDesc">
            </td>
        </tr>
        </tbody>
    </table>



<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 var userMap = new HashMap();
 
  $(document).ready(function(){
	  listUserByMeetingId();
	  updateUser();
	  flashIframe_GDZC();
  });
    
    
    
 
 function selectUser() {
     window.open('${baseURL}/jsp/component/basis/partymenber/multiSelecttPartyMember.jsp', '用户选择', 'height=600, width=1000, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
 }


 function getUserMap() {
     return userMap;
 }

 function updateUser() {
     $('#userIds').val(userMap.keys().join(","));
     var userTable = new DataTable({
         data: userMap.values(),
         colsCount:8
     });
     userTable.createTable("user_list");
     window.parent._resetFrameHeight(1);
 }
    
	function listUserByMeetingId() {
	    $.ajax({
	        url: "${baseURL}/meeting/dangkeAction!ajaxUser.action",
	        data: {"id": $("#meetingId").val()},
	        type: "post",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	                var userList = result.data.userList;
	                for (var i = 0; i < userList.length; i++) {
	                    userMap.put(userList[i].userId, userList[i].name);
	                }
	                updateUser();
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
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
</form>
</body>
</html>
