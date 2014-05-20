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
    <div class="act_top">志愿活动</div>
    <div class="act_bd">
        <div class="act_tabWrap mt10">
            <div class="act_tab_hd clearfix">
                <span class="on" onclick="changeDiv(1);" id="sp1">志愿活动</span><span onclick="changeDiv(2);" id="sp2">已报名</span>
            </div>
            <div class="act_tab_bd clearfix" id="inf" style="display: block" datasrc="${baseURL}/membercenter/membercenterAction!searchAllVolunteerActivity.action">
                <div class="searchbox">
                    <input type="text" id="keyWord1" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list" width="100%" >
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>发起人</td>
                        <td>发起时间</td>
                        <td>开展状态</td>
                        <td>报名状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/userVolunteerActivityDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="statusDesc"></td>
                        <td name="signUpStatusDesc"></td>
                        <td>
	                        <a href="javascript:void(0)" showOn="@{signUpStatus} == 0" onclick="updateStatus('@{id}','1')"  class="fgreen">我要报名</a>
                        </td>
                    	
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>

            <div class="act_tab_bd clearfix" id="inf1" style="display: none" datasrc="${baseURL}/membercenter/membercenterAction!searchVolunteerActivity.action">
                <div class="searchbox">
                    <input type="text" id="keyWord2" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch1(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list1" width="100%" >
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
                        <td><a href="${baseURL}/jsp/web/membercenter/userVolunteerActivityDetail.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createUserName"></td>
                        <td name="createTime"></td>
                        <td name="statusDesc"></td>
                        <td><a href="javascript:void(0)" showOn="@{signUpStatus} == 0" onclick="updateStatus('@{id}','2')"class="fgreen">取消报名</a></td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar1">
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
    	initPageBar();//生成前台分页工具
    	initPageBar1();//生成前台分页工具

    });
 function doSearch($pageIndex){
	    var keyword=$("#keyWord1").val();
    	var page=$pageIndex;
    	$("#inf").removeData("pageData");
    	$('#inf').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchAllVolunteerActivity.action?page="+page+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf");
    	 $("#keyWord1").val(keyword)
        initPageBar();
    }
  function doSearch1($pageIndex){
	    var keyword=$("#keyWord2").val();
    	var page=$pageIndex;
    	$("#inf1").removeData("pageData");
    	$('#inf1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchVolunteerActivity.action?page="+page+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf1");
    	 $("#keyWord2").val(keyword)
        initPageBar1();
    }
  function changeDiv(type){
	if("1"==type){
		doSearch(1);
		$("#inf").css("display","");
		$("#inf1").css("display","none");
	    $("#sp1").attr("class","on");
	    $("#sp2").attr("class","");
		_resetFrameHeight();
	}
	if("2"==type){
		doSearch1(1);
		$("#inf").css("display","none");
		$("#inf1").css("display","");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","on");
	    _resetFrameHeight();
	}
}
   function updateStatus(id,type){
	 $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!signUp.action",
            dataType:'json',
            data:{"id":id,"type":type},
            success:function (result) {
                if ("0" == result.code) {
                	alert(result.desc);
                	changeDiv(type);
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");  
            }
        });
	 
	
 }
 </script>
  </head>
  </html>