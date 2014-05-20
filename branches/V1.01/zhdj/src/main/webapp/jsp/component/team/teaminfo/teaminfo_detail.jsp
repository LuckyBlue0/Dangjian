<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="leaderStatus"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    
        <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
        <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
            </head>

<body templateId="SQLPager">

<table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body" datasrc="local:var_d8c19487bde14956b99cdece655186c2" templateId="default" dqdpCheckPoint="view_fields">
    <thead>
        <tr>
            <th colspan="4">新增报备</th>
        </tr>
    </thead>
    <tbody>
                                <tr dqdpCheckPoint="view_field_teamTypeId">
                        <td width="120" class="tdBlue">集体类别：</td>
                        <td width="280" name="tbTeamInfoPO.teamTypeId" />
                                                                                                    <td width="120" class="tdBlue">集体主题名称：</td>
                        <td width="280" name="tbTeamInfoPO.teamTitle" />
                                                                                </tr>
                                <tr dqdpCheckPoint="view_field_context">
                        <td width="120" class="tdBlue">集体内容：</td>
                        <td width="280" name="tbTeamInfoPO.context" />
                                                                                                    <td width="120" class="tdBlue">集体图片：</td>
                        <td width="280" name="tbTeamInfoPO.image" />
                                                                                </tr>
                                <tr dqdpCheckPoint="view_field_author">
                        <td width="120" class="tdBlue">发布人：</td>
                        <td width="280" name="tbTeamInfoPO.author" />
                                                                                                    <td width="120" class="tdBlue">发布时间：</td>
                        <td width="280" name="tbTeamInfoPO.releaseDate" />
                                                                                </tr>
                                <tr dqdpCheckPoint="view_field_status">
                        <td width="120" class="tdBlue">状态：</td>
                        <td width="280" name="tbTeamInfoPO.status" />
                                                                                                    <td width="120" class="tdBlue">创建时间：</td>
                        <td width="280" name="tbTeamInfoPO.createTime" />
                                                                                </tr>
                    </tbody>
</table>
<script type="text/javascript">
var var_d8c19487bde14956b99cdece655186c2=_doGetDataSrc("${baseURL}/teaminfo/teaminfoAction!ajaxView.action?id=${param.id}","");
</script>

<div class="toolBar" templateId="default">
    <div align="center">
                <input class="btn4" type="button" id="8b554aa5ddb6457c9d758e57ab8b46ff" onclick="javascript:history.go(-1)" value="返回"/>
            </div>
</div>

</body>
</html>
