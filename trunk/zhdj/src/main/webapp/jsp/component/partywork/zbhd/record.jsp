<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="branchRecord"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>支部活动记录</title>
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
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>

</head>

<body style="background: #f6ebe5;" >
<form action="${baseURL}/branch/branchAction!ajaxSaveRecord.action" method="post" id="id_form_search">
    <div class="searchWrap">
        <div class="title">
            <h2 class="icon1">活动记录</h2>
        </div>
    </div>
    <div class="text_bg noneborder" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/branch/branchAction!ajaxView.action?id=${param.id}">
        <tbody>
        <tr>
            <td class="tdBlue">标题<input type="hidden" name="tbBranchActivityPO.id" id="partyLectureId"/></td>
            <td colspan="3" name="tbBranchActivityPO.title">
            </td>
        </tr>
        <tr>
        	<td class="tdBlue">应到人员</td>
        	<td>
        		<div id="user_list_1"></div>
        	</td>
        </tr>
        <tr>
        	<td class="tdBlue">签到人员</td>
        	<td>
        		<div id="user_list_2"></div>
        	</td>
        </tr>
        <tr>
        	<td class="tdBlue">请假人员</td>
        	<td>
        		<div id="user_list_3"></div>
        	</td>
        </tr>
        
        <tr>
            <td class="tdBlue">补录人员</td>
            <td>
            	<div id="user_list_4"></div><input class="greenbtn mr10" type="button" value="选择用户"  onclick="javascript:selectUser();">
            	<input id="userIds" type="hidden" name="userIds"/>
            </td>
        </tr>
        <tr>
        	<td class="tdBlue">人数统计</td>
        	<td>
        		应到人数
        		<span id="should_person_number"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		实到人数
        		<span id="actual_person_number"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		请假人数
        		<span id="forLeave_person_number"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		缺席人数
        		<span id="absent_person_number""></span>
        	</td>
        </tr>
        <tr>
            <td class="tdBlue">活动记录</td>
            <td colspan="3">
               <textarea rows="6" cols="80" name="tbBranchActivityPO.record" id="record" ></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    </div>


<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="save" permission="branchRecord" value="完成"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">
 var userMap = new HashMap();
  
  $(document).ready(function(){
	  initUserInfo();
		
	  var editor1; 
	  KindEditor.ready(function(K) {
			editor1 = K.create('textarea[name="tbBranchActivityPO.record"]', {
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
	            $('#id_form_search').find('textarea[name="tbBranchActivityPO.record"]').val(K('textarea[name="tbBranchActivityPO.record"]').val());
	        }  
		})});
  });
    
    
    
    
 $('#save').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_search")) {
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
 
    
   
	 function getUserMap() {
	     return userMap;
	 }
	
	 function updateUser() {
		 $('#userIds').val(userMap.keys().join(","));
	     var userTable = new DataTable({
	         data: userMap.values(),
	         colsCount:8
	     });
	     userTable.createTable("user_list_4");
	     window.parent._resetFrameHeight(1);
	 }
    
    
	 function updateUserDefault(obj,map) {
		 if(map.values() != null){
		     var userTable = new DataTable({
		         data: map.values(),
		         colsCount:8
		     });
		     userTable.createTable(obj);
		     window.parent._resetFrameHeight(1);
	     }
	 }
	 
    function initUserInfo(){
    	var ydNum = 0;
    	var sdNum = 0;
    	var qjNum = 0;
    	var qxNum = 0;
	    $.ajax({
	        url: "${baseURL}/meetingUser/meetingUserAction!ajaxUserDetails.action",
	        data: {"id": $("#partyLectureId").val()},
	        type: "get",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	            	for(var i=1;i<=4;i++){
	            		var userList;
	            		if(i==1){
	            			userList = result.data.shoulds;
	            			ydNum = userList != null ? userList.length : 0;
	            			$("#should_person_number").html(ydNum);
	            		}else if(i==2){
	            			userList = result.data.actuals;
	            			sdNum = userList != null ? userList.length : 0;
						    
	            		}else if(i==3){
	            			userList = result.data.forLeaves;
	            			qjNum = userList != null ? userList.length : 0;
	            			$("#forLeave_person_number").html(qjNum);
	            		}else if(i==4){
	            			userList = result.data.supplements;
	            			qxNum = ydNum - sdNum - qjNum;
	            			$("#absent_person_number").html(qxNum);
	            			$("#actual_person_number").html(sdNum+(userList != null ? userList.length : 0));
	            		}
	            		var uMap = new HashMap();
	            		if(userList != null){
			                for (var j = 0; j < userList.length; j++) {
			                    uMap.put(userList[j].userId, userList[j].name);
			                }
			                if(i == 4){
			                	userMap = uMap;
			                }
		                }
		                updateUserDefault("user_list_"+i,uMap);
	            	}
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
    }
    
    function selectUser() {
     	window.open('${baseURL}/jsp/component/basis/partymenber/multiSelecttPartyMember.jsp', '用户选择', 'height=600, width=1000, toolbar=no,scrollbars=yes, menubar=no, resizable=yes,location=no, status=no');
 	}
</script>
</form>
</body>
</html>
