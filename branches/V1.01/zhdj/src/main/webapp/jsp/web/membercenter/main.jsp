<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<script type="text/javascript" src="../js/tab.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>

<title>智慧党建平台</title>
</head>

<body>
<!--头部-->
<jsp:include page="/jsp/web/membercenter/head.jsp"></jsp:include>
<!--头部-->
<!--正文-->
<div id="main">

    <div class="wrapper">
    <a href="${baseURL}/jsp/web/membercenter/main.jsp">
        <img src="../images/per_activity_top.png" alt=""/></a>
        <div id="per_activity" class="clearfix">
                        
                        
           <!-- 载入左边菜单 -->             
           <jsp:include page="/jsp/web/membercenter/left.jsp"></jsp:include>
        
          
                    
            <div class="activity_right fl mt10 pb20">
              <iframe id="ifm" align="center" name="mainFrame" src="${baseURL}/jsp/web/membercenter/info.jsp" style="margin-left: 5px" width="100%" scrolling="no" frameborder="0" height="100%"></iframe>
                

            </div>
        </div>
    </div>
</div>

<!--正文 end-->

<!--页脚-->
<s:include value="/jsp/web/common/foot.jsp"></s:include>
<!--页脚 end-->
<script type="text/javascript">
$(document).ready(function(){
      var isLogin="${sessionScope.isMemberLogin}";
	  if(isLogin=="true"){
	  }else{
		  alert("请先登录！"); 
		  window.location.href="${baseURL}/jsp/web/login.jsp";
	  }
    });
</script>
</body>
</html>
