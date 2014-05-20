<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/ajaxfileupload.js"></script>
    <script src="${baseURL}/js/3rd-plug/jsmart/smart-2.9.min.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
    <style type="text/css">

    </style>
</head>

<body>
<form action="${baseURL}/demomodel/demomodel!add.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body"
           dataSrc="local:v1">
        <thead>
            <tr>
                <th colspan="4">新增报备</th>
            </tr>
        </thead>
        <tbody>
            #foreach($poName in ${poList})
            <tr>
                <td width="120" class="tdBlue">报备类型：</td>
                <td width="280">
                    <input type="radio" name="testVB.radio1" id="ra1" value="0" onclick="change();" checked="checked"/>
                </td>
                <td width="120" class="tdBlue">考勤卡号：</td>
                <td width="280"><input class="form120px" name="testVB.carId" type="text" valid="{must:true,fieldType:'pattern',regex:'^\\d$',tip:'测试'}"/></td>
            </tr>
            #end
        </tbody>
    </table>
</form>


<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" id="save" onclick="doSave();" value="保存"/>
        <input class="btn4" type="button" onclick="doFresh();" value="刷新"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">
    var v1 = _doGetDataSrc("${baseURL}/demomodel/demomodel!testAddGet.action?id=${personId}", null);

    //    function doSave() {
    //        alert(_doCommonSubmit("id_form_search")) ;
    //    }

    $('#save').click(function () {
        (_doCommonSubmit("id_form_search", "", ""));
    });
    function doFresh() {
//        _initElementValue("id_form_search", true);
        _redraw();
    }
    $(document).ready(function () {
        doTest();
    });
    function doTest() {
        var tplText = document.getElementById('test_tpl').innerHTML;
        var data = {HAHA:"CCCC" };
        var tpl = new jSmart(tplText);
        var res = tpl.fetch(data);
    }
</script>
</body>
</html>
