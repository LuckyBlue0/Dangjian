<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="cn.com.do1.common.util.web.WebUtil"%>
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>


<div id="activity_left" class="activity_left fl mr10">
           
                <div class="pinfo clearfix pt15" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScore.action">
                    <img src="${baseURL}/@{scorevo.imgPath}" alt="" width="75px" height="75px" class="fl"/>
                    <div class="fl ml10">
                        <p name="scorevo.username"></p>
                        <p>积分：<span name="scorevo.tatalScore"></span></p>
                        <p>总排名：<span name="scorevo.sort"></span></p>
                    </div>
                </div>
               <ul class="leftnav mt10">
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/userDetail.jsp')" class='i1'>基本资料</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/userScore.jsp')" class='i2'>我的积分</a></li>
                   <li name="uid" class="" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScore.action"><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp')" class='i3'>组织生活(<span class="fred fb" id="zjshTip" name="num"></span>)</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/userVolunteerActivity.jsp')" class='i4'>志愿活动</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/userReportList.jsp')" class='i5'>思想汇报</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/tip.jsp')" class='i6'>党员服务</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/tip.jsp')" class='i7'>党员考试</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/tip.jsp')"class='i8'>留影墙</a></li>
                   <li name="uid" class=""><a href="javascript:void(0)" onclick="changePage(this,'${baseURL}/jsp/web/membercenter/tip.jsp')" class='i9'>论坛</a></li>
               </ul>
           </div>
           
<script type="text/javascript">
  function changePage($this,url){
	  $("#ifm").attr("src",url);
	  $("li[name='uid']").each(function (){$(this).removeClass("on");});
	  $($this).parent().attr("class","on");
	  var height = $("#ifm").height();
	  var newHeight=$("#activity_left").height();
	  if(height<newHeight){
		  height=height;
	  }else{
		   height=newHeight;
	  }
	$("#ifm").css("min-height",height);

  }
</script>