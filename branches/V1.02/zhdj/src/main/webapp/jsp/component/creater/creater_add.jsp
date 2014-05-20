<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
</head>

<body>
<form action="xxx.action" method="post" id="d144fbfebe5f416ca66609573dbb4a8b" onsubmit="return false;">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body">
        <thead>
            <tr>
                <th colspan="4">新增报备</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">createTime：</td>
                <td width="280">
                    <input class="form120px" type="text" name="test.createTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
                           valid="{must:false,fieldType:'datetime',regex:'',tip:'createTime'}"/>
                </td>
                <td width="120" class="tdBlue">operationId：</td>
                <td width="280">
                    <input class="form120px" name="test.operationId" type="text" valid="{must:false,fieldType:'pattern',regex:'^\\w+$',tip:'operationId'}"/>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">operationName：</td>
                <td width="280">
                    <input class="form120px" name="test.operationName" type="text" valid="{must:false,fieldType:'pattern',regex:'^\\w+$',tip:'operationName'}"/>
                </td>
                <td width="120" class="tdBlue">modelName：</td>
                <td width="280">
                    <input class="form120px" name="test.modelName" type="text" valid="{must:false,fieldType:'pattern',regex:'^\\w+$',tip:'modelName'}"/>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">operationType：</td>
                <td width="280">
                    <input class="form120px" name="test.operationType" type="text" valid="{must:false,fieldType:'pattern',regex:'^\\w+$',tip:'operationType'}"/>
                </td>
                <td width="120" class="tdBlue">operationDesc：</td>
                <td>
                    <textarea rows="3" cols="120" name="test.operationDesc" id="id_operationDesc" editor="{'soft':'kindeditor','param':''}"></textarea>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">operationResult：</td>
                <td width="280">
                    <input class="form120px" name="test.operationResult" type="text" valid="{must:false,fieldType:'pattern',regex:'^\\w+$',tip:'operationResult'}"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    function func_d144fbfebe5f416ca66609573dbb4a8b() {
        _doCommonSubmit("d144fbfebe5f416ca66609573dbb4a8b", "", "");
    }
</script>
<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" id="556c196664ae4b3495fecfa31028b617" onclick="func_d144fbfebe5f416ca66609573dbb4a8b()" value="测试按钮"/>
    </div>
</div>

</body>
</html>
