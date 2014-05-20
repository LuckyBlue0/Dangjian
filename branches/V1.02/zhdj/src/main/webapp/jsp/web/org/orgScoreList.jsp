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
		<script type="text/javascript" src="../js/plug.js">
</script>
		<script type="text/javascript" src="../js/fun.js">
</script>
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>

		<link type="text/css" rel="stylesheet" href="../css/l_style.css" />
		<script type="text/javascript" src="../js/tab.js">
</script>
		<script type="text/javascript" src="../js/forePager.js">
</script>
		<%@taglib prefix="s" uri="/struts-tags"%>
		<script type="text/javascript">
$(function() {
	$('.tabWraps').tab( {
		'tab_head' : '.tab_hds',
		'tab_body' : '.tab_bds'
	})
});
</script>
		<!--[if IE 6]>
    <script src="js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>
        DD_belatedPNG.fix('img,#cxzy_right li,.leftnav li a,.btngreen2,.tab_hds div,.logo');
    </script>
    <![endif]-->
		<title>文化家园</title>
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
								<a
									href="${baseURL}/jsp/web/org/orgIndex.jsp?orgId=${param.orgId}">支部首页</a>&nbsp;>&nbsp;
								<span>积分排行</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper clearfix">
				<div class="left_bar">
					<div class="pinfo clearfix pt15 pl15" datasrc="${baseURL}/orgIndex/orgIndexAction!getOrgScoreInfo.action?orgId=${param.orgId}">
						<img src="${baseURL}/@{orgPo.imgPath}" width="80" height="65" alt="" class="fl" />
						<div class="fl ml10">
							<p name="orgScore.organizationName">
							</p>
							<p>
								积分：<font name="orgScore.accumulativeScore"></font>
							</p>
							<p>
								总排名：<font name="orgScore.ranking"></font>
							</p>
						</div>
					</div>
					<div class="col2_box col2_box2">
						<h3>
							组织加分公示
						</h3>
						<div class="colText">
							<ul class="p10">
								<li>
									<a href="#">新华社：习近平考察河北 住招待所房间仅16平米</a>
								</li>
								<li>
									<a href="#">新华社：习近平考察河北 住招待所房间仅16平米</a>
								</li>
								<li>
									<a href="#">新华社：习近平考察河北 住招待所房间仅16平米</a>
								</li>
								<li>
									<a href="#">新华社：习近平考察河北 住招待所房间仅16平米</a>
								</li>
								<li>
									<a href="#">新华社：习近平考察河北 住招待所房间仅16平米</a>
								</li>
							</ul>
							<p class="tr mr20">
								<a href="#" class="more  ">更多>></a>
							</p>
						</div>
					</div>
				</div>
				<div class="right_bar">
					<div class="form_box">
						<div class="searchbox" style="background-color: #fff;">
							<span class="fred fb">党组</span>
							<span class="ml10">晋位升级榜</span>
							<select name="" id="" class="w100">
								<option value="1">
									全部
								</option>
							</select>
							<input type="text" class="inputstyle w230 ml25"
								placeholder="输入发起人，标题关键字" />
							<input type="button" value="搜索" class="redBtn" />
						</div>
						<div class="clearfix pt30">
							<div class="tabWraps">
								<div class="tab_bds"
									datasrc="${baseURL}/orgIndex/orgIndexAction!ajaxScoreRankSearch.action">
									<div class="table_w">
										<table class="tb1" width="100%">
											<thead>
												<tr>
													<td width="15%">
														名次
													</td>
													<td width="30%">
														组织名称
													</td>
													<td>
														星级
													</td>
													<td>
														积分
													</td>
												</tr>
											</thead>
											<tbody>
												<tr name="pageData">
													<td name="ranking">
													</td>
													<td name="organizationName">
													</td>
													<td name="leaveDesc">
													</td>
													<td name="accumulativeScore">
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="pageWrap mt20" showOn="@{totalRows}>0"
										id="pagerBar">
									</div>
									<input type="hidden" id="totalRows" value="@{totalRows}" />
									<input type="hidden" id="maxPage" value="@{maxPage}" />
									<input type="hidden" id="currPage" value="@{currPage}" />
								</div>
								<div class="tab_bds" style="display: none">
									<div class="table_w">
										<table class="tb1" width="100%">
											<thead>
												<tr>
													<td width="15%">
														名次
													</td>
													<td width="30%">
														组织名称
													</td>
													<td>
														星级
													</td>
													<td>
														积分
													</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>

													</td>
													<td>

													</td>
													<td>

													</td>
													<td>

													</td>
												</tr>

											</tbody>
										</table>
									</div>

								</div>
								<div class="tab_bds" style="display: none;">
									<div class="table_w">
										<table class="tb1" width="100%">
											<thead>
												<tr>
													<td width="15%">
														名次
													</td>
													<td width="30%">
														组织名称
													</td>
													<td>
														星级
													</td>
													<td>
														积分
													</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>

													</td>
													<td>

													</td>
													<td>

													</td>
													<td>

													</td>
												</tr>
											</tbody>
										</table>
									</div>

								</div>
							</div>
							<div class="tab_hds">
								<div class="tab1 on">
									<span>全部</span>
								</div>
								<div class="tab2">
									<span>两新组织</span>
								</div>
								<div class="tab3">
									<span>事业单位</span>
								</div>
							</div>
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
$(document).ready(function() {
	initPageBar();//生成前台分页工具
		if ("1" == $("#val").val()) {
			$("#name").text("探索创新");
		}
	});
function doSearch($pageIndex) {
	var page = $pageIndex;
	$("#list").removeData("pageData");
	$('#list').attr(
			"datasrc",
			"${baseURL}/orgIndex/orgIndexAction!ajaxScoreRankSearch.action?page="
					+ page);
	_redraw("list");
	initPageBar();
}
</script>
	</body>
</html>
