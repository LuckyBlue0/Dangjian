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
<script type="text/javascript" src="../js/plug.js"></script>
<script type="text/javascript" src="../js/fun.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>

<title>智慧党建平台</title>
</head>

<body>
<!--头部-->
	<s:include value="/jsp/web/common/orgHead.jsp"></s:include>
<!--头部-->
<!--正文-->
<div id="main" datasrc="${baseURL}/orgIndex/orgIndexAction!searchVolunteerDetail.action?id=${param.id}">
    <div class="loginWrap">
        <div class="wrapper">
            <div class="loginBox clearfix">
                <div class="fl">
                    <div id="position">
                        <span>当前位置：</span>
                        <a href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${param.orgId}">支部首页</a>&nbsp;>&nbsp;
                        <a href="${baseURL}/jsp/web/org/orgVolunteerList.jsp?orgId=${param.orgId}">志愿活动</a>&nbsp;>&nbsp;<span>活动详情</span>
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
            <h1 name="volunteer.title"></h1>
            <div class="tc art_detail mt5">
                <span class="mr20">发布时间：<em name="volunteer.createTime"></em></span>
                <span>发布组织：<em name="volunteer.organizationName"></em></span>
            </div>
            <div class="article-bd pt20">
          
                <p class="t2">@{volunteer.content}</p>
                <div class="artlist mt30">
                    <ul>
                        <li>
                            <span>上一篇：</span>
                            <input type="hidden" id="pId" name="partalVolunteer.id"/>
                            <a id="pUrl" href="${baseURL}/jsp/web/org/orgVolunteerDetail.jsp?orgId=${param.orgId}&id=@{partalVolunteer.id}"><span name="partalVolunteer.title"></span></a>
                        </li>
                        <li>
                            <span>下一篇：</span>
                            <input type="hidden" id="nId" name="nextVolunteer.id"/>
                            <a id="nUrl" href="${baseURL}/jsp/web/org/orgVolunteerDetail.jsp?orgId=${param.orgId}&id=@{nextVolunteer.id}"><span name="nextVolunteer.title"></span></a>
                        </li>
                    </ul>
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
 $(document).ready(function(){
	 var pId=$("#pId").val();
	 var nId=$("#nId").val()
     if(pId==''){
    	 $("#pUrl").attr("href","#");
     }
	 if(nId==''){
		 $("#nUrl").attr("href","#");
	 }
    });

</script>
</body>
</html>
