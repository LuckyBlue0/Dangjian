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
<div id="main" datasrc="${baseURL}/index/indexAction!searchHotNewsDetail.action?id=${param.id}">
    <div class="loginWrap">
        <div class="wrapper">
            <div class="loginBox clearfix">
                <div class="fl">
                    <div id="position">
                        <span>当前位置：</span>
                        <a href="${baseURL}/jsp/web/index.jsp">首页</a>&nbsp;>&nbsp;
                        <span>热点新闻</span></a>&nbsp;>&nbsp;<span>详情</span>
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
            <h1 name="news.title"></h1>
            <div class="tc art_detail mt5">
                <span class="mr20">发布时间：<em name="news.pushTime"></em></span>
                <span>发布人：<em name="news.createUser"></em></span>
            </div>
            <div class="article-bd pt20">
          
                <p class="t2">@{news.content}</p>
                
            </div>
        </div>
    </div>
</div>
<!--正文 end-->

<!--页脚-->
<s:include value="/jsp/web/common/foot.jsp"></s:include>
<!--页脚 end-->
<script type="text/javascript">


</script>
</body>
</html>
