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
    <div class="act_top">我的积分</div>
    <div class="act_bd">
        <div class="act_tabWrap mt10">
            <div class="act_tab_hd clearfix">
                <span class="on" onclick="changeDiv(1);" id="sp1">积分明细</span><span onclick="changeDiv(2);" id="sp2">积分排名</span>
            </div>
            <div class="act_tab_bd clearfix" id="inf" style="display: block" >
                <div class="searchbox" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScore.action">
                    <span>累积总积分</span>
                    <span name="scorevo.tatalScore"></span>&nbsp;&nbsp;&nbsp;
                    <span>昨天获得积分</span>
                    <span name="scorevo.lastdayScore"></span>&nbsp;&nbsp;&nbsp;
                    <span>当前排名</span>
                    <span name="scorevo.sort"></span>&nbsp;&nbsp;&nbsp;
                    <a href="javascript:changeDiv(2);" style="color: red">》》点击查看排名</a>
                </div>

                <table class="tb1 mt10" id="list" width="100%" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScoreList.action">
                    <thead>
                    <tr>
                        <td width="40%">积分类型</td>
                        <td>分数</td>
                        <td>获得时间</td>
                        <td>获取说明</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td name="scoreTypeDesc"></td>
                        <td name="score"></td>
                        <td name="getTime"></td>
                        <td name="scoreFromDesc"></td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScoreList.action">
	  			<input type="hidden" id="totalRows" value="@{totalRows}"/>
	  			<input type="hidden" id="maxPage" value="@{maxPage}"/>
	  			<input type="hidden" id="currPage" value="@{currPage}"/>
                </div>

                
            </div>

            <div class="act_tab_bd clearfix" id="inf1" style="display: none" >
                <div class="searchbox" datasrc="${baseURL}/membercenter/membercenterAction!searchPersonalScore.action">
                    <input type="hidden" name="type" id="type"/>
                    <span>当前排名</span>
                    <span name="scorevo.sort"></span>&nbsp;&nbsp;&nbsp;
                    <a href="javascript:changeSort(1);" style="color: red">向上排序</a>&nbsp;&nbsp;&nbsp;
                    <a href="javascript:changeSort(2);" style="color: red">向下排序</a>&nbsp;&nbsp;&nbsp;
                    <a href="javascript:changeSort(3);" style="color: red">查看我的排名</a>
                </div>

                <table class="tb1 mt10" id="list1" width="100%" datasrc="${baseURL}/membercenter/membercenterAction!searchAllScoreList.action">
                    <thead>
                    <tr>
                        <td width="40%">名次</td>
                        <td>姓名</td>
                        <td>星级</td>
                        <td>积分</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr name="pageData">
                        <td name="ranking"></td>
                        <td name="name"></td>
                        <td name="leaveDesc"></td>
                        <td name="accumulativeScore"></td>
                    </tr>
                    </tbody>
                </table>
                <div class="pagewrap mt10" id="pagerBar1" datasrc="${baseURL}/membercenter/membercenterAction!searchAllScoreList.action">
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
	    
    	var page=$pageIndex;
    	$("#list").removeData("pageData");
    	$('#list').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchPersonalScoreList.action?page="+page);   
    	$("#pagerBar").removeData("pageData");
    	$('#pagerBar').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchPersonalScoreList.action?page="+page); 
    	 _redraw("list");
        _redraw("pagerBar");
        initPageBar();
    }
  function doSearch1($pageIndex){
	    var type=$("#type").val();
    	var page=$pageIndex;
    	$("#list1").removeData("pageData");
    	$('#list1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchAllScoreList.action?page="+page+"&searchValue.type="+type);   
    	$("#pagerBar1").removeData("pageData");
    	$('#pagerBar1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchAllScoreList.action?page="+page+"&searchValue.type="+type);  
    	 _redraw("list1");
        _redraw("pagerBar1");
        initPageBar1();
    }
    function changeSort(type){
	    $("#type").val(type);
    	var page=1;
    	$("#list1").removeData("pageData");
    	$('#list1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchAllScoreList.action?page="+page+"&searchValue.type="+type);   
    	$("#pagerBar1").removeData("pageData");
    	$('#pagerBar1').attr("datasrc","${baseURL}/membercenter/membercenterAction!searchAllScoreList.action?page="+page+"&searchValue.type="+type);  
    	 _redraw("list1");
        _redraw("pagerBar1");
        initPageBar1();
    }
  function changeDiv(type){
	if("1"==type){
		$("#inf").css("display","");
		$("#inf1").css("display","none");
	    $("#sp1").attr("class","on");
	    $("#sp2").attr("class","");
		_resetFrameHeight();
	}
	if("2"==type){
		$("#inf").css("display","none");
		$("#inf1").css("display","");
		$("#sp1").attr("class","");
	    $("#sp2").attr("class","on");
	    _resetFrameHeight();
	}
}
 </script>
  </head>
  </html>