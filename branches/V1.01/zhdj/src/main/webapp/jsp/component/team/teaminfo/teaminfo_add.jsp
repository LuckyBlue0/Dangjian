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

<form action="${baseURL}/teaminfo/teaminfoAction!addTbTeamInfoPO.action" method="post" id="e7946c7896d34c84bde2258d5c859190" onsubmit="return false;" templateId="default" dqdpCheckPoint="add_form">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body" >
        <thead>
            <tr>
                <th colspan="4">新增</th>
            </tr>
        </thead>
        <tbody>
                                                <tr dqdpCheckPoint="add_field_teamTypeId">
                                <td width="120" class="tdBlue">集体类别：</td>
                                <td width="280">
                    <input class="form120px" name="tbTeamInfoPO.teamTypeId" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'集体类别'}"/>
                </td>
                                                                                                                                                                                                        <td width="120" class="tdBlue">集体主题名称：</td>
                                <td width="280">
                    <input class="form120px" name="tbTeamInfoPO.teamTitle" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'集体主题名称'}"/>
                </td>
                                                                                                                                                                                            </tr>
                                                            <tr dqdpCheckPoint="add_field_context">
                                <td width="120" class="tdBlue">集体内容：</td>
                                <td width="280">
                    <input class="form120px" name="tbTeamInfoPO.context" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'集体内容'}"/>
                </td>
                                                                                                                                                                                                        <td width="120" class="tdBlue">集体图片：</td>
                                <td width="280">
                    <input class="form120px" name="tbTeamInfoPO.image" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'集体图片'}"/>
                </td>
                                                                                                                                                                                            </tr>
                                                            <tr dqdpCheckPoint="add_field_author">
                                <td width="120" class="tdBlue">发布人：</td>
                                <td width="280">
                    <input class="form120px" name="tbTeamInfoPO.author" type="text" valid="{must:false,fieldType:'pattern',regex:'^.*$',tip:'发布人'}"/>
                </td>
                                                                                                                                                                                                        <td width="120" class="tdBlue">发布时间：</td>
                                                                                                                                <td width="280">
                    <input class="form120px" type="text" name="tbTeamInfoPO.releaseDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
                           valid="{must:false,fieldType:'datetime',regex:'',tip:'发布时间'}"/>
                </td>
                                                                                            </tr>
                                                            <tr dqdpCheckPoint="add_field_status">
                                <td width="120" class="tdBlue">状态：</td>
                                                                                <td width="280">
                    <select name="tbTeamInfoPO.status"  dictType="leaderStatus" ></select>
                </td>
                                                                                                                                                        <td width="120" class="tdBlue">创建时间：</td>
                                                                                                                                <td width="280">
                    <input class="form120px" type="text" name="tbTeamInfoPO.createTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
                           valid="{must:false,fieldType:'datetime',regex:'',tip:'创建时间'}"/>
                </td>
                                                                                            </tr>
                                            </tbody>
    </table>
    <input type="hidden" name="id" value="${param.id}"/>
</form>
<script type="text/javascript">

	    function func_e7946c7896d34c84bde2258d5c859190() {
        _doCommonSubmit("e7946c7896d34c84bde2258d5c859190", "", "");
    }
</script>
<div class="toolBar" templateId="default">
    <div align="center">
                <input class="btn4" type="button" id="4c4d51da55834c2b9b4a76543d75ede5" onclick="func_e7946c7896d34c84bde2258d5c859190()" value="提交"/>
                <input class="btn4" type="button" id="6b8c8c8c9fe844c5a73da70200d98c8d" onclick="javascript:history.go(-1)" value="返回"/>
            </div>
</div>

</body>
</html>
