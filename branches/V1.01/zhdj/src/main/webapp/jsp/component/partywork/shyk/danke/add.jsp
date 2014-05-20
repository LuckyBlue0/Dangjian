<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="dangkeType,meetingWay,meetingSmsNotice"></jsp:param>
    <jsp:param name="permission" value="dangkeAdd"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>起草党课</title>
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
<form action="${baseURL}/meeting/dangkeAction!ajaxSave.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="2">起草党课</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="tdBlue">标题</td>
            <td  >
            	<input type="hidden" name="tbMeetingPO.id"/>
                <input type="text" id="title" style="width:400px" name="tbMeetingPO.title" valid="{must:true,tip:'标题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">党课类型</td>
            <td >
                <select dictType="dangkeType" defaultTip="" name="tbMeetingPO.type" id="type" valid="{must:true,tip:'党课类型',fieldType:'pattern'}"/>
            </td>
        </tr>
        <tr>
            <td  class="tdBlue">上课方式</td>
            <td>
            	<input type="radio" dictType="meetingWay" name="tbMeetingPO.way" id="way" valid="{must:true,tip:'上课方式',fieldType:'pattern'}"/>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">上课时间</td>
            <td>开始
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"
					type="text" style="width: 200px"
					name="tbMeetingPO.startTime"
					valid="{must:true,tip:'开始时间',fieldType:'pattern'}" id="startTime"/>
            	&nbsp;&nbsp;结束 
				<input
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,readOnly:true,isShowWeek:true,minDate:'%y-%M-{%d} %H-%m-%s',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"
					type="text" style="width: 200px"
					name="tbMeetingPO.endTime"
					valid="{must:true,tip:'结束时间',fieldType:'pattern'}" id="endTime"/>
					<span style="color: red">*</span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">上课地点</td>
            <td >
            	<input type="text" id="title" style="width:400px" name="tbMeetingPO.address" valid="{must:true,tip:'上课地点',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
            	<span style="color: red">*</span>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">参与人员</td>
            <td>
            	<div id="user_list"></div><input class="btn4" type="button" value="选择用户"  onclick="javascript:selectUser();">
            	<input id="userIds" type="hidden" name="userIds"/>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">党课内容</td>
            <td >
               <textarea rows="5" cols="90" name="tbMeetingPO.content" id="content" ></textarea>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">短信通知</td>
            <td >
               <!--  <input type="checkbox" dictType="meetingSmsNotice" name="tbMeetingPO.smsNotice" id="smsNotice" />-->
               <input type="checkbox" value="1" name="tbMeetingPO.smsNotice" id="smsNotice" />
               	(勾选后，将以短信的方式发送到参与人员的手机)
            </td>
        </tr>
        </tbody>
    </table>



<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" id="save" permission="dangkeAdd" value="保存并发布"/>&nbsp;
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 var userMap = new HashMap();
 
  $(document).ready(function(){
	   updateUser();
	    document.getElementById("save").disabled = false;
	    //initChecked();
	    flashIframe_GDZC();
   
  });
    
    
    
    
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
    	flashIframe_GDZC();
         document.getElementById("save").disabled = true; 
        // 提交数据
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            success:function(result) {
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
    }});
 
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
