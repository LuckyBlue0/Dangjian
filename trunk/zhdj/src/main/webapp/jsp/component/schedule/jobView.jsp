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
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<div class="searchWrap">
            <div class="title">
                <h2 class="icon1">查看调度任务</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
<table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0"
       dataSrc="${baseURL}/schedulemgr/schedulemgr!ajaxViewJobById.action?id=${param.jobId}">

    <tbody>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">任务名称</td>
            <td width="280" name="jobVO.jobName" style="border:1px solid #e0e0e0;">
            </td>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">任务组名称</td>
            <td width="280" name="jobVO.jobGroup" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">执行类</td>
            <td width="280" name="jobVO.className" style="border:1px solid #e0e0e0;">
            </td>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">IP</td>
            <td width="280" name="jobVO.ip" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">cron表达式</td>
            <td width="280" name="jobVO.cronExpression" style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">状态</td>
            <td width="280" name="jobVO.statusDesc" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">运行状态</td>
            <td width="280" name="jobVO.triggerStatusDesc" style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">下次运行时间</td>
            <td width="280" name="jobVO.nextRunDate" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">成功运行次数</td>
            <td width="280" name="jobVO.successCount" style="border:1px solid #e0e0e0;"></td>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">失败运行次数</td>
            <td width="280" name="jobVO.failCount" style="border:1px solid #e0e0e0;"></td>
        </tr>
        <tr>
            <td width="120" class="tdBlue" style="border:1px solid #e0e0e0;">
                任务描述
            </td>
            <td colspan="3" style="border:1px solid #e0e0e0;">
                <textarea name="jobVO.jobDesc" style="width: 800px;height:70px" disabled="true"></textarea>
            </td>
        </tr>
    </tbody>
</table>
</div>

<div class="searchWrap" style="padding-bottom: 500px">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">任务运行记录</h2>
            </div>
            <div id="runRecordList"></div>
            <div class="toolBar">
                <div class="page" id="downIdPage">
                </div>
            </div>
            <div class="toolBar">
        <div align="center">
            
            <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
        </div>
    </div>
        </tr>
    </table>
     
</div>



<!--工具栏 end-->

<script type="text/javascript">
    $(document).ready(function () {
        doSearch(1);
    });

    function doSearch($pageIndex) {
        $.ajax({
            url:"${baseURL}/schedulemgr/schedulemgr!listJobLog.action",
            data:{
                "id":"${param.jobId}",
                "page":$pageIndex
            },
            dataType:"json",
            type:"post",
            success:function (result) {
                if ("0" == result.code) {
                    var logList = new ListTable({
                        title:[
                            {width:"30%", name:"completeDate", showName:"开始时间"},
                            {width:"30%", name:"completeDate", showName:"完成时间"},
                            {width:"20%", name:"executeResultDesc", showName:"运行结果"},
                            {width:"20%", name:"operation", showName:"操作", isOperationColumn:true}
                        ],
                        data:result.data.pageData,
                        operations:[
                            {name:"查看异常信息", permission:"",
                                condition:function (index, content) {
                                    return "1" == content.executeResult;
                                },
                                event:function (index, content) {
                                    $.ajax({
                                        url:"${baseURL}/schedulemgr/schedulemgr!viewJobExceptionByLogId.action",
                                        type:"post",
                                        dataType:"json",
                                        data:{
                                            "id":content.logId
                                        },
                                        success:function (result) {
                                            if ("0" == result.code) {
                                                $("#exceptionInfo").val(result.data.exceptionStr);
                                            } else {
                                                alert(result.desc);
                                            }
                                        },
                                        error:function () {
                                            alert("通讯错误");
                                        }
                                    });
                                }}
                        ],
                        trStyle:["trWhite"]
                    });
                    logList.createList("runRecordList");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("downIdPage");
                } else {
                    $("#tip").error({title:"错误", content:result.desc, button:[
                        {name:"确定", event:"_hideMsg()"}
                    ]});
                }
            }
        });
    }
</script>

</body>
</html>
