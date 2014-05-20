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
		<%@taglib prefix="s" uri="/struts-tags"%>
		<title>支部主页</title>
	</head>

	<body>
		<s:include value="/jsp/web/common/orgHead.jsp"></s:include>
		<!--正文-->
		<div id="main">
			<div class="loginWrap">
				<div class="wrapper">
					<div class="loginBox clearfix">
						<div class="fl" style="display: none;">
							<span> <label for="">
									用户名：
								</label> <input type="text" class="inputstyle w120" /> </span>

							<span> <label for="">
									密码：
								</label> <input type="text" class="inputstyle w120" /> </span>

							<input type="button" class="btnLogin" value="登 录" />
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
											<a href="${baseURL}/jsp/web/org/tip.jsp?type=6">政党组织</a>
										</li>
										<li>
											<a href="${baseURL}/jsp/web/org/tip.jsp?type=6">区党委会</a>
										</li>
										<li>
											<a href="${baseURL}/jsp/web/org/tip.jsp?type=6">政党组织</a>
										</li>
										<li>
											<a href="${baseURL}/jsp/web/org/tip.jsp?type=6">区党委会</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper">
				<div class="col1 clearfix mt20">
					<div class="slide fl"
						datasrc="${baseURL}/orgIndex/orgIndexAction!ajaxSearchTopPic.action?orgId=${param.orgId}">
						<div class="slideDiv" style="width: 380px; height: 300px">
							<li name="picList">
								<a href="${baseURL}/jsp/web/org/orgHotNewsDetail.jsp?orgId=${param.orgId}&id=@{newsInfoId}&newsTypeId=1">
								<img src="${baseURL}/@{imgPath}" alt="" text="@{title}"
									width="380px" height="300px" style="float: left;" /></a>
							</li>
						</div>
						<div class="picText">
							<p class="ml10">
							</p>
						</div>
					</div>

					<div class="gzzt box fl ml15"
						datasrc="${baseURL}/orgIndex/orgIndexAction!orgAnnouncementsIndex.action?orgId=${param.orgId}">
						<h3 class="ml10">
							通知公告
						</h3>
						<div style="height: 235px">
							<ul class="p20" style="padding-bottom: 10px; height: 10px"
								name="newsList">
								<li>
									<a
										href="${baseURL}/jsp/web/org/orgNewsDetail.jsp?orgId=${param.orgId}&id=@{newsInfoId}&newsTypeId=1"><span
										name="title"></span> </a>
								</li>
							</ul>
						</div>
						<p class="tr mr20">
							<a
								href="${baseURL}/jsp/web/org/orgNewsList.jsp?orgId=${param.orgId}"
								class="more">更多>></a>
						</p>
					</div>

					<div class="instr fl ml15"
						datasrc="${baseURL}/orgIndex/orgIndexAction!orgInfo.action?orgId=${param.orgId}">
						<h3>
							支部简介
						</h3>
						<div class="pl15 pr15">
							<div class="tc">
								<img name="organization.imgPath" src="${baseURL}/@{imgPath}"
									alt="" width="200px" height="130px" />
							</div>
							<p class="t2 mt10" name="organization.remark">

							</p>
						</div>
					</div>

				</div>
				<div class="col2 clearfix mt10">
					<div class="col2_box fl">
						<h3>
							支部动态
						</h3>
						<div style="height: 175px" class="colText"
							datasrc="${baseURL}/orgIndex/orgIndexAction!orgDynamicIndex.action?orgId=${param.orgId}">
							<div style="height: 150px">
								<ul class="p10" name="dynamicList" style="height: 5px">
									<li>
										<a
											href="${baseURL}/jsp/web/org/orgDynamicDetail.jsp?orgId=${param.orgId}&id=@{id}"><span
											name="title"></span> </a>
									</li>
								</ul>
							</div>
							<p class="tr mr20">
								<a
									href="${baseURL}/jsp/web/org/orgDynamicList.jsp?orgId=${param.orgId}"
									class="more  ">更多>></a>
							</p>
						</div>
					</div>

					<div class="col2_box fl ml10">
						<h3>
							志愿活动
						</h3>

						<div style="height: 175px" class="colText"
							datasrc="${baseURL}/orgIndex/orgIndexAction!orgVolunteerIndex.action?orgId=${param.orgId}">
							<div style="height: 150px">
								<ul class="p10" name="volunteerList" style="height: 5px">
									<li>
										<a
											href="${baseURL}/jsp/web/org/orgVolunteerDetail.jsp?orgId=${param.orgId}&id=@{id}"><span
											name="title"></span> </a>
									</li>
								</ul>
							</div>
							<p class="tr mr20">
								<a
									href="${baseURL}/jsp/web/org/orgVolunteerList.jsp?orgId=${param.orgId}"
									class="more  ">更多>></a>
							</p>
						</div>
					</div>


					<div class="col3_box fl ml10" id="rulebox">
						<h3 class="clearfix">
							<span class="fl">支部排行榜</span>
							<a class="fr rule" href="#">积分规则</a>
						</h3>
						<div class="colText" style="height: 175px"
							datasrc="${baseURL}/orgIndex/orgIndexAction!orgRankingIndex.action?orgId=${param.orgId}">
							<div style="height: 150px">
								<ul name="rankingList" class="p10 orderdiv" style="height: 5px">
									<li>
										<span name="ranking"></span>
										<span class="zhibu" name="organizationName"></span><span
											name="accumulativeScore"></span>
									</li>
								</ul>
							</div>
							<p class="tr mr20">
								<a
									href="${baseURL}/jsp/web/org/orgScoreList.jsp?orgId=${param.orgId}"
									class="more  ">查看更多排名>></a>
							</p>
						</div>
					</div>

				</div>


				<div class="picScroll p10 mt10">
					<h3>
						党员风采
					</h3>
					<div class="scrolDiv mt10">
						<span class="prev"></span>
						<span class="next"></span>
						<div class="scrollBox">
							<ul class="clearfix">
								<li>
									<a href="#"> <img src="../images/pic1.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic2.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic3.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic4.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic5.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic1.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>


								<li>
									<a href="#"> <img src="../images/pic3.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic4.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic5.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>

								<li>
									<a href="#"> <img src="../images/pic1.png" alt="" />
										<p>
											成都义工走上街头
										</p> </a>
								</li>


							</ul>
						</div>
					</div>
				</div>

				<div id="cxzy" class="clearfix mt10">
					<div id="cxzy_left">
						<div class="cx_leftbox fl">
							创先争优
						</div>
						<div class="cx_rightbox fl">
							<ul class="clearfix p10">
								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr1.png" alt="" /> </span>
										<p>
											我要入党
										</p> </a>
								</li>

								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr2.png" alt="" /> </span>
										<p>
											找组织
										</p> </a>
								</li>

								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr3.png" alt="" /> </span>
										<p>
											组织关系转移
										</p> </a>
								</li>

								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr4.png" alt="" /> </span>
										<p>
											书记之窗
										</p> </a>
								</li>

								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr5.png" alt="" /> </span>
										<p>
											党代表直通车
										</p> </a>
								</li>

								<li>
									<a href="#"> <span class="imgwrap2"> <img
												src="../images/instr6.png" alt="" /> </span>
										<p>
											办事指南
										</p> </a>
								</li>
							</ul>
						</div>
					</div>
					<div id="cxzy_right" class="fl ml10">
						<ul class="p10">
							<li>
								<a href="#">老干部工作</a>
							</li>
							<li>
								<a href="#">党代表直通车</a>
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
	var isLogin = "${sessionScope.isMemberLogin}";
	if (isLogin != "true") {
		alert("请先登录！");
		window.location.href = "${baseURL}/jsp/web/login.jsp";
	}
});
</script>
	</body>
</html>
