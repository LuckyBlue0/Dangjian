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
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main" style="display: none">
                <div class="pinfom" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScore.action">
                    <p><em class="fred fb" name="scorevo.username"></em>  欢迎您！</p>
                    <p showOn="@{scorevo.lastdayScore}==''">您昨天没有获得积分，要继续加油啦！</p>                    
                    <p showOn="@{scorevo.lastdayScore}!=''">您昨天获得 <span class="fred" name="scorevo.lastdayScore"></span> 积分，累计 <span class="fred" name="scorevo.tatalScore"></span> 积分，当前排名第 <span class="fred" name="scorevo.sort"></span> 。继续加油哦！</p>
                </div>
                <div class="pcol1 mt10">
                    <div class="pcoltop clearfix">
                        <span>
                            <i class="">&nbsp;</i>
                            <em class="vm">消息提醒</em>
                        </span>
                    </div>
                    <div class="pcolbd p20 f666" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalMeeting.action">
                        <p>
	                        <span>三会一课：</span>
	                        <a href="${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp">
	                        	<span>我的党课（<em class="fred" name="meetingvo.partyMeetingNum"></em>）</span></a>
	                        <a href="${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp?tabType=1">
	                        	<span>我的会议（<em class="fred" name="meetingvo.meetingNum"></em>）</span></a>
                        </p>
                        <p>
	                        <span>支部活动：</span>
	                        <a href="${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp?tabType=3">
	                        	<span>我的活动（<em class="fred" name="meetingvo.activityNum"></em>）</span></a>
	                        <a href="${baseURL}/jsp/web/membercenter/userReportList.jsp">
	                        	<span>思想汇报（<em class="fred" name="meetingvo.reportNum"></em>）</span></a>
                        </p>
                    </div>
                </div>

                <div class="pcol1">
                    <div class="pcoltop clearfix">
                        <span>
                            <i>&nbsp;</i>
                            <em class="vm">热门功能</em>
                        </span>
                    </div>
                    <div class="pcolbd p20 f666">
                        <ul class="picons clearfix mt20">
                            <li>
                                <a href="${baseURL}/jsp/web/membercenter/tip.jsp">
                                    <span class="imgwrap">
                                        <img src="../images/info_icon1.png" alt=""/>
                                    </span>
                                    <p class="mt10">留影墙</p>
                                </a>
                            </li>
                            <li>
                                <a href="${baseURL}/jsp/web/membercenter/tip.jsp">
                                    <span class="imgwrap">
                                        <img src="../images/info_icon2.png" alt=""/>
                                    </span>
                                    <p class="mt10">论坛</p>
                                </a>
                            </li>
                            <li>
                                <a href="${baseURL}/jsp/web/membercenter/tip.jsp">
                                    <span class="imgwrap">
                                        <img src="../images/info_icon3.png" alt=""/>
                                    </span>
                                    <p class="mt10">党员考试</p>
                                </a>
                            </li>
                            <li>
                                <a href="${baseURL}/jsp/web/membercenter/tip.jsp">
                                    <span class="imgwrap">
                                        <img src="../images/info_icon4.png" alt=""/>
                                    </span>
                                    <p class="mt10">组织生活</p>
                                </a>
                            </li>
                            <li>
                                <a href="${baseURL}/jsp/web/membercenter/tip.jsp">
                                    <span class="imgwrap">
                                        <img src="../images/info_icon5.png" alt=""/>
                                    </span>
                                    <p class="mt10">积分申请</p>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
  </div>              
  <script type="text/javascript">
$(document).ready(function() {
	var height = window.parent.$("#per_activity").height()-30;
	window.parent.$("#ifm").css("min-height",height);
	$("#main").css("display","");

});
  </script>
  </head>
  </html>