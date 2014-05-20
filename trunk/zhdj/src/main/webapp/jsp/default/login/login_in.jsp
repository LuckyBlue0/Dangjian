<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../../common/dqdp_common.jsp" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
//    if(request.getProtocol().split("\\/").equals("http")){
//        response.sendRedirect("https://"+request.getRequestURI());
//    };
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link href="${baseURL}/themes/${style}/css/login.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="${baseURL}/js/menu.js"></script>
    <!--
   按钮函数 -->
    <!--[if IE 6]>
    <script src="${baseURL}/js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('img,.topMenu a');
    </script>
    <![endif]-->
</head>
<body>
<script type="text/javascript">
    var lastError = "${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}";
    if (lastError)alert("${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}");
</script>
<div>
<form id="id_form_login" action="${baseURL}/j_spring_security_check" method="post" autocomplete="off">
    <input type='hidden' name='_spring_security_remember_me' value="true" />
    <input type='hidden' name='dqdp_csrf_token' value="${sessionScope.dqdp_csrf_token}" />
<div id="head">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
		      <td width="40%" class="headLeft" >
		          <div style="_float:left;">
		              <a href="#"><img src="${baseURL}/themes/${style}/css/images/logo.png" alt=""></a>
		          </div>
		      </td>
		      <td class="headRight">
		          
		      </td>
        </tr>
    </table>
</div>
<div id="main">
    <div class="loginDiv">
      <div class="loginInfo">
          <h3 class="lgTitle">管理员登录</h3>
          <div class="loginForm mt10">
                <div class="loginItem uname">
                    <input type="text" name="j_username" id="j_username" onkeydown="onKeyUp(event)" valid='{"must":true,"tip":"帐号"}' value='${sessionScope['SPRING_SECURITY_LAST_USERNAME']}' placeholder="用户名"/>
                    <br/><span id="tip1" style="color: red;position: absolute;top: 120px"></span>
                </div>

              <div class="loginItem psd">
                  <input type="password" name="j_password" id="j_password" valid="{must:true,tip:'密码'}" onkeydown="onKeyUp(event)" placeholder="密码"/>
                  <br/><span id="tip2" style="color: red;position: absolute;top: 170px"></span>
              </div>
          </div>
		<!-- 
         <div class="mt10">
             <input type="checkbox"/><label for="" class="ml5 f14">记住帐号</label>
         </div>
         -->
          <div class="mt10">
              <input type="button" onclick="javascript:doLogin();" class="btnLogin" value="登录"/>
          </div>
      </div>
    </div>
</div>
</form>
</div>
<div id="footer">
    <p class="copyright">
        <!-- <span>版权所有：中共广州市萝岗区委组织部</span>-->
        <span>Copyright 2013 www.do1.com.cn  All Rights Reserved</span>
        <!--<span>技术支持：广州市道一信息技术有限公司</span> -->
    </p>
</div>
</body>
<script type="text/javascript">
    function onKeyUp(event) {
        var e = event ? event : (window.event ? window.event : null);
        if (e.keyCode == 13)doLogin();
    }
    function doLogin() {
    	if($("#j_username").val()==""){
    		$("#tip1").text("用户名不能为空"); 
    		return;
    	}else{
    		$("#tip1").text(""); 
    	}
    	if($("#j_password").val()==""){
    		$("#tip2").text("密码不能为空");  
    		return;
    	}else{
    		$("#tip2").text(""); 
    	}
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("id_form_login"))
            document.getElementById("id_form_login").submit();
    }
    function doLoginTest() {
        $("#j_username").val("");
        $("#j_password").val("");
    }

    var _topDocument = getTopFrame(this).parent.document;
    if (_topDocument != document)
        _topDocument.location.href = "${baseURL}/jsp/default/login/login_in.jsp";
    function getTopFrame($win) {
        var pw = getWindowOpener($win);
        if (undefined != pw)
            $win = getTopFrame(pw);
        return $win;
    }


    function getWindowOpener($win) {
        return $win.opener;
    }

    function doAjaxLogin() {
        $('#id_form_login').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                alert(result.desc);
            },
            error:function(result){
                alert("通讯故障");
            }
        });
    }
</script>
</html>
