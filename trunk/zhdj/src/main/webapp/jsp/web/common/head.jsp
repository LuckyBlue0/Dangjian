<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--头部-->
	<div id="head">
	<input type="hidden" id="login" value="${sessionScope.isMemberLogin}"/>
        <div class="topHead">
            <div class="wrapper">
                <div class="welcome clearfix">
                    <p class="fl">
                        <span>${sessionScope.uservo.name}&nbsp;您好，欢迎来到智慧党建平台！</span>
                        <c:if test="${empty sessionScope.uservo}">
                        <span id="span1" style="display: none">[<a href="${baseURL}/jsp/web/login.jsp">登录</a>]</span>
                        <span>[<a href="${baseURL}/jsp/web/registe.jsp">注册</a>]</span>
                        </c:if>
                        <c:if test="${not empty sessionScope.uservo}">
                        <span>[<a href="javascript:doLoginOut();">注销</a>]</span>
                        </c:if>
                    </p>
                    <p class="fr">
                        <a href="javascript:void(0);" onclick="checkLogin('${baseURL}/jsp/web/membercenter/main.jsp','${sessionScope.uservo.userType}')">个人主页</a><i>|</i><a href="javascript:void(0)" onclick="checkLogin('${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${sessionScope.uservo.organizationId}','${sessionScope.uservo.userType}')">组织主页</a><i>|</i>
                        <a href="javascript:void(0)" onclick="window.external.AddFavorite(document.location.href,document.title)">收藏本站</a>

                    </p>
                </div>
            </div>
        </div>
    <div class="adver ad2">
        <div class="wrapper pos_relative">
            <a href="#" class="logo"></a>
            
        </div>
    </div>
        <div id="nav">
            <div class="wrapper">
                <ul class="clearfix">
                    <li style="border-left: 0;" class="on" onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/index.jsp">首页</a></li>
                    <li  onmouseover="showDiv(this);" onmouseout="hideDiv(this);">
                        <a href="#">探索创新</a>

                        <div class="sub" id="sub" style="display: none;">
                            <div class="subitem" id="sub1" datasrc=""><a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}&type=1">两新党建</a></div>
                            <div class="subitem" id="sub2"><a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}&type=1">社区党建</a></div>
                            <div class="subitem" id="sub3"><a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}&type=1">农村党建</a></div> 
                            <div class="subitem" id="sub4"><a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}&type=1">机关党建</a></div> 
                            <div class="subitem" id="sub5"><a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}&type=1">国企党建</a></div> 
                        </div>
                    </li>
                    <li onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/tip.jsp?type=1">支部在线</a>
                        <div class="sub" style="display: none;">
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=1">三会一课</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=1">支部活动</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=1">志愿活动</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=1">党内关爱</a></div>  
                        </div>
                    </li>
                    <li onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/tip.jsp?type=2">互动交流 </a>
                        <div class="sub" style="display: none;">
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=2">党员心声</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=2">党群互动</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=2">留影墙</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=2">回音壁</a></div>  
                        </div>
                    </li>
                    <li onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/tip.jsp?type=3">党员文库</a>
                        <div class="sub" style="display: none;">
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=3">党员视频</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=3">党史上的今天</a></div>
                        </div>
                    </li>
                    <li onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/tip.jsp?type=4">智慧地图</a></li>
                    <li style="border-right: 0;" onmouseover="showDiv(this);" onmouseout="hideDiv(this);"><a href="${baseURL}/jsp/web/tip.jsp?type=5">服务指引</a>
                        <div class="sub" style="display: none;">
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">党务指引</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">党务公文模版</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">办事指南</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">政策文件</a></div>  
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">党史之窗</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">关爱服务</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">党代表服务</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">流动党员找组织</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">志愿服务</a></div>
                            <div class="subitem" ><a href="${baseURL}/jsp/web/tip.jsp?type=5">经典好歌</a></div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
	</div>
<!--头部-->
<script type="text/javascript">
  $(document).ready(function(){
    	var show="${sessionScope.isMemberLogin}";
    	if(show=="true"){
    		$("#span1").css("display","none");
    	}else{
    		$("#span1").css("display","");
    	}
    });
  function showDiv($this){
	  $($this).attr("class","on");
	  $($this).find(".sub").css("display","");
	  if($($this).find(".sub").attr("id")=="sub"&&""==$("#sub1").attr("datasrc")){
	  $("#sub1").attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI("两新党建")));
	  $("#sub2").attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI("社区党建")));
	  $("#sub3").attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI("农村党建"))); 
	  $("#sub4").attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI("机关党建")));
	  $("#sub5").attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI("国企党建"))); 
	  _redraw("sub1");
	  _redraw("sub2");
	  _redraw("sub3");
	  _redraw("sub4");
	  _redraw("sub5");
	  }else{
	  }
  }
  function hideDiv($this){
	  $($this).attr("class","");
	   $($this).find(".sub").css("display","none");
  }
  function checkLogin(url,type){
	  
	  var isLogin="${sessionScope.isMemberLogin}";
	  if(isLogin=="true"){
		  if(type!="1"){
		  alert("您还不是党员，无法登录个人或组织主页！"); 
		  return;
	      }
		  window.location.href=url;
	  }else{
		  window.location.href="${baseURL}/jsp/web/login.jsp";
	  }
  }
  function doLoginOut(){
		if(confirm("您确认退出登录吗！")){
			$.ajax({
            url:"${baseURL}/index/indexAction!loginOut.action",
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
			        window.location.href="${baseURL}/jsp/web/index.jsp"; 
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");   
            }
        });
		}
	}
  </script>
