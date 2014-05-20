<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="dict" value="degree,personSex"></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<link href="../css/main.css" type="text/css" rel="Stylesheet" />
<script type="text/javascript" src="../js/jquery1.2.6.pack.js"></script>
<script  type="text/javascript" src="../js/ui.core.packed.js" ></script>
    <script type="text/javascript" src="../js/ui.draggable.packed.js" ></script>
    <script type="text/javascript" src="../js/CutPic.js"></script>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script type="text/javascript" src="../js/tab.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>智慧党建平台</title>
</head>

<body>
<!--头部-->
<!--头部-->
<!--正文-->
       
                        
           <!-- 右边内容区域 -->
           <div class="activity_right fl mt10 pb20" datasrc="${baseURL}/membercenter/membercenterAction!searchUserDetail.action">
                      <form action="${baseURL}/membercenter/membercenterAction!updateUserDetail.action" method="post" id="id_form_add">
           
           <input type="hidden" name="partymember.id" />
                <div class="act_top">基本资料</div> 
                <div class="act_bd">
                    <div class="act_tabWrap mt10">
                        <div class="act_tab_hd clearfix">
                            <span class="on" onclick="changeDiv(1);" id="sp1">个人资料</span><span onclick="changeDiv(2);" id="sp2">密码修改</span>
                        </div>
                        <div class="act_tab_bd clearfix" style="display: block" id="inf">
                            
                            <div id="Step2Container" class="div_right" style="width: 310px;height: 400px;display: none" >
                              <div class="title"><b> 裁切头像照片</b></div>
                              <div class="uploadtooltip">您可以拖动照片以裁剪满意的头像</div>                              
                   <div id="Canvas" class="uploaddiv">
                   
                            <div id="ImageDragContainer">                               
                               <img id="ImageDrag" class="imagePhoto" src=""  style="border-width:0px;" />                                                        
                            </div>
                            <div id="IconContainer">                               
                               <img id="ImageIcon" class="imagePhoto" src="" style="border-width:0px;" />                                                        
                            </div>                          
                    </div>
                    <div class="uploaddiv">
                        <input type="button"  value="保存头像" id="btn_Image" onclick="saveImg();"/>
                    </div>           
                   <input name="txt_width" type="hidden" value="1" id="txt_width" /><br />
                     <input name="txt_height" type="hidden" value="1" id="txt_height" /><br />
                    <input name="txt_top" type="hidden" value="82" id="txt_top" /><br />
                     <input name="txt_left" type="hidden" value="73" id="txt_left" /><br />
                    <input name="txt_DropWidth" type="hidden" value="120" id="txt_DropWidth" /><br />
                    <input name="txt_DropHeight" type="hidden" value="120" id="txt_DropHeight" /><br />
                     <input name="txt_Zoom" type="hidden" id="txt_Zoom" />
                      <input name="picture" type="hidden" id="picture" />
                   </div>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">用户名</dt>
                                <dd class="ml5em"><input type="text" class="reginput" name="partymember.userName"></dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">姓    名</dt>
                                <dd class="ml5em"><input type="text" class="reginput" disabled="disabled" name="partymember.name"></dd>
                            </dl>

                            <dl class="clearfix">
                                <dt class="fl w4em tr">用户头像</dt>
                                <dd class="ml5em">
                                <c:if test="${empty partymember.portraitPic}">
                                   <img src="${baseURL}/image/man.gif" id="img1" width="90px" height="90px" alt=""/>
                                </c:if>
                                <c:if test="${not empty partymember.portraitPic}">
                                    <img src="${baseURL}/@{partymember.portraitPic}" id="img2" width="90px" height="90px" alt=""/>
                                </c:if>
                              <input class="reginput" name="file" id="file" type="file"
								style="width: 65px" />
							<input name="partymember.portraitPic" id="imgPath"
								class="reginput" value="" type="hidden"
								valid="{must:false,tip:'头像'}" />
							
							<input type="button" value="上传头像" style="width: 60px" onClick="uploadFile();">
							
                                </dd>

                            </dl>

                            <dl class="clearfix">
                                <dt class="fl w4em tr">所属组织</dt>
                                <dd class="ml5em"><input type="text" class="reginput" disabled="disabled" name="partymember.organizationName"></dd>
                                <input type="hidden" name="partymember.organizationId"/>
                            </dl>

                            <!-- dl class="clearfix">
                                <dt class="fl w4em tr">党内职务</dt>
                                <dd class="ml5em"><input type="text" class="reginput"  disabled="disabled" name="partymember.positionDesc"></dd>
                            </dl> -->

                            <dl class="clearfix">
                                <dt class="fl w4em tr">学历</dt>
                                <dd class="ml5em">
                                    <select dictType="degree" id="" class="w240" name="partymember.degree"  disabled="disabled">
                                    </select>
                                </dd>
                            </dl>

                            <dl class="clearfix">
                                <dt class="fl w4em tr">入党时间</dt>
                                <dd class="ml5em">
                                    <input type="text" class="reginput"  disabled="disabled" name="partymember.partyTime">
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">性别</dt>
                                <dd class="ml5em">
                                    <select  class="w240"  disabled="disabled" name="partymember.sex" dictType="personSex">
                                    <input type="hidden" name="partymember.sex"/>
                                    </select>
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">手机号码</dt>
                                <dd class="ml5em">
                                    <input type="text" id="mobile" class="reginput" maxlength="11" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" name="partymember.mobile" valid="{must:false, length:11,fieldType:'pattern', tip:'手机号码'}" >
                                	<span style="color: red" id="mobileTip"></span>
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">电子邮箱</dt>
                                <dd class="ml5em">
                                    <input type="text" class="reginput" id="email" name="partymember.email">
                               	 	<span style="color: red" id="emailTip"></span>
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">备注</dt>
                                <dd class="ml5em">
                                    <textarea  id=""  class="textarea" name="partymember.note"></textarea>
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt class="fl w4em tr">&nbsp;</dt>
                                <dd class="ml5em">
                                    <input class="btnstore" type="button" onclick="doSave();" value="保存"/>
                                </dd>
                            </dl>

                        </div>
                        <div class="act_tab_bd" style="display: none;" id="pwd">
                           <div class="modify_psd pt50">
                               <dl class="clearfix">
                                   <dt class="fl w5em tr">旧密码</dt>
                                   <dd class="ml5em pl10">
                                       <input type="password" class="inputstyle" name="password" id="password" valid="{must:false, length:100,fieldType:'pattern', tip:'旧密码'}"/>
                                       <span class="fred">*</span><span style="color: red" id="pwdTip1"></span>
                                   </dd>
                               </dl>
                               <dl class="clearfix">
                                   <dt class="fl w5em tr">新密码</dt>
                                   <dd class="ml5em pl10">
                                       <input type="password" class="inputstyle" name="newPassword" id="newPassword" valid="{must:false, length:100,fieldType:'pattern', tip:'新密码'}"/>
                                       <span class="fred">*</span><span style="color: red" id="pwdTip2"></span>
                                       <span>6-18位</span>
                                   </dd>
                               </dl>
                               <dl class="clearfix">
                                   <dt class="fl w5em tr">确认密码</dt>
                                   <dd class="ml5em pl10">
                                       <input type="password" class="inputstyle" name="cfPassword" id="cfPassword" valid="{must:false, length:100,fieldType:'pattern', tip:'确认密码'}"/>
                                       <span class="fred">*</span><span style="color: red" id="pwdTip3"></span>
                                   </dd>
                               </dl>
                               <input type="button" value="保存" class="btnstore ml5em mt20" onclick="doSavePwd()"/>
                           </div> 
                        </div>
                    </div>
                </div>
                      </form>
            
          </div>
           


<!--正文 end-->

<!--页脚-->
<!--页脚 end-->
<script type="text/javascript">
$(document).ready(function() {
	var imgPath = $("#imgPath").val();
	if ('' != imgPath) {
		$("#img1").attr("src", "${baseURL}/" + imgPath);
		$("#img2").attr("src", "${baseURL}/" + imgPath);
	}
});
function doSave() {
    if ($('#mobile').val() != '' && !/^[1]\d{10}$/.test($('#mobile').val())) {
   		$("#mobileTip").html("<br/>请输入正确的手机号码");
   		return;
   }else{
	   $("#mobileTip").html("");
   }
	var email = $("#email").val();
	var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;
　　if (""!= email && !Regex.test(email)){
		$("#emailTip").html("<br/>请输入正确邮箱地址");
　　		return;
　　}else{
		$("#emailTip").html("");
	}
		$("#newPassword").val("");
		$("#cfPassword").val("");
		$("#password").val("");
	_doCommonSubmit("id_form_add", null, {
		ok : function() {
			window.parent.location.href="${baseURL}/jsp/web/login.jsp";
		},
		fail : function() {
		}
	});
}
function doSavePwd() {
	var dqdp = new Dqdp();
	     if (dqdp.submitCheck("id_form_add")) {
   if ($("#password").val()=="") {
		$("#pwdTip1").html("<br/>旧密码不能为空！");
		return;
	} else {
		$("#pwdTip1").html("");
	}
   if ($("#newPassword").val()=="") {
		$("#pwdTip2").html("<br/>新密码不能为空！"); 
		return;
	} else {
		$("#pwdTip2").html("");
	}
   if ($("#cfPassword").val()=="") {
		$("#pwdTip3").html("<br/>确认密码不能为空！");
		return;
	} else {
		$("#pwdTip3").html("");
	}

    var pwd=$("#newPassword").val();
        var repwd = new RegExp("^[A-Za-z0-9]{6,18}$", "");
         var ispwd= repwd.test(pwd);
         if(!ispwd){
        	 $("#pwdTip2").text("密码必须由6-18个字母或数字组成");
        	 return;
         }else{
        	 $("#pwdTip2").text("");
         }
   if ($("#newPassword").val() != $("#cfPassword").val()) {
		$("#pwdTip3").html("<br/>两次输出密码不一致，请重新输入！");
		return;
	} else {
		$("#pwdTip3").html("");
	}
   
  $('#id_form_add').ajaxSubmit({
      
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                	alert("您的信息更新成功，请重新登录！");
			        window.parent.location.href="${baseURL}/jsp/web/login.jsp";
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("修改出错，请稍后再试!");  
            }
        });
  }
}
function uploadFile() {
	
	$('#id_form_add').attr("action",
			"${baseURL}/orgManage/orgManageAction!fileUpload.action");
	$('#id_form_add').ajaxSubmit( {
		dataType : 'json',
		success : function(result) {
			if ("0" == result.code) {
				$("#ImageDrag").attr("src", "${baseURL}/" + result.desc);
				$("#ImageIcon").attr("src", "${baseURL}/" + result.desc);
				$("#picture").val(result.desc);			
				$("#ImageDrag").css("width", "354px");
				$("#ImageDrag").css("height", "266px");
				$("#ImageDrag").css("left", "-117px");
				$("#ImageDrag").css("top", "-73px");
				$("#ImageIcon").css("width", "354px");
				$("#ImageIcon").css("height", "266px");
				$("#ImageIcon").css("left", "-35px");
				$("#ImageIcon").css("top", "0px");
				$("#Step2Container").show();
			} else {
				alert(result.desc);
			}
		},
		error : function() { 
			alert('文件上传失败，请稍候再试');
		}
	});
	$('#id_form_add').attr("action",
			"${baseURL}/membercenter/membercenterAction!updateUserDetail.action");
}
function saveImg(){
	$('#id_form_add').attr("action",
			"${baseURL}/membercenter/membercenterAction!savePic.action");
	$('#id_form_add').ajaxSubmit( {
		dataType : 'json',
		success : function(result) {
			if ("0" == result.code) {
				$("#imgPath").val(result.desc);
				$("#img2").attr("src", "${baseURL}/" + result.desc);
				$("#img1").attr("src", "${baseURL}/" + result.desc);
			} else {
				alert(result.desc);
			}
		},
		error : function() { 
			alert('保存失败，请稍候再试'); 
		}
	});
	$('#id_form_add').attr("action",
			"${baseURL}/membercenter/membercenterAction!updateUserDetail.action");

}
function changeDiv(type){
	if("1"==type){
		$("#inf").css("display","");
		$("#pwd").css("display","none");
	    $("#sp1").attr("class","on");
	    $("#sp2").attr("class","");
		_resetFrameHeight();
	}
	if("2"==type){
		$("#inf").css("display","none");
		$("#pwd").css("display","");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","on");
	    _resetFrameHeight();
	}
}
</script>
</body>
</html>
