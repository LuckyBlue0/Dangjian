<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@include file="../../common/dqdp_common.jsp"%>
	<jsp:include page="../../common/dqdp_vars.jsp">
		<jsp:param name="dict" value="clientType"></jsp:param>
		<jsp:param name="permission"
			value="partyMenberManager,partyMenberSearch,partyMenberView,partyMenberAdd,partyMenberEdit,partyMenberDel"></jsp:param>
		<jsp:param name="mustPermission" value=""></jsp:param>
	</jsp:include>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>访问趋势分析</title>
		<link href="${baseURL}/themes/${style}/css/common.css"
			rel="stylesheet" type="text/css" />
		<script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js">
</script>
		<script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>">
		</script>
		<script src="${baseURL}/js/do1/common/error1.0.js">
</script>
<script src="${baseURL}/charts/JSClass/FusionCharts.js">
</script>
<script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>  
		<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js">
</script>
		<script src="${baseURL}/js/do1/common/pop_up1.0.js">
</script>
		<style type="text/css">
</style>
	</head>

	<body style="background: #f6ebe5;">
		<!--头部 end-->


		<!--公告 end-->
		<div class="searchWrap">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="searchLeft"></td>
					<div class="title">
						<h2 class="icon1">
							访问情况
						</h2>
					</div>
				</tr>
			</table>
		</div>

       <div>
        <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%" datasrc="${baseURL}/access/accessAction!ajaxSearchAccessData.action">
        <thead>
            <tr>
                <th width="25%">今日访问数</th>
                <th width="25%">累计访问数</th>
                <th width="25%">今日登录数</th>
                <th width="25%">累计登录数</th>
            </tr>
        </thead>
        <tbody>
            <tr class="trWhite">
                <td class="tc" name="accessData.accessNum"></td>
                <td class="tc" name="accessData.accessAll"></td>
                <td class="tc" name="accessData.loginNum"></td>
                <td class="tc" name="accessData.loginAll"></td>

            </tr>
        </tbody>
         </table>
      </div>
      <div>
      <div style="float: left;width: 45%;">
      <br/>
      <br/>
      <h1 align="center">访问用户数趋势图</h1>
      <div id="chartPvDiv" style="float: right;"></div>
      </div>
      <div style="float: right;width: 45%;">
      <br/>
      <br/>
      <h1 align="center">登录用户数趋势图</h1>
      <div id="chartPlDiv" ></div>
     
              
      </div>
      </div>



	</body>
<script type="text/javascript">
  $(document).ready(function() {
	  popupCharts();
  });
  function popupCharts(){
           $.ajax({
            url:'${baseURL}/access/accessAction!ajaxSearchPvData.action',
            type:'post',                                     
            dataType:'json',
            success:function (result) {
            	if("0"==result.code){
            		
            		 //var chart_Div = new FusionCharts('${baseURL}/charts/Charts/MSColumn3DLineDY.swf', 'chartId', 400, 350, '0', '1'); 
    var xml="<chart caption='营销情况' XAxisName='周期' SYAxisName='办理率（%）'  palette='1' animation='1' subcaption=' ' formatNumberScale='0' showValues='0' seriesNameInToolTip='0'>"+
    		"<categories>"+
    		"<category label='9月1日'/><category label='9月2日'/><category label='9月3日'/><category label='9月4日'/>"+
    		"</categories>"+
    	"<dataset seriesName='办理率' parentYAxis='P' anchorSides='10'  anchorRadius='3' anchorBorderColor='009900'>"+
    		"<set value='20'/><set value='10'/><set value='30'/><set value='40'/>"+ 
    		"</dataset>"+
 
    		"<dataset seriesName='办理率' parentYAxis='S' anchorSides='10'  anchorRadius='3' anchorBorderColor='009900'>"+
    		"<set value='20'/><set value='10'/><set value='30'/><set value='40'/>"+
    		"</dataset>"+
    		"<styles><definition><style type='font' color='666666' name='CaptionFont' size='15' /><style type='font' name='SubCaptionFont' bold='0' /></definition><application></application></styles>"+
    		"</chart>";
            		
            		 //生成报表图形
            var chart_Div_Pv = new FusionCharts('${baseURL}/charts/Charts/MSLine.swf', 'chartId', 420, 350, '0', '1');  
            chart_Div_Pv.setDataXML(result.data.pvdata);
            chart_Div_Pv.render("chartPvDiv");
            var chart_Div_Pl = new FusionCharts('${baseURL}/charts/Charts/MSLine.swf', 'chartId1', 420, 350, '0', '1');  
            chart_Div_Pl.setDataXML(result.data.pldata);
            chart_Div_Pl.render("chartPlDiv");
            	}
            }
           });
           
        
    }
</script>
</html>
