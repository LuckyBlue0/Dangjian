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
<script type="text/javascript" src="${baseURL}/jsp/web/js/forePager.js"></script>
<!--正文-->
<div id="main" style="display: none">

            <div class="activity_right fl mt10 pb20" dataSrc="${baseURL}/meeting/dangkeAction!ajaxView.action?id=${param.id}">
                <div class="act_top">党课详情</div>
                <div class="act_bd">
                    <div class="act_tabWrap mt10">
                        <div class="act_tab_bd" >
                            <table class="mycouse">
                                        <input type="hidden" name="partyLectureVO.id" id="meetingId"/>
                            
                                <thead>
                                <tr>
                                    <td colspan="4" name="partyLectureVO.title"></td>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th width="20%"  style="text-align: right;" >党课开始时间</th>
                                        <td width="30%" name="partyLectureVO.startTime"></td>
                                        <th style="text-align: center;"  width="20%">党课结束时间</th>
                                        <td width="30%" name="partyLectureVO.endTime"></td>
                                    </tr>
                                    <tr>
                                        <th  style="text-align: right;">发布组织</th>
                                        <td name="partyLectureVO.organizationName"></td>
                                        <th style="text-align: center;">党课类型</th>
                                        <td name="partyLectureVO._type_desc" ></td>
                                    </tr>
                                    <tr>
                                        <th  style="text-align: right;">党课地点</th>
                                        <td colspan="3" name="partyLectureVO.address"></td>
                                    </tr>
                                    <tr>
                                        <th  style="text-align: right;">参与人员</th>
                                        <td colspan="3"><span id="user_list"></span></td>
                                    </tr>
                                    <tr>
                                        <th  style="text-align: right;">党课详情</th>
                                        <td colspan="3" name="partyLectureVO.content"  width="550px" charLength="500"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="mt10 tc">
                                <input type="button" value="我要请假" showOn="@{partyLectureVO.status}==1 && @{partyLectureVO.whetherEnd}!=1" onclick="updateStatus('@{partyLectureVO.id}','1')" class="btnyellow mr30"/>
                                <!-- <input type="button" value="签到" showOn="@{partyLectureVO.status}==1" onclick="updateStatus('@{partyLectureVO.id}','2')" class="btnstore"/> -->
                                <input type="button" value="返回"  onclick="history.back();" class="btnyellow mr30"/>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
</div>
<!--正文 end-->
<script type="text/javascript">
 $(document).ready(function(){	  
	   var height = window.parent.$("#per_activity").height();
	window.parent.$("#ifm").css("min-height",height);
	$("#main").css("display","");
	   listUserByMeetingId();
  });
function listUserByMeetingId() {
	
	    $.ajax({
	        url: "${baseURL}/meeting/dangkeAction!ajaxUser.action",
	        data: {"id": $("#meetingId").val()},
	        type: "post",
	        dataType: "json",
	        success: function (result) {
	            if ("0" == result.code) {
	                var userList = result.data.userList;
	                var users="";
	                for (var i = 0; i < userList.length; i++) {
	                    users+=userList[i].name+" ";
	                    $("#user_list").html(users);
	                }
	            } else {
	                alert(result.desc);
	            }
	        }
	     });
	}
function updateStatus(id,type){
	 if(type=="1"){
	 $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!updateForLeaveStatus.action",
            dataType:'json',
            data:{"id":id,"type":type},
            success:function (result) {
                if ("0" == result.code) {
                	alert("操作成功！");
                	window.location.href="${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp";
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");  
            }
        });
	 }

	 if(type=="2"){
	 $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!updateSingInStatus.action",
            dataType:'json',
            data:{"id":id},
            success:function (result) {
                if ("0" == result.code) {
                	alert("操作成功！");
                	window.location.href="${baseURL}/jsp/web/membercenter/userPartyMeeting.jsp";
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
  </head>
  </html>