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
<link type="text/css" rel="stylesheet" href="../css/l_style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${baseURL}/jsp/web/js/forePager.js"></script>
<link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
<div id="main">



<div class="activity_right fl mt10 pb20" id="checkDiv" style="display: none">
    <div class="act_top">思想汇报</div>
    <div class="act_bd">
        <div class="act_tabWrap mt10">
            <div class="act_tab_hd clearfix" datasrc="${baseURL}/membercenter/membercenterAction!checkLoginUser.action"  >
                <input type="hidden" name="result" id="result"/>
                <span class="on" onclick="changeDiv(1);" id="sp1">我的思想汇报</span><span showOn="@{result}==1" onclick="changeDiv(2);" id="sp2">书记审阅</span>
            </div>
            <div class="act_tab_bd clearfix" id="inf" style="display: block" datasrc="${baseURL}/membercenter/membercenterAction!searchUserReportList.action">
                <div class="searchbox" >
                    <span>状态</span>
                    <select name="" id="sid1" class="w100">
                        <option value="">全部</option>
                        <option value="9">草稿</option>
                        <option value="0">未审核</option>
                        <option value="1">审核通过</option>
                        <option value="2">审核不通过</option>
                    </select>
                    <input type="text" id="keyWord1" class="inputstyle w230" placeholder="输入标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch(1)" class="redBtn"/>
                    <input type="button" value="写思想汇报" onclick="window.location.href='userReportAdd.jsp'" class="greenBtn3 ml10"/>
                </div>

                <table class="tb1 mt10" id="list" width="100%" >
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>提交时间</td>
                        <td>审阅状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/userReportView.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createTime"></td>
                        <td name="statusDesc"></td>
                        <td>
                        <div style="display: none" class="op1">
                        <a href="${baseURL}/jsp/web/membercenter/userReportEdit.jsp?id=@{id}" showOn="@{status}==9"  class="fgreen">编辑</a>
                        <a href="javascript:void(0)" onclick="delReport('@{id}');" showOn="@{status}==9"  class="fred ml15">删除</a>
                        <a href="javascript:void(0)" showOn="@{status}==2"  class="fred ml15">查看原因</a>
                        </div>
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

            <div class="act_tab_bd clearfix" id="inf1" style="display: none" datasrc="${baseURL}/membercenter/membercenterAction!searchUserReportByOrgIdList.action">
                <div class="searchbox">
                    <input type="text" id="keyWord2" class="inputstyle w230" placeholder="输入发起人，标题关键字"/>
                    <input type="button" value="搜索" onclick="doSearch1(1)" class="redBtn"/>
                </div>

                <table class="tb1 mt10" id="list1" width="100%" >
                    <thead>
                    <tr>
                        <td width="40%">标题</td>
                        <td>提交时间</td>
                        <td>审阅状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td><a href="${baseURL}/jsp/web/membercenter/userReportView.jsp?id=@{id}"><span name="title"></span></a></td>
                        <td name="createTime"></td>
                        <td name="statusDesc"></td>
                        <td><a href="javascript:void(0)" onclick="showDiv('@{id}');" class="fgreen">审阅</a></td>
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
<div id="examine" title="审阅意见">
 <form id="examine_form_save" action="${baseURL}/membercenter/membercenterAction!updateUserReport.action" method="post">
 <input type="hidden" name="tbIdeologyReportPO.id" id="id"/>
 <input type="hidden" name="tbIdeologyReportPO.status" id="status"/>
         <dl >
          <dd >
          <textarea  cols="50"  name="tbIdeologyReportPO.postil"></textarea>
        </dd>
       </dl>
</form>
</div>
</div>
<script type="text/javascript">
 $(document).ready(function(){
    	initPageBar();//生成前台分页工具
    	initPageBar1();//生成前台分页工具
    	$("#checkDiv").css("display","");
    	$("div.op1").each(function() {
    		$(this).css("display","");
    	});
    	
    });
  $("#examine").dialog({
        autoOpen:false,
        height:220,
        width:450,
        modal:true,
        buttons:{
	        "审核通过":function(){
	            $("#status").val("1");
	        	updateStatus();
	        },
	        "审核不通过":function(){ 
	        	$("#status").val("2");
	        	updateStatus();
	        },
            "关闭":function () {
                $(this).dialog("close");
            }
        },
        close:function () {

        }
    });
 function showDiv(id) {
	 $("#id").val(id);
        $("#examine").dialog("open");
    }
 function updateStatus(){
	  $("#examine_form_save").ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                	alert(result.desc);
                	$("#examine").dialog("close");
                	changeDiv(2);
                } else {
                    alert(result.desc);
                    $("#examine").dialog("close");
                    changeDiv(2);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");  
            }
        });
 }
 function doSearch($pageIndex){
	    var status=$("#sid1").val();
	    var keyword=$("#keyWord1").val();
    	var page=$pageIndex;
    	$("#inf").removeData("pageData");
    	$('#inf').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchUserReportList.action?page="+page+"&searchValue.status="+status+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf");
    	 $("#keyWord1").val(keyword)
    	 $("#sid1").val(status);
    	 $("div.op1").each(function() {
    		$(this).css("display","");
    	});
        initPageBar();
    }
  function doSearch1($pageIndex){
	    var keyword=$("#keyWord2").val();
    	var page=$pageIndex;
    	$("#inf1").removeData("pageData");
    	$('#inf1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchUserReportByOrgIdList.action?page="+page+"&searchValue.keyword="+encodeURI(encodeURI(keyword)));   
    	 _redraw("inf1");
    	 $("#keyWord2").val(keyword);
        initPageBar1();
    }
  function changeDiv(type){
	if("1"==type){
		doSearch(1);
		$("#inf").css("display","");
		$("#inf1").css("display","none");
	    $("#sp1").attr("class","on");
	    $("#sp2").attr("class","");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
		_resetFrameHeight();
	}
	if("2"==type){
		if($("#result").val()!="1"){
			return;
		}
		doSearch1(1);
		$("#inf").css("display","none");
		$("#inf1").css("display","");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","on");
	    $("div.op1").each(function() {
    		$(this).css("display","");
    	});
	    _resetFrameHeight();
	}
}

   function delReport(id){
	    $.ajax({
            url:"${baseURL}/membercenter/membercenterAction!delUserReport.action",
            dataType:'json',
            data:{"id":id},
            success:function (result) {
                if ("0" == result.code) {
                	alert(result.desc);
                	changeDiv("1");
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