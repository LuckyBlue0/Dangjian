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
    <title>新增调度任务</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
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
<form action="${baseURL}/schedulemgr/schedulemgr!ajaxAddJob.action" method="post" id="job_add">

<div class="searchWrap">
            <div class="title">
                <h2 class="icon1">新增调度任务</h2>
            </div>
        </div>
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tbody>
            <tr>
                <td width="120" class="tdBlue">任务名称</td>
                <td colspan="1"><input class="form120px" name="jobVO.jobName" type="text"
                                       valid="{must:true,length:20,tip:'任务名称'}"/><span><font color="red">*</font></span>
                    请控制在1到20字以内
                </td>
                <td width="120" class="tdBlue">任务组名称</td>
                <td colspan="1"><input class="form120px" name="jobVO.jobGroup" type="text"
                                       valid="{must:true,length:20,tip:'任务组名称'}"/><span><font color="red">*</font></span>
                    请控制在1到20字以内
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">执行类</td>
                <td colspan="1"><input class="form120px" name="jobVO.className" id="className" type="text"
                                       valid="{must:true,tip:'执行类'}"/><span><font color="red">*</font></span>
                </td>
                <td width="120" class="tdBlue">IP</td>
                <td colspan="1"><input class="form120px" name="jobVO.ip" type="text"/>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">cron表达式</td>
                <td><input type="text" name="jobVO.cronExpression" valid="{must:true,tip:'cron表达式'}" />
                    <a href="" onclick="javascript:openCronBuilder();return false;">cron表达式生成器</a>
                </td>
                <td width="120" class="tdBlue"></td>
                <td colspan="1">
                    <input type="hidden" name="jobVO.status" value="0"/>
                    <%--<select name="jobVO.status" dictType="taskStatus" defaultValue="0"/>--%>
                </td>
            </tr>
            <tr>
                <td width="100" class="tdBlue">
                    任务描述
                </td>
                <td colspan="3">
                    <textarea name="jobVO.jobDesc" style="width: 800px;height:70px"></textarea>
                </td>
            </tr>
        </tbody>
    </table>

    <div id="cronTrigger">
        常用cron表达式：
        <div>
            <input type="radio" name="usualCronExp" value="0 0 12 * * ?"/>每天12点触发<br/>
            <input type="radio" name="usualCronExp" value="0 15 10 ? * *"/>每天10点15分触发<br/>
            <input type="radio" name="usualCronExp" value="0 15 10 * * ?"/>每天10点15分触发<br/>
            <input type="radio" name="usualCronExp" value="0 15 10 * * ? *"/>每天10点15分触发<br/>
            <input type="radio" name="usualCronExp" value="0 15 10 * * ? 2005"/>2005年每天10点15分触发<br/>
            <input type="radio" name="usualCronExp" value="0 * 14 * * ?"/>每天14点到15点之间每分钟运行一次，开始于14:00，结束于14:59<br/>
            <input type="radio" name="usualCronExp" value="0 0/5 14 * * ?"/>每天14点到15点每5分钟运行一次，开始于14:00，结束于14:55<br/>
            <input type="radio" name="usualCronExp" value="0 0/5 14,18 * * ?"/>每天14点到15点每5分钟运行一次，此外每天18点到19点每5钟也运行一次<br/>
            <input type="radio" name="usualCronExp" value="0 0-5 14 * * ?"/>每天14:00点到14:05，每分钟运行一次<br/>
            <input type="radio" name="usualCronExp" value="0 10,44 14 ? 3 WED"/>3月每周三的14:10分到14:44，每分钟运行一次<br/>
            <input type="radio" name="usualCronExp" value="0 15 10 ? * MON-FRI"/>每周一，二，三，四，五的10:15分运行<br/>
        </div>
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btnQuery" id="save" type="button" value="保存"/>
        <input class="btnQuery" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    var allowCommit = false;

    $(document).ready(function () {

        $("#save").click(function () {
            var formCheck = new Dqdp();
            if (formCheck.submitCheck("job_add") && allowCommit) {
                $("#job_add").ajaxSubmit({
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

        $("[name=usualCronExp]").click(function () {
            $("[name=jobVO\\.cronExpression]").val($(this).val());
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
