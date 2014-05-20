<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="clientType"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看版本</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${baseURL}/css/progressBar.css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/progressBar.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<form action="" method="post" id="org_add" enctype="multipart/form-data">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/suggestion/suggestionAction!ajaxView.action?id=${param.id}">
        <thead>
            <tr>
                <th colspan="2">查看记录</th>  
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="tdBlue">用户名</td>
                <td name="tbSuggestionPO.userName">
                </td>
            </tr>
           
            <tr>
                <td class="tdBlue">平台类型</td>
                <td name="tbSuggestionPO._type_desc">
                </td>
            </tr>
            
            <tr>
                <td class="tdBlue">提交时间</td>
                <td name="tbSuggestionPO.createTime">
                 </td>
            </tr>
            <tr>
                <td class="tdBlue">反馈意见</td>
                <td name="tbSuggestionPO.suggestion">
                </td>
            </tr>
          
         
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

</script>

</body>
</html>
