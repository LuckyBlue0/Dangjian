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
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script type="text/javascript" src="js/plug.js"></script>
<script type="text/javascript" src="js/fun.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>

<title>智慧党建平台</title>
</head>

<body>
<!--头部-->
	<s:include value="/jsp/web/common/head.jsp"></s:include>
<!--头部-->
<!--正文-->
<div id="main" datasrc="">
    <div class="loginWrap">
        <div class="wrapper">
            <div class="loginBox clearfix">
                <div class="fl">
                    <div id="position">
                    <input type="hidden" id="val" value="${param.type}">
                        <span>当前位置：</span>
                        <a href="${baseURL}/jsp/web/index.jsp">首页</a>&nbsp;>&nbsp;
                        <span id="name"></span>
                    </div>
                </div>

                <div class="fr">
                    <div class="selectBox">
                        <div class="selectValue">
                            <label for="">组织导航</label>
                            <i></i>
                        </div>
                        <div class="list">
                            <ul>
                                <li><a href="#">政党组织</a></li>
                                <li><a href="#">区党委会</a></li>
                                <li><a href="#">政党组织</a></li>
                                <li><a href="#">区党委会</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
             </div>
        </div>
    </div>
    <div class="wrapper">
        <div class="article pt40">
            <h1 > 该内容正在建设中，敬请期待</h1>
           
        </div>
    </div>
</div>
<!--正文 end-->

<!--页脚-->
<s:include value="/jsp/web/common/foot.jsp"></s:include>
<!--页脚 end-->
<script type="text/javascript">
$(document).ready(function(){
	if("1"==$("#val").val()){
		$("#name").html("支部在线");
	}
	if("2"==$("#val").val()){
		$("#name").html("互动交流");
	}
	if("3"==$("#val").val()){
		$("#name").html("党员文库");
	}
	if("4"==$("#val").val()){
		$("#name").html("智慧地图");
	}
	if("5"==$("#val").val()){
		$("#name").html("服务指引");
	}
	if("6"==$("#val").val()){
		$("#name").html("组织导航");
	}
	if("7"==$("#val").val()){
		$("#name").html("个人空间");
	}
	if("8"==$("#val").val()){
		$("#name").html("组织空间");
	}
	if("9"==$("#val").val()){
		$("#name").html("收藏"); 
	}
	
	 });


</script>
</body>
</html>
