<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="cn.com.do1.common.util.web.WebUtil"%>
<%@include file="/jsp/web/common/dqdp_common.jsp"%>

<div id="head">
        <div class="topHead">
            <div class="wrapper">
                <div class="welcome clearfix">
                    <p class="fl">
                        <span>${sessionScope.uservo.name}&nbsp;您好，欢迎来到智慧党建平台！</span>
                        <span>[<a href="javascript:doLoginOut();">注销</a>]</span>
                    </p>
                    <p class="fr">
                        <a href="${baseURL}/jsp/web/index.jsp">首页</a><i>|</i><a href="${baseURL}/jsp/web/membercenter/main.jsp">个人主页</a><i>|</i><a href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${sessionScope.uservo.organizationId}">组织主页</a><i>|</i>
                        <a href="javascript:void(0)" onclick="window.external.AddFavorite(document.location.href,document.title)">收藏本站</a>
                    </p>
                </div>
            </div>
        </div>


	</div>
	<script type="text/javascript">
	function doLoginOut(){
		if(confirm("您确认退出登录吗！")){
			$.ajax({
            url:"${baseURL}/index/indexAction!loginOut.action",
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
			        window.location.href="${baseURL}/jsp/web/login.jsp"; 
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
	