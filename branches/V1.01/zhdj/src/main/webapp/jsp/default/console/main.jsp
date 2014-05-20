<%@ page import="cn.com.do1.dqdp.core.permission.IUser" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>智慧党建平台</title>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
        <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>

    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/do1/common/tree2.0.js"></script>  
      
    <script type="text/javascript" src="${baseURL}/js/showLeft.js"></script>
    <!--end-->
    <script type="text/javascript">
        try{ var _browser_Version = navigator.appVersion.split(";")[1].replace(/[ ]/g, ""); }catch(e){}
    </script>
    <!--[if IE 6]>
    <script src="${baseURL}/js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('img,.topMenu a');
    </script>
    <![endif]-->
    <style type="text/css">
		.hidden {
		    display: none;
		}
		html {
		overflow:visible;
		}
	</style>
</head>
<body class="indexBg" >
<div class="head">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
		      <td width="40%" class="headLeft" >
		          <div style="_float:left;">
		              <a href="#"><img src="${baseURL}/themes/${style}/css/images/logo.png" alt=""></a>
		          </div>
		      </td>
		      <td class="headRight">
		          <div class="topMenu mt10">
		             <a class="help" href="#">帮助</a>
		             <a class="exit" href="${baseURL}/j_spring_security_logout?dqdp_csrf_token=${sessionScope.dqdp_csrf_token}">退出</a>
		          </div>
		          <div class="loginmsg">欢迎您：<%=((IUser) (DqdpAppContext.getCurrentUser())).getPersonName()%> &nbsp;&nbsp;
		          <a href=""  onclick="changePassword();return false;">修改密码</a>
		           </div>
		      </td>
        </tr>
    </table>
</div>
<!--头部 end-->
<table style="height: 465px;" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td valign="top" bgcolor="#fffefb" class="left" id="left" height="400px">
            <div id='id_div_left' ></div>
        </td>
        <td width="5" valign="top" class="showLeft" id="cen2">
            <div class="shrink"><a href="javascript:showLeft();" title="点击可收缩左侧" id="showhide_btn"></a></div>

            <!--收缩左侧的小箭头 end--></td>
        <td valign="top" class="td_right" >
            <iframe id="ifm" align="center" name="mainFrame" style="margin-left: 5px;"
                    width="99.5%" frameborder="0" height="100%" src="${baseURL}/jsp/default/login/right.jsp"></iframe>
            <%--onload="javascript:dyniframesize('ifm');"--%>
        </td>
        <!--右侧 end-->
    </tr>
</table>
<!--主体部分 end-->



<div id="footer">
    <p class="copyright">
        <span>Copyright 2013 www.do1.com.cn  All Rights Reserved</span>
    	<!-- 
        <span>版权所有：中共广州市萝岗区委组织部</span>
        <span>技术支持：广州市道一信息技术有限公司</span>
         -->
    </p>
</div>

<!--底部 end-->
<!-- thickbox -->
<a href="" title="add a caption to title attribute / or leave blank" class="thickbox" id="thickbox_a" style="display: none">iFrame Modal auction</a>

<div id="change_password_div" title="修改密码">

        <form id="chang_password_form" action="${baseURL}/user/user!changePasswordBySelf.action" method="post">
            <fieldset>
                <br/>
                <br/>
                <input type="hidden" name="userName" id="userName" value="<%=((IUser) (DqdpAppContext.getCurrentUser())).getUsername()%>"/>
                <label for="oldPassword" style="padding-top: 15px">原&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码</label>
                <input type="password" name="oldPassword" id="oldPassword" class="form120px"/>
                <br/>
                <br/>
                <label for="newPassword" style="padding-top: 15px">新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码</label>
                <input type="password" name="newPassword" id="newPassword" class="form120px"/>
                <br/>
                <br/>
                <label for="confirmPassword" style="padding-top: 15px">确认新密码</label>
                <input type="password" name="confirmPassword" id="confirmPassword" class="form120px"/>
                <br/>
                <br/>
            </fieldset>
        </form>
    </div>

</body>
</html>

<script type="text/javascript">
    $(document).ready(function () {
        doMenuLoad();
       
	var i = parent.document.getElementById("ifm");
	if (i != null) {
		if (document.body.scrollHeight < 465) {
			i.style.height = "465px";
		} else {
			 i.style.height = ($(window).height()-90) + "px";
		}
	}
	
	init();
	
    });
    function init()
	{
		var screenH = $(window);
		var frameH = screenH - 90;
		
		$('#outwrap').height(frameH).css('overflow','auto');
	}
    function doMenuLoad() {
        $.ajax({
            type:"post",
            dataType:"json",
            data:{'dqdp_csrf_token':dqdp_csrf_token},
            url:baseURL + "/mainpage/mainpage!getMainPageInfo.action",
            beforeSend:function (XMLHttpRequest) {
                //ShowLoading();
            },
            success:function (data, textStatus) {
                if (data.code == "0"){
                    $("#id_div_left").tree({
                        nodes:data.data.tree,
                        showall:false
                    });
                }
                else
                    alert(data.desc);
            },
            complete:function (XMLHttpRequest, textStatus) {
                //HideLoading();
            },
            error:function () {
                //请求出错处理
            }
        });
    }

    $("#change_password_div").dialog({
        autoOpen:false,
        height:280,
        width:300,
        modal:true,
        buttons:{
            "保存":function () {
                if (checkPassword()) {
                    $('#chang_password_form').ajaxSubmit({
                        dataType:'json',
                        data:{'dqdp_csrf_token':dqdp_csrf_token},
                        success:function (result) {
                            _alert("操作结果", result.desc);
                            $("#change_password_div").dialog("close");
                        },
                        error:function () {
                            _alert("操作结果", "保存字典过程中发生网络错误");
                        }
                    });
                }
            },
            "取消":function () {
                $(this).dialog("close");
            }
        },
        close:function () {

        }
    });

    function changePassword() {
        $("#change_password_div").dialog("open");
    }

    function checkPassword() {
        if ($("#newPassword").val() != $("#confirmPassword").val()) {
            _alert("error", "两次输入的密码不一致");
            return false;
        }
        if (_isNull($("#newPassword").val()) || _isNull($("#confirmPassword").val())) {
            _alert("error", "输入的新密码为空");
            return false;
        }
        if (_isNull($("#oldPassword").val())) {
            _alert("error", "原始密码为空");
            return false;
        }
        return true;
    }

</script>
