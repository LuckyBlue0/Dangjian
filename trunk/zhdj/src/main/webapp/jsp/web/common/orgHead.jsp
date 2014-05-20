<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/jsp/web/common/dqdp_common.jsp"%>
<!--头部-->
<div id="head">
	<div class="topHead">
		<div class="wrapper">
			<div class="welcome clearfix">
				<p class="fl">
					<span>${sessionScope.uservo.name}&nbsp;您好，欢迎来到智慧党建平台！</span>
					<span id="span1" style="display: none">[<a
						href="${baseURL}/jsp/web/login.jsp">登录</a>]</span>
					<span>[<a href="javascript:doLoginOut();">注销</a>]</span>
				</p>
				<p class="fr">
					<a href="${baseURL}/jsp/web/index.jsp">首页</a><i>|</i><a
						href="${baseURL}/jsp/web/membercenter/main.jsp">个人主页</a><i>|</i><a
						href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${sessionScope.uservo.organizationId}">组织主页</a><i>|</i>
					<a href="javascript:void(0)"
						onclick="window.external.AddFavorite(document.location.href,document.title)">收藏本站</a>
				</p>
			</div>
		</div>
	</div>
	<div class="adver org_ad2"
		datasrc="${baseURL}/orgIndex/orgIndexAction!orgInfo.action?orgId=${param.orgId}">
		<div class="wrapper pos_relative">
			<a href="#" class="org_logo"></a>
			<span class="curtitle"> <span
				name="organization.organizationName"></span>主页</span>
		</div>
	</div>

	<div id="nav">
		<div class="wrapper">
			<ul class="clearfix">
				<li style="border-left: 0;" id="nav1" class="on">
					<a
						href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${sessionScope.uservo.organizationId}">支部主页</a>
				</li>
				<li id="nav2">
					<a
						href="${baseURL}/jsp/web/org/orgNewsList.jsp?orgId=${param.orgId}">通知公告</a>

				</li>
				<li id="nav3">
					<a
						href="${baseURL}/jsp/web/org/orgDynamicList.jsp?orgId=${param.orgId}">支部动态</a>
				</li>
				<li>
					<a href="#">党员风采 </a>
				</li>
				<li>
					<a href="#">服务指引</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<!--头部-->

<script type="text/javascript">
$(document).ready(function() {
	var show = "${sessionScope.isMemberLogin}";
	if (show == "true") {
		$("#span1").css("display", "none");
	} else {
		$("#span1").css("display", "");
	}
});
function checkLogin(url) {
	var isLogin = "${sessionScope.isMemberLogin}";
	if (isLogin == "true") {
		window.location.href = url;
	} else {
		window.location.href = "${baseURL}/jsp/web/login.jsp";
	}
}
function doLoginOut() {
	if (confirm("您确认退出登录吗！")) {
		$.ajax( {
			url : "${baseURL}/index/indexAction!loginOut.action",
			dataType : 'json',
			success : function(result) {
				if ("0" == result.code) {
					window.location.href = "${baseURL}/jsp/web/login.jsp";
				} else {
					alert(result.desc);
				}
			},
			error : function() {
				alert("系统出错，请稍后再试!");
			}
		});
	}
}
</script>

