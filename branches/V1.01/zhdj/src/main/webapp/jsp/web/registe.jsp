<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
    <jsp:param name="dict" value="personSex,degree,politicalLandscape"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script type="text/javascript" src="js/plug.js"></script>
<script type="text/javascript" src="js/fun.js"></script>
<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>

<title>智慧党建平台</title>
</head>

<body>
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
                        <a href="javascript:void(0);" onclick="checkLogin('${baseURL}/jsp/web/membercenter/main.jsp','${sessionScope.uservo.userType}')">个人空间</a><i>|</i><a href="javascript:void(0)" onclick="checkLogin('${baseURL}/jsp/web/org/orgIndex.jsp','${sessionScope.uservo.userType}')">组织空间</a><i>|</i>
                        <a href="javascript:void(0)" onclick="window.external.AddFavorite(document.location.href,document.title)">收藏本站</a>
                    </p>
                </div>
            </div>
        </div>


	</div><!--头部-->
<!--正文-->
<div id="main">

    <div class="wrapper">
        <div class="login">
            <div class="regTop"></div>
            <form action="${baseURL}/index/indexAction!registe.action" id="formId">
            <div class="regBox">
                <dl class="clearfix">
                    <dt>用户名：</dt>
                    <dd><input type="text" class="reginput" id="username" name="tbPartyDevelopmentMenberPO.userName" onblur="checkUserName();" valid="{must:true,tip:'用户名',fieldType:'pattern',length:'50'}" /><span class="redf mr5 ml5">*</span><span>英文/数字或组合</span><span id="tusername" style="color:red"></span>
                    </dd>
                </dl>

                <dl class="clearfix">
                    <dt>姓名：</dt>
                    <dd><input type="text" class="reginput" name="tbPartyDevelopmentMenberPO.name" id="name" valid="{must:false,tip:'姓名',fieldType:'pattern',length:'50'}" /><span class="ml10">中文字符</span><span id="tname" style="color:red"></span></dd>
                </dl>

                <dl class="clearfix">
                    <dt>性别：</dt>
                    <dd>
                        <span>
                            <input type="radio" dictType="personSex" name="tbPartyDevelopmentMenberPO.sex" valid="{must:false,tip:'性别',fieldType:'pattern'}" defaultValue="1" />
                        </span>

                        <span class="redf mr5 ml5">*</span>
                    </dd>
                </dl>

                <dl class="clearfix">
                    <dt>登录密码：</dt>
                    <dd><input type="password" class="reginput" id="userpwd" name="tbPartyDevelopmentMenberPO.passWord" valid="{must:true,tip:'密码',fieldType:'pattern',length:'18'}"><span class="redf mr5 ml5">*</span><span>6~18位</span><span id="tuserpwd" style="color:red"></span></dd>
                </dl>

                <dl class="clearfix">
                    <dt>确认密码：</dt>
                    <dd><input type="password" class="reginput" id="repassword" valid="{must:true,tip:'确认密码',fieldType:'pattern',length:'18'}"><span class="redf mr5 ml5">*</span><span>6~18位</span><span id="trepassword" style="color:red"></span></dd>
                </dl>

                <dl class="clearfix">
                    <dt>手机号码：</dt>
                    <dd><input type="text" class="reginput" id="mobile" name="tbPartyDevelopmentMenberPO.mobile" valid="{must:false,tip:'手机号码',fieldType:'pattern',length:'11'}"><span id="tmobile" style="color:red"></span></dd>
                </dl>

                <dl class="clearfix">
                    <dt>学历：</dt>
                    <dd>
                        <select name="tbPartyDevelopmentMenberPO.degree" dictType="degree" id="" class="w240" valid="{must:false,tip:'学历',fieldType:'pattern'}">
                        </select>
                    </dd>
                </dl>

                <dl class="clearfix">
                    <dt>出生年月：</dt>
                    <dd><input type="text" class="reginput" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="birthday" name="tbPartyDevelopmentMenberPO.birthday" valid="{must:false,tip:'出生年月',fieldType:'pattern',regex:'^.{1,20}$'}"/></dd>
                </dl>

                <dl class="clearfix">
                    <dt>家庭住址：</dt>
                    <dd><input type="text" class="reginput" name="tbPartyDevelopmentMenberPO.homeAddress" valid="{must:false,tip:'家庭住址',fieldType:'pattern',length:'100'}"></dd>
                </dl>

                <div class="tc mt20">
                    <input type="button" class="btn_green" onclick="registe();" value="立即注册"/>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>

<!--正文 end-->

<!--页脚-->
<s:include value="/jsp/web/common/foot.jsp"></s:include>
<!--页脚 end-->
<script type="text/javascript">

 function registe(){
	    var dqdp = new Dqdp();
	     if (dqdp.submitCheck("formId")) {
	    var name=$("#name").val();
	    var isName=isChn(name);
	    if(""!=name&&!isName){
	    	$("#tname").text("姓名必须为中文!");
        	 return;
	    }else{
	    	$("#tname").text("");
	    }
	    var pwd=$("#userpwd").val();
        var repwd = new RegExp("^[A-Za-z0-9]{6,18}$", "");
         var ispwd= repwd.test(pwd);
         if(!ispwd){
        	 $("#tuserpwd").text("密码必须由6-18个字母或数字组成");
        	 return;
         }else{
        	 $("#tuserpwd").text("");
         }
         var repassword=$("#repassword").val();
         if(pwd!=repassword){
        	 $("#trepassword").text("密码和确认密码不匹配！");
        	 return;
         }else{
        	 $("#trepassword").text("");
         }
         var mobile= $("#mobile").val();
         var reMobile=/^[0-9]*$/; 
         var isMobile=reMobile.test(mobile);
         if(""!=mobile&&!isMobile){
        	 $("#tmobile").text("请输入正确的手机号码");
        	 return;
         }else{
        	 $("#tmobile").text("");
        	 
         }
         if(""!=mobile&&mobile.length!=11){
        	 $("#tmobile").text("请输入正确的手机号码");
        	 return;
         }else{
        	 $("#tmobile").text("");
        	 
         }
       
	    $('#formId').ajaxSubmit({
      
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                	alert("注册成功！");
                    window.location.href="${baseURL}/jsp/web/login.jsp"
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("注册出错，请稍后再试!"); 
            }
        });
	    }
 }
 function checkUserName(){
	 var username=$("#username").val();
	  if(username==""){
		   $("#tusername").text("用户名不能为空！");
		   return;
	   }
	   $.ajax({  
            type:"post",
            url:'${baseURL}/index/indexAction!checkUserName.action?username='+username,
            dataType:'json',
            success:function(result) {
            if ("0"==result.code) {
             if(result.desc=="true"){
            	 $("#tusername").text("用户名已存在！"); 
             }else{
            	   $("#tusername").text("");
             }
                
            } else {
            alert(result.desc);
            }
            },
             error:function(){
            alert('查询失败，请稍候再试');  
            } 
            });  
 }
 function isChn(str){ 

       var reg = /^[\u4E00-\u9FA5]+$/; 

       if(!reg.test(str)){ 

        return false; 

       } 
       return true;

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
 
</script>
</body>
</html>
