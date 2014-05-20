<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>心得</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/jsp/web/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <script type="text/javascript" src="${baseURL}/jsp/web/js/forePager.js"></script>
    <style type="text/css">
    	#pagerBar {
    text-align: right;
    padding-right:20px;
}

#pagerBar .num {
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CCCCCC;
    color: #0066CC;
    display: inline-block;
    height: 23px;
    line-height: 23px;
    margin-right: 3px;
    text-align: center;
    vertical-align: middle;
    width: 25px;
    margin-right:10px;
}
    	
    </style>
</head>

<body> 
<!--头部 end-->


<!--公告 end-->
<div id="list" style="background: none;" class="searchWrap" dataSrc="${baseURL}/activityTips/activityTipsAction!ajaxSearch.action?id=${param.id}&type=${param.type}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1" name="type"></h2>
            </div>
        </tr>
    </table>
    <div class="text_bg">
      <h3 class="about" name="title"></h3>
      <div name="pageData">
	      <div class="iBox">
	           <div class="iBox-top clearfix">
	               <div class="iLeft fl ml10">
	                   <span name="userName"></span>
	                   <span name="organizationName"></span>
	               </div>
	               <div class="iRight fr mr10">
	                   <span name="createTime"></span>
	                   <span name="sourceDesc"></span>
	               </div>
	           </div>
	           <div class="iBox-bd p10 lh24">
	               <p class="t2" name="content"></p>
	           </div>
	       </div>
       </div>
            
    </div>
    <div style="margin-top: 20px;" showOn="@{totalRows}>0" id="pagerBar">
			<input type="hidden" id="totalRows" value="@{totalRows}"/>
			<input type="hidden" id="maxPage" value="@{maxPage}"/>
			<input type="hidden" id="currPage" value="@{currPage}"/>
        </div>
</div>

<script type="text/javascript">
 $(document).ready(function(){
    initPageBar();//生成前台分页工具
	flashIframe_GDZC();
 });
 
function doSearch($pageIndex){
	    
    	var page=$pageIndex;
    	$("#list").removeData("pageData");
    	$('#list').attr("datasrc","${baseURL}/activityTips/activityTipsAction!ajaxSearch.action?id=${param.id}&type=${param.type}&page="+page);   
        _redraw("list");
        initPageBar();
        flashIframe_GDZC()
    }
    function flashIframe_GDZC(){
    	var i=parent.document.getElementById("ifm");
    	if(i!=null)
    	{	
    		if(document.body.scrollHeight<700)
    		{
    			i.style.height ="730px";
    		}else
    		{
    			i.style.height = (document.body.scrollHeight+30)+"px";
    		}
    	}
    }
</script>

</body>

</html>
