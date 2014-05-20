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

<!--正文-->
<div id="main">


            <div class="activity_right fl mt10 pb20" datasrc="${baseURL}/membercenter/membercenterAction!viewUserReport.action?id=${param.id}">
                <div class="act_top clearfix"><span class="fl">思想汇报详情</span> <a href="${baseURL}/jsp/web/membercenter/userReportList.jsp" class="btngreen2 fr mb5">返回列表</a></div>
                <div class="act_bd p10 f666 pos_relative">
                    <h3 class="f16 fb tc" name="tbIdeologyReportPO.title"></h3>
                    <div class="biaozhu" showOn="@{tbIdeologyReportPO.status}==1" id="showDiv">√  已阅</div>
                    <p class="pt10">@{tbIdeologyReportPO.content}</p>
                </div>
            </div>

</div>
  <script type="text/javascript">
$(document).ready(function() {

});
  </script>
<!--正文 end-->
  </head>
  </html>