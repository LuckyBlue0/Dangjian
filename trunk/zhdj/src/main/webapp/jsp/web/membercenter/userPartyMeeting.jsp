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

<div id="main">



<div class="activity_right fl mt10 pb20">
    <div class="act_top">组织生活</div>
    <div class="act_bd">
        <div class="act_tabWrap mt10">
            <div class="act_tab_hd clearfix">
                <span class="on" onclick="changeDiv(2);" id="sp1">我的党课</span>
                <span onclick="changeDiv(1);" id="sp2">我的会议</span>
                <span onclick="changeDiv(3);" id="sp3">支部活动</span>
                <span onclick="changeDiv(4);" id="sp4">民主生活会</span>
            </div>
            <div class="act_tab_bd clearfix" id="inf" style="display: block" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalPartyMeetingList.action">
               <form action="" method="post" id="id_form_search">
                <div class="searchbox" >
                    <span>状态</span>
                    <select name="sid1" id="sid1" class="w100">
                        <option value="">全部</option>
                        <option value="1">进行中</option>
                        <option value="2">已办</option>
                        <option value="3">已结束</option>
                    </select>
                    <input type="text" id="keyWord1" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch(1)" class="redBtn"/>
                </div>
                </form>

                <table class="tb1 mt10" id="list" width="100%" >
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>发起人</td>
                        <td>发起时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/dangkeDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="carryOutStatus"></td>
                        <td>
                        <div class="op1">
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','1','2')" showOn="@{status}==1">请假</a>
                        <!-- a href="javascript:void(0);" class="fred ml15" onclick="updateStatus('@{id}','3','2')" showOn="@{status}==1">签到</a> -->
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','2','2')" showOn="@{status}==2&&@{forLeaveStatus}==1">取消请假</a>
                        <a href="${baseURL}/jsp/web/membercenter/dangkeDetail.jsp?id=@{id}" class="fgreen">查看</a>
                        </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="tabType" value="${param.tabType}" />
                <div class="pagewrap mt10" id="pagerBar">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>

            <div class="act_tab_bd clearfix" id="inf1" style="display: none" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalMeetingList.action">
                <div class="searchbox">
                    <span>状态</span>
                    <select name="sid2" id="sid2" class="w100">
                        <option value="0">全部</option>
                        <option value="1">进行中</option>
                        <option value="2">已办</option>
                        <option value="3">已结束</option>
                    </select>
                    <input type="text" id="keyWord2" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch1(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list1" width="100%">
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>发起人</td>
                        <td>发起时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/meetingDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="carryOutStatus"></td>
                         <td>
                         <div style="display: none" class="op2">
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','1','1')" showOn="@{status}==1">请假</a>
                        <!-- <a href="javascript:void(0);" class="fred ml15" onclick="updateStatus('@{id}','3','1')" showOn="@{status}==1">签到</a> -->
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','2','1')" showOn="@{status}==2&&@{forLeaveStatus}==1">取消请假</a>
                        <a href="${baseURL}/jsp/web/membercenter/meetingDetail.jsp?id=@{id}" class="fgreen">查看</a>
                        </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar1">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>
            <div class="act_tab_bd clearfix" id="inf2" style="display: none" datasrc="${baseURL}/membercenter/membercenterAction!searchBranchActivityListByUserid.action">
                <div class="searchbox">
                    <span>状态</span>
                    <select name="sid3" id="sid3" class="w100">
                        <option value="0">全部</option>
                        <option value="1">进行中</option>
                        <option value="2">已办</option>
                        <option value="3">已结束</option>
                    </select>
                    <input type="text" id="keyWord3" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch2(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list2" width="100%" >
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>发起人</td>
                        <td>发起时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/branchActivityDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="carryOutStatus"></td>
                         <td>
                         <div style="display: none" class="op3">
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','1','3')" showOn="@{status}==1">请假</a>
                        <!-- <a href="javascript:void(0);" class="fred ml15" onclick="updateStatus('@{id}','3','3')" showOn="@{status}==1">签到</a> -->
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','2','3')" showOn="@{status}==2&&@{forLeaveStatus}==1">取消请假</a>
                        <a href="${baseURL}/jsp/web/membercenter/branchActivityDetail.jsp?id=@{id}" class="fgreen">查看</a>
                        </div>
                         </td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar2">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>
            
            <div class="act_tab_bd clearfix" id="inf3" style="display: none" datasrc="${baseURL}/membercenter/membercenterAction!searchDemocrticLifeList.action">
                <div class="searchbox">
                    <span>状态</span>
                    <select name="sid4" id="sid4" class="w100">
                        <option value="0">全部</option>
                        <option value="1">进行中</option>
                        <option value="2">已办</option>
                        <option value="3">已结束</option>
                    </select>
                    <input type="text" id="keyWord4" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch3(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list4" width="100%">
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>发起人</td>
                        <td>发起时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/DemocrticLifeDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="carryOutStatus"></td>
                         <td>
                         <div class="op2">
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','1','4')" showOn="@{status}==1&&@{forLeaveStatus}==0">请假</a>
                        <!-- <a href="javascript:void(0);" class="fred ml15" onclick="updateStatus('@{id}','3','1')" showOn="@{status}==1">签到</a> -->
                        <a href="javascript:void(0);" class="fgreen" onclick="updateStatus('@{id}','2','4')" showOn="@{status}==1&&@{forLeaveStatus}==1">取消请假</a>
                        <a href="${baseURL}/jsp/web/membercenter/DemocrticLifeDetail.jsp?id=@{id}" class="fgreen">查看</a>
                        </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar3">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>
        </div>
    </div>
</div>

</div>
<script type="text/javascript">
 $(document).ready(function(){
	 	if($("#tabType").val() != ''){
	 		changeDiv($("#tabType").val());
	 	}
    	initPageBar();//生成前台分页工具
    	initPageBar1();//生成前台分页工具
        initPageBar2();//生成前台分页工具
        initPageBar3();//生成前台分页工具
        $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        $("div.op4").each(function() {
    		$(this).css("display","");
    	});
    });
 function doSearch($pageIndex){
	    var sid=$("#sid1").val();
	    var keyword=$("#keyWord1").val();
    	var page=$pageIndex;
    	$("#inf").removeData("pageData");
    	$('#inf').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchPersonalPartyMeetingList.action?page="+page+"&searchValue.sid="+sid+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf");
    	 $("#keyWord1").val(keyword)
    	 $("#sid1").val(sid);
    	 $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        initPageBar();
    }
  function doSearch1($pageIndex){
	    var sid=$("#sid2").val();
	    var keyword=$("#keyWord2").val();
    	var page=$pageIndex;
    	$("#inf1").removeData("pageData");
    	$('#inf1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchPersonalMeetingList.action?page="+page+"&searchValue.sid="+sid+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf1");
    	  $("#keyWord2").val(keyword)
    	 $("#sid2").val(sid);
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        initPageBar1();
    }
    function doSearch2($pageIndex){
	    var sid=$("#sid3").val();
	    var keyword=$("#keyWord3").val();
    	var page=$pageIndex;
    	$("#inf2").removeData("pageData");
    	$('#inf2').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchBranchActivityListByUserid.action?page="+page+"&searchValue.sid="+sid+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf2");
    	  $("#keyWord3").val(keyword)
    	 $("#sid3").val(sid);
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        initPageBar2();
    }
    function doSearch3($pageIndex){
	    var sid=$("#sid4").val();
	    var keyword=$("#keyWord4").val();
    	var page=$pageIndex;
    	$("#inf3").removeData("pageData");
    	$('#inf3').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchDemocrticLifeList.action?page="+page+"&searchValue.sid="+sid+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 	_redraw("inf3");
    	  	$("#keyWord4").val(keyword)
    	 	$("#sid4").val(sid);
        	$("div.op4").each(function() {
    			$(this).css("display","");
    	});
        initPageBar3();
    }
  function changeDiv(type){
	if("2"==type){
		doSearch(1);
		$("#inf").css("display","");
		$("#inf1").css("display","none");
		$("#inf2").css("display","none");
		$("#inf3").css("display","none");
	    $("#sp1").attr("class","on");
	    $("#sp2").attr("class","");
	    $("#sp3").attr("class","");
	    $("#sp4").attr("class","");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        $("div.op4").each(function() {
    		$(this).css("display","");
    	});
		_resetFrameHeight();
	}
	if("1"==type){
		doSearch1(1);
		$("#inf").css("display","none");
		$("#inf1").css("display","");
		$("#inf2").css("display","none");
		$("#inf3").css("display","none");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","on");
	    $("#sp3").attr("class","");
	    $("#sp4").attr("class","");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        $("div.op4").each(function() {
    		$(this).css("display","");
    	});
	    _resetFrameHeight();
	}
	if("4"==type){
		doSearch2(1);
		$("#inf").css("display","none");
		$("#inf1").css("display","none");
		$("#inf2").css("display","none");
		$("#inf3").css("display","");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","");
	    $("#sp3").attr("class","");
	    $("#sp4").attr("class","on");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        $("div.op4").each(function() {
    		$(this).css("display","");
    	});
        doSearch3(1);
	    _resetFrameHeight();
	}
	if("3"==type){
		doSearch2(1);
		$("#inf").css("display","none");
		$("#inf1").css("display","none");
		$("#inf2").css("display","");
		$("#inf3").css("display","none");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","");
	    $("#sp3").attr("class","on");
	    $("#sp4").attr("class","");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        $("div.op2").each(function() {
    		$(this).css("display","");
    	});
        $("div.op3").each(function() {
    		$(this).css("display","");
    	});
        $("div.op4").each(function() {
    		$(this).css("display","");
    	});
	    _resetFrameHeight();
	}
}
 function updateStatus(id,type,searchType){
	 var count = window.parent.document.getElementById('zjshTip').innerHTML;
	 if(type=="1"){
	 $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!updateForLeaveStatus.action",
            dataType:'json',
            data:{"id":id,"type":type},
            success:function (result) {
                if ("0" == result.code) {
	 				alert("操作成功！");
                	window.parent.document.getElementById('zjshTip').innerHTML = count - 1;
                	changeDiv(searchType);
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
            url:"${baseURL}/membercenter/membercenterAction!updateForLeaveStatus.action",
            dataType:'json',
            data:{"id":id,"type":type},
            success:function (result) {
                if ("0" == result.code) {
                	alert("操作成功！");
                	window.parent.document.getElementById('zjshTip').innerHTML = (count)*1 + 1;
                    changeDiv(searchType);
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");  
            }
        });
	 }
	 if(type=="3"){
	 $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!updateSingInStatus.action",
            dataType:'json',
            data:{"id":id},
            success:function (result) {
                if ("0" == result.code) {
                	alert("操作成功！");
                   changeDiv(searchType);
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