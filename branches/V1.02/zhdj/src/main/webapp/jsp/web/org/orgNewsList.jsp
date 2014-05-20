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
		<link type="text/css" rel="stylesheet" href="../css/base.css" />
		<link type="text/css" rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../js/jquery-1.7.1.min.js">
</script>
		<script type="text/javascript"
			src="${baseURL}/js/do1/common/common.js">
</script>
		<script type="text/javascript" src="../js/forePager.js">
</script>

		<%@taglib prefix="s" uri="/struts-tags"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<title>智慧党建平台</title>
	</head>

	<body>

		<!--头部-->
		<s:include value="/jsp/web/common/orgHead.jsp"></s:include>
		<!--头部-->
		<!--正文-->
		<div id="main">
			<div class="loginWrap">
				<div class="wrapper">
					<div class="loginBox clearfix">
						<div class="fl">
							<div id="position">
								<span>当前位置：</span>
								<a href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${param.orgId}">支部首页</a>&nbsp;>&nbsp;
								<span>通知公告</span>
							</div>
						</div>

						<div class="fr">
							<div class="selectBox">
								<div class="selectValue">
									<label for="">
										组织导航
									</label>
									<i></i>
								</div>
								<div class="list">
									<ul>
										<li>
											<a href="#">政党组织</a>
										</li>
										<li>
											<a href="#">区党委会</a>
										</li>
										<li>
											<a href="#">政党组织</a>
										</li>
										<li>
											<a href="#">区党委会</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper clearfix mt20" id="list"
				datasrc="${baseURL}/orgIndex/orgIndexAction!ajaxNewsSearch.action?searchValue.newsInfoType=1&searchValue.organizationId=${param.orgId}&searchValue.status=1">
				<div class="list-left fl">
					<div class="notice-top">
						<span name="newsType"></span>
					</div>
					<div class="notice-bd mt20">
						<ul class="ml15" name="pageData">
							<li class="clearfix">
								<p>
									<a
										href="${baseURL}/jsp/web/org/orgNewsDetail.jsp?orgId=${param.orgId}&id=@{newsInfoId}">@{title}</a>
								</p>
								<span name="pushTime"></span>
							</li>
						</ul>
					</div>
					<div class="pageWrap mt20" showOn="@{totalRows}>0" id="pagerBar">
						<input type="hidden" id="totalRows" value="@{totalRows}" />
						<input type="hidden" id="maxPage" value="@{maxPage}" />
						<input type="hidden" id="currPage" value="@{currPage}" />
					</div>

				</div>
				<div class="left-right fr">
					<div class="friendLink">
						<h3>
							友情链接
						</h3>
						<ul class="pt20">
							<li>
								<a href="#"><img src="../images/list_ad1.png" alt="" /> </a>
							</li>
							<li>
								<a href="#"><img src="../images/list_ad2.png" alt="" /> </a>
							</li>
							<li>
								<a href="#"><img src="../images/list_ad3.png" alt="" /> </a>
							</li>
							<li>
								<a href="#"><img src="../images/list_ad4.png" alt="" /> </a>
							</li>
							<li>
								<a href="#"><img src="../images/list_ad5.png" alt="" /> </a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--正文 end-->
		<!--页脚-->
		<s:include value="/jsp/web/common/foot.jsp"></s:include>
		<!--页脚 end-->
		<script type="text/javascript">
$(document).ready(function() {
	$("#nav1").attr("class","");
	$("#nav2").attr("class","on");
	$("#nav3").attr("class","");
	initPageBar();//生成前台分页工具
		if ("1" == $("#val").val()) {
			$("#name").text("探索创新");
		}
	});
function doSearch($pageIndex) {

	var page = $pageIndex;
	$("#list").removeData("pageData");
	$('#list')
			.attr(
					"datasrc",
					"${baseURL}/orgIndex/orgIndexAction!ajaxNewsSearch.action?searchValue.newsInfoType=1&searchValue.organizationId=${param.orgId}&searchValue.status=1&page="
							+ page);
	_redraw("list");
	initPageBar();
}
</script>
	</body>
</html>
