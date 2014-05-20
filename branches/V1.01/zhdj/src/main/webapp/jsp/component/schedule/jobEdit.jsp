<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="taskStatus"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看调度任务</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    .btnQuery {
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    height: 24px;
    line-height: 24px;
    padding-bottom: 3px;
    width: 68px;
     }
    </style>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/schedulemgr/schedulemgr!ajaxUpdateJob.action" method="post" id="job_edit">
    <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">修改调度任务</h2>
            </div>
        </div>
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0"
           dataSrc="${baseURL}/schedulemgr/schedulemgr!ajaxViewJobById.action?id=${param.jobId}">
        <input type="hidden" name="jobVO.jobId"/>

        <tbody>
            <tr>
                <td width="120" class="tdBlue">任务名称</td>
                <td width="280">
                    <input type="text" class="form120px" name="jobVO.jobName" readonly="true"/>
                </td>
                <td width="120" class="tdBlue">任务组名称</td>
                <td width="280">
                    <input type="text" class="form120px" name="jobVO.jobGroup" valid="{must:true,length:20,tip:'任务名称'}"/>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">执行类</td>
                <td width="280">
                    <input type="text" class="form120px" name="jobVO.className" valid="{must:true,tip:'执行类'}"/>
                </td>
                <td width="120" class="tdBlue">IP：</td>
                <td width="280">
                    <input type="text" class="form120px" name="jobVO.ip"/>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">cron表达式</td>
                <td width="280">
                    <input type="text" class="form120px" name="jobVO.cronExpression" valid="{must:true,tip:'cron表达式'}"/>
                    <a href="" onclick="javascript:openCronBuilder();return false;">cron表达式生成器</a>
                </td>
                <td width="120" class="tdBlue"></td>
                <td width="280">
                    <input type="hidden" name="jobVO.status" value=""/>
                    <%--<select dictType="taskStatus" name="jobVO.status"/>--%>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">
                    任务描述
                </td>
                <td colspan="3">
                    <textarea name="jobVO.jobDesc" style="width: 800px;height:70px" disabled="true"></textarea>
                </td>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btnQuery" id="save" type="button" value="保存"/>
        <input class="btnQuery" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    var allowCommit = true;

    $(document).ready(function () {

    });

    $("#save").click(function () {
        var formCheck = new Dqdp();
        if (formCheck.submitCheck("job_edit") && allowCommit) {
            $("#job_edit").ajaxSubmit({
                dataType:"json",
                success:function (result) {
                    if (result.code == "0") {
                        alert(result.desc);
                        window.location.href=("jobList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                    } else {
                        alert(result.desc);
                    }
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }
    });

    $("#className").blur(function () {
        $.ajax({
            url:"${baseURL}/schedulemgr/schedulemgr!isClassNameLegal.action",
            type:"post",
            dataType:"json",
            data:{
                "className":$("#className").val()
            },
            success:function (result) {
                $(".error").html("");
                if ("0" != result.code) {
                    $("#className").parent().append("<p class='error'><font color='red'>" + result.desc + "</font></p>");
                } else {
                    allowCommit = true;
                }
            }
        });
    });

    function openCronBuilder() {
        window.open("cronBuilder.jsp?dqdp_csrf_token="+dqdp_csrf_token, "cron表达式生成器", "height=600, width=1060, toolbar=no, menubar=no, resizable=yes,location=no, status=no");
    }

    function setCronExpression(cronExpression) {
        $("[name=jobVO\\.cronExpression]").val(cronExpression);
    }

</script>

</body>
</html>
