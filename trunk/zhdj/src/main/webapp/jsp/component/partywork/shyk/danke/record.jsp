<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="dangkeType,meetingWay,meetingSmsNotice"></jsp:param>
    <jsp:param name="permission" value="dangkeRecord"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>党课记录</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <script type="text/javascript" src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/HashMap.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
	
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
	<style type="text/css">
	</style>
</head>

<body>
<form action="${baseURL}/meeting/dangkeAction!ajaxSave.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/meeting/dangkeAction!ajaxUserDetails.action?id=${param.id}">
        <thead>
        <tr>
            <th colspan="4">党课记录</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="tdBlue">标题<input type="hidden" name="tbMeetingPO.id" id="meetingId"/></td>
            <td colspan="3" name="tbMeetingPO.title">
            </td>
        </tr>
        <tr>
            <td class="tdBlue">参与人员</td>
            <td>
                	共<span name="meetingUserDetail.canyu"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(1);">查看人员名单</a>
            </td>
            <td class="tdBlue">报名人员</td>
            <td >
                	共<span name="meetingUserDetail.baoming"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(2);">查看人员名单</a>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">请假人员</td>
            <td >
                	共<span name="meetingUserDetail.qingjia"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(3);">查看人员名单</a>
            </td>
            <td class="tdBlue">缺席人员</td>
            <td >
                	共<span name="meetingUserDetail.quexi"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(4);">查看人员名单</a>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">已签到人员</td>
            <td >
                	共<span name="meetingUserDetail.qiandao"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(5);">查看人员名单</a>
            </td>
            <td class="tdBlue">未签到人员</td>
            <td>
                	共<span name="meetingUserDetail.weiqiandao"></span>人&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);showMenber(6);">查看人员名单</a>
            </td>
        </tr>
        <tr>
            <td class="tdBlue">党课记录</td>
            <td colspan="3">
               <textarea rows="6" cols="100" name="tbMeetingPO.record" id="content" value=""></textarea>
            </td>
        </tr>
        </tbody>
    </table>

																												  
<div id="user_list"  title="人员信息"></div>
<div class="toolBar">
    <div align="center">
    	<input class="btn4" type="button" id="save" permission="dangkeRecord" value="完成"/>&nbsp;
    	<input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 var userMap = new HashMap();
  var editor1; 
  KindEditor.ready(function(K) {
		editor1 = K.create('textarea[name="tbMeetingPO.record"]', {
		cssPath : '${baseURL}/js/3rd-plug/kindeditor/plugins/code/prettify.css',
		uploadJson : '${baseURL}/KindEditUpload?method=upload',
		fileManagerJson : '${baseURL}/KindEditUpload?method=',
		allowFileManager : true,
		filterMode : false,
		afterCreate : function() {
			this.sync(); 
		},afterBlur:function(){ 
            this.sync(); 
            $('#id_form_search').find('textarea[name="tbMeetingPO.record"]').val(K('textarea[name="tbMeetingPO.record"]').val());
        }  
	})});
  
  
  $(document).ready(function(){
	  flashIframe_GDZC();
  });
    
    
    
    
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
	 function getUserMap() {
	     return userMap;
	 }
	
	 function updateUser() {
	     var userTable = new DataTable({
	         data: userMap.values(),
	         colsCount:5
	     });
	     userTable.createTableNew("user_list");
	     window.parent._resetFrameHeight(1);
	     shwoDiv();
	 }
    function showMenber(type){
    	userMap.clear();
	    $.ajax({
	        url: "${baseURL}/meetingUser/meetingUserAction!ajaxUserDetails.action",
	        data: {"id": $("#meetingId").val(),"type":type},
	        type: "get",
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
    function shwoDiv() {
        $("#user_list").dialog("open");
    }
    
    $("#user_list").dialog({
        autoOpen:false,
        height:200,
        width:550,
        modal:true,
        buttons:{
            "关闭":function () {
                $(this).dialog("close");
            }
        },
        close:function () {

        }
    });
     
</script>
</form>
</body>
</html>
