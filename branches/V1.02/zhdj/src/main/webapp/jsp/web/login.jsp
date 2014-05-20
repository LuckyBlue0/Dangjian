<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp"%>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
	<jsp:param name="permission" value=""></jsp:param>
	<jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="text/css" rel="stylesheet" href="css/base.css" />
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.7.1.min.js">
</script>
		<script type="text/javascript"
			src="${baseURL}/js/do1/common/common.js">
</script>
		<script type="text/javascript" src="js/plug.js">
</script>
		<script type="text/javascript" src="js/fun.js">
</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<%@taglib prefix="s" uri="/struts-tags"%>

		<title>智慧党建平台</title>
	</head>

<body  onkeydown="BindEnter(event)" onload="GetCookie();">
<!--头部-->
<div id="head">
        <div class="topHead">
            <div class="wrapper">
                <div class="welcome clearfix">
                    <p class="fl">
                        <span>${sessionScope.uservo.username}&nbsp;您好，欢迎来到智慧党建平台！</span>
                        <span>[<a href="${baseURL}/jsp/web/login.jsp">登录</a>]</span>
                        <span>[<a href="${baseURL}/jsp/web/registe.jsp">注册</a>]</span>
                    </p>
                    <p class="fr">
                     <a href="${baseURL}/jsp/web/index.jsp">首页</a><i>|</i>
                        <a href="javascript:void(0);" onclick="checkLogin('${baseURL}/jsp/web/membercenter/main.jsp','${sessionScope.uservo.userType}')">个人主页</a><i>|</i><a href="javascript:void(0)" onclick="checkLogin('${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${sessionScope.uservo.organizationId}','${sessionScope.uservo.userType}')">组织主页</a><i>|</i>
                        <a href="javascript:void(0)" onclick="window.external.AddFavorite(document.location.href,document.title)">收藏本站</a>
                    </p>
                </div>
            </div>
        </div>



		</div>
		<!--头部-->
		<!--正文-->
		<div id="main">

			<div class="wrapper">
				<div class="login">
					<div class="loginTop"></div>
					<div class="login_bd">
						<div class="login_tab">
							<div class="tab_hd">
								<ul class="clearfix">
									<li class="on" onclick="changeValue(1);">
										党员登录
									</li>
									<li onclick="changeValue(2);">
										群众登录
									</li>
								</ul>
							</div>
							<form action="${baseURL}/index/indexAction!login.action"
								id="formId">
								<input type="hidden" name="type" id="type" value="1" />
								<div class="tab_bd">
									<div class="lBox mt40">
										<dl class="clearfix">
											<dt>
												用户帐号：
											</dt>
											<dd>
												<input class="loginInput" type="text" id="username"
													name="uservo.username" />
											</dd>
											<span id="tip" style="color: red"></span>
										</dl>


                            <dl class="clearfix">
                                <dt>服务密码：</dt>
                                <dd><input class="loginInput" type="password" id="pwd" name="uservo.userPwd"/></dd><span id="tip1" style="color:red"></span>
                            </dl>
                            <dl class="clearfix">
                               <dt>&nbsp;</dt>
                                <dd class="clearfix">
                                   <div class="fl">
                                        <input type="checkbox" id="isRememberPassword" name="remember" onclick="checkInCorrect();" />
                                        <label for="" class="f14">记住密码</label>
                                   </div>
                                   <!--  
                                    <div class="fr">
                                        <a href="#" class="redf">忘记密码?</a>
                                    </div>
                                    -->
                                </dd>
                            </dl>

                            <input type="button" class="btnlg ml5em mt20" onclick="login();" value="登录"/>
                        </div>


									<div class="tr mt30 mr10 mb10">
										<a href="${baseURL}/jsp/web/registe.jsp" class="redf">群众注册入口>>
										</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--正文 end-->

		<!--页脚-->
		<s:include value="/jsp/web/common/foot.jsp"></s:include>
		<!--页脚 end-->
		<script type="text/javascript">
function changeValue(type) {
	$("#type").val(type);

}
function login() {
	if ($("#username").val() == '') {
		$("#tip").text("用户帐号不能为空！");
		return;
	} else {
		$("#tip").text("");
	}
	if ($("#pwd").val() == '') {
		$("#tip1").text("密码不能为空！");
		return;
	} else {
		$("#tip1").text("");
	}
	$('#formId')
			.ajaxSubmit(
					{

						dataType : 'json',
						success : function(result) {
							if ("0" == result.code) {
								if ($("#type").val() == "1") {
									window.location.href = "${baseURL}/jsp/web/index.jsp" 
								} else {
									window.location.href = "${baseURL}/jsp/web/index.jsp"
								}
							} else {
								alert(result.desc);
							}
						},
						error : function() {
							alert("登录出错，请稍后再试!");
						}
					});
}
function checkLogin(url) {
	var isLogin = "${sessionScope.isMemberLogin}";
	if (isLogin == "true") {
		window.location.href = url;
	} else {
		window.location.href = "${baseURL}/jsp/web/login.jsp";
	}
}
 function checkLogin(url,type){
	  
	  var isLogin="${sessionScope.isMemberLogin}";
	  if(isLogin=="true"){
		  if(type!="1"){
		  alert("您还不是党员，无法登录个人或组织空间！"); 
		  return;
	      }
		  window.location.href=url;
	  }else{
		  window.location.href="${baseURL}/jsp/web/login.jsp";
	  }
  }
 
 
 function checkInCorrect() //判断用户名和密码是否为空 
		{
			var isSave = document.getElementById('isRememberPassword').checked; //保存按键是否选中
			if(isSave){
			    if (document.getElementById('username').value == "") {
			        alert('请输入用户名！')
			        document.getElementById('username').focus();
			        return false
			    }
			    if (document.getElementById('pwd').value == "") {
			        alert('请输入密码！')
			        document.getElementById('pwd').focus();
			        return false
			    } else {
			        saveInfo();
			        return true;
			    }
		    }
			saveInfo();
		}
 
	saveInfo = function () {
		    try {
		        var isSave = document.getElementById('isRememberPassword').checked; //保存按键是否选中 
		        if (isSave) {
		            var usernm = document.getElementById('username').value;
		            var userpsw = document.getElementById('pwd').value;
		            if (usernm != "" && userpsw != "") {
		                SetCookie(usernm, userpsw);
		            }
		        } else {
		            SetCookie("", "");
		        }
		    } catch (e) {

		    }
		}
	var win = window;
	if(win != win.parent){
		window.relo = true;
	}
	while(win != win.parent){
		win = win.parent;
	}
	if(window.relo)win.location.reload();
	
	function myLogin(){
		login();
	}
	function myReset(){
		document.getElementById('username').value="";
		document.getElementById('pwd').value="";
	}
	function BindEnter(obj) {    
		 //使用document.getElementById获取到按钮对象     
		 if(obj.keyCode == 13){                      
			myLogin();
		  obj.returnValue = false;        
		 }
	
	 } 
	
	
		function SetCookie(username, psw) {
		    var Then = new Date();
		    Then.setTime(Then.getTime() + 18800001100);
		    document.cookie = "username=" + username + "%%" + psw + ";expires=" + Then.toGMTString();
		}

		function GetCookie() {
		    var nmpsd;
		    var nm;
		    var psd;
		    var cookieString = new String(document.cookie);
		    var cookieHeader = "username=";
		    var beginPosition = cookieString.indexOf(cookieHeader);
		    cookieString = cookieString.substring(beginPosition);
		    var ends = cookieString.indexOf(";");
		    if (ends != -1) {
		        cookieString = cookieString.substring(0, ends);
		    }
		    if (beginPosition > -1) {
		        nmpsd = cookieString.substring(cookieHeader.length);
		        if (nmpsd != "") {
		            beginPosition = nmpsd.indexOf("%%");
		            nm = nmpsd.substring(0, beginPosition);
		            psd = nmpsd.substring(beginPosition + 2);
		            document.getElementById('username').value = nm;
		            document.getElementById('pwd').value = psd;
		            if (nm != "" && psd != "") {
		               document.getElementById('isRememberPassword').checked = true;
		            }
		        }
		    }
		}
</script>
	</body>
</html>
