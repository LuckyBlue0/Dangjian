<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>编辑</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/dqdpconfig/config!add.action" method="post" id="id_form_add">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">新增配置</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tbody>
            <input type="hidden" name="configVO.configId"/>
            <input type="hidden" name="modelCode"/>
            <tr>
                <td  class="tdBlue">配置名</td>
                <td ><input  type="text" name="configVO.configName" valid="{must:true,length:50,tip:'配置名'}" /></td>
            </tr>
            <tr>
                <td class="tdBlue">配置值</td>
                <td ><input  class="form120px" name="configVO.configValue" type="text" valid="{must:true,length:300,tip:'配置值'}"/></td>
            </tr>
            <tr>
                <td  class="tdBlue">模块名</td>
                <td ><input   type="text" name="configVO.componentName" valid="{must:true,length:100,tip:'模块名'}"/></td>
            </tr>
        </tbody>
    </table>
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="doSave();" value="保存"/>
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">
    function doSave() {
        _doCommonSubmit("id_form_add",null,{ok:function(){history.back();},fail:function(){}});
    }
</script>
</body>
</html>
